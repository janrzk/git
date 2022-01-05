-- phpMyAdmin SQL Dump
-- version 5.0.4deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 17, 2021 at 10:39 AM
-- Server version: 10.5.12-MariaDB-0+deb11u1
-- PHP Version: 7.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pojistovna`
--

-- --------------------------------------------------------

--
-- Table structure for table `osoby`
--

CREATE TABLE `osoby` (
  `id` int(11) NOT NULL,
  `jmeno` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `prijmeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `rodne_cislo` varchar(12) COLLATE utf8_czech_ci NOT NULL,
  `ulice` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `obec` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `psc` varchar(10) COLLATE utf8_czech_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `telefon` varchar(15) COLLATE utf8_czech_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Dumping data for table `osoby`
--

INSERT INTO `osoby` (`id`, `jmeno`, `prijmeni`, `rodne_cislo`, `ulice`, `obec`, `psc`, `email`, `telefon`) VALUES
(1, 'Václav', 'Dvořák', '8110183122', 'Okružní 21', 'Liberec', '41102', 'dvorak_vaclav@centrum.cz', '733255331'),
(2, 'Martina', 'Pokorná', '7955025412', 'V Zahrádkách 105', 'Český Brod', '42008', 'pokorna.martina79@gmail.com', '741341311'),
(3, 'Věra', 'Malá', '9862120308', 'Náměstí Hrdinů 31', 'České Budějovice', '71194', 'malaveruska@atlas.cz', '608221025'),
(4, 'Jan', 'Novotný', '9102030176', 'Za Prádelnou 145', 'Všetaty', '61201', 'novotny1800@hotmail.com', '606221399'),
(5, 'Petr', 'Čermák', '6808250044', 'Generála Pavla 814', 'Brno', '24880', 'petr.cermak@seznam.cz', '741251123');

-- --------------------------------------------------------

--
-- Table structure for table `pojisteni`
--

CREATE TABLE `pojisteni` (
  `id` int(11) NOT NULL,
  `id_osoba` int(11) NOT NULL,
  `cislo_smlouvy` int(11) NOT NULL,
  `typ` varchar(30) COLLATE utf8_czech_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Dumping data for table `pojisteni`
--

INSERT INTO `pojisteni` (`id`, `id_osoba`, `cislo_smlouvy`, `typ`) VALUES
(1, 5, 21410, 'pojištění domácnosti'),
(2, 5, 21748, 'životní pojištění'),
(3, 3, 21034, 'pojištění automobilu'),
(4, 4, 21650, 'úrazové pojištění'),
(5, 2, 21097, 'životní pojištění'),
(6, 2, 21085, 'pojištění nemovitosti');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `osoby`
--
ALTER TABLE `osoby`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pojisteni`
--
ALTER TABLE `pojisteni`
  ADD PRIMARY KEY (`id`),
  ADD KEY `vazba_na_osobu` (`id_osoba`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `osoby`
--
ALTER TABLE `osoby`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `pojisteni`
--
ALTER TABLE `pojisteni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pojisteni`
--
ALTER TABLE `pojisteni`
  ADD CONSTRAINT `vazba_na_osobu` FOREIGN KEY (`id_osoba`) REFERENCES `osoby` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
