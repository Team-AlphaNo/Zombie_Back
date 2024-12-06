package com.teamalphano.zombieboom.model.shop;
import java.util.List;

public class Product {

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

    private List<ProductItem> items;    //상세 아이템 list

    //언어설정
    private String langType;
    private String prodName;
    private String prodDesc;

    private Integer unityProdType;

    public Integer getUnityProdType() {
        return unityProdType;
    }

    public void setUnityProdType(Integer unityProdType) {
        this.unityProdType = unityProdType;
    }

    public String getLangType() {
        return langType;
    }

    public void setLangType(String langType) {
        this.langType = langType;
    }

    public boolean isProdLimit() {
        return prodLimit;
    }

    public int getProdNo() {
        return prodNo;
    }

    public void setProdNo(int prodNo) {
        this.prodNo = prodNo;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
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

    public boolean getProdLimit() {
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

    public List<ProductItem> getItems() {
        return items;
    }

    public void setItems(List<ProductItem> items) {
        this.items = items;
    }
}
