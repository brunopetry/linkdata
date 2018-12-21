-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.6.25 - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.3.0.4984
-- --------------------------------------------------------

-- Copiando estrutura do banco de dados para linkdata
DROP DATABASE IF EXISTS `linkdata`;
CREATE DATABASE IF NOT EXISTS `linkdata`;
USE `linkdata`;

-- Copiando estrutura para tabela linkdata.empresa
DROP TABLE IF EXISTS `empresa`;
CREATE TABLE IF NOT EXISTS `empresa` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(14) NOT NULL,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela linkdata.empresa: ~3 rows (aproximadamente)
INSERT INTO `empresa` (`codigo`, `cnpj`, `nome`) VALUES
	(1, '61515171000148', 'Casas Bahia'),
	(2, '15203666000114', 'Ricardo Eletro'),
	(3, '47885115000174', 'Novo Mundo');


-- Copiando estrutura para tabela linkdata.funcionario
DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE IF NOT EXISTS `funcionario` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `codigo_empresa` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FKi1fyakdapwbsiahmdsvhsknvd` (`codigo_empresa`),
  CONSTRAINT `FKi1fyakdapwbsiahmdsvhsknvd` FOREIGN KEY (`codigo_empresa`) REFERENCES `empresa` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela linkdata.funcionario: ~5 rows (aproximadamente)
INSERT INTO `funcionario` (`codigo`, `cpf`, `email`, `nome`, `codigo_empresa`) VALUES
	(1, '11587818221', 'adelina@gmail.com', 'Adelina Ginjeira', 1),
	(2, '31345987102', 'cecilia@hotmail.com', 'Cecília Sucupira', 1),
	(3, '28732743874', 'murilo@ig.com.br', 'Murilo Póvoas', 1),
	(4, '08388882830', 'justino@gmail.com', 'Justino Gama', 2),
	(5, '80138316392', 'marlene@terra.com.br', 'Marlene César', 2);

