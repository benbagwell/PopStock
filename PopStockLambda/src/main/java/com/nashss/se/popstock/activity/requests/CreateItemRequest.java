package com.nashss.se.popstock.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateItemRequest.Builder.class)
public class CreateItemRequest {

    private final String warehouseId;
    private final String name;


    public CreateItemRequest(String warehouseId, String name) {
        this.warehouseId = warehouseId;
        this.name = name;

    }

    public String getWarehouseId() { return warehouseId; }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CreateItemRequest{" +
                "warehouseId='" + warehouseId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
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

        public CreateItemRequest build() {
            return new CreateItemRequest(warehouseId, name);
        }
    }
}
