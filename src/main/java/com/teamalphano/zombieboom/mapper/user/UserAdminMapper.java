package com.teamalphano.zombieboom.mapper.user;

import com.teamalphano.zombieboom.dto.user.AdminUserListDto;
import com.teamalphano.zombieboom.dto.user.UserLoginDto;
import com.teamalphano.zombieboom.model.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserAdminMapper {

    //유저 목록
    List<UserInfo> getUserListAll(AdminUserListDto adminUserListDto);

    //유저 상세 데이터 조회
    UserInfo getUserById(String id);

}
