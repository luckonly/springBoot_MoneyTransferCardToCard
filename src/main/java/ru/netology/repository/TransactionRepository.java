package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.dto.TransactionDTO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TransactionRepository {

    private AtomicInteger idCounter = new AtomicInteger(1);
    private Map<String, TransactionDTO> transactionMap = new ConcurrentHashMap<>();

    private String getNewOperationId() {
        return String.valueOf(idCounter.getAndIncrement());
    }

    public String addTransaction(TransactionDTO transactionDTO) {
        String operationId = getNewOperationId();
        transactionMap.put(operationId, transactionDTO);
        return operationId;
    }

    public TransactionDTO getTransactionById(String operationId) {
        return transactionMap.get(operationId);
    }

}
