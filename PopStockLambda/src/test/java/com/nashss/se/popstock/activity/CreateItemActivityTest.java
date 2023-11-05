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
    public void handleRequest_withNoNull_createsAndSavesItem() {
        String expectedName = "name";
        String expectedRegionOfOrigin = "regionOfOrigin";
        String expectedCategory = "category";
        int expectedRegionalDemand = 1;
        double expectedSalesForecast = 1.1;
        double expectedPerPallet = 1.1;
        double expectedPurchaseCost = 1.1;
        double expectedBaseMargin = 1.1;
        double expectedRateOfReplenishment = 1.1;
        String expectedSynergy = "synergy";
        boolean expectedActive = true;

        CreateItemRequest request = CreateItemRequest.builder()
                .withName(expectedName)
                .withCategory(expectedCategory)
                .withRegionOfOrigin(expectedRegionOfOrigin)
                .withRegionalDemand(expectedRegionalDemand)
                .withSalesForecast(expectedSalesForecast)
                .withPerPallet(expectedPerPallet)
                .withPurchaseCost(expectedPurchaseCost)
                .withBaseMargin(expectedBaseMargin)
                .withRateOfReplenishment(expectedRateOfReplenishment)
                .withSynergy(expectedSynergy)
                .build();

        CreateItemResult result = createItemActivity.handleRequest(request);

        verify(itemDao).saveItem(any(Item.class));

        assertNotNull(result.getItem().getItemId());
        assertEquals(expectedName,result.getItem().getName());
        assertEquals(expectedCategory,result.getItem().getCategory());
        assertEquals(expectedRegionOfOrigin,result.getItem().getRegionOfOrigin());
        assertEquals(expectedRegionalDemand,result.getItem().getRegionalDemand());
        assertEquals(expectedSalesForecast,result.getItem().getSalesForecast());
        assertEquals(expectedPerPallet,result.getItem().getPerPallet());
        assertEquals(expectedPurchaseCost,result.getItem().getPurchaseCost());
        assertEquals(expectedBaseMargin,result.getItem().getBaseMargin());
        assertEquals(expectedRateOfReplenishment,result.getItem().getRateOfReplenishment());
        assertEquals(expectedSynergy,result.getItem().getSynergy());
        assertEquals(expectedActive,result.getItem().isActive());
    }
}
