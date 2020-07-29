package com.selfemployee.market.mongo.converter;

import com.selfemployee.market.model.Seller;

import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class SellerConverter implements ConverterIF<Seller> {

    @Override
    public Document convertToMongoDocument(Seller model) {
        Document document = new Document();
        document.put("_id", model.getId());
        document.put("name", model.getName());
        document.put("lastName", model.getLastName());
        document.put("bid", model.getBid());
        return document;
    }

    @Override
    public Seller convertToModel(Document document) {
        Seller item = new Seller();
        item.setId(document.getObjectId("_id"));
        item.setName(document.getString("name"));
        item.setLastName(document.getString("lastName"));
        item.setBid(document.getDouble("bid"));
        return item;
    }
    
}