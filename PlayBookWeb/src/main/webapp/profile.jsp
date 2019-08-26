<%@page import="szaman.playbook.utility.HibernateConnector"%>
<!--JSP profilu uzytkownika-->
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
        <p><h1>Twój profil</h1></p><br /><br />

        <table align="center" class="data-table">
            <tr>
                <td class="data-row">
                    Imię:  
                </td>
                <td class="data-row">
                    ${dataUserName}
                </td>
            </tr>
            <tr>
                <td class="data-row">
                    Nazwisko: 
                </td>
                <td class="data-row">
                    ${dataUserSurname}
                </td>
            </tr>
            <tr>
                <td class="data-row">
                    Email: 
                </td>
                <td class="data-row">
                    ${dataUserEmail}
                </td>
            </tr>
            <tr>
                <td class="data-row">
                    Telefon:  
                </td>
                <td class="data-row">
                    ${dataUserPhoneNumber}
                </td>
            </tr>
            <td class="blank-row"></td>
        </table>
                
        <h2><div style="color: #FF0000;">${responseMessage}</div></h2>
        
        
        <form method="post" action="EditProfile">  
            <table align="center" class="form-table">
                <td> <input type="submit" value="Edytuj Profil" /></td>
            </table>
        </form>
        
        <form method="post" action="changepassword.jsp" >  
            <table align="center" class="form-table">
                <td><input type="submit" value="Zmień Hasło" /></td>
            </table>
        </form>
        
        <form method="post" action="deleteaccount.jsp">
            <table align="center" class="form-table">
                <td><input type="submit" value="Usuń Konto"/></td>
            </table>
        </form>
        
        <form method="post" action="welcome.jsp">
            <table align="center" class="form-table">
                <td class="blank-row"></td>
                <td><input type="submit" value="Powrót"/></td>
            </table>
        </form>
        
    </body>
</html>