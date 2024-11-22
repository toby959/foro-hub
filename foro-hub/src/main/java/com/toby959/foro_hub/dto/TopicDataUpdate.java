package com.toby959.foro_hub.dto;

import com.toby959.foro_hub.models.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TopicDataUpdate(

        @NotNull(message = "El tópico no puede estar vacío")
        Long id,

        @NotBlank(message = "El título no puede estar vacío")
        @Size(max = 100, message = "El titulo debe tener entre 1 y 100 caracteres.")
        String title,

        @NotBlank(message = "El mensaje no puede estar vacío")
        @Size(max = 250, message = "El mensaje debe terner entre 1 y 250 caracteres.")
        String message,

        @NotNull(message = "El estado no puede estar vacío")
        Status status

) {
}
