package ru.netology.springboot_moneytransfercardtocard.service;

import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;
import ru.netology.springboot_moneytransfercardtocard.dto.AmountDTO;
import ru.netology.springboot_moneytransfercardtocard.dto.TransactionDTO;
import ru.netology.springboot_moneytransfercardtocard.model.Card;
import ru.netology.springboot_moneytransfercardtocard.repository.CardRepo;
import ru.netology.springboot_moneytransfercardtocard.repository.ConfirmRepo;
import ru.netology.springboot_moneytransfercardtocard.repository.TransactionRepo;


public class TransferServiceTest {

//    private static final TransactionRepo transactionRepo = new TransactionRepo();
//    private static final ConfirmRepo confirmRepo = new ConfirmRepo();
//    private static final CardRepo cardRepo;
//
//    @BeforeAll
//    public static void init() {
//        Card cardFrom = new Card("1111222233334444",
//                "11/22", "234",
//                "RUB",
//                10000L);
//        Card cardTo = new Card("2222111133334444",
//                "08/22",
//                "324",
//                "RUB",
//                1000L);
//        TransactionDTO transactionDTO = new TransactionDTO("notNullCardNumber",
//                "12/21",
//                "333",
//                "notNullCardNumber",
//                new AmountDTO(1000L, "RUB"));
//        CardRepo cardRepo = Mockito.mock(CardRepo.class);
//        Mockito.when(cardRepo.getCardFrom(transactionDTO)).thenReturn(cardFrom);
//        Mockito.when(cardRepo.getCardTo(transactionDTO)).thenReturn(cardTo);
//
//    }

}
