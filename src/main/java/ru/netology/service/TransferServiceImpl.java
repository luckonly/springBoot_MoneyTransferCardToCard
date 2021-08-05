package ru.netology.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.netology.dto.ConfirmationDTO;
import ru.netology.dto.TransactionDTO;
import ru.netology.exception.ErrorConfirmation;
import ru.netology.exception.ErrorTransaction;
import ru.netology.model.Card;
import ru.netology.repository.CardRepository;
import ru.netology.repository.ConfirmationRepository;
import ru.netology.repository.TransactionRepository;

import java.util.concurrent.locks.ReentrantLock;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransactionRepository transactionRepository;
    private final ConfirmationRepository confirmRepository;
    private final CardRepository cardRepository;

    private final ReentrantLock locker = new ReentrantLock(true);
    private final static Logger logger = Logger.getLogger(TransferServiceImpl.class);

    public TransferServiceImpl(
            TransactionRepository transactionRepository,
            ConfirmationRepository confirmRepository,
            CardRepository cardRepository) {
        this.transactionRepository = transactionRepository;
        this.confirmRepository = confirmRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public String transfer(TransactionDTO transactionDTO) {
        Card cardFrom = cardRepository.getCardByNumber(transactionDTO.getCardFromNumber());
        Card cardTo = cardRepository.getCardByNumber(transactionDTO.getCardToNumber());

        if (transactionIsValid(cardFrom, cardTo, transactionDTO.getAmountValue())) {
            String idOperation = transactionRepository.addTransaction(transactionDTO);
            confirmRepository.addConfirmation(idOperation);
            return idOperation;
        }
        return null;
    }

    private void makeTransferFromCardToCard(
            Card cardFrom,
            Card cardTo,
            long amountValue) {
        try {
            locker.lock();
            if (transactionIsValid(cardFrom, cardTo, amountValue)) {
                cardFrom.setBalance(cardFrom.getBalance() - amountValue);
                cardTo.setBalance(cardTo.getBalance() + amountValue);
            }
        } finally {
            locker.unlock();
        }
    }

    private boolean transactionIsValid(
            Card cardFrom,
            Card cardTo,
            long amountValue) {
        if (cardFrom.getNumber() == cardTo.getNumber()) throw new ErrorTransaction("Cannot make transaction between same cards");
        if (cardFrom.getBalance() < amountValue) throw new ErrorTransaction("Not enough balance to close transaction");
        return true;
    }

    @Override
    public String confirmOperation(@RequestBody ConfirmationDTO confirmationDTO) throws ErrorConfirmation {

        String dtoOperationId = confirmationDTO.getOperationId();
        String dtoCode = confirmationDTO.getCode();
        String repoCode = confirmRepository.getConfirmationCodeByOperationId(dtoOperationId);

        if (repoCode != null && repoCode.equals(dtoCode)) {
            TransactionDTO transactionDTO = transactionRepository.getTransactionById(dtoOperationId);

            makeTransferFromCardToCard( cardRepository.getCardByNumber(transactionDTO.getCardFromNumber()),
                                        cardRepository.getCardByNumber(transactionDTO.getCardToNumber()),
                                        transactionDTO.getAmountValue());
            logger.info(transactionDTO.getInfo());
            return confirmationDTO.getOperationId();
        } else {
           throw new ErrorConfirmation("Confirmation code is not valid");
        }
    }

}
