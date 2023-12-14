package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.CreateItemRequest;
import com.nashss.se.popstock.activity.results.CreateItemResult;
import com.nashss.se.popstock.converters.ModelConverter;
import com.nashss.se.popstock.dynamodb.ItemDao;
import com.nashss.se.popstock.dynamodb.models.Item;
import com.nashss.se.popstock.models.ItemModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;
import javax.inject.Inject;


public class CreateItemActivity {

    private final Logger log = LogManager.getLogger();
    private final ItemDao itemDao;

    @Inject
    public CreateItemActivity(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public CreateItemResult handleRequest(final CreateItemRequest createItemRequest) {
        Item newItem = new Item();

        newItem.setWarehouseId(createItemRequest.getWarehouseId());
        newItem.setItemId(UUID.randomUUID().toString());
        newItem.setCount(0);
        newItem.setCategory(createItemRequest.getCategory());
        newItem.setName(createItemRequest.getName());
        newItem.setSalesForecast(createItemRequest.getSalesForecast());
        newItem.setPerPallet(createItemRequest.getPerPallet());
        newItem.setWeight(createItemRequest.getWeight());
        newItem.setRateOfReplenishment(createItemRequest.getRateOfReplenishment());

        itemDao.saveItem(newItem);

        ItemModel itemModel = new ModelConverter().toItemModel(newItem);
        return CreateItemResult.builder()
                .withItem(itemModel)
                .build();
    }
}
