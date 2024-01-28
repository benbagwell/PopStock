package com.nashss.se.popstock.activity.results;

import com.nashss.se.popstock.models.TransactionModel;

public class CreateTransactionResult {

    private final TransactionModel transaction;

    public CreateTransactionResult(TransactionModel transaction) {
        this.transaction = transaction;
    }

    public TransactionModel getTransaction() {
        return transaction;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private TransactionModel transaction;

        public Builder withTransaction(TransactionModel transaction) {
            this.transaction = transaction;
            return this;
        }

        public CreateTransactionResult build() { return new CreateTransactionResult(transaction); }
    }
}
