# Changelog


## Changelog aplikacji webowej systemu PlayBook.

- v1.0.0 - pomniejsze ostateczne latki.
- v0.9.0 - dodanie w pelni skonczonego front-endu + dodanie obslugi polskich znakow. 
- v0.8.0 - dodanie monitora wlasnych rezerwacji + mozliwosc anulowania rezerwacji. Wersja gotowa do wypuszczenia w swiat (program wykonuje swoj glowny cel).
- v0.7.8 - zakonczenie pracy nad rezerwacjami. Zabezpieczenie sesji. Do dodania: monitor rezerwacji i mozliwosc anulowania.
- v0.7.5 - dodanie funkcjonujacego formularza sprawdzajacego poprawnosc danych po wybraniu miejsc z formularza stworzonego w poprzednim commicie (submit przygotowany do wystosowania zapytania do bazy) + zabezpieczenia sesji rezerwacji + sytuacji, gdy nie user nie wybierze zadnego miejsca.
- v0.7.0 - dodanie w pelni funkcjonalnej makiety sali (wyswietla rzeczywisty wyglad sali, zajete miejsca, mozna wybrac swoje miejsca do rezerwacji) + serwlet na Submicie.
- v0.6.1 - dodanie opcji przegladania teatrow, dodanie opcji przegladania sztuk granych w wybranym teatrze, dodanie opcji zapoznania sie z opisem sztuki granej podczas wybranego seansu. Dodano (pusta) strone z danymi sali (podstawa do stworzenia sali).
- v0.5.0 - dodanie opcji edycji uzytkownika, dodano zabezpieczenia (edycja maila). 
- v0.4.5 - dodanie opcji usuniecia konta, fix kilku mniejszych bledow spowodowanych utrata wyswietlania danych. Cleanup. 
- v0.4.3 - w sumie pierwszy commit, po dlugim testowaniu stworzenie stabilnego rdzenia w postaci logowania. Podlaczenie Hibernate'a. Stworzenie stabilnej sesji logowania, usuwaniem po wylogowaniu. Stworzenie zabezpieczonego formularza rejestracyjnego. Stworzenie prostej przegladarki profilu uzytkownika. Mozliwosc zmiany hasla. Cleanup.

----------------------------------------------------

## Changelog panelu administracyjnego systemu PlayBook.

