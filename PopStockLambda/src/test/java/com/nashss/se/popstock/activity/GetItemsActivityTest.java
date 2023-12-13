package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.GetItemsRequest;
import com.nashss.se.popstock.activity.results.GetItemsResult;
import com.nashss.se.popstock.dynamodb.ItemDao;
import com.nashss.se.popstock.dynamodb.models.Item;
import com.nashss.se.popstock.models.ItemModel;
import com.nashss.se.popstock.models.WarehouseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetItemsActivityTest {

    @Mock
    ItemDao itemDao;

    GetItemsActivity getItemsActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        getItemsActivity = new GetItemsActivity(itemDao);
    }

    @Test
    public void handleRequest_whenPassedWarehouseId_returnsWarehouseItems() {

        String warehouseId = "1234";
        String itemId = "abcd";
        String itemId1 = "efgh";
        String itemId2 = "ijkl";

        GetItemsRequest getItemsRequest = GetItemsRequest.builder()
                .withWarehouseId(warehouseId)
                .build();

        Item item = new Item();
        item.setWarehouseId(warehouseId);
        item.setItemId(itemId);

        Item item1 = new Item();
        item1.setWarehouseId(warehouseId);
        item1.setItemId(itemId1);

        Item item2 = new Item();
        item2.setWarehouseId(warehouseId);
        item2.setItemId(itemId2);

        List<Item> items = new ArrayList<>();
        items.add(item);
        items.add(item1);
        items.add(item2);

        ItemModel itemModel = ItemModel.builder()
                .withWarehouseId(warehouseId)
                .withItemId(itemId)
                .build();

        ItemModel itemModel1 = ItemModel.builder()
                .withWarehouseId(warehouseId)
                .withItemId(itemId1)
                .build();

        ItemModel itemModel2 = ItemModel.builder()
                .withWarehouseId(warehouseId)
                .withItemId(itemId2)
                .build();

        List<ItemModel> itemModels = new ArrayList<>();
        itemModels.add(itemModel);
        itemModels.add(itemModel1);
        itemModels.add(itemModel2);

        when(itemDao.getItems(warehouseId)).thenReturn(items);

        GetItemsResult getItemsResult = getItemsActivity.handleRequest(getItemsRequest);

        assertEquals(getItemsResult.getItems(), itemModels);
    }

}
