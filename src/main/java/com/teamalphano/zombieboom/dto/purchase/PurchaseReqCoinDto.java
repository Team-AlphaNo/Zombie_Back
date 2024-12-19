package com.teamalphano.zombieboom.dto.purchase;

import lombok.Data;

@Data
public class PurchaseReqCoinDto {
    private Integer userNo;
    private String prodId;
    private String transactionId;
}
