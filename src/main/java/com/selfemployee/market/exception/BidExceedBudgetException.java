package com.selfemployee.market.exception;

public class BidExceedBudgetException extends Exception{

    private static final long serialVersionUID = 1L;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BidExceedBudgetException(String message) {
        this.message = message;
    }
    
}