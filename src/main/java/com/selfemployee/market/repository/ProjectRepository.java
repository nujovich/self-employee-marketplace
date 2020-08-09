package com.selfemployee.market.repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.StreamSupport;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.selfemployee.market.helper.ValidatorHelper;
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

    @Autowired
    private ValidatorHelper validatorHelper;

    public Project getProjectById(ObjectId id) {
        Project project = null;
        Document projectDocument = mongoOps.getCollection("projects").find(Filters.eq("_id", id)).first();
        if (Objects.nonNull(projectDocument)) {
            project = projectConverter.convertToModel(projectDocument);
        }
        return project;
    }

    public Project saveProject(Project project) {
        return mongoOps.save(project);
    }

    public Document getProjectForAutoBid(double bid) {
        AggregateIterable<Document> projectDocuments = mongoOps.getCollection("projects").aggregate(Arrays.asList(
             Aggregates.match(Filters.lte("budget", bid)),
             Aggregates.match(Filters.gt("endDateForBids", validatorHelper.asDate(LocalDate.now()))),
             Aggregates.project(new Document("min", new Document("$subtract", Arrays.asList(bid, "$budget"))))));
             Document project = StreamSupport.stream(projectDocuments.spliterator(), false)
             .min((d1, d2) -> d1.getDouble("min").compareTo(d2.getDouble("min"))).orElse(null);
         return project;
     }

}
