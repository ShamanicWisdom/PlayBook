<%@page import="szaman.playbook.entity.Play"%>
<%@page import="szaman.playbook.entity.Auditorium"%>
<%@page import="java.util.ArrayList"%>
<!--JSP opisu sztuki-->
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
        %>
        
        <br /><br /><br /><br /><br />
        <p><h1>Opis Sztuki</h1></p><br /><br />
        
        <table  align="center" class="description-table">
        <tr class="description-table">
            <td class="description-table">Nazwa</td>
            <td class="description-table">Opis</td>
        </tr>
        <%
            Play play = (Play)request.getAttribute("chosenPlay");
        %>
            <tr class="description-table">
                <td class="description-table"><%= play.getName() %></td>
                <td class="description-table"><%= play.getDescription() %></td>
            </tr>
        </table>

            
        <form method="post" action="ShowRepertoire">   
            <table align="center" class="form-table">
                <td class="blank-row"></td>
                <td class="blank-row"></td>
                <td class="blank-row"></td>
                <input type="hidden" name="theatreID" value="<%= request.getParameter("theatreID") %>">
                <input type="hidden" name="theatreName" value="<%= request.getParameter("theatreName") %>">
                <td><input type="submit" value="Powrót"/></td>
            </table>
        </form>
            
    </body>
</html>