package com.teamalphano.zombieboom.dto.user;

import lombok.Data;

@Data
public class UpdateUserDataDto {
    private Integer userNo;

    private Integer userTicket;
    private Integer userMoney;
    private String userCharList;
    private Integer userSelectChar;
    private String timeTicketEndDate;
    private String uniqProdList;
}
