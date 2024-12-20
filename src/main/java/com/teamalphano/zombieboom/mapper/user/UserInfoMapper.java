package com.teamalphano.zombieboom.mapper.user;

import com.teamalphano.zombieboom.dto.user.UserDataDto;
import com.teamalphano.zombieboom.dto.user.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {
    //userNo으로 userInfo 조회
    UserInfoDto getUserInfoByUserNo(Integer userNo);
    //uuid로 userInfo 조회
    UserInfoDto getUserInfoByUUID(String uuid);
    //userId로 userInfo 조회
    UserInfoDto getUserInfoByUserId(String userId);

    //userNo으로 userData 조회
    UserDataDto getUserDataByUserNo(Integer userNo);

    //게스트 회원가입
    int insertGuestSignUp(UserInfoDto userInfoDto);
    //기본 유저 데이터 등록
    int insertDefaultUserData(Integer userNo);

    //게스트 로그인
    int updateGuestSignIn(String uuid);

    //구글 회원가입
    int insertGoogleSignUp(UserInfoDto userInfoDto);
    //구글 로그인
    int updateGoogleSignIn(String userId);
    //구글 아이디 연동
    int updateUuidForGuestToGoogle(UserInfoDto userInfoDto);

    //구글 아이디 UUID 업데이트
    void updateUserInfoGoogleUUID(UserInfoDto userInfoDto);
    //게스트 아이디 - GPGS 데이터 업데이트
    int updateGuestInfoToGPGS(UserInfoDto userInfoDto);

    //유저 정보 업데이트
    void updateUserInfo(UserInfoDto userInfoDto);

    //userNo으로 비활성화 처리
    int inactiveUserInfoByUserNo(Integer userNo);
    //uuid로 비활성화 처리
    int inactiveUserInfoByUuid(String uuid);

    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertedUserNo();

    int signOut(Integer userNo);
}
