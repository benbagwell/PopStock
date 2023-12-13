package com.nashss.se.popstock.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class GetWarehouseRequest {
    private final String userId;

    private final String warehouseId;

    public GetWarehouseRequest(String userId, String warehouseId) {
        this.userId = userId;
        this.warehouseId = warehouseId;
    }

    public String getUserId() {
        return userId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    @Override
    public String toString() {
        return "GetWarehouseRequest{" +
                "userId='" + userId + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    @JsonPOJOBuilder
    public static class Builder {
        private String userId;
        private String warehouseId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public GetWarehouseRequest build() { return new GetWarehouseRequest(userId, warehouseId); }
    }
}
