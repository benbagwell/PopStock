package com.nashss.se.popstock.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import com.nashss.se.popstock.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class WarehouseDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private MetricsPublisher metricsPublisher;

    private WarehouseDao warehouseDao;

    @BeforeEach
    public void setup() {
        openMocks(this);
        warehouseDao = new WarehouseDao(dynamoDBMapper,metricsPublisher);
    }

    @Test
    public void saveWarehouse_callsMapperWithWarehouse() {

        Warehouse warehouse = new Warehouse();

        Warehouse result = warehouseDao.saveWarehouse(warehouse);

        verify(dynamoDBMapper).save(warehouse);
        assertEquals(warehouse,result);
    }
}
