package com.teamalphano.zombieboom.service.receipt;

import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.model.ProductPurchase;
import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.logs.PaymentCreateDto;
import com.teamalphano.zombieboom.model.shop.Product;
import com.teamalphano.zombieboom.model.shop.ProductItem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

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
    public String verifyReceiptForGoogleProduct(String productId, String purchaseToken)
            throws IOException, GeneralSecurityException {
        try {
            PaymentCreateDto paymentCreateDto = new PaymentCreateDto();
            paymentCreateDto.setPaymentStatus(false);

            //구입 요청 로그 추가
            paymentCreateDto.setProdNo();

            paymentLogsService.insertPaymentLog();

            AndroidPublisher.Purchases.Products.Get request = androidPublisher
                    .purchases()
                    .products()
                    .get("com.teamalphano.zombieboom", productId, purchaseToken);
            //인앱결제 영수증 검증
            ProductPurchase productPurchase = request.execute();


            System.out.println("############   purchase   :  " + productPurchase.toString());
            Integer status = productPurchase.getPurchaseState();

            if(status == 0){
                //영수증 검증 완료
                //구매 완료 로그 업데이트
                //지급처리
            }else{
                //error
                //구매 실패
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Product checkProduct(String prodId){
        Product productParam = new Product();
        productParam.setProdId(prodId);

        //상품 상세 데이터
        Product product = shopMapper.getProductDetail(productParam);
        if (product != null) {
            if(product.isProdLimit() && product.getProdPurchaseCount()<=0){
                System.out.println("상품 다 팔림");
                return null;
            }
            List<ProductItem> items = shopMapper.getProductItemById(prodId);
            product.setItems(items);

            return product;
        }else{
            System.out.println("상품 없음");
            return null;
        }
    }

}