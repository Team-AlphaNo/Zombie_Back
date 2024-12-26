package com.teamalphano.zombieboom.dto.user.admin;

import com.teamalphano.zombieboom.dto.user.UserDataDto;
import lombok.Data;

@Data
public class UserFullDataAdminDto {
    private UserInfoAdminDto userInfoAdminDto;
    private UserDataDto userDataDto;
}
