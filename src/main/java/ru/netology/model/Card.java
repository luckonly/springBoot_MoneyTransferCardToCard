package ru.netology.model;

import ru.netology.dto.TransactionDTO;
import ru.netology.exception.ErrorInputData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Card {

    private String number;
    private Date validTill;
    private String CVV;
    private String currency;
    private long balance;

    private final static SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("MM/yy");

    private static Date getDate(String validTill) {
        try {
            return simpleDateFormatter.parse(validTill);
        } catch (ParseException exc) {
            throw new ErrorInputData("Error occurred with validation date. Please check your input data");
        }
    }

    public static Card getCardFromTransactionDTO(TransactionDTO transactionDTO) {
        return new Card(transactionDTO.getCardFromNumber(),
                transactionDTO.getCardFromValidTill(),
                transactionDTO.getCardFromCVV(),
                transactionDTO.getAmount().getCurrency());
    }

    public static Card getCardToFromTransactionDTO(TransactionDTO transactionDTO) {
        return new Card(transactionDTO.getCardToNumber());
    }

    public Card(String number, String validTill, String CVV) {
        this.number = number;
        this.validTill = getDate(validTill);
        this.CVV = CVV;
    }

    public Card(String number, String validTill, String CVV, String currency, long balance) {
        this.number = number;
        this.validTill = getDate(validTill);
        this.CVV = CVV;
        this.currency = currency;
        this.balance = balance;
    }

    public Card(String number, String validTill, String CVV, String currency) {
        this.number = number;
        this.validTill = getDate(validTill);
        this.CVV = CVV;
        this.currency = currency;
        this.balance = balance;
    }

    public Card(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getValidTill() {
        return this.validTill;
    }

    public void setValidTillString(String validTill) {
        this.validTill = getDate(validTill);
    }

    public void setValidTillDate(Date validTill) {
        this.validTill = validTill;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number.equals(card.number) && validTill.equals(card.validTill) && CVV.equals(card.CVV);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, validTill, CVV);
    }

}
