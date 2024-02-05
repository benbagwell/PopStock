package com.nashss.se.popstock.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;
import java.util.Map;

@JsonDeserialize(builder = CreateTransactionRequest.Builder.class)
public class CreateTransactionRequest {

    private final String warehouseId;
    private final String itemId;
    private final Integer count;
    private final LocalDate transactionDate;
    private final String partnerId;
    private final String transactionType;

    public CreateTransactionRequest(String warehouseId, String itemId, Integer count, LocalDate transactionDate, String partnerId, String transactionType) {
        this.warehouseId = warehouseId;
        this.itemId = itemId;
        this.count = count;
        this.transactionDate = transactionDate;
        this.partnerId = partnerId;
        this.transactionType = transactionType;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public String getItemId() {
        return itemId;
    }

    public Integer getCount() {
        return count;
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
                ", itemId='" + itemId + '\'' +
                ", count=" + count +
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
        private String itemId;
        private Integer count;
        private LocalDate transactionDate;
        private String partnerId;
        private String transactionType;

        public Builder withWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder withItemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder withCount(Integer count) {
            this.count = count;
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
            return new CreateTransactionRequest(warehouseId, itemId, count, transactionDate, partnerId, transactionType);
        }
    }
}
