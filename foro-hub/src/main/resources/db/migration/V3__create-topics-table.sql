CREATE TABLE topics (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    message VARCHAR(250) NOT NULL, -- Corregido 'menssage' a 'message'
    creation_date DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX ui_topics_title_message (title, message), -- Cambiado a plural
    CONSTRAINT fk_topics_user_id
        FOREIGN KEY (user_id)
        REFERENCES users (id) -- Cambiado a plural
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_topics_course_id
        FOREIGN KEY (course_id)
        REFERENCES courses (id) -- Cambiado a plural
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);