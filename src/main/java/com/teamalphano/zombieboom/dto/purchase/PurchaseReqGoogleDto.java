package com.teamalphano.zombieboom.dto.purchase;

import lombok.Data;

@Data
public class PurchaseReqGoogleDto {
    private Integer userNo;
    private String prodId;
    private String transactionId;
    private String purchaseToken;
}
