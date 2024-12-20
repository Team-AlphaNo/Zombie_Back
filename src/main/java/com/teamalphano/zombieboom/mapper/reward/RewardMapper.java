package com.teamalphano.zombieboom.mapper.reword;

import com.teamalphano.zombieboom.dto.reword.RewordParams;
import com.teamalphano.zombieboom.dto.reword.RewordUpdateParams;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RewordMapper {
    int giveRewardToCoin(RewordUpdateParams rewordUpdateParams);
    int giveRewardToTicket(RewordUpdateParams rewordUpdateParams);
}
