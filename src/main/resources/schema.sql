drop table travel;
drop table city;

CREATE TABLE `travel` (
      `id` int(10) NOT NULL AUTO_INCREMENT,
      `member_id` varchar(255) NOT NULL,
      `city_id` int(10) NOT NULL,
      `start_date` date DEFAULT NULL,
      `end_date` date DEFAULT NULL,
      `register_date` datetime DEFAULT NULL,
      PRIMARY KEY (`id`)
);

CREATE TABLE `city` (
      `id` int(10) NOT NULL AUTO_INCREMENT,
      `name` varchar(255) NOT NULL,
      `address` varchar(255) NOT NULL,
      `comment` varchar(255) NOT NULL,
      `register_date` datetime DEFAULT NULL,
      PRIMARY KEY (`id`)
);