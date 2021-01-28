create schema java_bigbargainsstore collate = utf8_general_ci;
use java_bigbargainsstore;

--
-- Database: `java_bigbargainsstore`
--

-- --------------------------------------------------------

--
-- Table structure for table `adminlogin`
--

CREATE TABLE `adminlogin` (
  `AdminID` int(5) NOT NULL,
  `UserName` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `adminlogin`
--

INSERT INTO `adminlogin` (`AdminID`, `UserName`, `Password`) VALUES
(1, 'Admin', '1234'),
(2, 'Administrator', '1234'),
(3, 'Super User', '1234'),
(4, 'SU', '1234'),
(5, 'Ray', '1234'),
(6, 'rnjire', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `CustID` int(5) NOT NULL,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `UserName` varchar(30) CHARACTER SET latin1 COLLATE latin1_bin DEFAULT NULL,
  `Password` varchar(30) CHARACTER SET latin1 COLLATE latin1_bin NOT NULL,
  `PhoneNumber` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`CustID`, `FirstName`, `LastName`, `UserName`, `Password`, `PhoneNumber`, `Email`) VALUES
(1, 'Ray', 'Njire', 'RayNjire', '1234', 0703909356, 'raynjire@gmail.com'),
(2, 'Bruce', 'Wayne', 'BWayne', '1234', 0785956236, 'bwayne@gmail.com'),
(3, 'Clark', 'Kent', 'CKent', '1234', 0705484652, 'ckent@gmail.com'),
(4, 'Tony', 'Stark', 'TStark', '1234', 0708484513, 'tstark@avengers.com'),
(5, 'User', 'User', 'User', '1234', 0777777777, 'user@yahoo.com'),
(6, 'Paul', 'Phoenix', 'Paul', 'pol', 0754123658, 'paul@bigbargainscustomer.com');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `InvID` int(5) NOT NULL,
  `Product` varchar(20) DEFAULT NULL,
  `GroupCode` varchar(15) DEFAULT NULL,
  `Groups` varchar(15) DEFAULT NULL,
  `PriceinKSH` double(10,2) DEFAULT NULL,
  `Quantity` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`InvID`, `Product`, `GroupCode`, `Groups`, `PriceinKSH`, `Quantity`) VALUES
(1, 'Face Wash Gel', 'CO001', 'Cosmetics', 250.00, 300),
(2, 'Shampoo', 'CO008', 'Cosmetics', 500.00, 60),
(3, 'Polo Shirt', 'FA006', 'Fashion', 2650.00, 80),
(4, 'Jeans', 'FA019', 'Fashion', 8880.00, 50),
(5, 'Legos', 'TO121', 'Toys', 2000.00, 100),
(6, 'Funskool Kit', 'TO005', 'Toys', 900.00, 250);

-- --------------------------------------------------------

--
-- Table structure for table `loginattempt`
--

