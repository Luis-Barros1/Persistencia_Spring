create table usuario(
    id int not null primary key auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null,
    senha varchar(100) not null
);
INSERT INTO USUARIO(nome, email, senha) VALUES ('Rafael Moreno','Rafael.Moreno@teste.com','123321');