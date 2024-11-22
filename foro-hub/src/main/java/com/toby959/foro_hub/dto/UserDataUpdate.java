package com.toby959.foro_hub.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataUpdate {
        @NotNull(message = "El id no puede estar vacio.")
        Long id;

        @NotBlank(message = "El nombre no puede estar vacio.")
        @Size(min = 3, max = 100, message = "El nombre, debe tener entre 3 y 100 caracteres.")
        String name;

        @NotBlank(message = "El email no puede estar vacio.")
        @Size(min = 7, max = 250, message = "El email, debe contener entre 7 y 250 caracteres.")
        @Email(message = "Email incorrecto!!!.")
        @Pattern(regexp = ".+@.+\\..+", message = "El correo electrónico debe contener un dominio válido.")
        String email;

        @NotBlank(message = "El password no puede estar vacio.")
        @Size(min = 7, max = 250, message = "El password, debe tener entre 7 y 250 caracteres.")
        String password;

}
