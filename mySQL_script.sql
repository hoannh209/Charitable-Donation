drop database raise_money_for_charity;
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
    `created` varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `end_date` varchar(255) DEFAULT NULL,
    `money` int(11) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `organization_name` varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    `start_date` varchar(255) DEFAULT NULL,	
	`status` int(11) DEFAULT NULL,
    `status_text` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    
);

create table `user` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `address` varchar(255) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `full_name` varchar(255) DEFAULT NULL,
    `note` varchar(255) DEFAULT NULL,
    `password` varchar(128) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    `status` int(11) DEFAULT NULL,
    `user_name` varchar(255) DEFAULT NULL,
    `created` varchar(255) DEFAULT NULL,
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
insert into `role`(`id`, `role_name`) values (1,'Admin'),
											 (2,'User'),
                                             (3,'User'),	
                                             (4,'User'),	
                                             (5,'User'),	
                                             (6,'User');	

insert into `user`(`id`,`address`,`email`,`full_name`,`note`,`password`,`phone_number`,`status`,`user_name`,`created`,`role_id`) 
values (1, 'Thanh Hóa', 'an@gmail.com', 'Hồ Thanh An', null, '123456', '0982375920', 1, 'an123', null, 1),
	   (2, 'Nghệ An', 'binh@gmail.com', 'Nguyễn Phước Bình', null, '123456', '0982375209', 1, 'binh123', null, 2),
	   (3, 'Hà Tĩnh', 'cong@gmail.com', 'Võ Chí Công', null, '123456', '0982375029', 1, 'cong123', null, 3),
	   (4, 'Quảng Bình', 'duong@gmail.com', 'Trần Bình Dương', null, '123456', '0982375902', 1, 'duong123', null, 4),
	   (5, 'Quảng Trị', 'linh@gmail.com', 'Thái Thùy Linh', null, '123456', '0982375290', 1, 'linh123', null, 5),
	   (6, 'Huế', 'minh@gmail.com', 'Hồ Nhật Minh', null, '123456', '0982375092', 1, 'minh123', null, 6);

insert into `donation`(`id`,`code`,`created`,`description`,`end_date`,`money`,`name`,`organization_name`,`phone_number`,`start_date`,`status`, `status_text`) 
values (1, 'B535', null, null, '2023-05-05', 70000, 'Trái tim cho em', 'WHO', '0982375920','2023-05-05', 0, null),
	   (2, 'C209', null, null, '2023-05-05', 20000, 'Từ thiện lũ lụt', 'SpaceSpeakers', '0982375902','2023-05-05', 1, null),
	   (3, 'G204', null, null, '2023-05-05', 30000, 'Ủng hộ trẻ em nghèo', 'BTS', '0982375209','2023-05-05', 2, null),
	   (4, 'A103', null, null, '2023-05-05', 60000, 'Ủng hộ người già neo đơn', 'Mặt trận tổ quốc', '0982375290','2023-05-05', 3, null),
	   (5, 'H175', null, null, '2023-05-05', 150000, 'Ủng hộ phòng chống dịch', 'CV-TT', '0982375029','2023-05-05', 0, null);

insert into `user_donation`(`id`, `created`, `money`, `name`, `status`, `text`, `donation_id`, `user_id`)		
value (1, '2023-05-05', 20000, 'Hồ Thanh An', 1, 'Ủng hộ 20000', 2,1),
	(2, '2023-05-05', 30000, 'Nguyễn Phước Bình', 1, 'Ủng hộ 30000', 3,2),
    (3, '2023-05-05', 50000, 'Võ Chí Công', 1, 'Ủng hộ 50000', 5,6),
    (4, '2023-05-05', 60000, 'Trần Bình Dương', 1, 'Ủng hộ 60000', 4,3),
    (5, '2023-05-05', 70000, 'Thái Thùy Linh', 1, 'Ủng hộ 70000', 1,4),
    (6, '2023-05-05', 100000, 'Hồ Nhật Minh', 1, 'Ủng hộ 100000', 5,5);