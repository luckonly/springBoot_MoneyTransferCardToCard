package ru.netology.service;

import ru.netology.dto.ConfirmationDTO;
import ru.netology.exception.ErrorConfirmation;
import ru.netology.dto.TransactionDTO;

public interface TransferService {
    public String transfer(TransactionDTO transactionDTO);
    public String confirmOperation(ConfirmationDTO confirmationDTO) throws ErrorConfirmation;
}
