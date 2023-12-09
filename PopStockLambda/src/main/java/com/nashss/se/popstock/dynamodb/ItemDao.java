package com.nashss.se.popstock.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.popstock.dynamodb.models.Item;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import com.nashss.se.popstock.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

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

    public List<Item> getItems(String warehouseID) {

    }
}
