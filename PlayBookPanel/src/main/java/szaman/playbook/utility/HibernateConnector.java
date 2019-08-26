/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa pomocnicza dla Hibernate przechowujaca zapytania - HibernateConnector.java.
 * 
 */

package szaman.playbook.utility;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.entity.Account;
import szaman.playbook.entity.archive.ArchiveAccount;
import szaman.playbook.entity.Auditorium;
import szaman.playbook.entity.Play;
import szaman.playbook.entity.Show;
import szaman.playbook.entity.Theatre;
import szaman.playbook.entity.archive.ArchiveAuditorium;
import szaman.playbook.entity.archive.ArchivePlay;
import szaman.playbook.entity.archive.ArchiveShow;
import szaman.playbook.entity.archive.ArchiveTheatre;

// Reszta klas //

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import szaman.playbook.entity.Reservation;
import szaman.playbook.entity.archive.ArchiveReservation;

/**
 *
 * @author Szaman
 */
public class HibernateConnector 
{      
    Utility utility = new Utility();
    
    //Ping, który sprawdza łączność z bazą danych. Fragment biblioteki C3P0.
    public boolean testConnection() throws PropertyVetoException, SQLException
    {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("org.postgresql.Driver");
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/newBase");
        cpds.setUser("postgres");
        cpds.setAcquireRetryAttempts(1);
        
        try
        {
            cpds.getConnection();
            System.out.println("DB Connection established!");
        }
        catch(SQLException e)
        {
            System.out.println("DB Connection could not been established!");
            return false;
        }
        try
        {
            cpds.close();
        }
        catch(HibernateException e)
        {
            return false;
        }
        return true;
    }
    
    /********************************************************************************************************************************************/
    /*                                                                   ACCOUNT                                                                */
    /********************************************************************************************************************************************/
    
    //Metoda logujaca.
    public int accountValidation(String email, String password) throws NoSuchAlgorithmException 
    {
        String encryptedPassword = utility.sha1Encryptor(password);
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "SELECT A.role FROM Account A WHERE A.email = :email AND A.password = :password";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        query.setParameter("password", encryptedPassword);
        List<Account> accountList = query.list();
        System.out.println(accountList.size());
        if(accountList.isEmpty())
        {
            session.close();
            return 0;
        }
        else
        {
            String userRole = query.list().get(0).toString();
            session.close();
            if(userRole.equals("user"))
            {
                return 1;
            }
            else
            {
                return 2;
            }
        }        
    }
    
