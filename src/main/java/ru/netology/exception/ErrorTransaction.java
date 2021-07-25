package ru.netology.exception;

public class ErrorTransaction  extends RuntimeException {
    public ErrorTransaction(String msg) {
        super(msg);
    }
}
