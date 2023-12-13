package com.nashss.se.popstock.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateWarehouseRequest.Builder.class)
public class CreateWarehouseRequest {

    private final String userId;
    private final String name;
    private final String region;

    public CreateWarehouseRequest(String userId, String name, String region) {
        this.userId = userId;
        this.name = name;
        this.region = region;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "CreateWarehouseRequest{" +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    public static CreateWarehouseRequest.Builder builder() {
        return new CreateWarehouseRequest.Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String userId;
        private String name;
        private String region;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withRegion(String region) {
            this.region = region;
            return this;
        }

        public CreateWarehouseRequest build() { return new CreateWarehouseRequest(userId, name, region); }
    }
}
