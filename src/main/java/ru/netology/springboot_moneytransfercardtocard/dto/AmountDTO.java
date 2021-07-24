package ru.netology.springboot_moneytransfercardtocard.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Validated
public class AmountDTO {

    @NotEmpty
    @Min(1)
    private long value;
    @NotBlank
    private String currency;

    public AmountDTO(long value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "AmountDTO " + value +", currency " + currency;
    }
}
