package com.teamalphano.zombieboom.service.user;

import com.teamalphano.zombieboom.dto.user.admin.LoginParamsDto;
import com.teamalphano.zombieboom.dto.user.admin.UserListParamsDto;
import com.teamalphano.zombieboom.mapper.user.UserAdminMapper;
import com.teamalphano.zombieboom.mapper.user.UserInfoMapper;
import com.teamalphano.zombieboom.dto.user.UserDataDto;
import com.teamalphano.zombieboom.dto.user.UserFullDataDto;
import com.teamalphano.zombieboom.dto.user.UserInfoDto;
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
    public List<UserInfoDto> getUserListAll(UserListParamsDto userListParamsDto) {
        return userAdminMapper.getUserListAll(userListParamsDto);
    }

    //관리자 로그인
    @Transactional
    public UserInfoDto AdminLogin(LoginParamsDto loginParamsDto) {
        UserInfoDto userInfoDto = userAdminMapper.getUserById(loginParamsDto.getUserId());
        if(userInfoDto.getUserPwd().equals(loginParamsDto.getPassword())){
            return userInfoDto;
        }else{
            return null;
        }
    }

    @Transactional
    public UserFullDataDto getUserDataDetail(Integer userNo) {
        UserFullDataDto userFullDataDto = new UserFullDataDto();
        UserInfoDto userInfoDto = userAdminMapper.getUserInfoByUserNo(userNo);
        UserDataDto userDataDto = userAdminMapper.getUserDataByUserNo(userNo);
        userFullDataDto.setUserInfo(userInfoDto);
        userFullDataDto.setUserData(userDataDto);
        return userFullDataDto;
    }
}
