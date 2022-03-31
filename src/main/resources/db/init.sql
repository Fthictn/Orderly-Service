CREATE TABLE `answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `post_id` int DEFAULT NULL,
  `content` varchar(301) DEFAULT NULL,
  `created_time` varchar(75) DEFAULT NULL,
  `is_correct` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `post_title` varchar(300) DEFAULT NULL,
  `post_content` varchar(300) DEFAULT NULL,
  `created_time` varchar(300) DEFAULT NULL,
  `is_solved` varchar(300) DEFAULT NULL,
  `type` varchar(300) DEFAULT NULL,
  `project_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE `project` (
  `id` int NOT NULL AUTO_INCREMENT,
  `project_name` varchar(45) DEFAULT NULL,
  `project_code` varchar(45) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name_surname` varchar(70) NOT NULL,
  `user_title` varchar(90) DEFAULT NULL,
  `user_role` varchar(90) DEFAULT NULL,
  `user_password` varchar(15) NOT NULL,
  `user_email` varchar(45) NOT NULL,
  `is_banned` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `user_email_UNIQUE` (`user_email`)
);
insert into `user` (user_name_surname,user_title,user_role,user_password,user_email,is_banned) values ('fethicetin','Yazılım Mühendisi','Mühendisi','123','fthi.cetin@gmail.com','0');
