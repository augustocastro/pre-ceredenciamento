INSERT INTO PERFIL(nome) VALUES ('adm');
INSERT INTO USUARIO(email, senha) VALUES('adm@gmail.com', '$2a$10$PWUd35kF/snIIXZq6O/SWu/eelEL//.ooRhySwM1zKxdQq5V/ky.a');
INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(1, 1);
INSERT INTO ENDERECO(endereco, cidade, estado, pais, cep) VALUES('Teste', 'Teste', 'Teste', 'Teste', 'Teste');
INSERT INTO CONSULTOR(nome, telefone, usuario_id, endereco_id) VALUES('Teste', '(00) 0000-0000', 1, 1);