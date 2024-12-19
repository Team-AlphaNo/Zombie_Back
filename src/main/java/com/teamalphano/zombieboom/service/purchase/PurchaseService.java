package com.teamalphano.zombieboom.service.purchase;

import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.model.ProductPurchase;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public class PurchaseService {
    private final AndroidPublisher androidPublisher;

    public PurchaseService(AndroidPublisher androidPublisher) {
        this.androidPublisher = androidPublisher;
    }

    /**
     * Google Play 인앱 구매 영수증 검증
     *
     * @param productId     구매한 제품 ID
     * @param purchaseToken 구매 토큰
     * @return ProductPurchase 구매 정보
     * @throws IOException, GeneralSecurityException
     */
    public ProductPurchase verifyReceiptForGoogleProduct(String productId, String purchaseToken)
            throws IOException, GeneralSecurityException {
        try {
            AndroidPublisher.Purchases.Products.Get request = androidPublisher
                    .purchases()
                    .products()
                    .get("com.teamalphano.zombieboom", productId, purchaseToken);
            //인앱결제 영수증 검증
            return request.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
