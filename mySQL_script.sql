
create database raise_money_for_charity;
use raise_money_for_charity;

create table `role` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `role_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

create table `donation`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`code` varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `end_date` varchar(255) DEFAULT NULL,
    `money` int(11) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `organization_name` varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    `start_date` varchar(255) DEFAULT NULL,	
	`status` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
    
);

create table user (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `address` varchar(255) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `full_name` varchar(255) DEFAULT NULL,
    `password` varchar(128) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    `status` int(11) DEFAULT NULL,
    `user_name` varchar(255) DEFAULT NULL,
    `role_id` int (11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`role_id`) REFERENCES `role`(`id`)
);

create table `user_donation` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `created` varchar(255) DEFAULT NULL,
    `money` int(11) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `status` int(11) DEFAULT NULL,
    `text` varchar(255) DEFAULT NULL,
    `donation_id` int(11) DEFAULT NULL,
    `user_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`donation_id`) REFERENCES `donation`(`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
);

