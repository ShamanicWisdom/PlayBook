<!--JSP sali-->
<%@page import="java.util.ArrayList"%>
<%@page import="szaman.playbook.entity.Play"%>
<%@page import="szaman.playbook.entity.Show"%>
<%@page import="szaman.playbook.entity.Auditorium"%>
<%@page import="java.util.List"%>
<%@page import="szaman.playbook.utility.HibernateConnector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" session="true" content="text/html; charset=UTF-8">
        <title>System PlayBook</title>
        <link link href="Styles.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
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
                    request.setAttribute("responseMessage", "Brak połączenia z bazą danych! Następuje wylogowanie.");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            }
            
            session.setAttribute("activeReservation", "yes");
            
            String[] charArray = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
            List<String> missingSlotList = (List<String>)request.getAttribute("missingSlotList");
            List<String> reservedSlotList = (List<String>)request.getAttribute("reservedSlotList");
            Auditorium auditorium = (Auditorium)request.getAttribute("chosenAuditorium");
            Show show = (Show)request.getAttribute("chosenShow");
            Play play = (Play)request.getAttribute("chosenPlay");
            int theatreID = (Integer)request.getAttribute("chosenTheatreID");
            String theatreName = (String)request.getAttribute("theatreName");
            List<Show> showList = (ArrayList<Show>)request.getAttribute("repertoire");

            List<String> chosenSlotList = new ArrayList<>();
            
        %>
        
        <p><h1>Rezerwacja miejsc na <%= play.getName() %> (<%= show.getDate() %>, godz: <%= show.getStartHour()%>) w sali <%= auditorium.getAuditoriumNumber() %> w: <%= theatreName %></h1></p>
        
        <h2>Zaznacz wybrane miejsca na zwizualizowanej sali (max 6 na jedną rezerwację!)</h2>
        <h2><div style="color: #FF0000;">${responseMessage}</div></h2>  
        
        <form action="FurtherReservation" method="post" onsubmit="return checkSeatsCount()">
            <table align="center" cellspacing="0" cellpadding="0">
                <%
                    int columnNumber = 10;
                    int rowNumber = 0;
                    if(auditorium.getAuditoriumType().equals("Big"))
                    {
                        rowNumber = 10;
                    }
                    else
                    {
                        if(auditorium.getAuditoriumType().equals("Medium"))
                        {
                            rowNumber = 8;
                        }
                        else
                        {
                            rowNumber = 5;
                        }
                    }
                    String seatName;
                    for(int i = 0; i < rowNumber; i++)
                    {
                        %>
                        <tr>
                            <%
                                for(int j = 0; j < columnNumber; j++)
                                {
                                    seatName = charArray[i] + (j+1);
                                    if(!missingSlotList.contains(seatName))
                                    {
                                        if(!reservedSlotList.contains(seatName))
                                        {
                                            %>
                                            <td>
                                                <input type="button" class="SeatFree" id="<%=((10*i)+j)%>" value="<%=seatName%>"/>
                                            </td>
                                            <%
                                        }
                                        else
                                        {
                                            %>
                                            <td>
                                                <input type="button" disabled="true" class="SeatReserved" id="<%=((10*i)+j)%>" value="<%=seatName%>"/>
                                            </td>
                                            <%
                                        }
                                    }
                                    else
                                    {
                                        %>
                                        <td>
                                            <input type="button" disabled="true" class="SeatMissing" id="<%=((10*i)+j)%>" value="<%=seatName%>"/>
                                        </td>
                                        <%
                                    }
                                }
                            %>
                        </tr>
                        <%
                    }
                    %>
            </table>
                <input type="hidden" name="theatreID" value="<%= request.getParameter("theatreID") %>">
                <input type="hidden" name="theatreName" value="<%= request.getParameter("theatreName") %>">
                <input type="hidden" name="playID" value="<%= play.getPlayId() %>">
                <input type="hidden" name="showID" value="<%= show.getShowId() %>">
                <input type="hidden" id="seatsCount" name="seatsCount" value="" />
                <input type="hidden" id="chosenSeats" name="chosenSeats" value="" />
                <table align="center" class="form-table">
                    <td class="blank-row"></td>
                    <td><input type="submit" value="Zarezerwuj"></td>
                </table>
        </form>
            
        <form method="post" action="ShowRepertoire">   
            <table align="center" class="form-table">
                <td class="blank-row"></td>
                <td class="blank-row"></td>
                <input type="hidden" name="theatreID" value="<%= request.getParameter("theatreID") %>">
                <input type="hidden" name="theatreName" value="<%= request.getParameter("theatreName") %>">
                <td><input type="submit" value="Powrót"/></td>
            </table>
        </form>

        <script>
            var seatsCount = 0;
            var chosenSeats = [];
            for (var i = 0; i < 100; i++) 
            {
                chosenSeats.push(0);
            }
            $('#chosenSeats').val(chosenSeats);
            $('input[type="button"]').on('click', function(evt) 
            {
                var seat = $(this);
                var index = +seat.attr('id');
                if (chosenSeats[index] === 0) 
                {
                    if (seatsCount >= 6)
                    {
                        alert("Przekroczono dozwoloną liczbę rezerwowanych miejsc!");
                    }
                    else
                    {
                        chosenSeats[index] = seat.attr('value');
                        seat.addClass('SeatChosen');
                        seatsCount++;
                    }
                } 
                else 
                {
                    chosenSeats[index] = 0;
                    seat.removeClass('SeatChosen');
                    seatsCount--;
                }
                $('#chosenSeats').val(chosenSeats);
                $('#seatsCount').val(seatsCount);
            });
        </script>
            
        <script>
            function checkSeatsCount()
            {
                var chosenSeats = seatsCount;
                if (chosenSeats === 0) 
                {
                    alert("Proszę wybrać minimum jedno miejsce!");
                    return false;
                } 
            }
        </script>
    </body>
</html>