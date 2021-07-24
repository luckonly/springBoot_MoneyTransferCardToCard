package ru.netology.springboot_moneytransfercardtocard.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.netology.springboot_moneytransfercardtocard.dto.AmountDTO;
import ru.netology.springboot_moneytransfercardtocard.dto.ConfirmationDTO;
import ru.netology.springboot_moneytransfercardtocard.dto.TransactionDTO;
import ru.netology.springboot_moneytransfercardtocard.exception.ErrorConfirmation;
import ru.netology.springboot_moneytransfercardtocard.exception.ErrorTransaction;
import ru.netology.springboot_moneytransfercardtocard.model.Card;
import ru.netology.springboot_moneytransfercardtocard.repository.CardRepo;
import ru.netology.springboot_moneytransfercardtocard.repository.ConfirmRepo;
import ru.netology.springboot_moneytransfercardtocard.repository.TransactionRepo;

import java.util.concurrent.locks.ReentrantLock;

@Service
public class TransferServiceImpl implements TransferService {

    private static final TransactionRepo transactionRepo = new TransactionRepo();
    private static final ConfirmRepo confirmRepo = new ConfirmRepo();
    private static final CardRepo cardRepo = new CardRepo();

    private final ReentrantLock locker = new ReentrantLock(true);
    private final static Logger logger = Logger.getLogger(TransferServiceImpl.class);

    public void ServiceImpl() {
    }

    @Override
    public String transfer(TransactionDTO transactionDTO) {
        Card cardFrom = cardRepo.getCardFrom(transactionDTO);
        Card cardTo = cardRepo.getCardTo(transactionDTO);
        if (transactionIsValid(cardFrom, cardTo, transactionDTO.getAmount())) {
            String idOperation = transactionRepo.addTransaction(transactionDTO);
            logger.info(transactionDTO.getInfo());
            confirmRepo.addConfirmation(idOperation);
            return idOperation;
        }
        return "";
    }

    private void makeTransferFromCardToCard(Card cardFrom, Card cardTo, AmountDTO amount) {
        try {
            locker.lock();
            cardFrom.setBalance(cardFrom.getBalance() - amount.getValue());
            cardTo.setBalance(cardTo.getBalance() + amount.getValue());
        } finally {
            locker.unlock();
        }
    }

    private boolean transactionIsValid(Card cardFrom, Card cardTo, AmountDTO amount) {
        if (cardFrom.getNumber() == cardTo.getNumber()) throw new ErrorTransaction("Cannot make transaction between same cards");
        if (cardFrom.getBalance() < amount.getValue()) throw new ErrorTransaction("Not enough balance to close transaction");
        return true;
    }

    @Override
    public String confirmOperation(ConfirmationDTO confirmationDTO) throws ErrorConfirmation {
        String repoConfirmationCode = confirmRepo.getConfirmationCodeByOperationId(confirmationDTO.getOperationId());
        if (repoConfirmationCode != null && repoConfirmationCode.equals(confirmationDTO.getCode())) {
            TransactionDTO transactionDTO = transactionRepo.getTransactionById(confirmationDTO.getOperationId());

            makeTransferFromCardToCard( cardRepo.getCardByNumber(transactionDTO.getCardFromNumber()),
                                        cardRepo.getCardByNumber(transactionDTO.getCardToNumber()),
                                        transactionDTO.getAmount());
            return confirmationDTO.getOperationId();
        } else {
           throw new ErrorConfirmation("Confirmation code is not valid");
        }
    }

}
