package com.toby959.foro_hub.dto;

import com.toby959.foro_hub.models.Status;
import com.toby959.foro_hub.models.Topic;

import java.time.LocalDateTime;

public record TopicDataView(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        Status status,
        Long author,
        Long course
) {

    public TopicDataView(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus(),
                topic.getAuthor().getId(),
                topic.getCourse().getId()
        );
    }
}
