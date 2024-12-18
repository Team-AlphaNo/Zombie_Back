package com.teamalphano.zombieboom.dto.user;

import lombok.Data;

@Data
public class UserBuyDto {
    private Integer userNo;
    private String prodId;
    private Integer prodNo;

    private String userTicketTimer;
    private String charList;
    private Integer coin;
    private Integer ticket;
}
