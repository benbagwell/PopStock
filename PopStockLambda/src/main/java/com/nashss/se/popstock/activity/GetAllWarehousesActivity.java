package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.GetAllWarehousesRequest;
import com.nashss.se.popstock.activity.results.GetAllWarehousesResult;
import com.nashss.se.popstock.converters.ModelConverter;
import com.nashss.se.popstock.dynamodb.WarehouseDao;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import com.nashss.se.popstock.models.WarehousesModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class GetAllWarehousesActivity {

    private final Logger log = LogManager.getLogger();

    private final WarehouseDao warehouseDao;

    @Inject
    public GetAllWarehousesActivity(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    public GetAllWarehousesResult handleRequest(final GetAllWarehousesRequest getAllWarehousesRequest) {

        List<Warehouse> warehouses = warehouseDao.getWarehouses(getAllWarehousesRequest.getUserId());
        ModelConverter modelConverter = new ModelConverter();

        return GetAllWarehousesResult.builder()
                .withWarehousesModel(new WarehousesModel(
                        warehouses.stream()
                        .map(modelConverter::toWarehouseModel)
                        .collect(Collectors.toList())))
                .build();
    }
}
