package com.teamalphano.zombieboom.mapper.user;

import com.teamalphano.zombieboom.model.user.UserData;
import com.teamalphano.zombieboom.model.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    //userNo으로 userInfo 조회
    UserInfo getUserInfoByUserNo(Integer userNo);
    //uuid로 userInfo 조회
    UserInfo getUserInfoByUUID(String uuid);
    //userId로 userInfo 조회
    UserInfo getUserInfoByUserId(String userId);

    //userNo으로 userData 조회
    UserData getUserDataByUserNo(Integer userNo);

    //게스트 회원가입
    int insertGuestSignUp(UserInfo userInfo);
    //기본 유저 데이터 등록
    int insertDefaultUserData(Integer userNo);

    //게스트 로그인
    int updateGuestSignIn(String uuid);

    //구글 회원가입
    int insertGoogleSignUp(UserInfo userInfo);
    //구글 로그인
    int updateGoogleSignIn(String userId);
    //구글 아이디 연동
    int updateUuidForGuestToGoogle(UserInfo userInfo);

    //구글 아이디 UUID 업데이트
    void updateUserInfoGoogleUUID(UserInfo userInfo);
    //게스트 아이디 - GPGS 데이터 업데이트
    void updateGuestInfoToGPGS(UserInfo userInfo);

    //유저 정보 업데이트
    void updateUserInfo(UserInfo userInfo);

    //userNo으로 비활성화 처리
    int inactiveUserInfoByUserNo(Integer userNo);
    //uuid로 비활성화 처리
    int inactiveUserInfoByUuid(String uuid);
}
