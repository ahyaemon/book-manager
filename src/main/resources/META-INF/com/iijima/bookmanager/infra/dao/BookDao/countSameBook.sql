SELECT
  COUNT(1)
FROM
  book
INNER JOIN
  author
  ON
    author.id = book.author_id
    /*%if book.author.id != null */
      AND author.id = /* book.author.id */0
    /*%end*/
    /*%if !book.author.name.isEmpty() */
      AND author.name = /* book.author.name */''
    /*%end*/
INNER JOIN
  publisher
  ON
    publisher.id = book.publisher_id
    /*%if book.publisher.id != null */
      AND publisher.id = /* book.publisher.id */0
    /*%end*/
    /*%if !book.publisher.name.isEmpty() */
      AND publisher.name = /* book.publisher.name */''
    /*%end*/
WHERE
  book.title = /* book.title */''
  /*%if book.id != null */
    AND book.id <> /* book.id */0
  /*%end*/

