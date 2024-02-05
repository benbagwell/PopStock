package com.nashss.se.popstock.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.popstock.dynamodb.models.Warehouse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class WarehouseDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private PaginatedQueryList<Warehouse> queryList;

    private WarehouseDao warehouseDao;

    @BeforeEach
    public void setup() {
        openMocks(this);
        warehouseDao = new WarehouseDao(dynamoDBMapper);
    }

    @Test
    public void saveWarehouse_callsMapperWithWarehouse() {

        Warehouse warehouse = new Warehouse();

        Warehouse result = warehouseDao.saveWarehouse(warehouse);

        verify(dynamoDBMapper).save(warehouse);
        assertEquals(warehouse,result);
    }

    @Test
    public void getWarehouses_callsMapperWithQuery() {
        DynamoDBQueryExpression<Warehouse> queryExpression = new DynamoDBQueryExpression<>();
        when(dynamoDBMapper.query(Warehouse.class, queryExpression)).thenReturn(queryList);

        List<Warehouse> warehouses = warehouseDao.getWarehouses("userId");
        verify(dynamoDBMapper).query(any(), any());
    }

}
