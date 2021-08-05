package ru.netology.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.exception.ErrorInputData;
import ru.netology.model.Card;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CardRepositoryTest {

    public static CardRepository cardRepository = new CardRepository();
    public static String cardNumber = "1111222233334444";
    public static Card card = new Card(
            cardNumber,
            "12/23",
            "223",
            "RUB",
            1000L);

    @BeforeAll
    public static void init() {
        cardRepository.addCard(card);
    }

    @Test
    public void tryToAddCard_successful() {
        assertEquals(card, cardRepository.getCardByNumber(cardNumber));
    }

    @Test
    public void tryToAddSameCardTwice_exception() {
        assertThrows(ErrorInputData.class, () -> {
            cardRepository.addCard(card);
        });
    }

}