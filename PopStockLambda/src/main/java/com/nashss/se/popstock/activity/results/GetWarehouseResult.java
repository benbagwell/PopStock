package com.nashss.se.popstock.activity.results;

import com.nashss.se.popstock.models.WarehouseModel;

public class GetWarehouseResult {

    private final WarehouseModel warehouse;

    public GetWarehouseResult(WarehouseModel warehouse) {
        this.warehouse = warehouse;
    }

    public WarehouseModel getWarehouse() {
        return warehouse;
    }

    @Override
    public String toString() {
        return "GetWarehouseResult{" +
                "warehouse=" + warehouse +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static class Builder {
        private WarehouseModel warehouse;

        public Builder withWarehouse(WarehouseModel warehouse) {
            this.warehouse = warehouse;
            return this;
        }

        public GetWarehouseResult build() { return new GetWarehouseResult(warehouse); }
    }
}
