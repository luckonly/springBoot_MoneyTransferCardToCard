package ru.netology.repository;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ConfirmationRepositoryTest {

    public static ConfirmationRepository confirmationRepository = new ConfirmationRepository();
    public static String idOperation;
    public static String wrongIdOperation = "12345";

    @BeforeAll
    private static void init() {
        idOperation = String.valueOf(1);
        confirmationRepository.addConfirmation(idOperation);
    }


    @Test
    public void getConfirmationCodeByOperationId_successful() {
        String confirmationCode = confirmationRepository.getConfirmationCodeByOperationId(idOperation);
        Assert.assertNotNull(confirmationCode);
    }

    @Test
    public void tryTogetConfirmationCodeByWrongOperationId_null() {
        Assert.assertNull(confirmationRepository.getConfirmationCodeByOperationId(wrongIdOperation));
    }
}