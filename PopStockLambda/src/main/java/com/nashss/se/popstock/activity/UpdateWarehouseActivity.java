package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.GetWarehouseRequest;
import com.nashss.se.popstock.activity.requests.UpdateWarehouseRequest;
import com.nashss.se.popstock.activity.results.GetWarehouseResult;
import com.nashss.se.popstock.activity.results.UpdateWarehouseResult;
import com.nashss.se.popstock.converters.ModelConverter;
import com.nashss.se.popstock.dynamodb.WarehouseDao;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateWarehouseActivity {

    private final Logger log = LogManager.getLogger();

    private final WarehouseDao warehouseDao;

    @Inject
    public UpdateWarehouseActivity(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    public UpdateWarehouseResult handleRequest(final UpdateWarehouseRequest updateWarehouseRequest) {

       Warehouse warehouse = warehouseDao.getWarehouse(updateWarehouseRequest.getUserId(), updateWarehouseRequest.getWarehouseId());
       warehouse.setName(updateWarehouseRequest.getName());
       warehouseDao.saveWarehouse(warehouse);

        ModelConverter modelConverter = new ModelConverter();

        return UpdateWarehouseResult.builder()
                .withWarehouse(modelConverter.toWarehouseModel(warehouse))
                .build();
    }
}
