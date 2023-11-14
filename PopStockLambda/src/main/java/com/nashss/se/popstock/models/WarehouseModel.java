package com.nashss.se.popstock.models;

import com.nashss.se.popstock.dynamodb.models.Item;

import java.util.Objects;

public class WarehouseModel {
    private final String wareHouseId;
    private final String name;
    private final String region;

    public WarehouseModel(String wareHouseId, String name, String region) {
        this.wareHouseId = wareHouseId;
        this.name = name;
        this.region = region;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseModel that = (WarehouseModel) o;
        return Objects.equals(wareHouseId, that.wareHouseId) && Objects.equals(name, that.name) && Objects.equals(region, that.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wareHouseId, name, region);
    }

    public static Builder builder() {return new Builder();}

    public static class Builder {
        private String warehouseId;
        private String name;
        private String region;

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

        public WarehouseModel build() {return new WarehouseModel(warehouseId,name,region);}
    }
}
