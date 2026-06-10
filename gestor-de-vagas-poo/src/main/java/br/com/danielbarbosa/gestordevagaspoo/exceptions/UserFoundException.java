package br.com.danielbarbosa.gestordevagaspoo.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException() {
        super("Usuário já existe");
    }
}
