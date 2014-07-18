-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 18, 2014 at 05:24 PM
-- Server version: 5.5.37
-- PHP Version: 5.3.10-1ubuntu3.12

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `bank_atm_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE IF NOT EXISTS `accounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `account_no` int(11) NOT NULL,
  `account_type` varchar(45) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `balance` double DEFAULT '0',
  `card_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`id`, `customer_id`, `account_no`, `account_type`, `date_created`, `balance`, `card_id`) VALUES
(3, 18, 0, 'Saving', '2014-07-16 00:00:00', 30599, 1),
(4, 19, 2026116, 'Saving', '2014-07-16 00:00:00', 4600, 2),
(5, 20, 2027247, 'Saving', '2014-07-16 00:00:00', 51399, 3),
(6, 21, 2029743, 'Saving', '2014-07-16 00:00:00', 8248, 4),
(7, 22, 2027554, 'Saving', '2014-07-16 00:00:00', 340, 5),
(8, 23, 2028407, 'Saving', '2014-07-16 00:00:00', 799, 6),
(9, 24, 2029045, 'Saving', '2014-07-16 00:00:00', 1598, 7),
(10, 25, 2024568, 'Saving', '2014-07-17 00:00:00', 800, 8),
(11, 26, 2024445, 'Saving', '2014-07-17 00:00:00', 900, 9),
(12, 27, 2026252, 'Saving', '2014-07-18 00:00:00', 4450, 10),
(13, 28, 2025205, 'Saving', '2014-07-18 00:00:00', 400, 11),
(14, 29, 2024252, 'Checking', '2014-07-18 00:00:00', 6800, 12),
(15, 30, 20210066, 'Checking', '2014-07-18 00:00:00', 6800, 13),
(16, 31, 2024637, 'Saving', '2014-07-18 00:00:00', 2000, 14),
(17, 32, 2021837, 'Saving', '2014-07-18 00:00:00', 2000, 15),
(18, 33, 2026984, 'Saving', '2014-07-18 00:00:00', 5000, 16),
(19, 34, 2029151, 'Saving', '2014-07-18 00:00:00', 5000, 17),
(20, 35, 2028000, 'Saving', '2014-07-18 00:00:00', 4450, 18),
(21, 36, 2024449, 'Saving', '2014-07-18 14:45:34', 0, 0),
(22, 37, 20210905, 'Saving', '2014-07-18 00:00:00', 5000, 20),
(23, 38, 2024943, 'Saving', '2014-07-18 00:00:00', 13000, 21),
(24, 39, 2021901, 'Saving', '2014-07-18 00:00:00', 200, 22),
(25, 40, 2023164, 'Saving', '2014-07-18 00:00:00', 11000, 23),
(26, 41, 20210532, 'Saving', '2014-07-18 00:00:00', 1000, 24),
(27, 42, 2027832, 'Saving', '2014-07-18 00:00:00', 2000, 25),
(28, 43, 20210783, 'Saving', '2014-07-18 00:00:00', 1150, 26),
(29, 44, 20210118, 'Saving', '2014-07-18 00:00:00', 10000, 27);

-- --------------------------------------------------------

--
-- Table structure for table `cards`
--

CREATE TABLE IF NOT EXISTS `cards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_number` int(12) NOT NULL,
  `pin_code` varchar(45) NOT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Dumping data for table `cards`
--

INSERT INTO `cards` (`id`, `card_number`, `pin_code`, `customer_id`) VALUES
(1, 1000, '1000', 18),
(2, 20250424, '759', 19),
(3, 20262071, '805', 20),
(4, 20255129, '623', 21),
(5, 20297885, '523', 22),
(6, 20217472, '921', 23),
(7, 202101603, '986', 24),
(8, 20210587, '665', 25),
(9, 20270888, '920', 26),
(10, 20283061, '958', 27),
(11, 20236081, '894', 28),
(12, 20260655, '202', 29),
(13, 20284494, '1085', 30),
(14, 20294082, '263', 31),
(15, 20250482, '1027', 32),
(16, 20264877, '100', 33),
(17, 202106416, '240', 34),
(18, 20276455, '114', 35),
(20, 202108985, '461', 37),
(21, 20216359, '163', 38),
(22, 20269897, '491', 39),
(23, 20267365, '394', 40),
(24, 20268091, '407', 41),
(25, 20249989, '507', 42),
(26, 20275730, '787', 43),
(27, 20293542, '796', 44);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE IF NOT EXISTS `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `gender` enum('male','female') DEFAULT 'male',
  `date_of_birth` date DEFAULT NULL,
  `national_id` varchar(100) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=45 ;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `first_name`, `last_name`, `address`, `phone`, `gender`, `date_of_birth`, `national_id`, `email`) VALUES
