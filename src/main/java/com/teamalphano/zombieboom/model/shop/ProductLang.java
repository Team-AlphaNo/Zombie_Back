package com.teamalphano.zombieboom.model.shop;

public class ProductLang {
    private Integer prodLangNo;
    private String langType;
    private String prodName;
    private String prodDesc;

    public Integer getProdLangNo() {
        return prodLangNo;
    }

    public void setProdLangNo(Integer prodLangNo) {
        this.prodLangNo = prodLangNo;
    }

    public String getLangType() {
        return langType;
    }

    public void setLangType(String langType) {
        this.langType = langType;
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
