
create table hotel(
	id_hotel int(5) AUTO_INCREMENT PRIMARY KEY,
	nazwa VARCHAR(30),
	adres VARCHAR(150),
	nr_tel VARCHAR(30)
);

INSERT INTO hotel(`nazwa`,`adres`,`nr_tel`) VALUES 
('Hotel Grupa Projektowa','aleja Tysiąclecia Państwa Polskiego 7, 25-314 Kielce','41 342 44 44');

create table stanowisko(
	id_stanowisko INT(30) AUTO_INCREMENT PRIMARY KEY,
	nazwa_stanowiska VARCHAR(30)
);

INSERT INTO stanowisko (`nazwa_stanowiska`) VALUES ('Pokojówka');
INSERT INTO stanowisko (`nazwa_stanowiska`) VALUES ('Recepcjonista');
INSERT INTO stanowisko (`nazwa_stanowiska`) VALUES ('Bagażowy');
INSERT INTO stanowisko (`nazwa_stanowiska`) VALUES ('Kierowca');
INSERT INTO stanowisko (`nazwa_stanowiska`) VALUES ('Kelner');
INSERT INTO stanowisko (`nazwa_stanowiska`) VALUES ('Barman');
INSERT INTO stanowisko (`nazwa_stanowiska`) VALUES ('Dyrektor');
INSERT INTO stanowisko (`nazwa_stanowiska`) VALUES ('Barista');


create table pracownik(
	ID_PRACOWNIK INT(3) AUTO_INCREMENT PRIMARY KEY,
	IMIE VARCHAR(30),
	NAZWISKO VARCHAR(30),
	WIEK INT(4),
	PLEC VARCHAR(30),
	NR_PESEL VARCHAR(11),
	MIEJSCE_ZAMIESZKANIA VARCHAR(50),
	WYNAGRODZENIE FLOAT(6,1),
	hotel_id_hotel int(5),
	stanowisko_id_stanowisko int(30)
);

create table opinie(
	id_opinia int(5) AUTO_INCREMENT PRIMARY KEY,
	tresc VARCHAR(300),
	gwiazdki int(2),
    	hotel_id_hotel int(5)
);

create table formularz(
	id_formularz int(7) AUTO_INCREMENT PRIMARY KEY,
	imie VARCHAR(50),
	nazwisko VARCHAR(50),
	nr_tel VARCHAR(11),
	email VARCHAR(60),
	tresc VARCHAR(500),
	hotel_id_hotel int(5)
);

create table grafik(
	imie VARCHAR(30),
	nazwisko VARCHAR(30),
	PON VARCHAR(30),
	WT VARCHAR(30),
	SR VARCHAR(30),
	CZW VARCHAR(30),
	PT VARCHAR(30),
	SOB VARCHAR(30),
	ND VARCHAR(30)
); 

create table typ_pokoju(
	id_typ_pokoju int(3) AUTO_INCREMENT PRIMARY KEY,
	rodzaj_pokoju VARCHAR(50),
	cena_noclegu float(6,2)
);

INSERT INTO typ_pokoju (`rodzaj_pokoju`,`cena_noclegu`) VALUES ('Jednoosobowy','100');
INSERT INTO typ_pokoju (`rodzaj_pokoju`,`cena_noclegu`) VALUES ('Dwuosobowy','200');
INSERT INTO typ_pokoju (`rodzaj_pokoju`,`cena_noclegu`) VALUES ('Apartament','350');

create table rodza_udo(
	id_rodz_udo int(6) AUTO_INCREMENT PRIMARY KEY,
	nazwa VARCHAR(100),
	cena  FLOAT(6,2),
	udo_typ_pokoju int(3)
);

create table promocja(
	id_promocja int(6) AUTO_INCREMENT PRIMARY KEY,
	nazwa VARCHAR(50),
	wartosc_promocji FLOAT(6,2),
	data_rozpo date not null,
	data_zako  date not null
);


create table pokoj(
	id_pokoj int(6) AUTO_INCREMENT PRIMARY KEY,
	stan BIT(1),
	nr_pokoju int(6),
	hotel_id_hotel int(5),
	pokoj_typ_pokoju int(3)
);


create table klient(
	id_klient int(10) AUTO_INCREMENT PRIMARY KEY,
	imie VARCHAR(50),
	nazwisko VARCHAR(50),
	nr_tel VARCHAR(11),
	email VARCHAR(60)
);


