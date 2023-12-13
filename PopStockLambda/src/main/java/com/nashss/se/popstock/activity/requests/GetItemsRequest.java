package com.nashss.se.popstock.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class GetItemsRequest {

    private final String warehouseId;

    public GetItemsRequest(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    @Override
    public String toString() {
        return "GetItemsRequest{" +
                " warehouseId='" + warehouseId + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String warehouseId;

        public Builder withUserId(String userId) {
            return this;
        }

        public Builder withWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public GetItemsRequest build() { return new GetItemsRequest(warehouseId); }
    }
}
