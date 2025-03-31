package com.kodbook.dto;

public class ErrorDto {

    private String message;
    private Integer code;
    private String reason;

    public ErrorDto() {
        // TODO Auto-generated constructor stub
    }

    public ErrorDto(String message, Integer code, String reason) {
        super();
        this.message = message;
        this.code = code;
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ErrorDto [message=" + message + ", code=" + code + ", reason=" + reason + "]";
    }


}
