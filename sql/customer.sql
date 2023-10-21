DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `cid` int(20),
  `cname` varchar(50),
	`phoneNumber` varchar(11),
	`weChat` varchar(50),
	`note` varchar(255),
  PRIMARY KEY (`cid`),
  KEY `id` (`cid`,`cname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

select * from customer
insert into customer(cname) values('aaa')

select * from customer where cid = '180904' AND cname = 'aaa'
select * from customer where cid = '180904' AND cname = 'aaa' ;