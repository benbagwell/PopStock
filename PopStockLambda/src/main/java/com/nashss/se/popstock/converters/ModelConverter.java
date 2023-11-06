package com.nashss.se.popstock.converters;

import com.nashss.se.popstock.dynamodb.models.Item;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import com.nashss.se.popstock.models.ItemModel;
import com.nashss.se.popstock.models.WarehouseModel;

public class ModelConverter {

    public ItemModel toItemModel(Item item) {
        return ItemModel.builder()
                .withItemId(item.getItemId())
                .withCategory(item.getCategory())
                .withName(item.getName())
                .withRegionOfOrigin(item.getRegionOfOrigin())
                .withRegionalDemand(item.getRegionalDemand())
                .withSalesForecast(item.getSalesForecast())
                .withPerPallet(item.getPerPallet())
                .withWeight(item.getWeight())
                .withPurchaseCost(item.getPurchaseCost())
                .withBaseMargin(item.getBaseMargin())
                .withRateOfReplenishment(item.getRateOfReplenishment())
                .withSynergy(item.getSynergy()).withActive(item.isActive())
                .build();
    }

    public WarehouseModel toWarehouseModel(Warehouse warehouse) {
        return WarehouseModel.builder()
                .withUserId(warehouse.getUserId())
                .withWarehouseId(warehouse.getWarehouseId())
                .withName(warehouse.getName())
                .withRegion(warehouse.getRegion())
                .withInventory(warehouse.getInventory())
                .build();
    }
}
