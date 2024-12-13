package com.teamalphano.zombieboom.model.receipt;

import lombok.Data;

@Data
public class ReceiptRequest {
    private String packageName;  // 앱 패키지 이름
    private String productId;    // 구매한 상품 ID
    private String purchaseToken; // 구매 토큰
}
