package com.selfemployee.market.mongo.converter;

import org.bson.Document;

public interface ConverterIF<T> {
    
    public Document convertToMongoDocument(T model);
    public T convertToModel(Document document);
}