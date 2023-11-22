package com.nashss.se.popstock.activity.requests;

public class DeleteWarehouseRequest {
    private final String userId;

    private final String warehouseId;

    public DeleteWarehouseRequest(String userId, String warehouseId) {
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
        return "DeleteWarehouseRequest{" +
                "userId='" + userId + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                '}';
    }

    public static Builder builder() {return new Builder();}

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

        public DeleteWarehouseRequest build() {return new DeleteWarehouseRequest(userId,warehouseId);}
    }
}