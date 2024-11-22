package com.toby959.foro_hub.error;

import com.toby959.foro_hub.models.Topic;
import org.springframework.validation.FieldError;

public class UserValidationException extends RuntimeException{

    private final FieldError fieldError;

    public UserValidationException(String field, String message) {
        super(message);
        this.fieldError = new FieldError(Topic.class.getName(), field, message);
    }
}
