CREATE DATABASE loja;

USE loja;

CREATE TABLE cliente (
	id int(10) unsigned NOT NULL AUTO_INCREMENT,
	nome varchar(50) NOT NULL,
	telefone varchar(13) NOT NULL,
	email varchar(50) NOT NULL,
	dtaNasc datetime NOT NULL,
	senha varchar(10) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE fornecedor (
	id int(10) unsigned NOT NULL AUTO_INCREMENT,
	nome varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	senha varchar(10) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE categoria (
	id int(10) unsigned NOT NULL AUTO_INCREMENT,
	nome varchar(50) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE funcionario (
	id int(10) unsigned NOT NULL AUTO_INCREMENT,
	nome varchar(50) NOT NULL,
	telefone varchar(13) NOT NULL,
	email varchar(50) NOT NULL,
	dtaNasc datetime NOT NULL,
	senha varchar(10) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE produto (
	id int(10) unsigned NOT NULL AUTO_INCREMENT,
	modelo varchar(50) NOT NULL,
	descricao varchar(13) NOT NULL,
	valor float NOT NULL,
	qtdEstoque int NOT NULL,
	idFornecedor int(10) unsigned NOT NULL,
	idCategoria int(10) unsigned NOT NULL,
	PRIMARY KEY (id),
	KEY fk_produto_fornecedor (idFornecedor),
	CONSTRAINT fk_produto_fornecedor FOREIGN KEY (idFornecedor) REFERENCES fornecedor(id),
	KEY fk_produto_categoria (idCategoria),
	CONSTRAINT fk_produto_categoria FOREIGN KEY (idCategoria) REFERENCES categoria(id)
);

CREATE TABLE venda (
	id int(10) unsigned NOT NULL AUTO_INCREMENT,
	dtaNasc datetime NOT NULL,
	idCliente int(10) unsigned NOT NULL,
	PRIMARY KEY (id),
	KEY fk_venda_cliente (idCliente),
	CONSTRAINT fk_venda_cliente FOREIGN KEY (idCliente) REFERENCES cliente(id)
);

CREATE TABLE vendaProdutos (
	id int(10) unsigned NOT NULL AUTO_INCREMENT,
	quantidade int NOT NULL,
	valor float NOT NULL,
	idVenda int(10) unsigned NOT NULL,
	idProduto int(10) unsigned NOT NULL,
	PRIMARY KEY (id),
	KEY fk_vendaProdutos_venda (idVenda),
	CONSTRAINT fk_vendaProdutos_venda FOREIGN KEY (idVenda) REFERENCES venda(id),
	KEY fk_vendaProdutos_produto (idProduto),
	CONSTRAINT fk_vendaProdutos_produto FOREIGN KEY (idProduto) REFERENCES produto(id)
);