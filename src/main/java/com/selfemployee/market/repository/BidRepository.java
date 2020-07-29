package com.selfemployee.market.repository;

import java.util.Objects;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.selfemployee.market.model.Bid;
import com.selfemployee.market.mongo.converter.BidConverter;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class BidRepository {
    @Autowired
    private MongoOperations mongoOps;

    @Autowired
    private BidConverter bidConverter;

    public double getMinBidByProjectId(ObjectId id) {
        double minBid = 0.0;
        Document bidDocument = mongoOps.getCollection("bids").find(Filters.eq("projectId", id)).sort(Sorts.ascending("bid")).first();
        if(Objects.nonNull(bidDocument)) {
            Bid bid = bidConverter.convertToModel(bidDocument);
            minBid = bid.getBid();
        }
        return minBid;
    }
}