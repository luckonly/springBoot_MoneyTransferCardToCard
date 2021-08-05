package ru.netology.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
public class ConfirmationDTO {
    @NotBlank
    private String operationId;
    @NotBlank
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
