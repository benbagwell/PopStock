package com.nashss.se.popstock.activity.results;

import com.nashss.se.popstock.activity.requests.CreateTransactionRequest;
import com.nashss.se.popstock.dynamodb.models.Transaction;
import com.nashss.se.popstock.models.TransactionModel;

import java.util.List;

public class CreateTransactionResult {

    private final List<TransactionModel> transactions;

    public CreateTransactionResult(List<TransactionModel> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionModel> getTransactions() {
        return transactions;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<TransactionModel> transactions;

        public Builder withTransactions(List<TransactionModel> transactions) {
            this.transactions = transactions;
            return this;
        }

        public CreateTransactionResult build() { return new CreateTransactionResult(transactions); }
    }
}
