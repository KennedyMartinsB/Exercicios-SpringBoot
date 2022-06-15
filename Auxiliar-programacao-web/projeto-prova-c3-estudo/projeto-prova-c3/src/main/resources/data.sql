-- crie aqui as instruções SQL necessárias para a API iniciar com uma massa de dados mínima

insert into classe_heroi
(nome, pontuacao_minima, salario_base) values
('S', 10000, 20000.0),
('A', 9000, 15000.0),
('B', 5000, 8000.0),
('C', 0, 1000.0);

insert into heroi(nome, apelido, pontos, salario, classe_id) values
('Naruto', 'Uzumaki', 10000, 5500.0, 1),
('Sasuke', 'Uchiha', 9000, 5000.0, 2),
('Kakashi', 'Hatake', 7000, 4800.0, 3),
('Sakura', 'Haruno', 50000, 3000.0, 4);

insert into nivel_ameaca
(nome, pontuacao) values
('Lobo', 50),
('Tigre', 100),
('Demônio', 1000),
('Dragão', 4000),
('Deus', 10000);