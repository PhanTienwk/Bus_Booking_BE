-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 09, 2025 lúc 04:08 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `busbooking`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `role` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bus`
--

CREATE TABLE `bus` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_bus_type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `busroute`
--

CREATE TABLE `busroute` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `distance` float NOT NULL,
  `status` int(11) NOT NULL,
  `travel_time` float NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_bus_station_from` int(11) DEFAULT NULL,
  `id_bus_station_to` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `busstations`
--

CREATE TABLE `busstations` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_province` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bustrip`
--

CREATE TABLE `bustrip` (
  `id` int(11) NOT NULL,
  `cost_incurred` float NOT NULL,
  `cost_operating` float NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `departure_time` datetime(6) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_bus` int(11) DEFAULT NULL,
  `id_bus_route` int(11) DEFAULT NULL,
  `id_driver` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bustype`
--

CREATE TABLE `bustype` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `seat_count` int(11) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `changehistoryticket`
--

CREATE TABLE `changehistoryticket` (
  `id` int(11) NOT NULL,
  `change_time` datetime(6) DEFAULT NULL,
  `price` float NOT NULL,
  `id_bus_trip` int(11) DEFAULT NULL,
  `id_seat_positon` int(11) DEFAULT NULL,
  `id_ticket` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `invoice`
--

CREATE TABLE `invoice` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number_of_tickets` int(11) NOT NULL,
  `payment_method` int(11) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `time_of_booking` datetime(6) DEFAULT NULL,
  `total_amount` float NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_bus_trip` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `province`
--

CREATE TABLE `province` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `route`
--

CREATE TABLE `route` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `route_location` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_bus_route` int(11) DEFAULT NULL,
  `id_bus_station` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `seatposition`
--

CREATE TABLE `seatposition` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_bus` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ticket`
--

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_invoice` int(11) DEFAULT NULL,
  `id_seat_position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `birth_date` datetime(6) DEFAULT NULL,
  `cccd` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `gender` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_account` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfs8rn86mtgrcdmqjggp32nx2p` (`role`);

--
-- Chỉ mục cho bảng `bus`
--
ALTER TABLE `bus`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9d3wwf7hmeb8kydywkq601tdy` (`id_bus_type`);

--
-- Chỉ mục cho bảng `busroute`
--
ALTER TABLE `busroute`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKddd04vtj82ocuxtw7m07o33ce` (`id_bus_station_from`),
  ADD KEY `FK5nlhcrt67y49iw1n9e4xbgajr` (`id_bus_station_to`);

--
-- Chỉ mục cho bảng `busstations`
--
ALTER TABLE `busstations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcwosp9pcuk9sdlclub4r2b2s5` (`id_province`);

--
-- Chỉ mục cho bảng `bustrip`
--
ALTER TABLE `bustrip`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1fd3tt1uqjro9hruiso46ujft` (`id_bus`),
  ADD KEY `FKm4d2a947mahtfrv87su8mw61v` (`id_bus_route`),
  ADD KEY `FKbnelhf8rmms302gamtodfs3n` (`id_driver`);

--
-- Chỉ mục cho bảng `bustype`
--
ALTER TABLE `bustype`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `changehistoryticket`
--
ALTER TABLE `changehistoryticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpc0o5ye7vr5g29sbcmyrl2xdg` (`id_bus_trip`),
  ADD KEY `FKqhfet3fu0621nqbgh7l88if2s` (`id_seat_positon`),
  ADD KEY `FKfyol6dl74mtmoi7cs57t9pki4` (`id_ticket`);

--
-- Chỉ mục cho bảng `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqp7aa8tsj8u4adxd7ipro5sg9` (`id_bus_trip`),
  ADD KEY `FKbub7c17bl8pbefd91y21ovrr3` (`id_user`);

--
-- Chỉ mục cho bảng `province`
--
ALTER TABLE `province`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ixpn85566lwxgajun8msnplik` (`name`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbe1dao21ycwf60bdl0vjxfdjs` (`id_bus_route`),
  ADD KEY `FKmmpp2erj89cbs91b3flhu3kq` (`id_bus_station`);

--
-- Chỉ mục cho bảng `seatposition`
--
ALTER TABLE `seatposition`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtc6nabxm16pxgrjeh2l90bx9t` (`id_bus`);

--
-- Chỉ mục cho bảng `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK12j2qlekqc5lsijmr2pjlbiur` (`id_invoice`),
  ADD KEY `FK1my700hbodyyhdrrmwp7l3626` (`id_seat_position`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_31mo2ywg0rdercw43o44y2ddg` (`id_account`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `bus`
--
ALTER TABLE `bus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `busroute`
--
ALTER TABLE `busroute`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `busstations`
--
ALTER TABLE `busstations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `bustrip`
--
ALTER TABLE `bustrip`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `bustype`
--
ALTER TABLE `bustype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `changehistoryticket`
--
ALTER TABLE `changehistoryticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `province`
--
ALTER TABLE `province`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `route`
--
ALTER TABLE `route`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `seatposition`
--
ALTER TABLE `seatposition`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FKfs8rn86mtgrcdmqjggp32nx2p` FOREIGN KEY (`role`) REFERENCES `role` (`id`);

--
-- Các ràng buộc cho bảng `bus`
--
ALTER TABLE `bus`
  ADD CONSTRAINT `FK9d3wwf7hmeb8kydywkq601tdy` FOREIGN KEY (`id_bus_type`) REFERENCES `bustype` (`id`);

--
-- Các ràng buộc cho bảng `busroute`
--
ALTER TABLE `busroute`
  ADD CONSTRAINT `FK5nlhcrt67y49iw1n9e4xbgajr` FOREIGN KEY (`id_bus_station_to`) REFERENCES `busstations` (`id`),
  ADD CONSTRAINT `FKddd04vtj82ocuxtw7m07o33ce` FOREIGN KEY (`id_bus_station_from`) REFERENCES `busstations` (`id`);

--
-- Các ràng buộc cho bảng `busstations`
--
ALTER TABLE `busstations`
  ADD CONSTRAINT `FKcwosp9pcuk9sdlclub4r2b2s5` FOREIGN KEY (`id_province`) REFERENCES `province` (`id`);

--
-- Các ràng buộc cho bảng `bustrip`
--
ALTER TABLE `bustrip`
  ADD CONSTRAINT `FK1fd3tt1uqjro9hruiso46ujft` FOREIGN KEY (`id_bus`) REFERENCES `bus` (`id`),
  ADD CONSTRAINT `FKbnelhf8rmms302gamtodfs3n` FOREIGN KEY (`id_driver`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKm4d2a947mahtfrv87su8mw61v` FOREIGN KEY (`id_bus_route`) REFERENCES `busroute` (`id`);

--
-- Các ràng buộc cho bảng `changehistoryticket`
--
ALTER TABLE `changehistoryticket`
  ADD CONSTRAINT `FKfyol6dl74mtmoi7cs57t9pki4` FOREIGN KEY (`id_ticket`) REFERENCES `ticket` (`id`),
  ADD CONSTRAINT `FKpc0o5ye7vr5g29sbcmyrl2xdg` FOREIGN KEY (`id_bus_trip`) REFERENCES `bustrip` (`id`),
  ADD CONSTRAINT `FKqhfet3fu0621nqbgh7l88if2s` FOREIGN KEY (`id_seat_positon`) REFERENCES `seatposition` (`id`);

--
-- Các ràng buộc cho bảng `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `FKbub7c17bl8pbefd91y21ovrr3` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKqp7aa8tsj8u4adxd7ipro5sg9` FOREIGN KEY (`id_bus_trip`) REFERENCES `bustrip` (`id`);

--
-- Các ràng buộc cho bảng `route`
--
ALTER TABLE `route`
  ADD CONSTRAINT `FKbe1dao21ycwf60bdl0vjxfdjs` FOREIGN KEY (`id_bus_route`) REFERENCES `busroute` (`id`),
  ADD CONSTRAINT `FKmmpp2erj89cbs91b3flhu3kq` FOREIGN KEY (`id_bus_station`) REFERENCES `busstations` (`id`);

--
-- Các ràng buộc cho bảng `seatposition`
--
ALTER TABLE `seatposition`
  ADD CONSTRAINT `FKtc6nabxm16pxgrjeh2l90bx9t` FOREIGN KEY (`id_bus`) REFERENCES `bus` (`id`);

--
-- Các ràng buộc cho bảng `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `FK12j2qlekqc5lsijmr2pjlbiur` FOREIGN KEY (`id_invoice`) REFERENCES `invoice` (`id`),
  ADD CONSTRAINT `FK1my700hbodyyhdrrmwp7l3626` FOREIGN KEY (`id_seat_position`) REFERENCES `seatposition` (`id`);

--
-- Các ràng buộc cho bảng `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKol9lgnwinqxhgm253ffpxrj6k` FOREIGN KEY (`id_account`) REFERENCES `account` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
