-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-09-2022 a las 11:48:32
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.2

START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `feliandres_bdd`
--
CREATE DATABASE IF NOT EXISTS `feliandres_bdd` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE feliandres_bdd;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ajustes`
--

DROP TABLE IF EXISTS `ajustes`;
CREATE TABLE `ajustes` (
  `id_farmacia` int(11) NOT NULL,
  `ruc_farmacia` varchar(12) NOT NULL,
  `nom_farmacia` varchar(50) NOT NULL,
  `telf_farmacia` varchar(12) NOT NULL,
  `direc_farmacia` varchar(100) NOT NULL,
  `mensaje_farmacia` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ajustes`
--

INSERT INTO `ajustes` (`id_farmacia`, `ruc_farmacia`, `nom_farmacia`, `telf_farmacia`, `direc_farmacia`, `mensaje_farmacia`) VALUES
(123123222, '1919282842', 'FARMACIA \"EL ALIVIO\"', '022255420', 'AV. ELOY ALFARO Y JUAN MOLINEROS', 'GRACIAS POR TU COMPRA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `dni_cli` varchar(10) NOT NULL,
  `nom_cli` varchar(30) NOT NULL,
  `apel_cli` varchar(15) NOT NULL,
  `direc_cli` varchar(30) NOT NULL,
  `email_cli` varchar(50) NOT NULL,
  `telf_cli` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`dni_cli`, `nom_cli`, `apel_cli`, `direc_cli`, `email_cli`, `telf_cli`) VALUES
('1231232131', 'fefe', 'fefe', 'fefefefefe', 'fefe@fefe.com', '1234123211'),
('1231232132', 'cami', 'vanegas', 'eloy alfaro', 'cami.vanegas@gmail.com', '2312312323'),
('1728280927', 'Carlos', 'Guanin', 'Cuenca', 'carlos@gmail.com', '0983727262'),
('1728280928', 'Carla', 'Hurtado', 'Quinche', 'carla@gmail.com', '0983727224');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

