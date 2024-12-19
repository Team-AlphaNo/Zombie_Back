package com.teamalphano.zombieboom.mapper.user;

import com.teamalphano.zombieboom.dto.purchase.DeductAmountDto;
import com.teamalphano.zombieboom.dto.user.UpdateUserDataDto;
import com.teamalphano.zombieboom.dto.user.UserBuyDto;
import com.teamalphano.zombieboom.model.user.UserData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDataMapper {

    int updateUserDataAfterPurchase(UserBuyDto userBuyDto);

    //유저 데이터 조회
    UserData getUserData(Integer userNo);

    //유저 데이터 업데이트
    void updateUserData(UpdateUserDataDto updateUserDataDto);

    int deductUserCoin(DeductAmountDto deductAmountDto);
}
