package com.toby959.foro_hub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TopicDataCreate(

        @NotBlank
        @Size(max = 100, message = "El titulo debe tener entre 1 y 100 caracteres.")
        String title,

        @NotBlank
        @Size(max = 250, message = "El mensaje debe terner entre 1 y 250 caracteres.")
        String message,

        @NotNull
        Long author,

        @NotNull
        Long course
) {
}
