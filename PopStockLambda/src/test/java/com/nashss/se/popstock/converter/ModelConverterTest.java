package com.nashss.se.popstock.converter;

import com.nashss.se.popstock.converters.ModelConverter;
import com.nashss.se.popstock.dynamodb.models.Item;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import com.nashss.se.popstock.models.ItemModel;
import com.nashss.se.popstock.models.WarehouseModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelConverterTest {

    private final ModelConverter modelConverter = new ModelConverter();

    @Test
    public void toItemModel_convertsItem(){
        Item item = new Item();
        item.setItemId("id");
        item.setWarehouseId("warehouseId");
        item.setCount(5);
        item.setName("name");
        item.setCategory("category");
        item.setSalesForecast(2.0);
        item.setPerPallet(1.0);
        item.setWeight(3.0);
        item.setRateOfReplenishment(6.0);

        ItemModel itemModel = modelConverter.toItemModel(item);
        assertEquals(item.getWarehouseId(),itemModel.getWarehouseId());
        assertEquals(item.getItemId(),itemModel.getItemId());
        assertEquals(item.getCount(),itemModel.getCount());
        assertEquals(item.getName(),itemModel.getName());
        assertEquals(item.getCategory(),itemModel.getCategory());
        assertEquals(item.getSalesForecast(), itemModel.getSalesForecast());
        assertEquals(item.getPerPallet(),itemModel.getPerPallet());
        assertEquals(item.getWeight(),itemModel.getWeight());
        assertEquals(item.getRateOfReplenishment(),itemModel.getRateOfReplenishment());
    }

    @Test
    public void toWarehouseModel_convertsWarehouse() {
        Warehouse warehouse = new Warehouse();

        warehouse.setUserId("userId");
        warehouse.setWarehouseId("warehouseId");
        warehouse.setName("name");
        warehouse.setRegion("region");

        WarehouseModel warehouseModel = modelConverter.toWarehouseModel(warehouse);
        assertEquals(warehouse.getWarehouseId(),warehouseModel.getWarehouseId());
        assertEquals(warehouse.getName(),warehouseModel.getName());
        assertEquals(warehouse.getRegion(),warehouseModel.getRegion());
    }
}

