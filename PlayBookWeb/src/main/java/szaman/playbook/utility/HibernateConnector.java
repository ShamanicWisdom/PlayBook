/*
 * Praca Inzynierska - Czesc 3 - Aplikacja Webowa do obslugi sieci Teatrow
 *
 * Klasa przechowujaca wszystkie zapytania Hibernate - HibernateConnector.java
 * 
 */

package szaman.playbook.utility;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.entity.Account;

// Reszta klas //

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import szaman.playbook.entity.Auditorium;
import szaman.playbook.entity.Play;
import szaman.playbook.entity.Reservation;
import szaman.playbook.entity.Show;
import szaman.playbook.entity.Theatre;

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
    
    //Sprawdzenie, czy mail figuruje w bazie.
    public boolean isEmailExistsOrIsTheSame(String newEmail, String oldEmail)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "FROM Account A WHERE A.email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("email", newEmail);
        List<Account> accountList = query.list();
        System.out.println(accountList.size());
        String accEmail = accountList.get(0).getEmail();
        if(accountList.isEmpty())
        {
            session.close();
            return false;
        }
        else
        {
            session.close();
            if(accEmail.equals(oldEmail))
            {
                return false;
            }
            else
            {
                return true;
            }
        }        
    }
    
    //Dodanie konta.
    public void addAccount(String name, String surname, String email, String password, String phoneNumber) throws NoSuchAlgorithmException
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
                             .setString("role", "user")
                             .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Pobranie imienia uzytkownika.
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
    
    //Zmiana hasla usera.
    public void changeUserPassword(String newPassword, String userEmail) throws NoSuchAlgorithmException
    {
        String encryptedPassword = utility.sha1Encryptor(newPassword);
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Account SET  password = :password WHERE email = :email";
        int updatedEntity = session.createSQLQuery(hql)
                            .setString("password", encryptedPassword)
                            .setString("email", userEmail)
                            .executeUpdate();
        transaction.commit();
        session.close();
    }
    
    //Aktualizacja danych usera.
    public void updateAccount(String name, String surname, String email, String phoneNumber, String oldEmail)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Account SET name = :name, surname = :surname, email = :email, phone_number = :phoneNumber "
                   + "WHERE email = :oldEmail";
        int updatedEntity = session.createSQLQuery(hql)
                            .setString("name", name)
                            .setString("surname", surname)
                            .setString("email", email)
                            .setInteger("phoneNumber", Integer.parseInt(phoneNumber))
                            .setString("oldEmail", oldEmail)
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
    
    /********************************************************************************************************************************************/
    /*                                                                    SHOW                                                                  */
    /********************************************************************************************************************************************/
    
    //Pobranie listy danych sztuk.
    public List<Show> pullShowList(int theatreID) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql1 = "from Theatre T where theatre_id = :theatre_id";
        Query query1 = session.createQuery(hql1);
        query1.setParameter("theatre_id", theatreID);
        List<Theatre> theatreList = query1.list();
        Theatre theatre = theatreList.get(0);
        System.out.println("THEATRE LIST RESULT: " + theatreList);
        String hql2 = "from Auditorium as A WHERE A.theatre.theatreId = :theatre_id ORDER BY auditorium_number ASC";
        
        Query query2 = session.createQuery(hql2);
        query2.setParameter("theatre_id", theatre.getTheatreId());
        List<Auditorium> auditoriumList = query2.list();
        System.out.println("AUDITORIUMS FROM THEATRE: " + theatreList);
        List<Show> showList = new ArrayList<>();
        for(Auditorium a : auditoriumList)
        {
            String hql3 = "from Show S WHERE S.auditorium.auditoriumId = :auditorium_id";
            Query query3 = session.createQuery(hql3);
            query3.setParameter("auditorium_id", a.getAuditoriumId());
            List<Show> singleAuditoriumShowList = query3.list();
            for(Show s : singleAuditoriumShowList)
            {
                showList.add(s);
            }
        }
        System.out.println("ALL SHOWS: " + showList);
        session.close();
        return showList;    
    }
    
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
    
    //Pobranie danych seansu.
    public Show pullSingleShow(int showID) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from Show WHERE show_id = :show_id";
        Query query = session.createQuery(hql);
        query.setParameter("show_id", showID);
        List<Show> showList = query.list();
        session.close();
        return showList.get(0);    
    }
    
    /********************************************************************************************************************************************/
    /*                                                                 AUDITORIUM                                                               */
    /********************************************************************************************************************************************/
    
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
    
    /********************************************************************************************************************************************/
    /*                                                                    PLAY                                                                  */
    /********************************************************************************************************************************************/
    
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
    
    //Pobranie listy rezerwacji danego usera.
    public List<Reservation> pullReservationListOfSpecificUser(Account account) 
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        String hql = "from Reservation as A WHERE A.account.accountId = :account_id";
        Query query = session.createQuery(hql);
        query.setParameter("account_id", account.getAccountId());
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
}