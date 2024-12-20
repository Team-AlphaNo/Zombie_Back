package com.teamalphano.zombieboom.service.reward;

import com.teamalphano.zombieboom.dto.reward.RewardParams;
import com.teamalphano.zombieboom.dto.reward.RewardUpdateParams;
import com.teamalphano.zombieboom.dto.user.UserFullDataDto;
import com.teamalphano.zombieboom.mapper.reward.RewardMapper;
import com.teamalphano.zombieboom.service.user.UserDataService;
import com.teamalphano.zombieboom.service.user.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class RewardService {
    private final UserInfoService userInfoService;
    private final UserDataService userDataService;
    private final RewardMapper rewardMapper;
    public RewardService(UserInfoService userInfoService,
                         RewardMapper rewardMapper, UserDataService userDataService) {
        this.userInfoService = userInfoService;
        this.rewardMapper = rewardMapper;
        this.userDataService = userDataService;
    }

    public UserFullDataDto getRewardByType(RewardParams rewardParams) {
        //유저 데이터 업데이트
        userDataService.userDataUpdateByFullData(rewardParams.getUserFullData());

        RewardUpdateParams rewardUpdateParams = new RewardUpdateParams();
        rewardUpdateParams.setAmount(rewardParams.getAmount());
        rewardUpdateParams.setUserNo(rewardParams.getUserFullData().getUserInfo().getUserNo());

        if(rewardParams.getType() == 0 ){
        //코인 리워드 지급
            int reworded = rewardMapper.giveRewardToCoin(rewardUpdateParams);
            if(reworded==0){
                return null;
            }
        }else if(rewardParams.getType() == 1){
        //티켓 리워드 지급
            int reworded = rewardMapper.giveRewardToTicket(rewardUpdateParams);
            if(reworded==0){
                return null;
            }
        }else{
            return null;
        }

        return userInfoService.getUserFullData(rewardUpdateParams.getUserNo());
    }
}
