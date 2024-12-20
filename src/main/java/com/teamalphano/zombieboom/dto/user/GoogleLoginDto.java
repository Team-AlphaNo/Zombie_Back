package com.teamalphano.zombieboom.dto.user;

import lombok.Data;

@Data
public class GoogleLoginDto {
    private String userId;
    private String userEmail;
    private String userUuid;
    private String userName;
}
