package com.teamalphano.zombieboom.mapper.user;

import com.teamalphano.zombieboom.dto.purchase.DeductAmountDto;
import com.teamalphano.zombieboom.dto.user.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDataMapper {

    int updateAfterPurchase(UserBuyDto userBuyDto);

    //유저 데이터 조회
    UserDataDto getUserData(Integer userNo);

    int deductUserCoin(DeductAmountDto deductAmountDto);

    void updateUserData(UpdateUserDataDto updateUserDataDto);

    int updateUserTicket(UpdateTicketDto updateTicketDto);

    int useUserTicket(Integer userNo);

    int userUniqDataUpdate(UserBuyDto userBuyDto);
}
