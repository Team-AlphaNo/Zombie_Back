package com.teamalphano.zombieboom.dto.user;

public class UserBuyDto {
    private int userNo;
    private int prodNo;
    private boolean isCoin;

    private int coin;
    private int ticket;
    private String charList;
    private String userTicketTimer;

    public String getUserTicketTimer() {
        return userTicketTimer;
    }

    public void setUserTicketTimer(String userTicketTimer) {
        this.userTicketTimer = userTicketTimer;
    }

    public int getUserNo() {
        return userNo;
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

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getProdNo() {
        return prodNo;
    }

    public void setProdNo(int prodNo) {
        this.prodNo = prodNo;
    }

    public boolean getIsCoin() {
        return isCoin;
    }

    public void setCoin(boolean coin) {
        isCoin = coin;
    }
}
