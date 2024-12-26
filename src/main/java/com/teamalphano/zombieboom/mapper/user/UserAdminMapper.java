package com.teamalphano.zombieboom.mapper.user;

import com.teamalphano.zombieboom.dto.user.UserDataDto;
import com.teamalphano.zombieboom.dto.user.admin.UserInfoAdminDto;
import com.teamalphano.zombieboom.dto.user.admin.UserListParamsDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserAdminMapper {

    //유저 목록
    List<UserInfoAdminDto> getUserListAll(UserListParamsDto userListParamsDto);

    //유저 상세 데이터 조회
    UserInfoAdminDto getUserById(String id);

    UserInfoAdminDto getUserInfoByUserNo(Integer userNo);
    UserDataDto getUserDataByUserNo(Integer userNo);
}
