/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - atr
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`atr` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `atr`;

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` varchar(20) NOT NULL,
  `psw` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `id_card` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `customer` */

insert  into `customer`(`id`,`psw`,`name`,`phone`,`id_card`) values ('aaa','bbb','李四','18072760626','350823200001010606'),('lzh','lzh','蓝泽华','18250381375','350823200108061636');

/*Table structure for table `line` */

DROP TABLE IF EXISTS `line`;

CREATE TABLE `line` (
  `lineid` varchar(20) NOT NULL,
  `beginplace` varchar(20) DEFAULT NULL,
  `endplace` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`lineid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `line` */

insert  into `line`(`lineid`,`beginplace`,`endplace`) values ('P1111','北京','上海'),('P2222','北京','三亚'),('P3333','北京','深圳');

/*Table structure for table `plane` */

DROP TABLE IF EXISTS `plane`;

CREATE TABLE `plane` (
  `planeid` varchar(20) NOT NULL,
  `lineid` varchar(20) DEFAULT NULL,
  `company` varchar(20) DEFAULT NULL,
  `seat` int(10) DEFAULT NULL,
  `begintime` datetime DEFAULT NULL,
  `arrivetime` datetime DEFAULT NULL,
  `price` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`planeid`),
  KEY `lineid` (`lineid`),
  CONSTRAINT `lineid` FOREIGN KEY (`lineid`) REFERENCES `line` (`lineid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `plane` */

insert  into `plane`(`planeid`,`lineid`,`company`,`seat`,`begintime`,`arrivetime`,`price`) values ('波音111','P1111','波音',19,'2023-01-05 00:00:00','2023-01-05 08:00:00','400'),('波音222','P1111','波音',20,'2023-01-05 08:00:00','2023-01-05 16:00:00','400'),('波音333','P1111','波音',20,'2023-01-05 16:00:00','2023-01-06 00:00:00','400'),('海航HU111','P3333','海航集团',20,'2023-01-05 00:00:00','2023-01-05 08:00:00','450'),('海航HU222','P3333','海航集团',20,'2023-01-05 08:00:00','2023-01-05 16:00:00','450'),('海航HU333','P3333','海航集团',20,'2023-01-05 16:00:00','2023-01-06 00:00:00','450'),('空客A111','P2222','北京航空',20,'2023-01-05 00:00:00','2023-01-05 08:00:00','500'),('空客A222','P2222','北京航空',20,'2023-01-05 08:00:00','2023-01-05 16:00:00','500'),('空客A333','P2222','北京航空',20,'2023-01-05 16:00:00','2023-01-06 00:00:00','500');

/*Table structure for table `ticketorder` */

DROP TABLE IF EXISTS `ticketorder`;

CREATE TABLE `ticketorder` (
  `orderid` varchar(20) NOT NULL,
  `lineid` varchar(20) DEFAULT NULL,
  `planeid` varchar(20) DEFAULT NULL,
  `id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  KEY `lineid` (`lineid`),
  KEY `planeid` (`planeid`),
  KEY `id` (`id`),
  CONSTRAINT `ticketorder_ibfk_1` FOREIGN KEY (`lineid`) REFERENCES `line` (`lineid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ticketorder_ibfk_2` FOREIGN KEY (`planeid`) REFERENCES `plane` (`planeid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ticketorder_ibfk_4` FOREIGN KEY (`id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ticketorder` */

insert  into `ticketorder`(`orderid`,`lineid`,`planeid`,`id`) values ('45613','P1111','波音111','lzh');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
