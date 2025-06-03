-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-06-2025 a las 02:59:58
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `medicos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicos`
--

CREATE TABLE `medicos` (
  `codiMedi` int(11) NOT NULL,
  `ndniMedi` varchar(8) NOT NULL,
  `appaMedi` varchar(50) NOT NULL,
  `apmaMedi` varchar(50) DEFAULT NULL,
  `nombMedi` varchar(50) NOT NULL,
  `fechNaciMedi` date DEFAULT NULL,
  `logiMedi` varchar(100) NOT NULL,
  `passMedi` varchar(500) NOT NULL,
  `secret2fa` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `medicos`
--

INSERT INTO `medicos` (`codiMedi`, `ndniMedi`, `appaMedi`, `apmaMedi`, `nombMedi`, `fechNaciMedi`, `logiMedi`, `passMedi`, `secret2fa`) VALUES
(1, '71922277', 'Garcia', 'Palomino', 'Alessandro', '2025-06-13', 'alex', '$2a$12$ZUYMNHRdJtno5FDTjqtgb.OUAbIUdIRJ.EDBXL42oMzIWUNxwFLRS', NULL),
(2, '71922276', 'Garcia ', 'Palomino', 'Lady', '2025-06-14', 'lady', '$2a$10$z.Bjem4z/c2F6o451SI.KuwIKzTWg9As8rpwrpZDqzKH6mDe95MOm', NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `medicos`
--
ALTER TABLE `medicos`
  ADD PRIMARY KEY (`codiMedi`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `medicos`
--
ALTER TABLE `medicos`
  MODIFY `codiMedi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
