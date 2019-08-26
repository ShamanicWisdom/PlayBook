/*
 * Praca Inzynierska - Czesc 3 - Aplikacja Webowa do obslugi sieci Teatrow
 *
 * Serwlet finalizacji.
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
import szaman.playbook.entity.Reservation;
import szaman.playbook.entity.Show;

/**
*
* @author Szaman
*/
public class FurtherReservation extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    
    Boolean connectionValidation = false;
    
    HibernateConnector connector = new HibernateConnector();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        String chosenSeatsAsMatrix = (String)request.getParameter("chosenSeats");
        String theatreName = (String)request.getParameter("theatreName");
        int chosenTheatreID = Integer.parseInt(request.getParameter("theatreID"));
        int playID = Integer.parseInt(request.getParameter("playID"));
        int showID = Integer.parseInt(request.getParameter("showID"));
        Play play = connector.pullSinglePlay(playID);
        Show show = connector.pullSingleShow(showID);
        List<Show> showList = connector.pullShowList(chosenTheatreID);
        Auditorium auditorium = connector.pullSingleAuditorium(show.getAuditorium().getAuditoriumId());
        
        
        //Pull wybranych miejsc na sali.
        List<String> chosenSlotList = new ArrayList<>();
        List<String> catchedChosenSeatsAsMatrix = Arrays.asList(chosenSeatsAsMatrix.split(",\\s*"));
        for(int i = 0; i < catchedChosenSeatsAsMatrix.size(); i++)
        {
            if(!catchedChosenSeatsAsMatrix.get(i).equals("0"))
            {
                chosenSlotList.add(catchedChosenSeatsAsMatrix.get(i));
            }
        }
        System.out.println(chosenSlotList);
        
        //Pull brakujacych miejsc w sali - odwzorowanie wygladu sali.
        List<String> missingSlotList = new ArrayList<>();
        List<String> catchedMissingSlotList = Arrays.asList(auditorium.getMissingSlots().split(",\\s*"));
        for(int i = 0; i < catchedMissingSlotList.size(); i++)
        {
            missingSlotList.add(catchedMissingSlotList.get(i));
        }
        //Pull zarezerwowanych miejsc w sali.
        List<String> reservedSlotList = new ArrayList<>();
        List<Reservation> reservationList = connector.pullReservationList(show);
        for(Reservation r : reservationList)
        {
            List<String> catchedReservedSlotList = Arrays.asList(r.getSlotsId().split(",\\s*"));
            for(int i = 0; i < catchedReservedSlotList.size(); i++)
            {
                reservedSlotList.add(catchedReservedSlotList.get(i));
            }
        }
        
        request.setAttribute("reservedSlotList", reservedSlotList);
        request.setAttribute("missingSlotList", missingSlotList);
        request.setAttribute("chosenSlotList", chosenSlotList);
        request.setAttribute("chosenAuditorium", auditorium);
        request.setAttribute("chosenShow", show);
        request.setAttribute("chosenPlay", play);
        request.setAttribute("theatreID", chosenTheatreID);
        request.setAttribute("repertoire", showList);
        request.setAttribute("theatreName", theatreName);
        request.getRequestDispatcher("/furtherreservation.jsp").forward(request, response);
    }
}