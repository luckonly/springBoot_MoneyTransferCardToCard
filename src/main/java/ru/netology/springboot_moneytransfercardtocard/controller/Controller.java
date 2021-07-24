package ru.netology.springboot_moneytransfercardtocard.controller;

import ru.netology.springboot_moneytransfercardtocard.exception.ErrorConfirmation;
import ru.netology.springboot_moneytransfercardtocard.dto.ConfirmationDTO;
import ru.netology.springboot_moneytransfercardtocard.dto.TransactionDTO;

public interface Controller {
    public String transfer(TransactionDTO transactionDTO);
    public String confirmOperation(ConfirmationDTO confirmationDTO) throws ErrorConfirmation;
}
