package com.teamalphano.zombieboom.dto.shop.admin;

import lombok.Data;

@Data
public class ProdCreateParamsDto {
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

    private String prodName;
    private String prodDesc;
}
