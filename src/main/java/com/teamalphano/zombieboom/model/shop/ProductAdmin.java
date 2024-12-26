package com.teamalphano.zombieboom.model.shop;

import lombok.Data;

import java.util.List;

@Data
public class ProductAdmin {
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
    private Integer unityProdType;
    private String prodImgKey;
    private boolean isUnique;

    private List<ProductItem> items;    //상세 아이템 list

    private List<ProductLang> productLangData;
}
