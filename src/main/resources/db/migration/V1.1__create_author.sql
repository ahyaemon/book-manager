CREATE TABLE author
(
    id SERIAL NOT NULL,
    name TEXT UNIQUE NOT NULL,
    PRIMARY KEY (id)
)
