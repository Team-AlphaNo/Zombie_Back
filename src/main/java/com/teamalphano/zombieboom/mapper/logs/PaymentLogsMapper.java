package com.teamalphano.zombieboom.mapper.logs;

import com.teamalphano.zombieboom.dto.logs.CreatePaymentLogDto;
import com.teamalphano.zombieboom.model.logs.PaymentLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentLogsMapper {
    //전체 구입 로그
    List<PaymentLog> getPaymentLogsList();

    int insertPaymentLog(CreatePaymentLogDto paymentLog);

    void updateLogMessage(CreatePaymentLogDto paymentLog);
}
