package com.teamalphano.zombieboom.model.logs;

public class PaymentLog {
    private Integer paymentLogsNo;
    private Integer userNo;
    private String paymentStatus;
    private String createDate;
    private String updateDate;

    //유저 정보
    private String userId;
    private String userUuid;
    private String userName;
    private String userEmail;
    private boolean delYn;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public boolean isDelYn() {
        return delYn;
    }

    public void setDelYn(boolean delYn) {
        this.delYn = delYn;
    }
}
