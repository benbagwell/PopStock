package com.nashss.se.popstock.models;

import java.util.Objects;

public class ItemModel {
    private final String itemId;
    private final String name;
    private final String regionOfOrigin;
    private final int regionalDemand;
    private final double salesForecast;
    private final double perPallet;
    private final double weight;
    private final double purchaseCost;
    private final double baseMargin;
    private final double rateOfReplenishment;
    private final String category;
    private final String synergy;
    private final boolean active;

    public ItemModel(String itemId, String category, String name, String regionOfOrigin, int regionalDemand, double salesForecast, double perPallet, double weight, double purchaseCost,
                     double baseMargin, double rateOfReplenishment, String synergy, boolean active) {
        this.itemId = itemId;
        this.category = category;
        this.name = name;
        this.regionOfOrigin = regionOfOrigin;
        this.regionalDemand = regionalDemand;
        this.salesForecast = salesForecast;
        this.perPallet = perPallet;
        this.weight = weight;
        this.purchaseCost = purchaseCost;
        this.baseMargin = baseMargin;
        this.rateOfReplenishment = rateOfReplenishment;
        this.synergy = synergy;
        this.active = active;
    }

    public String getItemId() {
        return itemId;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }
    public String getRegionOfOrigin() { return regionOfOrigin; }

    public int getRegionalDemand() {
        return regionalDemand;
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

    public double getPurchaseCost() {
        return purchaseCost;
    }

    public double getBaseMargin() {
        return baseMargin;
    }

    public double getRateOfReplenishment() {
        return rateOfReplenishment;
    }

    public String getSynergy() {
        return synergy;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemModel itemModel = (ItemModel) o;
        return getRegionalDemand() == itemModel.getRegionalDemand() && Double.compare(itemModel.getSalesForecast(), getSalesForecast()) == 0 && Double.compare(itemModel.getPerPallet(),
                getPerPallet()) == 0 && Double.compare(itemModel.getWeight(), getWeight()) == 0 && Double.compare(itemModel.getPurchaseCost(), getPurchaseCost()) == 0 && Double.compare(itemModel.getBaseMargin(),
                getBaseMargin()) == 0 && Double.compare(itemModel.getRateOfReplenishment(), getRateOfReplenishment()) == 0 && isActive() == itemModel.isActive() && getItemId().equals(itemModel.getItemId())
                && getName().equals(itemModel.getName()) && getCategory().equals(itemModel.getCategory()) && getSynergy().equals(itemModel.getSynergy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(), getName(), getRegionalDemand(), getSalesForecast(), getPerPallet(), getWeight(), getPurchaseCost(), getBaseMargin(),
                getRateOfReplenishment(), getCategory(), getSynergy(), isActive());
    }

    public static Builder builder() {return new Builder();}

    public static class Builder {
        private String itemId;
        private String category;
        private String name;
        private int regionalDemand;
        private String regionOfOrigin;
        private double salesForecast;
        private double perPallet;
        private double weight;
        private double purchaseCost;
        private double baseMargin;
        private double rateOfReplenishment;
        private String synergy;
        private boolean active;

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

        public Builder withRegionOfOrigin(String regionOfOrigin) {
            this.regionOfOrigin = regionOfOrigin;
            return this;
        }

        public Builder withRegionalDemand(int regionalDemand) {
            this.regionalDemand = regionalDemand;
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

        public Builder withPurchaseCost(double purchaseCost) {
            this.purchaseCost = purchaseCost;
            return this;
        }

        public Builder withBaseMargin(double baseMargin) {
            this.baseMargin = baseMargin;
            return this;
        }

        public Builder withRateOfReplenishment(double rateOfReplenishment) {
            this.rateOfReplenishment = rateOfReplenishment;
            return this;
        }

        public Builder withSynergy(String synergy) {
            this.synergy = synergy;
            return this;
        }

        public Builder withActive(boolean active) {
            this.active = active;
            return this;
        }

        public ItemModel build() {return new ItemModel(itemId,category,name,regionOfOrigin,regionalDemand,salesForecast,perPallet,weight,purchaseCost,baseMargin,rateOfReplenishment,synergy,active);}
    }
}


