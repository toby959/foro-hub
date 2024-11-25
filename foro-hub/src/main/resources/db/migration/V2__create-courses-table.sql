CREATE TABLE courses (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX ui_courses_name (name) -- Cambiado a plural
);