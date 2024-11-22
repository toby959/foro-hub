package com.toby959.foro_hub.error;

import com.toby959.foro_hub.models.Topic;
import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
public class TopicValidationException extends RuntimeException {

    private final FieldError fieldError;

    public TopicValidationException(String field, String message) {
        super(message);
        this.fieldError = new FieldError(Topic.class.getName(), field, message);
    }
}
