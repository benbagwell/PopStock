package com.nashss.se.popstock.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateWarehouseRequest.Builder.class)
public class UpdateWarehouseRequest {

    private final String userId;

    private final String warehouseId;

    private final String name;

    public UpdateWarehouseRequest(String userId, String warehouseId, String name) {
        this.userId = userId;
        this.warehouseId = warehouseId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UpdateWarehouseRequest{" +
                "userId='" + userId + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static Builder builder() {return new Builder();}

    @JsonPOJOBuilder
    public static class Builder {
        private String userId;
        private String warehouseId;
        private String name;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public UpdateWarehouseRequest build() {return new UpdateWarehouseRequest(userId,warehouseId,name);}
    }
}
