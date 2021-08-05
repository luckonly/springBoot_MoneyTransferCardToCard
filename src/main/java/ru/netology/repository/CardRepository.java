package ru.netology.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.netology.exception.ErrorInputData;
import ru.netology.model.Card;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CardRepository {

    private Map<String, Card> cardMap = new ConcurrentHashMap<>();
    @Value("${enabled.test}")
    private int testMode;

    private void init() {
        Card card1 = new Card(
                "1111222233334444",
                "11/22",
                "234",
                "RUB",
                0L);
        Card card2 = new Card(
                "2222111133334444",
                "08/22",
                "324",
                "RUB",
                1000L);
        Card card3 = new Card(
                "1111333322224444",
                "19/23",
                "432",
                "RUB",
                5000L);

        List<Card> cardList = List.of(card1, card2, card3);
        addCardList(cardList);
    }

    public CardRepository() {
       if (testMode == 1) init();
    }

    public void addCardList(List<Card> cardList) {
        for (int i = 0; i < cardList.size(); i++) {
            addCard(cardList.get(i));
        }
    }

    public void addCard(Card card) {
        if (cardMap.containsKey(card.getNumber())) {
            throw new ErrorInputData("This card have been already added");
        }
        cardMap.put(card.getNumber(), card);
    }

    public Card getCardByNumber(String number) {
        if (!cardMap.containsKey(number)) {
            throw new ErrorInputData("Card number is not valid");
        }
        return cardMap.get(number);
    }

}