(18, 'Tony', 'Ben', 'Washington', '2324223', 'male', NULL, NULL, 'tony@gmail.com'),
(19, 'Ebrima', 'Tunkara', 'NY', '888433', 'male', NULL, NULL, 'ebtunkara@gmail.com'),
(20, 'James', 'Berner', 'Washington', '77777', 'male', NULL, NULL, 'james@gmail.com'),
(21, 'Mariam', 'Helen', 'Washington', '3888343', 'male', NULL, NULL, 'mhelen@gmail.com'),
(22, 'Mariam', 'Helen', 'Seatle', '4543555543', 'male', NULL, NULL, 'mhelen@gmail.com'),
(23, 'Mariam A', 'Helen', 'Washington', '23234223', 'male', NULL, NULL, 'mhelen@gmail.com'),
(24, 'Mariam A', 'Helen', 'Washington', '23234223', 'male', NULL, NULL, 'mhelen@gmail.com'),
(25, 'Lamin', 'Kim', 'Dakar', '35334', 'male', NULL, NULL, 'test@gll.com'),
(26, 'James ', 'Bene', 'Washington', '343', 'male', NULL, NULL, 'ebtunkara@gmail.com'),
(27, 'Tuntu', 'Tuntong', 'NY', '2147558414', 'male', NULL, NULL, 'the.programmers.test@gmail.com'),
(28, 'Ousman', 'Bah', 'Banjul', '6466435435', 'male', NULL, NULL, 'ebtunkara@hotmail.com'),
(29, 'Kebba', 'Jaiteh', 'Bundung', '6466435435', 'male', NULL, NULL, 'ebrimatunkara@gmail.com'),
(30, 'Kebba', 'Jaiteh', 'Bundung', '6466435435', 'male', NULL, NULL, 'ebrimatunkara@gmail.com'),
(31, 'Moses', 'Camara', 'Fairfield', '6466435435', 'male', NULL, NULL, 'ebtunkara@gmail.com'),
(32, 'Moses', 'Camara', 'Fairfield', '6466435435', 'male', NULL, NULL, 'ebtunkara@gmail.com'),
(33, 'Henry', 'Lowe', 'Washington', '6466435435', 'male', NULL, NULL, 'ebrimatunkara@gmail.com'),
(34, 'Henry', 'Lowe', 'Washington', '6466435435', 'male', NULL, NULL, 'ebrimatunkara@gmail.com'),
(35, 'John', 'Menes', 'Washington', '6466435435', 'male', NULL, NULL, 'ebrimatunkara@gmail.com'),
(36, 'Isa', 'Fatty', 'Washington', '6466435435', 'male', NULL, NULL, 'ebrimatunkara@gmail.com'),
(37, 'David', 'Njie', 'Washington', '6466435435', 'male', NULL, NULL, 'ebrimatunkara@gmail.com'),
(38, 'Samuel', 'Fred', 'Atlanta', '6466435435', 'male', NULL, NULL, 'ebrimatunkara@gmail.com'),
(39, 'Andrew', 'Johnson', 'Washington', '6466435435', 'male', NULL, NULL, 'ebrimatunkara@gmail.com'),
(40, 'Bello', 'Barry', 'Washington', '6466435435', 'male', NULL, NULL, 'ebrimatunkara@gmail.com'),
(41, 'Carine', 'Meelon', 'Atlanta', '6466435435', 'male', NULL, NULL, 'ebrimatunkara@gmail.com'),
(42, 'Denis', 'Lee', 'Washington', '6466435435', 'male', NULL, NULL, 'ebrimatunkara@gmail.com'),
(43, 'Ellen', 'Den', 'Washington', '2147558414', 'male', NULL, NULL, 'ebrimatunkara@gmail.com'),
(44, 'Jack', 'Marley', 'Washington', '2147558414', 'male', NULL, NULL, 'ebrimatunkara@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `sessions`
--

CREATE TABLE IF NOT EXISTS `sessions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `session` longtext,
  `card_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=85 ;

--
-- Dumping data for table `sessions`
--

INSERT INTO `sessions` (`id`, `user_id`, `customer_id`, `timestamp`, `session`, `card_id`) VALUES
(1, 14, NULL, 1405602802029, NULL, NULL),
(2, 14, NULL, 1405602980754, NULL, NULL),
(3, 14, NULL, 1405602980754, '98C4A2E129C626330B82401A1C34C51E', NULL),
(4, 14, NULL, 1405603604101, '3BFF705B4EACF7B747702EEC9B19A6CD', NULL),
(5, 14, NULL, 1405603656957, '00B77A5C91E89A346A0BDD95BCC9D3EE', NULL),
(6, 14, NULL, 1405606472697, '485CD9CE17909CD80F90D44FECAADED5', NULL),
(7, 14, NULL, 1405615346123, 'FF7FB882B3E1B00A6A96B2764186B7B8', NULL),
(8, 14, NULL, 1405615346123, 'FF7FB882B3E1B00A6A96B2764186B7B8', NULL),
(9, 14, NULL, 1405615346123, 'FF7FB882B3E1B00A6A96B2764186B7B8', NULL),
(10, 14, NULL, 1405617035222, 'DB47F383A685FFA6CD612B6B6165E869', NULL),
(11, 14, NULL, 1405617064038, '8E7841A66E8CC5A9C7100AB8E3156401', NULL),
(12, 14, NULL, 1405617064038, '8E7841A66E8CC5A9C7100AB8E3156401', NULL),
(13, 14, NULL, 1405617108328, '4C2CDDF0AFD862F7B9C65622C100FEC1', NULL),
(14, 14, NULL, 1405618513418, '39937CB8ED34110046FF6240F3E2E155', NULL),
(15, NULL, 26, 1405619574701, '4F8A1B2873156482005AB06B91C8BB42', 9),
(16, NULL, 26, 1405619580303, '78FAE8B026B03C72271B23AC8FBDCB21', 9),
(17, NULL, 26, 1405619638453, '92DBB4446159AC6512286415F6C2D178', 9),
(18, 14, NULL, 1405621555628, 'F83FC6FB820E90DE8190870C7A5EE78D', NULL),
(19, 14, NULL, 1405634678320, '1745567A8D14524A613E8339AB0A22B6', NULL),
(20, 14, NULL, 1405637238222, '5C1F3FEF802AC6BE2DC20C2C3D74B6B0', NULL),
(21, 14, NULL, 1405637304228, 'CA0DC4B9CAFAB042FFED44AEE6C2C1FA', NULL),
(22, 14, NULL, 1405637337940, 'DA0EBE76F45B48FBA310FC337313CA31', NULL),
(23, 14, NULL, 1405637451007, '893260D2367AFCA34598093BA856E1EA', NULL),
(24, 14, NULL, 1405637495630, '6D5E816D2F3E1BF076AB18F1FA200C66', NULL),
(25, 14, NULL, 1405637495630, '6D5E816D2F3E1BF076AB18F1FA200C66', NULL),
(26, 14, NULL, 1405640419237, 'F26C48513873E9FB0E83EAF59A5551DC', NULL),
(27, 14, NULL, 1405640419237, 'F26C48513873E9FB0E83EAF59A5551DC', NULL),
(28, 14, NULL, 1405640419237, 'F26C48513873E9FB0E83EAF59A5551DC', NULL),
(29, 14, NULL, 1405643388209, '4B794F0D6F34A91BBC4789EAA0A01587', NULL),
(30, NULL, 27, 1405643591529, '7D8FB093E088ECA1F157422EF27F57DE', 10),
(31, 14, NULL, 1405647685029, 'D9D30952B6A29227DA2FF459FF8BE025', NULL),
(32, NULL, 27, 1405650029385, 'AF28772C56FBBCD96113D945637992ED', 10),
(33, NULL, 27, 1405653713837, '722904DA211D8D65579D1FE4A4D69457', 10),
(34, NULL, 27, 1405656118513, '38B2B7E3DB2D2580451B6DDF0A9E9F8D', 10),
(35, 14, NULL, 1405656383101, '0F444BE388E703FD45F9581C064ED00D', NULL),
(36, 14, NULL, 1405656383101, '0F444BE388E703FD45F9581C064ED00D', NULL),
(37, 14, NULL, 1405656383101, '0F444BE388E703FD45F9581C064ED00D', NULL),
(38, 14, NULL, 1405659109255, 'D0BF8F62BCBEB86D6B61A96E9C64ACB1', NULL),
(39, 14, NULL, 1405659404599, 'AEC9F3F01C83345C54FE458B636DF029', NULL),
(40, 14, NULL, 1405659404599, 'AEC9F3F01C83345C54FE458B636DF029', NULL),
(41, 14, NULL, 1405659404599, 'AEC9F3F01C83345C54FE458B636DF029', NULL),
(42, 14, NULL, 1405659404599, 'AEC9F3F01C83345C54FE458B636DF029', NULL),
(43, 14, NULL, 1405659404599, 'AEC9F3F01C83345C54FE458B636DF029', NULL),
(44, 14, NULL, 1405659404599, 'AEC9F3F01C83345C54FE458B636DF029', NULL),
(45, 14, NULL, 1405659404599, 'AEC9F3F01C83345C54FE458B636DF029', NULL),
(46, 14, NULL, 1405659404599, 'AEC9F3F01C83345C54FE458B636DF029', NULL),
(47, 14, NULL, 1405659404599, 'AEC9F3F01C83345C54FE458B636DF029', NULL),
(48, 14, NULL, 1405659404599, 'AEC9F3F01C83345C54FE458B636DF029', NULL),
(49, 14, NULL, 1405659404599, 'AEC9F3F01C83345C54FE458B636DF029', NULL),
(50, 14, NULL, 1405659404599, 'AEC9F3F01C83345C54FE458B636DF029', NULL),
(51, 15, NULL, 1405664025960, '5A58979079D34FA11A208BA59FBC8C2B', NULL),
(52, 15, NULL, 1405664025960, '5A58979079D34FA11A208BA59FBC8C2B', NULL),
(53, 14, NULL, 1405671574723, '563D6ECA4414F1AF430FF9A364042F1A', NULL),
(54, 14, NULL, 1405671574723, '563D6ECA4414F1AF430FF9A364042F1A', NULL),
(55, 14, NULL, 1405675166713, 'AFE26EA517A60542803F0512E220BF04', NULL),
(56, 14, NULL, 1405687805955, '80E0D4120D993F26B18F7F59F1A8E76F', NULL),
(57, 14, NULL, 1405687805955, '80E0D4120D993F26B18F7F59F1A8E76F', NULL),
(58, 14, NULL, 1405688419387, '9FCC5C33C63011AD2EBF291FC882CF9B', NULL),
(59, 14, NULL, 1405688419387, '9FCC5C33C63011AD2EBF291FC882CF9B', NULL),
(60, 14, NULL, 1405688419387, '9FCC5C33C63011AD2EBF291FC882CF9B', NULL),
(61, 14, NULL, 1405688419387, '9FCC5C33C63011AD2EBF291FC882CF9B', NULL),
(62, 14, NULL, 1405688419387, '9FCC5C33C63011AD2EBF291FC882CF9B', NULL),
(63, 18, NULL, 1405690043968, 'C0A5791F8152EDC4906797A1B1656577', NULL),
(64, NULL, 35, 1405690347471, 'FAF4A4460C252D80EB2F1B533D840B60', 18),
(65, NULL, 35, 1405690858616, '8D4B7B4CFAC668096552B2527039AF89', 18),
(66, NULL, 35, 1405690898018, '3284402DCF52715C808F976CB7E241BE', 18),
(67, 14, NULL, 1405694603907, '050D5275E1AB7124C3D859F6726493D8', NULL),
(68, 14, NULL, 1405694603907, '050D5275E1AB7124C3D859F6726493D8', NULL),
(69, 14, NULL, 1405697022312, '6AEF3A04923A98884D30193494655C8D', NULL),
(70, 14, NULL, 1405697022312, '6AEF3A04923A98884D30193494655C8D', NULL),
(71, 14, NULL, 1405697022312, '6AEF3A04923A98884D30193494655C8D', NULL),
(72, 14, NULL, 1405697022312, '6AEF3A04923A98884D30193494655C8D', NULL),
(73, 14, NULL, 1405697022312, '6AEF3A04923A98884D30193494655C8D', NULL),
(74, 14, NULL, 1405697022312, '6AEF3A04923A98884D30193494655C8D', NULL),
(75, 14, NULL, 1405697022312, '6AEF3A04923A98884D30193494655C8D', NULL),
(76, NULL, 44, 1405697022312, '6AEF3A04923A98884D30193494655C8D', 27),
(77, NULL, 44, 1405700245525, 'ED7F80A90F4C6302534D519E9186CB10', 27),
(78, NULL, 44, 1405700280512, '527A2C9BADCEA6EF528B1504227BCC18', 27),
(79, 14, NULL, 1405700280512, '527A2C9BADCEA6EF528B1504227BCC18', NULL),
(80, 19, NULL, 1405701263183, '27537AFDE2586EE5746ED8B34ACDD023', NULL),
(81, 14, NULL, 1405701263183, '27537AFDE2586EE5746ED8B34ACDD023', NULL),
(82, 14, NULL, 1405701263183, '27537AFDE2586EE5746ED8B34ACDD023', NULL),
(83, 14, NULL, 1405701263183, '27537AFDE2586EE5746ED8B34ACDD023', NULL),
(84, 14, NULL, 1405701263183, '27537AFDE2586EE5746ED8B34ACDD023', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE IF NOT EXISTS `transactions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,0) NOT NULL,
  `account_id` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  `card_id` int(11) DEFAULT NULL,
  `type` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=40 ;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `amount`, `account_id`, `date`, `user_id`, `card_id`, `type`) VALUES
(2, 400, 10, '2014-07-17 16:44:36', 14, NULL, 'Deposit'),
(3, 700, 11, '2014-07-17 16:54:30', 14, NULL, 'Deposit'),
(4, 200, 4, '2014-07-17 23:53:18', 14, NULL, 'Deposit'),
(5, 232, 5, '2014-07-17 23:54:26', 14, NULL, 'Deposit'),
(6, 268, 5, '2014-07-17 23:59:50', 14, NULL, 'Deposit'),
(7, 200, 5, '2014-07-18 00:00:41', 14, NULL, 'Deposit'),
(8, 300, 5, '2014-07-18 00:01:50', 14, NULL, 'Deposit'),
(9, 399, 5, '2014-07-18 00:01:57', 14, NULL, 'Deposit'),
(10, 399, 3, '2014-07-18 00:02:05', 14, NULL, 'Deposit'),
(11, 200, 4, '2014-07-18 00:03:15', 14, NULL, 'Deposit'),
(12, 200, 4, '2014-07-18 00:14:25', 14, NULL, 'Deposit'),
(13, 3000, 4, '2014-07-18 00:14:48', 14, NULL, 'Deposit'),
(14, 30000, 3, '2014-07-18 00:14:55', 14, NULL, 'Deposit'),
(15, 7999, 6, '2014-07-18 00:15:13', 14, NULL, 'Deposit'),
(16, 200, 11, '2014-07-18 00:15:37', 14, NULL, 'Deposit'),
(17, 4500, 12, '2014-07-18 00:33:01', 14, NULL, 'Deposit'),
(18, 50, 12, '2014-07-18 04:03:06', NULL, 27, 'WITHDRAW'),
(19, 400, 13, '2014-07-18 04:10:56', 14, NULL, 'Deposit'),
(20, 6800, 14, '2014-07-18 04:18:32', 14, NULL, 'Deposit'),
(21, 6800, 15, '2014-07-18 04:18:33', 14, NULL, 'Deposit'),
(22, 2000, 16, '2014-07-18 13:09:18', 14, NULL, 'DEPOSIT'),
(23, 2000, 17, '2014-07-18 13:09:18', 14, NULL, 'DEPOSIT'),
(24, 5000, 18, '2014-07-18 13:14:19', 14, NULL, 'DEPOSIT'),
(25, 5000, 19, '2014-07-18 13:14:20', 14, NULL, 'DEPOSIT'),
(26, 4500, 20, '2014-07-18 13:21:06', 14, NULL, 'DEPOSIT'),
(27, 50, 20, '2014-07-18 13:33:26', NULL, 35, 'WITHDRAW'),
(28, 5000, 22, '2014-07-18 14:52:10', 14, NULL, 'DEPOSIT'),
(29, 13000, 23, '2014-07-18 15:29:07', 14, NULL, 'DEPOSIT'),
(30, 200, 24, '2014-07-18 15:40:30', 14, NULL, 'DEPOSIT'),
(31, 11000, 25, '2014-07-18 15:50:06', 14, NULL, 'DEPOSIT'),
(32, 1000, 26, '2014-07-18 15:57:54', 14, NULL, 'DEPOSIT'),
(33, 2000, 27, '2014-07-18 16:03:17', 14, NULL, 'DEPOSIT'),
(34, 1150, 28, '2014-07-18 16:08:09', 14, NULL, 'DEPOSIT'),
(35, 4500, 29, '2014-07-18 16:11:42', 14, NULL, 'DEPOSIT'),
(36, 50, 29, '2014-07-18 16:14:13', NULL, 44, 'WITHDRAW'),
(37, 2000, 29, '2014-07-18 16:35:37', 19, NULL, 'DEPOSIT'),
(38, 3000, 29, '2014-07-18 16:37:05', 19, NULL, 'DEPOSIT'),
(39, 550, 29, '2014-07-18 16:37:26', 19, NULL, 'DEPOSIT');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `first_name`, `last_name`) VALUES
(1, 'admin', 'admin1', 'John', 'Mendy'),
(8, 'Ebrima', 'Tunkara', 'eTunkara', 'tunkara'),
(9, 'tunk1', 'tunkara', 'Ebrima ', 'Tunkara'),
(10, 'admin2', 'test1', 'Firdus', 'Hassan'),
(11, 'kjohnson', 'kjohnson1', 'Kene', 'Johnson'),
(12, 'bkofi', '6b602032941b36e0ecbe074e6aac35ce', 'Ben', 'Kofi'),
(13, 'admin3', '21232f297a57a5a743894a0e4a801fc3', 'Ebrima', 'Tunkara'),
(14, 'admin4', 'fc1ebc848e31e0a68e868432225e3c82', 'Ebrima', 'Tunkara'),
(15, 'kjenes', '9d74a2add8575e8616ca09641bbe1466', 'Karine', 'Jenes'),
(18, 'wcamara', '3295b518cf109e8d819fbe8485d22cb8', 'Walley', 'Camara'),
(19, 'fara1215', '58587d8953d14e0294e8b293c1a68e02', 'Firdaus', 'Mustapha');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE IF NOT EXISTS `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`id`, `user_id`, `role`) VALUES
(1, 7, 'administrator'),
(2, 8, 'administrator'),
(3, 9, 'account manager'),
(4, 10, 'administrator'),
(5, 11, 'administrator'),
(6, 12, 'administrator'),
(7, 13, 'administrator'),
(8, 14, 'administrator'),
(9, 15, 'account manager'),
(10, 16, 'account manager'),
(11, 17, 'account manager'),
(12, 18, 'account manager'),
(13, 19, 'account manager');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
