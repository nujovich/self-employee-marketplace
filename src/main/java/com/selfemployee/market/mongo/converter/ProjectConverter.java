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
        Project item = new Project();
        item.setId(document.getObjectId("_id"));
        item.setDescription(document.getString("description"));
        item.setBudget(document.getDouble("budget"));
        item.setEndDateForBids(document.getDate("endDateForBids"));
        return item;
    }
    
}