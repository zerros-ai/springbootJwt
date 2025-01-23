CREATE TABLE Parent(
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL
);

CREATE TABLE Child (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       parent_id BIGINT,
                       CONSTRAINT fk_child_parent FOREIGN KEY (parent_id) REFERENCES Parent (id) ON DELETE CASCADE
);