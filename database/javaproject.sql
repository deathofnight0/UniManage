-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 03, 2024 at 10:15 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javaproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `course` varchar(20) NOT NULL,
  `semester` int(11) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`id`, `name`, `course`, `semester`, `subject`, `date`, `status`) VALUES
(100, 'Manik Aggarwal', 'BTECH', 1, 'Engineering Maths-1', '2023-07-21', 'present'),
(101, 'akshay patil', 'BTECH', 1, 'Engineering Maths-1', '2023-07-21', 'absent'),
(104, 'Chirag Bansal', 'BTECH', 1, 'Engineering Maths-1', '2023-07-21', 'present'),
(105, 'Akshat Gupta', 'BTECH', 1, 'Engineering Maths-1', '2023-07-21', 'present'),
(106, 'Simran Sinha', 'BTECH', 1, 'Engineering Maths-1', '2023-07-21', 'present'),
(107, 'Vivek Choudhary', 'BTECH', 1, 'Engineering Maths-1', '2023-07-21', 'absent'),
(110, 'Muskan Gupta', 'BTECH', 1, 'Engineering Maths-1', '2023-07-21', 'present'),
(112, 'Angad  Singh', 'BTECH', 1, 'Engineering Maths-1', '2023-07-21', 'present'),
(115, 'Mayank Aggarwal', 'BTECH', 1, 'Engineering Maths-1', '2023-07-21', 'present'),
(100, 'Manik Aggarwal', 'BTECH', 1, 'Engineering Physics', '2023-07-21', 'present'),
(100, 'Manik Aggarwal', 'BTECH', 1, 'Engineering Maths-1', '2023-07-20', 'absent'),
(100, 'Manik Aggarwal', 'BTECH', 1, 'Engineering Maths-1', '2023-07-19', 'present'),
(103, 'Anushka Arora', 'BTECH', 2, 'Engineering Maths-2', '2023-07-21', 'absent'),
(114, 'Shivani Kalra', 'BTECH', 2, 'Engineering Maths-2', '2023-07-21', 'present'),
(103, 'Anushka Arora', 'BTECH', 2, 'Engineering Chemistry', '2023-07-21', 'present'),
(114, 'Shivani Kalra', 'BTECH', 2, 'Engineering Chemistry', '2023-07-21', 'absent');

-- --------------------------------------------------------

--
-- Table structure for table `feerecord`
--

