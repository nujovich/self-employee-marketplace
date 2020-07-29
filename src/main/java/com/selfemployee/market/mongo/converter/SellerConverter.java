package com.selfemployee.market.mongo.converter;

import com.selfemployee.market.model.Seller;

import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class SellerConverter implements ConverterIF<Seller> {

    @Override
    public Document convertToMongoDocument(Seller model) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Seller convertToModel(Document document) {
        // TODO Auto-generated method stub
        return null;
    }
    
}