package com.teamalphano.zombieboom.dto.user;

import com.teamalphano.zombieboom.model.item.ItemData;
import lombok.Data;

import java.util.List;

@Data
public class UserDataDto {
    private Integer userTicket;
    private Integer userMoney;
    private String userCharList;
    private List<ItemData> userCharDataList;
    private Integer userSelectChar;
    private String timeTicketEndDate;
    private String uniqProdList;
}
