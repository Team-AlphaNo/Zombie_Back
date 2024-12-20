package com.teamalphano.zombieboom.mapper.reward;

import com.teamalphano.zombieboom.dto.reward.RewardUpdateParams;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RewardMapper {
    int giveRewardToCoin(RewardUpdateParams rewardUpdateParams);
    int giveRewardToTicket(RewardUpdateParams rewardUpdateParams);
}
