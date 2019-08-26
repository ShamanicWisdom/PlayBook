<!--JSP pozwalajaca na rejestracje nowego konta-->
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
        
        <br /><br /><br /><br /><br />
        <p><h1>Rejestracja w serwisie PlayBook</h1></p><br /><br />
        <form method="post" action="Register">    
            <table align="center" class="form-table">
                <td>Imię:</td>
                <td><input type="text" name="name" /></td>
                <td>Nazwisko:</td>
                <td><input type="text" name="surname" /></td>
                <td>Numer Telefonu:</td>
                <td><input type="text" name="phoneNumber" /></td>
                <td>Email:</td>
                <td><input type="text" name="email" /></td>
                <td>Hasło:</td>
                <td><input type="password" name="password" /></td>
                <td>Powtórz Hasło:</td>
                <td><input type="password" name="confirmPassword" /></td>
                <td class="blank-row"></td>
                <td><input type="submit" name="button" value="Zarejestruj się!"/></td>            
            </table>
        </form>
    
        <h2><div style="color: #FF0000;">${errorMessage}</div></h2>
        <br />
    
        <form action="index.jsp">
            <table align="center" class="form-table">
                <td><input type="submit" value="Cofnij" /></td>
            </table>
        </form>
        
    </body>
</html>

