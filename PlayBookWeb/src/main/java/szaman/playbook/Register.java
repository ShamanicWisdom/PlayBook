/*
 * Praca Inzynierska - Czesc 3 - Aplikacja Webowa do obslugi sieci Teatrow
 *
 * Serwlet rejestracyjny
 * 
 */

package szaman.playbook;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.utility.HibernateConnector;

// Reszta klas //

import java.beans.PropertyVetoException;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
*
* @author Szaman
*/
public class Register extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    
    Boolean connectionValidation = false;
    
    HibernateConnector connector = new HibernateConnector();
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        try 
        {
            String errorMessage = ""; //Pusty string, do ktorego beda dodawane poszczegolne errory.
            connectionValidation = connector.testConnection();
            if(connectionValidation == false)
            {
                errorMessage += "Brak połączenia z bazą danych!" + "\n";
            }
            else
            {
                System.out.println("DB Connected!");
                Pattern charsOnly = Pattern.compile("\\D+"); //Wzorzec, w ktorym nie wystepuja znaki specjalne.
                Pattern initialChar = Pattern.compile("^[A-Z].*"); //Wzorzec, w ktorym pierwsza litera jest duza.  
                
                if(name == null || name.length() == 0)
                {
                    errorMessage += "Proszę podać imię!" + "\n";
                }
                else
                {
                    Matcher matchName = charsOnly.matcher(name); //Matcher pobiera nazwe miasta i porownuje , czy zgadza sie ze wzorcem.
                    Boolean nameWithoutSpecials = matchName.matches(); //Logika dla matchera od imienia.

                    //Imie ze znakami specjalnymi.
                    if (!(nameWithoutSpecials))
                    {
                        errorMessage += "Imię nie może zawierać znaków specjalnych ani cyfr!" + "\n";
                    }

                    Matcher matchNameInitial = initialChar.matcher(name); //Matcher pobiera imie i porownuje , czy zgadza sie ze wzorcem.
                    Boolean nameWithBigInitial = matchNameInitial.matches(); //Logika dla matchera od imienia.

                    //Imie nie zaczyna sie na duza litere.
                    if (!(nameWithBigInitial))
                    {
                        errorMessage += "Imię musi zaczynać się z dużej litery!" + "\n";
                    }
                }
                
                if(surname == null || surname.length() == 0)
                {
                    errorMessage += "Proszę podać nazwisko!" + "\n";
                }
                else
                {
                    Matcher matchSurname = charsOnly.matcher(surname); //Matcher pobiera nazwe miasta i porownuje , czy zgadza sie ze wzorcem.
                    Boolean surnameWithoutSpecials = matchSurname.matches(); //Logika dla matchera od nazwiska.

                    //Nazwisko ze znakami specjalnymi.
                    if (!(surnameWithoutSpecials))
                    {
                        errorMessage += "Nazwisko nie może zawierać znaków specjalnych ani cyfr!" + "\n";
                    }

                    Matcher matchSurnameInitial = initialChar.matcher(surname); //Matcher pobiera imie i porownuje , czy zgadza sie ze wzorcem.
                    Boolean surnameWithBigInitial = matchSurnameInitial.matches(); //Logika dla matchera od nazwiska.

                    //Nazwisko nie zaczyna sie na duza litere.
                    if (!(surnameWithBigInitial))
                    {
                        errorMessage += "Imię musi zaczynać się z dużej litery!" + "\n";
                    }
                }
                
                if(phoneNumber == null || phoneNumber.length() == 0)
                {
                    errorMessage += "Proszę podać numer telefonu!" + "\n";
                }
                else
                {
                    Pattern numbersOnly = Pattern.compile("^[0-9]*$"); //Wzorzec, w ktorym nie wystepuja litery ani znaki specjalne.
                    Matcher matchPhoneNumber = numbersOnly.matcher(phoneNumber); //Matcher pobiera numer telefonu i porownuje , czy zgadza sie ze wzorcem.
                    Boolean phoneNumberWithoutChars = matchPhoneNumber.matches(); //Logika dla matchera od numeru telefonu.

                    //Numer telefonu ze znakami nienumerycznymi
                    if (!(phoneNumberWithoutChars))
                    {
                        errorMessage += "Numer telefonu musi składać się tylko z cyfr!" + "\n";
                    }
                }
                
                if(email == null || email.length() == 0)
                {
                    errorMessage += "Proszę podać adres mailowy!" + "\n";
                }
                else
                {
                    Pattern validatedEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE); //Wzorzec adresu email.
                    Matcher matchEmail = validatedEmail.matcher(email); //Matcher pobiera podanego maila i porownuje, czy zgadza sie ze wzorcem.
                    Boolean emailValidated = matchEmail.matches(); //Logika dla matchera od adresu email. 
                    
                    if (!(emailValidated))
                    {
                        errorMessage += "Niepoprawny adres mailowy!" + "\n";
                    }

                    if(connector.isEmailExists(email))
                    {
                        errorMessage += "Email jest już w użyciu!" + "\n";
                    }
                }
                
                if(password == null || password.length() == 0)
                {
                    errorMessage += "Proszę podać hasło!" + "\n";
                }
                else
                {
                    if (password.length() < 6) 
                    {
                        errorMessage += "Proszę podać minimum 6 znakowe hasło!" + "\n"; 
                    }
                }
                
                if(confirmPassword == null || confirmPassword.length() == 0)
                {
                    errorMessage += "Proszę ponownie podać hasło!" + "\n";
                }
                else
                {
                    if(password.length() != 0)
                    {
                        if(!password.equals(confirmPassword))
                        {
                            errorMessage += "Podane hasła są niezgodne ze sobą!" + "\n";
                        }
                    }
                }
            }
                
            //Gdy liczba bledow jest rowna zero:
            if (errorMessage.length() == 0) 
            {
                connector.addAccount(name, surname, email, password, phoneNumber);
                request.setAttribute("responseMessage", "Konto zostalo utworzone! Możesz się teraz zalogowac.");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } 

            //Gdy liczba bledow jest rozna od zera:
            else 
            {
                System.out.println(errorMessage);
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
        } 
        catch (NoSuchAlgorithmException | PropertyVetoException | SQLException  ex) 
        {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}