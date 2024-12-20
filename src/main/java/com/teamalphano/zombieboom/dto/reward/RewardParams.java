package com.teamalphano.zombieboom.dto.reword;

import com.teamalphano.zombieboom.dto.user.UserFullDataDto;
import lombok.Data;

@Data
public class RewordParams {
    private UserFullDataDto userFullData;
    private Integer type;
    private Integer amount;
}
