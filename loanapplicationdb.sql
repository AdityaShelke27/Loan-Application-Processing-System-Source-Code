-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 19, 2023 at 10:59 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `loanapplicationdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `applications`
--

CREATE TABLE `applications` (
  `application_id` int(11) NOT NULL,
  `applicant_name` varchar(30) NOT NULL,
  `loan_type` varchar(10) NOT NULL,
  `poi_type` varchar(10) NOT NULL,
  `poi_id` varchar(15) NOT NULL,
  `poa_type` varchar(20) NOT NULL,
  `address` varchar(500) NOT NULL,
  `loan_product` varchar(20) NOT NULL,
  `loan_amount` int(12) NOT NULL,
  `occupation` varchar(20) NOT NULL,
  `status` int(1) NOT NULL,
  `officer_id` varchar(20) NOT NULL,
  `Notes` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `applications`
--

INSERT INTO `applications` (`application_id`, `applicant_name`, `loan_type`, `poi_type`, `poi_id`, `poa_type`, `address`, `loan_product`, `loan_amount`, `occupation`, `status`, `officer_id`, `Notes`) VALUES
(1, 'Aditya Shelke', 'Secure', 'PAN', 'ADFRY1234H', 'Current Address', 'visdnblksjbtr', 'Personal Loan', 999999, 'Salaried', 1, 'Aditya123', 'yjdtymdfyu'),
(54, 'safragerg', 'Secure', 'AADHAR', '123456789012', 'Current Address', 'dfnjdytjdt', 'Personal Loan', 123456, 'Salaried', 2, 'Aditya123', 'yjdtymdfyuhgmh'),
(55, '[value-2]', '[value-3]', '[value-4]', '[value-5]', '[value-6]', '[value-7]', '[value-8]', 0, '[value-10]', 1, '[value-12]', '[value-13]'),
(56, 'cthfujtgvyj', 'Secure', 'AADHAR', '123456789012', 'Current Address', 'vyikbvlj', 'Personal Loan', 123465, 'Salaried', 2, 'Aditya123', 'gjvgkio'),
(57, 'byuogbyul', 'Secure', 'AADHAR', '123456789012', 'Current Address', 'njlbiublo', 'Personal Loan', 123456, 'Salaried', 2, 'Aditya123', 'Not Applicable'),
(58, 'Amit', 'Secure', 'AADHAR', '123456789012', 'Current Address', 'Ahmedabad', 'Business Loan', 340000, 'Salaried', 1, 'Aditya123', 'cyygvyik'),
(59, 'regfer', 'Secure', 'AADHAR', '123456789012', 'Current Address', 'fgvergerg', 'Personal Loan', 23000, 'Salaried', 2, 'Aditya123', 'Not Applicable'),
(60, 'regfer', 'Secure', 'AADHAR', '123456789012', 'Current Address', 'fgvergerg', 'Personal Loan', 23000, 'Salaried', 2, 'Aditya123', 'Not Applicable'),
(61, 'Jay', 'Unsecure', 'AADHAR', '123456789012', 'Current Address', 'Mumbai', 'Personal Loan', 25000, 'Salaried', 0, 'Aditya123', NULL),
(62, 'Sachin', 'Secure', 'AADHAR', '123456789012', 'Current Address', 'Pune', 'Student Loan', 30000, 'Salaried', 0, 'Aditya123', NULL),
(63, 'Amit', 'Secure', 'AADHAR', '123456789012', 'Current Address', 'Gujarat', 'Personal Loan', 35000, 'Salaried', 0, 'Aditya123', NULL),
(64, 'hgstrhrt', 'Secure', 'AADHAR', '123456789012', 'Current Address', 'rgergs', 'Personal Loan', 23900, 'Salaried', 0, 'Aditya123', NULL),
(65, 'tshtrhr', 'Secure', 'AADHAR', '123456789012', 'Current Address', 'dfarfserg', 'Personal Loan', 12000, 'Salaried', 0, 'Aditya123', NULL),
(66, 'efaefer', 'Secure', 'OTHER', 'rewgwsr', 'Current Address', 'ergwes', 'Personal Loan', 12000, 'Salaried', 0, 'Aditya123', NULL),
(67, 'sghbdrth', 'Secure', 'OTHER', '2343', 'Current Address', 'fgsrgrtg', 'Personal Loan', 12700, 'Salaried', 0, 'Aditya123', NULL),
(68, 'efreafe', 'Secure', 'OTHER', '23423', 'Current Address', 'fdgvsgr', 'Personal Loan', 12000, 'Salaried', 0, 'Aditya123', NULL),
(69, 'ghstrdh', 'Secure', 'OTHER', 'e4te43', 'Current Address', 'ryjyu', 'Personal Loan', 45000, 'Salaried', 0, 'Aditya123', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `USER_ID` varchar(20) NOT NULL,
  `USER_NAME` varchar(20) NOT NULL,
  `USER_PASSWORD` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`USER_ID`, `USER_NAME`, `USER_PASSWORD`) VALUES
('abc123', 'ABCD', '123'),
('Aditya123', 'Aditya Shelke', '123'),
('AS123', 'Aya Se', 'Somg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `applications`
--
ALTER TABLE `applications`
  ADD PRIMARY KEY (`application_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`USER_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `applications`
--
ALTER TABLE `applications`
  MODIFY `application_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
