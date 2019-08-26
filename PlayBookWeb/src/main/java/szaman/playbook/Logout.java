/*
 * Praca Inzynierska - Czesc 3 - Aplikacja Webowa do obslugi sieci Teatrow
 *
 * Serwlet wylogowania
 * 
 */

package szaman.playbook;

// Laczenie z reszta wlasnych klas //



// Reszta klas //

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

/**
*
* @author Szaman
*/
public class Logout extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        //Zlikwidowanie sesji.
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        session.invalidate();
        request.setAttribute("responseMessage", "Wylogowano!");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}