    //Sprawdzenie, czy mail figuruje w bazie.
    public boolean isEmailExists(String email)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "FROM Account A WHERE A.email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        List<Account> accountList = query.list();
        System.out.println(accountList.size());
        if(accountList.isEmpty())
        {
            session.close();
            return false;
        }
        else
        {
            session.close();
            return true;
        }        
    }
    
    //Pobranie poziomu konta uzytkownika.
    public String getAccountRole(String email)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "SELECT A.role FROM Account A WHERE A.email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        String userRole = query.list().get(0).toString();
        session.close();
        return userRole;
    }
    
    //Pobranie imienia uzytkownika.
    public String getUserName(String email)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "SELECT A.name FROM Account A WHERE A.email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        String userName = query.list().get(0).toString();
        session.close();
        return userName;
    }
    
    //Pobranie hasla uzytkownika.
    public String pullUserPassword(String email)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "SELECT A.password FROM Account A WHERE A.email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        String userPassword = query.list().get(0).toString();
        session.close();
        return userPassword;
    }
    
    //Pobranie maila uzytkownika.
    public String pullUserEmail(int accountID)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "SELECT A.email FROM Account A WHERE account_id = :account_id";
        Query query = session.createQuery(hql);
        query.setParameter("account_id", accountID);
        String userEmail = query.list().get(0).toString();
        session.close();
        return userEmail;
    }
    
    //Pobranie listy danych uzytkownikow.
    public List<Account> pullAccountList() 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from Account A";
        Query query = session.createQuery(hql);
        List<Account> accountList = query.list();
        session.close();
        return accountList;    
    }
    
    //Pobranie danych konta.
    public List<Account> pullAccountData(int accountID) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "FROM Account A WHERE account_id = :account_id";
        Query query = session.createQuery(hql);
        query.setParameter("account_id", accountID);
        List<Account> accountList = query.list();
        session.close();
        return accountList;    
    }
    
    //Pobranie danych konta.
    public Account pullSingleAccount(int accountID) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "FROM Account A WHERE account_id = :account_id";
        Query query = session.createQuery(hql);
        query.setParameter("account_id", accountID);
        List<Account> accountList = query.list();
        session.close();
        return accountList.get(0);    
    }
    
    //Pobranie danych konta.
    public Account pullSingleAccountViaEmail(String email) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "FROM Account A WHERE email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        List<Account> accountList = query.list();
        session.close();
        return accountList.get(0);    
    }
    
    //Dodanie konta.
    public void addAccount(String name, String surname, String email, String password, String phoneNumber, String role) throws NoSuchAlgorithmException
    {
        String encryptedPassword = utility.sha1Encryptor(password);
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "INSERT INTO ACCOUNT (name, surname, email, password, phone_number, role) "
                   + "VALUES "
                   + "(:name, :surname, :email, :password, :phone_number, :role)";
        int insertedEntity = session.createSQLQuery(hql)
                             .setString("name", name)
                             .setString("surname", surname)
                             .setString("email", email)
                             .setString("password", encryptedPassword)
                             .setInteger("phone_number", Integer.parseInt(phoneNumber))
                             .setString("role", role)
                             .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Aktualizacja danych usera.
    public void editAccountData(String name, String surname, String email, String password, String phoneNumber, String role, int accountID) throws NoSuchAlgorithmException
    {
        String encryptedPassword = utility.sha1Encryptor(password);
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Account SET name = :name, surname = :surname, email = :email, password = :password, phone_number = :phoneNumber, role = :role "
                   + "WHERE account_id = :account_id";
        int updatedEntity = session.createSQLQuery(hql)
                            .setString("name", name)
                            .setString("surname", surname)
                            .setString("email", email)
                            .setString("password", encryptedPassword)
                            .setInteger("phoneNumber", Integer.parseInt(phoneNumber))
                            .setString("role", role)
                            .setInteger("account_id", accountID)
                            .executeUpdate();
        transaction.commit();
        session.close();
    }
            
    //Reset hasla usera.
    public void resetUserPassword(String userEmail) throws NoSuchAlgorithmException
    {
        String encryptedPassword = utility.sha1Encryptor("default");
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Account SET password = :password WHERE email = :email";
        int updatedEntity = session.createSQLQuery(hql)
                            .setString("password", encryptedPassword)
                            .setString("email", userEmail)
                            .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Reset hasla usera.
    public void selfChangePassword(String userEmail, String password) throws NoSuchAlgorithmException
    {
        String encryptedPassword = utility.sha1Encryptor(password);
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Account SET password = :password WHERE email = :email";
        int updatedEntity = session.createSQLQuery(hql)
                            .setString("password", encryptedPassword)
                            .setString("email", userEmail)
                            .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Usuniecie konta uzytkownika
    public void deleteAccount(String userEmail)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "DELETE FROM Account WHERE email = :email";
        int deletedEntity = session.createSQLQuery(hql)
                            .setString("email", userEmail)
                            .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    /********************************************************************************************************************************************/
    /*                                                                    PLAY                                                                  */
    /********************************************************************************************************************************************/
    
    //Pobranie listy danych sztuk.
    public List<Play> pullPlayList() 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "FROM Play P";
        Query query = session.createQuery(hql);
        List<Play> playList = query.list();
        session.close();
        return playList;    
    }
    
    //Pobranie danych sztuki.
    public List<Play> pullPlayData(int playID) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "FROM Play P WHERE play_id = :play_id";
        Query query = session.createQuery(hql);
        query.setParameter("play_id",playID);
        List<Play> playList = query.list();
        session.close();
        return playList;    
    }
    
    //Pobieranie danych pojedynczej sztuki.
    public Play pullSinglePlay(int playID) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from Play WHERE play_id = :play_id";
        Query query = session.createQuery(hql);
        query.setParameter("play_id", playID);
        List<Play> playList = query.list();
        session.close();
        return playList.get(0);    
    }
    
    //Sprawdzenie, czy sztuka istnieje.
    public boolean isPlayExists(String name)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "FROM Play P WHERE P.name = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        List<Play> playList = query.list();
        if(playList.isEmpty())
        {
            session.close();
            return false;
        }
        else
        {
            session.close();
            return true;            
        }        
    }
    
    //Dodanie sztuki.
    public void addPlay(String playName, String playDescription, String genre)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "INSERT INTO PLAY (name, description, genre) VALUES (:playName, :playDescription, :genre)";
        int insertedEntity = session.createSQLQuery(hql)
                             .setString("playName", playName)
                             .setString("playDescription", playDescription)
                             .setString("genre", genre)
                             .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Aktualizacja danych sztuki.
    public void editPlayData(int playID, String name, String genre, String description)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Play SET name = :name, genre = :genre, description = :description WHERE play_id = :play_id";
        int updatedEntity = session.createSQLQuery(hql)
                            .setString("name", name)
                            .setString("genre", genre)
                            .setString("description", description)
                            .setInteger("play_id", playID)
                            .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Usuniecie sztuki.
    public void deletePlay(int playID)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "DELETE FROM Play WHERE play_id = :play_id";
        int deletedEntity = session.createSQLQuery(hql)
                            .setInteger("play_id", playID)
                            .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    /********************************************************************************************************************************************/
    /*                                                                   THEATRE                                                                */
    /********************************************************************************************************************************************/
    
    //Pobranie listy danych teatrow.
    public List<Theatre> pullTheatreList() 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from Theatre T";
        Query query = session.createQuery(hql);
        List<Theatre> theatreList = query.list();
        session.close();
        return theatreList;    
    }
    
    //Pobieranie danych pojedynczego teatru.
    public Theatre pullSingleTheatre(int theatreID) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from Theatre WHERE theatre_id = :theatre_id";
        Query query = session.createQuery(hql);
        query.setParameter("theatre_id", theatreID);
        List<Theatre> theatreList = query.list();
        session.close();
        return theatreList.get(0);    
    }
    
    //Pobranie danych wybranego teatru.
    public List<Theatre> pullTheatreData(int theatreId) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "FROM Theatre T WHERE theatre_id = :theatre_id";
        Query query = session.createQuery(hql);
        query.setParameter("theatre_id", theatreId);
        List<Theatre> theatreList = query.list();
        session.close();
        return theatreList;    
    }
    
    //Sprawdzenie, czy teatr istnieje.
    public boolean isTheatreExists(String name, String phoneNumber)
    {
        int convertedPhoneNumber = Integer.parseInt(phoneNumber);
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "FROM Theatre T WHERE T.name = :name AND T.phoneNumber = :phoneNumber";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        query.setParameter("phoneNumber", convertedPhoneNumber);
        List<Theatre> theatreList = query.list();
        if(theatreList.isEmpty())
        {
            session.close();
            return false;
        }
        else
        {
            session.close();
            return true;            
        }        
    }
    
    //Dodanie teatru.
    public void addTheatre(String name, String city, String street, String voivodeship, String phoneNumber)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "INSERT INTO Theatre (name, city, street, voivodeship, phone_number) VALUES (:name, :city, :street, :voivodeship, :phoneNumber)";
        int insertedEntity = session.createSQLQuery(hql)
                             .setString("name", name)
                             .setString("city", city)
                             .setString("street", street)
                             .setString("voivodeship", voivodeship)
                             .setInteger("phoneNumber", Integer.parseInt(phoneNumber))
                             .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Aktualizacja danych teatru.
    public void editTheatreData(int theatreID, String name, String city, String street, String voivodeship, String phoneNumber)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Theatre SET name = :name, city = :city, street = :street, voivodeship = :voivodeship, phone_number = :phone_number WHERE theatre_id = :theatre_id";
        int updatedEntity = session.createSQLQuery(hql)
                            .setString("name", name)
                            .setString("city", city)
                            .setString("street", street)
                            .setString("voivodeship", voivodeship)
                            .setInteger("phone_number", Integer.parseInt(phoneNumber))
                            .setInteger("theatre_id", theatreID)
                            .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Usuniecie teatru.
    public void deleteTheatre(int theatreID)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "DELETE FROM Theatre WHERE theatre_id = :theatre_id";
        int deletedEntity = session.createSQLQuery(hql)
                            .setInteger("theatre_id", theatreID)
                            .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    /********************************************************************************************************************************************/
    /*                                                                 AUDITORIUM                                                               */
    /********************************************************************************************************************************************/
    
    //Pobranie listy danych sal.
    public List<Auditorium> pullAuditoriumList(Theatre theatre) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from Auditorium as A WHERE A.theatre.theatreId = :theatre_id ORDER BY auditorium_number ASC";
        Query query = session.createQuery(hql);
        query.setParameter("theatre_id", theatre.getTheatreId());
        List<Auditorium> auditoriumList = query.list();
        session.close();
        return auditoriumList;    
    }
    
    //Pobranie danych sali.
    public List<Auditorium> pullAuditoriumData(int auditoriumID) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from Auditorium WHERE auditorium_id = :auditorium_id";
        Query query = session.createQuery(hql);
        query.setParameter("auditorium_id", auditoriumID);
        List<Auditorium> auditoriumList = query.list();
        session.close();
        return auditoriumList;    
    }
    
    //Pobranie danych pojedynczej sali.
    public Auditorium pullSingleAuditorium(int auditoriumID) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from Auditorium WHERE auditorium_id = :auditorium_id";
        Query query = session.createQuery(hql);
        query.setParameter("auditorium_id", auditoriumID);
        List<Auditorium> auditoriumList = query.list();
        session.close();
        return auditoriumList.get(0);    
    }
    
    //Dodanie sali.
    public void addAuditorium(int theatreID, int auditoriumNumber, int noSlots, String missingSlots, String auditoriumType)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
                
        String hql = "INSERT INTO Auditorium (theatre_id, auditorium_number, no_slots, missing_slots, auditorium_type) "
                   + "VALUES (:theatreID, :auditoriumNumber, :noSlots, :missingSlots, :auditoriumType)";
        
        int insertedEntity = session.createSQLQuery(hql)
                             .setInteger("theatreID", theatreID)
                             .setInteger("auditoriumNumber", auditoriumNumber)
                             .setInteger("noSlots", noSlots)
                             .setString("missingSlots", missingSlots)
                             .setString("auditoriumType", auditoriumType)
                             .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Edycja sali.
    public void editAuditorium(int theatreID, int auditoriumNumber, int noSlots, String missingSlots)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "UPDATE Auditorium SET no_slots = :no_slots, missing_slots = :missing_slots WHERE theatre_id = :theatre_id AND auditorium_number = :auditorium_number";
        
        int updatedEntity = session.createSQLQuery(hql)
                            .setInteger("theatre_id", theatreID)
                            .setInteger("auditorium_number", auditoriumNumber)
                            .setInteger("no_slots", noSlots)
                            .setString("missing_slots", missingSlots)
                            .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Usuniecie sali.
    public void deleteAuditorium(int auditoriumID)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "DELETE FROM Auditorium WHERE auditorium_id = :auditorium_id";
        int deletedEntity = session.createSQLQuery(hql)
                            .setInteger("auditorium_id", auditoriumID)
                            .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    /********************************************************************************************************************************************/
    /*                                                                    SHOW                                                                  */
    /********************************************************************************************************************************************/
    
    //Pobranie listy danych sztuk.
    public List<Show> pullShowList() 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from Show S";
        Query query = session.createQuery(hql);
        List<Show> showList = query.list();
        session.close();
        return showList;    
    }
    
    //Pobranie danych sali.
    public List<Show> pullShowData(int showID) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from Show WHERE show_id = :show_id";
        Query query = session.createQuery(hql);
        query.setParameter("show_id", showID);
        List<Show> showList = query.list();
        session.close();
        return showList;    
    }
    
    //Pobranie danych seansu.
    public Show pullSingleShow(int showID) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "FROM Show A WHERE show_id = :show_id";
        Query query = session.createQuery(hql);
        query.setParameter("show_id", showID);
        List<Show> showList = query.list();
        session.close();
        return showList.get(0);    
    }
    
    //Pobranie listy danych sztuk o podanej dacie..
    public List<Show> pullSpecificDateShowList(LocalDate date, Auditorium auditorium) 
    {
        Date convertedDate = Date.valueOf(date);
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from Show S where date = :date AND auditorium_id = :auditorium_id";
        Query query = session.createQuery(hql);
        query.setParameter("date", convertedDate);
        query.setParameter("auditorium_id", auditorium.getAuditoriumId());
        List<Show> showList = query.list();
        session.close();
        return showList;    
    }
    
    //Dodanie gatunku.
    public void addShow(Theatre theatre, Auditorium auditorium, Play play, String price, LocalDate date, String startHour, String length)
    {
        Float convertedPrice = Float.parseFloat(price.replace(",", "."));
        Date convertedDate = Date.valueOf(date);
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "INSERT INTO SHOW (auditorium_id, play_id, price, date, start_hour, length, slots_left) "
                   + "VALUES (:auditorium_id, :play_id, :price, :date, :start_hour, :length, :slots_left)";
        int insertedEntity = session.createSQLQuery(hql)
                             .setInteger("auditorium_id", auditorium.getAuditoriumId())
                             .setInteger("play_id", play.getPlayId())
                             .setFloat("price", convertedPrice)
                             .setDate("date", convertedDate)
                             .setString("start_hour", startHour)
                             .setInteger("length", Integer.parseInt(length))
                             .setInteger("slots_left", auditorium.getNoSlots())
                             .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Edycja seansu.
    public void editShow(int showID, Theatre theatre, Auditorium auditorium, Play play, String price, LocalDate date, String startHour, String length)
    {
        Float convertedPrice = Float.parseFloat(price.replace(",", "."));
        Date convertedDate = Date.valueOf(date);
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "UPDATE SHOW SET auditorium_id = :auditorium_id, play_id = :play_id, price = :price, date = :date, start_hour = :start_hour, length = :length WHERE show_id = :show_id";
        
        int updatedEntity = session.createSQLQuery(hql)
                             .setInteger("auditorium_id", auditorium.getAuditoriumId())
                             .setInteger("play_id", play.getPlayId())
                             .setFloat("price", convertedPrice)
                             .setDate("date", convertedDate)
                             .setString("start_hour", startHour)
                             .setInteger("length", Integer.parseInt(length))
                             .setInteger("show_id", showID)
                             .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Edycja seansu.
    public void updateShowSlotsLeft(Show show, int reservationCount)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        Auditorium auditorium = pullSingleAuditorium(show.getAuditorium().getAuditoriumId());
        int currentSlotsLeft = auditorium.getNoSlots() - reservationCount;
        
        String hql = "UPDATE SHOW SET slots_left = :slots_left WHERE show_id = :show_id";
        
        int updatedEntity = session.createSQLQuery(hql)
                             .setInteger("slots_left", currentSlotsLeft)
                             .setInteger("show_id", show.getShowId())
                             .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Usuniecie seansu.
    public void deleteShow(int showID)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "DELETE FROM Show WHERE show_id = :show_id";
        int deletedEntity = session.createSQLQuery(hql)
                            .setInteger("show_id", showID)
                            .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    /********************************************************************************************************************************************/
    /*                                                                RESERVATION                                                               */
    /********************************************************************************************************************************************/
    
    //Pobranie listy rezerwacji na dany seans.
    public List<Reservation> pullReservationList(Show show) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from Reservation as R WHERE R.show.showId = :show_id";
        Query query = session.createQuery(hql);
        query.setParameter("show_id", show.getShowId());
        List<Reservation> reservationList = query.list();
        session.close();
        return reservationList;    
    }
    
    //Dodanie rezerwacji.
    public void addReservation(Account account, Show show, int noSlots, String slotsID, int noNormal, int noHalf, float price)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "INSERT INTO RESERVATION (account_id, show_id, no_slots, slots_id, no_normal, no_half, price) "
                   + "VALUES (:account_id, :show_id, :no_slots, :slots_id, :no_normal, :no_half, :price)";
        
        int insertedEntity = session.createSQLQuery(hql)
                             .setInteger("account_id", account.getAccountId())
                             .setInteger("show_id", show.getShowId())
                             .setInteger("no_slots", noSlots)
                             .setString("slots_id", slotsID)
                             .setInteger("no_normal", noNormal)
                             .setInteger("no_half", noHalf)
                             .setFloat("price", price)
                             .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Edycja rezerwacji.
    public void editReservation(int reservationID, int noSlots, String slotsID, int noNormal, int noHalf, float price)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "UPDATE RESERVATION SET "
                   + "no_slots = :no_slots, slots_id = :slots_id, no_normal = :no_normal, no_half = :no_half, price = :price "
                   + "WHERE reservation_id = :reservation_id";
        
        int insertedEntity = session.createSQLQuery(hql)
                             .setInteger("no_slots", noSlots)
                             .setString("slots_id", slotsID)
                             .setInteger("no_normal", noNormal)
                             .setInteger("no_half", noHalf)
                             .setFloat("price", price)
                             .setInteger("reservation_id", reservationID)
                             .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Usuniecie rezerwacji.
    public void deleteReservation(int reservationID)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "DELETE FROM Reservation WHERE reservation_id = :reservation_id";
        int deletedEntity = session.createSQLQuery(hql)
                            .setInteger("reservation_id", reservationID)
                            .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    /********************************************************************************************************************************************/
    /*                                                                  ARCHIWUM                                                                */
    /********************************************************************************************************************************************/
    
    //Pobranie archiwalnej listy danych uzytkownikow.
    public List<ArchiveAccount> pullArchiveAccountList() 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from ArchiveAccount A";
        Query query = session.createQuery(hql);
        List<ArchiveAccount> accountList = query.list();
        session.close();
        return accountList;    
    }
    
    //Pobranie archiwalnej listy danych sztuk.
    public List<ArchivePlay> pullArchivePlayList() 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from ArchivePlay P";
        Query query = session.createQuery(hql);
        List<ArchivePlay> playList = query.list();
        session.close();
        return playList;    
    }
    
    //Pobranie archiwalnej listy danych teatrow.
    public List<ArchiveTheatre> pullArchiveTheatreList() 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from ArchiveTheatre T";
        Query query = session.createQuery(hql);
        List<ArchiveTheatre> theatreList = query.list();
        session.close();
        return theatreList;    
    }
    
    //Pobranie archiwalnej listy danych sal.
    public List<ArchiveAuditorium> pullArchiveAuditoriumList() 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from ArchiveAuditorium A";
        Query query = session.createQuery(hql);
        List<ArchiveAuditorium> auditoriumList = query.list();
        session.close();
        return auditoriumList;    
    }
    
    //Pobranie archiwalnej listy danych seansow.
    public List<ArchiveShow> pullArchiveShowList() 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from ArchiveShow S";
        Query query = session.createQuery(hql);
        List<ArchiveShow> showList = query.list();
        session.close();
        return showList;    
    }
    
    //Pobranie listy danych sztuk.
    public List<ArchiveReservation> pullArchiveReservationList() 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from ArchiveReservation R";
        Query query = session.createQuery(hql);
        List<ArchiveReservation> reservationList = query.list();
        session.close();
        return reservationList;    
    }
}