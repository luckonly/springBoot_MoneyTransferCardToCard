package ru.netology.springboot_moneytransfercardtocard.exception;

public class ErrorTransaction  extends RuntimeException {
    public ErrorTransaction(String msg) {
        super(msg);
    }
}
