/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa pomocnicza dla Hibernate - HibernateUtility.java.
 * 
 */

package szaman.playbook.utility;

// Laczenie z reszta wlasnych klas //

// Reszta klas //

import org.hibernate.HibernateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Szaman
 */
public class HibernateUtility 
{

    private static final SessionFactory sessionFactory;
    
    static 
    {
        try 
        {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } 
        catch (HibernateException e) 
        {
            // Log the exception. 
            System.err.println("Zainicjowanie SessionFactory zakonczone niepowodzeniem. " + e);
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static SessionFactory getSessionFactory() 
    {
        return sessionFactory;
    }
}
