package com.nashss.se.popstock.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.nashss.se.popstock.converters.LocalDateToStringConverter;

import java.time.LocalDate;

@DynamoDBTable(tableName = "transactions")
public class Transaction {

    public static final String SHIPPING_DATE_INDEX = "shipping_date_index";
    private String warehouseId;

    private String transactionId;

    private String itemId;

    private Integer count;

    private LocalDate transactionDate;

    private String partnerId;

    private String transactionType;

    @DynamoDBHashKey(attributeName = "warehouse_id")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = SHIPPING_DATE_INDEX)
    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    @DynamoDBHashKey(attributeName = "transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @DynamoDBAttribute(attributeName = "item_id")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @DynamoDBAttribute(attributeName = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @DynamoDBAttribute(attributeName = "transaction_date")
    @DynamoDBTypeConverted(converter = LocalDateToStringConverter.class)
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = SHIPPING_DATE_INDEX)
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @DynamoDBAttribute(attributeName = "partner_id")
    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @DynamoDBAttribute(attributeName = "transaction_type")
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
