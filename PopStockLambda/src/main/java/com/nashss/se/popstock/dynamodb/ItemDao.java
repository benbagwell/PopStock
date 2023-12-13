package com.nashss.se.popstock.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.nashss.se.popstock.dynamodb.models.Item;

import com.nashss.se.popstock.metrics.MetricsPublisher;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class ItemDao {

    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public ItemDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Item saveItem(Item item) {
        this.dynamoDBMapper.save(item);
        return item;
    }

    public void deleteItem(Item item) {
        this.dynamoDBMapper.delete(item);
    }

    public List<Item> getItems(String warehouseId) {
        Item itemPartition = new Item();
        itemPartition.setWarehouseId(warehouseId);

        DynamoDBQueryExpression<Item> dynamoDBQueryExpression = new DynamoDBQueryExpression<Item>()
                .withHashKeyValues(itemPartition);

        return this.dynamoDBMapper.query(Item.class, dynamoDBQueryExpression);
    }
}
