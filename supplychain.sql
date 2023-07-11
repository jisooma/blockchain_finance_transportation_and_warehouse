/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.28 : Database - supplychain
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`supplychain` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `supplychain`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  `name` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `bal` varchar(20) NOT NULL,
  `status` int(11) NOT NULL,
  `addr` varchar(100) NOT NULL,
  `privateKey` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=238 DEFAULT CHARSET=utf8;

/*Data for the table `account` */

insert  into `account`(`id`,`type`,`name`,`password`,`bal`,`status`,`addr`,`privateKey`) values 
(232,'0','bank_1','123','10000',0,'0x2cae2fa9243753f70c061ed74651c0870a72786f','376a477842e6a3718966ba42a05a9b467038b8b5550bdc25207d17c2c7fa4f3f'),
(233,'1','logistics_1','123','1000',0,'0x4405e21bbb989e4b3c09bee7eeeab904d8522769','a6705f20a03b186b277363690170146215f615f7c4ef72b1a6bc04f6898988a7'),
(234,'2','enterprise_1','123','1600',0,'0x3d01e6d883e143a10e56e41b0ff238ad22424fb0','89d93624899c5cfd509a12a2d5397d98f6cc03d83b40d3f3e3cfbb49487c5a94'),
(235,'2','enterprise_2','123','500',0,'0x5c5eb72d0cca3ecf6c5e33373c6170938d2ff706','4fa50c98d64c1b5d725c82fc4cb592b8a86326fa4d021cd27e66251974b2dc0b'),
(236,'2','enterprise_3','123','9000',0,'0xa2d1cb2e1cf49363484950134ac59a5fa7f0199b','d38618efb69039197fe9d48ac5bbc685fe6bf7db1eb783a4c57ba6c5a108edd5'),
(237,'3','supervise','123','1000',0,'0x3b7756bf3d68fd9672d5f9d1bc105febfb2b1bcd','d4f662cdfb8bfcce3fad564dbe5b2be9b36c4cf7d543bf19a7890ba69f7bfb41');

/*Table structure for table `accounttest` */

DROP TABLE IF EXISTS `accounttest`;

CREATE TABLE `accounttest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `accounttest` */

insert  into `accounttest`(`id`,`type`,`name`,`password`) values 
(1,1,'物流1','123456');

/*Table structure for table `appointcontract` */

DROP TABLE IF EXISTS `appointcontract`;

CREATE TABLE `appointcontract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bank` varchar(30) NOT NULL,
  `logistics` varchar(30) NOT NULL,
  `money` varchar(1000) NOT NULL,
  `status` varchar(40) NOT NULL,
  `updateTime` datetime NOT NULL,
  `endTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

/*Data for the table `appointcontract` */

insert  into `appointcontract`(`id`,`bank`,`logistics`,`money`,`status`,`updateTime`,`endTime`) values 
(100,'bank_1','logistics_1','120','同意签署','2021-04-20 06:33:50','2021-04-20 16:00:00');

/*Table structure for table `collateral` */

DROP TABLE IF EXISTS `collateral`;

CREATE TABLE `collateral` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise` varchar(40) NOT NULL,
  `consigner` varchar(40) NOT NULL,
  `status` varchar(40) NOT NULL,
  `updateTime` timestamp NULL DEFAULT NULL,
  `endTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

/*Data for the table `collateral` */

insert  into `collateral`(`ID`,`enterprise`,`consigner`,`status`,`updateTime`,`endTime`) values 
(83,'enterprise_2','enterprise_3','同意签署','2021-04-20 06:35:05','2021-04-20 06:34:15');

/*Table structure for table `contracthashdata` */

DROP TABLE IF EXISTS `contracthashdata`;

