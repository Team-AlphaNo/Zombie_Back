package com.teamalphano.zombieboom.service.logs;

import com.teamalphano.zombieboom.mapper.logs.PaymentLogsMapper;
import com.teamalphano.zombieboom.model.logs.PaymentLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentLogsService {
    private final PaymentLogsMapper paymentLogsMapper;

    public PaymentLogsService(PaymentLogsMapper paymentLogsMapper){
        this.paymentLogsMapper = paymentLogsMapper;
    }

    public List<PaymentLog> getPaymentLogsList(){
        return paymentLogsMapper.getPaymentLogsList();
    }
}
