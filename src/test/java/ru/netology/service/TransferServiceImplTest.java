package ru.netology.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.dto.AmountDTO;
import ru.netology.dto.ConfirmationDTO;
import ru.netology.dto.TransactionDTO;
import ru.netology.exception.ErrorInputData;
import ru.netology.exception.ErrorTransaction;
import ru.netology.model.Card;
import ru.netology.repository.CardRepository;
import ru.netology.repository.ConfirmationRepository;
import ru.netology.repository.TransactionRepository;

import java.util.List;

class TransferServiceImplTest {

    public static TransactionRepository transactionRepository;
    public static ConfirmationRepository confirmationRepository;
    public static CardRepository cardRepository;
    public static TransferServiceImpl transferServiceImpl;
    public static String confirmationCode;
    public static String operationId;
    public static Card cardFrom;
    public static Card cardTo;


    @BeforeAll
    public static void init() {

        confirmationCode = "7777";
        operationId = "1";

        confirmationRepository = Mockito.mock(ConfirmationRepository.class);
        Mockito.when(confirmationRepository.getConfirmationCodeByOperationId(String.valueOf(1))).thenReturn(confirmationCode);
        transactionRepository =  new TransactionRepository();
        cardRepository = new CardRepository();

        transferServiceImpl = new TransferServiceImpl(
                transactionRepository,
                confirmationRepository,
                cardRepository);

        cardFrom = new Card(
                            "1111222233334444",
                            "02/22",
                "123",
                "RUB",
                1000L
        );

        cardTo = new Card(
                "5555222233334444",
                "04/24",
                "321",
                "RUB",
                1000L
        );

        cardRepository.addCardList(List.of(cardFrom, cardTo));

        TransactionDTO transactionDTO = new TransactionDTO()
                .setCardFromNumber(cardFrom.getNumber())
                .setCardToNumber(cardTo.getNumber())
                .setCardFromCVV(cardFrom.getCVV())
                .setCardFromValidTill(cardFrom.getValidTill().toString())
                .setAmount(new AmountDTO(1000L, "RUB"));
    }

    @Test
    void transferFromCardToCard_successful() {

        Long sumTransfer = 1000L;
        Long cardToBalanceBeforeTransfer = cardTo.getBalance();

        TransactionDTO transactionDTO = new TransactionDTO()
                .setCardFromNumber(cardFrom.getNumber())
                .setCardToNumber(cardTo.getNumber())
                .setCardFromCVV(cardFrom.getCVV())
                .setCardFromValidTill(cardFrom.getValidTill().toString())
                .setAmount(new AmountDTO(sumTransfer, "RUB"));

        ConfirmationDTO confirmationDTO = new ConfirmationDTO(operationId, confirmationCode);

        transferServiceImpl.transfer(transactionDTO);
        System.out.println(transferServiceImpl.confirmOperation(confirmationDTO));
        Assert.assertEquals(cardToBalanceBeforeTransfer + sumTransfer, cardTo.getBalance());

    }

    @Test
    void transfer_notEnoughMoney_exception() {
        TransactionDTO transactionDTO = new TransactionDTO()
                .setCardFromNumber("1111222233334444")
                .setCardToNumber("5555222233334444")
                .setCardFromCVV("123")
                .setCardFromValidTill("02/22")
                .setAmount(new AmountDTO(2000L, "RUB"));

        Assert.assertThrows(ErrorTransaction.class, () -> {
            transferServiceImpl.transfer(transactionDTO);
        });
    }

    @Test
    void transfer_incorrectCardFromNumber_exception() {
        String incorrectCardNumber = "INCORRECT_NUMBER";
        TransactionDTO transactionDTO = new TransactionDTO()
                .setCardFromNumber(incorrectCardNumber)
                .setCardToNumber("5555222233334444")
                .setCardFromCVV("123")
                .setCardFromValidTill("02/22")
                .setAmount(new AmountDTO(2000L, "RUB"));

        Assert.assertThrows(ErrorInputData.class, () -> {
            transferServiceImpl.transfer(transactionDTO);
        });
    }

    @Test
    void transfer_incorrectCardToNumber_exception() {
        String incorrectCardNumber = "INCORRECT_NUMBER";
        TransactionDTO transactionDTO = new TransactionDTO()
                .setCardFromNumber("1111222233334444")
                .setCardToNumber(incorrectCardNumber)
                .setCardFromCVV("123")
                .setCardFromValidTill("02/22")
                .setAmount(new AmountDTO(2000L, "RUB"));

        Assert.assertThrows(ErrorInputData.class, () -> {
            transferServiceImpl.transfer(transactionDTO);
        });
    }

    @Test
    void transfer_betweenSameCards_exception() {
        TransactionDTO transactionDTO = new TransactionDTO()
                .setCardFromNumber("1111222233334444")
                .setCardToNumber("1111222233334444")
                .setCardFromCVV("123")
                .setCardFromValidTill("02/22")
                .setAmount(new AmountDTO(2000L, "RUB"));

        Assert.assertThrows(ErrorTransaction.class, () -> {
            transferServiceImpl.transfer(transactionDTO);
        });
    }


}