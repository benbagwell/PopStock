package com.nashss.se.popstock.activity.results;

import com.nashss.se.popstock.models.WarehouseModel;

public class DeleteWarehouseResult {
    private final WarehouseModel warehouse;

    public DeleteWarehouseResult(WarehouseModel warehouse) {
        this.warehouse = warehouse;
    }

    public WarehouseModel getWarehouse() {
        return warehouse;
    }

    @Override
    public String toString() {
        return "DeleteWarehouseResult{" +
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

        public DeleteWarehouseResult build() {return new DeleteWarehouseResult(warehouse);}
    }
}

