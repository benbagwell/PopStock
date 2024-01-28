package com.nashss.se.popstock.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.popstock.dynamodb.models.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class TransactionDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private PaginatedQueryList<Transaction> queryList;

    private TransactionDao transactionDao;

    @BeforeEach
    public void setup() {
        openMocks(this);
        transactionDao = new TransactionDao(dynamoDBMapper);
    }

    @Test
    public void saveTransaction_callsMapperWithTransaction() {
        Transaction transaction = new Transaction();

        Transaction result = transactionDao.saveTransaction(transaction);

        verify(dynamoDBMapper).save(transaction);
        assertEquals(transaction,result);
    }

    @Test
    public void getTransactions_callsMapperWithQuery() {
        DynamoDBQueryExpression<Transaction> queryExpression = new DynamoDBQueryExpression<>();
        when(dynamoDBMapper.query(Transaction.class, queryExpression)).thenReturn(queryList);

        List<Transaction> transactions = transactionDao.getTransactions("warehouseId");
        verify(dynamoDBMapper).query(any(), any());
    }
}
