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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;

public class DeleteWarehouseActivity {

    private final Logger log = LogManager.getLogger();

    private final WarehouseDao warehouseDao;
    private final ItemDao itemDao;
    private final TransactionDao transactionDao;

    @Inject
    public DeleteWarehouseActivity(WarehouseDao warehouseDao, ItemDao itemDao, TransactionDao transactionDao) {
        this.warehouseDao = warehouseDao;
        this.itemDao = itemDao;
        this.transactionDao = transactionDao;
    }

    public DeleteWarehouseResult handleRequest(final DeleteWarehouseRequest deleteWarehouseRequest) {

        Warehouse warehouse = warehouseDao.getWarehouse(deleteWarehouseRequest.getUserId(),
                deleteWarehouseRequest.getUserId());
        List<Item> items = itemDao.getItems(warehouse.getWarehouseId());
        List<Transaction> transactions = transactionDao.getTransactions(warehouse.getWarehouseId());

        transactions.forEach(transactionDao::deleteTransaction);
        items.forEach(itemDao::deleteItem);
        warehouseDao.deleteWarehouse(warehouse);

        return DeleteWarehouseResult.builder()
                .withWarehouse(new ModelConverter().toWarehouseModel(warehouse))
                .build();
    }


}
