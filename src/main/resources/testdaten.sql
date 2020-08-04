-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server Version:               10.5.4-MariaDB - mariadb.org binary distribution
-- Server Betriebssystem:        Win64
-- HeidiSQL Version:             11.0.0.6062
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle streamingdb.serien: ~16 rows (ungefähr)
/*!40000 ALTER TABLE `serien` DISABLE KEYS */;
INSERT INTO `serien` (`id`, `beschreibung`, `name`) VALUES
	(1, NULL, 'Dark'),
	(2, NULL, 'Gravity Falls'),
	(3, NULL, 'Final Space'),
	(4, NULL, 'Family Guy'),
	(5, NULL, 'Sherlock'),
	(6, NULL, 'Sex Education'),
	(7, NULL, 'Rick and Morty'),
	(8, NULL, 'Archer'),
	(9, NULL, 'Happy'),
	(10, NULL, 'Haus des Geldes'),
	(11, NULL, 'How to sell Drugs online (fast)'),
	(12, NULL, 'Naruto'),
	(13, NULL, 'V Wars'),
	(14, NULL, 'Daybreak'),
	(15, NULL, 'The Rain'),
	(16, NULL, 'The Fresh Prince of Bel Air');
/*!40000 ALTER TABLE `serien` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle streamingdb.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `nachname` varchar(255) DEFAULT NULL,
  `vorname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle streamingdb.user: ~8 rows (ungefähr)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `password`, `username`, `nachname`, `vorname`) VALUES
	(1, '12345', 'bummse_biene', 'Bumms', 'Thorsten'),
	(2, '54321', 'karl_und_die_steine', 'Ess', 'Karl'),
	(3, 'kekw', 'Comrade Elmo', 'Heller', 'Leon'),
	(4, 'uwu', 'V3kta', 'Rasche', 'Luca'),
	(5, 'minecraft', 'Kühnemann', 'Kühne', 'Phillip'),
	(6, 'SarahMelina0803!', 'MissesMelina', 'Slotta', 'Melina'),
	(7, 'hart', 'Reinholzen', 'Starsten', 'Carl'),
	(8, 'Esel', 'GetOutOfMySwOmp', 'Shrek', 'Allstar');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle streamingdb.user_serie_data
CREATE TABLE IF NOT EXISTS `user_serie_data` (
  `serie_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `zg_datum` varchar(255) DEFAULT NULL,
  `zg_folge` int(11) DEFAULT NULL,
  `zg_staffel` int(11) DEFAULT NULL,
  PRIMARY KEY (`serie_id`,`user_id`),
  KEY `FKdp2aqq7c2ly8xdmcupgsd523w` (`user_id`),
  CONSTRAINT `FKdp2aqq7c2ly8xdmcupgsd523w` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKfgu725xgf10bw5bsyesm8nxxs` FOREIGN KEY (`serie_id`) REFERENCES `serien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle streamingdb.user_serie_data: ~5 rows (ungefähr)
/*!40000 ALTER TABLE `user_serie_data` DISABLE KEYS */;
INSERT INTO `user_serie_data` (`serie_id`, `user_id`, `zg_datum`, `zg_folge`, `zg_staffel`) VALUES
	(1, 1, '05.06.2020', 5, 1),
	(2, 1, NULL, NULL, NULL),
	(3, 1, NULL, NULL, NULL),
	(4, 1, NULL, NULL, NULL),
	(4, 2, NULL, NULL, NULL),
	(4, 7, '04.08.2020', 1, 2),
	(5, 2, '20.05.2020', 2, 1),
	(6, 4, '19.12.1975', 4, 6),
	(6, 6, '06.06.2006', 6, 6),
	(9, 3, '20.05.2001', 7, 3),
	(10, 6, '05.06.2020', 5, 1),
	(10, 8, '01.04.2002', 7, 2),
	(12, 7, '08.05.1945', 9, 1);
/*!40000 ALTER TABLE `user_serie_data` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
