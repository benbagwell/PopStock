package com.nashss.se.popstock.models;

import java.util.Objects;

public class ItemModel {
    private final String warehouseId;
    private final String itemId;
    private final int count;
    private final String name;

    public ItemModel(String warehouseId, String itemId, int count, String name) {
        this.warehouseId = warehouseId;
        this.itemId = itemId;
        this.count = count;
        this.name = name;

    }
    public String getWarehouseId() { return warehouseId; }
    public String getItemId() {
        return itemId;
    }

    public int getCount() { return count; }


    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemModel itemModel = (ItemModel) o;
        return count == itemModel.count && Objects.equals(warehouseId, itemModel.warehouseId) && Objects.equals(itemId, itemModel.itemId) && Objects.equals(name, itemModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouseId, itemId, count, name);
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {

        private String warehouseId;
        private String itemId;
        private int count;
        private String name;

        public Builder withWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder withItemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder withCount(int count) {
            this.count = count;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public ItemModel build() { return new ItemModel(warehouseId, itemId, count, name); }
    }
}


