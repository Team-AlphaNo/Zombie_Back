package com.teamalphano.zombieboom.mapper.rank;

import com.teamalphano.zombieboom.dto.rank.RankInsertDto;
import com.teamalphano.zombieboom.dto.rank.RankDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RankMapper {


    //전체 리스트
    List<RankDto> getWorldTotalRankList();
    List<RankDto> getWorldMonthRankList();
    List<RankDto> getWorldDayRankList();

    int insertRank(RankInsertDto rankInsertDto);

    RankDto getMyRankTotal(Integer userNo);
    RankDto getMyRankMonth(Integer userNo);
    RankDto getMyRankDay(Integer userNo);

}
