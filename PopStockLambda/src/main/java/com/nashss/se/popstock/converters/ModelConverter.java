package com.nashss.se.popstock.converters;

import com.nashss.se.popstock.dynamodb.models.Item;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import com.nashss.se.popstock.models.ItemModel;
import com.nashss.se.popstock.models.WarehouseModel;

public class ModelConverter {

    public ItemModel toItemModel(Item item) {
        return ItemModel.builder()
                .withWarehouseId(item.getWarehouseId())
                .withItemId(item.getItemId())
                .withCategory(item.getCategory())
                .withName(item.getName())
                .withSalesForecast(item.getSalesForecast())
                .withPerPallet(item.getPerPallet())
                .withWeight(item.getWeight())
                .withRateOfReplenishment(item.getRateOfReplenishment())
                .withActive(item.isActive())
                .build();
    }

    public WarehouseModel toWarehouseModel(Warehouse warehouse) {
        return WarehouseModel.builder()
                .withWarehouseId(warehouse.getWarehouseId())
                .withName(warehouse.getName())
                .withRegion(warehouse.getRegion())
                .build();
    }
}
