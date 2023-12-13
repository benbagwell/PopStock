package com.nashss.se.popstock.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class GetAllWarehousesRequest {

    private final String userId;

    public GetAllWarehousesRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "GetAllWarehousesRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder(); }

    @JsonPOJOBuilder
    public static class Builder {

        private String userId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public GetAllWarehousesRequest build() { return new GetAllWarehousesRequest(userId); }
    }
}
