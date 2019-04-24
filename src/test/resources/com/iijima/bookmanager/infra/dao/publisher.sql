DELETE FROM publisher;

INSERT INTO publisher
  (id, name)
VALUES
  (1, 'testpublisher1'),
  (2, 'testpublisher2')
;

SELECT SETVAL ('publisher_id_seq', 1000);
