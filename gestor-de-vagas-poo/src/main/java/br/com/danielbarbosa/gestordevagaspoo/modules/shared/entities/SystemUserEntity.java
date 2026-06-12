package br.com.danielbarbosa.gestordevagaspoo.modules.shared.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class SystemUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Pattern(
            regexp = "^(?!\\s*$).+",
            message = "O campo [username] nao deve estar vazio"
    )
    private String username;

    @Email(message = "O campo [email] deve conter um e-mail valido")
    private String email;

    @Length(
            min = 10,
            max = 100,
            message = "A senha deve conter entre 10 e 100 caracteres"
    )
    private String password;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
