-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 20 juin 2025 à 05:23
-- Version du serveur : 11.7.2-MariaDB
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `beers`
--

-- --------------------------------------------------------

--
-- Structure de la table `beer`
--

DROP TABLE IF EXISTS `beer`;
CREATE TABLE IF NOT EXISTS `beer` (
  `idBeer` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `brewer` varchar(50) NOT NULL,
  `style` varchar(50) NOT NULL,
  `alcohol` float NOT NULL,
  `price` double NOT NULL,
  `stock` int(11) NOT NULL,
  PRIMARY KEY (`idBeer`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `beer`
--

INSERT INTO `beer` (`idBeer`, `name`, `brewer`, `style`, `alcohol`, `price`, `stock`) VALUES
(1, 'Blanche du Mont Blanc', 'Brasserie du Mont Blanc', 'Blanche', 4.6, 3.85, 87),
(2, 'La Choulette Ambrée', 'Brasserie La Choulette', 'Ambrée', 8, 4, 76),
(3, 'Page 24 Blonde', 'Brasserie Saint Germain', 'Blonde', 5.9, 4.35, 71),
(4, 'Bellerose Blonde', 'Brasserie des Sources', 'Blonde IPA', 6.5, 4, 49),
(5, 'Jenlain Ambrée', 'Brasserie Duyck', 'Ambrée', 7.5, 3.25, 63),
(6, 'La Débauche Triple', 'Brasserie La Débauche', 'Triple', 9, 4, 46),
(7, 'Cuvée des Trolls', 'Brasserie Dubuisson', 'Blonde', 7, 4.1, 83),
(8, 'La Parisienne Blanche', 'Brasserie La Parisienne', 'Blanche', 5.3, 3, 118),
(9, 'La Bête', 'Brasserie La Bête', 'Ambrée', 8, 4, 55),
(10, 'Mont Salève Pale Ale', 'Brasserie du Mont Salève', 'Pale Ale', 5.6, 5.9, 45),
(11, 'BAPBAP Originale', 'BAPBAP', 'Blonde', 5.8, 4, 100),
(12, 'Oskare IPA', 'Oskare', 'IPA', 6.8, 4, 95),
(13, 'Parisis Blanche', 'Brasserie Parisis', 'Blanche', 5.2, 4, 107),
(14, 'La Lémance Blonde', 'Brasserie de la Lémance', 'Blonde', 6, 4, 73),
(15, 'Hapchot Ambrée', 'Brasserie Hapchot', 'Ambrée', 7.2, 4, 85),
(16, 'Bourganel Châtaigne', 'Brasserie Bourganel', 'Spéciale', 5.5, 4, 56),
(17, 'Gallia Lager', 'Gallia', 'Lager', 5, 3, 90),
(18, 'Cap d’Ona Triple', 'Brasserie Cap d’Ona', 'Triple', 8.5, 4, 40),
(19, 'Effet Papillon IPA', 'Brasserie Effet Papillon', 'IPA', 6.3, 4, 75),
(20, 'Brasserie du Grand Paris Pale Ale', 'Grand Paris', 'Pale Ale', 5.6, 4, 53),
(21, 'Azimut NEIPA', 'Brasserie Azimut', 'NEIPA', 6.2, 4, 46),
(22, 'Pietra', 'Brasserie Pietra', 'Ambrée', 6, 4, 120),
(23, 'Corse Colomba', 'Brasserie Pietra', 'Blanche', 5, 4, 100),
(24, 'Ninkasi Blanche', 'Ninkasi', 'Blanche', 4.8, 3, 100),
(25, 'La Minotte IPA', 'La Minotte', 'IPA', 6.5, 4, 60),
(26, 'Fischer Amber', 'Fischer', 'Ambrée', 6.3, 3, 80),
(27, 'Meteor Pils', 'Brasserie Meteor', 'Pils', 5, 3, 130),
(28, '3 Monts', 'Brasserie de Saint Sylvestre', 'Bière de Garde', 8.5, 4, 70),
(29, 'Anosteké Blonde', 'Brasserie du Pays Flamand', 'Blonde', 8, 4, 95),
(30, 'La Rieuse Blonde', 'Brasserie La Rieuse', 'Blonde', 5.5, 3, 75),
(31, 'La Ptite Maiz IPA', 'La P\'tite Maiz', 'IPA', 6.5, 4, 60),
(32, 'La Pigeonne', 'Brasserie L’Instant', 'Saison', 5.8, 4, 45),
(33, 'Brew Saint Omer Blonde', 'Saint Omer', 'Blonde', 5.5, 3, 150),
(34, 'Brasserie du Vercors Rousse', 'Brasserie du Vercors', 'Rousse', 6, 4, 55),
(35, 'La Parisienne IPA', 'Brasserie La Parisienne', 'IPA', 6.5, 4, 90),
(36, 'Brasserie La Corrézienne Blonde', 'La Corrézienne', 'Blonde', 5.2, 4, 85),
(37, 'Brasserie du Mont Blanc Ambrée', 'Mont Blanc', 'Ambrée', 6.5, 4, 70),
(38, 'Brasserie de Sutter Blanche', 'De Sutter', 'Blanche', 4.5, 3, 65),
(39, 'La Canute Bière Blanche', 'Canute', 'Blanche', 5, 3, 80),
(40, 'Brasserie Mira Blonde', 'Mira', 'Blonde', 5.6, 4, 90),
(41, 'Brasserie La Débauche IPA', 'La Débauche', 'IPA', 6.8, 4, 60),
(42, 'BAPBAP NEIPA', 'BAPBAP', 'NEIPA', 6.4, 4, 75),
(43, 'Brasserie Craig Allan Ambrée', 'Craig Allan', 'Ambrée', 7.5, 4, 55),
(45, 'La Germanoise Rousse', 'La Germanoise', 'Rousse', 6.2, 4, 100),
(46, 'Brasserie Parisis IPA', 'Parisis', 'IPA', 7, 4, 60),
(48, 'La Débauche Stout', 'La Débauche', 'Stout', 9.5, 5, 40),
(49, 'Brasserie du Mont Blanc IPA', 'Mont Blanc', 'IPA', 6, 4, 100),
(63, 'La Parisienne IPA', 'Brasserie La Parisienne', 'IPA', 6.5, 4, 90),
(64, 'Bière test', 'Etienne', 'Blonde', 5.5, 4.56, 30),
(83, 'Bière test', 'Etienne', 'Blonde', 5.3, 5.87, 20),
(100, 'Test quantité', 'qsdqsd', 'qsqsdqs', 5, 2, 0);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `idClient` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `adress` varchar(50) NOT NULL,
  `zipCode` int(11) NOT NULL,
  `city` varchar(50) NOT NULL,
  `phoneNumber` varchar(19) NOT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`idClient`, `firstName`, `lastName`, `adress`, `zipCode`, `city`, `phoneNumber`) VALUES
(1, 'Julien', 'Delmas', '200 Rue de Toulouse', 12000, 'Rodez', '0606060606'),
(2, 'Claire', 'Dupond', '456 Boulevard Saint-Germain', 75000, 'Paris', '0606060606'),
(3, 'Pierre', 'Johnny TEST', 'Adresse de Pierre', 12330, 'Lyon', '0606484788'),
(4, 'Lucie', 'Bernard', '101 Rue du Faubourg', 13001, 'Marseille', '0648478888'),
(5, 'Marc', 'Lemoine', '234 Avenue de la République', 33000, 'Bordeaux', '0617654321'),
(31, 'Etienne', 'LARROUMETS TEST', 'Adresse', 12000, 'VILLE', '0565656565');

-- --------------------------------------------------------

--
-- Structure de la table `clientorder`
--

DROP TABLE IF EXISTS `clientorder`;
CREATE TABLE IF NOT EXISTS `clientorder` (
  `idOrder` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `idClient` int(11) NOT NULL,
  PRIMARY KEY (`idOrder`),
  KEY `Order_Client_FK` (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `clientorder`
--

INSERT INTO `clientorder` (`idOrder`, `date`, `idClient`) VALUES
(1, '2025-05-01', 1),
(2, '2025-05-02', 2),
(3, '2025-05-03', 3),
(4, '2025-05-04', 4),
(5, '2025-05-05', 5),
(8, '2025-05-27', 3),
(12, '2025-06-12', 2),
(18, '2025-06-13', 3),
(19, '2025-06-13', 1),
(20, '2025-06-13', 1),
(21, '2025-06-13', 3),
(22, '2025-06-13', 3),
(31, '2025-06-13', 3),
(32, '2025-06-13', 4),
(33, '2025-06-13', 2),
(34, '2025-06-13', 3),
(35, '2025-06-13', 3),
(36, '2025-06-13', 3),
(37, '2025-06-14', 5),
(38, '2025-06-14', 2),
(40, '2025-06-14', 31),
(41, '2025-06-14', 31),
(42, '2025-06-14', 31),
(43, '2025-06-15', 2),
(44, '2025-06-15', 3),
(45, '2025-06-15', 4),
(48, '2025-06-15', 3),
(49, '2025-06-16', 2),
(50, '2025-06-16', 31),
(51, '2025-06-16', 5),
(52, '2025-06-16', 3),
(53, '2025-06-16', 2),
(54, '2025-06-17', 2),
(55, '2025-06-17', 3),
(56, '2025-06-17', 3),
(57, '2025-06-17', 3),
(60, '2025-06-17', 3),
(61, '2025-06-17', 3),
(62, '2025-06-17', 3),
(63, '2025-06-17', 3),
(64, '2025-06-17', 5),
(65, '2025-06-19', 2),
(66, '2025-06-19', 2),
(67, '2025-06-20', 3),
(68, '2025-06-20', 3);

-- --------------------------------------------------------

--
-- Structure de la table `orderline`
--

DROP TABLE IF EXISTS `orderline`;
CREATE TABLE IF NOT EXISTS `orderline` (
  `idOrder` int(11) NOT NULL,
  `idBeer` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`idOrder`,`idBeer`),
  KEY `Order_Beer_Beer0_FK` (`idBeer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `orderline`
--

INSERT INTO `orderline` (`idOrder`, `idBeer`, `quantity`) VALUES
(1, 1, 2),
(1, 5, 1),
(2, 3, 3),
(2, 7, 2),
(3, 4, 1),
(3, 6, 2),
(4, 2, 3),
(4, 8, 1),
(5, 10, 2),
(5, 12, 1),
(8, 10, 2),
(8, 15, 4),
(12, 10, 1),
(12, 14, 1),
(18, 5, 1),
(18, 18, 1),
(19, 10, 1),
(20, 7, 1),
(20, 18, 1),
(21, 8, 1),
(21, 10, 1),
(22, 9, 1),
(22, 17, 1),
(22, 21, 1),
(31, 3, 1),
(31, 5, 1),
(31, 12, 2),
(32, 1, 1),
(32, 10, 1),
(32, 22, 1),
(33, 5, 1),
(33, 18, 1),
(34, 6, 1),
(34, 13, 1),
(34, 22, 1),
(35, 7, 1),
(35, 12, 1),
(36, 3, 1),
(36, 16, 1),
(37, 8, 1),
(37, 12, 1),
(37, 19, 1),
(38, 6, 1),
(38, 14, 1),
(38, 22, 1),
(40, 63, 3),
(41, 36, 3),
(41, 42, 3),
(41, 64, 3),
(41, 83, 3),
(42, 41, 3),
(42, 42, 3),
(42, 43, 3),
(42, 83, 3),
(43, 15, 1),
(43, 17, 1),
(43, 22, 1),
(44, 8, 1),
(45, 13, 1),
(45, 20, 1),
(48, 1, 6),
(48, 2, 4),
(48, 4, 7),
(48, 17, 2),
(49, 16, 4),
(49, 17, 4),
(50, 6, 1),
(51, 32, 10),
(51, 36, 5),
(51, 43, 5),
(51, 63, 5),
(52, 4, 4),
(52, 16, 3),
(53, 14, 4),
(53, 19, 3),
(54, 4, 6),
(55, 6, 6),
(56, 8, 1),
(57, 1, 12),
(60, 1, 1),
(61, 1, 2),
(62, 1, 5),
(63, 100, 1),
(64, 3, 6),
(64, 6, 4),
(64, 7, 1),
(65, 1, 5),
(65, 3, 12),
(66, 2, 4),
(66, 5, 2),
(66, 8, 2),
(66, 14, 2),
(66, 21, 4),
(67, 13, 3),
(67, 16, 9),
(68, 3, 1),
(68, 7, 1),
(68, 20, 2);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `clientorder`
--
ALTER TABLE `clientorder`
  ADD CONSTRAINT `Order_Client_FK` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`);

--
-- Contraintes pour la table `orderline`
--
ALTER TABLE `orderline`
  ADD CONSTRAINT `Order_Beer_Beer0_FK` FOREIGN KEY (`idBeer`) REFERENCES `beer` (`idBeer`),
  ADD CONSTRAINT `Order_Beer_Order_FK` FOREIGN KEY (`idOrder`) REFERENCES `clientorder` (`idOrder`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
