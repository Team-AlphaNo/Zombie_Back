package com.teamalphano.zombieboom.dto.user.admin;

import lombok.Data;

@Data
public class UserInfoAdminDto {
    private Integer userNo;
    private String userId;
    private String userPwd;
    private String userName;
    private String platform;
    private String userEmail;
    private String userUuid;
    private String lastLgin;
    private String lastLgout;
    private String createDate;
    private String createUser;
    private String updateDate;
    private String updateUser;
    private boolean delYn;
    private String lastTicketChargeTime;
}
