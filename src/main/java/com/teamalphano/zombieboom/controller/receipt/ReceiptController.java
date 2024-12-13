package com.teamalphano.zombieboom.controller.receipt;

import com.google.auth.oauth2.GoogleCredentials;
import com.teamalphano.zombieboom.model.receipt.ReceiptRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    @PostMapping("/verify")
    public ResponseEntity<?> verifyReceipt(@RequestBody ReceiptRequest receiptRequest) {
        try {
            // 서비스 계정 JSON 파일 경로
            String jsonKeyPath = "src/main/resources/service-account.json";

            // Google Play 인증 설정
            GoogleCredentials credential = GoogleCredentials.fromStream(new FileInputStream(jsonKeyPath))
                    .createScoped("https://www.googleapis.com/auth/androidpublisher");

            // Google Play API 클라이언트 생성
            AndroidPublisher publisher = new AndroidPublisher.Builder(
                    credential.getTransport(),
                    credential.getJsonFactory(),
                    credential)
                    .setApplicationName("YourAppName")
                    .build();

            // 영수증 검증
            AndroidPublisher.Purchases.Products.Get request = publisher.purchases().products().get(
                    receiptRequest.getPackageName(),
                    receiptRequest.getProductId(),
                    receiptRequest.getPurchaseToken()
            );

            ProductPurchase purchase = request.execute();

            // 구매 상태 확인 (0: 구매 완료)
            if (purchase.getPurchaseState() == 0) {
                return ResponseEntity.ok("Purchase is valid!");
            } else {
                return ResponseEntity.badRequest().body("Invalid purchase!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}