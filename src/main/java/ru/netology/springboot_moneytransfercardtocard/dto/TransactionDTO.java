package ru.netology.springboot_moneytransfercardtocard.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TransactionDTO {
    @NotBlank
    @Size(min = 16, max = 16)
    private String cardFromNumber;
    @NotBlank
    @Size(min = 5, max = 5)
    private String cardFromValidTill;
    @NotBlank
    @Size(min = 3, max = 3)
    private String cardFromCVV;
    @NotBlank
    @Size(min = 16, max = 16)
    private String cardToNumber;
    private AmountDTO amountDTO;

    public TransactionDTO(String cardFromNumber, String cardFromValidTill, String cardFromCVV, String cardToNumber, AmountDTO amountDTO) {
        this.cardFromNumber = cardFromNumber;
        this.cardFromValidTill = cardFromValidTill;
        this.cardFromCVV = cardFromCVV;
        this.cardToNumber = cardToNumber;
        this.amountDTO = amountDTO;
    }

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public void setCardFromNumber(String cardFromNumber) {
        this.cardFromNumber = cardFromNumber;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public void setCardFromValidTill(String cardFromValidTill) {
        this.cardFromValidTill = cardFromValidTill;
    }

    public String getCardFromCVV() {
        return cardFromCVV;
    }

    public void setCardFromCVV(String cardFromCVV) {
        this.cardFromCVV = cardFromCVV;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public void setCardToNumber(String cardToNumber) {
        this.cardToNumber = cardToNumber;
    }

    public AmountDTO getAmount() {
        return amountDTO;
    }

    public void setAmount(AmountDTO amountDTO) {
        this.amountDTO = amountDTO;
    }

    public String getInfo() {
        return "Transaction: from c: " + cardFromNumber + ", to card: " + cardToNumber + ", amount: " + this.getAmount().getValue();
    }

}

