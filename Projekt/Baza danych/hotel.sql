-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 01 Cze 2022, 17:11
-- Wersja serwera: 10.4.10-MariaDB
-- Wersja PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `hotel`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `formularz`
--

CREATE TABLE `formularz` (
  `id_formularz` int(7) NOT NULL,
  `imie` varchar(50) DEFAULT NULL,
  `nazwisko` varchar(50) DEFAULT NULL,
  `nr_tel` varchar(11) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `tresc` varchar(500) DEFAULT NULL,
  `hotel_id_hotel` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `formularz`
--

INSERT INTO `formularz` (`id_formularz`, `imie`, `nazwisko`, `nr_tel`, `email`, `tresc`, `hotel_id_hotel`) VALUES
(1, 'Witold', 'Warka', '513256834', 'wies@o2.pl', 'Chciałem spytać czy posiadają państwo parking, proszę o odpowiedź SMS.', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `grafik`
--

CREATE TABLE `grafik` (
  `imie` varchar(30) DEFAULT NULL,
  `nazwisko` varchar(30) DEFAULT NULL,
  `PON` varchar(30) DEFAULT NULL,
  `WT` varchar(30) DEFAULT NULL,
  `SR` varchar(30) DEFAULT NULL,
  `CZW` varchar(30) DEFAULT NULL,
  `PT` varchar(30) DEFAULT NULL,
  `SOB` varchar(30) DEFAULT NULL,
  `ND` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `grafik`
--

INSERT INTO `grafik` (`imie`, `nazwisko`, `PON`, `WT`, `SR`, `CZW`, `PT`, `SOB`, `ND`) VALUES
('Konrad', 'Basa', '09:00 - 16:00', 'Wolne', '09:00 - 16:00', 'Wolne', '09:00 - 16:00', 'Wolne', 'Wolne'),
('Marcin', 'Bonar', 'Wolne', 'Wolne', 'Wolne', 'Wolne', 'Wolne', 'Wolne', 'Wolne'),
('Piotr ', 'Bielecki', '08:00 - 16:00', 'Wolne', '08:00 - 16:00', 'Wolne', '08:00 - 16:00', 'Wolne', 'Wolne'),
('Jan', 'Nowak', '08:00 - 16:00', 'Wolne', '08:00 - 16:00', 'Wolne', '08:00 - 16:00', 'Wolne', 'Wolne'),
('Karol', 'Wilk', 'Wolne', 'Wolne', '08:00 - 16:00', 'Wolne', '08:00 - 16:00', 'Wolne', 'Wolne'),
('Zofia', 'Lech', '07:00 - 15:00', '07:00 - 15:00', '07:00 - 15:00', 'Wolne', 'Wolne', 'Wolne', 'Wolne');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `hotel`
--

CREATE TABLE `hotel` (
  `id_hotel` int(5) NOT NULL,
  `nazwa` varchar(30) DEFAULT NULL,
  `adres` varchar(150) DEFAULT NULL,
  `nr_tel` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `hotel`
--

INSERT INTO `hotel` (`id_hotel`, `nazwa`, `adres`, `nr_tel`) VALUES
(1, 'Hotel Grupa Projektowa', 'aleja Tysiąclecia Państwa Polskiego 7, 25-314 Kielce', '41 342 44 44');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klient`
--

CREATE TABLE `klient` (
  `id_klient` int(10) NOT NULL,
  `imie` varchar(50) DEFAULT NULL,
  `nazwisko` varchar(50) DEFAULT NULL,
  `nr_tel` varchar(11) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `klient`
--

INSERT INTO `klient` (`id_klient`, `imie`, `nazwisko`, `nr_tel`, `email`) VALUES
(1, 'Konrad', 'Basa', '513062738', 'konradb@wp.pl'),
(4, 'Marcin', 'Bonar', '513089325', 'marcin@wp.pl'),
(5, 'Piotr', 'Bielecki', '600243657', 'piotrek@wp.pl'),
(6, 'Kamil', 'Siudak', '510789025', 'kamil@wp.pl'),
(7, 'Karol', 'Wilk', '602378022', 'karol@wp.pl'),
(8, 'Jan', 'Nowak', '512904002', 'nowak@wp.pl'),
(9, 'Iwona', 'Karaś', '512089783', 'iwonka@wp.pl'),
(10, 'Patryk', 'Maleta', '603456812', 'patryk@wp.pl'),
(22, 'Jacek', 'Placek', '513089154', 'jacek@gmail.com');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `opinie`
--

CREATE TABLE `opinie` (
  `id_opinia` int(5) NOT NULL,
  `tresc` varchar(300) DEFAULT NULL,
  `gwiazdki` int(2) DEFAULT NULL,
  `hotel_id_hotel` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `opinie`
--

INSERT INTO `opinie` (`id_opinia`, `tresc`, `gwiazdki`, `hotel_id_hotel`) VALUES
(1, 'Polecam serdecznie.', 5, 1),
(2, 'Wszystko w porządku, wrócę z chęcią.', 4, 1),
(3, 'Słabo, brudno, nie polecam!', 1, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pokoj`
--

CREATE TABLE `pokoj` (
  `id_pokoj` int(6) NOT NULL,
  `stan` bit(1) DEFAULT NULL,
  `nr_pokoju` int(6) DEFAULT NULL,
  `hotel_id_hotel` int(5) DEFAULT NULL,
  `pokoj_typ_pokoju` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `pokoj`
--

INSERT INTO `pokoj` (`id_pokoj`, `stan`, `nr_pokoju`, `hotel_id_hotel`, `pokoj_typ_pokoju`) VALUES
(1, b'1', 1, 1, 1),
(2, b'1', 2, 1, 2),
(3, b'1', 3, 1, 3),
(4, b'1', 4, 1, 1),
(5, b'1', 5, 1, 2),
(6, b'0', 6, 1, 1),
(7, b'0', 7, 1, 3),
(8, b'0', 8, 1, 1),
(9, b'1', 9, 1, 2),
(10, b'0', 10, 1, 3),
(11, b'1', 11, 1, 2),
(12, b'0', 12, 1, 1),
(13, b'0', 13, 1, 3),
(14, b'0', 14, 1, 1),
(15, b'0', 15, 1, 1),
(16, b'0', 16, 1, 1),
(17, b'0', 17, 1, 1),
(18, b'0', 18, 1, 3),
(19, b'0', 19, 1, 1),
(20, b'0', 20, 1, 1),
(21, b'0', 21, 1, 2),
(22, b'0', 22, 1, 1),
(23, b'0', 23, 1, 1),
(24, b'0', 24, 1, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownik`
--

CREATE TABLE `pracownik` (
  `ID_PRACOWNIK` int(3) NOT NULL,
  `IMIE` varchar(30) DEFAULT NULL,
  `NAZWISKO` varchar(30) DEFAULT NULL,
  `WIEK` int(4) DEFAULT NULL,
  `PLEC` varchar(30) DEFAULT NULL,
  `NR_PESEL` varchar(11) DEFAULT NULL,
  `MIEJSCE_ZAMIESZKANIA` varchar(50) DEFAULT NULL,
  `WYNAGRODZENIE` float(6,1) DEFAULT NULL,
  `hotel_id_hotel` int(5) DEFAULT NULL,
  `stanowisko_id_stanowisko` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `pracownik`
--

INSERT INTO `pracownik` (`ID_PRACOWNIK`, `IMIE`, `NAZWISKO`, `WIEK`, `PLEC`, `NR_PESEL`, `MIEJSCE_ZAMIESZKANIA`, `WYNAGRODZENIE`, `hotel_id_hotel`, `stanowisko_id_stanowisko`) VALUES
(12, 'Konrad', 'Basa', 22, 'Mężczyzna', '25252436463', 'Kielce', 12000.0, 1, 6),
(14, 'Marcin', 'Bonar', 22, 'Mężczyzna', '52678562325', 'Kielce', 10000.0, 1, 2),
(15, 'Piotr ', 'Bielecki', 21, 'Mężczyzna', '13566876967', 'Kielce', 10000.0, 1, 8),
(16, 'Zofia', 'Lech', 16, 'Kobieta', '63368745454', 'Checiny', 4500.0, 1, 1),
(17, 'Jan', 'Nowak', 34, 'Mężczyzna', '13446568768', 'Warszawa', 4100.0, 1, 5),
(18, 'Karol', 'Wilk', 23, 'Mężczyzna', '03342346574', 'Szczecin', 6000.0, 1, 4);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `promocja`
--

CREATE TABLE `promocja` (
  `id_promocja` int(6) NOT NULL,
  `nazwa` varchar(50) DEFAULT NULL,
  `wartosc_promocji` int(6) DEFAULT NULL,
  `data_rozpo` date NOT NULL,
  `data_zako` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `promocja`
--

INSERT INTO `promocja` (`id_promocja`, `nazwa`, `wartosc_promocji`, `data_rozpo`, `data_zako`) VALUES
(35, 'Letnia Promocja', 5, '2022-05-11', '2022-05-28'),
(37, 'Nowa przygoda', 2, '2022-07-01', '2022-07-10');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `promocja_pokoj`
--

CREATE TABLE `promocja_pokoj` (
  `id_promocja_pokoj` int(7) NOT NULL,
  `promocja_id_promocja` int(7) DEFAULT NULL,
  `pokoj_id_pokoj` int(7) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `promocja_pokoj`
--

INSERT INTO `promocja_pokoj` (`id_promocja_pokoj`, `promocja_id_promocja`, `pokoj_id_pokoj`) VALUES
(64, 35, 1),
(65, 35, 4),
(66, 35, 6),
(67, 35, 8),
(68, 35, 12),
(69, 35, 14),
(70, 35, 15),
(71, 35, 16),
(72, 35, 17),
(79, 35, 2),
(80, 35, 5),
(81, 35, 9),
(82, 35, 11),
(86, 35, 19),
(87, 35, 20),
(88, 35, 21),
(89, 35, 22),
(119, 35, 23),
(120, 37, 1),
(121, 37, 4),
(122, 37, 6),
(123, 37, 8),
(124, 37, 12),
(125, 37, 14),
(126, 37, 15),
(127, 37, 16),
(128, 37, 17),
(129, 37, 19),
(130, 37, 20),
(131, 37, 22),
(132, 37, 23),
(135, 37, 2),
(136, 37, 5),
(137, 37, 9),
(138, 37, 11),
(139, 37, 21),
(140, 35, 24),
(141, 37, 24);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rezerwacja`
--

CREATE TABLE `rezerwacja` (
  `id_rezer` int(7) NOT NULL,
  `data_przyjazdu` date NOT NULL,
  `data_wyjazdu` date NOT NULL,
  `caly_koszt` float(8,2) DEFAULT NULL,
  `pokoj_id_pokoj` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `rezerwacja`
--

INSERT INTO `rezerwacja` (`id_rezer`, `data_przyjazdu`, `data_wyjazdu`, `caly_koszt`, `pokoj_id_pokoj`) VALUES
(1, '2022-05-01', '2022-05-05', 520.00, 1),
(2, '2022-05-01', '2022-05-05', 520.00, 4),
(3, '2022-05-01', '2022-05-05', 1020.00, 2),
(4, '2022-05-16', '2022-05-17', 290.00, 5),
(5, '2022-06-01', '2022-06-05', 1850.00, 3),
(6, '2022-05-13', '2022-05-15', 600.00, 9),
(7, '2022-06-03', '2022-06-05', 620.00, 11);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rezerwacja_klient`
--

CREATE TABLE `rezerwacja_klient` (
  `id_rezerwacja_klient` int(7) NOT NULL,
  `rezer_id_rezer` int(7) DEFAULT NULL,
  `klient_id_klient` int(7) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `rezerwacja_klient`
--

INSERT INTO `rezerwacja_klient` (`id_rezerwacja_klient`, `rezer_id_rezer`, `klient_id_klient`) VALUES
(1, 1, 1),
(2, 2, 4),
(3, 3, 5),
(5, 4, 7),
(6, 5, 8),
(7, 6, 9),
(21, 7, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rezerwacja_udo`
--

CREATE TABLE `rezerwacja_udo` (
  `id_rez_udo` int(7) NOT NULL,
  `udo_id_udo` int(7) DEFAULT NULL,
  `rez_id_rez` int(7) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `rezerwacja_udo`
--

INSERT INTO `rezerwacja_udo` (`id_rez_udo`, `udo_id_udo`, `rez_id_rez`) VALUES
(1, 4, 1),
(2, 4, 2),
(3, 5, 3),
(4, 10, 3),
(7, 13, 5),
(8, 5, 6),
(26, 11, 5),
(30, 10, 4),
(31, 5, 7),
(32, 10, 7);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rodza_udo`
--

CREATE TABLE `rodza_udo` (
  `id_rodz_udo` int(6) NOT NULL,
  `nazwa` varchar(100) DEFAULT NULL,
  `cena` float(6,2) DEFAULT NULL,
  `udo_typ_pokoju` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `rodza_udo`
--

INSERT INTO `rodza_udo` (`id_rodz_udo`, `nazwa`, `cena`, `udo_typ_pokoju`) VALUES
(4, 'Room Service', 120.00, 1),
(5, 'Room Service', 120.00, 2),
(6, 'Room Service', 120.00, 3),
(10, 'Barek', 100.00, 2),
(11, 'Barek', 100.00, 3),
(13, 'Jacuzzi', 250.00, 3),
(14, 'Śniadanie do pokoju', 80.00, 1),
(15, 'Śniadanie do pokoju', 80.00, 2),
(16, 'Śniadanie do pokoju', 80.00, 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `skargi`
--

CREATE TABLE `skargi` (
  `id_skarga` int(8) NOT NULL,
  `tresc` varchar(500) DEFAULT NULL,
  `rez_id_rez` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `skargi`
--

INSERT INTO `skargi` (`id_skarga`, `tresc`, `rez_id_rez`) VALUES
(1, 'Zimna woda pod prysznicem!', 1),
(2, 'Mrówki w pokoju!', 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `stanowisko`
--

CREATE TABLE `stanowisko` (
  `id_stanowisko` int(30) NOT NULL,
  `nazwa_stanowiska` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `stanowisko`
--

INSERT INTO `stanowisko` (`id_stanowisko`, `nazwa_stanowiska`) VALUES
(1, 'Pokojówka'),
(2, 'Recepcjonista'),
(3, 'Bagażowy'),
(4, 'Kierowca'),
(5, 'Kelner'),
(6, 'Barman'),
(7, 'Dyrektor'),
(8, 'Barista');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `typ_pokoju`
--

CREATE TABLE `typ_pokoju` (
  `id_typ_pokoju` int(3) NOT NULL,
  `rodzaj_pokoju` varchar(50) DEFAULT NULL,
  `cena_noclegu` float(6,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `typ_pokoju`
--

INSERT INTO `typ_pokoju` (`id_typ_pokoju`, `rodzaj_pokoju`, `cena_noclegu`) VALUES
(1, 'Jednoosobowy', 100.00),
(2, 'Dwuosobowy', 200.00),
(3, 'Apartament', 350.00);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_account`
--

CREATE TABLE `user_account` (
  `account_id` int(11) NOT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `user_account`
--

INSERT INTO `user_account` (`account_id`, `firstname`, `lastname`, `username`, `password`) VALUES
(1, 'Admin', 'Admin', 'Admin', 'Admin'),
(2, 'Worker', 'Worker', 'Worker', 'Worker'),
(14, '.', '.', '.', '.'),
(17, 'Konrad', 'Basa', 'Konrad', '25252436463'),
(19, 'Marcin', 'Bonar', 'Marcin', '52678562325'),
(20, 'Piotr ', 'Bielecki', 'Piotr ', '13566876967'),
(22, 'Zofia', 'Lech', 'Zofia', '63368745454'),
(23, 'Jan', 'Nowak', 'Jan', '13446568768'),
(24, 'Karol', 'Wilk', 'Karol', '03342346574');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `formularz`
--
ALTER TABLE `formularz`
  ADD PRIMARY KEY (`id_formularz`),
  ADD KEY `fk_formhotel` (`hotel_id_hotel`);

--
-- Indeksy dla tabeli `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id_hotel`);

--
-- Indeksy dla tabeli `klient`
--
ALTER TABLE `klient`
  ADD PRIMARY KEY (`id_klient`);

--
-- Indeksy dla tabeli `opinie`
--
ALTER TABLE `opinie`
  ADD PRIMARY KEY (`id_opinia`),
  ADD KEY `fk_opiniahotel` (`hotel_id_hotel`);

--
-- Indeksy dla tabeli `pokoj`
--
ALTER TABLE `pokoj`
  ADD PRIMARY KEY (`id_pokoj`),
  ADD KEY `fk_pokojhotel` (`hotel_id_hotel`),
  ADD KEY `fk_pokojtyp` (`pokoj_typ_pokoju`);

--
-- Indeksy dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  ADD PRIMARY KEY (`ID_PRACOWNIK`),
  ADD KEY `fk_pracownikhotel` (`hotel_id_hotel`),
  ADD KEY `fk_pracownikstanowsiko` (`stanowisko_id_stanowisko`);

--
-- Indeksy dla tabeli `promocja`
--
ALTER TABLE `promocja`
  ADD PRIMARY KEY (`id_promocja`);

--
-- Indeksy dla tabeli `promocja_pokoj`
--
ALTER TABLE `promocja_pokoj`
  ADD PRIMARY KEY (`id_promocja_pokoj`),
  ADD KEY `fk_id_promocja` (`promocja_id_promocja`),
  ADD KEY `fk_id_pokoj` (`pokoj_id_pokoj`);

--
-- Indeksy dla tabeli `rezerwacja`
--
ALTER TABLE `rezerwacja`
  ADD PRIMARY KEY (`id_rezer`),
  ADD KEY `fk_rezpokoj` (`pokoj_id_pokoj`);

--
-- Indeksy dla tabeli `rezerwacja_klient`
--
ALTER TABLE `rezerwacja_klient`
  ADD PRIMARY KEY (`id_rezerwacja_klient`),
  ADD KEY `fk_id_rezerwacja` (`rezer_id_rezer`),
  ADD KEY `fk_id_klient` (`klient_id_klient`);

--
-- Indeksy dla tabeli `rezerwacja_udo`
--
ALTER TABLE `rezerwacja_udo`
  ADD PRIMARY KEY (`id_rez_udo`),
  ADD KEY `fk_id_udo` (`udo_id_udo`),
  ADD KEY `fk_id_rez` (`rez_id_rez`);

--
-- Indeksy dla tabeli `rodza_udo`
--
ALTER TABLE `rodza_udo`
  ADD PRIMARY KEY (`id_rodz_udo`),
  ADD KEY `fk_udopokoj` (`udo_typ_pokoju`);

--
-- Indeksy dla tabeli `skargi`
--
ALTER TABLE `skargi`
  ADD PRIMARY KEY (`id_skarga`),
  ADD KEY `fk_skarga_rez` (`rez_id_rez`);

--
-- Indeksy dla tabeli `stanowisko`
--
ALTER TABLE `stanowisko`
  ADD PRIMARY KEY (`id_stanowisko`);

--
-- Indeksy dla tabeli `typ_pokoju`
--
ALTER TABLE `typ_pokoju`
  ADD PRIMARY KEY (`id_typ_pokoju`);

--
-- Indeksy dla tabeli `user_account`
--
ALTER TABLE `user_account`
  ADD PRIMARY KEY (`account_id`);

--
-- AUTO_INCREMENT dla tabel zrzutów
--

--
-- AUTO_INCREMENT dla tabeli `formularz`
--
ALTER TABLE `formularz`
  MODIFY `id_formularz` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id_hotel` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `klient`
--
ALTER TABLE `klient`
  MODIFY `id_klient` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT dla tabeli `opinie`
--
ALTER TABLE `opinie`
  MODIFY `id_opinia` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `pokoj`
--
ALTER TABLE `pokoj`
  MODIFY `id_pokoj` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  MODIFY `ID_PRACOWNIK` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT dla tabeli `promocja`
--
ALTER TABLE `promocja`
  MODIFY `id_promocja` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT dla tabeli `promocja_pokoj`
--
ALTER TABLE `promocja_pokoj`
  MODIFY `id_promocja_pokoj` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=149;

--
-- AUTO_INCREMENT dla tabeli `rezerwacja`
--
ALTER TABLE `rezerwacja`
  MODIFY `id_rezer` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT dla tabeli `rezerwacja_klient`
--
ALTER TABLE `rezerwacja_klient`
  MODIFY `id_rezerwacja_klient` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT dla tabeli `rezerwacja_udo`
--
ALTER TABLE `rezerwacja_udo`
  MODIFY `id_rez_udo` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT dla tabeli `rodza_udo`
--
ALTER TABLE `rodza_udo`
  MODIFY `id_rodz_udo` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT dla tabeli `skargi`
--
ALTER TABLE `skargi`
  MODIFY `id_skarga` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `stanowisko`
--
ALTER TABLE `stanowisko`
  MODIFY `id_stanowisko` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT dla tabeli `typ_pokoju`
--
ALTER TABLE `typ_pokoju`
  MODIFY `id_typ_pokoju` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `user_account`
--
ALTER TABLE `user_account`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `formularz`
--
ALTER TABLE `formularz`
  ADD CONSTRAINT `fk_formhotel` FOREIGN KEY (`hotel_id_hotel`) REFERENCES `hotel` (`id_hotel`);

--
-- Ograniczenia dla tabeli `opinie`
--
ALTER TABLE `opinie`
  ADD CONSTRAINT `fk_opiniahotel` FOREIGN KEY (`hotel_id_hotel`) REFERENCES `hotel` (`id_hotel`);

--
-- Ograniczenia dla tabeli `pokoj`
--
ALTER TABLE `pokoj`
  ADD CONSTRAINT `fk_pokojhotel` FOREIGN KEY (`hotel_id_hotel`) REFERENCES `hotel` (`id_hotel`),
  ADD CONSTRAINT `fk_pokojtyp` FOREIGN KEY (`pokoj_typ_pokoju`) REFERENCES `typ_pokoju` (`id_typ_pokoju`);

--
-- Ograniczenia dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  ADD CONSTRAINT `fk_pracownikhotel` FOREIGN KEY (`hotel_id_hotel`) REFERENCES `hotel` (`id_hotel`),
  ADD CONSTRAINT `fk_pracownikstanowsiko` FOREIGN KEY (`stanowisko_id_stanowisko`) REFERENCES `stanowisko` (`id_stanowisko`);

--
-- Ograniczenia dla tabeli `promocja_pokoj`
--
ALTER TABLE `promocja_pokoj`
  ADD CONSTRAINT `fk_id_pokoj` FOREIGN KEY (`pokoj_id_pokoj`) REFERENCES `pokoj` (`id_pokoj`),
  ADD CONSTRAINT `fk_id_promocja` FOREIGN KEY (`promocja_id_promocja`) REFERENCES `promocja` (`id_promocja`);

--
-- Ograniczenia dla tabeli `rezerwacja`
--
ALTER TABLE `rezerwacja`
  ADD CONSTRAINT `fk_rezpokoj` FOREIGN KEY (`pokoj_id_pokoj`) REFERENCES `pokoj` (`id_pokoj`);

--
-- Ograniczenia dla tabeli `rezerwacja_klient`
--
ALTER TABLE `rezerwacja_klient`
  ADD CONSTRAINT `fk_id_klient` FOREIGN KEY (`klient_id_klient`) REFERENCES `klient` (`id_klient`),
  ADD CONSTRAINT `fk_id_rezerwacja` FOREIGN KEY (`rezer_id_rezer`) REFERENCES `rezerwacja` (`id_rezer`);

--
-- Ograniczenia dla tabeli `rezerwacja_udo`
--
ALTER TABLE `rezerwacja_udo`
  ADD CONSTRAINT `fk_id_rez` FOREIGN KEY (`rez_id_rez`) REFERENCES `rezerwacja` (`id_rezer`),
  ADD CONSTRAINT `fk_id_udo` FOREIGN KEY (`udo_id_udo`) REFERENCES `rodza_udo` (`id_rodz_udo`);

--
-- Ograniczenia dla tabeli `rodza_udo`
--
ALTER TABLE `rodza_udo`
  ADD CONSTRAINT `fk_udopokoj` FOREIGN KEY (`udo_typ_pokoju`) REFERENCES `typ_pokoju` (`id_typ_pokoju`);

--
-- Ograniczenia dla tabeli `skargi`
--
ALTER TABLE `skargi`
  ADD CONSTRAINT `fk_skarga_rez` FOREIGN KEY (`rez_id_rez`) REFERENCES `rezerwacja` (`id_rezer`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