DROP TABLE IF EXISTS `detalle`;
CREATE TABLE `detalle` (
  `id_detalle` int(11) NOT NULL,
  `cod_prod` varchar(10) NOT NULL,
  `cantidad_prod` int(10) NOT NULL,
  `pvp_prod` decimal(5,2) NOT NULL,
  `id_venta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalle`
--

INSERT INTO `detalle` (`id_detalle`, `cod_prod`, `cantidad_prod`, `pvp_prod`, `id_venta`) VALUES
(1, 'PFR0001', 2, '1.00', 1),
(2, 'PFR0006', 6, '36.72', 1),
(3, 'PFR0122', 4, '20.48', 1),
(4, 'PFR0004', 2, '20.00', 2),
(5, 'PFR0008', 3, '15.00', 3),
(6, 'PFR0007', 5, '5.75', 4),
(7, 'PFR0001', 2, '1.00', 4),
(8, 'PFR0007', 1, '1.15', 5),
(9, 'PFR0008', 28, '140.00', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

DROP TABLE IF EXISTS `empleados`;
CREATE TABLE `empleados` (
  `dni_emp` varchar(10) NOT NULL,
  `nom_emp` varchar(30) NOT NULL,
  `apel_emp` varchar(15) NOT NULL,
  `username_emp` varchar(20) NOT NULL,
  `telf_emp` varchar(10) NOT NULL,
  `email_emp` varchar(50) NOT NULL,
  `pass_emp` varchar(30) NOT NULL,
  `conf_pass_emp` varchar(30) NOT NULL,
  `cod_rol` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`dni_emp`, `nom_emp`, `apel_emp`, `username_emp`, `telf_emp`, `email_emp`, `pass_emp`, `conf_pass_emp`, `cod_rol`) VALUES
('1725259152', 'felipe', 'pazmino', 'feliandres21', '0984507476', 'fefe@gmail.com', '123', '123', 'admi'),
('1725259153', 'andres', 'rodriguez', 'andres122', '0984508989', 'andres@gmail.com', '123', '123', 'caje'),
('1725259199', 'Luis', 'Suarez', 'Luis1234', '0984508111', 'luis@gmail.com', '123', '123', 'caje');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `cod_prod` varchar(10) NOT NULL,
  `nom_prod` varchar(30) NOT NULL,
  `ruc_prov` varchar(15) NOT NULL,
  `descrip_prod` varchar(100) NOT NULL,
  `stock_prod` int(10) NOT NULL,
  `pvp_prod` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`cod_prod`, `nom_prod`, `ruc_prov`, `descrip_prod`, `stock_prod`, `pvp_prod`) VALUES
('PFR0001', 'Levonorgestrel + etinilestradi', '992459212001', 'S?lido oral 150 mcg + 30 mcg', 192465, '0.50'),
('PFR0004', 'Ciclofosfamida', '1791253551001', 'S?lido parenteral 1.000 mg', 1439, '10.00'),
('PFR0005', 'arbohidratos (Dextrosa en agua', '1791253531001', 'L?quido\nparenteral 50 % (500 ml)', 6215, '1.00'),
('PFR0006', 'Bupivaca?na sin epinefrina', '990689768001', 'L?quido\nparenteral 0.5 %', 10570, '6.12'),
('PFR0007', 'Clomifeno', '990689768001', 'S?lido oral 50 mg', 19140, '1.15'),
('PFR0008', 'initrato de glicerilo (Nitrogl', '991312080001', 'L?quido parenteral 5 mg/ml', 463, '5.00'),
('PFR0010', 'Atropina', '991312080001', 'L?quido parenteral 1 mg/ml', 74477, '2.23'),
('PFR0011', 'Calcio gluconato', '991312080001', 'L?quido parenteral 0,1', 30570, '0.99'),
('PFR0012', 'Citarabina', '991312080001', 'S?lido parenteral 500 mg', 75, '7.00'),
('PFR0013', 'Dopamina', '991312080001', 'L?quido parenteral 40 mg/ml', 3697, '23.11'),
('PFR0014', 'Fentanilo', '991312080001', 'L?quido parenteral 0.5 mg/10ml', 39681, '1.00'),
('PFR0015', 'Oligoelementos', '991312080001', 'L?quido parenteral ', 6515, '1.00'),
('PFR0016', 'Oxitocina', '991312080001', 'L?quido parenteral 10 UI/ml', 14998, '12.34'),
('PFR0018', 'diol valerato + noretisterona ', '1791888146001', 'L?quido parenteral (5 mg + 50 mg)/ml', 11146, '1.00'),
('PFR0019', 'Heparina no fraccionada', '1791888146001', 'L?quido parenteral 5.000 UI/ml', 10325, '3.00'),
('PFR0020', 'Micofenolato', '1791888146001', 'S?lido oral 250 mg', 26532, '1.00'),
('PFR0021', 'Fluoruracilo', '991445242001', 'L?quido Parenteral 25 mg/ml', 452, '2.00'),
('PFR0022', 'Colistina', '991445242001', 'S?lido parenteral 100 mg', 3515, '23.00'),
('PFR0023', 'Aciclovir', '1768152130001', 'L?quido oral 200mg/5ml', 4425, '5.00'),
('PFR0024', 'Digoxina', '1768152130001', 'S?lido oral 250 mcg', 171857, '7.77'),
('PFR0025', 'Doxiciclina', '1768152130001', 'S?lido oral 100 mg', 146508, '8.88'),
('PFR0026', 'Enalapril', '1768152130001', 'S?lido oral 5 mg', 221008, '9.99'),
('PFR0027', 'Haloperidol', '1768152130001', 'S?lido oral 5 mg', 18677, '11.22'),
('PFR0028', 'Levonorgestrel', '1768152130001', 'S?lido oral 0,030 mg', 182905, '34.32'),
('PFR0029', 'Betametasona', '17924887610001', 'Semis?lido\ncut?neo 0,001', 18051, '4.00'),
('PFR0030', 'Citarabina', '179248876001', 'S?lido parenteral 1.000 mg', 1278, '13.00'),
('PFR0031', 'Beclometasona', '1792488761001', 'L?quido para\ninhalaci?n 50 mcg/dosis', 4140, '4.00'),
('PFR0032', 'Beclometasona', '1792488761001', 'L?quido para\ninhalaci?n 250 mcg/dosis', 19937, '5.00'),
('PFR0033', 'Bupivaca?na hiperb?rica', '1792488761001', 'Soluci?n\ninyectable 0.5 % 4ml', 17427, '1.00'),
('PFR0034', 'Digoxina', '1792488761001', 'L?quido\nparenteral 0.25 mg/ml', 12955, '2.00'),
('PFR0035', 'Hidrocortisona', '1792488761001', 'Crema 0,01', 20853, '2.00'),
('PFR0036', 'Hidrocortisona', '1792488761001', 'Semis?lido cut?neo/ l?quido cut?neo 0,01', 10101, '1.00'),
('PFR0037', 'Levonorgestrel', '1792488761001', 'S?lido oral 0.75 mg', 20204, '1.00'),
('PFR0038', 'Propofol', '1792488761001', 'L?quido\nparenteral 20mg/ml', 17199, '64.00'),
('PFR0039', 'Amoxicilina', '1792488761001', 'S?lido oral\n(polvo) 100 mg/ml', 18631, '1.00'),
('PFR0040', 'Amoxicilina + ?cido clavul?nic', '1792488761001', 'S?lido parenteral 1.000 mg + 200 mg', 43891, '1.00'),
('PFR0041', 'Doxorubicina', '1792488761001', 'S?lido parenteral 50 mg', 885, '8.00'),
('PFR0042', 'Levodopa + Carbidopa', '1792488761001', 'S?lido oral 100 mg + 10 mg', 139000, '13.30'),
('PFR0043', 'Levofloxacina', '1792488761001', 'L?quido\nparenteral 500 mg/ 100ml', 8044, '3.00'),
('PFR0044', 'Meropenem', '1792488761001', 'S?lido parenteral 500 mg', 9468, '3.00'),
('PFR0045', 'Metoclopramida', '1792488761001', 'L?quido\nparenteral 5 mg/ml', 276302, '1.50'),
('PFR0046', 'Omeprazol', '1792488761001', 'S?lido parenteral 40 mg', 614589, '1.00'),
('PFR0047', 'Estreptoquinasa', '1792488761001', 'S?lido parenteral 1?500.000 UI', 333, '224.00'),
('PFR0048', 'Ferroso sulfato', '992412755001', 'S?lido oral 50mg', 219750, '2.22'),
('PFR0049', 'Trolamina', '992216972001', 'L?quido cut?neo 0,67 g/100 g', 4407, '11.00'),
('PFR0050', 'Ciprofloxacina', '1792066921001', 'L?quido\nparenteral 20 mg/ml', 11150, '1.00'),
('PFR0051', 'Colistina', '1792066921001', 'S?lido parenteral 100 mg', 3515, '16.00'),
('PFR0052', 'Vancomicina', '1792066921001', 'S?lido parenteral 500 mg', 19723, '2.00'),
('PFR0053', 'Diclofenaco', '1792029368001', 'L?quido oft?lmico 0.1 %', 8459, '1.00'),
('PFR0054', 'Eritropoyetina', '1792029368001', 'L?quido o s?lido parenteral 2.000 UI', 28038, '3.00'),
('PFR0055', 'Eritropoyetina', '1792029368001', 'L?quido o s?lido parenteral 5000 UI', 88631, '14.00'),
('PFR0056', 'as artificiales y otros prepar', '1792029368001', 'L?quido oft?lmico ', 118113, '1.00'),
('PFR0057', 'Fitomenadiona', '1792029368001', 'L?quido\nparenteral 10 mg/ml', 28840, '8.77'),
('PFR0058', 'Clotrimazol', '990160422001', 'S?lido vaginal 200 mg', 40288, '6.66'),
('PFR0059', 'cilina benzat?nica (Penicilina', '701189359001', 'S?lido parenteral 600.000 UI', 12602, '1.00'),
('PFR0060', 'Betametasona', '701189359001', 'L?quido cut?neo 0,001', 3640, '1.00'),
('PFR0061', 'Clotrimazol', '701189359001', 'Semis?lido vaginal 0,01', 6558, '1.00'),
('PFR0062', 'Tiamina B1?Piridoxina B6?Ciano', '701189359001', 'Polvo parenteral ? 200 mg\n? 50 mg\n? 0,030 mg', 8614, '1.00'),
('PFR0063', '?cido f?lico', '701189359001', 'S?lido oral 1 mg', 2150900, '1.12'),
('PFR0064', 'Ampicilina', '701189359001', 'S?lido parenteral 1.000 mg', 36026, '24.40'),
('PFR0065', 'Betametasona', '701189359001', 'L?quido parenteral 4 mg/ml', 6725, '2.21'),
('PFR0066', 'Ferroso sulfato', '701189359001', 'L?quido oral 25 - 50 mg/5 ml (equivalente a\nhierro elemental)', 39985, '5.00'),
('PFR0067', 'Furosemida', '701189359001', 'S?lido oral 40 mg', 943296, '5.55'),
('PFR0068', 'Furosemida', '701189359001', 'L?quido parenteral 10mg /ml', 107480, '1.80'),
('PFR0069', 'Nifedipina', '701189359001', 'S?lido oral 10 mg', 126287, '2.09'),
('PFR0070', 'Retinol (Vitamina A)', '701189359001', 'S?lido oral 50 000 UI', 47340, '3.03'),
('PFR0071', 'Atropina', '992689943001', 'L?quido\nparenteral 1 mg/ml', 74477, '4.12'),
('PFR0072', 'Bupivaca?na hiperb?rica', '1790750892001', 'L?quido\nparenteral 0.75 %', 1773, '3.00'),
('PFR0073', 'Bupivaca?na sin epinefrina', '1790750892001', 'L?quido\nparenteral 0.75 %', 220, '1.00'),
('PFR0074', 'Lidoca?na', '1790750892001', 'Semis?lido\ncut?neo 0,02', 4578, '2.00'),
('PFR0075', 'Lidoca?na', '1790750892001', 'Semis?lido\ncut?neo 0,05', 1064, '1.00'),
('PFR0076', 'Per?xido de Benzoilo', '990987874001', 'Semis?lido cut?neo o l?quido cut?neo 0,05', 3270, '3.00'),
('PFR0077', 'Per?xido de Benzoilo', '990987874001', 'Semis?lido cut?neo o l?quido cut?neo 0,1', 1920, '5.00'),
('PFR0078', 'Metoclopramida', '992539895001', 'S?lido oral 10 mg', 334440, '1.12'),
('PFR0079', 'Ceftriaxona', '992539895001', 'S?lido parenteral 500 mg', 491875, '1.00'),
('PFR0080', 'Amfotericina B', '400649356001', 'S?lido parenteral 50 mg', 5222, '1.00'),
('PFR0081', 'Ceftazidima', '400649356001', 'S?lido parenteral 1.000 mg', 1670, '8.00'),
('PFR0082', 'Biperideno', '400649356001', 'S?lido oral 2 mg', 24257, '1.00'),
('PFR0083', 'Ferroso sulfato', '990347654001', 'L?quido oral 25 mg/ml (equivalente a\nhierro elemental)', 426128, '4.09'),
('PFR0084', 'Carvedilol', '990347654001', 'S?lido oral 6.25 mg', 27757, '3.05'),
('PFR0085', 'Salbutamol', '1791224493001', 'L?quido para inhalaci?n 0.1 mg/dosis', 259619, '2.00'),
('PFR0086', '?cido fus?dico', '1790013502001', 'Semis?lido\ncut?neo 0,02', 46936, '2.00'),
('PFR0087', 'Haloperidol', '1791897498001', 'S?lido oral 5 mg', 48730, '1.00'),
('PFR0088', 'Dicloxacilina', '1791897498001', 'S?lido oral\n(polvo) 125 mg/5 ml', 18677, '3.00'),
('PFR0089', 'Hidrocortisona, succinato s?di', '990000360001', 'S?lido parenteral 100 mg', 1218, '1.00'),
('PFR0090', 'Dapsona', '1791881915001', 'S?lido oral 100 mg', 44788, '4.00'),
('PFR0092', 'Inmunoglobulina antitet?nica', '992262192001', 'L?quido parenteral/ s?lido parenteral 250 UI', 573, '31.00'),
('PFR0093', 'nmunoglobulina humana norma', '992262192001', 'Soluci?n\ninyectable 0.45 g/ml', 50, '32.00'),
('PFR0094', 'Levotiroxina s?dica', '992262192001', 'S?lido oral 0,088 mg', 168, '452.00'),
('PFR0095', 'Levotiroxina s?dica', '992262192001', 'S?lido oral 0,112 mg', 36000, '5.00'),
('PFR0096', 'Ciprofloxacina', '992262192001', 'L?quido\nparenteral 20 mg/ml', 33000, '6.00'),
('PFR0097', 'Paracetamol', '1792193362001', 'L?quido\nparenteral 10 mg/ml', 11150, '1.00'),
('PFR0098', 'Tinidazol', '1792193362001', 'S?lido vaginal 150 mg', 74533, '4.00'),
('PFR0099', 'Ferroso sulfato', '1792193362001', 'S?lido oral 100 mg', 108348, '7.00'),
('PFR0100', 'Levotiroxina s?dica', '1711806438001', 'S?lido Oral 0.150 mg', 701400, '8.00'),
('PFR0101', 'Metformina', '1790001024001', 'S?lido oral 750 mg', 15534, '9.00'),
('PFR0102', 'Metformina + glibenclamida', '1790001024001', 'S?lido oral 250 mg + 1.25 mg', 1500, '2.22'),
('PFR0103', 'Levotiroxina s?dica', '1790001024001', 'S?lido oral 0,125 mg', 56033, '1.11'),
('PFR0104', 'Caspofungina', '1790001024001', 'S?lido parenteral 70 mg', 34000, '3.09'),
('PFR0106', 'Sunitinib', '990101176001', 'S?lido oral 12.5 mg', 81, '425.00'),
('PFR0107', 'Etanercep', '990014825001', 'L?quido\nparenteral 25 mg', 576, '93.00'),
('PFR0108', 'Metilprednisolona, succinato', '990014825001', 'S?lido parenteral 500 mg', 40, '146.00'),
('PFR0109', 'Amitriptilina', '990014825001', 'S?lido oral 25 mg', 2477, '23.00'),
('PFR0110', 'Biperideno', '992316861001', 'S?lido oral 2 mg', 873733, '2.23'),
('PFR0111', 'Clonazepam', '992316861001', 'S?lido oral 0.5mg', 426128, '1.12'),
('PFR0112', 'Adapaleno', '1790371506001', 'Semis?lido\ncut?neo 0.1 %', 58534, '0.62'),
('PFR0113', 'Agentes gelatinas (poligelina)', '1790371506001', 'L?quido\nparenteral 0,035', 2250, '4.00'),
('PFR0114', 'Testosterona', '1790371506001', 'L?quido\nparenteral 250 mg/ml', 7190, '9.00'),
('PFR0115', 'Carvedilol', '1790371506001', 'S?lido oral 12.5 mg', 767, '2.00'),
('PFR0116', 'Dornasa Alfa', '1790371506001', 'L?quido para\ninhalaci?n 2.5mg/2.5ml (2500\nUI)', 389627, '0.66'),
('PFR0117', 'Levonorgestrel', '1790371506001', 'S?lido oral 1,5 mg', 540, '24.00'),
('PFR0118', 'Midazolam', '1790371506001', 'L?quido\nparenteral 1 mg/ml', 6105, '4.00'),
('PFR0119', 'Pegfilgrastrim', '1790371506001', 'L?quido\nparenteral 10 mg/ml', 3770, '1.00'),
('PFR0120', 'Desmopresina', '1790371506001', 'L?quido\nparenteral 15 mcg/ml', 456, '589.00'),
('PFR0121', 'Tenofovir (disoproxilo)', '1790371506001', 'S?lido oral 300 mg', 665, '14.00'),
('PFR0122', 'Diafloxileno', '400649356001', 'Topico humectante 50mg', 300, '5.12'),
('PFR0124', 'Desmopresina zerox', '1790371506001', 'L?quido parenteral 15 mcg/ml', 4561, '589.99');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
CREATE TABLE `proveedores` (
  `ruc_prov` varchar(15) NOT NULL,
  `nom_prov` varchar(50) NOT NULL,
  `direc_prov` varchar(50) NOT NULL,
  `telf_prov` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`ruc_prov`, `nom_prov`, `direc_prov`, `telf_prov`) VALUES
('1711806438001', 'MASTERCORP', 'CCI', '982736490'),
('1768152130001', 'ENFARMA EP', 'COTOCOLLAO', '982736461'),
('1790001024001', 'MERCK C.A.', 'CARCELEN', '9982736491'),
('1790013502001', 'Laboratorios Industriales Farmaceuticos Ecuatorian', 'AV. PATRIA', '982736483'),
('1790371506001', 'QUIFATEX', 'LA ROLDOS', '982736496'),
('1790750892001', 'HOSPIMEDIKKA CIA\nLTDA', 'SAN JUAN', '982736476'),
('1791224493001', 'LABORATORIOS BAGO DEL ECUADOR\nS.A.', 'SAN LORENZO', '982736482'),
('1791253531001', 'BAXTER ECUADOR\nS.A.', 'CARCELEN', '982736453'),
('1791253551001', 'BAXTER ECUADOR\nS.A.', 'CUMBAYA', '982736454'),
('1791881915001', 'LABVITALIS S.A.', 'EL PINTADO', '982736486'),
('1791888146001', 'CORPORACION\nMAGMA ECUADOR S.A.', 'LA ROLDOS', '982736458'),
('1791897498001', 'LABORATORIOS\nSIEGFRIED S.A.', 'SAN MIGUEL', '982736484'),
('1792029368001', 'GINSBERG ECUADOR\nS.A.', 'LA KENNEDY', '982736469'),
('1792066921001', 'GENECOM CIA. LTDA.', 'LA FLORIDA', '982736468'),
('1792193362001', 'LIMERICKPHARMA\nCIA. LTDA.', 'BICENTENARIO', '982736489'),
('179248876001', 'FARMABION DEL\nECUADOR C.A', 'SAN LORENZO', '982736463'),
('17924887610001', 'FARMABION DEL\nECUADOR C.A', 'COMITE DEL PUEBLO', '982736462'),
('1792488761001', 'FARMABION DEL\nECUADOR C.A', 'AV. PATRIA', '982736464'),
('400649356001', 'JORGE RICARDO\nSALAZAR URRESTA', 'COTOCOLLAO', '982736480'),
('701189359001', 'GYBA MEDICA (JOSE\nGONZALO APOLO APOLO)', 'CARCELEN', '982736472'),
('990000360001', 'LABORATORIOS TOFIS\nS.A.', 'GUAMANI', '982736485'),
('990014825001', 'PFIZER CIA. LTDA.', 'EL VALLE', '982736494'),
('990101176001', 'MERCK SHARP &\nDOHME', 'CUMBAYA', '982736492'),
('990160422001', 'GRUNENTHAL ECUATORIANA C?A.\nLTDA.', 'CCI', '982736471'),
('990347654001', 'KRONOS LABORATORIOS\nC.LTDA.', 'COMITE DEL PUEBLO', '982736481'),
('990689768001', 'COMERCIOSA S.A,', 'CONOCOTO', '982736455'),
('990987874001', 'Industria de Belleza y Salud BASSA C. Ltda.', 'LA ROLDOS', '982736477'),
('991312080001', 'CORPORACION\nFARMACEUTICA MEDISUMI S.A.', 'EL VALLE', '982736456'),
('991445242001', 'Diempec C?a. Ltda. Distribuidora\nFarmac?utica', 'PISULI', '982736460'),
('992216972001', 'GALIAFARM S.A.', 'EL PINTADO', '982736467'),
('992262192001', 'LETERAGO DEL\nECUADOR S.A.', 'LA FLORIDA', '982736487'),
('992316861001', 'PSICOFARMA DEL\nECUADOR (PSICODELCU)', 'SAN JUAN', '982736495'),
('992412755001', 'FARMALIGHT S.A.', 'GUAMANI', '982736466'),
('992459212001', 'Baxleygroup S.A.', 'AV.EL CONDADO', '982736452'),
('992539895001', 'ITALCHEM ECUADOR\nS.A.', 'CENTRO NORTE', '982736478'),
('992689943001', 'GYKORMED S.A.', 'EL VALLE', '982736475');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `cod_rol` varchar(4) NOT NULL,
  `tipo_rol` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`cod_rol`, `tipo_rol`) VALUES
