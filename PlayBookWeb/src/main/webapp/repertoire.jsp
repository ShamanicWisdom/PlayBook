<!--JSP repertuaru-->
<%@page import="szaman.playbook.entity.Play"%>
<%@page import="szaman.playbook.entity.Auditorium"%>
<%@page import="java.util.ArrayList"%>
<%@page import="szaman.playbook.entity.Show"%>
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
            
            HibernateConnector connector = new HibernateConnector();
                            
            if(session.getAttribute("loggedEmail") == null)
            {
                request.setAttribute("responseMessage", "Proszę się zalogować!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else 
            {
                if(!connector.testConnection())
                {
                    session.invalidate();
                    request.setAttribute("responseMessage", "Brak połączenia z bazą danych! Następuje wylogowanie.");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            }
            
            session.setAttribute("activeReservation", "no");
        %>
        
        <br /><br /><br /><br /><br />
        <p><h1><%= request.getParameter("theatreName") %> - Repertuar:</h1></p><br /><br />
    
        <table  align="center" class="content-table">
        <tr class="content-table">
            <td class="content-table">Sztuka</td>
            <td class="content-table">Sala</td>
            <td class="content-table">Data</td>
            <td class="content-table">Cena</td>
            <td class="content-table">Długość</td>
            <td class="content-table">Wolne Miejsca</td>
        </tr>
        <%
            List<Show> showList = (ArrayList<Show>)request.getAttribute("repertoire");
            for(Show s : showList)
            {
                System.out.println(s);
                //Pull danych sali.
                Auditorium auditorium = connector.pullSingleAuditorium(s.getAuditorium().getAuditoriumId());
                //Pull danych sztuki.
                Play play = connector.pullSinglePlay(s.getPlay().getPlayId());
        %>
                <tr class="content-table">
                    <td class="content-table"><%= play.getName() %></td>
                    <td class="content-table"><%= auditorium.getAuditoriumNumber() %> (Typ <%= auditorium.getAuditoriumType() %>)</td>
                    <td class="content-table"><%= s.getDate() %> Godzina: <%= s.getStartHour() %></td>
                    <td class="content-table"><%= String.format("%.2f", s.getPrice()) + "zł" %></td>
                    <td class="content-table"><%= s.getLength() %>min.</td>
                    <td class="content-table"><%= s.getSlotsLeft() %></td>
                    <td class="content-table">
                        <form method="post" action="PlayDescription"> 
                            <input type="hidden" name="theatreID" value="<%= request.getParameter("theatreID") %>">
                            <input type="hidden" name="theatreName" value="<%= request.getParameter("theatreName") %>">
                            <input type="hidden" name="playID" value="<%= s.getPlay().getPlayId() %>">
                            <input type="submit" value="Dane Sztuki"/>
                        </form>
                    </td>
                    <td class="content-table">
                        <form method="post" action="ShowAuditorium"> 
                            <input type="hidden" name="theatreID" value="<%= request.getParameter("theatreID") %>">
                            <input type="hidden" name="theatreName" value="<%= request.getParameter("theatreName") %>">
                            <input type="hidden" name="playID" value="<%= s.getPlay().getPlayId() %>">
                            <input type="hidden" name="showID" value="<%= s.getShowId() %>">
                            <input type="submit" value="Rezerwuj miejsca"/>
                        </form>
                    </td>
                </tr>
        <%
            }
        %>
        </table>
        
        <form method="post" action="theatrelist.jsp">   
            <table align="center" class="form-table">
                <td class="blank-row"></td>
                <td class="blank-row"></td>
                <td class="blank-row"></td>
                <td><input type="submit" value="Powrót"/></td>
            </table>
        </form>
        
    </body>
</html>