package com.teamalphano.zombieboom.dto.reward;

import com.teamalphano.zombieboom.dto.user.UserFullDataDto;
import lombok.Data;

@Data
public class RewardParams {
    private UserFullDataDto userFullData;
    private Integer type;
    private Integer amount;
}
