SELECT
  book.id,
  book.title,
  author.name AS author_name,
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
