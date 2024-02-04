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


        ItemModel itemModel = modelConverter.toItemModel(item);
        assertEquals(item.getWarehouseId(),itemModel.getWarehouseId());
        assertEquals(item.getItemId(),itemModel.getItemId());
        assertEquals(item.getCount(),itemModel.getCount());
        assertEquals(item.getName(),itemModel.getName());
    }

    @Test
    public void toWarehouseModel_convertsWarehouse() {
        Warehouse warehouse = new Warehouse();

        warehouse.setUserId("userId");
        warehouse.setWarehouseId("warehouseId");
        warehouse.setName("name");

        WarehouseModel warehouseModel = modelConverter.toWarehouseModel(warehouse);
        assertEquals(warehouse.getWarehouseId(),warehouseModel.getWarehouseId());
        assertEquals(warehouse.getName(),warehouseModel.getName());
    }
}

