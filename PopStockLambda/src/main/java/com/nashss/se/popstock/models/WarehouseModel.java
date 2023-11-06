package com.nashss.se.popstock.models;

import com.nashss.se.popstock.dynamodb.models.Item;

import java.util.Map;
import java.util.Objects;

public class WarehouseModel {
    private final String userId;
    private final String wareHouseId;
    private final String name;
    private final String region;
    private final Map<Item,Integer> inventory;

    public WarehouseModel(String userId, String wareHouseId, String name, String region, Map<Item, Integer> inventory) {
        this.userId = userId;
        this.wareHouseId = wareHouseId;
        this.name = name;
        this.region = region;
        this.inventory = inventory;
    }

    public String getUserId() {
        return userId;
    }

    public String getWareHouseId() {
        return wareHouseId;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public Map<Item, Integer> getInventory() {
        return inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseModel that = (WarehouseModel) o;
        return getUserId().equals(that.getUserId()) && getWareHouseId().equals(that.getWareHouseId()) && getName().equals(that.getName()) && getRegion().equals(that.getRegion()) && getInventory().equals(that.getInventory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getWareHouseId(), getName(), getRegion(), getInventory());
    }

    public static Builder builder() {return new Builder();}

    public static class Builder {
        private String userId;
        private String warehouseId;
        private String name;
        private String region;
        private Map<Item,Integer> inventory;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withRegion(String region) {
            this.region = region;
            return this;
        }

        public Builder withInventory(Map<Item, Integer> inventory) {
            this.inventory = inventory;
            return this;
        }

        public WarehouseModel build() {return new WarehouseModel(userId,warehouseId,name,region,inventory);}
    }
}
