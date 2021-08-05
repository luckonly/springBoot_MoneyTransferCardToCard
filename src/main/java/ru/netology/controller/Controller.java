package ru.netology.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.dto.TransactionDTO;
import ru.netology.service.TransferService;
import ru.netology.service.TransferServiceImpl;
import ru.netology.dto.ConfirmationDTO;
import ru.netology.dto.ResponseDTO;
import ru.netology.exception.ErrorConfirmation;
import ru.netology.exception.ErrorInputData;
import ru.netology.exception.ErrorTransaction;

import javax.validation.Valid;


@RestController("/")
@Validated
public class Controller {

    private final TransferService transferService;

    public Controller(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("transfer")
    public String transfer(@RequestBody @Valid TransactionDTO transactionDTO) {
        return transferService.transfer(transactionDTO);
    }

    @PostMapping("confirmOperation")
    public String  confirmOperation(@RequestBody @Valid ConfirmationDTO confirmationDTO) {
        return transferService.confirmOperation(confirmationDTO);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ErrorInputData.class)
    public ResponseDTO ErrorInputData(ErrorInputData e) {
        return new ResponseDTO(e.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ErrorConfirmation.class)
    public ResponseDTO errorConfirmation(ErrorConfirmation e) {
        return new ResponseDTO(e.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ErrorTransaction.class)
    public ResponseDTO errorTransaction(ErrorTransaction e) {
        return new ResponseDTO(e.getLocalizedMessage());
    }

}