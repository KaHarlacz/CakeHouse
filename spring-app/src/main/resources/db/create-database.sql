DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id       BIGINT       NOT NULL,
    email    VARCHAR(50)  NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(20)  NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS ban;
CREATE TABLE ban
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    user_id  BIGINT       NOT NULL,
    ban_from DATETIME     NOT NULL,
    ban_to   DATETIME     NOT NULL,
    cause    VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE
);

DROP TABLE IF EXISTS ingredient;
CREATE TABLE ingredient
(
    id      BIGINT       NOT NULL AUTO_INCREMENT,
    name    VARCHAR(255) NULL DEFAULT NULL,
    unit    VARCHAR(50)  NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS recipe;
CREATE TABLE recipe
(
    id          BIGINT        NOT NULL AUTO_INCREMENT,
    author_id   BIGINT        NOT NULL,
    description VARCHAR(255)  NOT NULL,
    prep_method VARCHAR(2000) NOT NULL,
    image       LONGTEXT      NULL DEFAULT NULL,
    name        VARCHAR(255)  NOT NULL UNIQUE,
    category    VARCHAR(50)   NOT NULL,
    rating      INT           NULL DEFAULT NULL,
    date_added  DATE          NULL DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES user (id)
);

DROP TABLE IF EXISTS recipe_to_ingredient;
CREATE TABLE recipe_to_ingredient
(
    recipe_id     BIGINT NOT NULL,
    ingredient_id BIGINT NOT NULL,
    quantity      FLOAT  NOT NULL,
    PRIMARY KEY (ingredient_id, recipe_id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredient (id),
    FOREIGN KEY (recipe_id) REFERENCES recipe (id)
);

DROP TABLE IF EXISTS comment;
CREATE TABLE comment
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    user_id    BIGINT       NOT NULL,
    recipe_id  BIGINT       NOT NULL,
    date_added DATETIME     NOT NULL,
    content    VARCHAR(200) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (recipe_id) REFERENCES recipe (id)
);
