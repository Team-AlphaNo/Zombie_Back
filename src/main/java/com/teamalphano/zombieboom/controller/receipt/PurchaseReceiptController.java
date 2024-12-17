package com.teamalphano.zombieboom.controller.receipt;

import com.google.api.services.androidpublisher.model.ProductPurchase;
import com.google.api.services.androidpublisher.model.SubscriptionPurchaseV2;
import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.dto.purchase.PurchaseDto;
import com.teamalphano.zombieboom.service.receipt.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/purchase")
public class PurchaseReceiptController {
    private final PurchaseService purchaseService;

    public PurchaseReceiptController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }


    @PostMapping("/verify/google")
    public ResponseEntity<ApiResponse<?>> purchaseVerify(
            @RequestBody PurchaseDto purchaseDto) throws GeneralSecurityException, IOException {
        try{
            ProductPurchase purchase = purchaseService.verifyReceiptForGoogleProduct(purchaseDto.getProductID(), purchaseDto.getPurchaseToken());
            System.out.println("############   purchase   :  " + purchase.toString());
            Integer status = purchase.getPurchaseState();

            if(status == 0){
                //지급
                return ResponseEntity.ok(new ApiResponse<>(200, "Success", "Success"));
            }else{
                //error
                return ResponseEntity.status(500).body(new ApiResponse<>(500, "Fail", "purchaseStatus not success"));
            }
        }catch (Exception e){
            System.out.println("exception : " +e);
            return ResponseEntity.status(500).body(new ApiResponse<>(500,"VerifyFailure", e));
        }
    }
}
