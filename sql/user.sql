DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(20) not null auto_increment,
  `uname` varchar(50),
	`password` varchar(50),
	`status` int(2),
	`tel` varchar(11),
  PRIMARY KEY (`uid`),
  KEY `id` (`uid`,`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;