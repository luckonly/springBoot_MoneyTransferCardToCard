package ru.netology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.netology")
public class SpringBootMoneyTransferCardToCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMoneyTransferCardToCardApplication.class, args);
    }

}
