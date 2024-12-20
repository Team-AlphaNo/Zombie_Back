package com.teamalphano.zombieboom.dto.user;

import lombok.Data;

@Data
public class UserInfoDto {

    private Integer userNo;
    private String userId;
    private String userPwd;
    private String userName;
    private String platform;
    private String userEmail;
    private String createDate;
    private String createUser;
    private String updateDate;
    private String updateUser;
    private boolean delYn;
    private String userUuid;
}