CREATE TABLE `contracthashdata` (
  `number` int(11) NOT NULL AUTO_INCREMENT,
  `no` int(11) DEFAULT NULL,
  `id` int(11) DEFAULT NULL,
  `partyA` varchar(60) DEFAULT NULL,
  `partyB` varchar(60) DEFAULT NULL,
  `Type` varchar(20) DEFAULT NULL,
  `hash` varchar(60) DEFAULT NULL,
  `updateTime` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `contracthashdata` */

/*Table structure for table `contracthashdetail` */

DROP TABLE IF EXISTS `contracthashdetail`;

CREATE TABLE `contracthashdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blockNumber` varchar(100) DEFAULT NULL,
  `thHash` varchar(100) DEFAULT NULL,
  `fromAddr` varchar(100) DEFAULT NULL,
  `toAddr` varchar(100) DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL,
  `Number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

/*Data for the table `contracthashdetail` */

insert  into `contracthashdetail`(`id`,`blockNumber`,`thHash`,`fromAddr`,`toAddr`,`updateTime`,`Number`) values 
(29,'560','0xfc2daa1bd305ab4f1a6555c51b56a0389b9114902837d4743ed5d48685e91462','0x5ead962f1221105159b6688d9940ca88cb8cf3f3','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-14 10:29:33',17),
(30,'569','0x3651497f80fc2c1451fb0aa8327cfde6ac0882429130cb7e59d65e8d3d6164da','0x5ead962f1221105159b6688d9940ca88cb8cf3f3','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-14 10:38:35',18),
(31,'597','0xb20d29edfe7b2b3c1523ca56d36931413f7045a83afe13e7e0f4c7c7641e40f8','0x6f5ec4f566bc89331c256dda08b9db154f7ead0d','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-14 11:18:44',19),
(32,'601','0x2aaac085f4f3d392c6a91c1f47f3a14ee78314586de3f29206dc0468fc4d80a4','0x6f5ec4f566bc89331c256dda08b9db154f7ead0d','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-14 11:20:31',20),
(33,'605','0x50ca9f9b4ba5cae81ae48eedd35daff493fef83c5d481e8d86d26303280ff0ee','0x6f5ec4f566bc89331c256dda08b9db154f7ead0d','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-14 11:39:29',21),
(34,'629','0x2d0add332f5cd9eac506f047442edda50f1f8b7c566464e359ee6cb84c92e1ac','0xc15e6454e2476100ce435b88eb01c26563e0595c','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-14 12:04:33',23),
(35,'630','0x1bce12ca31a194f09223bb6d196158ab07375c63dce37c5999e65eeeef504f64','0xc15e6454e2476100ce435b88eb01c26563e0595c','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-14 12:06:27',24),
(36,'637','0x12a0e494b5c53d4a70b4a9330c99635b347569079fc5c2974bd3c3cfc5fc09e4','0xc15e6454e2476100ce435b88eb01c26563e0595c','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-14 12:08:25',25),
(37,'663','0xbe6a418e6f625d0272c4b76cb86e4185340503abc805b9031974714a68886711','0xc15e6454e2476100ce435b88eb01c26563e0595c','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-14 13:09:36',28),
(38,'664','0xe4085ff02dcbdb51fa3f28adaf7fffa68968f0c714a21f682ecddc39d04623d3','0xc15e6454e2476100ce435b88eb01c26563e0595c','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-14 13:14:03',29),
(39,'668','0x538758a61cd7574f16d9e803bd67ce77ce1e928adb4bfffcc056f59b12a8bf9f','0xc15e6454e2476100ce435b88eb01c26563e0595c','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-14 13:27:09',30),
(40,'697','0xe12531b3c690eb64d7bbe784c5465b0d87f4a45e828aa289c5a89d64b041d958','0xecd25793a8286de9541220b45ac593fca60acd05','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-15 07:38:58',31),
(41,'698','0xc709e06b66b65f0057c7cadc59d643cb85eacc825681bb73462a47e09f766f92','0xecd25793a8286de9541220b45ac593fca60acd05','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-15 07:40:22',32),
(42,'747','0x870002c673795a13218e2cee6a5c56c6ecb16b4cb0a28e7212ee6ba5fa9f7984','0xecd25793a8286de9541220b45ac593fca60acd05','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-15 09:18:41',37),
(43,'748','0xf14b3a344a1f924248776968f7dd130fca1d048176cd8c1d617612b12bf790f6','0xecd25793a8286de9541220b45ac593fca60acd05','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-15 09:21:11',38),
(44,'805','0x822c7d1dac3fbc1df1e7f01d3cff58bb98e35ada1da815c42dc8390c0c5c54bd','0x097482b8feca021915f4df0269f990b4715142c2','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-16 07:38:48',40),
(45,'806','0x4883e1fca307ecf5f39eedb2fdf2448d0fe22ff387e364f6daac618e1ea2a546','0x097482b8feca021915f4df0269f990b4715142c2','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-16 07:40:36',41),
(46,'813','0x49646155e55f080bac2798fc24b3e6da3e5ade933ba19af98aafb1b765c9426a','0x097482b8feca021915f4df0269f990b4715142c2','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-16 07:43:41',42),
(47,'919','0x13e0c062bcdd06eaa95b5454e68aa126b32b28b753d4c36a55da02178d3ec71f','0xa3775477a99dd2fdaea5995cde226323f0ea7ed8','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-16 12:28:24',44),
(48,'939','0x917ef2b1eea30133f69367ff70314cc7d18e8dcd3bfa0ee1522b1fbcb2339a0a','0xa3775477a99dd2fdaea5995cde226323f0ea7ed8','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-16 12:59:53',46),
(49,'948','0x862a41b1496108336aaff2b05ea40d80db30e464c119ab14e3e67876217369c7','0xb5a8f93bd7e2026e036329411c6a9a33d3f817e3','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 02:48:52',48),
(50,'949','0xa8f4a42c6f2048bf779d436fdea599dec7af9d53bb6b1cc29f8f3896bc7be82b','0xb5a8f93bd7e2026e036329411c6a9a33d3f817e3','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 03:02:49',49),
(51,'950','0x4f94655d346155874f557b344fdcec61fbdca152115ec76694c2c2a65c61bf5a','0xb5a8f93bd7e2026e036329411c6a9a33d3f817e3','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 03:07:12',50),
(52,'951','0x67f7c2a4e1cbd884f1daa93f19018c6f55a63790ec95f46604d9ce34203baf42','0xb5a8f93bd7e2026e036329411c6a9a33d3f817e3','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 03:19:09',51),
(53,'952','0xc36083cfb9fe04a10cc9558fdb54c2653d70e72c52d84926f0927b69850d742b','0xb5a8f93bd7e2026e036329411c6a9a33d3f817e3','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 03:26:21',52),
(54,'953','0xf3d2b7544c0a29b3f12eaff70bd717fc13a5fa08ed46354142e568b5826fe1a5','0xb5a8f93bd7e2026e036329411c6a9a33d3f817e3','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 03:30:16',53),
(55,'954','0xaa8ffadf90f2978f36aa9fe6dd5352151d9ab8222b181900b37cb1cee44af6c9','0xb5a8f93bd7e2026e036329411c6a9a33d3f817e3','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 03:34:43',54),
(56,'955','0x53baa9e4f26899a58cd8491182a94e5ee3081d973a2dd3fdcdefe5acc2adb948','0xb5a8f93bd7e2026e036329411c6a9a33d3f817e3','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 03:36:48',55),
(57,'956','0x65e9ee31e8f4755648031e3e751737b9e9caee94464d82fb50fcbe342d1c89d8','0xb5a8f93bd7e2026e036329411c6a9a33d3f817e3','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 06:32:13',56),
(58,'983','0x23f814500c7f562788c09be00d7112532a3b344a8eed05605fc659f727130834','0xb5a8f93bd7e2026e036329411c6a9a33d3f817e3','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 07:03:24',57),
(59,'1025','0x616c1d553b9b7ec07543ef9d939f2fbb84e93b2e80efcc2d1ab92df79b3c5ff7','0x574073a25abec3bd8d3b478f8be1be35bf022076','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 07:28:49',58),
(60,'1038','0x1ee280b5b5dbc8d83e82ebcd691abd22100369a0af1b87d3ac675842f0006978','0x574073a25abec3bd8d3b478f8be1be35bf022076','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 07:34:14',59),
(61,'1039','0x6d9c417278187bf7f9f8dfa6a8aeeb4d9b2a1d8c24dd3f24b8f32c937fe428fc','0x574073a25abec3bd8d3b478f8be1be35bf022076','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 08:00:10',60),
(62,'1040','0x47816181a45398e31e7d3d61f6a4c38302e96678bcebb7dee7df4e1a1aa1fd63','0xa4e4682a66de221761b065f57d613e4a309c243e','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 08:07:17',61),
(63,'1069','0xbcf1f010704117f3ecb71d7971ee53484cc81cc2acf540d5f44fd8d637a5e088','0xb96c77757a38c0f80313ec7ca8bfb10f76d259e9','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 11:55:55',62),
(64,'1070','0x490f165097b2b98bc8e192e67086c2e2b5b03daadfc88aef0036d17a6b4a473e','0xb96c77757a38c0f80313ec7ca8bfb10f76d259e9','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 11:58:41',63),
(65,'1076','0x9c27adb3f652b11ce560253882e0228f188e7448323c7c6dbe228798af98bad6','0xb96c77757a38c0f80313ec7ca8bfb10f76d259e9','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 12:02:17',64),
(66,'1079','0xf8919f2d468f5b3347973baed416e3e9646618b7e9a76bc82658cba871c02673','0xb96c77757a38c0f80313ec7ca8bfb10f76d259e9','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 12:09:58',65),
(67,'1109','0x53a3f9b2d58527b445e56e1efc5fa29d9e6f982f8e3793325b0a9f42fb0b8abb','0xd7e0841383929f45a4fb154ca810d29475ca2961','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 12:22:33',66),
(68,'1110','0x00d5c6c0fbcdddc390acd453e44717e113d5eb50c4dfea2c40aab43378a90c90','0xd7e0841383929f45a4fb154ca810d29475ca2961','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 12:23:05',67),
(69,'1113','0xfdf8c893835dcaa85a2e6020219b5a8a5f170d42ce6863d4cfd78df45ef5ab76','0xd7e0841383929f45a4fb154ca810d29475ca2961','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 12:40:04',68),
(70,'1126','0xf4e33f0e458a67bfb742ee07ec68dfc86822b1e323dbea992b436fc912a6d194','0xd7e0841383929f45a4fb154ca810d29475ca2961','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 12:44:37',69),
(71,'1135','0x85228078e5bc4482d733db107c27196c7baab43dc4b1da256985959917083228','0xd7e0841383929f45a4fb154ca810d29475ca2961','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 12:50:43',70),
(72,'1136','0x7547e742a555dfa8ba307097b868d8ac7472ad11b292cf5cb8543eccca083ddc','0xd7e0841383929f45a4fb154ca810d29475ca2961','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 12:51:25',71),
(73,'1138','0xd43e7f1ea692ae608c09841fda87a2acc8da0ea6a391a27a321864e30731c99a','0xd7e0841383929f45a4fb154ca810d29475ca2961','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-17 12:54:04',72),
(74,'1171','0xdb8d8c548e200f43064948a0d56d8932e88bd16b105494d1628d37517c01beac','0x0a1356b173dad99abf3d95cc32b32c9c6de516e8','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-18 03:13:38',73),
(75,'1172','0x9614a38413b02de342b323d6138d9bfea8c31e52e86412c3633b54268de32495','0x0a1356b173dad99abf3d95cc32b32c9c6de516e8','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-18 03:14:18',74),
(76,'1179','0xfd3f59e73d8204665ea5b53f42a6d14c77a41b6e1898d58712021817e35c3c43','0x0a1356b173dad99abf3d95cc32b32c9c6de516e8','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-18 03:17:51',75),
(77,'1180','0x830dab51e0fa3549194e17288a15b8f4f3b4fd820803f4c324d598cd820a5226','0x0a1356b173dad99abf3d95cc32b32c9c6de516e8','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-18 03:21:14',76),
(78,'1187','0x59678332fee10f13eb2671446c436af3ae270ad5dddd327848403c4adb69a10c','0x0a1356b173dad99abf3d95cc32b32c9c6de516e8','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-18 03:23:53',77),
(79,'1193','0x3e3232fd1e1b885bdb2f85459b3b9b60562d14a7944c01760b3d3fc8268af66d','0x0a1356b173dad99abf3d95cc32b32c9c6de516e8','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-18 03:26:41',78),
(80,'1231','0xe6c0a05016029f29e2f356a51d113010294305860e5acc589b3198d53b020a04','0xe5a17a7a79d60efb4a5ecd87649f7fa8b36755c0','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-18 12:38:28',79),
(81,'1238','0x84897447c0194501d9257757de04f0287d9e0ea7521c332927237b03000ec4d0','0xe5a17a7a79d60efb4a5ecd87649f7fa8b36755c0','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-18 12:42:06',80),
(82,'1239','0xc05d24f79378e9d923b5813eab38c1e08cabd0ccbd00c66f4b92d27bfc344f31','0xe5a17a7a79d60efb4a5ecd87649f7fa8b36755c0','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-18 12:42:51',81),
(83,'1240','0xa5419ada2a1f9344c9b1cf95f6b50afffbb0d8a331facd9cebcca10ca2110d2f','0xe5a17a7a79d60efb4a5ecd87649f7fa8b36755c0','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-18 12:45:54',82),
(84,'1251','0x7c39e1693a007bda54375a1931501ebd283fecb37ccf1086200c1f1650760095','0xe5a17a7a79d60efb4a5ecd87649f7fa8b36755c0','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-18 12:47:10',83),
(85,'1252','0xca26a966a6336782ba236bd063005331424bcf9440a183dc1578f85d55a3da5b','0xe5a17a7a79d60efb4a5ecd87649f7fa8b36755c0','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-18 12:50:32',84),
(86,'1258','0xf4933d81863a1e4ea297d706c0bfcd20d1cc7bc5fbf4c23b1cf290733156347e','0xe5a17a7a79d60efb4a5ecd87649f7fa8b36755c0','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-18 12:52:37',85),
(87,'1296','0x11603416e0361317ea897f3b2df1bc4a8c05a96eaf7f81b78615a4a0c5ff51ee','0xbcdc452f9f243f7a300a745d5dae8c2d9db57884','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-20 01:35:52',86),
(88,'1297','0x4107761680c4a2af64cab903825bbb40fa25937486e5cd5e3446cde512d644d8','0xbcdc452f9f243f7a300a745d5dae8c2d9db57884','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-20 01:36:41',87),
(89,'1304','0xc34059d7242114498574ee69acbed6f3e1e18323a6b633d2c9db9d515867398a','0xbcdc452f9f243f7a300a745d5dae8c2d9db57884','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-20 01:38:25',88),
(90,'1305','0xc13b577bcdaff239ca4b51e077bff380c09fe7754515afef83ddd07c7f63e02f','0xbcdc452f9f243f7a300a745d5dae8c2d9db57884','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-20 01:41:37',89),
(91,'1312','0x0c68c4dedcb0a2e3638754eac50d0cc5a207b1b08875a840b05d264d6270dab7','0xbcdc452f9f243f7a300a745d5dae8c2d9db57884','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-20 01:41:46',90),
(92,'1319','0x424c3b72f6255b0eecd3639426fd47eebf46dcfbea0db2cdf097a7f8d108d22a','0xbcdc452f9f243f7a300a745d5dae8c2d9db57884','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-20 01:47:35',91),
(93,'1325','0x80694a362f7a9faf9e0fbc117325cd0c07ff4f7f978f6f776f4bc5dd844ecd15','0xbcdc452f9f243f7a300a745d5dae8c2d9db57884','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-20 01:50:20',92),
(94,'1357','0x01581c51a2963991689dced5ea8e9eb25ed40e5536a2ac6c420cb34bee029b9a','0x4405e21bbb989e4b3c09bee7eeeab904d8522769','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-20 06:33:50',93),
(95,'1358','0xf67b5beaa4ad3639db3ed4922ac8472e1eceebb89d275cdfd5da4dbc666151a6','0x4405e21bbb989e4b3c09bee7eeeab904d8522769','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-20 06:35:05',94),
(96,'1365','0x3bc929716929a4a1f75cc63fa7e276a98a46a59074723405f18f711b91e43603','0x4405e21bbb989e4b3c09bee7eeeab904d8522769','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-20 06:36:53',95),
(97,'1366','0xf18e59ef78d69fc12a72bee071681897fb8133107c0ddd47780764966d4b02d7','0x4405e21bbb989e4b3c09bee7eeeab904d8522769','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-20 06:44:10',96),
(98,'1373','0x4a0fbc1c4b169722945ef5158752f3e1a8e9f12e269c170c467d48203d2b83a7','0x4405e21bbb989e4b3c09bee7eeeab904d8522769','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-20 06:45:59',97),
(99,'1379','0x71af8915ddad32794a89ec8e9e023062dd1ae316d501f457975d1df5031e7056','0x4405e21bbb989e4b3c09bee7eeeab904d8522769','0xab8ff93194249a7801732e63b478a9973cef43b8','2021-04-20 06:47:41',98);

/*Table structure for table `contracts` */

DROP TABLE IF EXISTS `contracts`;

CREATE TABLE `contracts` (
  `ID` int(11) NOT NULL,
  `enterprise` varchar(10) NOT NULL,
  `transport` varchar(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `contracts` */

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goNo` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `specification` varchar(40) NOT NULL,
  `number` int(11) NOT NULL,
  `addr` varchar(40) NOT NULL,
  `holder` varchar(40) NOT NULL,
  `InputTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(40) NOT NULL,
  `remark` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`goNo`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;

/*Data for the table `goods` */

/*Table structure for table `inventory` */

DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise` varchar(30) NOT NULL,
  `logistics` varchar(30) NOT NULL,
  `waNo` int(11) NOT NULL,
  `reMoney` varchar(1000) NOT NULL,
  `status` varchar(40) NOT NULL,
  `updateTime` datetime NOT NULL,
  `endTime` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

/*Data for the table `inventory` */

insert  into `inventory`(`ID`,`enterprise`,`logistics`,`waNo`,`reMoney`,`status`,`updateTime`,`endTime`) values 
(86,'enterprise_2','logistics_1',101,'100','同意签署','2021-04-20 06:36:53','2021-04-20 06:36:38');

/*Table structure for table `loanapply` */

DROP TABLE IF EXISTS `loanapply`;

CREATE TABLE `loanapply` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise` varchar(30) CHARACTER SET utf8 NOT NULL,
  `bank` varchar(30) CHARACTER SET utf8 NOT NULL,
  `coID` int(11) NOT NULL,
  `inID` int(11) NOT NULL,
  `loanPurpose` varchar(1000) COLLATE utf8_general_mysql500_ci NOT NULL,
  `loMoney` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `status` varchar(40) COLLATE utf8_general_mysql500_ci NOT NULL,
  `applyTime` datetime DEFAULT NULL,
  `dueTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

/*Data for the table `loanapply` */

insert  into `loanapply`(`ID`,`enterprise`,`bank`,`coID`,`inID`,`loanPurpose`,`loMoney`,`status`,`applyTime`,`dueTime`,`updateTime`) values 
(63,'enterprise_2','bank_1',83,86,'购买原材料','560.0','贷款申请失效','2021-04-20 06:42:26','2021-04-20 16:00:00','2021-04-20 06:48:04');

/*Table structure for table `loancontract` */

DROP TABLE IF EXISTS `loancontract`;

CREATE TABLE `loancontract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise` varchar(40) NOT NULL,
  `bank` varchar(40) NOT NULL,
  `loanPurpose` varchar(60) NOT NULL,
  `Money` varchar(1000) NOT NULL,
  `waNo` int(11) NOT NULL,
  `reciptId` int(11) DEFAULT NULL,
  `interests` int(11) NOT NULL,
  `repayMoney` varchar(1000) DEFAULT NULL,
  `startTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dueTime` timestamp NULL DEFAULT NULL,
  `status` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `loancontract` */

insert  into `loancontract`(`id`,`enterprise`,`bank`,`loanPurpose`,`Money`,`waNo`,`reciptId`,`interests`,`repayMoney`,`startTime`,`dueTime`,`status`) values 
(37,'enterprise_2','bank_1','购买原材料','560.0',101,61,0,'560.0','2021-04-20 06:43:42','2021-04-20 16:00:00','贷款已偿还,合同失效');

/*Table structure for table `loanrecipt` */

DROP TABLE IF EXISTS `loanrecipt`;

CREATE TABLE `loanrecipt` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise` varchar(30) NOT NULL,
  `bank` varchar(30) NOT NULL,
  `loanID` int(11) NOT NULL,
  `allMoney` varchar(1000) NOT NULL,
  `reMoney` varchar(1000) NOT NULL,
  `status` varchar(40) NOT NULL,
  `updateTime` timestamp NULL DEFAULT NULL,
  `dueTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

/*Data for the table `loanrecipt` */

insert  into `loanrecipt`(`ID`,`enterprise`,`bank`,`loanID`,`allMoney`,`reMoney`,`status`,`updateTime`,`dueTime`) values 
(61,'enterprise_2','bank_1',63,'560.0','560','已偿还贷款','2021-04-20 06:48:04','2021-04-20 16:00:00');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(100) NOT NULL,
  `supplier` varchar(60) NOT NULL,
  `buyer` varchar(40) NOT NULL,
  `status` varchar(40) NOT NULL,
  `totalMoney` varchar(1000) DEFAULT NULL,
  `orderTime` datetime NOT NULL,
  `remark` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `order` */

insert  into `order`(`id`,`uuid`,`supplier`,`buyer`,`status`,`totalMoney`,`orderTime`,`remark`) values 
(28,'10000021435074121618901092002','enterprise_1','enterprise_2','供应商已确认','600','2021-04-20 06:44:50',NULL),
(29,'10000011815167991618901195616','enterprise_2','enterprise_3','供应商已确认','1000','2021-04-20 06:46:34',NULL);

/*Table structure for table `ordercontract` */

DROP TABLE IF EXISTS `ordercontract`;

CREATE TABLE `ordercontract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `supplier` varchar(40) NOT NULL,
  `buyer` varchar(40) NOT NULL,
  `uuid` varchar(40) NOT NULL,
  `demands` varchar(60) DEFAULT NULL,
  `addr` varchar(60) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `expiration` varchar(11) DEFAULT NULL,
  `startTime` timestamp NULL DEFAULT NULL,
  `endTime` timestamp NULL DEFAULT NULL,
  `status` varchar(60) NOT NULL,
  `remark` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `ordercontract` */

insert  into `ordercontract`(`id`,`supplier`,`buyer`,`uuid`,`demands`,`addr`,`time`,`expiration`,`startTime`,`endTime`,`status`,`remark`) values 
(19,'enterprise_1','enterprise_2','10000021435074121618901092002','无','太原','2021-04-20 06:45:26','1月','2021-04-20 06:45:32','2021-04-20 06:45:34','同意签署',''),
(20,'enterprise_2','enterprise_3','10000011815167991618901195616','无','太原','2021-04-20 06:47:08','2月','2021-04-20 06:47:14','2021-04-20 16:00:00','同意签署','');

/*Table structure for table `orderitem` */

DROP TABLE IF EXISTS `orderitem`;

CREATE TABLE `orderitem` (
  `orID` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(100) DEFAULT NULL,
  `name` varchar(60) NOT NULL,
  `specification` varchar(60) NOT NULL,
  `number` int(11) NOT NULL,
  `status` varchar(40) NOT NULL,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`orID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `orderitem` */

insert  into `orderitem`(`orID`,`uuid`,`name`,`specification`,`number`,`status`,`updateTime`) values 
(26,'10000021435074121618901092002','1号轮胎','中型',100,'等待供应商确认采购订单','2021-04-20 06:44:50'),
(27,'10000011815167991618901195616','1号车','中型',100,'等待供应商确认采购订单','2021-04-20 06:46:34');

/*Table structure for table `respository` */

DROP TABLE IF EXISTS `respository`;

CREATE TABLE `respository` (
  `reNo` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `specification` varchar(50) NOT NULL,
  `number` int(11) NOT NULL,
  `addr` varchar(50) NOT NULL,
  `holder` varchar(50) NOT NULL,
  `logistics` varchar(50) DEFAULT NULL,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(30) NOT NULL,
  `remark` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`reNo`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

/*Data for the table `respository` */

insert  into `respository`(`reNo`,`name`,`specification`,`number`,`addr`,`holder`,`logistics`,`updateTime`,`status`,`remark`) values 
(102,'1号车','中型',100,'北京','enterprise_2','logistics_1','2021-04-20 06:48:04','同意入库','');

/*Table structure for table `tokenexchange` */

DROP TABLE IF EXISTS `tokenexchange`;

CREATE TABLE `tokenexchange` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bank` varchar(40) NOT NULL,
  `initiator` varchar(40) NOT NULL,
  `token` varchar(1000) NOT NULL,
  `status` varchar(40) NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tokenexchange` */

/*Table structure for table `tranferinfo` */

DROP TABLE IF EXISTS `tranferinfo`;

CREATE TABLE `tranferinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `payer` varchar(30) NOT NULL,
  `payee` varchar(30) NOT NULL,
  `money` varchar(12345) NOT NULL,
  `type` varchar(40) DEFAULT NULL,
  `updateTime` datetime NOT NULL,
  `remark` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

/*Data for the table `tranferinfo` */

insert  into `tranferinfo`(`id`,`payer`,`payee`,`money`,`type`,`updateTime`,`remark`) values 
(61,'bank_1','enterprise_2','560.0','借贷转账','2021-04-20 06:44:10','借款'),
(62,'enterprise_2','enterprise_1','600','普通转账','2021-04-20 06:45:59',NULL),
(63,'enterprise_3','enterprise_2','1000','普通转账','2021-04-20 06:47:41',NULL),
(64,'enterprise_2','bank_1','560.0','借贷转账','2021-04-20 06:48:04','还款');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `bal` varchar(1000) NOT NULL,
  `status` int(11) NOT NULL,
  `address` varchar(60) DEFAULT NULL,
  `privateKey` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*Table structure for table `valuereport` */

DROP TABLE IF EXISTS `valuereport`;

CREATE TABLE `valuereport` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `logistics` varchar(30) NOT NULL,
  `enterprise` varchar(30) NOT NULL,
  `reNo` int(11) NOT NULL,
  `valMoney` varchar(1000) NOT NULL,
  `status` int(11) NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `valuereport` */

/*Table structure for table `warehousereceipt` */

DROP TABLE IF EXISTS `warehousereceipt`;

CREATE TABLE `warehousereceipt` (
  `waNo` int(11) NOT NULL AUTO_INCREMENT,
  `holder` varchar(30) NOT NULL,
  `logistics` varchar(30) NOT NULL,
  `reno` int(11) NOT NULL,
  `valMoney` varchar(1000) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  `status` varchar(40) NOT NULL,
  `updateTime` datetime NOT NULL,
  `remark` text,
  PRIMARY KEY (`waNo`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

/*Data for the table `warehousereceipt` */

insert  into `warehousereceipt`(`waNo`,`holder`,`logistics`,`reno`,`valMoney`,`address`,`status`,`updateTime`,`remark`) values 
(100,'bank_1','logistics_1',101,'900','北京','仓单已抵押','2021-04-20 01:41:46',NULL),
(101,'enterprise_2','logistics_1',102,'800','北京','合法','2021-04-20 06:48:04',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
