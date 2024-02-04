package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.GetAllWarehousesRequest;
import com.nashss.se.popstock.activity.results.GetAllWarehousesResult;
import com.nashss.se.popstock.dynamodb.WarehouseDao;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import com.nashss.se.popstock.models.WarehouseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetAllWarehousesActivityTest {

    @Mock
    private WarehouseDao warehouseDao;

    GetAllWarehousesActivity getAllWarehousesActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        getAllWarehousesActivity = new GetAllWarehousesActivity(warehouseDao);
    }

    @Test
    public void handleRequest_whenPassedUserId_returnsUserWarehouses() {
        String userId = "userId";
        GetAllWarehousesRequest getAllWarehousesRequest = GetAllWarehousesRequest.builder()
                .withUserId(userId)
                .build();

        Warehouse warehouse = new Warehouse();
        warehouse.setUserId(userId);
        warehouse.setWarehouseId("1234");
        warehouse.setName("name");

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setUserId(userId);
        warehouse2.setWarehouseId("12342");
        warehouse2.setName("name2");

        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse);
        warehouses.add(warehouse2);

        WarehouseModel warehouseModel = WarehouseModel.builder()
                .withWarehouseId("1234")
                .withName("name")
                .build();

        WarehouseModel warehouseModel2 = WarehouseModel.builder()
                .withWarehouseId("12342")
                .withName("name2")
                .build();

        List<WarehouseModel> modelList = new ArrayList<>();
        modelList.add(warehouseModel);
        modelList.add(warehouseModel2);

        when(warehouseDao.getWarehouses(userId)).thenReturn(warehouses);

        GetAllWarehousesResult getAllWarehousesResult = getAllWarehousesActivity.handleRequest(getAllWarehousesRequest);

        assertEquals(getAllWarehousesResult.getWarehousesModel().getWarehouses(), modelList);
    }


}
