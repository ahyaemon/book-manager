DELETE FROM author;

INSERT INTO author
  (id, name)
VALUES
  (1, 'testman1'),
  (2, 'testman2')
;

SELECT SETVAL ('author_id_seq', 1000);
