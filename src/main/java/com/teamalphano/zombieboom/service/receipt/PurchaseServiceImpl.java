package com.teamalphano.zombieboom.service.receipt;

import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.model.ProductPurchase;
import com.google.api.services.androidpublisher.model.SubscriptionPurchaseV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final AndroidPublisher androidPublisher;

    @Override
    public SubscriptionPurchaseV2 verifyReceiptForGoogleSubscribe(String purchaseToken) throws IOException, GeneralSecurityException {
        try {
            AndroidPublisher.Purchases.Subscriptionsv2.Get request = androidPublisher
                    .purchases().subscriptionsv2().get("com.teamalphano.zombieboom", purchaseToken);
            return request.execute();
        } catch (Exception e) {
            e.printStackTrace(); // 예외 로그 출력
            throw e; // 다시 던지기
        }
    }

    /**
     * Google Play 인앱 구매 영수증 검증
     *
     * @param productId     구매한 제품 ID
     * @param purchaseToken 구매 토큰
     * @return ProductPurchase 구매 정보
     * @throws IOException, GeneralSecurityException
     */
    @Override
    public ProductPurchase verifyReceiptForGoogleProduct(String productId, String purchaseToken)
            throws IOException, GeneralSecurityException {
        try {
            AndroidPublisher.Purchases.Products.Get request = androidPublisher
                    .purchases()
                    .products()
                    .get("com.teamalphano.zombieboom", productId, purchaseToken);
            return request.execute(); // 구매 정보 반환
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}