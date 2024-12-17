package com.teamalphano.zombieboom.dto.purchase;

import lombok.Data;

@Data
public class PurchaseDto{
    private String purchaseToken;
    private String productID;
    private Integer type;
}