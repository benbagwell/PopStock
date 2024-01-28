package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.GetWarehouseRequest;
import com.nashss.se.popstock.activity.results.GetWarehouseResult;
import com.nashss.se.popstock.dynamodb.WarehouseDao;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetWarehouseActivityTest {

    @Mock
    private WarehouseDao warehouseDao;

    private GetWarehouseActivity getWarehouseActivity;

    @BeforeEach
    public void setup() {
        openMocks(this);
        getWarehouseActivity = new GetWarehouseActivity(warehouseDao);
    }
    @Test
    public void handleRequest_warehouseFound_returnsWarehouseModelInResult() {
        String userId = "userId";
        String expectedWarehouseId = "expectedWarehouseId";
        String expectedName = "expectedName";

        Warehouse warehouse = new Warehouse();
        warehouse.setUserId(userId);
        warehouse.setWarehouseId(expectedWarehouseId);
        warehouse.setName(expectedName);

        when(warehouseDao.getWarehouse(userId,expectedWarehouseId)).thenReturn(warehouse);

        GetWarehouseRequest request = GetWarehouseRequest.builder()
                .withUserId(userId)
                .withWarehouseId(expectedWarehouseId)
                .build();

        GetWarehouseResult result = getWarehouseActivity.handleRequest(request);

        assertEquals(expectedName,result.getWarehouse().getName());
        assertEquals(expectedWarehouseId,result.getWarehouse().getWarehouseId());
    }
}
