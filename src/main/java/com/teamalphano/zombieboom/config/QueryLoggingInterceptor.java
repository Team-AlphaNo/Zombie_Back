package com.teamalphano.zombieboom.config;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.Connection;
import java.util.List;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class QueryLoggingInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        // MetaObject를 사용하여 StatementHandler 내부의 MappedStatement에 접근
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        // MyBatis Configuration 가져오기
        Configuration configuration = mappedStatement.getConfiguration();

        // BoundSql 및 SQL 가져오기
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();
        List<org.apache.ibatis.mapping.ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        // 파라미터를 포함한 완전한 SQL 생성
        String completeSql = buildCompleteSql(sql, parameterMappings, parameterObject, configuration);
        // 매퍼 이름 가져오기
        String mapperName = mappedStatement.getId(); // 패키지명.매퍼명.메서드명

        // SQL 로그 출력
        System.out.println("Mapper: " + mapperName);
        System.out.println("Executing SQL: " + completeSql);

        return invocation.proceed();
    }

    private String buildCompleteSql(String sql, List<org.apache.ibatis.mapping.ParameterMapping> parameterMappings,
                                    Object parameterObject, Configuration configuration) {
        if (parameterMappings == null || parameterMappings.isEmpty() || parameterObject == null) {
            return sql;
        }

        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        StringBuilder completeSql = new StringBuilder(sql);

        // MetaObject 생성
        MetaObject metaObject = configuration.newMetaObject(parameterObject);

        // '?'를 파라미터 값으로 대체
        for (org.apache.ibatis.mapping.ParameterMapping parameterMapping : parameterMappings) {
            String propertyName = parameterMapping.getProperty();
            Object value;

            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                value = parameterObject; // 단순 타입
            } else {
                value = metaObject.getValue(propertyName); // 복잡한 객체
            }

            // SQL에서 '?'를 파라미터 값으로 대체
            int index = completeSql.indexOf("?");
            if (index != -1) {
                completeSql.replace(index, index + 1, formatValue(value));
            }
        }

        return completeSql.toString();
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "NULL";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value.toString();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(java.util.Properties properties) {
        // 필요 시 설정 추가
    }
}
