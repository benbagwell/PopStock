package com.nashss.se.popstock.models;

import java.time.LocalDate;
import java.util.Objects;

public class TransactionModel {
    private final String transactionId;

    private final String warehouseId;

    private final String shipmentId;

    private final String itemId;

    private final Integer count;

    private final LocalDate transactionDate;

    private final String partnerId;

    private final String transactionType;

    public TransactionModel(String transactionId, String warehouseId, String shipmentId, String itemId, Integer count,
                            LocalDate transactionDate, String partnerId, String transactionType) {
        this.transactionId = transactionId;
        this.warehouseId = warehouseId;
        this.shipmentId = shipmentId;
        this.itemId = itemId;
        this.count = count;
        this.transactionDate = transactionDate;
        this.partnerId = partnerId;
        this.transactionType = transactionType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public String getShipmentId() {
        return shipmentId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionModel that = (TransactionModel) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(warehouseId, that.warehouseId) && Objects.equals(shipmentId, that.shipmentId) && Objects.equals(itemId, that.itemId) && Objects.equals(count, that.count) && Objects.equals(transactionDate, that.transactionDate) && Objects.equals(partnerId, that.partnerId) && Objects.equals(transactionType, that.transactionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, warehouseId, shipmentId, itemId, count, transactionDate, partnerId, transactionType);
    }

    public static ItemModel.Builder builder() {return new ItemModel.Builder();}

    public static class Builder {

        private String transactionId;

        private String warehouseId;

        private String shipmentId;

        private String itemId;

        private Integer count;

        private LocalDate transactionDate;

        private String partnerId;

        private String transactionType;

        public Builder withTransactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder withWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder withShipmentId(String shipmentId) {
            this.shipmentId = shipmentId;
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

        public TransactionModel build(){return new TransactionModel(transactionId,warehouseId,shipmentId,itemId,count,transactionDate,partnerId,transactionType);}
    }

}
