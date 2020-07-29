package com.selfemployee.market.mongo.converter;

import com.selfemployee.market.model.Project;

import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter implements ConverterIF<Project> {

    @Override
    public Document convertToMongoDocument(Project model) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Project convertToModel(Document document) {
        // TODO Auto-generated method stub
        return null;
    }
    
}