package com.teamalphano.zombieboom.service.user;

import com.teamalphano.zombieboom.dto.user.UserLoginDto;
import com.teamalphano.zombieboom.mapper.user.UserAdminMapper;
import com.teamalphano.zombieboom.model.user.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAdminService {
    private final UserAdminMapper userAdminMapper;

    public UserAdminService(UserAdminMapper userAdminMapper){
        this.userAdminMapper = userAdminMapper;
    }

    //관리자 유저 목록
    @Transactional(readOnly = true)
    public List<UserInfo> getUserListAll() {
        return userAdminMapper.getUserListAll();
    }

    //관리자 로그인
    public UserInfo AdminLogin(UserLoginDto userLoginDto) {
        UserInfo userInfo = userAdminMapper.getUserById(userLoginDto);
        if(userInfo.getUserPwd().equals(userLoginDto.getPassword())){
            return userInfo;
        }else{
            return null;
        }
    }

}
