CREATE USER 'transdb_user'@'localhost' IDENTIFIED BY 'spring';

GRANT ALL PRIVILEGES ON cs548_bookstore.* TO 'transdb_user'@'localhost' WITH GRANT OPTION;

SHOW GRANTS FOR 'transdb_user'@'localhost';