package com.teamalphano.zombieboom.dto.shop;

public class ShopLangDto {

    private Integer prodLangNo;
    private Integer prodNo;
    private String prodLangType;
    private String prodName;
    private String prodDesc;

    public Integer getProdLangNo() {
        return prodLangNo;
    }

    public void setProdLangNo(Integer prodLangNo) {
        this.prodLangNo = prodLangNo;
    }

    public Integer getProdNo() {
        return prodNo;
    }

    public void setProdNo(Integer prodNo) {
        this.prodNo = prodNo;
    }

    public String getProdLangType() {
        return prodLangType;
    }

    public void setProdLangType(String prodLangType) {
        this.prodLangType = prodLangType;
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
}
