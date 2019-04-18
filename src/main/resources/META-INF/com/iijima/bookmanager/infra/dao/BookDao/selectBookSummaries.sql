SELECT
  book.id,
  book.title,
  author.id AS author_id,
  author.name AS author_name,
  publisher.id AS publisher_id,
  publisher.name AS publisher_name
FROM
  book
INNER JOIN
  author
  ON
    author.id = book.author_id
INNER JOIN
  publisher
  ON
    publisher.id = book.publisher_id
