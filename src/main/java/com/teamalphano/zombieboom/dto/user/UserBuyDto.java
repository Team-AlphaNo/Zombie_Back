package com.teamalphano.zombieboom.dto.user;

public class UserBuyDto {
    private int userNo;
    private String prodId;
    private boolean isCoin;

    private int coin;
    private int ticket;
    private String charList;
    private String userTicketTimer;

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public boolean isCoin() {
        return isCoin;
    }

    public void setCoin(boolean coin) {
        isCoin = coin;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public String getCharList() {
        return charList;
    }

    public void setCharList(String charList) {
        this.charList = charList;
    }

    public String getUserTicketTimer() {
        return userTicketTimer;
    }

    public void setUserTicketTimer(String userTicketTimer) {
        this.userTicketTimer = userTicketTimer;
    }
}
