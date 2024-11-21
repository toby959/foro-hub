CREATE TABLE topics (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    message VARCHAR(250) NOT NULL,
    creationdate TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    user_id BIGINT,
    course_id BIGINT,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);