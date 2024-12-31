package com.teamalphano.zombieboom.service.logs;

import com.teamalphano.zombieboom.dto.logs.CreatePaymentLogDto;
import com.teamalphano.zombieboom.mapper.logs.PaymentLogsMapper;
import com.teamalphano.zombieboom.model.logs.PaymentLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentLogsService {
    private final PaymentLogsMapper paymentLogsMapper;

    public PaymentLogsService(PaymentLogsMapper paymentLogsMapper){
        this.paymentLogsMapper = paymentLogsMapper;
    }

    @Transactional
    public List<PaymentLog> getPaymentLogsList(){
        return paymentLogsMapper.getPaymentLogsList();
    }

    @Transactional
    public boolean insertPaymentLog(CreatePaymentLogDto createPaymentLogDto){
        int insert = paymentLogsMapper.insertPaymentLog(createPaymentLogDto);
        System.out.println("insert " + insert + " " + createPaymentLogDto);
        return insert > 0;
    }

    @Transactional
    public void updateLogMessage(CreatePaymentLogDto createPaymentLogDto){
        paymentLogsMapper.updateLogMessage(createPaymentLogDto);
    }

}
