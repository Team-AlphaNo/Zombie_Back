package com.teamalphano.zombieboom.service.rank;

import com.teamalphano.zombieboom.dto.rank.RankInsertDto;
import com.teamalphano.zombieboom.mapper.rank.RankMapper;
import com.teamalphano.zombieboom.dto.rank.RankDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RankService {
    private final RankMapper rankMapper;

    public RankService(RankMapper rankMapper) {
        this.rankMapper = rankMapper;
    }

    @Transactional
    public List<RankDto> getWorldRankList(Integer type) {
        if(type == 0){
            return rankMapper.getWorldDayRankList();
        }else if(type == 1){
            return rankMapper.getWorldMonthRankList();
        }else{
            return rankMapper.getWorldTotalRankList();
        }
    }

    @Transactional
    public String insertRank(RankInsertDto rankInsertDto){
        int insert = rankMapper.insertRank(rankInsertDto);
        if(insert == 1){
            return "Success";
        }else{
            return "Fail";
        }
    }

    @Transactional
    public RankDto getMyRank(Integer type, Integer userNo){
        if(type == 0){
            return rankMapper.getMyRankDay(userNo);
        }else if(type == 1){
            return rankMapper.getMyRankMonth(userNo);
        }else{
            return rankMapper.getMyRankTotal(userNo);
        }
    }
}
