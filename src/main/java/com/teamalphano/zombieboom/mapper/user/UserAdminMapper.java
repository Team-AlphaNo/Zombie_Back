package com.teamalphano.zombieboom.mapper.user;

import com.teamalphano.zombieboom.dto.user.admin.UserListParamsDto;
import com.teamalphano.zombieboom.dto.user.UserDataDto;
import com.teamalphano.zombieboom.dto.user.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserAdminMapper {

    //유저 목록
    List<UserInfoDto> getUserListAll(UserListParamsDto userListParamsDto);

    //유저 상세 데이터 조회
    UserInfoDto getUserById(String id);

    UserInfoDto getUserInfoByUserNo(Integer userNo);
    UserDataDto getUserDataByUserNo(Integer userNo);
}
