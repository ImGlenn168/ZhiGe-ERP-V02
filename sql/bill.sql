DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `bid` int(20) not null auto_increment,
	`cname` varchar(50),
  `orderTime` DATE,
	`quantity` INT(5),
	`unitPrice` varchar(10),
	`totalPrice` varchar(10),
	`createTime` TIMESTAMP,
	`year` varchar(10),
	`note` varchar(255),
  PRIMARY KEY (`bid`),
  KEY `cname` (`cname`),
	KEY `orderTime` (`orderTime`),
	key `year` (`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

select totalPrice from bill where cname ='宏福'
select totalPrice from bill where cname = 'aaa'

select totalPrice from bill where cname = '宏福' and orderTime BETWEEN '2011-02-11' and '2023-05-11'
select * from bill