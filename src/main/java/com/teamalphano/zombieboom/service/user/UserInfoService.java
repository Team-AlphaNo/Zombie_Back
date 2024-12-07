package com.teamalphano.zombieboom.service.user;

import com.teamalphano.zombieboom.dto.user.GoogleLoginDto;
import com.teamalphano.zombieboom.mapper.user.UserInfoMapper;
import com.teamalphano.zombieboom.model.user.UserData;
import com.teamalphano.zombieboom.model.user.UserFullData;
import com.teamalphano.zombieboom.model.user.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoService {
    private final UserInfoMapper userInfoMapper;

    public UserInfoService(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    //게스트 회원가입

    @Transactional
    public UserFullData GuestLogin(String uuid) {
        //uuid 있는지 체크
        UserInfo userInfo = userInfoMapper.getUserInfoByUUID(uuid);
        if(userInfo == null || userInfo.getUserUuid() == null) {
            //회원가입
            System.out.println("----------------------회원가입");
            UserInfo newUserInfo = new UserInfo();
            newUserInfo.setUserUuid(uuid);

            int rowsAffected = userInfoMapper.insertGuestSignUp(newUserInfo);
            System.out.println("Rows affected by insert: " + rowsAffected);
            if (rowsAffected > 0) {
                int userNo = newUserInfo.getUserNo();
                int userData = userInfoMapper.insertDefaultUserData(userNo);
                if(userData > 0) {
                    UserFullData fullData = getUserFullData(userNo);
                    return fullData;
                }
            }else {
                return null;
            }
        }else{
            //로그인 처리
            System.out.println("로그인처리");
            int updateLogin = userInfoMapper.updateGuestSignIn(uuid);
            if(updateLogin > 0) {
                return getUserFullData(userInfo.getUserNo());
            }
        }
        return null;
    }

    //구글 로그인

    @Transactional
    public UserFullData GoogleLogin(GoogleLoginDto googleLoginDto){
        UserInfo userInfo = userInfoMapper.getUserInfoByUUID(googleLoginDto.getUserUuid());
        if(userInfo.getUserUuid() == null) {
            UserInfo _userInfo = userInfoMapper.getUserInfoByUserId(googleLoginDto.getUserId());
            if(_userInfo != null) {
                System.out.println("#########uuid는 없는데 해당 구글 아이디 있음");
                System.out.println("#########해당 구글아이디의 uuid를 업데이트");
                //uuid는 없는데 해당 구글 아이디 있음
                //해당 구글아이디의 uuid를 업데이트
                _userInfo.setUserUuid(googleLoginDto.getUserUuid());
                userInfoMapper.updateUserInfoGoogleUUID(_userInfo);
                return getUserFullData(_userInfo.getUserNo());
            }else{
                //회원가입
                System.out.println("#########회원가입");

                UserInfo newUserInfo = new UserInfo();
                newUserInfo.setUserUuid(googleLoginDto.getUserUuid());
                newUserInfo.setUserID(googleLoginDto.getUserId());
                newUserInfo.setUserName(googleLoginDto.getUserName());
                newUserInfo.setUserEmail(googleLoginDto.getUserEmail());
                int insert = userInfoMapper.insertGoogleSignUp(newUserInfo);
                if(insert > 0) {
                    int insertData = userInfoMapper.insertDefaultUserData(newUserInfo.getUserNo());
                    if (insertData > 0) {
                        int userNo = newUserInfo.getUserNo();
                        return getUserFullData(userNo);
                    }
                }
            }
        }else{
            //게스트 id 있음
            if(userInfo.getUserID() != null){
                //구글 로그인
                System.out.println("#########게스트 아이디에 구글 로그인");
                int updateLogin = userInfoMapper.updateGoogleSignIn(googleLoginDto.getUserId());
                return getUserFullData(userInfo.getUserNo());
            }else{
                //구글 아이디 찾기
                UserInfo _userInfo = userInfoMapper.getUserInfoByUserId(googleLoginDto.getUserId());
                if(_userInfo != null) {
                    //구글 아이디가 있고, 게스트 아이디도 있음
                    //아이디는 delete처리, 구글아이디에 uuid 업데이트
                    System.out.println("#########구글 아이디가 있고, 게스트 아이디도 있음");
                    System.out.println("#########아이디는 delete처리, 구글아이디에 uuid 업데이트");
                    userInfoMapper.inactiveUserInfoByUserNo(userInfo.getUserNo());
                    _userInfo.setUserUuid(googleLoginDto.getUserId());
                    userInfoMapper.updateUuidForGuestToGoogle(_userInfo);
                    return getUserFullData(_userInfo.getUserNo());
                }else{
                    //게스트 아이디는 있으나 구글 아이디는 없음
                    //게스트 아이디에 구글아이디 업데이트
                    System.out.println("#########게스트 아이디는 있으나 구글 아이디는 없음");
                    System.out.println("#########게스트 아이디에 구글아이디 업데이트");
                    userInfo.setUserID(googleLoginDto.getUserId());
                    userInfo.setUserEmail(googleLoginDto.getUserEmail());
                    userInfo.setUserName(googleLoginDto.getUserName());
                    userInfoMapper.updateGuestInfoToGPGS(userInfo);
                    return getUserFullData(userInfo.getUserNo());
                }
            }
        }
        return null;
    }

    public String signOut(String uuid) {
        int del = userInfoMapper.inactiveUserInfoByUuid(uuid);
        if(del > 0) {
            return "Success";
        }else{
            return "Fail";
        }
    }

    private UserFullData getUserFullData(Integer userNo) {
        UserFullData userFullData = new UserFullData();
        UserInfo userInfo = userInfoMapper.getUserInfoByUserNo(userNo);
        UserData userData = userInfoMapper.getUserDataByUserNo(userNo);
        userFullData.setUserInfo(userInfo);
        userFullData.setUserData(userData);
        return userFullData;
    }
}