CREATE TABLE `loginattempt` (
  `LoginID` int(5) NOT NULL,
  `UserName` varchar(20) DEFAULT NULL,
  `PasswordUsed` varchar(20) DEFAULT NULL,
  `Date` datetime DEFAULT NULL,
  `Attempt` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loginattempt`
--

INSERT INTO `loginattempt` (`LoginID`, `UserName`, `PasswordUsed`, `Date`, `Attempt`) VALUES
(1, 'Paul', 'Pol', '2019-11-29 23:09:08', 'Success'),
(2, 'SuperUser', '1234', '2019-11-29 23:11:21', 'Successful Login'),
(3, 'su', NULL, '2019-11-29 23:17:12', 'Successful'),
(4, 'admin', 'admin', '2019-11-30 00:21:33', 'Failed Login'),
(5, 'admin', '1234', '2019-11-30 00:21:43', 'Successful Login'),
(6, 'admin', '1234', '2019-11-30 01:23:04', 'Successful Login'),
(7, 'administrator', '1234', '2019-11-30 01:23:29', 'Successful Login'),
(8, 'Ray', 'Njire', '2019-11-30 01:59:16', 'Failed Login'),
(9, 'RayNjire', '1234', '2019-11-30 01:59:33', 'Successful Login'),
(10, 'admin', '1234', '2019-11-30 02:20:52', 'Successful Login'),
(11, 'admin', '1234', '2019-11-30 02:21:48', 'Successful Login'),
(12, 'super user', '1234', '2019-11-30 02:38:35', 'Successful Login'),
(13, 'admin', '1234', '2019-11-30 02:39:15', 'Successful Login'),
(14, 'su', '1234', '2019-11-30 02:50:11', 'Successful Login'),
(15, 'su', '1234', '2019-11-30 02:51:04', 'Successful Login'),
(16, 'su', '1234', '2019-11-30 03:09:05', 'Successful Login'),
(17, 'su', '1234', '2019-11-30 03:20:15', 'Successful Login'),
(18, 'su', '1234', '2019-11-30 03:20:29', 'Successful Login'),
(19, 'admin', '1234', '2019-11-30 03:42:39', 'Successful Login'),
(20, 'SUPER USER', '1234', '2019-11-30 03:44:05', 'Successful Login'),
(21, 'RayNjire', '1234', '2019-11-30 11:32:18', 'Successful Login'),
(22, 'ClarkKent', '1234', '2019-11-30 11:35:01', 'Failed Login'),
(23, 'Dr.Banner', '1234', '2019-11-30 11:35:17', 'Successful Login'),
(24, 'su', '1234', '2019-11-30 20:40:50', 'Successful Login'),
(25, 'RayNjire', '1234', '2019-11-30 20:43:55', 'Successful Login'),
(26, 'CKent', '1234', '2019-11-30 20:46:43', 'Successful Login'),
(27, 'BWayne', '1234', '2019-11-30 20:47:59', 'Successful Login'),
(28, 'BWayne', '1234', '2019-11-30 20:48:58', 'Successful Login'),
(29, 'CKent', '1234', '2019-11-30 20:49:39', 'Successful Login'),
(30, 'ADMIN', '1234', '2019-11-30 18:26:04', 'Successful Login'),
(31, 'ADMIN', NULL, '2019-11-30 18:27:45', 'Logged Out'),
(32, 'CKent', '1234', '2019-11-30 18:31:24', 'Successful Login'),
(33, 'IN CART: ', NULL, '2019-11-30 18:32:42', 'Logged Out'),
(34, 'su', '1234', '2019-11-30 23:18:26', 'Successful Login'),
(35, 'su', NULL, '2019-11-30 23:19:53', 'Logged Out'),
(36, 'su', '1234', '2019-11-30 23:20:06', 'Successful Login'),
(37, 'su', NULL, '2019-11-30 23:20:35', 'Logged Out'),
(38, 'admin', '1234', '2019-11-30 23:23:42', 'Successful Login'),
(39, 'admin', NULL, '2019-11-30 23:23:49', 'Logged Out'),
(40, 'su', '1234', '2019-12-01 00:11:43', 'Successful Login'),
(41, 'su', NULL, '2019-12-01 00:12:30', 'Logged Out'),
(42, 'BWayne', '1234', '2019-12-01 00:37:59', 'Successful Login'),
(43, 'bwayne', '1234', '2019-12-01 00:38:33', 'Failed Login'),
(44, 'BWayne', '1234', '2019-12-01 00:38:41', 'Successful Login'),
(45, 'BWayne', NULL, '2019-12-01 00:39:28', 'Logged Out'),
(46, 'admin', '1234', '2019-12-01 20:37:29', 'Successful Login'),
(47, 'admin', NULL, '2019-12-01 20:42:38', 'Logged Out'),
(48, 'admin', '1234', '2019-12-01 20:49:55', 'Successful Login'),
(49, 'User', '1234', '2019-12-01 21:03:41', 'Successful Login'),
(50, 'administrator', '1234', '2019-12-01 21:04:29', 'Successful Login'),
(51, 'administrator', NULL, '2019-12-01 21:06:12', 'Logged Out'),
(52, 'CKent', '1234', '2019-12-01 21:08:51', 'Successful Login'),
(53, 'CKent', NULL, '2019-12-01 21:09:00', 'Logged Out'),
(54, 'BWayne', '1234', '2019-12-01 21:10:24', 'Successful Login'),
(55, 'BWayne', NULL, '2019-12-01 21:10:30', 'Logged Out'),
(56, 'SUPER USER', '1234', '2019-12-01 21:15:47', 'Successful Login'),
(57, 'SUPER USER', NULL, '2019-12-01 21:16:10', 'Logged Out'),
(58, 'ADMIN', '1234', '2019-12-01 21:16:33', 'Successful Login'),
(59, 'ADMIN', NULL, '2019-12-01 21:18:28', 'Logged Out'),
(60, 'SUPER USER', '1234', '2019-12-01 21:19:41', 'Successful Login'),
(61, 'SUPER USER', '1234', '2019-12-01 21:19:57', 'Successful Login'),
(62, 'SUPER USER', NULL, '2019-12-01 21:20:07', 'Logged Out'),
(63, 'ADMIN', '1234', '2019-12-01 21:20:18', 'Successful Login'),
(64, 'ADMIN', NULL, '2019-12-01 21:20:27', 'Logged Out'),
(65, 'su', '1234', '2019-12-01 21:26:12', 'Successful Login'),
(66, 'admin', '1234', '2019-12-01 21:26:33', 'Successful Login'),
(67, 'admin', NULL, '2019-12-01 21:26:50', 'Logged Out'),
(68, 'BWayne', '1234', '2019-12-01 21:46:21', 'Successful Login'),
(69, 'BWayne', NULL, '2019-12-01 21:47:05', 'Logged Out'),
(70, 'CKent', '1234', '2019-12-01 21:50:41', 'Successful Login'),
(71, 'CKent', NULL, '2019-12-01 21:50:56', 'Logged Out'),
(72, 'administrator', '1234', '2019-12-01 21:57:58', 'Successful Login'),
(73, 'administrator', NULL, '2019-12-01 21:59:46', 'Logged Out'),
(74, 'su', '1234', '2019-12-01 22:00:45', 'Successful Login'),
(75, 'su', NULL, '2019-12-01 22:03:01', 'Logged Out'),
(76, 'admin', '1234', '2019-12-01 22:04:49', 'Successful Login'),
(77, 'su', '1234', '2019-12-01 22:12:46', 'Successful Login'),
(78, 'su', '1234', '2019-12-01 22:13:23', 'Successful Login'),
(79, 'su', '1234', '2019-12-01 22:18:43', 'Successful Login'),
(80, 'su', NULL, '2019-12-01 22:18:58', 'Logged Out'),
(81, 'su', '1234', '2019-12-01 22:19:30', 'Successful Login'),
(82, 'su', NULL, '2019-12-01 22:27:38', 'Logged Out'),
(83, 'super user', '1234', '2019-12-01 22:29:10', 'Successful Login'),
(84, 'super user', NULL, '2019-12-01 22:31:24', 'Logged Out'),
(85, 'admin', '1234', '2019-12-01 22:31:57', 'Successful Login'),
(86, 'BWayne', '1234', '2019-12-01 22:34:48', 'Successful Login'),
(87, 'BWayne', NULL, '2019-12-01 22:36:01', 'Logged Out'),
(88, 'admin', NULL, '2019-12-01 22:36:17', 'Logged Out'),
(89, 'ADMIN', '1234', '2019-12-01 22:53:27', 'Successful Login'),
(90, 'ADMIN', NULL, '2019-12-01 22:54:34', 'Logged Out'),
(91, 'ADMINISTRATOR', '1234', '2019-12-01 22:56:23', 'Successful Login'),
(92, 'ADMINISTRATOR', NULL, '2019-12-01 22:57:11', 'Logged Out'),
(93, 'super user', '1234', '2019-12-01 22:58:05', 'Successful Login'),
(94, 'RayNjire', '1234', '2019-12-01 23:04:18', 'Successful Login'),
(95, 'RayNjire', NULL, '2019-12-01 23:09:43', 'Logged Out'),
(96, 'Dr.Banner', '1234', '2019-12-02 00:43:47', 'Successful Login'),
(97, 'Dr.Banner', NULL, '2019-12-02 00:44:48', 'Logged Out'),
(98, 'superuser', '1234', '2019-12-02 00:45:20', 'Successful Login'),
(99, 'superuser', '1234', '2019-12-02 01:05:00', 'Successful Login'),
(100, 'superuser', NULL, '2019-12-02 01:05:32', 'Logged Out'),
(101, 'su', '1234', '2019-12-02 01:10:48', 'Successful Login'),
(102, 'raynjire', '1234', '2019-12-02 01:17:16', 'Failed Login'),
(103, 'raynjire', '1234', '2019-12-02 01:17:29', 'Failed Login'),
(104, 'su', '1234', '2019-12-02 01:17:43', 'Successful Login'),
(105, 'su', NULL, '2019-12-02 01:19:47', 'Logged Out'),
(106, 'RayNjire', '1234', '2019-12-02 01:42:14', 'Successful Login'),
(107, 'RayNjire', NULL, '2019-12-02 01:46:07', 'Logged Out'),
(108, 'Ray', '1234', '2019-12-02 01:48:36', 'Successful Login'),
(109, 'Ray', NULL, '2019-12-02 01:49:37', 'Logged Out'),
(110, 'SU', '1234', '2019-12-02 02:08:48', 'Successful Login'),
(111, 'SU', NULL, '2019-12-02 02:09:36', 'Logged Out'),
(112, 'ray', '1234', '2019-12-02 03:08:18', 'Successful Login'),
(113, 'ray', NULL, '2019-12-02 03:08:38', 'Logged Out'),
(114, 'raynjire', '1234', '2019-12-02 03:08:55', 'Successful Login'),
(115, 'raynjire', NULL, '2019-12-02 03:17:11', 'Logged Out'),
(116, 'admin', '1234', '2019-12-02 03:17:29', 'Successful Login'),
(117, 'admin', NULL, '2019-12-02 03:17:51', 'Logged Out'),
(118, 'TStark', '1234', '2019-12-02 03:21:18', 'Successful Login'),
(119, 'superuser', '1234', '2019-12-02 03:25:59', 'Successful Login'),
(120, 'superuser', NULL, '2019-12-02 03:29:44', 'Logged Out'),
(121, 'ADMIN', '1234', '2019-12-02 04:13:21', 'Successful Login'),
(122, 'ADMIN', NULL, '2019-12-02 04:15:38', 'Logged Out'),
(123, 'RayNjire', '1234', '2019-12-02 11:55:40', 'Successful Login'),
(124, 'admin', '1234', '2019-12-02 12:17:31', 'Successful Login'),
(125, 'admin', NULL, '2019-12-02 12:17:58', 'Logged Out'),
(126, 'RayNjire', NULL, '2019-12-02 12:18:05', 'Logged Out'),
(127, 'admin', '1234', '2019-12-02 12:21:12', 'Successful Login'),
(128, 'admin', NULL, '2019-12-02 12:29:10', 'Logged Out'),
(129, 'raynjire', '1234', '2019-12-02 21:06:51', 'Successful Login'),
(130, 'raynjire', NULL, '2019-12-02 21:07:14', 'Logged Out'),
(131, 'CKent', '1234', '2019-12-02 21:08:56', 'Successful Login'),
(132, 'admin', '1234', '2019-12-02 21:11:24', 'Successful Login'),
(133, 'CKent', '1234', '2019-12-02 21:20:24', 'Successful Login'),
(134, 'admin', NULL, '2019-12-02 21:22:13', 'Logged Out'),
(135, 'raynjire', '1234', '2019-12-02 21:33:57', 'Successful Login'),
(136, 'raynjire', NULL, '2019-12-02 21:34:19', 'Logged Out'),
(137, 'admin', '1234', '2019-12-02 21:52:06', 'Successful Login'),
(138, 'admin', NULL, '2019-12-02 21:52:25', 'Logged Out'),
(139, 'admin', '1234', '2019-12-02 21:59:06', 'Successful Login'),
(140, 'admin', NULL, '2019-12-02 21:59:34', 'Logged Out'),
(141, 'admin', '1234', '2019-12-02 22:03:49', 'Successful Login'),
(142, 'admin', NULL, '2019-12-02 22:04:08', 'Logged Out'),
(143, 'admin', '1234', '2019-12-02 22:07:37', 'Successful Login'),
(144, 'admin', NULL, '2019-12-02 22:07:52', 'Logged Out'),
(145, 'admin', '1234', '2019-12-02 22:09:12', 'Successful Login'),
(146, 'admin', NULL, '2019-12-02 22:22:24', 'Logged Out'),
(147, 'raynjire', '1234', '2019-12-02 22:22:39', 'Successful Login'),
(148, 'raynjire', NULL, '2019-12-02 22:22:51', 'Logged Out'),
(149, 'BWayne', '1234', '2019-12-02 22:23:07', 'Successful Login'),
(150, 'BWayne', NULL, '2019-12-02 22:23:38', 'Logged Out'),
(151, 'BWayne', '1234', '2019-12-02 22:34:52', 'Successful Login'),
(152, 'BWayne', NULL, '2019-12-02 22:35:06', 'Logged Out'),
(153, 'CKent', '1234', '2019-12-02 22:35:16', 'Successful Login'),
(154, 'CKent', NULL, '2019-12-02 22:35:33', 'Logged Out'),
(155, 'admin', '1234', '2019-12-02 22:36:01', 'Successful Login'),
(156, 'admin', NULL, '2019-12-02 22:42:14', 'Logged Out'),
(157, 'su', '1234', '2019-12-02 23:51:28', 'Successful Login'),
(158, 'su', NULL, '2019-12-02 23:51:43', 'Logged Out'),
(159, 'su', '1234', '2019-12-02 23:51:54', 'Successful Login'),
(160, 'su', NULL, '2019-12-02 23:53:10', 'Logged Out'),
(161, 'SU', '1234', '2019-12-02 23:53:29', 'Successful Login'),
(162, 'SU', '1234', '2019-12-02 23:58:00', 'Successful Login'),
(163, 'su', '1234', '2019-12-03 01:07:01', 'Successful Login'),
(164, 'su', NULL, '2019-12-03 01:07:16', 'Logged Out'),
(165, 'su', '1234', '2019-12-03 01:08:58', 'Successful Login'),
(166, 'su', '1234', '2019-12-03 02:57:02', 'Successful Login'),
(167, 'SU', NULL, '2019-12-03 03:07:27', 'Logged Out'),
(168, 'RAYNJIRE', '1234', '2019-12-03 03:07:39', 'Successful Login'),
(169, 'RAYNJIRE', NULL, '2019-12-03 03:08:18', 'Logged Out'),
(170, 'admin', '1234', '2019-12-03 03:23:07', 'Successful Login'),
(171, 'ADMIN', NULL, '2019-12-03 03:26:49', 'Logged Out'),
(172, 'CKent', '1234', '2019-12-03 03:27:25', 'Successful Login'),
(173, 'CKENT', NULL, '2019-12-03 03:34:51', 'Logged Out'),
(174, 'CKent', '1234', '2019-12-03 03:35:15', 'Successful Login'),
(175, 'CKENT', NULL, '2019-12-03 03:40:01', 'Logged Out'),
(176, 'ray', '1234', '2019-12-03 03:41:52', 'Successful Login'),
(177, 'RAY', NULL, '2019-12-03 03:49:14', 'Logged Out'),
(178, 'RAY', '1234', '2019-12-03 03:51:55', 'Successful Login'),
(179, 'RAY', NULL, '2019-12-03 03:53:20', 'Logged Out'),
(180, 'CKent', '1234', '2019-12-03 03:53:37', 'Successful Login'),
(181, 'CKENT', NULL, '2019-12-03 03:56:44', 'Logged Out'),
(182, 'su', '1234', '2019-12-03 03:57:01', 'Successful Login'),
(183, 'SU', NULL, '2019-12-03 03:58:08', 'Logged Out'),
(184, 'super user', '1234', '2019-12-03 03:58:29', 'Successful Login'),
(185, 'SUPER USER', NULL, '2019-12-03 03:59:46', 'Logged Out'),
(186, 'su', '1234', '2019-12-03 04:25:49', 'Successful Login'),
(187, 'SU', NULL, '2019-12-03 04:25:57', 'Logged Out'),
(188, 'su', '1234', '2019-12-03 04:26:13', 'Successful Login'),
(189, 'SU', NULL, '2019-12-03 04:26:34', 'Logged Out'),
(190, 'su', '1234', '2019-12-03 04:26:48', 'Successful Login'),
(191, 'SU', NULL, '2019-12-03 04:27:05', 'Logged Out'),
(192, 'su', '1234', '2019-12-03 04:27:25', 'Successful Login'),
(193, 'SU', NULL, '2019-12-03 04:28:22', 'Logged Out'),
(194, 'su', '1234', '2019-12-03 04:28:35', 'Successful Login'),
(195, 'SU', NULL, '2019-12-03 04:28:40', 'Logged Out'),
(196, 'su', '1234', '2019-12-03 04:29:21', 'Successful Login'),
(197, 'SU', NULL, '2019-12-03 04:30:13', 'Logged Out'),
(198, 'su', '1234', '2019-12-03 04:31:02', 'Successful Login'),
(199, 'SU', NULL, '2019-12-03 04:31:10', 'Logged Out'),
(200, 'su', '1234', '2019-12-03 04:31:32', 'Successful Login'),
(201, 'SU', NULL, '2019-12-03 04:33:18', 'Logged Out'),
(202, 'su', '1234', '2019-12-03 04:34:23', 'Successful Login'),
(203, 'SU', NULL, '2019-12-03 04:34:30', 'Logged Out'),
(204, 'su', '1234', '2019-12-03 04:35:11', 'Successful Login'),
(205, 'SU', NULL, '2019-12-03 04:36:04', 'Logged Out'),
(206, 'su', '1234', '2019-12-03 04:36:52', 'Successful Login'),
(207, 'SU', NULL, '2019-12-03 04:37:12', 'Logged Out'),
(208, 'su', '1234', '2019-12-03 04:37:24', 'Successful Login'),
(209, 'SU', NULL, '2019-12-03 04:37:35', 'Logged Out'),
(210, 'su', '1234', '2019-12-03 04:38:45', 'Successful Login'),
(211, 'SU', NULL, '2019-12-03 04:38:51', 'Logged Out'),
(212, 'su', '1234', '2019-12-03 04:39:19', 'Successful Login'),
(213, 'SU', NULL, '2019-12-03 04:39:58', 'Logged Out'),
(214, 'su', '1234', '2019-12-03 04:40:13', 'Successful Login'),
(215, 'SU', NULL, '2019-12-03 04:40:22', 'Logged Out'),
(216, 'ray', '1234', '2019-12-03 04:41:44', 'Successful Login'),
(217, 'RAY', NULL, '2019-12-03 04:42:00', 'Logged Out'),
(218, 'ray', '1234', '2019-12-03 04:42:12', 'Successful Login'),
(219, 'RAY', NULL, '2019-12-03 04:42:17', 'Logged Out'),
(220, 'ray', '1234', '2019-12-03 04:42:36', 'Successful Login'),
(221, 'RAY', NULL, '2019-12-03 04:42:49', 'Logged Out'),
(222, 'su', '1234', '2019-12-03 04:43:36', 'Successful Login'),
(223, 'SU', NULL, '2019-12-03 04:43:47', 'Logged Out'),
(224, 'su', '1234', '2019-12-03 04:45:30', 'Successful Login'),
(225, 'SU', NULL, '2019-12-03 04:45:37', 'Logged Out'),
(226, 'su', '1234', '2019-12-03 04:46:09', 'Successful Login'),
(227, 'SU', NULL, '2019-12-03 04:46:17', 'Logged Out'),
(228, 'su', '1234', '2019-12-03 04:46:29', 'Successful Login'),
(229, 'SU', NULL, '2019-12-03 04:46:33', 'Logged Out'),
(230, 'su', '1234', '2019-12-03 04:46:48', 'Successful Login'),
(231, 'SU', NULL, '2019-12-03 04:47:00', 'Logged Out'),
(232, 'raynjire', '1234', '2019-12-03 04:50:13', 'Failed Login'),
(233, 'RayNjire', '1234', '2019-12-03 04:50:24', 'Successful Login'),
(234, 'RAYNJIRE', NULL, '2019-12-03 04:50:37', 'Logged Out'),
(235, 'su', '1234', '2019-12-03 04:51:03', 'Successful Login'),
(236, 'SU', NULL, '2019-12-03 04:51:17', 'Logged Out'),
(237, 'CKent', '1234', '2019-12-03 04:59:10', 'Successful Login'),
(238, 'CKENT', NULL, '2019-12-03 04:59:28', 'Logged Out'),
(239, 'BWayne', '1234', '2019-12-03 04:59:49', 'Successful Login'),
(240, 'BWAYNE', NULL, '2019-12-03 05:00:42', 'Logged Out'),
(241, 'su', '1234', '2019-12-03 05:02:41', 'Successful Login'),
(242, 'SU', NULL, '2019-12-03 05:03:03', 'Logged Out'),
(243, 'su', '1234', '2019-12-03 05:03:10', 'Successful Login'),
(244, 'SU', NULL, '2019-12-03 05:03:34', 'Logged Out'),
(245, 'su', '1234', '2019-12-03 05:11:19', 'Successful Login'),
(246, 'SU', NULL, '2019-12-03 05:11:50', 'Logged Out'),
(247, 'su', '1234', '2019-12-03 05:12:00', 'Successful Login'),
(248, 'su', '1234', '2019-12-03 05:29:40', 'Successful Login'),
(249, 'SU', NULL, '2019-12-03 05:29:58', 'Logged Out'),
(250, 'su', '1234', '2019-12-03 05:30:08', 'Successful Login'),
(251, 'SU', NULL, '2019-12-03 05:30:20', 'Logged Out'),
(252, 'su', '1234', '2019-12-03 05:30:34', 'Successful Login'),
(253, 'SU', NULL, '2019-12-03 05:34:08', 'Logged Out'),
(254, 'Ray', '1234', '2019-12-03 18:04:35', 'Successful Login'),
(255, 'RAY', NULL, '2019-12-03 18:06:34', 'Logged Out'),
(256, 'CKent', '1234', '2019-12-03 18:07:06', 'Successful Login'),
(257, 'CKENT', NULL, '2019-12-03 18:10:16', 'Logged Out'),
(258, 'RayNjire', '1234', '2019-12-04 03:44:52', 'Successful Login'),
(259, 'RAYNJIRE', NULL, '2019-12-04 03:53:35', 'Logged Out'),
(260, 'CKent', '1234', '2019-12-04 04:07:12', 'Successful Login'),
(261, 'BWayne', '1234', '2019-12-04 04:10:08', 'Successful Login'),
(262, 'BWAYNE', NULL, '2019-12-04 04:11:56', 'Logged Out'),
(263, 'User', '1234', '2019-12-04 04:22:55', 'Successful Login'),
(264, 'USER', NULL, '2019-12-04 12:59:16', 'Logged Out'),
(265, 'CKent', '1234', '2019-12-05 22:52:36', 'Successful Login'),
(266, 'CKENT', NULL, '2019-12-05 22:55:07', 'Logged Out'),
(267, 'TStark', '1234', '2019-12-05 23:47:27', 'Successful Login'),
(268, 'TSTARK', NULL, '2019-12-07 01:30:55', 'Logged Out'),
(269, 'RayNjire', '1234', '2019-12-08 01:13:35', 'Successful Login'),
(270, 'RAYNJIRE', NULL, '2019-12-08 01:16:39', 'Logged Out'),
(271, 'admin', '1234', '2019-12-08 01:41:22', 'Successful Login'),
(272, 'ADMIN', NULL, '2019-12-08 01:50:46', 'Logged Out'),
(273, 'TStark', '1234', '2019-12-08 01:58:03', 'Successful Login'),
(274, 'TSTARK', NULL, '2019-12-08 02:12:00', 'Logged Out'),
(275, 'su', '1234', '2019-12-08 02:48:08', 'Successful Login'),
(276, 'SU', NULL, '2019-12-08 03:03:24', 'Logged Out'),
(277, 'RayNjire', '1234', '2019-12-09 14:30:51', 'Successful Login'),
(278, 'RAYNJIRE', NULL, '2019-12-09 14:39:51', 'Logged Out'),
(279, 'TStark', '1234', '2019-12-09 14:42:56', 'Successful Login'),
(280, 'TSTARK', NULL, '2019-12-09 15:01:08', 'Logged Out'),
(281, 'BWayne', '1234', '2019-12-09 15:10:36', 'Successful Login'),
(282, 'BWAYNE', NULL, '2019-12-09 15:12:49', 'Logged Out'),
(283, 'BWayne', '1234', '2019-12-09 15:16:15', 'Successful Login'),
(284, 'BWAYNE', NULL, '2019-12-09 15:17:54', 'Logged Out'),
(285, 'RayNjire', '1234', '2019-12-15 01:59:39', 'Successful Login'),
(286, 'RAYNJIRE', NULL, '2019-12-15 02:01:47', 'Logged Out'),
(287, 'admin', '1234', '2019-12-15 02:05:53', 'Successful Login'),
(288, 'ADMIN', NULL, '2019-12-15 02:43:22', 'Logged Out'),
(289, 'ADMIN', '1234', '2019-12-15 16:05:20', 'Successful Login'),
(290, 'admin', '1234', '2019-12-15 19:12:58', 'Successful Login'),
(291, 'ADMIN', NULL, '2019-12-15 19:15:53', 'Logged Out'),
(292, 'RayNjire', '1234', '2019-12-22 13:16:38', 'Successful Login'),
(293, 'RAYNJIRE', NULL, '2019-12-22 13:20:21', 'Logged Out'),
(294, 'RayNjire', '1234', '2019-12-23 01:02:54', 'Successful Login'),
(295, 'RAYNJIRE', NULL, '2019-12-23 01:03:20', 'Logged Out'),
(296, 'RayNjire', '1234', '2019-12-23 01:03:32', 'Successful Login'),
(297, 'admin', '1234', '2019-12-23 01:03:41', 'Successful Login'),
(298, 'admin', '1234', '2019-12-23 01:20:00', 'Successful Login'),
(299, 'ADMIN', NULL, '2019-12-23 01:28:19', 'Logged Out'),
(300, 'RayNjire', '1234', '2019-12-23 19:01:49', 'Successful Login'),
(301, 'RAYNJIRE', NULL, '2019-12-23 19:03:43', 'Logged Out'),
(302, 'Paul', 'pol', '2019-12-23 20:24:12', 'Failed Login'),
(303, 'Paul', 'Pol', '2019-12-23 20:24:22', 'Successful Login'),
(304, 'PAUL', NULL, '2019-12-23 20:25:34', 'Logged Out'),
(305, 'RayNjire', '1234', '2019-12-23 20:26:13', 'Failed Login'),
(306, 'rnjire', '1234', '2019-12-23 20:26:24', 'Failed Login'),
(307, 'raynjire', '1234', '2019-12-23 20:26:31', 'Failed Login'),
(308, 'su', '1234', '2019-12-23 20:26:39', 'Successful Login'),
(309, 'su', '1234', '2019-12-23 22:33:15', 'Successful Login'),
(310, 'SU', NULL, '2019-12-23 22:34:51', 'Logged Out'),
(311, 'admin', '1234', '2019-12-25 23:32:57', 'Successful Login'),
(312, 'ADMIN', NULL, '2019-12-25 23:33:08', 'Logged Out'),
(313, 'admin', '1111', '2019-12-25 23:33:25', 'Failed Login'),
(314, 'admin', '1234', '2019-12-25 23:33:33', 'Successful Login'),
(315, 'ADMIN', NULL, '2019-12-25 23:39:38', 'Logged Out'),
(316, 'admin', '1234', '2019-12-25 23:45:36', 'Successful Login'),
(317, 'ADMIN', '1234', '2019-12-25 23:50:34', 'Successful Login'),
(318, 'ADMIN', NULL, '2019-12-25 23:52:03', 'Logged Out'),
(319, 'admin', '1234', '2019-12-25 23:52:10', 'Successful Login'),
(320, 'admin', '1234', '2019-12-26 00:02:09', 'Successful Login'),
(321, 'admin', '1234', '2019-12-26 00:22:20', 'Successful Login'),
(322, 'admin', '1234', '2019-12-26 00:24:30', 'Successful Login'),
(323, 'ADMIN', NULL, '2019-12-26 00:52:20', 'Logged Out'),
(324, 'admin', '1234', '2019-12-26 00:53:40', 'Successful Login'),
(325, 'ADMIN', NULL, '2019-12-26 01:12:50', 'Logged Out'),
(326, 'admin', '1234', '2019-12-26 01:43:18', 'Successful Login'),
(327, 'admin', '1234', '2019-12-27 17:46:54', 'Successful Login'),
(328, 'admin', '1234', '2019-12-27 17:47:12', 'Successful Login'),
(329, 'admin', '1234', '2019-12-27 17:58:37', 'Successful Login'),
(330, 'admin', '1234', '2019-12-27 17:59:23', 'Successful Login'),
(331, 'admin', '1234', '2019-12-27 18:02:38', 'Successful Login'),
(332, 'admin', '1234', '2019-12-27 19:37:55', 'Successful Login'),
(333, 'admin', '1234', '2019-12-27 19:43:29', 'Successful Login'),
(334, 'admin', '1234', '2019-12-27 19:52:00', 'Successful Login'),
(335, 'admin', '1234', '2019-12-27 20:17:31', 'Successful Login'),
(336, 'ADMIN', NULL, '2019-12-27 20:17:42', 'Logged Out'),
(337, 'su', '1234', '2019-12-29 22:46:46', 'Successful Login'),
(338, 'RayNjire', '1234', '2019-12-29 23:26:11', 'Successful Login'),
(339, 'RAYNJIRE', NULL, '2019-12-29 23:29:54', 'Logged Out'),
(340, 'su', '1234', '2019-12-29 23:30:27', 'Successful Login'),
(341, 'admin', '1234', '2019-12-29 23:32:49', 'Successful Login'),
(342, 'admin', '1234', '2019-12-29 23:35:05', 'Successful Login'),
(343, 'admin', '1234', '2019-12-30 17:33:55', 'Successful Login'),
(344, 'admin', '1234', '2019-12-30 17:37:41', 'Successful Login'),
(345, 'admin', '1234', '2019-12-30 17:58:53', 'Successful Login'),
(346, 'ADMIN', NULL, '2019-12-30 18:01:28', 'Logged Out'),
(347, 'admin', '1234', '2019-12-30 18:04:10', 'Successful Login'),
(348, 'su', '1234', '2019-12-30 18:14:27', 'Successful Login'),
(349, 'su', '1234', '2019-12-30 18:15:36', 'Successful Login'),
(350, 'su', '1234', '2019-12-30 18:20:31', 'Successful Login'),
(351, 'SU', NULL, '2019-12-30 18:38:58', 'Logged Out'),
(352, 'admin', '1234', '2019-12-30 18:40:45', 'Successful Login'),
(353, 'ADMIN', NULL, '2019-12-30 18:50:37', 'Logged Out'),
(354, 'admin', '1234', '2019-12-30 18:57:21', 'Successful Login'),
(355, 'ray', '1234', '2019-12-30 19:26:33', 'Successful Login'),
(356, 'RAY', NULL, '2019-12-30 19:26:52', 'Logged Out'),
(357, 'ray', '1234', '2019-12-30 19:26:59', 'Successful Login'),
(358, 'RAY', NULL, '2019-12-30 19:27:01', 'Logged Out'),
(359, 'ray', '1234', '2019-12-30 19:27:07', 'Successful Login'),
(360, 'RAY', NULL, '2019-12-30 19:46:48', 'Logged Out'),
(361, 'admin', '1234', '2019-12-30 20:22:06', 'Successful Login'),
(362, 'ADMIN', NULL, '2019-12-30 20:27:19', 'Logged Out'),
(363, 'admin', '1234', '2019-12-30 21:00:08', 'Successful Login'),
(364, 'jude', '1234', '2019-12-30 21:11:34', 'Successful Login'),
(365, 'rnjire', '1234', '2019-12-30 21:15:33', 'Successful Login'),
(366, 'RNJIRE', NULL, '2019-12-30 21:20:43', 'Logged Out'),
(367, 'jude', '1234', '2019-12-30 21:20:53', 'Successful Login'),
(368, 'JUDE', NULL, '2019-12-30 21:38:31', 'Logged Out'),
(369, 'lol', 'lol', '2019-12-30 21:39:13', 'Failed Login'),
(370, 'su', '1234', '2019-12-30 21:39:37', 'Successful Login'),
(371, 'admin', '1234', '2019-12-30 22:11:52', 'Successful Login'),
(372, 'admin', '1234', '2019-12-30 22:17:47', 'Successful Login'),
(373, 'admin', '1234', '2019-12-30 22:20:05', 'Successful Login'),
(374, 'su', '1234', '2019-12-30 22:33:23', 'Successful Login'),
(375, 'SU', NULL, '2019-12-30 22:35:41', 'Logged Out'),
(376, 'superuser', '1234', '2019-12-30 22:40:18', 'Successful Login'),
(377, 'SUPERUSER', NULL, '2019-12-30 22:47:08', 'Logged Out'),
(378, 'su', '1234', '2019-12-30 22:48:35', 'Successful Login'),
(379, 'SU', NULL, '2019-12-30 22:51:46', 'Logged Out'),
(380, 'admin', '1234', '2019-12-30 22:52:25', 'Successful Login'),
(381, 'admin', '1234', '2019-12-30 23:40:41', 'Successful Login'),
(382, 'ADMIN', NULL, '2019-12-30 23:41:32', 'Logged Out'),
(383, 'su', '1234', '2019-12-30 23:50:31', 'Successful Login'),
(384, 'SU', NULL, '2019-12-30 23:52:22', 'Logged Out'),
(385, 'jude', '1234', '2019-12-30 23:53:35', 'Successful Login'),
(386, 'ray', '1234', '2019-12-30 23:59:53', 'Successful Login'),
(387, 'rnjire', '1234', '2019-12-31 10:12:18', 'Successful Login'),
(388, 'RNJIRE', NULL, '2019-12-31 10:12:29', 'Logged Out'),
(389, 'su', '1234', '2019-12-31 10:12:38', 'Successful Login'),
(390, 'SU', NULL, '2019-12-31 10:25:12', 'Logged Out'),
(391, 'Paul', 'pol', '2019-12-31 10:25:39', 'Successful Login'),
(392, 'PAUL', NULL, '2019-12-31 10:29:07', 'Logged Out'),
(393, 'Paul', 'pol', '2019-12-31 10:34:29', 'Successful Login'),
(394, 'PAUL', NULL, '2019-12-31 10:39:48', 'Logged Out'),
(395, 'rnjire', '1234', '2019-12-31 10:41:09', 'Successful Login'),
(396, 'RNJIRE', NULL, '2019-12-31 10:51:23', 'Logged Out'),
(397, 'super user', '1234', '2019-12-31 11:26:51', 'Successful Login'),
(398, 'SUPER USER', NULL, '2019-12-31 11:27:16', 'Logged Out'),
(399, 'administrator', '1234', '2019-12-31 11:31:52', 'Successful Login'),
(400, 'ADMINISTRATOR', NULL, '2019-12-31 11:33:12', 'Logged Out'),
(401, 'ADMIN', '1234', '2019-12-31 11:36:24', 'Successful Login'),
(402, 'admin', '1234', '2019-12-31 11:42:02', 'Successful Login'),
(403, 'ADMIN', NULL, '2019-12-31 11:42:46', 'Logged Out'),
(404, 'admin', '1234', '2019-12-31 11:49:19', 'Successful Login'),
(405, 'ADMIN', NULL, '2019-12-31 11:49:36', 'Logged Out'),
(406, 'jude', '1234', '2019-12-31 12:01:24', 'Successful Login'),
(407, 'JUDE', NULL, '2019-12-31 12:12:43', 'Logged Out'),
(408, 'Paul', 'pol', '2019-12-31 12:12:55', 'Successful Login'),
(409, 'PAUL', NULL, '2019-12-31 12:13:27', 'Logged Out'),
(410, 'BWayne', '1234', '2019-12-31 12:16:11', 'Successful Login'),
(411, 'BWAYNE', NULL, '2019-12-31 12:17:32', 'Logged Out'),
(412, 'su', '1234', '2019-12-31 12:17:41', 'Successful Login'),
(413, 'SU', NULL, '2019-12-31 12:22:18', 'Logged Out'),
(414, 'admin', '1234', '2019-12-31 12:22:20', 'Successful Login'),
(415, 'ADMIN', NULL, '2019-12-31 12:22:23', 'Logged Out'),
(416, 'admin', '1234', '2019-12-31 12:43:59', 'Successful Login'),
(417, 'ADMIN', NULL, '2019-12-31 12:46:23', 'Logged Out'),
(418, 'admin', '1234', '2019-12-31 14:07:54', 'Successful Login'),
(419, 'ADMIN', NULL, '2019-12-31 14:09:11', 'Logged Out'),
(420, 'su', '1234', '2019-12-31 14:09:19', 'Successful Login'),
(421, 'SU', NULL, '2019-12-31 14:09:33', 'Logged Out'),
(422, 'admin', '1234', '2019-12-31 14:11:14', 'Successful Login'),
(423, 'ADMIN', NULL, '2019-12-31 14:13:36', 'Logged Out'),
(424, 'admin', '1234', '2019-12-31 14:16:42', 'Successful Login'),
(425, 'ADMIN', NULL, '2019-12-31 14:41:47', 'Logged Out'),
(426, 'admin', '1234', '2019-12-31 14:42:42', 'Successful Login'),
(427, 'ADMIN', NULL, '2019-12-31 14:45:42', 'Logged Out'),
(428, 'admin', '1234', '2019-12-31 14:55:37', 'Successful Login'),
(429, 'ADMIN', NULL, '2019-12-31 15:02:48', 'Logged Out'),
(430, 'admin', '1234', '2019-12-31 15:03:11', 'Successful Login'),
(431, 'ADMIN', NULL, '2019-12-31 15:05:46', 'Logged Out'),
(432, 'admin', '1234', '2019-12-31 15:06:11', 'Successful Login'),
(433, 'su', '1234', '2019-12-31 15:08:51', 'Successful Login'),
(434, 'admin', '1234', '2019-12-31 15:26:05', 'Successful Login'),
(435, 'ADMIN', NULL, '2019-12-31 15:44:26', 'Logged Out'),
(436, 'ADMIN', '1234', '2019-12-31 16:38:39', 'Successful Login'),
(437, 'ADMIN', NULL, '2019-12-31 16:41:27', 'Logged Out'),
(438, 'ADMIN', '1234', '2019-12-31 16:41:48', 'Successful Login'),
(439, 'ADMIN', NULL, '2019-12-31 16:43:30', 'Logged Out'),
(440, 'admin', '1234', '2019-12-31 16:44:20', 'Successful Login'),
(441, 'ADMIN', NULL, '2019-12-31 16:45:32', 'Logged Out'),
(442, 'admin', '1234', '2019-12-31 17:06:37', 'Successful Login'),
(443, 'ADMIN', NULL, '2019-12-31 17:06:56', 'Logged Out');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adminlogin`
--
ALTER TABLE `adminlogin`
  ADD PRIMARY KEY (`AdminID`),
  ADD UNIQUE KEY `UserName` (`UserName`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`CustID`),
  ADD UNIQUE KEY `UserName` (`UserName`),
  ADD UNIQUE KEY `UserName_2` (`UserName`);

--
-- Indexes for table `loginattempt`
--
ALTER TABLE `loginattempt`
  ADD PRIMARY KEY (`LoginID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `adminlogin`
--
ALTER TABLE `adminlogin`
  MODIFY `AdminID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `CustID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `loginattempt`
--
ALTER TABLE `loginattempt`
  MODIFY `LoginID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=444;
COMMIT;
