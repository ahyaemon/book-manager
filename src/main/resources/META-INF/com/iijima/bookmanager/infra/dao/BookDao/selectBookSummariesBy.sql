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
    /*%if authorId != null */
    AND author.id = /* authorId */0
    /*%end*/
INNER JOIN
  publisher
  ON
    publisher.id = book.publisher_id
    /*%if publisherId != null */
    AND publisher.id = /* publisherId */0
    /*%end*/
WHERE
  /*%if title != null */
  book.title LIKE /* title */''
  /*%end*/
ORDER BY
  book.id
