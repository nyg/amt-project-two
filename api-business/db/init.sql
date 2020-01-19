-- phpMyAdmin SQL Dump
-- version 4.9.4
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Jan 19, 2020 at 10:50 PM
-- Server version: 10.4.11-MariaDB-1:10.4.11+maria~bionic
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `business`
--
CREATE DATABASE IF NOT EXISTS `business` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `business`;

-- --------------------------------------------------------

--
-- Table structure for table `article`
--

CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`id`, `name`, `description`, `price`) VALUES
(1, 'USB Key 16 Go', 'USB QueenPaper 16 Go', 15),
(2, 'NoisyDiscomfort 53', 'Brand new Bouse Headphone ', 421),
(3, 'BlueTouff Bouse speaker', 'Bluetouff or wirefull speaker from Bouse', 120),
(4, 'MacLivre Nooby ', 'Brand new device from Mac', 1400),
(5, 'USB Key 64 Go', 'USB QueenPaper 64 Go', 40),
(7, 'USB Key 128 Go', 'USB QueenPaper 64 Go', 60),
(8, 'USB Key 256 Go', 'USB QueenPaper 256 Go', 80),
(9, 'USB Key 512 Go', 'USB QueenPaper 512 Go', 90);

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `customer_ID` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `list_article` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`customer_ID`, `list_article`) VALUES
('samuel@amt.ch', 3),
('samuel@amt.ch', 4);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `email` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`email`, `first_name`, `last_name`, `address`) VALUES
('nikolaos@amt.ch', 'Nikolaos', 'Garanis', 'On the beach 21\r\nFloride\r\nUSA'),
('sam@amt.ch', 'Sam', 'Mettler', 'A coté du monsieur 23\r\nYverdon\r\nSuisse'),
('samuel@amt.ch', 'Sam', 'Mettler', 'On the beach 23\r\nFloride\r\nUSA');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD KEY `article_id_fk` (`list_article`),
  ADD KEY `customer_id_fk` (`customer_ID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `article`
--
ALTER TABLE `article`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `article_id_fk` FOREIGN KEY (`list_article`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `customer_id_fk` FOREIGN KEY (`customer_ID`) REFERENCES `customer` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
