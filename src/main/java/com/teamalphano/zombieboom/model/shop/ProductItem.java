package com.teamalphano.zombieboom.model.shop;

import lombok.Data;

@Data
public class ProductItem {
    private int prodItemNo;
    private int itemNo;
    private String itemName;
    private int itemCount;
    private String itemTime;
    private int itemType;
}
