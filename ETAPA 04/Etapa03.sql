CREATE DATABASE checkPets;
SELECT checkPets;

CREATE TABLE funcionario(
	matricula INT NOT NULL AUTO_INCREMENT,
	nomeCompleto VARCHAR(100) NOT NULL,
    dataNascimento DATE NOT NULL,
    telefoneContato VARCHAR(45) NOT NULL,
    crmv VARCHAR(45),
    cargo_idCargo INT NOT NULL,
    endereco_idEndereco INT NOT NULL,
    PRIMARY KEY (matricula)
	);

ALTER TABLE funcionario ADD FOREIGN KEY (cargo_idCargo) REFERENCES cargo (idCargo);
ALTER TABLE funcionario ADD FOREIGN KEY (endereco_idEndereco) REFERENCES endereco (idEndereco);
ALTER TABLE Funcionario ADD COLUMN DTYPE VARCHAR(255) NOT NULL;
UPDATE Funcionario SET DTYPE = "GER" WHERE matricula = 1;
     
CREATE TABLE cargo(
	idCargo INT NOT NULL AUTO_INCREMENT,
    funcao VARCHAR(45) NOT NULL,
    salario DECIMAL NOT NULL,
    horaEntrada TIME NOT NULL,
    horaSaida TIME NOT NULL,
    PRIMARY KEY (idCargo)
	);
    
    SELECT*FROM cargo;
    
CREATE TABLE endereco(
	idEndereco INT NOT NULL AUTO_INCREMENT,
    estado CHAR(2) NOT NULL,
    cidade VARCHAR(45) NOT NULL,
    bairro VARCHAR(45) NOT NULL,
    rua VARCHAR(45) NOT NULL,
    numero VARCHAR(45) NOT NULL,
    complemento VARCHAR(45),
    cep CHAR(8) NOT NULL,
    PRIMARY KEY (idEndereco)
	);

CREATE TABLE pets(
id INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
peso DOUBLE NOT NULL,
doencas VARCHAR(100) NOT NULL,
medicacao VARCHAR(100) NOT NULL,
historicoMedico VARCHAR(200),
PRIMARY KEY (id)
);

ALTER TABLE pets ADD COLUMN tutorId_IdTutor INT NOT NULL;
ALTER TABLE pets ADD FOREIGN KEY (tutorId_idTutor) REFERENCES tutor(idTutor);

CREATE TABLE usuario(
funcionario_matriculaFuncionario INT NOT NULL,
login VARCHAR(100) NOT NULL,
senha VARCHAR(100) NOT NULL,
PRIMARY KEY (funcionario_matriculaFuncionario),
FOREIGN KEY (funcionario_matriculaFuncionario) REFERENCES funcionario(matricula)
);

CREATE TABLE consulta(
idConsulta INT NOT NULL AUTO_INCREMENT,
realizada BOOLEAN NOT NULL,
medicacao VARCHAR(500) NOT NULL,
veterinarioMatricula_matriculaVeterinario INT NOT NULL,
petId_idPet INT NOT NULL,
PRIMARY KEY(idConsulta),
FOREIGN KEY (veterinarioMatricula_matriculaVeterinario) REFERENCES funcionario(matricula),
FOREIGN KEY (petId_idPet) REFERENCES pets(id)
);

CREATE TABLE tutor(
idTutor INT NOT NULL AUTO_INCREMENT,
email VARCHAR(200),
nome VARCHAR(100) NOT NULL,
telefone CHAR(11) NOT NULL,
enderecoId_idEndereco INT NOT NULL,
PRIMARY KEY (idTutor),
FOREIGN KEY (enderecoId_idEndereco) REFERENCES endereco(idEndereco)
);

CREATE TABLE Consulta (
    id INT PRIMARY KEY AUTO_INCREMENT, 
    data DATE NOT NULL,                
    realizada BOOLEAN DEFAULT FALSE, 
    petId_idPet INT NOT NULL,    
    veterinarioId_idVeterinario INT NOT NULL,
    FOREIGN KEY (petId_idPet) REFERENCES pets(id),    
    FOREIGN KEY (veterinarioId_idVeterinario) REFERENCES Funcionario(matricula)
);

