package ru.netology.springboot_moneytransfercardtocard.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ConfirmRepo {

    private Map<String, String> confirmationRepo = new ConcurrentHashMap<>();

    @Value("${test.enabled}")
    private int testMode;
    @Value("${test.verification.code}")
    private int verificationCode;

    public ConfirmRepo() {
    }

    public void addConfirmation(String idOperation) {
        confirmationRepo.put(idOperation, getNewCode());
    }

    private synchronized String getNewCode() {
        if (testMode == 0) {
            Random random = new Random();
            int code = random.nextInt(9999);
            if (code < 1000) { //Проверочный код должен быть из 4-х цифр
                code += 1000 + random.nextInt(1000);
            }
            return String.valueOf(code);
        } else {
            return String.valueOf(verificationCode);
        }
    }

    public String getConfirmationCodeByOperationId(String OperationId) {
        return confirmationRepo.get(OperationId);
    }

}
