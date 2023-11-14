package com.nashss.se.popstock.models;

import java.util.ArrayList;
import java.util.List;

public class WarehousesModel {
    private final List<WarehouseModel> warehouses;
    public WarehousesModel(List<WarehouseModel> warehouses) {
        this.warehouses = warehouses;
    }

    public List<WarehouseModel> getWarehouses() {
        return new ArrayList<>(warehouses);
    }


}
