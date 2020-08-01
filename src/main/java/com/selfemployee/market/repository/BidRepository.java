package com.selfemployee.market.repository;

import java.util.Arrays;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.selfemployee.market.model.Bid;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class BidRepository {

    @Autowired
    private MongoOperations mongoOps;

    public Document getMinBidByProjectId(ObjectId id) {
        Document bidDocument = mongoOps.getCollection("bids").aggregate(Arrays.asList(
                Aggregates.match((Filters.eq("projectId", id))),
                Aggregates.sort(Sorts.ascending("bid")), 
                Aggregates.lookup("sellers", "sellerId", "_id", "seller"))).first();
        return bidDocument;
    }

    public Bid saveBid(Bid bid) {
        return mongoOps.save(bid);
    }
}