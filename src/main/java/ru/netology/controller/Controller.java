package ru.netology.controller;

import ru.netology.exception.ErrorConfirmation;
import ru.netology.dto.ConfirmationDTO;
import ru.netology.dto.TransactionDTO;

public interface Controller {
    public String transfer(TransactionDTO transactionDTO);
    public String confirmOperation(ConfirmationDTO confirmationDTO) throws ErrorConfirmation;
}
