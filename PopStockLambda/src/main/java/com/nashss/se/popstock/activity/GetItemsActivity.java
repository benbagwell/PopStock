package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.GetItemsRequest;
import com.nashss.se.popstock.activity.results.GetItemsResult;
import com.nashss.se.popstock.converters.ModelConverter;
import com.nashss.se.popstock.dynamodb.ItemDao;
import com.nashss.se.popstock.dynamodb.models.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class GetItemsActivity {

    private final Logger log = LogManager.getLogger();

    private final ItemDao itemDao;

    @Inject
    public GetItemsActivity(ItemDao itemDao) { this.itemDao = itemDao; }

    public GetItemsResult handleRequest(final GetItemsRequest getItemsRequest) {

        List<Item> items = itemDao.getItems(getItemsRequest.getWarehouseId());
        ModelConverter modelConverter = new ModelConverter();

        return GetItemsResult.builder()
                .withItems(items.stream()
                        .map(modelConverter::toItemModel)
                        .collect(Collectors.toList()))
                .build();
    }

}
