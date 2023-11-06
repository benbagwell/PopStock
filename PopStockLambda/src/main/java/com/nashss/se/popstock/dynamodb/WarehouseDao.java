package com.nashss.se.popstock.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import com.nashss.se.popstock.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class WarehouseDao {

    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public WarehouseDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {
        this.dynamoDBMapper.save(warehouse);
        return warehouse;
    }
}
