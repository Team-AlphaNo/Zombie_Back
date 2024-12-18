package com.teamalphano.zombieboom.model.logs;

import lombok.Data;

@Data
public class PaymentLog {
    private Integer userNo;
    private String paymentStatus;
    private String createDate;
    private String updateDate;
    private String userName;
    private String userUuid;
    private String userId;
    private String userEmail;
    private Integer prodNo;
}
