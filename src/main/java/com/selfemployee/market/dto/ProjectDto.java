package com.selfemployee.market.dto;

import java.util.Date;

public class ProjectDto {
    
    private String id;
    private String description;
    private double budget;
    private Date endDateForBids;
    private double minBid;
    private String name;
    private String lastName;

   
    public String getId() {
        return this.id;
    }


    public String getDescription() {
        return this.description;
    }

    public double getBudget() {
        return this.budget;
    }

    public Date getEndDateForBids() {
        return this.endDateForBids;
    }

    public double getMinBid() {
        return this.minBid;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public ProjectDto(){
        super();
    }

    private ProjectDto (ProjectDtoBuilder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.budget = builder.budget;
        this.endDateForBids = builder.endDateForBids;
        this.minBid = builder.minBid;
        this.name = builder.name;
        this.lastName = builder.lastName;
    }

    public static class ProjectDtoBuilder {
        private final String id;
        private final String description;
        private final double budget;
        private final Date endDateForBids;
        private double minBid;
        private String name = "N/A";
        private String lastName = "N/A";

        public ProjectDtoBuilder(String id, String description, double budget, Date endDateForBids) {
            this.id = id;
            this.description = description;
            this.budget = budget;
            this.endDateForBids = endDateForBids;
        }

        public ProjectDtoBuilder setMinBid(double minBid) {
            this.minBid = minBid;
            return this;
        }

        public ProjectDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProjectDtoBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ProjectDto build() {
            return new ProjectDto(this);
        }
        
    }
}
