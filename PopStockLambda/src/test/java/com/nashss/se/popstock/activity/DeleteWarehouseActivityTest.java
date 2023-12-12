package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.DeleteWarehouseRequest;
import com.nashss.se.popstock.activity.results.DeleteWarehouseResult;
import com.nashss.se.popstock.converters.ModelConverter;
import com.nashss.se.popstock.dynamodb.ItemDao;
import com.nashss.se.popstock.dynamodb.TransactionDao;
import com.nashss.se.popstock.dynamodb.WarehouseDao;
import com.nashss.se.popstock.dynamodb.models.Item;
import com.nashss.se.popstock.dynamodb.models.Transaction;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import com.nashss.se.popstock.models.WarehouseModel;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class DeleteWarehouseActivityTest {

    @Mock
    private WarehouseDao warehouseDao;
    @Mock
    private ItemDao itemDao;
    @Mock
    private TransactionDao transactionDao;

    private DeleteWarehouseActivity deleteWarehouseActivity;

    @BeforeEach
    public void setup() {
        openMocks(this);
        deleteWarehouseActivity = new DeleteWarehouseActivity(warehouseDao,itemDao,transactionDao);

        String userid = "userId";
        String warehouseID = "warehouse";
        String itemID = "itemId";
        String transactionId = "transactionId";

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseId(warehouseID);
        warehouse.setUserId(userid);

        Item item = new Item();
        item.setItemId(itemID);
        item.setWarehouseId(warehouseID);

        Transaction transaction = new Transaction();
        transaction.setWarehouseId(warehouseID);
        transaction.setTransactionId(transactionId);

        List<Item> items = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();

        when(itemDao.getItems(warehouseID)).thenReturn(items);
        when(transactionDao.getTransactions(warehouseID)).thenReturn(transactions);

        DeleteWarehouseRequest request = DeleteWarehouseRequest.builder()
                .withWarehouseId(warehouseID)
                .withUserId(userid)
                .build();

        DeleteWarehouseResult result = deleteWarehouseActivity.handleRequest(request);
        WarehouseModel warehouseModel = new ModelConverter().toWarehouseModel(warehouse);

        assertEquals(warehouseModel,result.getWarehouse());
        verify(transactionDao).deleteTransaction(any(Transaction.class));
        verify(itemDao).deleteItem(any(Item.class));
        verify(warehouseDao).deleteWarehouse(any(Warehouse.class));
    }


}