('admi', 'administrador'),
('bode', 'bodeguero'),
('caje', 'cajero');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

DROP TABLE IF EXISTS `ventas`;
CREATE TABLE `ventas` (
  `id_venta` int(11) NOT NULL,
  `dni_cli` varchar(10) NOT NULL,
  `nom_cli` varchar(30) NOT NULL,
  `nom_emp` varchar(30) NOT NULL,
  `total_venta` decimal(5,2) NOT NULL,
  `fecha_venta` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id_venta`, `dni_cli`, `nom_cli`, `nom_emp`, `total_venta`, `fecha_venta`) VALUES
(1, '1231232132', 'cami vanegas', 'felipe pazmino', '42.25', '2022-09-06'),
(2, '1728280928', 'Carla Hurtado', 'felipe pazmino', '22.40', '2022-09-06'),
(3, '1728280928', 'Carla Hurtado', 'felipe pazmino', '16.80', '2022-09-06'),
(4, '1728280928', 'Carla Hurtado', 'felipe pazmino', '6.75', '2022-09-06'),
(5, '1728280928', 'Carla Hurtado', 'felipe pazmino', '141.15', '2022-09-06');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ajustes`
--
ALTER TABLE `ajustes`
  ADD PRIMARY KEY (`id_farmacia`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`dni_cli`) USING BTREE;

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `FK_cod_prod` (`cod_prod`),
  ADD KEY `FK_id_venta` (`id_venta`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`dni_emp`),
  ADD KEY `FK_cod_rol` (`cod_rol`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`cod_prod`),
  ADD KEY `FK_ruc_prov` (`ruc_prov`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`ruc_prov`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`cod_rol`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `FK_dni_cli` (`dni_cli`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ajustes`
--
ALTER TABLE `ajustes`
  MODIFY `id_farmacia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=123123223;

--
-- AUTO_INCREMENT de la tabla `detalle`
--
ALTER TABLE `detalle`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD CONSTRAINT `FK_cod_prod` FOREIGN KEY (`cod_prod`) REFERENCES `productos` (`cod_prod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_id_venta` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD CONSTRAINT `FK_cod_rol` FOREIGN KEY (`cod_rol`) REFERENCES `roles` (`cod_rol`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `FK_ruc_prov` FOREIGN KEY (`ruc_prov`) REFERENCES `proveedores` (`ruc_prov`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `FK_dni_cli` FOREIGN KEY (`dni_cli`) REFERENCES `clientes` (`dni_cli`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
