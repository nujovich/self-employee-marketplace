package com.selfemployee.market.repository;

import java.util.Objects;

import com.mongodb.client.model.Filters;
import com.selfemployee.market.model.Project;
import com.selfemployee.market.mongo.converter.ProjectConverter;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {

    @Autowired
    private MongoOperations mongoOps;

    @Autowired
    private ProjectConverter projectConverter;

    public Project getProjectById(ObjectId id) {
        Project project = null;
        Document projectDocument = mongoOps.getCollection("projects").find(Filters.eq("_id", id)).first();
        if(Objects.nonNull(projectDocument)) {
            project = projectConverter.convertToModel(projectDocument);
        }
        return project;
    }

    public Project saveProject(Project project) {
        return mongoOps.save(project);
    }

}
