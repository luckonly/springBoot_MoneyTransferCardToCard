package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.dto.TransactionDTO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransactionRepo {

    private static int idCounter = 1;
    private Map<String, TransactionDTO> transactionRepo = new ConcurrentHashMap<>();

    public TransactionRepo() {
    }

    private synchronized static String getNewOperationId() {
        return String.valueOf(idCounter++);
    }

    public String addTransaction(TransactionDTO transactionDTO) {
        String operationId = getNewOperationId();
        transactionRepo.put(operationId, transactionDTO);
        return operationId;
    }

    public TransactionDTO getTransactionById(String operationId) {
        return transactionRepo.get(operationId);
    }

}
