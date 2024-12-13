package com.teamalphano.zombieboom.dto.item;

import java.util.List;

public class ReqItemRelationDto {
    private Integer prodNo;
    private List<Integer> itemNoList;

    public Integer getProdNo() {
        return prodNo;
    }

    public void setProdNo(Integer prodNo) {
        this.prodNo = prodNo;
    }

    public List<Integer> getItemNoList() {
        return itemNoList;
    }

    public void setItemNoList(List<Integer> itemNoList) {
        this.itemNoList = itemNoList;
    }
}
