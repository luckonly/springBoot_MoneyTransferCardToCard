package ru.netology.springboot_moneytransfercardtocard.dto;

public class ConfirmationDTO {

    private String operationId;
    private String code;

    public ConfirmationDTO(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
