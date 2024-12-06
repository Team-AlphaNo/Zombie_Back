package com.teamalphano.zombieboom.dto.common;

public class ApiResponse<T>  {
    private Integer status;
    private String message;// 사용자에게 전달할 메시지
    private T data;         // 실제 데이터 페이로드

    // 기본 생성자
    public ApiResponse() {
    }

    // 모든 필드를 받는 생성자
    public ApiResponse(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
