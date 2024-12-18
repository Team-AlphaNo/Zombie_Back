package com.teamalphano.zombieboom.controller.logs;

import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.model.logs.PaymentLog;
import com.teamalphano.zombieboom.service.logs.PaymentLogsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log/purchase")
public class PaymentLogsController {
    private final PaymentLogsService paymentLogsService;

    public PaymentLogsController(PaymentLogsService paymentLogsService){
        this.paymentLogsService = paymentLogsService;
    }

    //로그 목록
    @GetMapping("/payments")
    public ResponseEntity<ApiResponse<List<PaymentLog>>> getPaymentLogsList() {
        try {
            List<PaymentLog> paymentLogs = paymentLogsService.getPaymentLogsList();
            return ResponseEntity.ok(new ApiResponse<>(200, "Success",paymentLogs));
        }catch (Exception e) {
            return ResponseEntity.status(500).body( null);
        }
    }

    @PostMapping("/ready")
    public ResponseEntity<ApiResponse<String>> insertPurchaseLogReady(
            @RequestBody
            ) {

    }

    @PostMapping("/complete")
    public ResponseEntity<ApiResponse<String>> insertPurchaseLogReady(
            @RequestBody
            ) {

    }

    // Temparory added : 2024.12.18. 11:10. JHH
    @PostMapping("/insert")
    public ResponseEntity<ApiResponse<PaymentLog>> insertPaymentLog(
            @RequestBody PaymentLog paymentLog
    ) {

    }
}
