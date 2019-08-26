/*
 * Praca Inzynierska - Czesc 3 - Aplikacja Webowa do obslugi sieci Teatrow
 *
 * Serwlet potwierdzenia wszystkich danych.
 * 
 */

package szaman.playbook.repertoire;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.utility.HibernateConnector;

// Reszta klas //

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.*;
import szaman.playbook.entity.Auditorium;
import szaman.playbook.entity.Play;
import szaman.playbook.entity.Show;

/**
*
* @author Szaman
*/
public class FinallizeReservation extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    
    Boolean connectionValidation = false;
    
    HibernateConnector connector = new HibernateConnector();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        
        String purifiedChosenSlotString = (String)request.getParameter("chosenSlotList");
        String theatreName = (String)request.getParameter("theatreName");
        int chosenTheatreID = Integer.parseInt(request.getParameter("theatreID"));
        int playID = Integer.parseInt(request.getParameter("playID"));
        int showID = Integer.parseInt(request.getParameter("showID"));
        int normalSlotCount = Integer.parseInt(request.getParameter("normalSlotCount"));
        int halfSlotCount = Integer.parseInt(request.getParameter("halfSlotCount"));
        
        //Pull wybranych miejsc na sali.
        List<String> chosenSlotList = new ArrayList<>();
        List<String> catchedChosenSeatsAsMatrix = Arrays.asList(purifiedChosenSlotString.split(",\\s*"));
        for(int i = 0; i < catchedChosenSeatsAsMatrix.size(); i++)
        {
            if(!catchedChosenSeatsAsMatrix.get(i).equals("0"))
            {
                chosenSlotList.add(catchedChosenSeatsAsMatrix.get(i));
            }
        }
        
        System.out.println("Normals: " + normalSlotCount + " Halfs: " + halfSlotCount);
                
        Play play = connector.pullSinglePlay(playID);
        Show show = connector.pullSingleShow(showID);
        List<Show> showList = connector.pullShowList(chosenTheatreID);
        
        double price = (normalSlotCount * show.getPrice()) + (halfSlotCount * 0.5 * show.getPrice());
        
        Auditorium auditorium = connector.pullSingleAuditorium(show.getAuditorium().getAuditoriumId());
        
        request.setAttribute("chosenSlotsString", purifiedChosenSlotString);
        request.setAttribute("price", price);
        request.setAttribute("normalSlotCount", normalSlotCount);
        request.setAttribute("halfSlotCount", halfSlotCount);
        request.setAttribute("chosenSlotList", chosenSlotList);
        request.setAttribute("chosenAuditorium", auditorium);
        request.setAttribute("chosenShow", show);
        request.setAttribute("chosenPlay", play);
        request.setAttribute("theatreID", chosenTheatreID);
        request.setAttribute("repertoire", showList);
        request.setAttribute("theatreName", theatreName);
        request.getRequestDispatcher("/finallizereservation.jsp").forward(request, response);
    }
}