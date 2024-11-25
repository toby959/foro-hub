CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX ui_users_name (name), -- Cambiado a plural
    UNIQUE INDEX ui_users_email (email) -- Cambiado a plural
);