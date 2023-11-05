package com.nashss.se.popstock.activity.results;

import com.nashss.se.popstock.models.ItemModel;

public class CreateItemResult {

    private final ItemModel item;

    public CreateItemResult(ItemModel item) {
        this.item = item;
    }

    public ItemModel getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "CreateItemResult{" +
                "item=" + item +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static class Builder {
        private ItemModel item;

        public Builder withItem(ItemModel item) {
            this.item = item;
            return this;
        }

        public CreateItemResult build(){return new CreateItemResult(item);}
    }
}
