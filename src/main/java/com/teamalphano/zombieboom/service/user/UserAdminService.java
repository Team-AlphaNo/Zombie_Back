package com.teamalphano.zombieboom.service.user;

import com.teamalphano.zombieboom.dto.user.AdminUserListDto;
import com.teamalphano.zombieboom.dto.user.UserLoginDto;
import com.teamalphano.zombieboom.mapper.user.UserAdminMapper;
import com.teamalphano.zombieboom.mapper.user.UserInfoMapper;
import com.teamalphano.zombieboom.model.user.UserData;
import com.teamalphano.zombieboom.model.user.UserFullData;
import com.teamalphano.zombieboom.model.user.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;

@Service
public class UserAdminService {
    private final UserAdminMapper userAdminMapper;

    public UserAdminService(UserAdminMapper userAdminMapper, UserInfoMapper userInfoMapper) {
        this.userAdminMapper = userAdminMapper;
    }

    //관리자 유저 목록
    @Transactional
    public List<UserInfo> getUserListAll(AdminUserListDto adminUserListDto) {
        return userAdminMapper.getUserListAll(adminUserListDto);
    }

    //관리자 로그인
    @Transactional
    public UserInfo AdminLogin(UserLoginDto userLoginDto) {
        UserInfo userInfo = userAdminMapper.getUserById(userLoginDto.getUserId());
        if(userInfo.getUserPwd().equals(userLoginDto.getPassword())){
            return userInfo;
        }else{
            return null;
        }
    }

    @Transactional
    public UserFullData getUserDataDetail(Integer userNo) {
        UserFullData userFullData = new UserFullData();
        UserInfo userInfo = userAdminMapper.getUserInfoByUserNo(userNo);
        UserData userData = userAdminMapper.getUserDataByUserNo(userNo);
        userFullData.setUserInfo(userInfo);
        userFullData.setUserData(userData);
        return userFullData;
    }
}
