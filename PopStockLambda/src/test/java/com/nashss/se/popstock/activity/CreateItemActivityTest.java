package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.CreateItemRequest;
import com.nashss.se.popstock.activity.results.CreateItemResult;
import com.nashss.se.popstock.dynamodb.ItemDao;
import com.nashss.se.popstock.dynamodb.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateItemActivityTest {

    @Mock
    private ItemDao itemDao;

    private CreateItemActivity createItemActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        createItemActivity = new CreateItemActivity(itemDao);
    }

    @Test
    public void handleRequest_createsAndSavesItem() {
        String expectedName = "name";


        CreateItemRequest request = CreateItemRequest.builder()
                .withName(expectedName)
                .build();

        CreateItemResult result = createItemActivity.handleRequest(request);

        verify(itemDao).saveItem(any(Item.class));

        assertNotNull(result.getItem().getItemId());
        assertEquals(expectedName,result.getItem().getName());

    }
}
