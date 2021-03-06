package com.selfemployee.market.controlleradvice;

import com.selfemployee.market.exception.BidExceedBudgetException;
import com.selfemployee.market.exception.ExceptionResponse;
import com.selfemployee.market.exception.NotMatchingProjectFound;
import com.selfemployee.market.exception.PastDateException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
    
    @ExceptionHandler(PastDateException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handlePastDateException(final PastDateException exception) {
        final ExceptionResponse ex = new ExceptionResponse();
        ex.setErrorMessage(exception.getMessage());
        ex.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return ex;
    }

    @ExceptionHandler(BidExceedBudgetException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleBidExceedBudgetException(final BidExceedBudgetException exception) {
        final ExceptionResponse ex = new ExceptionResponse();
        ex.setErrorMessage(exception.getMessage());
        ex.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return ex;
    }
    @ExceptionHandler(NotMatchingProjectFound.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleNotMatchingProjectException(final NotMatchingProjectFound exception) {
        final ExceptionResponse ex = new ExceptionResponse();
        ex.setErrorMessage(exception.getMessage());
        ex.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return ex;
    }
}