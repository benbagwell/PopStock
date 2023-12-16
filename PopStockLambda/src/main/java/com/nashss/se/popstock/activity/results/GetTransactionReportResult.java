package com.nashss.se.popstock.activity.results;

import com.nashss.se.popstock.models.TransactionModel;

import java.util.List;

public class GetTransactionReportResult {

    private final List<TransactionModel> transactions;

    public GetTransactionReportResult(List<TransactionModel> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "GetTransactionReportResult{" +
                "transactions=" + transactions +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static class Builder {

        private List<TransactionModel> transactions;

        public Builder withTransactions(List<TransactionModel> transactions) {
            this.transactions = transactions;
            return this;
        }

        public GetTransactionReportResult build() { return new GetTransactionReportResult(transactions); }
    }
}


