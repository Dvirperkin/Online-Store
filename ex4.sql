-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: יוני 16, 2022 בזמן 10:54 PM
-- גרסת שרת: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ex4`
--

-- --------------------------------------------------------

--
-- מבנה טבלה עבור טבלה `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `discount` double DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- הוצאת מידע עבור טבלה `product`
--

INSERT INTO `product` (`id`, `discount`, `image`, `name`, `price`, `stock`) VALUES
(1, 0, 'ProductImage.png', 'product 1', 100, 50),
(2, 20, 'ProductImage.png', 'product 2', 300, 43),
(3, 35, 'ProductImage.png', 'product 3', 500, 64),
(4, 63, 'ProductImage.png', 'product 4', 4564, 15),
(5, 0, 'ProductImage.png', 'product 5', 76, 32),
(6, 0, 'ProductImage.png', 'product 6', 1532, 89),
(7, 93, 'ProductImage.png', 'product 7', 100, 7),
(8, 10, 'ProductImage.png', 'product 8', 3750, 243),
(9, 26, 'ProductImage.png', 'product 9', 250, 3),
(10, 0, 'ProductImage.png', 'product 10', 67, 5);

--
-- Indexes for dumped tables
--

--
-- אינדקסים לטבלה `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
