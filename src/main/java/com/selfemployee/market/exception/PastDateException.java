package com.selfemployee.market.exception;

public class PastDateException extends Exception {

    private static final long serialVersionUID = 1L;

    private String message;

    public PastDateException(String message) {
        this.message = message;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}