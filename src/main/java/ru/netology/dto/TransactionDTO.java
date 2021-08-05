package ru.netology.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Validated
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
    @Valid
    private AmountDTO amountDTO;

    public TransactionDTO(String cardFromNumber,
                          String cardFromValidTill,
                          String cardFromCVV,
                          String cardToNumber,
                          AmountDTO amountDTO) {
        this.cardFromNumber = cardFromNumber;
        this.cardFromValidTill = cardFromValidTill;
        this.cardFromCVV = cardFromCVV;
        this.cardToNumber = cardToNumber;
        this.amountDTO = amountDTO;
    }

    public TransactionDTO() {
    }

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public TransactionDTO setCardFromNumber(String cardFromNumber) {
        this.cardFromNumber = cardFromNumber;
        return this;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public TransactionDTO setCardFromValidTill(String cardFromValidTill) {
        this.cardFromValidTill = cardFromValidTill;
        return this;
    }

    public String getCardFromCVV() {
        return cardFromCVV;
    }

    public TransactionDTO setCardFromCVV(String cardFromCVV) {
        this.cardFromCVV = cardFromCVV;
        return this;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public TransactionDTO setCardToNumber(String cardToNumber) {
        this.cardToNumber = cardToNumber;
        return this;
    }

    public long getAmountValue() {
        return amountDTO.getValue();
    }

    public TransactionDTO setAmount(AmountDTO amountDTO) {
        this.amountDTO = amountDTO;
        return this;
    }

    public String getInfo() {
        return "Transaction: from c: " + cardFromNumber + ", to card: " + cardToNumber + ", amount: " + this.getAmountValue();
    }

}

