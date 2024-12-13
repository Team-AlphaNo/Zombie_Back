package com.teamalphano.zombieboom.model.user;

import com.teamalphano.zombieboom.model.item.ItemData;
import lombok.Data;

import java.util.List;

@Data
public class UserData {
    private Integer userTicket;
    private Integer userMoney;
    private String userCharList;
    private String userTicketTimer;
    private List<ItemData> userCharDataList;
    private Integer userSelectChar;
}
