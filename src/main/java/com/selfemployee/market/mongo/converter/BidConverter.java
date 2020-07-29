package com.selfemployee.market.mongo.converter;

import com.selfemployee.market.model.Bid;

import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class BidConverter implements ConverterIF<Bid> {

    @Override
    public Document convertToMongoDocument(Bid model) {
        Document document = new Document();
        document.put("_id", model.getId());
        document.put("projectId", model.getProjectId());
        document.put("sellerId", model.getSellerId());
        document.put("bid", model.getBid());
        return document;
    }

    @Override
    public Bid convertToModel(Document document) {
        Bid item = new Bid();
        item.setId(document.getObjectId("_id"));
        item.setProjectId(document.getObjectId("projectId"));
        item.setSellerId(document.getObjectId("sellerId"));
        item.setBid(document.getDouble("bid"));
        return item;
    }
    
}