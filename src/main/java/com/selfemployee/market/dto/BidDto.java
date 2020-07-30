package com.selfemployee.market.dto;

public class BidDto {

    private String id;
    private String projectId;
    private String sellerId;
    private double bid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public BidDto(String id, String projectId, String sellerId, double bid) {
        this.id = id;
        this.projectId = projectId;
        this.sellerId = sellerId;
        this.bid = bid;
    }

    public BidDto() {
        super();
    }
    
}