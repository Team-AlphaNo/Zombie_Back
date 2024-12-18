package com.teamalphano.zombieboom.controller.receipt;

import com.google.api.services.androidpublisher.model.ProductPurchase;
import com.google.api.services.androidpublisher.model.SubscriptionPurchaseV2;
import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.logs.PaymentCreateDto;
import com.teamalphano.zombieboom.dto.purchase.PurchaseDto;
import com.teamalphano.zombieboom.service.logs.PaymentLogsService;
import com.teamalphano.zombieboom.service.receipt.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/purchase")
public class PurchaseReceiptController {
    private final PurchaseService purchaseService;

    public PurchaseReceiptController(
            PurchaseService purchaseService
    ) {
        this.purchaseService = purchaseService;
    }


    @PostMapping("/verify/google")
    public ResponseEntity<ApiResponse<?>> purchaseVerify(
            @RequestBody PurchaseDto purchaseDto) throws GeneralSecurityException, IOException {
        try{
            String purchase = purchaseService.verifyReceiptForGoogleProduct(purchaseDto.getProdID(), purchaseDto.getPurchaseToken());
            if(purchase.equals("Success")){
                //지급
                return ResponseEntity.ok(new ApiResponse<>(200, "Success", purchase));
            }else{
                return ResponseEntity.status(500).body(new ApiResponse<>(500,"VerifyFailure", purchase));
            }
        }catch (Exception e){
            System.out.println("exception : " +e);
            return ResponseEntity.status(500).body(new ApiResponse<>(500,"VerifyFailure", e));
        }
    }
}
