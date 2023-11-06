package com.nashss.se.popstock.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Map;

@DynamoDBTable(tableName = "warehouses")
public class Warehouse {

    private String userId;;
    private String warehouseId;
    private String name;
    private String region;
    private Map<Item,Integer> inventory;

    @DynamoDBHashKey(attributeName = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBRangeKey(attributeName = "warehouse_id")
    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @DynamoDBAttribute(attributeName = "inventory")
    public Map<Item, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Item, Integer> inventory) {
        this.inventory = inventory;
    }
}
