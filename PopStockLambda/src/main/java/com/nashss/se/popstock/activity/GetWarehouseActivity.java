package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.GetWarehouseRequest;
import com.nashss.se.popstock.activity.results.GetWarehouseResult;
import com.nashss.se.popstock.converters.ModelConverter;
import com.nashss.se.popstock.dynamodb.WarehouseDao;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import com.nashss.se.popstock.models.WarehouseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetWarehouseActivity {

    private final Logger log = LogManager.getLogger();

    private final WarehouseDao warehouseDao;

    @Inject
    public GetWarehouseActivity(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    public GetWarehouseResult handleRequest(final GetWarehouseRequest getWarehouseRequest) {

        Warehouse warehouse = warehouseDao.getWarehouse(getWarehouseRequest.getUserId(), getWarehouseRequest.getWarehouseId());

        return GetWarehouseResult.builder()
                .withWarehouse(new ModelConverter().toWarehouseModel(warehouse))
                .build();
    }
}
