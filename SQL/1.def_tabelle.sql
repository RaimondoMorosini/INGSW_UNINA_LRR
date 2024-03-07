CREATE TABLE utente(

	email VARCHAR(32) PRIMARY KEY,
	password VARCHAR(32),
	nome VARCHAR(32),
	cognome VARCHAR(32),
	siti JSON,
	area_geografica VARCHAR(32),
	bio VARCHAR(256)
);

CREATE TABLE tipo(
	
	nome VARCHAR(16) PRIMARY KEY
);

CREATE TABLE relazione_utente_tipo(

	nome_utente VARCHAR(32),
	nome_tipo VARCHAR(16),
	
	PRIMARY KEY(nome_utente,nome_tipo),
	FOREIGN KEY(nome_utente) REFERENCES utente(email),
	FOREIGN KEY(nome_tipo) REFERENCES tipo(nome)
);

CREATE TABLE permessi(

	nome VARCHAR(16) PRIMARY KEY,
	descrizione VARCHAR(256)
);

CREATE TABLE relazione_tipo_permessi(

	nome_tipo VARCHAR(16),
	nome_permesso VARCHAR(16),
	
	PRIMARY KEY(nome_tipo, nome_permesso)
);

CREATE TABLE dati_venditori(
	
	nome_utente VARCHAR(32),
	partita_iva VARCHAR(11),
	codice_fiscale VARCHAR(16),
	nome_azienda VARCHAR(64),
	numero_telefono VARCHAR(16),
	
	FOREIGN KEY(nome_utente) REFERENCES utente(email)
);
