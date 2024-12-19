package com.teamalphano.zombieboom.dto.shop;

import com.teamalphano.zombieboom.model.shop.ProductLang;
import lombok.Data;

import java.util.List;

@Data
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
}