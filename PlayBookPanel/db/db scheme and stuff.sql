--create tables

CREATE TABLE account 
(
	account_id serial NOT NULL PRIMARY KEY, 
	name character varying(50) NOT NULL, 
	surname character varying(50) NOT NULL, 
	email character varying NOT NULL, 
	password character varying NOT NULL, 
	phone_number integer, 
	role character varying(20) NOT NULL
);

CREATE TABLE auditorium 
(
	auditorium_id serial NOT NULL PRIMARY KEY, 
	theatre_id integer NOT NULL, 
	auditorium_number integer NOT NULL, 
	no_slots integer NOT NULL, 
	missing_slots character varying,
	auditorium_type character varying NOT NULL
);

CREATE TABLE play 
(
	play_id serial NOT NULL PRIMARY KEY, 
	name character varying(100) NOT NULL, 
	description character varying(1000) NOT NULL,
	genre character varying(50) NOT NULL 
);

CREATE TABLE reservation 
(
	reservation_id serial NOT NULL PRIMARY KEY, 
	show_id integer NOT NULL, 
	account_id integer NOT NULL, 
	no_slots integer NOT NULL, 
	slots_id character varying(200) NOT NULL, 
	no_normal integer NOT NULL, 
	no_half integer NOT NULL, 
	price real NOT NULL
);
	
CREATE TABLE show 
(
	show_id serial NOT NULL PRIMARY KEY, 
	auditorium_id integer NOT NULL, 
	play_id integer NOT NULL, 
	price real NOT NULL, 
	date date NOT NULL, 
	start_hour character varying(100) NOT NULL, 
	length integer NOT NULL, 
	slots_left integer NOT NULL
);

CREATE TABLE theatre 
(
	theatre_id serial NOT NULL PRIMARY KEY, 
	voivodeship character varying(100) NOT NULL, 
	city character varying(100) NOT NULL, 
	street character varying(100) NOT NULL, 
	name character varying NOT NULL, 
	phone_number integer NOT NULL
);

--create FKs

