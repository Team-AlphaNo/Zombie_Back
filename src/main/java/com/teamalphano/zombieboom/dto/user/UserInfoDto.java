package com.teamalphano.zombieboom.dto.user;

import lombok.Data;

@Data
public class UserInfoDto {
    private Integer userNo;
    private String userId;
    private String userName;
    private String platform;
    private String userEmail;
    private String userUuid;
    private String lastLgin;
    private String lastLgout;
    private String lastTicketChargeTime;
}
