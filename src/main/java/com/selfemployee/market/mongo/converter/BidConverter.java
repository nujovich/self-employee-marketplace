package com.selfemployee.market.mongo.converter;

import com.selfemployee.market.model.Bid;

import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class BidConverter implements ConverterIF<Bid> {

    @Override
    public Document convertToMongoDocument(Bid model) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Bid convertToModel(Document document) {
        // TODO Auto-generated method stub
        return null;
    }
    
}