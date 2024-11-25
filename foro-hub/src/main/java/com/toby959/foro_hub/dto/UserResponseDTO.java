package com.toby959.foro_hub.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserResponseDTO {
        private Long id;
        private String email;
        private String name;
        private List<TopicDataView> topics;

        public UserResponseDTO(Long id, String email, String name, List<TopicDataView> topics) {
            this.id = id;
            this.email = email;
            this.name = name;
            this.topics = topics;
        }

}