- v1.0.0 - pomniejsze ostateczne latki.
- v0.9.0 - poprawa kilku bledow (miedzy innymi bledne wstawianie numeru sali na miejsce usunietej). Zakonczenie pracy nad front-endem.
- v0.8.5 - poprawa wykrytych bledow, wprowadzenie zmian w profilu 'operator' (mniejsze prawa). Front-end czesciowo skonczony (zakonczone na rezerwacjach).
- v0.8.0 - zakonczenie pracy nad rezerwacjami. Cleanup. Fix wykrytych bledow. Wersja gotowa do wypuszczenia w swiat (program wykonuje swoj glowny cel).
- v0.7.8 - dodanie mozliwosci edycji rezerwacji dla kazdego typu sali.
- v0.7.4 - dodanie wyswietlaczy zarezerwowanych miejsc zarowno dla wszystkich jak i dla specyficznych rezerwacji dla kazdego rodzaju sali + dodanie obslugi dodawania rezerwacji dla kazdego rodzaju sali.
- v0.7.2 - mozliwosc dodania rezerwacji + zabezpieczenia + synchronizacja wyswietlania ilosci rezerwacji w monitorze seansow. 
- v0.7.1 - dodanie monitora rezerwacji + makietę dodawania rezerwacji miejsc dla duzej sali (dummy).
- v0.7.0 - zakonczenie pracy nad seansami. Cleanup. Nastepna tabela - Rezerwacje.
- v0.6.7 - usprawnienie edycji seansow (dodanie porcji zabezpieczen).
- v0.6.6 - dodanie opcji edycji seansow po wczesniejszym ponownym wybraniu teatru i sali. 
- v0.6.3 - dodanie opcji dodawania seansow z porcja zabezpieczen (brak zabezpieczenia przez wrzuceniem kolejnego seansu do tej samej sali o tej samej godzinie). Cleanup i modyfikacja monitora seansow. Fix bledow w sektorze Show.
- v0.6.2 - dodanie monitora seansow + dzialajacy w 50% formularz dodawania seansow (poki co wersja dummy, nie dodajaca).
- v0.6.1 - dodanie nowego poziomu konta - operator. Zwiekszenie mozliwosci poziomu admin, prace glownie przy obsludze tabeli 'Account'.
- v0.6.0 - zakonczenie sal + archiwum, fix wykrytych bledow, dodanie sortowania sal po numerach.
- v0.5.4 - dodanie edycji sal typu Big. Fix wykrytych bledow w poprzednich dwoch commitach. Standaryzacja rozmiarow poszczegolnych wizualizacji siedzen w salach.
- v0.5.3 - dodanie wyswietlacza sal typu Big.
- v0.5.2 - dodawanie duzej sali zakonczone, poprawa wstecznych bledow z wyswietlaniem danych i przekazywaniem parametrow do kolejnych scen.
- v0.5.11 - dodanie formularza wybierania typu dodawanych sal - AuditoriumChooser. Pelni on role rozgaleznika dla roznych typow sal.
- v0.5.1 - dodanie monitora sal.
- v0.5.0 - zakonczenie teatru + archiwum, przygotowanie do budowania sal teatralnych.
- v0.4.8 - dodawanie i edycja teatrow - przygotowanie do budowania sal teatralnych.
- v0.4.1 - fix bledow zwiazanych z przechodzeniem miedzy odpowiednimi menu, przypiecie niekompletnego monitora teatrow (to-do na pozniej).  
- v0.4.0 - dodanie pelnej kontroli nad tabela Play (monitor + add + edit + delete + przenoszenie do monitora archiwum przy pomocy funkcji + timestamp). Dodatkowo czesciowa kontrola nad tabela Genre (ale wystepuja watpliwosci, czy ta tabela bedzie potrzebna).
- v0.3.1 - minor - dodanie timestampa, kiedy cos zostalo usuniete (ArchiveAccount). Update funkcji w DB, dodanie do hibernate itp.
- v0.3.0 - dodanie opcji usuwania konta uzytkownika, cleanup, zabezpieczenie przez usunieciem wlasnego konta, praca w bazie - trigger + funkcja przenoszaca usuwane dane do tabeli archiwalnej, dodanie monitora archiwalnych danych kont. 
- v0.2.7 - modyfikacja mapowania tabeli uzytkownikow (StringProperty), dodanie monitora danych uzytkownikow + mozliwosc resetu hasla usera / zmiany wlasnego hasla. 
- v0.2.2 - dodanie menu dostepnego po zalogowaniu sie + opcje logoutu i wylaczenia programu.
- v0.2.1 - zalatanie bledow zwiazanych z logowaniem, ktore wyniknely podczas testowania.
- v0.2.0 - stworzenie 'pingu' do bazy danych (biblioteka c3p0), cleanup kodu, dodanie logowania wraz z zabezpieczeniami i szyfrowaniem hasla (zmiana hasla w bazie na zaszyfrowane SHA-1).
- v0.1.6 - poprawne polaczenie z baza danych poprzez Hibernate. Poprawienie wszelkich bledow spowodowanych Hibernate'm, testowe zapytanie do bazy (w opcji About).
- v0.1.52 - poprawa changelogu z .docx na .md.
- v0.1.51 - poprawa bledow mapowania Hibernate, glownie zmiany w strukturze bazy. Kolejna aktualizacja kodu naprawi bledy.
- v0.1.5 - podlaczenie i konfiguracja Hibernate'a.
- v0.1.4 - przebudowanie projektu na Maven.
- v0.1.3 - czysty szkielet z dodatkowymi usprawnieniami (Utility.java).
