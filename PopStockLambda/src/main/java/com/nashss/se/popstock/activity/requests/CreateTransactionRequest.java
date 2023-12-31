package com.nashss.se.popstock.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;
import java.util.Map;

public class CreateTransactionRequest {

    private final String warehouseId;
    private final Map<String, Integer> inventoryUpdate;
    private final LocalDate transactionDate;
    private final String partnerId;
    private final String transactionType;

    public CreateTransactionRequest(String warehouseId, Map<String, Integer> inventoryUpdate, LocalDate transactionDate, String partnerId, String transactionType) {
        this.warehouseId = warehouseId;
        this.inventoryUpdate = inventoryUpdate;
        this.transactionDate = transactionDate;
        this.partnerId = partnerId;
        this.transactionType = transactionType;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

   public Map<String, Integer> getInventoryUpdate() {
        return inventoryUpdate;
   }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    @Override
    public String toString() {
        return "CreateTransactionRequest{" +
                "warehouseId='" + warehouseId + '\'' +
                ", inventoryUpdate=" + inventoryUpdate +
                ", transactionDate=" + transactionDate +
                ", partnerId='" + partnerId + '\'' +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String warehouseId;
        private Map<String, Integer> inventoryUpdate;
        private LocalDate transactionDate;
        private String partnerId;
        private String transactionType;

        public Builder withWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder withInventoryUpdate(Map<String, Integer> inventoryUpdate) {
            this.inventoryUpdate = inventoryUpdate;
            return this;
        }

        public Builder withTransactionDate(LocalDate transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public Builder withPartnerId(String partnerId) {
            this.partnerId = partnerId;
            return this;
        }

        public Builder withTransactionType(String transactionType) {
            this.transactionType = transactionType;
            return this;
        }

        public CreateTransactionRequest build() {
            return new CreateTransactionRequest(warehouseId, inventoryUpdate, transactionDate, partnerId, transactionType);
        }
    }
}
