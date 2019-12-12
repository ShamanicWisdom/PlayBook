# Projekt i implementacja systemu rezerwacji biletów dla teatrów.

## System Playbook 

W skład systemu wchodzi:

- Baza Danych na platformie PostgreSQL (schemat dostepny w folderze db w plikach Panelu Administracyjnego);
- Panel Administracyjny - PlayBook Panel;
- Aplikacja Webowa - PlayBook Web;

## Opis:
System do obsługi rezerwacji biletów do teatrów. Składa się z dwóch aplikacji:
- Aplikacji desktopowej dla pracowników (PlayBook Panel),
- Aplikacji webowej dla użytkowników (PlayBook Web).

Użytkownicy systemu są podzieleni na 3 typy:
- user - klient (użytkownik nie mający dostępu do aplikacji desktopowej),
- operator - pracownik (użytkownik mający ograniczone prawa do modyfikacji danych),
- admin - administrator (użytkownik mający nieograniczone prawa do modyfikacji).

## PlayBook Panel:
Aplikacja desktopowa przeznaczona dla pracowników sieci teatrów. Dostęp do niej mają tylko użytkownicy ze statusem konta 'operator' lub 'admin'. 

Po zalogowaniu do aplikacji użytkownik ma do dyspozycji dodania, edycji lub usunięcia poszczególnych danych:
- użytkowników (konta ze statusem 'operator' mogą modyfikować tylko konto swoje lub osoby ze statusem 'user'. Dane innych użytkowników typu 'operator' lub 'admin' są zawartością ukrytą),
- teatrów (po wybraniu teatru również ilości oraz kształtu sal zawartych w nim),
- sztuk,
- seansów (po wybraniu seansu również rezerwacji na niego),
Dostępne jest również (w trybie tylko do odczytu) przeglądanie danych archiwalnych ze wszystkich wyżej wymienionych sektorów.

Interfejs graficzny wykonany został przy użyciu JavaFX, estetyka wykonana przy pomocy CSS.

## PlayBook Web:
Aplikacja webowa przeznaczona dla klientów sieci teatrów. 

Po uruchomieniu dostępna jest rejestracja oraz możliwość zalogowania się. Po zalogowaniu użytkownik może przeglądać listę dostępnych teatrów oraz repertuarów sztuk granych w nich (po wcześniejszym wybraniu interesującego użytkownika teatru) lub przejść do swojego profilu (gdzie może modyfikować swoje dane lub usunąć konto).

Po wybraniu prze użytkownika interesującej go sztuki, aplikacja przeniesie go do interaktywnej mapki sali, na której jest pokazany aktualny stan miejsc na seans. Kolorystyka:
- szary - miejsce wolne,
- czerwony - miejsce już zajęte,
- zielony - wybrane przez użytkownika miejsce. 

Użytkownik może anulować swoją rezerwację lub modyfikować ją. 

Maksymalna liczba miejsc wybranych na jedną rezerwację to 6.

Użytkownik po wybraniu miejsc może określić ilość i typ biletów, które rezerwuje (wybór między normalnymi, a ulgowymi - z 50% zniżką).

## Technologie:
- PlayBook Panel:
  - Java,
  - JavaFX (GUI),
  - Hibernate,
  - CSS,
- PlayBook Panel:
  - JavaEE (JSP + Servlety),
  - Hibernate,
  - CSS,
- Baza danych PostgreSQL 9.6.
