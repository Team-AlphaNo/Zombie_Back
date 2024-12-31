package com.teamalphano.zombieboom.service.user;

import com.teamalphano.zombieboom.common.CharStringEdit;
import com.teamalphano.zombieboom.dto.user.*;
import com.teamalphano.zombieboom.mapper.item.ItemMapper;
import com.teamalphano.zombieboom.mapper.user.UserInfoMapper;
import com.teamalphano.zombieboom.model.item.ItemData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoService {
    private final UserInfoMapper userInfoMapper;
    private final UserDataService userDataService;
    private final UserCommonService userCommonService;

    public UserInfoService(UserInfoMapper userInfoMapper,
                           UserDataService userDataService,
                           UserCommonService userCommonService
    ) {
        this.userInfoMapper = userInfoMapper;
        this.userDataService = userDataService;
        this.userCommonService = userCommonService;
    }

    //게스트 로그인
    @Transactional
    public UserFullDataDto GuestLogin(String uuid) {
        //uuid 있는지 체크
        UserInfoDto userInfoDto = userInfoMapper.getUserInfoByUUID(uuid);
        if(userInfoDto == null || userInfoDto.getUserUuid() == null) {
            //회원가입
            System.out.println("##################회원가입");
            UserInfoDto newUserInfoDto = new UserInfoDto();
            newUserInfoDto.setUserUuid(uuid);

            int rowsAffected = userInfoMapper.insertGuestSignUp(newUserInfoDto);
            System.out.println("Rows affected by insert: " + rowsAffected);
            if (rowsAffected > 0) {
                int userNo = newUserInfoDto.getUserNo();
                int userData = userInfoMapper.insertDefaultUserData(userNo);
                if(userData > 0) {
                    return userCommonService.getUserFullData(userNo);
                }
            }else {
                return null;
            }
        }else{
            //로그인 처리
            System.out.println("로그인처리");
            int updateLogin = userInfoMapper.updateGuestSignIn(uuid);
            if(updateLogin > 0) {
                return userCommonService.getUserFullData(userInfoDto.getUserNo());
            }
        }
        return null;
    }

    //구글 로그인
    @Transactional
    public UserFullDataDto GoogleLogin(GoogleLoginDto googleLoginDto){
        UserInfoDto userInfoDto = userInfoMapper.getUserInfoByUUID(googleLoginDto.getUserUuid());
        System.out.println(userInfoDto.toString());
        if(userInfoDto.getUserUuid() == null) {
            System.out.println("----------------------------------uuidEmpty");
            //해당 기기로 등록된 데이터가 없음

            System.out.println(googleLoginDto.toString());
            UserInfoDto _userInfoDto = userInfoMapper.getUserInfoByUserId(googleLoginDto.getUserId());
            if(_userInfoDto != null) {
                System.out.println("#########uuid는 없는데 해당 구글 아이디 있음");
                System.out.println("#########해당 구글아이디의 uuid를 업데이트");
                //uuid는 없는데 해당 구글 아이디 있음
                //해당 구글아이디의 uuid를 업데이트
                _userInfoDto.setUserUuid(googleLoginDto.getUserUuid());
                userInfoMapper.updateUserInfoGoogleUUID(_userInfoDto);
                return userCommonService.getUserFullData(_userInfoDto.getUserNo());
            }else{
                //회원가입
                System.out.println("#########회원가입");

                UserInfoDto newUserInfoDto = new UserInfoDto();
                newUserInfoDto.setUserUuid(googleLoginDto.getUserUuid());
                newUserInfoDto.setUserId(googleLoginDto.getUserId());
                newUserInfoDto.setUserName(googleLoginDto.getUserName());
                newUserInfoDto.setUserEmail(googleLoginDto.getUserEmail());
                int insert = userInfoMapper.insertGoogleSignUp(newUserInfoDto);
                if(insert > 0) {
                    int insertData = userInfoMapper.insertDefaultUserData(newUserInfoDto.getUserNo());
                    if (insertData > 0) {
                        int userNo = newUserInfoDto.getUserNo();
                        return userCommonService.getUserFullData(userNo);
                    }else{
                        throw new RuntimeException("Failed to insert Google SignUp data");
                    }
                }else{
                    throw new RuntimeException("Failed to insert Google SignUp data");
                }
            }
        }else{
            System.out.println("----------------------------------uuid OK");
            //게스트 id 있음
            if(userInfoDto.getUserId() != null ){
                //구글 로그인
                System.out.println("#########게스트 아이디에 구글 로그인");
                int updateLogin = userInfoMapper.updateGoogleSignIn(googleLoginDto.getUserId());
                return userCommonService.getUserFullData(userInfoDto.getUserNo());
            }else{
                //구글 아이디 찾기
                UserInfoDto _userInfoDto = userInfoMapper.getUserInfoByUserId(googleLoginDto.getUserId());
                if(_userInfoDto != null) {
                    //구글 아이디가 있고, 게스트 아이디도 있음
                    //아이디는 delete처리, 구글아이디에 uuid 업데이트
                    System.out.println("#########구글 아이디가 있고, 게스트 아이디도 있음");
                    System.out.println("#########아이디는 delete처리, 구글아이디에 uuid 업데이트");
                    userInfoMapper.inactiveUserInfoByUserNo(_userInfoDto.getUserNo());
                    _userInfoDto = userInfoMapper.getUserInfoByUUID(googleLoginDto.getUserUuid());
                    userInfoMapper.updateUuidForGuestToGoogle(_userInfoDto);
                    return userCommonService.getUserFullData(_userInfoDto.getUserNo());
                }else{
                    //게스트 아이디는 있으나 구글 아이디는 없음
                    //게스트 아이디에 구글아이디 업데이트
                    System.out.println("#########게스트 아이디는 있으나 구글 아이디는 없음");
                    System.out.println("#########게스트 아이디에 구글아이디 업데이트");
                    userInfoDto.setUserId(googleLoginDto.getUserId());
                    userInfoDto.setUserEmail(googleLoginDto.getUserEmail());
                    userInfoDto.setUserName(googleLoginDto.getUserName());
                    int update = userInfoMapper.updateGuestInfoToGPGS(userInfoDto);
                    if(update > 0) {
                        return userCommonService.getUserFullData(userInfoDto.getUserNo());
                    }else{
                        System.out.println("no update");
                    }
                }
            }
        }
        return null;
    }



    public String deleteAccount(String uuid) {
        int del = userInfoMapper.inactiveUserInfoByUuid(uuid);
        if(del > 0) {
            return "Success";
        }else{
            return "Fail";
        }
    }

    public String signOut(UserFullDataDto userFullDataDto){
        //데이터 업데이트
        userDataService.userDataUpdateByFullData(userFullDataDto);
        LogOutDto logOutDto = new LogOutDto();
        logOutDto.setUserNo(userFullDataDto.getUserInfo().getUserNo());
        logOutDto.setLastTicketChargeTime(userFullDataDto.getUserInfo().getLastTicketChargeTime());
        int signOutBool = userInfoMapper.signOut(logOutDto);
        if(signOutBool > 0) {
            return "Success";
        }else{
            return "Fail";
        }
    }
}
