create database db_pessoa;
use db_pessoa;

create table tb_endereco_pessoa(
id_endereco_pessoa int8 auto_increment primary key,
cidade varchar(100),
bairro varchar(100),
rua varchar(150),
numero varchar(20),
cep varchar(8)
);

create table tb_pessoa(
id_pessoa int8 auto_increment primary key,
nome varchar(100),
idade int8,
celular varchar(20),
sexo varchar(1),
id_endereco_pessoa int8,
constraint fk_id_endereco_pessoa foreign key (id_endereco_pessoa) references tb_endereco_pessoa (id_endereco_pessoa)
);
