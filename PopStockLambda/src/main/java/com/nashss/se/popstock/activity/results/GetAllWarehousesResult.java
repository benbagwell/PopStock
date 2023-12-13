package com.nashss.se.popstock.activity.results;

import com.nashss.se.popstock.models.WarehousesModel;

public class GetAllWarehousesResult {

    private final WarehousesModel warehousesModel;

    private GetAllWarehousesResult(WarehousesModel warehousesModel) {
        this.warehousesModel = warehousesModel;
    }

    public WarehousesModel getWarehousesModel() {
        return warehousesModel;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private WarehousesModel warehousesModel;

        public Builder withWarehousesModel(WarehousesModel warehousesModel) {
            this.warehousesModel = warehousesModel;
            return this;
        }

        public GetAllWarehousesResult build() { return new GetAllWarehousesResult(warehousesModel); }
    }
}
