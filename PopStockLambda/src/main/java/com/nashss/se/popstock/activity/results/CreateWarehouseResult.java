package com.nashss.se.popstock.activity.results;


import com.nashss.se.popstock.models.WarehouseModel;

public class CreateWarehouseResult {

    private final WarehouseModel warehouse;

    public CreateWarehouseResult(WarehouseModel warehouse) {
        this.warehouse = warehouse;
    }

    public WarehouseModel getWarehouse() {
        return warehouse;
    }

    @Override
    public String toString() {
        return "CreateWarehouseResult{" +
                "warehouse=" + warehouse +
                '}';
    }

    public static CreateWarehouseResult.Builder builder() {return new CreateWarehouseResult.Builder();}

    public static class Builder {
        private WarehouseModel warehouse;

        public Builder withWarehouse(WarehouseModel warehouse) {
            this.warehouse = warehouse;
            return this;
        }

        public CreateWarehouseResult build(){return new CreateWarehouseResult(warehouse);}
    }
}