CREATE TABLE `feerecord` (
  `receipt_no` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `student_id` int(11) NOT NULL,
  `course` varchar(20) NOT NULL,
  `semester` int(11) NOT NULL,
  `paid` int(11) NOT NULL,
  `balance` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `feerecord`
--

INSERT INTO `feerecord` (`receipt_no`, `date`, `student_id`, `course`, `semester`, `paid`, `balance`) VALUES
(0, '2023-07-18', 100, 'BTECH', 1, 0, 80000),
(0, '2023-07-19', 101, 'BTECH', 1, 0, 80000),
(0, '2023-07-19', 103, 'BTECH', 2, 0, 85000),
(0, '2023-07-19', 104, 'BTECH', 1, 0, 80000),
(0, '2023-07-19', 105, 'BTECH', 1, 0, 80000),
(0, '2023-07-19', 106, 'BTECH', 1, 0, 80000),
(0, '2023-07-19', 107, 'BTECH', 1, 0, 80000),
(0, '2023-07-19', 109, 'BCA', 1, 0, 62000),
(0, '2023-07-19', 110, 'BTECH', 1, 0, 80000),
(0, '2023-07-19', 111, 'BCA', 1, 0, 62000),
(0, '2023-07-19', 112, 'BTECH', 1, 0, 80000),
(0, '2023-07-19', 113, 'BCA', 1, 0, 62000),
(0, '2023-07-19', 114, 'BTECH', 2, 0, 85000),
(0, '2023-07-20', 116, 'BCA', 1, 0, 62000),
(1, '2023-07-20', 115, 'BTECH', 1, 60000, 20000),
(2, '2023-07-21', 102, 'BCA', 1, 30000, 32000),
(3, '2023-07-21', 108, 'BCA', 1, 62000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `fees`
--

CREATE TABLE `fees` (
  `sr_no` int(11) NOT NULL,
  `course` varchar(10) NOT NULL,
  `semester` int(11) NOT NULL,
  `fees` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fees`
--

INSERT INTO `fees` (`sr_no`, `course`, `semester`, `fees`) VALUES
(1, 'BCA', 1, 62000),
(2, 'BCA', 2, 65000),
(3, 'BCA', 3, 70000),
(4, 'BCA', 4, 75000),
(5, 'BCA', 5, 74000),
(6, 'BCA', 6, 85000),
(7, 'BTECH', 1, 75000),
(8, 'BTECH', 2, 85000),
(9, 'BTECH', 3, 82000),
(10, 'BTECH', 4, 87000),
(11, 'BTECH', 5, 84000),
(12, 'BTECH', 6, 89000),
(13, 'BTECH', 7, 88000),
(14, 'BTECH', 8, 95000);

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE `result` (
  `sr_no` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `course` varchar(10) NOT NULL,
  `semester` int(11) NOT NULL,
  `sub1` int(11) NOT NULL,
  `sub2` int(11) NOT NULL,
  `sub3` int(11) NOT NULL,
  `sub4` int(11) NOT NULL,
  `sub5` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`sr_no`, `student_id`, `name`, `course`, `semester`, `sub1`, `sub2`, `sub3`, `sub4`, `sub5`) VALUES
(1, 101, 'akshay patil', 'BTECH', 1, 78, 56, 90, 82, 80),
(2, 100, 'Manik Aggarwal', 'BTECH', 1, 67, 54, 78, 98, 45);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `admission_date` date NOT NULL,
  `name` varchar(25) NOT NULL,
  `email` varchar(25) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `dob` date NOT NULL,
  `course` varchar(10) NOT NULL,
  `semester` int(11) NOT NULL,
  `address` varchar(50) NOT NULL,
  `pincode` int(11) NOT NULL,
  `city` varchar(25) NOT NULL,
  `state` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `admission_date`, `name`, `email`, `gender`, `phone`, `dob`, `course`, `semester`, `address`, `pincode`, `city`, `state`) VALUES
(100, '2023-07-18', 'Manik Aggarwal', 'manikagg23@gmail.com', 'Male', '9547584628', '2005-02-24', 'BTECH', 1, '69A Model town Agra', 282001, 'Agra', 'Uttar Pradesh'),
(101, '2023-07-19', 'akshay patil', 'akshay76@gmail.com', 'Male', '9761234065', '2025-10-07', 'BTECH', 1, 'C-44A shivaji park', 125050, 'Fatehabad', 'Haryana'),
(102, '2023-07-19', 'Rakshit Aggarwal', 'rakshitagg07@gmail.com', 'Male', '9842387464', '2024-09-03', 'BCA', 1, '67 Model town', 132103, 'Panipat', 'Haryana'),
(103, '2023-07-19', 'Anushka Arora', 'anushkaa22@gmail.com', 'Female', '7866534290', '2005-01-12', 'BTECH', 2, '66D Vatika Apartments', 303007, 'Jaipur', 'Rajasthan'),
(104, '2023-07-19', 'Chirag Bansal', 'chiragban76@gmail.com', 'Male', '7892367428', '2004-06-07', 'BTECH', 1, '112A Model Town', 125050, 'Fatehabad', 'Haryana'),
(105, '2023-07-19', 'Akshat Gupta', 'akshat789@gmail.com', 'Male', '7845234958', '2004-09-15', 'BTECH', 1, '56 malika nagar', 282003, 'Agra', 'Uttar Pradesh'),
(106, '2023-07-19', 'Simran Sinha', 'simrunn56@gmail.com', 'Female', '8743856271', '2004-10-10', 'BTECH', 1, '23B Model Town', 125050, 'Fatehabad', 'Haryana'),
(107, '2023-07-19', 'Vivek Choudhary', 'vivekk23@hotmail.com', 'Male', '7823712958', '2005-03-27', 'BTECH', 1, '96C Shivaji park', 110026, 'West Delhi', 'Delhi'),
(108, '2023-07-19', 'Karthik Jain', 'karthikjain2004@gmail.com', 'Male', '7829056285', '2003-04-20', 'BCA', 1, '89 District Chowk', 110056, 'North West Delhi', 'Delhi'),
(109, '2023-07-19', 'Yashika Aggarwal', 'yashika28@yahoo.com', 'Female', '7856290528', '2004-09-20', 'BCA', 1, '65 Avenue Street', 110020, 'South Delhi', 'Delhi'),
(110, '2023-07-19', 'Muskan Gupta', 'muskan78@gmail.com', 'Female', '8965278205', '2025-05-25', 'BTECH', 1, '26A keshogiri', 500005, 'K.V.Rangareddy', 'Telangana'),
(111, '2023-07-19', 'Anuv Jain', 'anuvjain@gmail.com', 'Male', '9255555106', '1994-03-12', 'BCA', 1, '56B DLF City', 122010, 'Gurgaon', 'Haryana'),
(112, '2023-07-19', 'Angad  Singh', 'angadsingh56@gmail.com', 'Male', '7620523968', '2004-10-02', 'BTECH', 1, 'D-89A shivaji park	', 110026, 'West Delhi', 'Delhi'),
(113, '2023-07-19', 'ankit kaswan', 'ankit89@hotmail.com', 'Male', '8920450628', '2005-05-10', 'BCA', 1, '12A Model Town', 125050, 'Fatehabad', 'Haryana'),
(114, '2023-07-19', 'Shivani Kalra', 'shivaani82@yahoo.com', 'Female', '7620876402', '2004-12-10', 'BTECH', 2, '202 street avenue	', 110020, 'South Delhi', 'Delhi'),
(115, '2023-07-19', 'Mayank Aggarwal', 'mayank12@gmail.com', 'Male', '7820320484', '2023-10-05', 'BTECH', 1, '100 street avenue	', 110020, 'South Delhi', 'Delhi'),
(116, '2023-07-20', 'Karthikay Jain', 'karthikay67@gmail.com', 'Male', '8940320574', '2005-08-20', 'BCA', 1, '123 Street Avenue', 110020, 'South Delhi', 'Delhi');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `sr_no` int(11) NOT NULL,
  `category` varchar(20) NOT NULL,
  `course` varchar(10) NOT NULL,
  `semester` int(11) NOT NULL,
  `sub_code` varchar(10) NOT NULL,
  `sub_name` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`sr_no`, `category`, `course`, `semester`, `sub_code`, `sub_name`) VALUES
(1, 'Undergrad', 'BTECH', 1, 'BTECH101', 'Engineering Maths-1'),
(2, 'Undergrad', 'BTECH', 1, 'BTECH102', 'Engineering Physics'),
(3, 'Undergrad', 'BTECH', 1, 'BTECH103', 'Civil Engineering'),
(4, 'Undergrad', 'BTECH', 1, 'BTECH104', 'Basic Electronics'),
(5, 'Undergrad', 'BTECH', 1, 'BTECH105', 'Engineering Graphics'),
(6, 'Undergrad', 'BTECH', 2, 'BTECH201', 'Engineering Maths-2'),
(7, 'Undergrad', 'BTECH', 2, 'BTECH202', 'Engineering Chemistry'),
(8, 'Undergrad', 'BTECH', 2, 'BTECH203', 'Basic Electrical'),
(9, 'Undergrad', 'BTECH', 2, 'BTECH204', 'Mechanical Engineering'),
(10, 'Undergrad', 'BTECH', 2, 'BTECH205', 'Communication in English'),
(11, 'Undergrad', 'BTECH', 3, 'BTECH301', 'Engineering Maths-3'),
(12, 'Undergrad', 'BTECH', 3, 'BTECH302', 'Data Communication'),
(13, 'Undergrad', 'BTECH', 3, 'BTECH303', 'System Architecture'),
(14, 'Undergrad', 'BTECH', 3, 'BTECH304', 'Data Structure & Algo'),
(15, 'Undergrad', 'BTECH', 3, 'BTECH305', 'OOPs in JAVA'),
(16, 'Undergrad', 'BTECH', 4, 'BTECH401', 'Engineering Maths-4`'),
(17, 'Undergrad', 'BTECH', 4, 'BTECH402', 'Operating System'),
(18, 'Undergrad', 'BTECH', 4, 'BTECH403', 'Computer Organisation'),
(19, 'Undergrad', 'BTECH', 4, 'BTECH404', 'RDBMS'),
(20, 'Undergrad', 'BTECH', 4, 'BTECH405', 'Web Technology'),
(21, 'Undergrad', 'BTECH', 5, 'BTECH501', 'Artificial Intelligence'),
(22, 'Undergrad', 'BTECH', 5, 'BTECH502', 'Automata & Compiler Design'),
(23, 'Undergrad', 'BTECH', 5, 'BTECH503', 'Computer Networks'),
(24, 'Undergrad', 'BTECH', 5, 'BTECH504', 'Cyber Security'),
(25, 'Undergrad', 'BTECH', 5, 'BTECH505', 'Design & Analysis of Algo'),
(37, 'Undergrad', 'BTECH', 7, 'BTECH701', 'Cloud Computing'),
(38, 'Undergrad', 'BTECH', 7, 'BTECH702', 'Digital Forensics'),
(39, 'Undergrad', 'BTECH', 7, 'BTECH703', 'Enterprise Resource PLanning'),
(40, 'Undergrad', 'BTECH', 7, 'BTECH704', 'Internet of Things'),
(41, 'Undergrad', 'BTECH', 7, 'BTECH705', 'App Dev with Kotlin'),
(42, 'Undergrad', 'BTECH', 8, 'BTECH801', 'Information Coding'),
(43, 'Undergrad', 'BTECH', 8, 'BTECH802', 'Big Data Analysis'),
(44, 'Undergrad', 'BTECH', 8, 'BTECH803', 'Deep Neural Network'),
(45, 'Undergrad', 'BTECH', 8, 'BTECH804', 'Linux & Shell Programming'),
(46, 'Undergrad', 'BTECH', 8, 'BTECH805', 'Python Programming'),
(47, 'Undergrad', 'BCA', 1, 'BCA101', 'Fundamentals of Maths'),
(48, 'Undergrad', 'BCA', 1, 'BCA102', 'Technical Communication'),
(49, 'Undergrad', 'BCA', 1, 'BCA103', 'C++ Programming'),
(50, 'Undergrad', 'BCA', 1, 'BCA104', 'Digital Systems'),
(51, 'Undergrad', 'BCA', 1, 'BCA105', 'Intro to Web Programming'),
(52, 'Undergrad', 'BCA', 2, 'BCA201', 'Basic Statistics and Probability'),
(53, 'Undergrad', 'BCA', 2, 'BCA202', 'Data Structures'),
(54, 'Undergrad', 'BCA', 2, 'BCA203', 'DBMS'),
(55, 'Undergrad', 'BCA', 2, 'BCA204', 'Principle of Programming languages'),
(56, 'Undergrad', 'BCA', 2, 'BCA205', 'OOPs using C++'),
(57, 'Undergrad', 'BCA', 3, 'BCA301', 'Computer Organisation & Architecture'),
(58, 'Undergrad', 'BCA', 3, 'BCA302', 'Java Programming'),
(59, 'Undergrad', 'BCA', 3, 'BCA303', 'Data Communication & Protocols'),
(60, 'Undergrad', 'BCA', 3, 'BCA304', 'Operating Systems'),
(61, 'Undergrad', 'BCA', 3, 'BCA305', 'AI for Problem Solving'),
(62, 'Undergrad', 'BCA', 4, 'BCA401', 'Python Programming'),
(63, 'Undergrad', 'BCA', 4, 'BCA402', 'Software Engineering'),
(64, 'Undergrad', 'BCA', 4, 'BCA403', 'Data Mining & Visualization'),
(65, 'Undergrad', 'BCA', 4, 'BCA404', 'Intro to Network Security'),
(66, 'Undergrad', 'BCA', 4, 'BCA405', 'Internet of Things'),
(67, 'Undergrad', 'BCA', 5, 'BCA501', 'Mobile Application development'),
(68, 'Undergrad', 'BCA', 5, 'BCA502', 'Machine Learning'),
(69, 'Undergrad', 'BCA', 5, 'BCA503', 'Cloud Computing & Applications'),
(70, 'Undergrad', 'BCA', 5, 'BCA504', 'Real Time Systems'),
(71, 'Undergrad', 'BCA', 5, 'BCA505', 'Aptitude & Technical Development'),
(72, 'Undergrad', 'BCA', 6, 'BCA601', 'Wireless Communication'),
(73, 'Undergrad', 'BCA', 6, 'BCA602', 'Unix and Shell Programming'),
(74, 'Undergrad', 'BCA', 6, 'BCA603', 'Big data'),
(75, 'Undergrad', 'BCA', 6, 'BCA604', 'Distributes Systems'),
(76, 'Undergrad', 'BCA', 6, 'BCA605', 'Software Testing and Quality Assurance'),
(77, 'Postgrad', 'MCA', 1, 'MCA101', 'Discrete Maths with Graph Theory'),
(78, 'Postgrad', 'MCA', 1, 'MCA102', 'Web Technologies'),
(79, 'Postgrad', 'MCA', 1, 'MCA103', 'Programming in C'),
(80, 'Postgrad', 'MCA', 1, 'MCA104', 'Data Visualization'),
(81, 'Postgrad', 'MCA', 1, 'MCA105', 'RDBMS'),
(82, 'Postgrad', 'MCA', 2, 'MCA201', 'Computer Networks and Protocols'),
(83, 'Postgrad', 'MCA', 2, 'MCA202', 'OOPs using JAVA'),
(84, 'Postgrad', 'MCA', 2, 'MCA203', 'Operating System'),
(85, 'Postgrad', 'MCA', 2, 'MCA204', 'Data Structures and Algo'),
(86, 'Postgrad', 'MCA', 2, 'MCA205', 'Artificial Intelligence'),
(87, 'Postgrad', 'MCA', 3, 'MCA301', 'Unix & Shell Programming'),
(88, 'Postgrad', 'MCA', 3, 'MCA302', 'Android Application Development'),
(89, 'Postgrad', 'MCA', 3, 'MCA301', 'Unix & Shell Programming'),
(90, 'Postgrad', 'MCA', 3, 'MCA302', 'Android Application Development'),
(91, 'Postgrad', 'MCA', 3, 'MCA303', 'Software Engineering'),
(92, 'Postgrad', 'MCA', 3, 'MCA304', 'Blockchain Technologies'),
(93, 'Postgrad', 'MCA', 3, 'MCA305', 'Data Science'),
(94, 'Undergrad', 'BTECH', 6, 'BTECH601', 'Software Engineering '),
(95, 'Undergrad', 'BTECH', 6, 'BTECH602', 'Information Systems Security'),
(96, 'Undergrad', 'BTECH', 6, 'BTECH603', 'Machine Learning'),
(97, 'Undergrad', 'BTECH', 6, 'BTECH604', 'Data Science'),
(98, 'Undergrad', 'BTECH', 6, 'BTECH605', 'Organisation & Management');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `s_no` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `id` varchar(25) NOT NULL,
  `pass` varchar(25) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(25) NOT NULL,
  `type` varchar(20) NOT NULL,
  `designation` varchar(20) NOT NULL,
  `address` varchar(40) NOT NULL,
  `pincode` int(11) NOT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`s_no`, `name`, `id`, `pass`, `phone`, `email`, `type`, `designation`, `address`, `pincode`, `city`, `state`) VALUES
(100, 'Super Admin', 'admin', 'admin', '9999955555', 'admin@tagoreacademy.com', 'admin', 'admin', 'Tagore Academy', 0, 'Unknown', 'Unknown'),
(101, 'Ankit Garg', 'ankit42', 'ankit@42', '9628034956', 'ankit427@hotmail.com', 'Teaching', 'Assistant Professor', '67C SHivaji Park', 110026, 'West Delhi', 'Delhi'),
(102, 'Rakesh Gupta', 'rakeshg77', 'Tagore@1960', '9503720684', 'rakeshgarg76@gmail.com', 'Non-Teaching', 'Clerk', '77F Shivaji Park', 110026, 'West Delhi', 'Delhi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`sr_no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `sr_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
