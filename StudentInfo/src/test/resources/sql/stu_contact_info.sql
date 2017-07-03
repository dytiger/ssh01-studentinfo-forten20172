DROP TABLE IF EXISTS stu_contact_info;

CREATE TABLE stu_contact_info
(
	id int(10) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	name varchar(30) NOT NULL,
	password varchar(40) NOT NULL,
	gender char(1) NOT NULL DEFAULT 'U',
	birthday date,
	id_card_num varchar(30) NOT NULL,
	tel varchar(30) NOT NULL,
	email varchar(50),
	edu_bg varchar(30) NOT NULL,
	address varchar(100) NOT NULL,
	status char(2) NOT NULL DEFAULT 'BM',
	regist_time datetime
);

INSERT INTO stu_contact_info VALUES
(
	1,'张小军','7c4a8d09ca3762af61e59520943dc26494f8941b','M','1990-05-09','148323199005092353','13868392993','zxp@163.com','本科','北京市朝阳区朝阳公园东路12号','BM','2017-06-01 10:33:25'
),(
	2,'刘晨','7c4a8d09ca3762af61e59520943dc26494f8941b','M','1989-01-13','110105198901133218','18839299382','lc113@126.com','本科','北京市东城区东直门内大街202号','BM','2017-06-11 15:07:11'
),(
	3,'凌岚','7c4a8d09ca3762af61e59520943dc26494f8941b','F','1991-10-23','288238199119236328','13899283921','lingl@sohu.com','硕士','北京市海淀区蓟门北里5号楼1101房间','BM','2017-06-22 20:22:15'
),(
	4,'杨梅','7c4a8d09ca3762af61e59520943dc26494f8941b','F','1985-01-19','111382999333','15538288321','yangtiger@126.com','本科','北京市西城区德胜门内大街35号','BM','2017-06-25 07:03:22'
),(
	5,'李平','7c4a8d09ca3762af61e59520943dc26494f8941b','M','1998-11-20','683221199811202312','18011838223','liping_sun@gmail.com','高中','北京市西城区地安门外大街115号','BM','2017-07-02 12:00:19'
);