-- Criação do banco de dados
DROP DATABASE IF EXISTS MyMovieList;
CREATE DATABASE IF NOT EXISTS MyMovieList;
USE MyMovieList;

-- Criação das tabelas

-- Tabela de Sinopse
-- Uma sinopse existe só para um filme
CREATE TABLE tb_sinopse (
    id_sinopse INT AUTO_INCREMENT PRIMARY KEY,
    descricao_sinopse TEXT
);

-- Tabela de Diretor
-- Um diretor pode estar em mais de um filme. Mas um filme só pode ter um diretor
CREATE TABLE tb_diretor (
    id_diretor INT AUTO_INCREMENT PRIMARY KEY,
    nome_diretor VARCHAR(255) NOT NULL,
    data_nascimento_diretor VARCHAR(255)
);

-- Tabela de Filmes
-- Um filme pode ter um diretor, uma sinopse, uma imagem.
CREATE TABLE tb_filme (
    id_filme INT AUTO_INCREMENT PRIMARY KEY,
    nome_filme VARCHAR(255) NOT NULL,
    score_filme DECIMAL(3, 2),
    logo_filme TEXT,
    fk_sinopse INT,
    fk_diretor INT,
    FOREIGN KEY (fk_sinopse) REFERENCES tb_sinopse(id_sinopse),
    FOREIGN KEY (fk_diretor) REFERENCES tb_diretor(id_diretor)
);

-- Tabela de Usuários
-- Um usuário pode fazer diversas reviews. Mas uma review só pode ser feita por 
-- um único usuários



CREATE TABLE tb_usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome_usuario VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL, 
    email_usuario VARCHAR(255) UNIQUE NOT NULL
);

-- Tabela de Reviews
-- Há somente um usuário e um filme em uma review. 
CREATE TABLE tb_review (
    id_review INT AUTO_INCREMENT PRIMARY KEY,
    fk_usuario INT,
    fk_filme INT,
    estrela INT CHECK (estrela BETWEEN 1 AND 5),
    comentario TEXT,
    FOREIGN KEY (fk_usuario) REFERENCES tb_usuario(id_usuario),
    FOREIGN KEY (fk_filme) REFERENCES tb_filme(id_filme)
);

-- Tabela de Logs
CREATE TABLE tb_logs (
    id_log INT AUTO_INCREMENT PRIMARY KEY,
    tabela_afetada VARCHAR(255),
    operacao_realizada VARCHAR(50), 
    dado_anterior TEXT,
    dado_posterior TEXT,
    fk_operador INT,
    data_hora_operacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_operador) REFERENCES tb_usuario(id_usuario)
);

DELIMITER $$
CREATE TRIGGER registra_usuario_insert
AFTER INSERT ON tb_usuario 
FOR EACH ROW 
BEGIN
    INSERT INTO tb_logs (tabela_afetada, operacao_realizada, dado_anterior, dado_posterior, fk_operador)
    VALUES (
        'tb_usuario', 
        'INSERT', 
        NULL, 
        CONCAT('Nome: ', NEW.nome_usuario, ', Email: ', NEW.email_usuario), 
        NEW.id_usuario
    );
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER registra_usuario_update
BEFORE UPDATE ON tb_usuario 
FOR EACH ROW 
BEGIN
    INSERT INTO tb_logs (tabela_afetada, operacao_realizada, dado_anterior, dado_posterior, fk_operador)
    VALUES (
        'tb_usuario', 
        'UPDATE', 
        CONCAT('Nome: ', OLD.nome_usuario, ', Email: ', OLD.email_usuario), 
        CONCAT('Nome: ', NEW.nome_usuario, ', Email: ', NEW.email_usuario), 
        NEW.id_usuario
    );
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER registra_usuario_delete
BEFORE DELETE ON tb_usuario 
FOR EACH ROW 
BEGIN
    INSERT INTO tb_logs (tabela_afetada, operacao_realizada, dado_anterior, dado_posterior, fk_operador)
    VALUES (
        'tb_usuario', 
        'DELETE', 
        CONCAT('Nome: ', OLD.nome_usuario, ', Email: ', OLD.email_usuario), 
        NULL, 
        OLD.id_usuario
    );
END $$
DELIMITER ;

select * from tb_usuario;



