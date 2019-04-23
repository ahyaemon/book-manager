CREATE TABLE book (
  id SERIAL NOT NULL,
  title TEXT NOT NULL,
  author_id INTEGER NOT NULL REFERENCES author(id),
  publisher_id INTEGER NOT NULL REFERENCES publisher(id),
  PRIMARY KEY (id)
)
