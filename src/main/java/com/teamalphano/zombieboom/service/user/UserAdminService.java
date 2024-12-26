package com.teamalphano.zombieboom.service.user;

import com.teamalphano.zombieboom.dto.user.admin.LoginParamsDto;
import com.teamalphano.zombieboom.dto.user.admin.UserFullDataAdminDto;
import com.teamalphano.zombieboom.dto.user.admin.UserInfoAdminDto;
import com.teamalphano.zombieboom.dto.user.admin.UserListParamsDto;
import com.teamalphano.zombieboom.mapper.user.UserAdminMapper;
import com.teamalphano.zombieboom.mapper.user.UserInfoMapper;
import com.teamalphano.zombieboom.dto.user.UserDataDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAdminService {
    private final UserAdminMapper userAdminMapper;

    public UserAdminService(UserAdminMapper userAdminMapper, UserInfoMapper userInfoMapper) {
        this.userAdminMapper = userAdminMapper;
    }

    //관리자 유저 목록
    @Transactional
    public List<UserInfoAdminDto> getUserListAll(UserListParamsDto userListParamsDto) {
        return userAdminMapper.getUserListAll(userListParamsDto);
    }

    //관리자 로그인
    @Transactional
    public UserInfoAdminDto AdminLogin(LoginParamsDto loginParamsDto) {
        UserInfoAdminDto userInfoDto = userAdminMapper.getUserById(loginParamsDto.getUserId());
        if(userInfoDto.getUserPwd().equals(loginParamsDto.getPassword())){
            return userInfoDto;
        }else{
            return null;
        }
    }

    @Transactional
    public UserFullDataAdminDto getUserDataDetail(Integer userNo) {
        UserFullDataAdminDto userFullDataDto = new UserFullDataAdminDto();
        UserInfoAdminDto userInfoDto = userAdminMapper.getUserInfoByUserNo(userNo);
        UserDataDto userDataDto = userAdminMapper.getUserDataByUserNo(userNo);
        userFullDataDto.setUserInfoAdminDto(userInfoDto);
        userFullDataDto.setUserDataDto(userDataDto);
        return userFullDataDto;
    }
}
