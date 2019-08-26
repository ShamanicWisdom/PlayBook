/*
 * Praca Inzynierska - Czesc 3 - Aplikacja Webowa do obslugi sieci Teatrow
 *
 * Klasa pomocnicza dla Hibernate - HibernateUtility.java
 * 
 */

package szaman.playbook.utility;

// Laczenie z reszta wlasnych klas //



// Reszta klas //

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
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
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } 
        catch (Throwable ex) 
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() 
    {
        return sessionFactory;
    }
}
