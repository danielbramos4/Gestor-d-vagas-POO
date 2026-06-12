package br.com.danielbarbosa.gestordevagaspoo.modules.shared.validators;

import br.com.danielbarbosa.gestordevagaspoo.exceptions.BusinessValidationException;
import br.com.danielbarbosa.gestordevagaspoo.modules.shared.entities.SystemUserEntity;

public final class SystemUserValidator {

    private SystemUserValidator() {
    }

    public static void validate(SystemUserEntity user) {
        if (isBlank(user.getUsername())) {
            throw new BusinessValidationException("Informe o usuario.");
        }

        if (isBlank(user.getName())) {
            throw new BusinessValidationException("Informe o nome.");
        }

        if (isBlank(user.getEmail()) || !user.getEmail().contains("@")) {
            throw new BusinessValidationException("Informe um e-mail valido.");
        }

        if (user.getPassword() == null || user.getPassword().length() < 10 || user.getPassword().length() > 100) {
            throw new BusinessValidationException("A senha deve conter entre 10 e 100 caracteres.");
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
