package com.teamalphano.zombieboom.dto.user;

import lombok.Data;

@Data
public class UserBuyDto {
    private Integer userNo;
    private String prodId;
    private Integer prodNo;

    private String charList;
    private Integer coin;
    private Integer ticket;
    private String uniqProdList;
    private String timeTicketEndDate;
    private String timeTicketRange;
}
