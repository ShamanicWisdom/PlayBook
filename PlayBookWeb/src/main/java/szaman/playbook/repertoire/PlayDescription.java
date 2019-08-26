/*
 * Praca Inzynierska - Czesc 3 - Aplikacja Webowa do obslugi sieci Teatrow
 *
 * Serwlet danych sztuki
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
import szaman.playbook.entity.Play;
import szaman.playbook.entity.Show;

/**
*
* @author Szaman
*/
public class PlayDescription extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    
    Boolean connectionValidation = false;
    
    HibernateConnector connector = new HibernateConnector();
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        String theatreName = (String)request.getParameter("theatreName");
        int chosenTheatreID = Integer.parseInt(request.getParameter("theatreID"));
        int chosenPlayID = Integer.parseInt(request.getParameter("playID"));
        Play play = connector.pullSinglePlay(chosenPlayID);
        List<Show> showList = connector.pullShowList(chosenTheatreID);
        System.out.println("TheatreName: " + theatreName + " TheatreID: " + chosenTheatreID + " PlayID:" + chosenPlayID + " play: " + play);
        request.setAttribute("chosenPlay", play);
        request.setAttribute("theatreID", chosenTheatreID);
        request.setAttribute("repertoire", showList);
        request.setAttribute("theatreName", theatreName);
        request.getRequestDispatcher("/description.jsp").forward(request, response);
    }  
}