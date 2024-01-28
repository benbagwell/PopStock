package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.GetTransactionReportRequest;
import com.nashss.se.popstock.activity.results.GetTransactionReportResult;
import com.nashss.se.popstock.converters.ModelConverter;
import com.nashss.se.popstock.dynamodb.TransactionDao;
import com.nashss.se.popstock.dynamodb.models.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class GetTransactionReportActivity {

    private final Logger log = LogManager.getLogger();

    private final TransactionDao transactionDao;

    @Inject

    public GetTransactionReportActivity(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public GetTransactionReportResult handleRequest(final GetTransactionReportRequest getTransactionReportRequest) {

        List<Transaction> transactions = transactionDao.getTransactionsBetweenDates(getTransactionReportRequest.getWarehouseId(),getTransactionReportRequest.getStartDate(),getTransactionReportRequest.getEndDate());

        return GetTransactionReportResult.builder()
                .withTransactions(transactions.stream()
                        .map(transaction -> new ModelConverter().toTransactionModel(transaction))
                        .collect(Collectors.toList()))
                .build();
        
    }
}
