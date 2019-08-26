<!--JSP pozwalajaca anulowac rezerwacje-->
<%@page import="szaman.playbook.utility.HibernateConnector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            int reservationID = Integer.parseInt(request.getParameter("reservationID"));
        %>
        
        <br /><br /><br /><br /><br />
        <p><h1>Anulowanie rezerwacji</h1></p><br /><br />
    
        <h2>Aby anulować rezerwację, wpisz swoje hasło</h2>
        
        <form method="post" action="CancelReservation">    
            <table align="center" class="form-table">
                <input type="hidden" name="reservationID" value="<%= reservationID %>"/>
                <td>Hasło:</td>
                <td><input type="password" name="password" /></td>
                <td>Powtórz Hasło:</td>
                <td><input type="password" name="confirmPassword" /></td>
                <td class="blank-row"></td>
                <td><input type="submit" name="button" value="Zatwierdź"/></td>            
            </table>
        </form>
        
        <h2><div style="color: #FF0000;">${errorMessage}</div></h2>   
                
        <form method="post" action="reservationlist.jsp">   
            <table align="center" class="form-table">
                <td><input type="submit" value="Cofnij"/></td>
            </table>
        </form>
        
    </body>
</html>