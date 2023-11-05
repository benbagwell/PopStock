package com.nashss.se.popstock.converters;

import com.nashss.se.popstock.dynamodb.models.Item;
import com.nashss.se.popstock.models.ItemModel;

public class ModelConverter {

    public ItemModel toItemModel(Item item) {
        return ItemModel.builder()
                .withItemId(item.getItemId())
                .withCategory(item.getCategory())
                .withName(item.getName())
                .withRegionOfOrigin(item.getRegionOfOrigin())
                .withRegionalDemand(item.getRegionalDemand())
                .withSalesForecast(item.getSalesForecast())
                .withPerPallet(item.getPerPallet())
                .withWeight(item.getWeight())
                .withPurchaseCost(item.getPurchaseCost())
                .withBaseMargin(item.getBaseMargin())
                .withRateOfReplenishment(item.getRateOfReplenishment())
                .withSynergy(item.getSynergy()).withActive(item.isActive())
                .build();
    }
}
