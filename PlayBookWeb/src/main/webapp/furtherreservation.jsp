<!--JSP kontynuacji rezerwacji-->
<%@page import="szaman.playbook.entity.Auditorium"%>
<%@page import="java.util.List"%>
<%@page import="szaman.playbook.entity.Play"%>
<%@page import="szaman.playbook.entity.Show"%>
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
                else
                {
                    if(session.getAttribute("activeReservation") == "no")
                    {
                        request.setAttribute("responseMessage", "Sesja rezerwacji wygasła!");
                        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
                    }
                }
            }
            Auditorium auditorium = (Auditorium)request.getAttribute("chosenAuditorium");
            Play play = (Play)request.getAttribute("chosenPlay");
            Show show = (Show)request.getAttribute("chosenShow");
            List<String> chosenSlotList = (List<String>)request.getAttribute("chosenSlotList");
            String purifiedChosenSlotString = "";
            for(int i = 0; i < chosenSlotList.size(); i++)
            {
                if(i+1 != chosenSlotList.size())
                {
                    purifiedChosenSlotString += chosenSlotList.get(i) + ", ";
                }
                else
                {
                    purifiedChosenSlotString += chosenSlotList.get(i);
                }
            }
        %>
        
        <br /><br /><br /><br /><br />
        <p><h1>Informacje o rezerwacji</h1></p><br /><br />
        <form method="post" action="FinallizeReservation" onsubmit="return checkSlotsCompatibility()"> 
            <table align="center" class="big-data-table">
                <tr>
                    <td class="big-data-row">
                        Dane Seansu:  
                    </td>
                    <td>
                        <%= play.getName() %> (<%= show.getDate() %>, godz: <%= show.getStartHour()%>) w sali <%= auditorium.getAuditoriumNumber() %> w: <%= request.getAttribute("theatreName") %>
                    </td>
                </tr>
                <tr>
                    <td class="big-data-row">
                        Wybrane Miejsca:
                    </td>
                    <td>
                        <%=chosenSlotList.size() %>, ID miejsc: <%=chosenSlotList %>
                    </td>
                </tr>
                <tr>
                    <td class="big-data-row">
                        Bilety Normalne:
                    </td>
                    <td>
                        <input type="number" name="normalSlotCounter" id="normalCounter" value="0" min="0" max="<%= chosenSlotList.size()%>">
                    </td>
                </tr>
                <tr>
                    <td class="big-data-row">
                        Bilety Ulgowe:  
                    </td>
                    <td>
                        <input type="number" name="halfSlotCounter" id="halfCounter" value="0" min="0" max="<%= chosenSlotList.size()%>">
                    </td>
                </tr>
                <td class="blank-row"></td>
            </table>
            <table align="center" class="form-table">
                <td class="blank-row"></td>
                <td><input type="submit" value="Dalej"></td>
            </table>
            <input type="hidden" name="theatreID" value="<%= request.getParameter("theatreID") %>">
            <input type="hidden" name="theatreName" value="<%= request.getParameter("theatreName") %>">    
            <input type="hidden" name="playID" value="<%= play.getPlayId() %>">
            <input type="hidden" name="showID" value="<%= show.getShowId() %>">
            <input type="hidden" name="chosenSlotList" value="<%= purifiedChosenSlotString %>">
            <input type="hidden" id="normalSlotCount" name="normalSlotCount" value=""/>
            <input type="hidden" id="halfSlotCount" name="halfSlotCount" value="" />
        </form>
            
        <form method="post" action="ShowAuditorium">   
            <table align="center" class="form-table">
                <td class="blank-row"></td>
                <td class="blank-row"></td>
                <input type="hidden" name="theatreID" value="<%= request.getParameter("theatreID") %>">
                <input type="hidden" name="theatreName" value="<%= request.getParameter("theatreName") %>">
                <input type="hidden" name="playID" value="<%= play.getPlayId() %>">
                <input type="hidden" name="showID" value="<%= show.getShowId() %>">
                <td><input type="submit" value="Powrót"/></td>
            </table>
        </form>
            
        <script>
            function checkSlotsCompatibility()
            {
                var inputtedNormalSlots = parseInt(document.getElementById("normalCounter").value);
                var inputtedHalfSlots = parseInt(document.getElementById("halfCounter").value);
                var inputtedAllSlots = inputtedNormalSlots + inputtedHalfSlots;
                if(!(inputtedAllSlots == <%= chosenSlotList.size()%>))
                {
                    alert("Nieodpowiednia liczba miejsc do tych, które zostały wybrane!");
                    return false;
                }
                else
                {
                    document.getElementById('normalSlotCount').value = inputtedNormalSlots;
                    document.getElementById('halfSlotCount').value = inputtedHalfSlots;
                    return true;
                }
            }
        </script>
            
        
    </body>
</html>
