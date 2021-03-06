package com.selfemployee.market.controller;

import com.selfemployee.market.dto.BidDto;
import com.selfemployee.market.exception.BidExceedBudgetException;
import com.selfemployee.market.exception.NotMatchingProjectFound;
import com.selfemployee.market.service.BidServiceIF;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("marketplace/v1")
public class BidController {

    @Autowired
    private BidServiceIF bidServiceIF;

    @PostMapping(value = "/bid")
    public BidDto createNewBid(@RequestBody BidDto bidDto) throws BidExceedBudgetException {
        return bidServiceIF.createNewBid(bidDto);
    }

    @PostMapping(value = "/autobid")
    public BidDto createAutoBid(@RequestBody BidDto bidDto) throws NotMatchingProjectFound {
        return bidServiceIF.createAutoBid(bidDto);
        
    }
    
}