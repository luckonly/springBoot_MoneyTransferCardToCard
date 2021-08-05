package ru.netology.repository;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.dto.AmountDTO;
import ru.netology.dto.TransactionDTO;

import java.util.concurrent.atomic.AtomicInteger;

public class TransactionRepositoryTest {

    public static TransactionRepository transactionRepository = new TransactionRepository();
    public static TransactionDTO transactionDTO;
    public static String wrongOperationId;

    @BeforeAll
    public static void init() {
        transactionDTO = new TransactionDTO()
                                    .setCardFromNumber("1111222233334444")
                                    .setCardToNumber("1111222233334444")
                                    .setCardFromCVV("123")
                                    .setCardFromValidTill("02/22")
                                    .setAmount(new AmountDTO(1000L, "RUB"));
        wrongOperationId = "WRONG_OPERATION_ID";
    }
    @BeforeEach
    public void clear() {
        transactionRepository = new TransactionRepository();
    }

    @Test
    public void addTransactionsAndCheckIdOperationGen_successful() {
        String operationId = transactionRepository.addTransaction(transactionDTO);
        Assert.assertTrue(new AtomicInteger(1).toString().equals(operationId));
        operationId = transactionRepository.addTransaction(transactionDTO);
        Assert.assertTrue(new AtomicInteger(2).toString().equals(operationId));
    }

    @Test
    public void getTransactionById_successful() {
        String operationId = transactionRepository.addTransaction(transactionDTO);
        Assert.assertTrue(transactionRepository.getTransactionById(operationId).equals(transactionDTO));
    }

    @Test
    public void getNullWhenTryToGetTransactionWithWrongId_true() {
        Assert.assertNull(transactionRepository.getTransactionById(wrongOperationId));
    }

}