package com.teamalphano.zombieboom.dto.user;

public class UpdateUserDataDto {
    private Integer userNo;
    private Integer userTicket;
    private Integer userMoney;
    private String userCharList;
    private String userTicketTimer;
    private Integer userSelectChar;

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getUserTicket() {
        return userTicket;
    }

    public void setUserTicket(Integer userTicket) {
        this.userTicket = userTicket;
    }

    public Integer getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(Integer userMoney) {
        this.userMoney = userMoney;
    }

    public String getUserCharList() {
        return userCharList;
    }

    public void setUserCharList(String userCharList) {
        this.userCharList = userCharList;
    }

    public String getUserTicketTimer() {
        return userTicketTimer;
    }

    public void setUserTicketTimer(String userTicketTimer) {
        this.userTicketTimer = userTicketTimer;
    }

    public Integer getUserSelectChar() {
        return userSelectChar;
    }

    public void setUserSelectChar(Integer userSelectChar) {
        this.userSelectChar = userSelectChar;
    }
}
