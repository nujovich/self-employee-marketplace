package com.selfemployee.market.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProjectDto {
    
    private String id;
    private String description;
    private float budget;
    private LocalDateTime endDateForBids;
    private float minBid;


    public ProjectDto() {
        super();

    }
    public ProjectDto(String id, String description, float budget, LocalDateTime endDateForBids, float minBid) {
        this.id = id;
        this.description = description;
        this.budget = budget;
        this.endDateForBids = endDateForBids;
        this.minBid = minBid;
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

    public float getBudget() {
        return this.budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public LocalDateTime getEndDateForBids() {
        return this.endDateForBids;
    }

    public void setEndDateForBids(LocalDateTime endDateForBids) {
        this.endDateForBids = endDateForBids;
    }

    public float getMinBid() {
        return this.minBid;
    }

    public void setMinBid(float minBid) {
        this.minBid = minBid;
    }

    public ProjectDto id(String id) {
        this.id = id;
        return this;
    }

    public ProjectDto description(String description) {
        this.description = description;
        return this;
    }

    public ProjectDto budget(float budget) {
        this.budget = budget;
        return this;
    }

    public ProjectDto endDateForBids(LocalDateTime endDateForBids) {
        this.endDateForBids = endDateForBids;
        return this;
    }

    public ProjectDto minBid(float minBid) {
        this.minBid = minBid;
        return this;
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
