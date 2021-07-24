package ru.netology.springboot_moneytransfercardtocard.exception;

public class ErrorInputData extends RuntimeException {
    public ErrorInputData(String message) {
        super(message);
    }
}
