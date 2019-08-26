<!--JSP listy teatrow-->
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
                    request.setAttribute("responseMessage", "Brak połączenia z bazą danych! Następuje wylogowanie.");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            }
        %>
        
        <br /><br /><br /><br /><br />
        <p><h1>Lista Teatrów</h1></p><br /><br />
    
         
        <table  align="center" class="content-table">
        <tr class="content-table">
            <td class="content-table">Nazwa</td>
            <td class="content-table">Ulica</td>
            <td class="content-table">Miasto</td>
            <td class="content-table">Województwo</td>
            <td class="content-table">Telefon</td>
        </tr>
        <%
            HibernateConnector connector = new HibernateConnector();
            List<Theatre> theatreList = connector.pullTheatreList();
            request.setAttribute("listOfTheatres", theatreList);
            for(Theatre t : theatreList)
            {
                System.out.println(t);
        %>
            
                <tr class="content-table">
                    <td class="content-table"><%= t.getName() %></td>
                    <td class="content-table"><%= t.getStreet() %></td>
                    <td class="content-table"><%= t.getCity() %></td>
                    <td class="content-table"><%= t.getVoivodeship() %></td>
                    <td class="content-table"><%= t.getPhoneNumber() %></td>
                    <td class="content-table">
                        <form method="post" action="ShowRepertoire"> 
                            <input type="hidden" name="theatreID" value="<%= t.getTheatreId() %>">
                            <input type="hidden" name="theatreName" value="<%= t.getName() %>">
                            <input type="submit" value="Repertuar"/>
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
