package com.selfemployee.market.mongo.converter;

import com.selfemployee.market.model.Project;

import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter implements ConverterIF<Project> {

    @Override
    public Document convertToMongoDocument(Project model) {
        Document document = new Document();
        document.put("_id", model.getId());
        document.put("description", model.getDescription());
        document.put("budget", model.getBudget());
        document.put("endDateForBids", model.getEndDateForBids());
        return document;
    }

    @Override
    public Project convertToModel(Document document) {
        Project item = new Project.ProjectBuilder(document.getString("description"), 
        document.getDouble("budget"), document.getDate("endDateForBids")).setId(document.getObjectId("_id"))
        .build();
        return item;
    }
    
}