package com.selfemployee.market.service;

import com.selfemployee.market.dto.BidDto;
import com.selfemployee.market.model.Bid;
import com.selfemployee.market.repository.BidRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidServiceIF {

    @Autowired
    private BidRepository bidRepository;

    @Override
    public BidDto createnewBid(BidDto bidDto) {
        Bid bid = new Bid(new ObjectId(bidDto.getProjectId()), new ObjectId(bidDto.getSellerId()), bidDto.getBid());
        bid = bidRepository.saveBid(bid);
        bidDto.setId(bid.getId().toString());
        return bidDto;
    }
    
}