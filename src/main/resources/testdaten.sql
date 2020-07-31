-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server Version:               10.5.4-MariaDB - mariadb.org binary distribution
-- Server Betriebssystem:        Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Exportiere Datenbank Struktur für streamingdb
CREATE DATABASE IF NOT EXISTS `streamingdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `streamingdb`;

-- Exportiere Struktur von Tabelle streamingdb.serien
CREATE TABLE IF NOT EXISTS `serien` (
  `id` int(11) NOT NULL,
  `beschreibung` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `zg_datum` varchar(255) DEFAULT NULL,
  `zg_folge` int(11) DEFAULT NULL,
  `zg_staffel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle streamingdb.serien: ~0 rows (ungefähr)
/*!40000 ALTER TABLE `serien` DISABLE KEYS */;
INSERT INTO `serien` (`id`, `beschreibung`, `name`, `zg_datum`, `zg_folge`, `zg_staffel`) VALUES
	(1, NULL, 'Castle', NULL, NULL, NULL),
	(2, NULL, 'The Mandalorian', NULL, NULL, NULL),
	(3, NULL, 'Dark', NULL, NULL, NULL),
	(4, NULL, 'Tokyo Ghoul', NULL, NULL, NULL);
/*!40000 ALTER TABLE `serien` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle streamingdb.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle streamingdb.user: ~0 rows (ungefähr)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `password`, `username`) VALUES
	(1, '1234', 'Luca'),
	(2, '1234', 'Supi');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle streamingdb.user_serie_data
CREATE TABLE IF NOT EXISTS `user_serie_data` (
  `user_id` int(11) NOT NULL,
  `serie_id` int(11) NOT NULL,
  KEY `FKfgu725xgf10bw5bsyesm8nxxs` (`serie_id`),
  KEY `FKdp2aqq7c2ly8xdmcupgsd523w` (`user_id`),
  CONSTRAINT `FKdp2aqq7c2ly8xdmcupgsd523w` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKfgu725xgf10bw5bsyesm8nxxs` FOREIGN KEY (`serie_id`) REFERENCES `serien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle streamingdb.user_serie_data: ~0 rows (ungefähr)
/*!40000 ALTER TABLE `user_serie_data` DISABLE KEYS */;
INSERT INTO `user_serie_data` (`user_id`, `serie_id`) VALUES
	(1, 4),
	(1, 3),
	(2, 4),
	(2, 2);
/*!40000 ALTER TABLE `user_serie_data` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
