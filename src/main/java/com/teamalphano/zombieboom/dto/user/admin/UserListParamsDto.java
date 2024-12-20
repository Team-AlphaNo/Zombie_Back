package com.teamalphano.zombieboom.dto.user.admin;

import lombok.Data;

@Data
public class UserListParamsDto {
    private String searchType;
    private String searchValue;
}
