package com.nashss.se.popstock.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;

public class GetTransactionReportRequest {

    private final String warehouseId;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public GetTransactionReportRequest(String warehouseId, LocalDate startDate, LocalDate endDate) {
        this.warehouseId = warehouseId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
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
        private LocalDate startDate;
        private LocalDate endDate;

        public Builder withWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }


    }
}
