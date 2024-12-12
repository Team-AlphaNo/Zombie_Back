package com.teamalphano.zombieboom.model.shop;

import java.util.List;

public class ProductAdmin {
    private int prodNo;
    private int prodPrice;
    private int prodPriceType;
    private int prodType;
    private boolean prodLimit;
    private String prodStartDate;
    private String prodEndDate;
    private int prodPurchaseLimit;
    private int prodPurchaseCount;
    private int prodOrdr;
    private boolean prodActive;
    private Integer unityProdType;
    private String itemImgKey;

    private List<ProductItem> items;    //상세 아이템 list

    private List<ProductLang> productLangData;

    public int getProdNo() {
        return prodNo;
    }

    public void setProdNo(int prodNo) {
        this.prodNo = prodNo;
    }

    public int getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(int prodPrice) {
        this.prodPrice = prodPrice;
    }

    public int getProdPriceType() {
        return prodPriceType;
    }

    public void setProdPriceType(int prodPriceType) {
        this.prodPriceType = prodPriceType;
    }

    public int getProdType() {
        return prodType;
    }

    public void setProdType(int prodType) {
        this.prodType = prodType;
    }

    public boolean isProdLimit() {
        return prodLimit;
    }

    public void setProdLimit(boolean prodLimit) {
        this.prodLimit = prodLimit;
    }

    public String getProdStartDate() {
        return prodStartDate;
    }

    public void setProdStartDate(String prodStartDate) {
        this.prodStartDate = prodStartDate;
    }

    public String getProdEndDate() {
        return prodEndDate;
    }

    public void setProdEndDate(String prodEndDate) {
        this.prodEndDate = prodEndDate;
    }

    public int getProdPurchaseLimit() {
        return prodPurchaseLimit;
    }

    public void setProdPurchaseLimit(int prodPurchaseLimit) {
        this.prodPurchaseLimit = prodPurchaseLimit;
    }

    public int getProdPurchaseCount() {
        return prodPurchaseCount;
    }

    public void setProdPurchaseCount(int prodPurchaseCount) {
        this.prodPurchaseCount = prodPurchaseCount;
    }

    public int getProdOrdr() {
        return prodOrdr;
    }

    public void setProdOrdr(int prodOrdr) {
        this.prodOrdr = prodOrdr;
    }

    public boolean isProdActive() {
        return prodActive;
    }

    public void setProdActive(boolean prodActive) {
        this.prodActive = prodActive;
    }

    public Integer getUnityProdType() {
        return unityProdType;
    }

    public void setUnityProdType(Integer unityProdType) {
        this.unityProdType = unityProdType;
    }

    public String getItemImgKey() {
        return itemImgKey;
    }

    public void setItemImgKey(String itemImgKey) {
        this.itemImgKey = itemImgKey;
    }

    public List<ProductItem> getItems() {
        return items;
    }

    public void setItems(List<ProductItem> items) {
        this.items = items;
    }

    public List<ProductLang> getProductLangData() {
        return productLangData;
    }

    public void setProductLangData(List<ProductLang> productLangData) {
        this.productLangData = productLangData;
    }
}
