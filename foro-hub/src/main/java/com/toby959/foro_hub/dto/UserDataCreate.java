package com.toby959.foro_hub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDataCreate(

        @NotBlank(message = "El nombre, es obligatorio.")
        @Size(min = 3, max = 100, message = "El nombre, debe tener entre 3 y 100 caracteres.")
        String name,

        @NotBlank(message = "El email, no puede estar vacio.")
        @Size(min = 7, max = 250, message = "El email, debe tener entre 7 y 250 caracteres.")
        @Email(message = "Email incorrecto!!!.")
        @Pattern(regexp = ".+@.+\\..+", message = "El correo electrónico debe contener un dominio válido.")
        String email,

        @NotBlank(message = "El password, no debe estar vacio.")
        @Size(min = 7, max = 250, message = "El password, debe tener entre 7 y 250 caracteres.")
        String password
){
}
