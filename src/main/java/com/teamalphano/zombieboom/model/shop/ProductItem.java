package com.teamalphano.zombieboom.model.shop;

public class ProductItem {
    private int prodItemNo;
    private int itemNo;
    private String itemName;
    private String itemImgKey;
    private int itemCount;
    private String itemTime;
    private int itemType;

    public int getProdItemNo() {
        return prodItemNo;
    }

    public void setProdItemNo(int prodItemNo) {
        this.prodItemNo = prodItemNo;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImgKey() {
        return itemImgKey;
    }

    public void setItemImgKey(String itemImgKey) {
        this.itemImgKey = itemImgKey;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getItemTime() {
        return itemTime;
    }

    public void setItemTime(String itemTime) {
        this.itemTime = itemTime;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
