package com.selfemployee.market.service;

import com.selfemployee.market.dto.BidDto;
import com.selfemployee.market.exception.BidExceedBudgetException;

public interface BidServiceIF {

    public BidDto createNewBid(BidDto bidDto) throws BidExceedBudgetException;
    
}