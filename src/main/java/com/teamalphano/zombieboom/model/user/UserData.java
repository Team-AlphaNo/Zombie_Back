package com.teamalphano.zombieboom.model.user;

import com.teamalphano.zombieboom.model.item.ItemData;

import java.util.List;

public class UserData {
    private Integer userTicket;
    private Integer userMoney;
    private String userCharList;
    private String userTicketTimer;
    private List<ItemData> userCharDataList;
    private Integer userSelectChar;

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

    public List<ItemData> getUserCharDataList() {
        return userCharDataList;
    }

    public void setUserCharDataList(List<ItemData> userCharDataList) {
        this.userCharDataList = userCharDataList;
    }

    public Integer getUserSelectChar() {
        return userSelectChar;
    }

    public void setUserSelectChar(Integer userSelectChar) {
        this.userSelectChar = userSelectChar;
    }
}
