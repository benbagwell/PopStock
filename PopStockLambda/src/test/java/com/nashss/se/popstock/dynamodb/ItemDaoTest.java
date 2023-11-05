package com.nashss.se.popstock.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.popstock.dynamodb.models.Item;
import com.nashss.se.popstock.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class ItemDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private MetricsPublisher metricsPublisher;

    private ItemDao itemDao;

    @BeforeEach
    public void setup() {
        openMocks(this);
        itemDao = new ItemDao(dynamoDBMapper,metricsPublisher);
    }

    @Test
    public void saveItem_callsMapperWithItem() {
        // GIVEN
        Item item = new Item();

        // WHEN
        Item result = itemDao.saveItem(item);

        // THEN
        verify(dynamoDBMapper).save(item);
        assertEquals(item, result);
    }
}
