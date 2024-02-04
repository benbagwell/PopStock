package com.nashss.se.popstock.converters;

import com.nashss.se.popstock.dynamodb.models.Item;
import com.nashss.se.popstock.dynamodb.models.Transaction;
import com.nashss.se.popstock.dynamodb.models.Warehouse;
import com.nashss.se.popstock.models.ItemModel;
import com.nashss.se.popstock.models.TransactionModel;
import com.nashss.se.popstock.models.WarehouseModel;

public class ModelConverter {

    public ItemModel toItemModel(Item item) {
        return ItemModel.builder()
                .withWarehouseId(item.getWarehouseId())
                .withItemId(item.getItemId())
                .withCount(item.getCount())
                .withName(item.getName())

                .build();
    }

    public WarehouseModel toWarehouseModel(Warehouse warehouse) {
        return WarehouseModel.builder()
                .withWarehouseId(warehouse.getWarehouseId())
                .withName(warehouse.getName())
                .build();
    }

    public TransactionModel toTransactionModel(Transaction transaction) {
        return TransactionModel.builder()
                .withWarehouseId(transaction.getWarehouseId())
                .withTransactionId(transaction.getTransactionId())
                .withItemId(transaction.getItemId())
                .withCount(transaction.getCount())
                .withTransactionDate(transaction.getTransactionDate())
                .withPartnerId(transaction.getPartnerId())
                .withTransactionType(transaction.getTransactionType())
                .build();
    }
}
