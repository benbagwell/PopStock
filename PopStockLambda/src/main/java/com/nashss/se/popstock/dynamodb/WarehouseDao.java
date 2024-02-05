package com.nashss.se.popstock.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.nashss.se.popstock.dynamodb.models.Warehouse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class WarehouseDao {

    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public WarehouseDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {
        this.dynamoDBMapper.save(warehouse);
        return warehouse;
    }

    public Warehouse getWarehouse(String userId, String warehouseId) {
        return this.dynamoDBMapper.load(Warehouse.class, userId, warehouseId);
    }

    public List<Warehouse> getWarehouses(String userId) {
        Warehouse warehousePartition = new Warehouse();
        warehousePartition.setUserId(userId);

        DynamoDBQueryExpression<Warehouse> dynamoDBQueryExpression = new DynamoDBQueryExpression<Warehouse>()
                .withHashKeyValues(warehousePartition);

        return this.dynamoDBMapper.query(Warehouse.class, dynamoDBQueryExpression);
    }

    public void deleteWarehouse(Warehouse warehouse) {
        System.out.println("warehouseid: " + warehouse.getWarehouseId() + " username: " + warehouse.getUserId());
        dynamoDBMapper.delete(warehouse);
    }

}
