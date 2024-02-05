package com.nashss.se.popstock.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetTransactionReportRequest.Builder.class)
public class GetTransactionReportRequest {

    private final String warehouseId;
    private final String startDate;
    private final String endDate;

    public GetTransactionReportRequest(String warehouseId, String startDate, String endDate) {
        this.warehouseId = warehouseId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "GetTransactionReportRequest{" +
                "warehouseId='" + warehouseId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String warehouseId;
        private String startDate;
        private String endDate;

        public Builder withWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder withStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public GetTransactionReportRequest build() { return new GetTransactionReportRequest(warehouseId, startDate, endDate); }
    }
}
