/*
 * Praca Inzynierska - Czesc 3 - Aplikacja Webowa do obslugi sieci Teatrow
 *
 * Serwlet wysylajacy rezerwacje do DB.
 * 
 */

package szaman.playbook.repertoire;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.utility.HibernateConnector;

// Reszta klas //

import java.beans.PropertyVetoException;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import szaman.playbook.entity.Account;
import szaman.playbook.entity.Show;

/**
*
* @author Szaman
*/
public class PushReservationToDatabase extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    
    Boolean connectionValidation = false;
    
    HibernateConnector connector = new HibernateConnector();
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account account = connector.pullSingleAccountViaEmail((String)session.getAttribute("loggedEmail"));
        Show show = connector.pullSingleShow(Integer.parseInt(request.getParameter("showID")));
        int noSlots = Integer.parseInt(request.getParameter("noSlots"));
        String slotsID = (String)request.getParameter("chosenSlots");
        int noNormal = Integer.parseInt(request.getParameter("normalSlotCount"));
        int noHalf = Integer.parseInt(request.getParameter("halfSlotCount"));
        float price = Float.parseFloat(request.getParameter("price"));
        
        try 
        {
            String errorMessage = ""; //Pusty string, do ktorego beda dodawane poszczegolne errory.
            connectionValidation = connector.testConnection();
            if(connectionValidation == false)
            {
                errorMessage += "Brak połączenia z bazą danych!" + "\n";
            }
            //Gdy liczba bledow jest rowna zero:
            if (errorMessage.length() == 0) 
            {
                connector.addReservation(account, show, noSlots, slotsID, noNormal, noHalf, price);
                session.setAttribute("activeReservation", "no");
                request.setAttribute("responseMessage", "Rezerwacja została dokonana!");
                request.getRequestDispatcher("welcome.jsp").forward(request, response);
            } 

            //Gdy liczba bledow jest rozna od zera:
            else 
            {
                System.out.println(errorMessage);
                request.setAttribute("responseMessage", errorMessage);
                request.getRequestDispatcher("welcome.jsp").forward(request, response);
            }
        } 
        catch (PropertyVetoException | SQLException  ex) 
        {
            Logger.getLogger(PushReservationToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
