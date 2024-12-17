package com.teamalphano.zombieboom.service.receipt;

import com.google.api.services.androidpublisher.model.ProductPurchase;
import com.google.api.services.androidpublisher.model.SubscriptionPurchaseV2;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface PurchaseService {
    // 영수증 검증 - 구독
    SubscriptionPurchaseV2 verifyReceiptForGoogleSubscribe(String purchaseToken) throws IOException, GeneralSecurityException;
    
    // 영수증 검증 - 그냥
    ProductPurchase verifyReceiptForGoogleProduct(String productId, String purchaseToken) throws IOException, GeneralSecurityException;
}