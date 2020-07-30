package com.selfemployee.market.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bids")
public class Bid {

    private ObjectId id;
    private ObjectId projectId;
    private ObjectId sellerId;
    private double bid;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getProjectId() {
        return projectId;
    }

    public void setProjectId(ObjectId projectId) {
        this.projectId = projectId;
    }

    public ObjectId getSellerId() {
        return sellerId;
    }

    public void setSellerId(ObjectId sellerId) {
        this.sellerId = sellerId;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public Bid(ObjectId projectId, ObjectId sellerId, double bid) {
        this.projectId = projectId;
        this.sellerId = sellerId;
        this.bid = bid;
    }

    public Bid() {
        super();
    }

}