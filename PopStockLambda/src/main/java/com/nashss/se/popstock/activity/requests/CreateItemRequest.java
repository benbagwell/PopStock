package com.nashss.se.popstock.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateItemRequest.Builder.class)
public class CreateItemRequest {
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

    public CreateItemRequest(String category, String name, String regionOfOrigin, int regionalDemand, double salesForecast, double perPallet,
                             double weight, double purchaseCost, double baseMargin, double rateOfReplenishment, String synergy) {
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

    @Override
    public String toString() {
        return "CreateItemRequest{" +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", regionOfOrigin=" + regionOfOrigin +
                ", regionalDemand=" + regionalDemand +
                ", salesForecast=" + salesForecast +
                ", perPallet=" + perPallet +
                ", weight=" + weight +
                ", purchaseCost=" + purchaseCost +
                ", baseMargin=" + baseMargin +
                ", rateOfReplenishment=" + rateOfReplenishment +
                ", synergy='" + synergy+
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
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

        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withRegionOfOrigin(String regionOfOrigin){
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


        public CreateItemRequest build() {return new CreateItemRequest(category,name, regionOfOrigin,regionalDemand,salesForecast,perPallet,weight,purchaseCost,baseMargin,rateOfReplenishment,synergy);}
    }
}
