/*
 * Praca Inzynierska - Czesc 3 - Aplikacja Webowa do obslugi sieci Teatrow
 *
 * Serwlet logowania
 * 
 */

package szaman.playbook;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.utility.HibernateConnector;

// Reszta klas //

import java.beans.PropertyVetoException;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import szaman.playbook.entity.Account;

/**
*
* @author Szaman
*/
public class Login extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    
    Boolean connectionValidation = false;
    
    HibernateConnector connector = new HibernateConnector();
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        int accountValidation = 0;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try 
        {
            connectionValidation = connector.testConnection();
            if(connectionValidation == true)
            {
                System.out.println("DB Connected!");
                accountValidation = connector.accountValidation(email, password);
                if(accountValidation == 0)
                {
                    request.setAttribute("responseMessage", "Niepoprawne dane...");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                else
                {
                    if(accountValidation == 1)
                    {
                        //Stworzenie sesji, przypisanie do niej maila i imienia usera do dalszej manipulacji danymi.
                        HttpSession session = request.getSession();
                        Account account = connector.pullSingleAccountViaEmail(email);
                        session.setAttribute("activeReservation", "no");
                        session.setAttribute("loggedEmail", email);
                        session.setAttribute("loggedUserName", account.getName());
                        response.sendRedirect("welcome.jsp");
                    }
                    else
                    {
                        request.setAttribute("responseMessage", "PlayBook Web jest dostępny tylko dla standardowych użytkowników.");
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    }
                }
                
            }
            else
            {
                out.println("DB connection could not been established!");
            }
        }
        catch (PropertyVetoException | NoSuchAlgorithmException | SQLException ex) 
        {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}