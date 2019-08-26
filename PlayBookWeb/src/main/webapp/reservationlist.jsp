<%@page import="szaman.playbook.entity.Play"%>
<%@page import="szaman.playbook.entity.Auditorium"%>
<%@page import="szaman.playbook.entity.Show"%>
<!--JSP listy rezerwacji-->
<%@page import="szaman.playbook.entity.Account"%>
<%@page import="szaman.playbook.entity.Reservation"%>
<%@page import="java.util.List"%>
<%@page import="szaman.playbook.entity.Theatre"%>
<%@page import="szaman.playbook.utility.HibernateConnector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" session="true" content="text/html; charset=UTF-8">
        <title>System PlayBook</title>
        <link link href="Styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <%
            response.setHeader("Cache-Control","no-cache"); //Wymuszenie na przegladarce, by pobrala nowa kopie strony z serwera.
            response.setHeader("Cache-Control","no-store"); //Wymuszenie na przegladarce, by nie zapisywala w cache zadnej strony.
            response.setDateHeader("Expires", 0); //Wymuszenie odswiezania strony.
            response.setHeader("Pragma","no-cache"); //Kompatybilnosc wstecz dla HTML 1.0
            
            if(session.getAttribute("loggedEmail") == null)
            {
                request.setAttribute("responseMessage", "Proszę się zalogować!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else 
            {
                HibernateConnector connector = new HibernateConnector();
                if(!connector.testConnection())
                {
                    session.invalidate();
                    request.setAttribute("responseMessage", "Brak połączenia z bazą danych! Nastepuję wylogowanie.");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            }
        %>
        
        <br /><br /><br /><br /><br />
        <p><h1>Lista Twoich Rezerwacji</h1></p><br /><br />
    
         
        <table  align="center" class="content-table">
        <tr class="content-table">
            <td class="content-table">Teatr</td>
            <td class="content-table">Sala</td>
            <td class="content-table">Seans</td>
            <td class="content-table">Data</td>
            <td class="content-table">ID Miejsc</td>
            <td class="content-table">Bilety normalne</td>
            <td class="content-table">Bilety ulgowe</td>
            <td class="content-table">Cena</td>
        </tr>
        <%
            HibernateConnector connector = new HibernateConnector();
            Account account = connector.pullSingleAccountViaEmail((String)session.getAttribute("loggedEmail"));
            List<Reservation> reservationList = connector.pullReservationListOfSpecificUser(account);
            request.setAttribute("reservationList", reservationList);
            for(Reservation r : reservationList)
            {
                Show show = connector.pullSingleShow(r.getShow().getShowId());
                Play play = connector.pullSinglePlay(show.getPlay().getPlayId());
                Auditorium auditorium = connector.pullSingleAuditorium(show.getAuditorium().getAuditoriumId());
                Theatre theatre = connector.pullSingleTheatre(auditorium.getTheatre().getTheatreId());
        %>
            
                <tr class="content-table">
                    <td class="content-table"><%= theatre.getName() %></td>
                    <td class="content-table"><%= auditorium.getAuditoriumNumber() %></td>
                    <td class="content-table"><%= play.getName() %></td>
                    <td class="content-table"><%= show.getDate() %>, <%= show.getStartHour() %></td>
                    <td class="content-table"><%= r.getSlotsId() %></td>
                    <td class="content-table"><%= r.getNoNormal() %></td>
                    <td class="content-table"><%= r.getNoHalf() %></td>
                    <td class="content-table"><%= String.format("%.2f", r.getPrice()) + "zł" %></td>
                    <td class="content-table">
                        <form method="post" action="cancelreservation.jsp"> 
                            <input type="hidden" name="reservationID" value="<%= r.getReservationId() %>">
                            <input type="submit" value="Anuluj rezerwację"/>
                        </form>
                    </td>
                </tr>
        <%      
            }
        %>
        
        <form method="post" action="contentmenu.jsp">   
            <table align="center" class="form-table">
                <td class="blank-row"></td>
                <td class="blank-row"></td>
                <td class="blank-row"></td>
                <td><input type="submit" value="Powrót"/></td>
            </table>
        </form>
                
    </body>
</html>