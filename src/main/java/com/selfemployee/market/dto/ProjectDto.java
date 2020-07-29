package com.selfemployee.market.dto;

import java.util.Date;
import java.util.Objects;

public class ProjectDto {
    
    private String id;
    private String description;
    private double budget;
    private Date endDateForBids;
    private double minBid;


    public ProjectDto() {
        super();

    }
    public ProjectDto(String id, String description, double budget, Date endDateForBids, double minBid) {
        this.id = id;
        this.description = description;
        this.budget = budget;
        this.endDateForBids = endDateForBids;
        this.minBid = minBid;
    }

    public ProjectDto(String id, String description, double budget, Date endDateForBids) {
        this.id = id;
        this.description = description;
        this.budget = budget;
        this.endDateForBids = endDateForBids;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBudget() {
        return this.budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Date getEndDateForBids() {
        return this.endDateForBids;
    }

    public void setEndDateForBids(Date endDateForBids) {
        this.endDateForBids = endDateForBids;
    }

    public double getMinBid() {
        return this.minBid;
    }

    public void setMinBid(float minBid) {
        this.minBid = minBid;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProjectDto)) {
            return false;
        }
        ProjectDto projectDto = (ProjectDto) o;
        return Objects.equals(id, projectDto.id) && Objects.equals(description, projectDto.description) && budget == projectDto.budget && Objects.equals(endDateForBids, projectDto.endDateForBids) && minBid == projectDto.minBid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, budget, endDateForBids, minBid);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", budget='" + getBudget() + "'" +
            ", endDateForBids='" + getEndDateForBids() + "'" +
            ", minBid='" + getMinBid() + "'" +
            "}";
    }
}
