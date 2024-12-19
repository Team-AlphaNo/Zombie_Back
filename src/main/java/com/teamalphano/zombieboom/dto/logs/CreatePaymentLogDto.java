package com.teamalphano.zombieboom.dto.logs;

import lombok.Data;

@Data
public class CreatePaymentLogDto {
    private Integer userNo;
    private String prodId;
    private Integer prodNo;
    private String paymentStatus;
    private String transactionId;
    private String type;
}
