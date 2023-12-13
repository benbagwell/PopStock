package com.nashss.se.popstock.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "items")
public class Item {
    private String warehouseId;
    private String itemId;
    private String category;
    private String name;
    private double salesForecast;
    private double perPallet;
    private double weight;
    private double rateOfReplenishment;
    private boolean active;

    public Item() {
    }

    @DynamoDBHashKey(attributeName = "warehouse_id")
    public String getWarehouseId() { return warehouseId; }

    public void setWarehouseId(String warehouseId) { this.warehouseId = warehouseId; }

    @DynamoDBRangeKey(attributeName = "item_id")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @DynamoDBAttribute(attributeName = "category")
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

    @DynamoDBAttribute(attributeName = "rate_of_replenishment")
    public double getRateOfReplenishment() {
        return rateOfReplenishment;
    }

    public void setRateOfReplenishment(double rateOfReplenishment) {
        this.rateOfReplenishment = rateOfReplenishment;
    }

    @DynamoDBAttribute(attributeName = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
