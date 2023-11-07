package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.CreateWarehouseRequest;
import com.nashss.se.popstock.activity.results.CreateWarehouseResult;
import com.nashss.se.popstock.dynamodb.WarehouseDao;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateWarehouseActivityTest {

    @Mock
    private WarehouseDao warehouseDao;

    private CreateWarehouseActivity createWarehouseActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        createWarehouseActivity = new CreateWarehouseActivity(warehouseDao);
    }

    @Test
    public void handleRequest_createsAndSavesWarehouse() {
        String expectedUserId = "userId";
        String expectedName = "name";
        String expectedRegion = "region";

        CreateWarehouseRequest request = CreateWarehouseRequest.builder()
                .withUserId(expectedUserId)
                .withName(expectedName)
                .withRegion(expectedRegion)
                .build();

        CreateWarehouseResult result = createWarehouseActivity.handleRequest(request);

        verify(warehouseDao).saveWarehouse(any(Warehouse.class));

        assertNotNull(result.getWarehouse().getWareHouseId());
        assertEquals(expectedUserId,result.getWarehouse().getUserId());
        assertEquals(expectedName,result.getWarehouse().getName());
        assertEquals(expectedRegion,result.getWarehouse().getRegion());
        
    }
}
