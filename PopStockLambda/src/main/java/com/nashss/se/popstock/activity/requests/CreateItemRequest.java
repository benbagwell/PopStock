package com.nashss.se.popstock.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateItemRequest.Builder.class)
public class CreateItemRequest {

    private final String warehouseId;
    private final String category;
    private final String name;
    private final double salesForecast;
    private final double perPallet;
    private final double weight;
    private final double rateOfReplenishment;

    public CreateItemRequest(String warehouseId, String category, String name, double salesForecast, double perPallet,
                             double weight, double rateOfReplenishment) {
        this.warehouseId = warehouseId;
        this.category = category;
        this.name = name;
        this.salesForecast = salesForecast;
        this.perPallet = perPallet;
        this.weight = weight;
        this.rateOfReplenishment = rateOfReplenishment;
    }

    public String getWarehouseId() { return warehouseId; }
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
    public String toString() {
        return "CreateItemRequest{" +
                "warehouseId='" + warehouseId + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", salesForecast=" + salesForecast +
                ", perPallet=" + perPallet +
                ", weight=" + weight +
                ", rateOfReplenishment=" + rateOfReplenishment +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String warehouseId;
        private String category;
        private String name;
        private double salesForecast;
        private double perPallet;
        private double weight;
        private double rateOfReplenishment;

        public Builder withWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
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

        public CreateItemRequest build() {return new CreateItemRequest(warehouseId,category,name,salesForecast,perPallet,weight,rateOfReplenishment);}
    }
}
