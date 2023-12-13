package com.nashss.se.popstock.activity.results;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.popstock.models.ItemModel;

import java.util.List;

public class GetItemsResult {

    private final List<ItemModel> items;

    public GetItemsResult(List<ItemModel> items) {
        this.items = items;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public static GetItemsResult.Builder builder() {
        return new GetItemsResult.Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private List<ItemModel> items;

        public Builder withItems(List<ItemModel> items) {
            this.items = items;
            return this;
        }

        public GetItemsResult build() { return new GetItemsResult(items); }
    }
}
