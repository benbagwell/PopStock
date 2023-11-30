package com.nashss.se.popstock.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.nashss.se.popstock.metrics.MetricsPublisher;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class TransactionDao {

    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    public TransactionDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public void deleteWarehouseTransactions(String warehouseId) {
        DynamoDBScanExpression dbScanExpression = new DynamoDBScanExpression();
        dbScanExpression.
        List<String> warehouseTransactions = dynamoDBMapper.scan()
    }
}
