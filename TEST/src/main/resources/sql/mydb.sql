-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.5.9-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- mydb 데이터베이스 구조 내보내기
DROP DATABASE IF EXISTS `mydb`;
CREATE DATABASE IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mydb`;

-- 테이블 mydb.dept 구조 내보내기
DROP TABLE IF EXISTS `dept`;
CREATE TABLE IF NOT EXISTS `dept` (
  `id` varchar(50) DEFAULT NULL,
  `sal` int(11) DEFAULT NULL,
  `dept` varchar(50) DEFAULT NULL,
  `dept2` varchar(50) DEFAULT NULL,
  `regDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 mydb.dept:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` (`id`, `sal`, `dept`, `dept2`, `regDate`) VALUES
	('orion', 200000, '행정', '서무', '2021-02-24 18:20:24'),
	('testdrive', 100000, '행정', '계약', '2021-02-24 18:20:40');
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;

-- 테이블 mydb.member 구조 내보내기
DROP TABLE IF EXISTS `member`;
CREATE TABLE IF NOT EXISTS `member` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `regDate` datetime DEFAULT current_timestamp(),
  `loc` varchar(50) DEFAULT 'X',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 mydb.member:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`id`, `name`, `password`, `age`, `regDate`, `loc`) VALUES
	('alpha', '허문도', '123', 16, '2021-02-24 13:36:55', 'D'),
	('orion', '여포', 'orion', 28, '2021-02-24 17:35:37', 'C'),
	('test', '조운', 'rf8wq8', 11, '2021-02-24 11:45:05', 'DJ'),
	('testdrive', 'testdrive', '123', 47, '2021-02-24 13:45:46', 'S');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
