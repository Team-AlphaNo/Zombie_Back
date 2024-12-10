package com.teamalphano.zombieboom.service.rank;

import com.teamalphano.zombieboom.dto.rank.RankInsertDto;
import com.teamalphano.zombieboom.mapper.rank.RankMapper;
import com.teamalphano.zombieboom.model.rank.WorldRank;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankService {
    private final RankMapper rankMapper;

    public RankService(RankMapper rankMapper) {
        this.rankMapper = rankMapper;
    }

    public List<WorldRank> getWorldRankList(Integer type) {
        if(type == 0){
            return rankMapper.getWorldDayRankList();
        }else if(type == 1){
            return rankMapper.getWorldMonthRankList();
        }else{
            return rankMapper.getWorldTotalRankList();
        }
    }

    public String insertRank(RankInsertDto rankInsertDto){
        int insert = rankMapper.insertRank(rankInsertDto);
        if(insert == 1){
            return "Success";
        }else{
            return "Fail";
        }
    }
}
