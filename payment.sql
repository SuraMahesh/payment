-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 04:01 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `payment2`
--


-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `NIC` varchar(20),	
  `patientId` varchar(20) NOT NULL,
  `cardNumber` int,
  `nameOfTheCard` varchar(20),
  `validDate` varchar(20),
  `cvcCode` int,
  `amount` float,
  `Password` varchar(20),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`patientId`, `cardNumber`, `nameOfTheCard`, `validDate`, `cvcCode`, `amount`,`Password`) VALUES
('6268425475V', 'P004', '78451247', 'kaun', '2022-10-4', '021', '7541', 'k111'),
('785841472V', 'P007', '4785412', 'nadun', '2022-05-15', '741', '7985', 'n221'),
('941654519V', 'P011', '8745712', 'kavidu', '2023-07-4', '547', '5000', 'k555'),


--
-- Indexes for dumped tables
--

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`patientId`),
  

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `patientId` varchar(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=P004;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;