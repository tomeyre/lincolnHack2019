DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
  user_id INT NOT NULL,
  type VARCHAR(250) NOT NULL,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250),
  school_year INT,
  name VARCHAR(250)
);
DROP TABLE IF EXISTS class_name;
CREATE TABLE IF NOT EXISTS class_name (
  class_name_id INT NOT NULL,
  class_name VARCHAR(250) NOT NULL
);
DROP TABLE IF EXISTS class_info;
CREATE TABLE IF NOT EXISTS class_info (
  class_id INT NOT NULL,
  class_name_id INT NOT NULL,
  user_id INT NOT NULL,
  result INT DEFAULT NULL,
  image_id VARCHAR(250),
  band VARCHAR(10),
  teacher_id INT,
  test_id INT,
  test_time VARCHAR(250)
);

INSERT INTO users (user_id, type, username, password, school_year, name) VALUES
(1,'Teacher', 'tom@me.com', 'password', NULL, 'Mr Eyre'),
(2,'Student', 'kacper@me.com', 'password', 9, 'Kacper Terebus'),
(3,'Student', 'calvin@me.com', 'password', 10, 'Calvin Scott'),
(4,'Student', 'chris@me.com', 'password', 11, 'Chris Gilet'),
(5,'Teacher', 'garrison@me.com', 'password', NULL, 'Mr Garrison');

INSERT INTO class_name (class_name_id, class_name) VALUES
(1, 'Math'),
(2, 'English'),
(3, 'Science'),
(4, 'History'),
(5, 'Geography'),
(6, 'RE');

INSERT INTO class_info (class_id, class_name_id, user_id, result, image_id, band, teacher_id, test_id, test_time) VALUES
(1,1,2,NULL,NULL,'upper',1, 1, NULL),
(2,1,3,NULL,NULL,'lower',5, 1, NULL),
(3,1,4,NULL,NULL,'upper',1, 1, NULL),
(4,2,2,NULL,NULL,'upper',1, 1, NULL),
(5,2,3,NULL,NULL,'lower',5, 1, NULL),
(6,2,4,NULL,NULL,'upper',1, 1, NULL),
(7,3,2,NULL,NULL,'upper',1, 1, NULL),
(8,3,3,NULL,NULL,'lower',5, 1, NULL),
(9,3,4,NULL,NULL,'upper',1, 1, NULL),
(10,4,2,NULL,NULL,'upper',1, 1, NULL),
(11,4,3,NULL,NULL,'lower',5, 1, NULL),
(12,4,4,NULL,NULL,'upper',1, 1, NULL),
(13,5,2,NULL,NULL,'upper',1, 1, NULL),
(14,5,3,NULL,NULL,'lower',5, 1, NULL),
(15,5,4,NULL,NULL,'upper',1, 1, NULL),
(16,6,2,NULL,NULL,'upper',1, 1, NULL),
(17,6,3,NULL,NULL,'lower',5, 1, NULL),
(18,6,4,NULL,NULL,'upper',1, 1, NULL),
(19,1,2,NULL,NULL,'upper',1, 2, NULL),
(20,1,3,NULL,NULL,'lower',5, 2, NULL),
(21,1,4,NULL,NULL,'upper',1, 2, NULL);