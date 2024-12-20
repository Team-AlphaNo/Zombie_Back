package com.teamalphano.zombieboom.dto.shop;

import com.teamalphano.zombieboom.model.shop.ProductItem;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
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
    private String prodImgKey;
    private String prodId;

    private List<ProductItem> items;    //상세 아이템 list

    //언어설정
    private String langType;
    private String prodName;
    private String prodDesc;

    private Integer unityProdType;
    private boolean isUnique;       //일회성 여부
    private boolean isPurchased;    //판매 완료 여부
}
