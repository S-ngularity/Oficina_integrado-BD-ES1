--OBS: Necessario criar um bd Chamado Oficina
--comando: createdb Oficina
--==========================================Criacao das Tabelas===========================================================
CREATE TABLE Funcionario (
	codFuncionario smallserial PRIMARY KEY,
	atendente boolean NOT NULL, --atendente ou mecanico
	nomeFuncionario varchar(100) NOT NULL,
	senha varchar(10) --somente para atendente
);

CREATE TABLE Cliente(
	codCliente smallserial PRIMARY KEY,
	codCadastrante smallint NOT NULL,
	pessoaFisica boolean NOT NULL,
	emailCliente varchar(150),
	ruaCliente varchar(265) NOT NULL,
	numeroCliente smallint NOT NULL,
	bairroCliente varchar(35) NOT NULL,
	cidadeCliente varchar(35) NOT NULL,
	estadoCliente varchar(2) NOT NULL,
	CEPCliente varchar(9) NOT NULL,
	observacoesCliente text,

	FOREIGN KEY (codCadastrante) REFERENCES Funcionario(codFuncionario) ON UPDATE CASCADE
);

CREATE TABLE PessoaJuridica(
	codCliente smallint PRIMARY KEY,
	CNPJ varchar(18) NOT NULL,
	nomeFantasia varchar(100) NOT NULL,
	telefoneComercial varchar(13),
	Fax varchar(13),

	FOREIGN KEY (codCliente) REFERENCES Cliente(codCliente) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE PessoaFisica(
	codCliente smallint PRIMARY KEY,
	rg varchar(12) NOT NULL,
	cpf varchar(14) NOT NULL,
	nome varchar(100) NOT NULL,
	telefoneResidencial varchar(13),
	celular varchar(14),

	FOREIGN KEY (codCliente) REFERENCES Cliente(codCliente) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE MarcaCarro(

	codMarca smallserial PRIMARY KEY,
	nomeMarca varchar(60) NOT NULL
);

CREATE TABLE ModeloCarro(

	codModelo smallserial PRIMARY KEY,
	codMarca smallint NOT NULL,
	nomeModelo varchar(60) NOT NULL,

	FOREIGN KEY (codMarca) REFERENCES MarcaCarro(codMarca) ON UPDATE CASCADE
);

CREATE TABLE Carro(
	placaCarro varchar(8) PRIMARY KEY,
	codModelo smallint NOT NULL,
	codDono smallint NOT NULL,
	codCadastrante smallint NOT NULL,
	cor varchar(15) NOT NULL,
	ano smallint NOT NULL,
	observacoesCarro text,
	

	FOREIGN KEY (codModelo) REFERENCES modeloCarro(codModelo) ON UPDATE CASCADE,
	FOREIGN KEY (codDono) REFERENCES Cliente(codCliente) ON UPDATE CASCADE,
	FOREIGN KEY (codCadastrante) REFERENCES Funcionario(codFuncionario) ON UPDATE CASCADE
);

CREATE TABLE TipoEstadoOS(
	codEstadoOS smallserial PRIMARY KEY,
	nomeEstado varchar(40) NOT NULL
);

CREATE TABLE OrdemDeServico(
	codOS smallserial NOT NULL,
	codClienteOS smallint NOT NULL,
	placaCarroOS varchar(8) NOT NULL,
	CodAtendenteOS smallint NOT NULL,
	codEstadoOS smallint NOT NULL,
	kmEntradaOS decimal NOT NULL,
	kmSaidaOS decimal ,
	valorTotalOS decimal ,
	dataHoraInicioOS timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
	dataHoraFimOS timestamp with time zone,
	
	PRIMARY KEY (codOS),
	FOREIGN KEY (codClienteOS) REFERENCES Cliente(codCliente) ON UPDATE CASCADE,
	FOREIGN KEY (codAtendenteOS) REFERENCES Funcionario(codFuncionario) ON UPDATE CASCADE,
	FOREIGN KEY (placaCarroOS) REFERENCES Carro(placaCarro),
	FOREIGN KEY (codEstadoOS) REFERENCES TipoEstadoOS(codEstadoOS) ON UPDATE CASCADE
);

CREATE TABLE ServicoFuncionarioOS(
	codServico smallserial NOT NULL,
	codOS smallint NOT NULL,
	codFuncionario smallint NOT NULL,
	DataServico date NOT NULL DEFAULT CURRENT_DATE,
	DescricaoServico text,
	preco decimal,
	
	PRIMARY KEY (codServico,codOS , codFuncionario),
	FOREIGN KEY (codOS) REFERENCES OrdemDeServico(codOS) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (codFuncionario) REFERENCES Funcionario(codFuncionario)	ON UPDATE CASCADE
);



--=====================Insercoes=========================================================================

--Insercao Funcionarios
insert into Funcionario(nomeFuncionario,atendente, senha) VALUES ('Henrique', true, 'manoel');
insert into Funcionario(nomeFuncionario,atendente, senha) VALUES ('Carolina', true, 'pascale');
insert into Funcionario(nomeFuncionario,atendente, senha) VALUES ('Rafael', true, 'fairbanks');
insert into Funcionario(nomeFuncionario,atendente, senha) VALUES ('Filipe', true,'rocchi');
insert into Funcionario(nomeFuncionario,atendente, senha) VALUES ('Nicholas', true,'cage');
insert into Funcionario(nomeFuncionario,atendente) VALUES ('Jonas', false);
insert into Funcionario(nomeFuncionario,atendente) VALUES ('Gustavo', false);

--Insercao Clientes
insert into Cliente(codCadastrante, pessoaFisica, emailCliente, ruaCliente, numeroCliente,bairroCliente, cidadeCliente,estadoCliente, CEPCliente ) values(1,true, 'urameshi@urameshi.com', 'rua alem',23,'bairro Nobre','Sao Paulo','SP', '02121-020');

insert into Cliente(codCadastrante, pessoaFisica, emailCliente, ruaCliente, numeroCliente,bairroCliente, cidadeCliente,estadoCliente, CEPCliente ) values(2,true, 'appa@avatar.com', 'Rua do Ar',456,'bairro Nobre','Sao Paulo','SP', '02222-020');

insert into Cliente(codCadastrante, pessoaFisica, emailCliente, ruaCliente, numeroCliente,bairroCliente, cidadeCliente,estadoCliente, CEPCliente ) values(1,true, 'aantonio@gmail.com', 'rua estados unidos',567,'bairro Nobre','Sorocaba','SP', '01010-010');

insert into Cliente(codCadastrante, pessoaFisica, emailCliente, ruaCliente, numeroCliente,bairroCliente, cidadeCliente,estadoCliente, CEPCliente ) values(4,true, 'biaVascon@outlook.com', 'rua sao paulo',12,'bairro Nobre','Sao Paulo','SP', '11111-111');

insert into Cliente(codCadastrante, pessoaFisica, emailCliente, ruaCliente, numeroCliente,bairroCliente, cidadeCliente,estadoCliente, CEPCliente ) values(3,true, 'quixote@sanchopanca.com', 'Av Aragao',13 ,'bairro Nobre','Sorocaba','SP','02222-020');

insert into Cliente(codCadastrante, pessoaFisica, emailCliente, ruaCliente, numeroCliente,bairroCliente, cidadeCliente,estadoCliente, CEPCliente ) values(1,false, 'xgh@xgh.com', 'rua das gambiarras',45,'bairro Nobre','Rio de Janeiro','RJ','02222-020');

insert into Cliente(codCadastrante, pessoaFisica, emailCliente, ruaCliente, numeroCliente,bairroCliente, cidadeCliente,estadoCliente, CEPCliente ) values(4,false, 'rango@namesa.com', 'rua satisfacao',21,'bairro Nobre','Sao Paulo','SP','02222-020');

insert into Cliente(codCadastrante, pessoaFisica, emailCliente, ruaCliente, numeroCliente,bairroCliente, cidadeCliente,estadoCliente, CEPCliente ) values(5,false, 'acme@looneytunes.com', 'Av Frangolino',122,'bairro Nobre','Sao Paulo','SP', '02222-020');

insert into Cliente(codCadastrante, pessoaFisica, emailCliente, ruaCliente, numeroCliente,bairroCliente, cidadeCliente,estadoCliente, CEPCliente ) values(5,false, 'anabella@canabella.com', 'rua dos sonos',11,'bairro Nobre','Sao Paulo','SP','02222-020');

insert into Cliente(codCadastrante, pessoaFisica, emailCliente, ruaCliente, numeroCliente,bairroCliente, cidadeCliente,estadoCliente, CEPCliente ) values(1,false, 'mbyte@1mb.com', 'rua dos consertos',51,'bairro Nobre','Sao Paulo','SP','02222-020');

--Insercao PessoaFisica
insert into PessoaFisica(codCliente,nome, rg, cpf, telefoneResidencial, celular) values(1, 'Yusuke Urameshi', '32.344.312-1', '321.545.794-34','(15)2345-2225', '(15)99224-2325');

insert into PessoaFisica(codCliente,nome, rg, cpf, telefoneResidencial, celular) values(2, 'Appa Bisao', '32.378.352-2', '361.345.624-90','(24)6565-2225', '(24)99265-2325');

insert into PessoaFisica(codCliente,nome, rg, cpf, telefoneResidencial, celular) values(3, 'Armando Antonio Freitas', '47.383.212-4', '211.925.812-65','(11)3084-0186', '(11)99235-7398');

insert into PessoaFisica(codCliente,nome, rg, cpf, telefoneResidencial, celular) values(4, 'Beatriz Vasconcelos da Silva', '66.266.616-6', '853.100.852-52','(15)3809-0003', '(15)92224-4000');

insert into PessoaFisica(codCliente,nome, rg, cpf, telefoneResidencial, celular) values(5, 'Don Quixote de La Mancha', '11.333.222-3', '987.221.323-11','(52)1134-2425', '(52)90213-5525');

--Insercao PessoaJuridica

insert into PessoaJuridica(codCliente,  nomeFantasia, CNPJ, telefoneComercial, FAX) values(6,'Extreme Go Horse LTDA', '91.295.997/5969-29', '(21)5951-5527', '(21)9967-8181');

insert into PessoaJuridica(codCliente,  nomeFantasia, CNPJ, telefoneComercial, FAX) values(7,'Rango na Mesa - Comidas', '31.225.837/8274-70', '(12)1212-2121', '(12)9954-4000');

insert into PessoaJuridica(codCliente,  nomeFantasia, CNPJ, telefoneComercial, FAX) values(8,'ACME - Produtos Diversos', '11.378.217/1212-43', '(11)6712-1999', '(11)9712-8282');

insert into PessoaJuridica(codCliente,  nomeFantasia, CNPJ, telefoneComercial, FAX) values(9,'Colchões Anabella', '91.513.768/3456-26', '(21)5235-6820', '(21)9777-8922');

insert into PessoaJuridica(codCliente,  nomeFantasia, CNPJ, telefoneComercial, FAX) values(10,'MegaByte-Produtos de Informatica', '10.101.111/1011-10', '(10)2113-1033', '(10)9032-1111');

--Insercao MarcaCarro
insert into MarcaCarro(nomeMarca) VALUES('Chevrolet');
insert into MarcaCarro(nomeMarca) VALUES('FIAT');
insert into MarcaCarro(nomeMarca) VALUES('Nissan');
insert into MarcaCarro(nomeMarca) VALUES('Renault');
insert into MarcaCarro(nomeMarca) VALUES('Volkswagem');


--Insercao ModeloCarro

--Carros Chevrolet
insert into ModeloCarro(codMarca,nomeModelo) VALUES(1,'Celta');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(1,'Camaro');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(1,'Captiva');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(1,'Onix');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(1,'Prisma');
--Carros Fiat
insert into ModeloCarro(codMarca,nomeModelo) VALUES(2,'Fiorino');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(2,'Palio');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(2,'Siena');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(2,'Strada');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(2,'Uno');
--Carros Nissan
insert into ModeloCarro(codMarca,nomeModelo) VALUES(3,'Altima');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(3,'Frontier');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(3,'Livina');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(3,'March');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(3,'Sentra');
--Carros Renault
insert into ModeloCarro(codMarca,nomeModelo) VALUES(4,'Clio');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(4,'Duster');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(4,'Kangoo');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(4,'Logan');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(4,'Sandero');
--Carros Volkswagem
insert into ModeloCarro(codMarca,nomeModelo) VALUES(5,'Fox');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(5,'Fusca');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(5,'Gol');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(5,'Saveiro');
insert into ModeloCarro(codMarca,nomeModelo) VALUES(5,'Voyage');

--Insercao Carro

insert into Carro(placaCarro, codModelo, codDono, codCadastrante, cor, ano) values('COC-1237', 1, 3, 2,'prata',2001);
insert into Carro(placaCarro, codModelo, codDono, codCadastrante, cor, ano) values('BOM-9032', 7, 7, 7,'preto',2003);
insert into Carro(placaCarro, codModelo, codDono, codCadastrante, cor, ano) values('FOI-0203', 21, 5, 1,'branco',2000);
insert into Carro(placaCarro, codModelo, codDono, codCadastrante, cor, ano) values('UFA-2122', 8, 1, 3,'prata',2006);
insert into Carro(placaCarro, codModelo, codDono, codCadastrante, cor, ano) values('NIC-7788', 17, 4, 2,'preto',2010);

--Insercao TipoEstadoOS

insert into TipoEstadoOS(nomeEstado) values('Pendente para orçamento');
insert into TipoEstadoOS(nomeEstado) values('Pendente para aprovação');
insert into TipoEstadoOS(nomeEstado) values('Em atendimento');
insert into TipoEstadoOS(nomeEstado) values('Pendente para pagamento');
insert into TipoEstadoOS(nomeEstado) values('Finalizada');
insert into TipoEstadoOS(nomeEstado) values('Cancelada');

--Insercao Ordem de Servico
insert into OrdemDeServico(codClienteOS, placaCarroOS, codAtendenteOS, codEstadoOS, kmEntradaOS, valorTotalOS) values(4,'UFA-2122',1,1,234.8, 200);
insert into OrdemDeServico(codClienteOS, placaCarroOS, codAtendenteOS, codEstadoOS, kmEntradaOS, valorTotalOS) values(1,'COC-1237',4,4,200.1, 50);
insert into OrdemDeServico(codClienteOS, placaCarroOS, codAtendenteOS, codEstadoOS, kmEntradaOS, valorTotalOS) values(5,'NIC-7788',3,4,1000.99, 1000);
insert into OrdemDeServico(codClienteOS, placaCarroOS, codAtendenteOS, codEstadoOS, kmEntradaOS, valorTotalOS) values(3,'FOI-0203',3,4,50.86, 14);
insert into OrdemDeServico(codClienteOS, placaCarroOS, codAtendenteOS, codEstadoOS, kmEntradaOS, valorTotalOS) values(1,'COC-1237',1,5,122.11, 0);
insert into OrdemDeServico(codClienteOS, placaCarroOS, codAtendenteOS, codEstadoOS, kmEntradaOS, valorTotalOS) values(2,'BOM-9032',1,5,127.11, 3000);
insert into OrdemDeServico(codClienteOS, placaCarroOS, codAtendenteOS, codEstadoOS, kmEntradaOS, valorTotalOS) values(1,'COC-1237',2,5,122.11, 500);
insert into OrdemDeServico(codClienteOS, placaCarroOS, codAtendenteOS, codEstadoOS, kmEntradaOS, valorTotalOS) values(2,'BOM-9032',3,5,127.11, 1500);

--Insercao ServicoFuncionarioOS -- eletrica, balanceamento, alinhamento, bateria, vidracaria, 
insert into ServicoFuncionarioOS(codOS, codFuncionario, descricaoServico, preco) values(1,2,'eletrica', 20);
insert into ServicoFuncionarioOS(codOS, codFuncionario, descricaoServico, preco) values(2,6,'eletrica', 30);
insert into ServicoFuncionarioOS(codOS, codFuncionario, descricaoServico, preco) values(3,7,'troca de oleo', 10);
insert into ServicoFuncionarioOS(codOS, codFuncionario, descricaoServico, preco) values(4,6,'freios', 50);
insert into ServicoFuncionarioOS(codOS, codFuncionario, descricaoServico, preco) values(5,7,'troca de carburador', 35);
insert into ServicoFuncionarioOS(codOS, codFuncionario, descricaoServico, preco) values(6,7,'conserto da injecao eletronica', 100);
