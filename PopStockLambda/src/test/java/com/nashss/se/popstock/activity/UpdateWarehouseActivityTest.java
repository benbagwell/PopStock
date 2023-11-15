package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.GetWarehouseRequest;
import com.nashss.se.popstock.activity.requests.UpdateWarehouseRequest;
import com.nashss.se.popstock.activity.results.GetWarehouseResult;
import com.nashss.se.popstock.activity.results.UpdateWarehouseResult;
import com.nashss.se.popstock.dynamodb.WarehouseDao;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateWarehouseActivityTest {

    @Mock
    private WarehouseDao warehouseDao;

    private UpdateWarehouseActivity updateWarehouseActivity;

    @BeforeEach
    public void setup() {
        openMocks(this);
        updateWarehouseActivity = new UpdateWarehouseActivity(warehouseDao);
    }
    @Test
    public void handleRequest_warehouseFound_returnsWarehouseModelInResult() {
        String userId = "userId";
        String expectedWarehouseId = "expectedWarehouseId";
        String originalName = "originalName";
        String expectedRegion = "expectedRegion";
        String updatedName = "updatedName";

        Warehouse warehouse = new Warehouse();
        warehouse.setUserId(userId);
        warehouse.setWarehouseId(expectedWarehouseId);
        warehouse.setName(originalName);
        warehouse.setRegion(expectedRegion);

        when(warehouseDao.getWarehouse(userId,expectedWarehouseId)).thenReturn(warehouse);

        UpdateWarehouseRequest request = UpdateWarehouseRequest.builder()
                .withUserId(userId)
                .withWarehouseId(expectedWarehouseId)
                .withName(updatedName)
                .build();

        UpdateWarehouseResult result = updateWarehouseActivity.handleRequest(request);

        assertEquals(updatedName,result.getWarehouse().getName());
        assertEquals(expectedWarehouseId,result.getWarehouse().getWarehouseId());
        assertEquals(expectedRegion,result.getWarehouse().getRegion());
    }
}
