package ru.netology.springboot_moneytransfercardtocard.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.netology.springboot_moneytransfercardtocard.dto.TransactionDTO;
import ru.netology.springboot_moneytransfercardtocard.model.Card;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CardRepo {

    private Map<String, Card> cardRepo = new ConcurrentHashMap<>();
    @Value("${test.enabled}")
    private int testMode;

    private void init() {
        Card card1 = new Card("1111222233334444",
                "11/22",
                "234",
                "RUB",
                0L);
        Card card2 = new Card("2222111133334444",
                "08/22",
                "324",
                "RUB",
                1000L);
        Card card3 = new Card("1111333322224444",
                "19/23",
                "432",
                "RUB",
                5000L);

        List<Card> cardList = Arrays.asList(new Card[]{card1, card2, card3});
        addCardList(cardList);
    }

    public CardRepo() {
       init();
    }

    public void addCardList(List<Card> cardList) {
        for (int i = 0; i < cardList.size(); i++) {
            addCard(cardList.get(i));
        }
    }

    public void addCard(Card card) {
        cardRepo.put(card.getNumber(), card);
    }

    public Card getCardByNumber(String number) {
        return cardRepo.get(number);
    }

    public Card getCardFrom(TransactionDTO transactionDTO) {

        if (cardRepo.containsKey(transactionDTO.getCardFromNumber())) {
            return cardRepo.get(transactionDTO.getCardFromNumber());
        } else {
            Card cardFrom = Card.getCardFromTransactionDTO(transactionDTO);
            cardRepo.put(cardFrom.getNumber(), cardFrom);
            return cardFrom;
        }
    }

    public Card getCardTo(TransactionDTO transactionDTO) {

        if (cardRepo.containsKey(transactionDTO.getCardToNumber())) {
            return cardRepo.get(transactionDTO.getCardToNumber());
        } else {
            Card cardTo = Card.getCardToFromTransactionDTO(transactionDTO);
            cardRepo.put(cardTo.getNumber(), cardTo);
            return cardTo;
        }
    }

}