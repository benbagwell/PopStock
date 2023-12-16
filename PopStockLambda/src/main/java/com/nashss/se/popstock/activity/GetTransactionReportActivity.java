package com.nashss.se.popstock.activity;

import com.nashss.se.popstock.activity.requests.GetTransactionReportRequest;
import com.nashss.se.popstock.activity.results.GetTransactionReportResult;
import com.nashss.se.popstock.dynamodb.TransactionDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetTransactionReportActivity {

    private final Logger log = LogManager.getLogger();

    private final TransactionDao transactionDao;

    @Inject

    public GetTransactionReportActivity(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public GetTransactionReportResult handleRequest(final GetTransactionReportRequest getTransactionReportRequest) {
        
    }
}
