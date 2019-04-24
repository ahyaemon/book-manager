DELETE FROM book;
DELETE FROM publisher;
DELETE FROM author;

INSERT INTO author
  (id, name)
VALUES
  (1, 'testman1'),
  (2, 'testman2')
;

INSERT INTO publisher
  (id, name)
VALUES
  (1, 'testpublisher1'),
  (2, 'testpublisher2')
;

INSERT INTO book
  (id, title, author_id, publisher_id)
VALUES
  (1, 'testbook1', 1, 1),
  (2, 'testbook2', 2, 2)
;

SELECT SETVAL ('author_id_seq', 1000);
SELECT SETVAL ('publisher_id_seq', 1000);
SELECT SETVAL ('book_id_seq', 1000);
