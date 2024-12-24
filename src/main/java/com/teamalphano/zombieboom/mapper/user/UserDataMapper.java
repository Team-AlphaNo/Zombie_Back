package com.teamalphano.zombieboom.mapper.user;

import com.teamalphano.zombieboom.dto.purchase.DeductAmountDto;
import com.teamalphano.zombieboom.dto.user.UpdateTicketDto;
import com.teamalphano.zombieboom.dto.user.UpdateUserDataDto;
import com.teamalphano.zombieboom.dto.user.UserBuyDto;
import com.teamalphano.zombieboom.dto.user.UserDataDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDataMapper {

    int updateUserDataAfterPurchase(UserBuyDto userBuyDto);

    //유저 데이터 조회
    UserDataDto getUserData(Integer userNo);

    int deductUserCoin(DeductAmountDto deductAmountDto);

    int updateUserData(UpdateUserDataDto updateUserDataDto);

    int updateUserTicket(UpdateTicketDto updateTicketDto);
}
