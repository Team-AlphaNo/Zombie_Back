package com.teamalphano.zombieboom.dto.logs;

public class PaymentCreateDto {
    private Integer paymentLogsNo;
    private Integer userNo;
    private String paymentStatus;
    private String createDate;
    private String updateDate;
    private Integer prodNo;

    public Integer getPaymentLogsNo() {
        return paymentLogsNo;
    }

    public void setPaymentLogsNo(Integer paymentLogsNo) {
        this.paymentLogsNo = paymentLogsNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getProdNo() {
        return prodNo;
    }

    public void setProdNo(Integer prodNo) {
        this.prodNo = prodNo;
    }
}
