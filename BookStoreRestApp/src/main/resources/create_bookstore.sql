delimiter ;
DROP SCHEMA IF EXISTS `cs548_bookstore`; 
CREATE SCHEMA `cs548_bookstore` ;
use `cs548_bookstore`;

delimiter $$

CREATE TABLE `publishers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `publisherName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `publisherId` int(11) NOT NULL,
  `isbn` varchar(45) NOT NULL,
  `bookTitle` varchar(255) NOT NULL,
  `price` float unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`publisherId`) references `publishers` (`id`),
  UNIQUE KEY `isbn_UNIQUE` (`isbn`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(45) UNIQUE NOT NULL,
  `instock` int unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(45) NOT NULL,
  `quantity` int unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1$$

delimiter ;
INSERT INTO publishers VALUES
(1, 'Apress'),
(2, 'Microsoft Press'),
(3, 'Sams Publishing');


INSERT INTO books VALUES
(1, 1, '1010101010', 'Programming in Java', '69.00'),
(2, 2, '1010101011', 'Programming in C#', '59.00'),
(3, 3, '1010101102', 'Web Progamming with PHP', '25.17'),
(5, 1, '1010101103', 'Cisco Networking', '79.99'),
(6, 2, '1010101104', 'Configuring Home Network', '49.99'),
(7, 3, '1010101105', 'MySQL Database', '29.99'),
(8, 1, '1010101106', 'MS SQL Server', '59.99');

INSERT INTO inventory VALUES
(1,'1010101010', 4),
(2,'1010101011', 5),
(3,'1010101103', 6);