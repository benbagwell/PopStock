package com.nashss.se.popstock.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.nashss.se.popstock.dynamodb.models.Transaction;
import com.nashss.se.popstock.metrics.MetricsPublisher;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class TransactionDao {

    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public TransactionDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
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
                .withHashKeyValues(transactionPartition);

        return this.dynamoDBMapper.query(Transaction.class, dynamoDBQueryExpression);
    }

}