create table rezerwacja(
	id_rezer int(7) AUTO_INCREMENT PRIMARY KEY,
	data_przyjazdu DATE not null,
	data_wyjazdu DATE not null,
	caly_koszt float(8,2),
	pokoj_id_pokoj int(6)
);

create table skargi(
	id_skarga int(8) AUTO_INCREMENT PRIMARY KEY,
	tresc VARCHAR(500),
	rez_id_rez int(8)
);

create table rezerwacja_udo(
	id_rez_udo int(7) AUTO_INCREMENT PRIMARY KEY,
	udo_id_udo int(7),
	rez_id_rez int(7)
);


CREATE TABLE promocja_pokoj
  (
	id_promocja_pokoj int(7) AUTO_INCREMENT PRIMARY KEY,
	promocja_id_promocja int(7),
	pokoj_id_pokoj int(7)
  );


CREATE TABLE rezerwacja_klient
  (
    id_rezerwacja_klient int(7) AUTO_INCREMENT PRIMARY KEY,
	rezer_id_rezer INT(7),
	klient_id_klient INT(7)
  );
 
  
create table user_account(
	account_id INT(11) AUTO_INCREMENT PRIMARY KEY,
	firstname VARCHAR(50),
	lastname VARCHAR(50),
	username VARCHAR(50),
	password VARCHAR(50)
);

INSERT INTO `user_account`( `firstname`, `lastname`, `username`, `password`) VALUES ('Admin','Admin','Admin','Admin');
INSERT INTO `user_account`( `firstname`, `lastname`, `username`, `password`) VALUES ('Worker','Worker','Worker','Worker');
INSERT INTO `user_account`( `firstname`, `lastname`, `username`, `password`) VALUES ('.','.','.','.');



alter table pracownik ADD CONSTRAINT fk_pracownikhotel  FOREIGN KEY (hotel_id_hotel) REFERENCES hotel (id_hotel);
alter table pracownik ADD CONSTRAINT fk_pracownikstanowsiko  FOREIGN KEY (stanowisko_id_stanowisko) REFERENCES stanowisko (id_stanowisko);

alter table opinie ADD CONSTRAINT fk_opiniahotel  FOREIGN KEY (hotel_id_hotel) REFERENCES hotel (id_hotel);

alter table formularz ADD CONSTRAINT fk_formhotel  FOREIGN KEY (hotel_id_hotel) REFERENCES hotel (id_hotel);

alter table rodza_udo ADD CONSTRAINT fk_udopokoj FOREIGN KEY (udo_typ_pokoju) REFERENCES typ_pokoju (id_typ_pokoju);

alter table pokoj ADD CONSTRAINT fk_pokojhotel FOREIGN KEY (hotel_id_hotel) REFERENCES hotel (id_hotel);
alter table pokoj ADD CONSTRAINT fk_pokojtyp FOREIGN KEY (pokoj_typ_pokoju) REFERENCES typ_pokoju (id_typ_pokoju);

alter table rezerwacja ADD CONSTRAINT fk_rezpokoj FOREIGN KEY (pokoj_id_pokoj) REFERENCES pokoj (id_pokoj);

alter table skargi ADD CONSTRAINT fk_skarga_rez FOREIGN KEY (rez_id_rez) REFERENCES rezerwacja (id_rezer);

alter table rezerwacja_udo ADD CONSTRAINT fk_id_udo FOREIGN KEY (udo_id_udo) REFERENCES rodza_udo (id_rodz_udo);
alter table rezerwacja_udo ADD CONSTRAINT fk_id_rez FOREIGN KEY (rez_id_rez) REFERENCES rezerwacja (id_rezer);

alter table promocja_pokoj ADD CONSTRAINT fk_id_promocja FOREIGN KEY (promocja_id_promocja) REFERENCES promocja (id_promocja);
alter table promocja_pokoj ADD CONSTRAINT fk_id_pokoj FOREIGN KEY (pokoj_id_pokoj) REFERENCES pokoj (id_pokoj);

alter table rezerwacja_klient ADD CONSTRAINT fk_id_rezerwacja FOREIGN KEY (rezer_id_rezer) REFERENCES rezerwacja (id_rezer);
alter table rezerwacja_klient ADD CONSTRAINT fk_id_klient FOREIGN KEY (klient_id_klient) REFERENCES klient (id_klient);