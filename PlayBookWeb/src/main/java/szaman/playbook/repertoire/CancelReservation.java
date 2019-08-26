/*
 * Praca Inzynierska - Czesc 3 - Aplikacja Webowa do obslugi sieci Teatrow
 *
 * Serwlet anulowania rezerwacji
 * 
 */

package szaman.playbook.repertoire;

// Laczenie z reszta wlasnych klas //

import java.beans.PropertyVetoException;
import szaman.playbook.utility.HibernateConnector;

// Reszta klas //

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.*;
import javax.servlet.*;
import szaman.playbook.Register;
import szaman.playbook.utility.Utility;

/**
*
* @author Szaman
*/
public class CancelReservation extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    
    Boolean connectionValidation = false;
    
    Utility utility = new Utility();
    HibernateConnector connector = new HibernateConnector();
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        int reservationID = Integer.parseInt(request.getParameter("reservationID"));
        System.out.println("ID:" + reservationID + "X");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        HttpSession session = request.getSession();
        try 
        {
            String errorMessage = ""; //Pusty string, do ktorego beda dodawane poszczegolne errory.
            connectionValidation = connector.testConnection();
            if(connectionValidation == false)
            {
                errorMessage += "Brak polaczenia z baza danych!" + "\n";
            }
            else
            {
                System.out.println("DB Connected!");   
                if(password == null || password.length() == 0)
                {
                    errorMessage += "Proszę podac aktualne haslo!" + "\n";
                }
                else
                {
                    String storedOldPassword = connector.pullUserPassword((String)session.getAttribute("loggedEmail"));
                    String hashedOldPassword = utility.sha1Encryptor(password);
                    if(!storedOldPassword.equals(hashedOldPassword))
                    {
                        errorMessage += "Podano nieprawidłowe aktualne hasło!" + "\n";
                    }
                }
                
                if(confirmPassword == null || confirmPassword.length() == 0)
                {
                    errorMessage += "Proszę ponownie podać aktualne hasło!" + "\n";
                }
                else
                {
                    if(confirmPassword.length() != 0)
                    {
                        if(!password.equals(confirmPassword))
                        {
                            errorMessage += "Podane hasła są niezgodne ze sobą!" + "\n";
                        }
                    }
                }
            }
                
            //Gdy liczba bledow jest rowna zero:
            if (errorMessage.length() == 0) 
            {
                connector.deleteReservation(reservationID);
                request.setAttribute("responseMessage", "Rezerwacja została anulowana!");
                request.getRequestDispatcher("/welcome.jsp").forward(request, response);
            } 

            //Gdy liczba bledow jest rozna od zera:
            else 
            {
                System.out.println(errorMessage);
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/cancelreservation.jsp").forward(request, response);
            }
        } 
        catch (NoSuchAlgorithmException | PropertyVetoException | SQLException  ex) 
        {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}