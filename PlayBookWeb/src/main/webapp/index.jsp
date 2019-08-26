<!--JSP startowa-->
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
        
        <br /><br /><br /><br /><br />
        <h1>Logowanie do serwisu PlayBook</h1><br /><br />
        <form method="post" action="Login">       
            <table align="center" class="form-table">
                <td>Email : <input type=text name=email /></td>
                <td>Hasło : <input type=password name=password /></td>
                <td class="blank-row"></td>
                <td><input type=submit value=Zaloguj się /></td>
            </table>            
        </form>
        
        <h2><div style="color: #FF0000;">${responseMessage}</div></h2>
        
        <form action="register.jsp">
            <table align="center" class="form-table">
                <td><input type="submit" value="Zarejestruj się" /></td>
            </table> 
        </form>
        
    </body>
</html>
