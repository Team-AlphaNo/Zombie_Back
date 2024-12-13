package com.teamalphano.zombieboom.dto.shop;

import com.teamalphano.zombieboom.model.shop.ProductLang;

import java.util.List;

public class ShopUpdateDto {
    private int prodNo;
    private String prodId;
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
    private String prodImgKey;

    private List<ProductLang> langData;

    public int getProdNo() {
        return prodNo;
    }

    public void setProdNo(int prodNo) {
        this.prodNo = prodNo;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
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

    public String getProdImgKey() {
        return prodImgKey;
    }

    public void setProdImgKey(String prodImgKey) {
        this.prodImgKey = prodImgKey;
    }

    public List<ProductLang> getLangData() {
        return langData;
    }

    public void setLangData(List<ProductLang> langData) {
        this.langData = langData;
    }
}