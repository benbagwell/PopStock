package com.nashss.se.popstock.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.popstock.dynamodb.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class ItemDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private PaginatedQueryList<Item> queryList;

    private ItemDao itemDao;

    @BeforeEach
    public void setup() {
        openMocks(this);
        itemDao = new ItemDao(dynamoDBMapper);
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

    @Test
    public void deleteItem_callsMapperWithItem() {
        Item item = new Item();

        itemDao.deleteItem(item);

        verify(dynamoDBMapper).delete(item);
    }

    @Test
    public void getItems_verifiesMapperQuery() {
        DynamoDBQueryExpression<Item> queryExpression = new DynamoDBQueryExpression<>();
        when(dynamoDBMapper.query(Item.class, queryExpression)).thenReturn(queryList);

        List<Item> items = itemDao.getItems("warehouseId");
        verify(dynamoDBMapper).query(any(), any());
    }
}
