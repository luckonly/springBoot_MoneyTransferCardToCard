package ru.netology.springboot_moneytransfercardtocard.service;

import ru.netology.springboot_moneytransfercardtocard.dto.ConfirmationDTO;
import ru.netology.springboot_moneytransfercardtocard.exception.ErrorConfirmation;
import ru.netology.springboot_moneytransfercardtocard.dto.TransactionDTO;

public interface TransferService {
    public String transfer(TransactionDTO transactionDTO);
    public String confirmOperation(ConfirmationDTO confirmationDTO) throws ErrorConfirmation;
}
