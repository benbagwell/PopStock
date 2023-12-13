package com.nashss.se.popstock.models;

import java.util.Objects;

public class ItemModel {
    private final String warehouseId;
    private final String itemId;
    private final String name;
    private final double salesForecast;
    private final double perPallet;
    private final double weight;
    private final double rateOfReplenishment;
    private final String category;
    private final boolean active;

    public ItemModel(String warehouseId, String itemId, String category, String name, double salesForecast,
                     double perPallet, double weight, double rateOfReplenishment, boolean active) {
        this.warehouseId = warehouseId;
        this.itemId = itemId;
        this.category = category;
        this.name = name;
        this.salesForecast = salesForecast;
        this.perPallet = perPallet;
        this.weight = weight;
        this.rateOfReplenishment = rateOfReplenishment;
        this.active = active;
    }
    public String getWarehouseId() { return warehouseId; }
    public String getItemId() {
        return itemId;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public double getSalesForecast() {
        return salesForecast;
    }

    public double getPerPallet() {
        return perPallet;
    }

    public double getWeight() {
        return weight;
    }

    public double getRateOfReplenishment() {
        return rateOfReplenishment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemModel itemModel = (ItemModel) o;
        return Double.compare(itemModel.salesForecast, salesForecast) == 0 && Double.compare(itemModel.perPallet, perPallet) == 0 && Double.compare(itemModel.weight, weight) == 0
                && Double.compare(itemModel.rateOfReplenishment, rateOfReplenishment) == 0 && active == itemModel.active && Objects.equals(warehouseId, itemModel.warehouseId)
                && Objects.equals(itemId, itemModel.itemId) && Objects.equals(name, itemModel.name) && Objects.equals(category, itemModel.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouseId, itemId, name, salesForecast, perPallet, weight, rateOfReplenishment, category, active);
    }

    public boolean isActive() {
        return active;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {

        private String warehouseId;
        private String itemId;
        private String category;
        private String name;
        private double salesForecast;
        private double perPallet;
        private double weight;
        private double rateOfReplenishment;
        private boolean active;

        public Builder withWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder withItemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSalesForecast(double salesForecast) {
            this.salesForecast = salesForecast;
            return this;
        }

        public Builder withPerPallet(double perPallet) {
            this.perPallet = perPallet;
            return this;
        }

        public Builder withWeight(double weight) {
            this.weight = weight;
            return this;
        }

        public Builder withRateOfReplenishment(double rateOfReplenishment) {
            this.rateOfReplenishment = rateOfReplenishment;
            return this;
        }

        public Builder withActive(boolean active) {
            this.active = active;
            return this;
        }

        public ItemModel build() { return new ItemModel(warehouseId, itemId, category, name, salesForecast,
                perPallet, weight, rateOfReplenishment, active); }
    }
}


