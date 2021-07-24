package ru.netology.springboot_moneytransfercardtocard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.netology.springboot_moneytransfercardtocard.dto.ConfirmationDTO;
import ru.netology.springboot_moneytransfercardtocard.dto.ResponseDTO;
import ru.netology.springboot_moneytransfercardtocard.exception.ErrorConfirmation;
import ru.netology.springboot_moneytransfercardtocard.dto.TransactionDTO;
import ru.netology.springboot_moneytransfercardtocard.exception.ErrorInputData;
import ru.netology.springboot_moneytransfercardtocard.exception.ErrorTransaction;
import ru.netology.springboot_moneytransfercardtocard.service.TransferService;
import ru.netology.springboot_moneytransfercardtocard.service.TransferServiceImpl;

import javax.validation.Valid;

@RestController("/")
public class ControllerImpl implements Controller {

    private final TransferService service = new TransferServiceImpl();

    @Override
    @PostMapping("transfer")
    public String transfer(@RequestBody @Valid TransactionDTO transactionDTO) {
        return service.transfer(transactionDTO);
    }

    @Override
    @PostMapping("confirmOperation")
    public String  confirmOperation(@RequestBody @Valid ConfirmationDTO confirmationDTO) throws ErrorConfirmation {
        return service.confirmOperation(confirmationDTO);
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