/*
 * Praca Inzynierska - Czesc 3 - Aplikacja Webowa do obslugi sieci Teatrow
 *
 * Serwlet profilu uzytkownika
 * 
 */

package szaman.playbook.profile;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.utility.HibernateConnector;

// Reszta klas //

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import szaman.playbook.entity.Account;

/**
*
* @author Szaman
*/
public class Profile extends HttpServlet 
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
        request.setAttribute("dataUserName", account.getName());
        request.setAttribute("dataUserSurname", account.getSurname());
        request.setAttribute("dataUserEmail", account.getEmail());
        request.setAttribute("dataUserPhoneNumber", account.getPhoneNumber());
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }  
}
