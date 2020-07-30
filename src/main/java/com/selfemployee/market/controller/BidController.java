package com.selfemployee.market.controller;

import com.selfemployee.market.dto.BidDto;
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
    public BidDto createNewBid(@RequestBody BidDto bidDto) {
        return bidServiceIF.createNewBid(bidDto);
    }
    
}