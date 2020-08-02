package com.selfemployee.market.service;

import com.selfemployee.market.dto.BidDto;
import com.selfemployee.market.exception.BidExceedBudgetException;
import com.selfemployee.market.model.Bid;
import com.selfemployee.market.model.Project;
import com.selfemployee.market.repository.BidRepository;
import com.selfemployee.market.repository.ProjectRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidServiceIF {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public BidDto createNewBid(BidDto bidDto) throws BidExceedBudgetException{
        Project project = projectRepository.getProjectById(new ObjectId(bidDto.getProjectId()));
        if(project.getBudget() < bidDto.getBid()) {
            throw new BidExceedBudgetException("Your bid exceeds the project's budget");
        }
        Bid bid = new Bid(new ObjectId(bidDto.getProjectId()), new ObjectId(bidDto.getSellerId()), bidDto.getBid());
        bid = bidRepository.saveBid(bid);
        bidDto.setId(bid.getId().toString());
        return bidDto;
    }
    
}