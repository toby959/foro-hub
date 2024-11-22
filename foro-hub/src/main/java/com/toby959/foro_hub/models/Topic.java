package com.toby959.foro_hub.models;

import com.toby959.foro_hub.dto.TopicDataUpdate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 250)
    private String message;

    @Column(nullable = false, name = "creation_date")
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;



    public Topic(String title, String message, User author, Course course) {
        this.title = title;
        this.message = message;
        this.creationDate = LocalDateTime.now();
        this.status = Status.ACTIVE;
        this.author = author;
        this.course = course;
    }

    public void update(TopicDataUpdate data) {
        this.title = data.title();
        this.message = data.message();
        this.status = data.status();
    }
}
