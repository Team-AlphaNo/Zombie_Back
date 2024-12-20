package com.teamalphano.zombieboom.service.reword;

import com.teamalphano.zombieboom.dto.reword.RewordParams;
import com.teamalphano.zombieboom.dto.reword.RewordUpdateParams;
import com.teamalphano.zombieboom.dto.user.UserFullDataDto;
import com.teamalphano.zombieboom.mapper.reword.RewordMapper;
import com.teamalphano.zombieboom.service.user.UserDataService;
import com.teamalphano.zombieboom.service.user.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class RewordService {
    private final UserInfoService userInfoService;
    private final UserDataService userDataService;
    private final RewordMapper rewordMapper;
    public RewordService(UserInfoService userInfoService,
                         RewordMapper rewordMapper, UserDataService userDataService) {
        this.userInfoService = userInfoService;
        this.rewordMapper = rewordMapper;
        this.userDataService = userDataService;
    }

    public UserFullDataDto getRewordByType(RewordParams rewordParams) {
        //유저 데이터 업데이트
        userDataService.userDataUpdateByFullData(rewordParams.getUserFullData());

        RewordUpdateParams rewordUpdateParams = new RewordUpdateParams();
        rewordUpdateParams.setAmount(rewordParams.getAmount());
        rewordUpdateParams.setUserNo(rewordParams.getUserFullData().getUserInfo().getUserNo());

        if(rewordParams.getType() == 0 ){
        //코인 리워드 지급
            int reworded = rewordMapper.giveRewardToCoin(rewordUpdateParams);
            if(reworded==0){
                return null;
            }
        }else if(rewordParams.getType() == 1){
        //티켓 리워드 지급
            int reworded = rewordMapper.giveRewardToTicket(rewordUpdateParams);
            if(reworded==0){
                return null;
            }
        }else{
            return null;
        }

        return userInfoService.getUserFullData(rewordUpdateParams.getUserNo());
    }
}
