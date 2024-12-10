package com.teamalphano.zombieboom.mapper.rank;

import com.teamalphano.zombieboom.dto.rank.RankInsertDto;
import com.teamalphano.zombieboom.model.rank.WorldRank;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RankMapper {


    List<WorldRank> getWorldTotalRankList();

    List<WorldRank> getWorldMonthRankList();

    List<WorldRank> getWorldDayRankList();

    int insertRank(RankInsertDto rankInsertDto);
}
