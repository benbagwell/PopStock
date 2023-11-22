package com.nashss.se.popstock.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.time.LocalDate;

@DynamoDBTable(tableName = "transactions")
public class Transaction {

    private String warehouseId;

    private String transactionId;

    private String shipmentId;

    private String itemId;

    private Integer count;

    private LocalDate transactionDate;

    private String partnerId;

    private String transactionType;


}
