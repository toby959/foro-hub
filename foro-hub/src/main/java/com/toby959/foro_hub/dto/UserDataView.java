package com.toby959.foro_hub.dto;

import com.toby959.foro_hub.models.User;

public record UserDataView(
        Long id,
        String name,
        String email
) {
    public UserDataView(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
