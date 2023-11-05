package com.nashss.se.popstock.converter;

import com.nashss.se.popstock.converters.ModelConverter;
import com.nashss.se.popstock.dynamodb.models.Item;
import com.nashss.se.popstock.models.ItemModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelConverterTest {

    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toItemModel_withNonNullValues_convertsItem(){
        Item item = new Item();
        item.setItemId("id");
        item.setName("name");
        item.setCategory("category");
        item.setRegionOfOrigin("region");
        item.setSalesForecast(2.0);
        item.setPerPallet(1.0);
        item.setWeight(3.0);
        item.setPurchaseCost(4.0);
        item.setBaseMargin(5.0);
        item.setRateOfReplenishment(6.0);
        item.setSynergy("synergy");
        item.setActive(true);

        ItemModel itemModel = modelConverter.toItemModel(item);
        assertEquals(item.getItemId(),itemModel.getItemId());
        assertEquals(item.getName(),itemModel.getName());
        assertEquals(item.getCategory(),itemModel.getCategory());
        assertEquals(item.getRegionOfOrigin(),itemModel.getRegionOfOrigin());
        assertEquals(item.getSalesForecast(), itemModel.getSalesForecast());
        assertEquals(item.getPerPallet(),itemModel.getPerPallet());
        assertEquals(item.getWeight(),itemModel.getWeight());
        assertEquals(item.getPurchaseCost(),itemModel.getPurchaseCost());
        assertEquals(item.getBaseMargin(),itemModel.getBaseMargin());
        assertEquals(item.getRateOfReplenishment(),itemModel.getRateOfReplenishment());
        assertEquals(item.getSynergy(),itemModel.getSynergy());
        assertEquals(item.isActive(), itemModel.isActive());
    }

}

