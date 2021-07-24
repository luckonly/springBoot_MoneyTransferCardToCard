package ru.netology.springboot_moneytransfercardtocard.exception;

public class ErrorConfirmation extends RuntimeException {
    public ErrorConfirmation(String message) {
        super(message);
    }
}
