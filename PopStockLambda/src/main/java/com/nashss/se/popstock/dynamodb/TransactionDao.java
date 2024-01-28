package com.nashss.se.popstock.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.popstock.dynamodb.models.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class TransactionDao {

    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public TransactionDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Transaction saveTransaction(Transaction transaction) {
        dynamoDBMapper.save(transaction);
        return transaction;
    }

    public void deleteTransaction(Transaction transaction) {
        dynamoDBMapper.delete(transaction);
    }

    public List<Transaction> getTransactions(String warehouseId) {
        Transaction transactionPartition = new Transaction();
        transactionPartition.setWarehouseId(warehouseId);

        DynamoDBQueryExpression<Transaction> dynamoDBQueryExpression = new DynamoDBQueryExpression<Transaction>()
                .withConsistentRead(false)
                .withHashKeyValues(transactionPartition);

        return this.dynamoDBMapper.query(Transaction.class, dynamoDBQueryExpression);
    }

    public List<Transaction> getTransactionsBetweenDates(String warehouseId, LocalDate startDate, LocalDate endDate) {

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":warehouseId", new AttributeValue().withS(warehouseId));
        valueMap.put(":startDate", new AttributeValue().withS(startDate.toString()));
        valueMap.put(":endDate", new AttributeValue().withS(endDate.toString()));

        DynamoDBQueryExpression<Transaction> queryExpression = new DynamoDBQueryExpression<Transaction>()
                .withIndexName(Transaction.SHIPPING_DATE_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("warehouseId = :warehouseId AND dateOfEvent BETWEEN :startDate and :today")
                .withExpressionAttributeValues(valueMap);

        PaginatedQueryList<Transaction> transactions = dynamoDBMapper.query(Transaction.class, queryExpression);
        return transactions == null ? new ArrayList<>() : transactions;
    }

}
