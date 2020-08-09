package com.selfemployee.market.service;

import com.selfemployee.market.dto.BidDto;
import com.selfemployee.market.exception.BidExceedBudgetException;
import com.selfemployee.market.exception.NotMatchingProjectFound;

public interface BidServiceIF {

    public BidDto createNewBid(BidDto bidDto) throws BidExceedBudgetException;
    public BidDto createAutoBid(BidDto bidDto) throws NotMatchingProjectFound;
    
}