ALTER TABLE ONLY reservation ADD CONSTRAINT account_to_reservation_fk FOREIGN KEY (account_id) REFERENCES account(account_id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE ONLY show ADD CONSTRAINT auditorium_to_show_fk FOREIGN KEY (auditorium_id) REFERENCES auditorium(auditorium_id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE ONLY show ADD CONSTRAINT play_to_show_fk FOREIGN KEY (play_id) REFERENCES play(play_id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE ONLY reservation ADD CONSTRAINT show_to_reservation_fk FOREIGN KEY (show_id) REFERENCES show(show_id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE ONLY auditorium ADD CONSTRAINT theatre_to_auditorium_fk FOREIGN KEY (theatre_id) REFERENCES theatre(theatre_id) ON UPDATE CASCADE ON DELETE CASCADE;

--create archive tables

CREATE TABLE archive_account 
(
	account_id integer, 
	name character varying, 
	surname character varying, 
	email character varying, 
	password character varying, 
	phone_number integer, 
	role character varying, 
	delete_date timestamp without time zone
);

CREATE TABLE archive_play 
(
	play_id integer, 
	name character varying, 
	description character varying, 
	genre character varying, 
	delete_date timestamp without time zone
);

CREATE TABLE archive_theatre 
(
	theatre_id integer, 
	name character varying, 
	city character varying, 
	street character varying, 
	voivodeship character varying, 
	phone_number integer, 
	delete_date timestamp without time zone
);

CREATE TABLE archive_auditorium 
(
	auditorium_id integer, 
	theatre_id integer, 
	auditorium_number integer,
	no_slots integer, 
	missing_slots character varying, 
	auditorium_type character varying, 
	delete_date timestamp without time zone
);

CREATE TABLE archive_show 
(
	show_id integer, 
	price real, 
	date date, 
	start_hour character varying, 
	length integer, 
	slots_left integer, 
	play_name character varying, 
	theatre_and_auditorium_data character varying, 
	delete_date timestamp without time zone
);

CREATE TABLE archive_reservation 
(
	reservation_id integer, 
	show_data character varying, 
	account_data character varying, 
	no_slots integer, 
	slots_id character varying, 
	no_normal integer, 
	no_half integer, 
	price real, 
	delete_date timestamp without time zone
);

--funkcje przenoszace dane do archiwow 

CREATE FUNCTION "moveAccountToArchive"() 
RETURNS trigger 
LANGUAGE plpgsql AS $$ 
BEGIN 
	INSERT INTO archive_account 
	(
		account_id, 
		name, 
		surname, 
		email, 
		password, 
		phone_number, 
		role, 
		delete_date
	) 
	VALUES 
	(
		OLD.account_id, 
		OLD.name, 
		OLD.surname, 
		OLD.email, 
		OLD.password, 
		OLD.phone_number, 
		OLD.role, 
		date_trunc('second', transaction_timestamp() at time zone 'GMT-1')
	); 
	RETURN OLD; 
END; $$;


CREATE FUNCTION "movePlayToArchive"() 
RETURNS trigger 
LANGUAGE plpgsql AS $$ 
BEGIN 
	INSERT INTO archive_play 
	(
		play_id, 
		name, 
		description, 
		genre, 
		delete_date
	) 
	VALUES 
	(
		OLD.play_id, 
		OLD.name, 
		OLD.description, 
		OLD.genre, 
		date_trunc('second', transaction_timestamp() at time zone 'GMT-1')
	); 
	RETURN OLD; 
END; $$;

CREATE FUNCTION "moveTheatreToArchive"() 
RETURNS trigger 
LANGUAGE plpgsql AS $$ 
BEGIN 
	INSERT INTO archive_theatre 
	(
		theatre_id, 
		name, 
		city, 
		street, 
		voivodeship, 
		phone_number, 
		delete_date
	)
	VALUES 
	(
		OLD.theatre_id, 
		OLD.name, 
		OLD.city, 
		OLD.street, 
		OLD.voivodeship, 
		OLD.phone_number, 
		date_trunc('second', transaction_timestamp() at time zone 'GMT-1')
	); 
	RETURN OLD; 
END; $$;

CREATE FUNCTION "moveAuditoriumToArchive"() 
RETURNS trigger 
LANGUAGE plpgsql AS $$ 
BEGIN 
	INSERT INTO archive_auditorium 
	(
		auditorium_id, 
		theatre_id, 
		auditorium_number, 
		no_slots, 
		missing_slots, 
		auditorium_type, 
		delete_date
	) 
	VALUES 
	(
		OLD.auditorium_id, 
		OLD.theatre_id, 
		OLD.auditorium_number, 
		OLD.no_slots, 
		OLD.missing_slots, 
		OLD.auditorium_type, 
		date_trunc('second', transaction_timestamp() at time zone 'GMT-1')
	); 
	RETURN OLD; 
END; $$;

CREATE FUNCTION "moveShowToArchive"() 
RETURNS trigger 
LANGUAGE plpgsql AS $$ 
DECLARE 
	theatreLocation character varying; 
	theatreName character varying; 
	auditoriumNumber integer; 
	playName character varying; 
	theatreID integer; 
BEGIN 
	SELECT theatre_id FROM auditorium WHERE auditorium_id = OLD.auditorium_id INTO theatreID; 
	SELECT city FROM theatre WHERE theatre_id = theatreID INTO theatreLocation; 
	SELECT name FROM theatre WHERE theatre_id = theatreID INTO theatreName; 
	SELECT auditorium_number FROM auditorium WHERE auditorium_id = OLD.auditorium_id INTO auditoriumNumber; 
	SELECT name FROM play WHERE play_id = OLD.play_id INTO playName; 
	INSERT INTO archive_show 
	(
		show_id, 
		price, 
		date, 
		start_hour, 
		length, 
		slots_left, 
		theatre_and_auditorium_data, 
		play_name, 
		delete_date
	) 
	VALUES 
	(
		OLD.show_id, 
		OLD.price, 
		OLD.date, 
		OLD.start_hour, 
		OLD.length, 
		OLD.slots_left, 
		theatreLocation || ', ' || theatreName || ', Sala: ' || auditoriumNumber, 
		playName, 
		date_trunc('second', transaction_timestamp() at time zone 'GMT-1')
	); 
	RETURN OLD; 
END; $$;

CREATE FUNCTION "moveReservationToArchive"() 
RETURNS trigger 
LANGUAGE plpgsql AS $$ 
DECLARE 
	accountName character varying; 
	accountSurname character varying; 
	playName character varying; 
	playID integer; 
BEGIN 
	SELECT name FROM account WHERE account_id = OLD.account_id INTO accountName;	
	SELECT surname FROM account WHERE account_id = OLD.account_id INTO accountSurname; 
	SELECT play_id FROM show WHERE show_id = OLD.show_id INTO playID; 
	SELECT name FROM play WHERE play_id = playID INTO playName; 
	INSERT INTO archive_reservation 
	(
		reservation_id, 
		show_data, 
		account_data, 
		no_slots, 
		slots_id, 
		no_normal, 
		no_half, 
		price, 
		delete_date
	) 
	VALUES 
	(
		OLD.reservation_id, 
		playName || ', Seans ID: ' || OLD.show_id, 
		accountName || ' ' || accountSurname || ' ID: ' || OLD.account_id, 
		OLD.no_slots, 
		OLD.slots_id, 
		OLD.no_normal, 
		OLD.no_half, 
		OLD.price, 
		date_trunc('second', transaction_timestamp() at time zone 'GMT-1')
	); 
	RETURN OLD; 
END; $$;
		
--triggery dla funkcji przenoszacych do archiwow 

CREATE TRIGGER "moveAccountToArchiveTrigger" BEFORE DELETE ON account FOR EACH ROW EXECUTE PROCEDURE "moveAccountToArchive"();
CREATE TRIGGER "movePlayToArchiveTrigger" BEFORE DELETE ON play FOR EACH ROW EXECUTE PROCEDURE "movePlayToArchive"();
CREATE TRIGGER "moveTheatreToArchiveTrigger" BEFORE DELETE ON theatre FOR EACH ROW EXECUTE PROCEDURE "moveTheatreToArchive"();
CREATE TRIGGER "moveAuditoriumToArchiveTrigger" BEFORE DELETE ON auditorium FOR EACH ROW EXECUTE PROCEDURE "moveAuditoriumToArchive"();
CREATE TRIGGER "moveShowToArchiveTrigger" BEFORE DELETE ON show FOR EACH ROW EXECUTE PROCEDURE "moveShowToArchive"();
CREATE TRIGGER "moveReservationToArchiveTrigger" BEFORE DELETE ON reservation FOR EACH ROW EXECUTE PROCEDURE "moveReservationToArchive"();

--unique constrainty

ALTER TABLE public.theatre ADD CONSTRAINT unique_phone_number UNIQUE (phone_number);
ALTER TABLE public.account ADD CONSTRAINT unique_email UNIQUE (email);

--inserty

--admin acc

INSERT INTO public.account(name, surname, email, password, phone_number, role) VALUES ('Szymon', 'Zawadzki', 'admin@playbook.com', 'f3f9617dbc551ea61d78c9e65868509a5eca80c1', 600988177, 'admin');

--operator acc

INSERT INTO public.account(name, surname, email, password, phone_number, role) VALUES ('First', 'Operator', 'operator1@playbook.com', 'f3f9617dbc551ea61d78c9e65868509a5eca80c1', 600988177, 'operator');
INSERT INTO public.account(name, surname, email, password, phone_number, role) VALUES ('Second', 'Operator', 'operator2@playbook.com', 'f3f9617dbc551ea61d78c9e65868509a5eca80c1', 600988177, 'operator');

--other stuff

INSERT INTO public.account(name, surname, email, password, phone_number, role) VALUES ('Test', 'Test', 'rngtest@test.com', '7505d64a54e061b7acd54ccd58b49dc43500b635', 123456789, 'user');

--Romeo i Julia sformatowane.
INSERT INTO public.play(name, genre, description) VALUES (
'Romeo i Julia', 
'Tragedia',
'Romeo i Julia są bohaterami sławnej legendy włoskiej, która dzięki dramatowi Szekspira i wielu innym arcydziełom sztuki stała się dziedzictwem całej ludzkości i żywym ciągle symbolem niepokonanej miłości.

Romeo i Julia pochodzą z dwu zwaśnionych rodów Werony - Montekich i Kapuletich. Romego, ukryty pod maską, wkrada się na bal urządzany przez wrogi ród i poznaje tam piękną Julię. Zakochuje się w córce nieprzejednanego wroga. Umawia się z nią na nocne spotkanie. Zakrada się do ogrodu Kapuletich i rozmawiając z Julią, stojącą na balkonie, wyznaje jej miłość. Julia również jest oczarowana młodzieńcem. Wiedzą, że na przeszkodzie stoi śmiertelna nienawiść rodziców.
'
);

--Teatry

INSERT INTO public.theatre(name, city, street, voivodeship, phone_number) VALUES ('Teatr Wielki', 'Warszawa', 'Marszałkowska', 'Mazowieckie', 123456789);
INSERT INTO public.theatre(name, city, street, voivodeship, phone_number) VALUES ('Teatr Nowy', 'Łódź', 'Szpitalna', 'Łódzkie', 500988766);

--Sale

INSERT INTO public.auditorium(theatre_id, auditorium_number, no_slots, missing_slots, auditorium_type) VALUES (1, 1, 94, 'A1, B1, C1, A10, B10, C10', 'Big');
INSERT INTO public.auditorium(theatre_id, auditorium_number, no_slots, missing_slots, auditorium_type) VALUES (1, 2, 94, 'J1, I1, H1, H10, I10, J10', 'Big');
INSERT INTO public.auditorium(theatre_id, auditorium_number, no_slots, missing_slots, auditorium_type) VALUES (1, 3, 68, 'A1, B1, C1, H1, I1, J1, J4, I4, H4, G4, F4, E4, D4, C4, A4, B4, A7, B7, C7, D7, E7, F7, G7, H7, I7, J7, J10, I10, H10, C10, B10, A10', 'Big');
INSERT INTO public.auditorium(theatre_id, auditorium_number, no_slots, missing_slots, auditorium_type) VALUES (2, 1, 46, 'A1, B1, D10, E10', 'Small');
INSERT INTO public.auditorium(theatre_id, auditorium_number, no_slots, missing_slots, auditorium_type) VALUES (2, 2, 64, 'A9, B9, C9, D9, E9, E10, A2, B2, C2, D2, E2, E1, H4, H5, H6, H7', 'Medium');
INSERT INTO public.auditorium(theatre_id, auditorium_number, no_slots, missing_slots, auditorium_type) VALUES (2, 3, 92, 'A9, B9, C9, C10, A2, B2, C2, C1', 'Big');

--Seanse

INSERT INTO public.show(auditorium_id, play_id, price, date, start_hour, length, slots_left) VALUES (2,1,30.5, '2017-12-20', '12:00', 130, 94);
