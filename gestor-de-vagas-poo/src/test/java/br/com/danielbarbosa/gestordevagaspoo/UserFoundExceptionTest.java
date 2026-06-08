package br.com.danielbarbosa.gestordevagaspoo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.danielbarbosa.gestordevagaspoo.exceptions.UserFoundException;

public class UserFoundExceptionTest {

    @Test
    void deveRetornarMensagemCorreta() {

        UserFoundException exception = new UserFoundException();

        assertEquals(
                "Usuário já existe",
                exception.getMessage()
        );
    }
}