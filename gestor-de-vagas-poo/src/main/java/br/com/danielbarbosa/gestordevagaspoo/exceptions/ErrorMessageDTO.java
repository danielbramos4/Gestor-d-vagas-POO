package br.com.danielbarbosa.gestordevagaspoo.exceptions;

public class ErrorMessageDTO {

    private String message;
    private String field;

    public ErrorMessageDTO() {
    }

    public ErrorMessageDTO(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}