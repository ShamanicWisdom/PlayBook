/*
 * Praca Inzynierska - Czesc 3 - Aplikacja Webowa do obslugi sieci Teatrow
 *
 * Serwlet repertuaru.
 * 
 */

package szaman.playbook.repertoire;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.utility.HibernateConnector;

// Reszta klas //

import java.io.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.*;
import szaman.playbook.entity.Show;

/**
*
* @author Szaman
*/
public class ShowRepertoire extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    
    Boolean connectionValidation = false;
    
    HibernateConnector connector = new HibernateConnector();
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        String theatreName = (String)request.getParameter("theatreName");
        int chosenTheatreIndex = Integer.parseInt(request.getParameter("theatreID"));
        List<Show> showList = connector.pullShowList(chosenTheatreIndex);
        for(Show s : showList)
        {
            System.out.println(s);
        }
        request.setAttribute("theatreID", chosenTheatreIndex);
        request.setAttribute("repertoire", showList);
        request.setAttribute("theatreName", theatreName);
        request.getRequestDispatcher("/repertoire.jsp").forward(request, response);
    }  
}