use mayandb;

create table Foto (
id_foto int not null auto_increment,
link_foto varchar(255) not null,
primary key(id_foto)
);

create table Item (
id_item int not null auto_increment,
nome varchar(255) not null,
produttore varchar(255),
thumbnail varchar(255),
descr_item text,
categoria varchar(255) not null,
tot_acquistato int,
tot_visualizzazioni int,
voto_medio decimal(3,2),
primary key(id_item)
);

create table Visual (
id_item int not null,
contatore int,
primary key(id_item),
foreign key(id_item) references Item(id_item)
on update cascade
on delete cascade
);

create table Link_Item_Foto (
id_item int not null,
id_foto int not null,
primary key (id_item, id_foto),
foreign key (id_item) references Item(id_item)
on update cascade
on delete cascade,
foreign key (id_foto) references Foto(id_foto)
on update cascade
on delete cascade
);

create table Citta (
id_citta int not null auto_increment,
regione varchar(255) not null,
citta varchar(255) not null,
stato varchar(255) not null,
primary key(id_citta)
);

create table Location (
id_location int not null auto_increment,
latitudine decimal(9,6) not null,
longitudine decimal(9,6) not null,
id_citta int not null,
via varchar(255),
primary key(id_location),
foreign key (id_citta) references Citta(id_citta)
on update cascade
on delete cascade
);

create table Negozio (
id_negozio int not null auto_increment,
nome varchar(255) not null,
tipo enum('online','fisico') not null,
descrizione text,
web_link varchar(255),
valutazione_media decimal(3,2) unsigned,
orari varchar(255),
num_warning int,
id_location int,
primary key(id_negozio),
foreign key(id_location) references Location(id_location)
on update cascade
on delete cascade
);

create table Link_Negozio_Foto (
id_negozio int not null,
id_foto int not null,
primary key(id_negozio,id_foto),
foreign key (id_negozio) references Negozio(id_negozio)
on update cascade
on delete cascade,
foreign key(id_foto) references Foto(id_foto)
on update cascade
on delete cascade
);

create table Link_Negozio_Item (
id_item int not null,
id_negozio int not null,
num_stock int,
primary key(id_negozio,id_item),
foreign key (id_negozio) references Negozio(id_negozio)
on update cascade
on delete cascade,
foreign key(id_item) references Item(id_item)
on update cascade
on delete cascade
);

create table User (
id_user int not null auto_increment,
tipo enum('registrato','venditore','amministratore') not null,
nome varchar(255) not null,
cognome varchar(255) not null,
email varchar(255) not null,
password varchar(255) not null,
primary key(id_user)
);

create table Acquisto (
id_transazione int not null auto_increment,
quantità int not null,
prezzo float,
dataora datetime not null,
id_item int not null,
id_user int not null,
id_venditore int not null,
primary key(id_transazione),
foreign key(id_item) references Item(id_item)
on update cascade
on delete cascade,
foreign key(id_user) references User(id_user)
on update cascade
on delete cascade,
foreign key(id_venditore) references User(id_user)
on update cascade
on delete cascade
);

create table Recensione (
id_recensione int not null auto_increment,
tipo enum('recensione','risposta') not null,
testo text,
stelline decimal(2,1),
id_risp_rec int,
id_user int not null,
primary key(id_recensione),
foreign key(id_risp_rec) references Recensione(id_recensione)
on update cascade
on delete cascade,
foreign key(id_user) references User(id_user)
on update cascade
on delete cascade
);

create table Link_Rec_Item (
id_item int not null,
id_recensione int not null,
primary key(id_item, id_recensione),
foreign key(id_item) references Item(id_item)
on update cascade
on delete cascade,
foreign key(id_recensione) references Recensione(id_recensione)
on update cascade
on delete cascade
);

create table Messaggio (
id_messaggio int not null auto_increment,
tipo enum('anomalia','risposta','risoluzione','warning') not null,
descrizione text,
stato enum('aperta','chiusa'),
id_risposta int,
id_destinatario int not null,
id_mittente int not null,
id_transazione int not null,
primary key (id_messaggio),
foreign key(id_risposta) references Messaggio(id_messaggio)
on update cascade
on delete cascade,
foreign key(id_destinatario) references User(id_user)
on update cascade
on delete cascade,
foreign key(id_mittente) references User(id_user)
on update cascade
on delete cascade,
foreign key(id_transazione) references Acquisto(id_transazione)
on update cascade
on delete cascade
);

create table Allegato(
id_foto int not null,
id_messaggio int not null,
primary key (id_foto,id_messaggio),
foreign key(id_foto) references Foto(id_foto)
on update cascade
on delete cascade,
foreign key(id_messaggio) references Messaggio(id_messaggio)
on update cascade
on delete cascade
);