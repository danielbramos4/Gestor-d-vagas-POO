package br.com.danielbarbosa.gestordevagaspoo.exceptions;

public class BusinessValidationException extends RuntimeException {

    public BusinessValidationException(String message) {
        super(message);
    }
}
