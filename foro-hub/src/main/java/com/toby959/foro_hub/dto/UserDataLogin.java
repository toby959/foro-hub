package com.toby959.foro_hub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDataLogin(
        @NotBlank(message = "El email, no puede ser vacio.")
        @Size(min = 7, max = 250, message = "El email, debe contener entre 7 y 250 caracteres.")
        String email,

        @NotBlank(message = "El password, nmo puede ser vacio.")
        @Size(min = 7, max = 250, message = "El password, debe tener entre 7 y 250 caracteres.")
        String password
) {
        public UserDataLogin(UserDataLogin loginData) {
                this(loginData.email(), loginData.password());
        }
}