INSERT INTO Tutor (petId_idPet, nome, email, telefone, enderecoId_idEndereco) VALUES
(null, 'Ana Silva', 'ana.silva@email.com', '11987654321', 11),
(null, 'Bruno Costa', 'bruno.c@email.com', '21912345678', 10),
(null, 'Carla Dias', 'carla.dias@email.com', '31955554444', 9);

INSERT INTO pets (nome, peso, doencas, medicacao, historicoMedico, tutorId_IdTutor) VALUES
('Max', 15.5, 'Nenhuma', 'Controle de pulgas e carrapatos mensal', 'Castrado, vacinas em dia', 8),
('Mimi', 4.2, 'Alergia de pele', 'Cetirizina 5mg, 1 vez ao dia', 'Realizou limpeza de tártaro em 2024', 9),
('Rex', 28.0, 'Artrose no quadril', 'Carprofeno 100mg quando apresentar dor',
'Cirurgia no joelho esquerdo em 2022. Acompanhamento semestral.', 8),
('Luna', 5.1, 'Asma felina', 'Budesonida inalatória, 2 vezes ao dia',
'Diagnóstico de asma em 2023. Exames de sangue anuais.', 8);

INSERT INTO Tutor (petId_idPet, nome, email, telefone) VALUES
(null, 'Ana Silva', 'ana.silva@email.com', '11987654321'),
(null, 'Bruno Costa', 'bruno.c@email.com', '21912345678'),
(null, 'Carla Dias', 'carla.dias@email.com', '31955554444');

INSERT INTO usuario(login, senha, funcionario_matriculaFuncionario) VALUES
('Gerente', 'senhaGerente', 1),
('Atendente', 'senhaAtendente', 2),
('Veterinario1', 'senhaVeterinario1', 4),
('Veterinario2', 'senhaVeterinario2', 5)
;
    
INSERT INTO funcionario(nomeCompleto, dataNascimento, telefoneContato, crmv, cargo_idCargo, Endereco_idEndereco) VALUES
	('Michael Joseph Jackson', '1958-08-29', '74998877665', NULL, 1, 1),
	('Justin Drew Bieber', '1994-03-01', '86987766554', NULL, 2, 2),
	('Travis Scott', '1991-04-30', '11943322110', NULL, 3,3),
	('Billie Eilish', '2001-12-18', '11936589714', 'SP-00000-SP', 4, 4),
	('Bruno Mars', '1985-10-08', '11987143682', 'SP-00001-SP', 5,5)
	;    	
    
INSERT INTO cargo (funcao, salario, horaEntrada, horaSaida) VALUES
	('gerente', 4000.00, '08:00:00', '16:00:00'),
	('atendente', 2000, '07:00:00', '15:00:00'),
	('atendente', 2000, '10:00:00', '18:00:00'),
	('veterinario', 3000, '07:00:00', '15:00:00'),
	('veterinario', 3000, '10:00:00', '18:00:00')
    ;

INSERT INTO endereco (estado, cidade, bairro, rua, numero, complemento, cep) VALUES
    ('RJ', 'Rio de Janeiro', 'Ipanema', 'Avenida Vieira Souto', 500, 'Apto 301', '22420002'),
	('MG', 'Belo Horizonte', 'Savassi', 'Rua Pernambuco', 1050, NULL, '30130150'),
	('PR', 'Curitiba', 'Batel', 'Avenida do Batel', 1800, NULL, '80420090'),
	('SP', 'São Paulo', 'Vila Madalena', 'Rua Harmonia', 950, 'Fundos', '05435001'),
	('PE', 'Recife', 'Boa Viagem', 'Avenida Boa Viagem', 2020, NULL, '51020001');   
    
	INSERT INTO Consulta (realizada, medicacao, petId_idPet, veterinarioMatricula_matriculaVeterinario) VALUES
	( TRUE, 'Antibiótico Amoxicilina 50mg por 7 dias', 7, 4),
	( TRUE, 'Apenas check-up, vacinas atualizadas', 9, 4),
	( FALSE, 'Agendamento de rotina', 9, 4),
	( TRUE, 'Recomendação de troca de ração para controle de peso', 10, 5),
	( FALSE, 'Primeira vacina de filhote', 7, 5);


SELECT*FROM funcionario;
Select*from consulta;
SELECT*FROM tutor;
SELECT*FROM cargo;
SELECT*FROM endereco;
SELECT*FROM pets;
