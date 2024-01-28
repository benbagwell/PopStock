package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.CreateTransactionRequest;
import com.nashss.se.popstock.activity.results.CreateTransactionResult;
import com.nashss.se.popstock.converters.ModelConverter;
import com.nashss.se.popstock.dynamodb.ItemDao;
import com.nashss.se.popstock.dynamodb.TransactionDao;
import com.nashss.se.popstock.dynamodb.models.Item;
import com.nashss.se.popstock.dynamodb.models.Transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import javax.inject.Inject;


public class CreateTransactionActivity {

    private final Logger log = LogManager.getLogger();
    private final ItemDao itemDao;
    private final TransactionDao transactionDao;

    @Inject
    public CreateTransactionActivity(ItemDao itemDao, TransactionDao transactionDao) {
        this.itemDao = itemDao;
        this.transactionDao = transactionDao;
    }

    public CreateTransactionResult handleRequest(final CreateTransactionRequest createTransactionRequest) {

        Transaction transaction = new Transaction();
        transaction.setWarehouseId(createTransactionRequest.getWarehouseId());
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setItemId(createTransactionRequest.getItemId());
        transaction.setCount(createTransactionRequest.getCount());
        transaction.setTransactionDate(transaction.getTransactionDate());
        transaction.setPartnerId(createTransactionRequest.getPartnerId());
        transaction.setTransactionType(createTransactionRequest.getTransactionType());

        updateItemCount(transaction);
        transactionDao.saveTransaction(transaction);

        return CreateTransactionResult.builder()
                .withTransaction(new ModelConverter().toTransactionModel(transaction))
                .build();
    }

    public void updateItemCount(Transaction transaction) {

        Item keyItem = new Item();
        keyItem.setWarehouseId(transaction.getWarehouseId());
        keyItem.setItemId(transaction.getItemId());

        Item item = itemDao.getItem(keyItem);

        if(transaction.getTransactionType().equals("outgoing")) {
            if (item.getCount() - transaction.getCount() < 0) {
                throw new RuntimeException("Item " + item.getItemId() + " has insufficient quantity for shipment");
            } else {
                item.setCount(item.getCount()-transaction.getCount());
                itemDao.saveItem(item);
            }
        } else {
            item.setCount(item.getCount()+transaction.getCount());
            itemDao.saveItem(item);
        }

    }

}
