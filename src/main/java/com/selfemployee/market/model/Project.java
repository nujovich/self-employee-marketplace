package com.selfemployee.market.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


 /**
  * Model class for project
  */
@Document(collection = "projects")
public class Project {
    
    @JsonProperty("_id")
    private ObjectId id;
    private String description;
    private double budget;
    private Date endDateForBids;

    public Project() {
        super();
    }

    private Project(ProjectBuilder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.budget = builder.budget;
        this.endDateForBids = builder.endDateForBids;
    }

    public ObjectId getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getBudget() {
        return budget;
    } 

    public Date getEndDateForBids() {
        return endDateForBids;
    }

    public static class ProjectBuilder {
        private ObjectId id;
        private final String description;
        private final double budget;
        private final Date endDateForBids;

        public ProjectBuilder(String description, double budget, Date endDateForBids) {
            this.description = description;
            this.budget = budget;
            this.endDateForBids = endDateForBids;
        }

        public ProjectBuilder setId(ObjectId id) {
            this.id = id;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }
}