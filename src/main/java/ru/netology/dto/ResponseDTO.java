package ru.netology.dto;

import org.springframework.boot.context.properties.bind.Name;

public class ResponseDTO {

    private static int id = 1;
    private String message;

    public ResponseDTO(String message) {
        this.id = getNewId();
        this.message = message;
    }

    public synchronized int getNewId() {
        return id++;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static int getId() {
        return id;
    }

}
