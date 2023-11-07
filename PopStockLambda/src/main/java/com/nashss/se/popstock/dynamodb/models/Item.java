package com.nashss.se.popstock.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "items")
public class Item {
    private String itemId;
    private String category;
    private String name;
    private String regionOfOrigin;
    private int regionalDemand;
    private double salesForecast;
    private double perPallet;
    private double weight;
    private double purchaseCost;
    private double baseMargin;
    private double rateOfReplenishment;
    private String synergy;
    private boolean active;

    public Item() {
    }

    @DynamoDBHashKey(attributeName = "item_id")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @DynamoDBRangeKey(attributeName = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "region_of_origin")
    public String getRegionOfOrigin() {
        return regionOfOrigin;
    }

    public void setRegionOfOrigin(String regionOfOrigin) {
        this.regionOfOrigin = regionOfOrigin;
    }

    @DynamoDBAttribute(attributeName = "regional_demand")
    public int getRegionalDemand() {
        return regionalDemand;
    }

    public void setRegionalDemand(int regionalDemand) {
        this.regionalDemand = regionalDemand;
    }

    @DynamoDBAttribute(attributeName = "sales_forecast")
    public double getSalesForecast() {
        return salesForecast;
    }

    public void setSalesForecast(double salesForecast) {
        this.salesForecast = salesForecast;
    }

    @DynamoDBAttribute(attributeName = "per_pallet")
    public double getPerPallet() {
        return perPallet;
    }

    public void setPerPallet(double perPallet) {
        this.perPallet = perPallet;
    }

    @DynamoDBAttribute(attributeName = "weight")
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @DynamoDBAttribute(attributeName = "purchase_cost")
    public double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    @DynamoDBAttribute(attributeName = "base_margin")
    public double getBaseMargin() {
        return baseMargin;
    }

    public void setBaseMargin(double baseMargin) {
        this.baseMargin = baseMargin;
    }

    @DynamoDBAttribute(attributeName = "rate_of_replenishment")
    public double getRateOfReplenishment() {
        return rateOfReplenishment;
    }

    public void setRateOfReplenishment(double rateOfReplenishment) {
        this.rateOfReplenishment = rateOfReplenishment;
    }

    @DynamoDBAttribute(attributeName = "synergy")
    public String getSynergy() {
        return synergy;
    }

    public void setSynergy(String synergy) {
        this.synergy = synergy;
    }

    @DynamoDBAttribute(attributeName = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
