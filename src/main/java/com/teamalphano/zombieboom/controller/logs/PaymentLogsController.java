package com.teamalphano.zombieboom.controller.logs;

import com.teamalphano.zombieboom.dto.common.ApiResponse;
import com.teamalphano.zombieboom.model.logs.PaymentLog;
import com.teamalphano.zombieboom.service.logs.PaymentLogsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/logs")
public class PaymentLogsController {
    private final PaymentLogsService paymentLogsService;

    public PaymentLogsController(PaymentLogsService paymentLogsService){
        this.paymentLogsService = paymentLogsService;
    }

    @GetMapping("/payments")
    public ResponseEntity<ApiResponse<List<PaymentLog>>> getPaymentLogsList() {
        try {
            List<PaymentLog> paymentLogs = paymentLogsService.getPaymentLogsList();
            return ResponseEntity.ok(new ApiResponse<>(200, "Success",paymentLogs));
        }catch (Exception e) {
            return ResponseEntity.status(500).body( null);
        }
    }
}
