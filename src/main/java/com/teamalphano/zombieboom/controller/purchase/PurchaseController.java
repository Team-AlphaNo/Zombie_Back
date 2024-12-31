package com.teamalphano.zombieboom.controller.purchase;

import com.google.api.services.androidpublisher.model.ProductPurchase;
import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.logs.CreatePaymentLogDto;
import com.teamalphano.zombieboom.dto.purchase.*;
import com.teamalphano.zombieboom.dto.shop.ProductDto;
import com.teamalphano.zombieboom.dto.user.UserDataDto;
import com.teamalphano.zombieboom.dto.user.UserFullDataDto;
import com.teamalphano.zombieboom.service.logs.PaymentLogsService;
import com.teamalphano.zombieboom.service.purchase.PurchaseService;
import com.teamalphano.zombieboom.service.shop.ShopAdminService;
import com.teamalphano.zombieboom.service.shop.ShopService;
import com.teamalphano.zombieboom.service.user.UserCommonService;
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
    private final UserCommonService userCommonService;

    public PurchaseController(
            PaymentLogsService paymentLogsService,
            ShopService shopService,
            UserDataService userDataService,
            ShopAdminService shopAdminService,
            PurchaseService purchaseService,
            UserCommonService userCommonService
    ) {
        this.paymentLogsService = paymentLogsService;
        this.shopService = shopService;
        this.userDataService = userDataService;
        this.shopAdminService = shopAdminService;
        this.purchaseService = purchaseService;
        this.userCommonService = userCommonService;
    }

    @PostMapping("/coin")
    public ResponseEntity<ApiResponse<UserFullDataDto>> buyProductCoin(
            @RequestBody PurchaseReqCoinDto purchaseReqCoinDto) {
        return handlePurchase(
                purchaseReqCoinDto.getUserNo(),
                purchaseReqCoinDto.getProdId(),
                purchaseReqCoinDto.getTransactionId(),
                null,
                "coin"
        );
    }

    @PostMapping("/google")
    public ResponseEntity<ApiResponse<UserFullDataDto>> buyProductGoogle(
            @RequestBody PurchaseReqGoogleDto purchaseReqGoogleDto) {
        return handlePurchase(
                purchaseReqGoogleDto.getUserNo(),
                purchaseReqGoogleDto.getProdId(),
                purchaseReqGoogleDto.getTransactionId(),
                purchaseReqGoogleDto.getPurchaseToken(),
                "google"
        );
    }

    private ResponseEntity<ApiResponse<UserFullDataDto>> handlePurchase(
            Integer userNo,
            String prodId,
            String transactionId,
            String purchaseToken,
            String paymentType
    ) {
        if (userNo == 0 || prodId == null || transactionId == null || ("google".equals(paymentType) && purchaseToken == null)) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(400, "Bad Request", null));
        }

        UserFullDataDto responseData = new UserFullDataDto();

        try {
            //상품 select
            ProductDto prod = shopService.getProductDetailById(prodId, "ko");
            System.out.println("---------------------------");
            System.out.println(prod.toString());
            System.out.println("---------------------------");
            if (prod == null) {
                return ResponseEntity.status(404).body(new ApiResponse<>(404, "error", responseData));
            }

            //구입시도 log 저장
            CreatePaymentLogDto createPaymentLogDto = initializePaymentLog(userNo, prod, transactionId, paymentType);
            System.out.println("---------------------------");
            System.out.println(createPaymentLogDto.toString());
            System.out.println("---------------------------");
            boolean insertLog = paymentLogsService.insertPaymentLog(createPaymentLogDto);

            System.out.println("---------------------------");
            if (!insertLog) {
                return ResponseEntity.badRequest().body(new ApiResponse<>(500, "Logged Error", responseData));
            }

            if ("google".equals(paymentType) ) {
                System.out.println("google check-------------------------");
                boolean valid = validateGooglePurchase(prodId, purchaseToken, createPaymentLogDto);
                if (!valid) {
                    return ResponseEntity.badRequest().body(new ApiResponse<>(500, "Validation Error", responseData));
                }
            }

            if (prod.isProdLimit()) {
                System.out.println("limit update -------------------------");
                updateProductPurchaseLimit(prod);
            }

            if(prod.getProdPriceType()==2){
                DeductAmountDto deductAmountDto = new DeductAmountDto();
                deductAmountDto.setUserNo(userNo);
                deductAmountDto.setAmount(prod.getProdPrice());
                boolean userDeducted = userDataService.deductAmount(deductAmountDto);
                updatePaymentLog(createPaymentLogDto, userDeducted ? "PAID" : "PAID_FAIL");
            }else{
                updatePaymentLog(createPaymentLogDto, "PAID" );
            }

            PurchaseGrantDto purchaseGrantDto = new PurchaseGrantDto();
            purchaseGrantDto.setUserNo(userNo);
            purchaseGrantDto.setProdId(prodId);
            boolean grant = userDataService.userGrantProduct(purchaseGrantDto);
            updatePaymentLog(createPaymentLogDto, grant ? "GRANTED" : "GRANTED_FAIL");

            responseData = userCommonService.getUserFullData(userNo);

            System.out.println("---------------------------");
            System.out.println(responseData.toString());

            return ResponseEntity.ok(new ApiResponse<>(200, grant ? "GRANTED" : "GRANTED_FAIL", responseData));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Exception Error", responseData));
        }
    }

    private CreatePaymentLogDto initializePaymentLog(Integer userNo, ProductDto prod, String transactionId, String paymentType) {
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
        System.out.println(receipt.toString());
        boolean isValid = receipt.getPurchaseState() == 0;
        updatePaymentLog(createPaymentLogDto, isValid ? "VALID_SUCCESS" : "VALID_FAIL");
        return isValid;
    }

    private void updateProductPurchaseLimit(ProductDto prod) {
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
