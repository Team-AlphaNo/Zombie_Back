package com.teamalphano.zombieboom.controller.purchase;

import com.google.api.services.androidpublisher.model.ProductPurchase;
import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.logs.CreatePaymentLogDto;
import com.teamalphano.zombieboom.dto.purchase.*;
import com.teamalphano.zombieboom.model.shop.Product;
import com.teamalphano.zombieboom.model.user.UserData;
import com.teamalphano.zombieboom.service.logs.PaymentLogsService;
import com.teamalphano.zombieboom.service.purchase.PurchaseService;
import com.teamalphano.zombieboom.service.shop.ShopAdminService;
import com.teamalphano.zombieboom.service.shop.ShopService;
import com.teamalphano.zombieboom.service.user.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    private final PaymentLogsService paymentLogsService;
    private final ShopService shopService;
    private final UserDataService userDataService;
    private final ShopAdminService shopAdminService;
    private final PurchaseService purchaseService;

    public PurchaseController(
            PaymentLogsService paymentLogsService,
            ShopService shopService,
            UserDataService userDataService,
            ShopAdminService shopAdminService,
            PurchaseService purchaseService
    ) {
        this.paymentLogsService = paymentLogsService;
        this.shopService = shopService;
        this.userDataService = userDataService;
        this.shopAdminService = shopAdminService;
        this.purchaseService = purchaseService;
    }

    @PostMapping("/coin")
    public ResponseEntity<ApiResponse<PurchaseResponseDto>> buyProductCoin(@RequestBody PurchaseReqCoinDto purchaseReqCoinDto) {
        return handlePurchase(
                purchaseReqCoinDto.getUserNo(),
                purchaseReqCoinDto.getProdId(),
                purchaseReqCoinDto.getTransactionId(),
                null,
                "coin"
        );
    }

    @PostMapping("/google")
    public ResponseEntity<ApiResponse<PurchaseResponseDto>> buyProductGoogle(@RequestBody PurchaseReqGoogleDto purchaseReqGoogleDto) {
        return handlePurchase(
                purchaseReqGoogleDto.getUserNo(),
                purchaseReqGoogleDto.getProdId(),
                purchaseReqGoogleDto.getTransactionId(),
                purchaseReqGoogleDto.getPurchaseToken(),
                "google"
        );
    }

    private ResponseEntity<ApiResponse<PurchaseResponseDto>> handlePurchase(
            Integer userNo,
            String prodId,
            String transactionId,
            String purchaseToken,
            String paymentType
    ) {
        if (userNo == 0 || prodId == null || transactionId == null || ("google".equals(paymentType) && purchaseToken == null)) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(400, "Bad Request", null));
        }

        String message = "Processing";
        PurchaseResponseDto responseData = new PurchaseResponseDto();
        responseData.setUserData(null);

        try {
            Product prod = shopService.getProductDetailById(prodId, "ko");
            if (prod == null) {
                message = "Product not found";
                responseData.setPurchaseStatus(message);
                return ResponseEntity.status(404).body(new ApiResponse<>(404, message, responseData));
            }

            CreatePaymentLogDto createPaymentLogDto = initializePaymentLog(userNo, prod, transactionId, paymentType);
            if (!paymentLogsService.insertPaymentLog(createPaymentLogDto)) {
                responseData.setPurchaseStatus("Purchase Logged Fail");
                return ResponseEntity.badRequest().body(new ApiResponse<>(500, "Logged Error", responseData));
            }

            if ("google".equals(paymentType) && !validateGooglePurchase(prodId, purchaseToken, createPaymentLogDto)) {
                responseData.setPurchaseStatus("Purchase Validation Fail");
                return ResponseEntity.badRequest().body(new ApiResponse<>(500, "Validation Error", responseData));
            }

            if (prod.isProdLimit()) {
                updateProductPurchaseLimit(prod);
            }

            DeductAmountDto deductAmountDto = new DeductAmountDto();
            deductAmountDto.setUserNo(userNo);
            deductAmountDto.setAmount(prod.getProdPrice());
            boolean userDeducted = userDataService.deductAmount(deductAmountDto);
            updatePaymentLog(createPaymentLogDto, userDeducted ? "PAID" : "PAID_FAIL");

            PurchaseGrantDto purchaseGrantDto = new PurchaseGrantDto();
            purchaseGrantDto.setUserNo(userNo);
            purchaseGrantDto.setProdId(prodId);
            UserData userData = userDataService.userGrantProduct(purchaseGrantDto);
            updatePaymentLog(createPaymentLogDto, userData!=null ? "GRANTED" : "GRANTED_FAIL");

            responseData.setUserData(userData);
            return ResponseEntity.ok(new ApiResponse<>(200, userData!=null ? "GRANTED" : "GRANTED_FAIL", responseData));
        } catch (Exception e) {
            message = "Exception occurred: " + e.getMessage();
            responseData.setPurchaseStatus(message);
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Exception Error", responseData));
        }
    }

    private CreatePaymentLogDto initializePaymentLog(Integer userNo, Product prod, String transactionId, String paymentType) {
        CreatePaymentLogDto createPaymentLogDto = new CreatePaymentLogDto();
        createPaymentLogDto.setUserNo(userNo);
        createPaymentLogDto.setProdNo(prod.getProdNo());
        createPaymentLogDto.setTransactionId(transactionId);
        createPaymentLogDto.setPaymentStatus("READY");
        createPaymentLogDto.setType(paymentType);
        return createPaymentLogDto;
    }

    private boolean validateGooglePurchase(String prodId, String purchaseToken, CreatePaymentLogDto createPaymentLogDto) throws GeneralSecurityException, IOException {
        ProductPurchase receipt = purchaseService.verifyReceiptForGoogleProduct(prodId, purchaseToken);
        boolean isValid = receipt.getPurchaseState() == 0;
        updatePaymentLog(createPaymentLogDto, isValid ? "VALID_SUCCESS" : "VALID_FAIL");
        return isValid;
    }

    private void updateProductPurchaseLimit(Product prod) {
        UpdateProdPurchaseDto updateProdPurchaseDto = new UpdateProdPurchaseDto();
        updateProdPurchaseDto.setProdNo(prod.getProdNo());
        updateProdPurchaseDto.setProdPurchaseLimit(prod.getProdPurchaseLimit() - 1);
        shopAdminService.updateProductAfterPurchase(updateProdPurchaseDto);
    }

    private void updatePaymentLog(CreatePaymentLogDto createPaymentLogDto, String status) {
        createPaymentLogDto.setPaymentStatus(status);
        paymentLogsService.updateLogMessage(createPaymentLogDto);
    }
}
