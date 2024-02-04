package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.CreateWarehouseRequest;
import com.nashss.se.popstock.activity.results.CreateWarehouseResult;
import com.nashss.se.popstock.converters.ModelConverter;
import com.nashss.se.popstock.dynamodb.WarehouseDao;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import com.nashss.se.popstock.models.WarehouseModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;
import javax.inject.Inject;


public class CreateWarehouseActivity {

    private final Logger log = LogManager.getLogger();
    private final WarehouseDao warehouseDao;

    @Inject
    public CreateWarehouseActivity(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    public CreateWarehouseResult handleRequest(final CreateWarehouseRequest createWarehouseRequest) {
        Warehouse newWarehouse = new Warehouse();

        newWarehouse.setUserId(createWarehouseRequest.getUserId());
        newWarehouse.setWarehouseId(UUID.randomUUID().toString());
        newWarehouse.setName(createWarehouseRequest.getName());

        warehouseDao.saveWarehouse(newWarehouse);

        WarehouseModel warehouseModel = new ModelConverter().toWarehouseModel(newWarehouse);
        return CreateWarehouseResult.builder()
                .withWarehouse(warehouseModel)
                .build();
    }


}
