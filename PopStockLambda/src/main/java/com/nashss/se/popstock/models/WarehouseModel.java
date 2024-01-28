package com.nashss.se.popstock.models;

import java.util.Objects;

public class WarehouseModel {
    private final String warehouseId;
    private final String name;

    public WarehouseModel(String warehouseId, String name) {
        this.warehouseId = warehouseId;
        this.name = name;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseModel that = (WarehouseModel) o;
        return Objects.equals(warehouseId, that.warehouseId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouseId, name);
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String warehouseId;
        private String name;

        public Builder withWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public WarehouseModel build() { return new WarehouseModel(warehouseId, name); }
    }
}
