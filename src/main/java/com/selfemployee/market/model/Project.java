package com.selfemployee.market.model;

import java.time.LocalDateTime;

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
    private LocalDateTime endDateForBids;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public LocalDateTime getEndDateForBids() {
        return endDateForBids;
    }

    public void setEndDateForBids(LocalDateTime endDateForBids) {
        this.endDateForBids = endDateForBids;
    }

}