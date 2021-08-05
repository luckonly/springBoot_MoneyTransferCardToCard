package ru.netology.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ConfirmationRepository {
    @Value("${enabled.test}")
    private String testMode;
    @Value("${verification.test.value}")
    private String verificationCode;
    private Map<String, String> confirmationMap = new ConcurrentHashMap<>();

    public void addConfirmation(String idOperation) {
        confirmationMap.put(idOperation, getNewCode());
    }

    private String getNewCode() {
        if (testMode == "0") {
            Random random = new Random();
            int code = random.nextInt(9999);
            if (code < 1000) { //Проверочный код должен быть из 4-х цифр
                code += 1000 + random.nextInt(1000);
            }
            return String.valueOf(code);
        } else {
            return String.valueOf(this.verificationCode);
        }
    }

    public String getConfirmationCodeByOperationId(String OperationId) {
        return confirmationMap.get(OperationId);
    }

}
