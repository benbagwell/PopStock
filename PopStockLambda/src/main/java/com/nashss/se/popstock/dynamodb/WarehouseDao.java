package com.nashss.se.popstock.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import com.nashss.se.popstock.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

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

    public Warehouse getWarehouse(String userId, String warehouseId) {

       return this.dynamoDBMapper.load(Warehouse.class,userId,warehouseId);
    }

    public List<Warehouse> getWarehouses(String userId) {
        Warehouse warehousePartition = new Warehouse();
        warehousePartition.setUserId(userId);

        DynamoDBQueryExpression<Warehouse> dynamoDBQueryExpression = new DynamoDBQueryExpression<Warehouse>()
                .withHashKeyValues(warehousePartition);

        return this.dynamoDBMapper.query(Warehouse.class, dynamoDBQueryExpression);
    }

    public void deleteWarehouse(Warehouse warehouse) {
        dynamoDBMapper.delete(warehouse);
    }

}
