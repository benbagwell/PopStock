package com.nashss.se.popstock.converter;

import com.nashss.se.popstock.converters.ModelConverter;
import com.nashss.se.popstock.dynamodb.models.Item;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import com.nashss.se.popstock.models.ItemModel;
import com.nashss.se.popstock.models.WarehouseModel;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelConverterTest {

    private ModelConverter modelConverter = new ModelConverter();

    @Test
    public void toItemModel_convertsItem(){
        Item item = new Item();
        item.setItemId("id");
        item.setName("name");
        item.setCategory("category");
        item.setRegionOfOrigin("region");
        item.setSalesForecast(2.0);
        item.setPerPallet(1.0);
        item.setWeight(3.0);
        item.setPurchaseCost(4.0);
        item.setBaseMargin(5.0);
        item.setRateOfReplenishment(6.0);
        item.setSynergy("synergy");
        item.setActive(true);

        ItemModel itemModel = modelConverter.toItemModel(item);
        assertEquals(item.getItemId(),itemModel.getItemId());
        assertEquals(item.getName(),itemModel.getName());
        assertEquals(item.getCategory(),itemModel.getCategory());
        assertEquals(item.getRegionOfOrigin(),itemModel.getRegionOfOrigin());
        assertEquals(item.getSalesForecast(), itemModel.getSalesForecast());
        assertEquals(item.getPerPallet(),itemModel.getPerPallet());
        assertEquals(item.getWeight(),itemModel.getWeight());
        assertEquals(item.getPurchaseCost(),itemModel.getPurchaseCost());
        assertEquals(item.getBaseMargin(),itemModel.getBaseMargin());
        assertEquals(item.getRateOfReplenishment(),itemModel.getRateOfReplenishment());
        assertEquals(item.getSynergy(),itemModel.getSynergy());
        assertEquals(item.isActive(), itemModel.isActive());
    }

    @Test
    public void toWarehouseModel_convertsWarehouse() {
        Warehouse warehouse = new Warehouse();
        Map<Item,Integer> inventoryMap = new HashMap();

        Item item = new Item();
        item.setItemId("id");
        item.setName("name");
        inventoryMap.put(item,5);

        warehouse.setUserId("userId");
        warehouse.setWarehouseId("warehouseId");
        warehouse.setName("name");
        warehouse.setRegion("region");

        WarehouseModel warehouseModel = modelConverter.toWarehouseModel(warehouse);
        assertEquals(warehouse.getWarehouseId(),warehouseModel.getWareHouseId());
        assertEquals(warehouse.getName(),warehouseModel.getName());
        assertEquals(warehouse.getRegion(),warehouseModel.getRegion());
    }
}

