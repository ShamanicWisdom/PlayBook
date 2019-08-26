/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska dodawania konta - AddAccountController.java.
 * 
 */

package szaman.playbook.view.menu.account;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.entity.Account;
import szaman.playbook.utility.HibernateConnector;

// Reszta klas // 

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import java.beans.PropertyVetoException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Szaman
 */

public class AddAccountController
{
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private ChoiceBox accountLevelBox;
    
    private Stage dialogStage;
    
    private Account account;
    
    private boolean okClicked = false;
    
    HibernateConnector connector = new HibernateConnector();
    
    List<String> accountLevelList = new ArrayList<String>();
    
    String chosenLevel = null;
    
    //Inicjalizacja.
    @FXML
    private void initialize() 
    {
    }

    //Przypisanie okna dialogowego.
    public void setDialogStage(Stage dialogStage) 
    {
        this.dialogStage = dialogStage;
    }

    //Zbieranie danych odnosnie nowego konta.
    public void setAccount(Account account) 
    {
        this.account = account;
        nameField.setPromptText("Imię");
        surnameField.setPromptText("Nazwisko");
        emailField.setPromptText("Adres email");
        passwordField.setPromptText("Hasło (min. 6 znaków!)");
        phoneNumberField.setPromptText("Numer Telefonu");
        accountLevelList.addAll(Arrays.asList("admin", "operator", "user"));
        
        accountLevelBox.setTooltip(new Tooltip("Wybierz poziom konta"));
        accountLevelBox.getSelectionModel().clearSelection(); //Czyszczenie wyboru Choice Boxa
        accountLevelBox.getItems().clear(); //Czyszczenie Choice Boxa.

        accountLevelBox.getItems().addAll(accountLevelList); //Wypelnienie Choice Boxa danymi z bazy danych.
        accountLevelBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() //Nasluch wybranej rzeczy z Choice Boxa
        { 
            public void changed(ObservableValue observableValue, Number voivodeshipValue, Number newLevelValue) //observableValue - wybrana rzecz, value - stary indeks, newValue - nowy indeks (przy ewentualnej zmianie wyboru)
            {
                if(newLevelValue.equals(-1))
                {
                    chosenLevel = null;
                    Number fixer = 1;
                    newLevelValue = newLevelValue.intValue() + fixer.intValue();
                    observableValue.removeListener(this); //Usuniecie nasluchu powoduje zaprzestanie obserwacji listy - pozwala to nam na ewentualna zmiane nie zawiesi uzycia listy.
                }
                chosenLevel = accountLevelList.get(newLevelValue.intValue());
                System.out.println("Wybrany poziom: " + chosenLevel); 
            }
        }); 
        
    }

    //Logika, czy guzik OK zostanie klikniety.
    public boolean isOkClicked() 
    {
        return okClicked;
    }

    //Skrypt dla guzika OK.
    @FXML
    private void handleOk() throws SQLException, ClassNotFoundException, PropertyVetoException, NoSuchAlgorithmException 
    {
        if (validator()) 
        {
            account.setName(nameField.getText());
            account.setSurname(surnameField.getText());
            account.setEmail(emailField.getText());
            account.setPassword(passwordField.getText());
            account.setRole(chosenLevel);
            account.setPhoneNumber(Integer.parseInt(phoneNumberField.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }

    //Walidator danych.
    private boolean validator() throws SQLException, ClassNotFoundException, PropertyVetoException, NoSuchAlgorithmException 
    {
        String errorMessage = ""; //Pusty string, do ktorego beda dodawane poszczegolne errory.
        Boolean isConnectionAvailable = false; //Boolean przetrzymujacy informacje, czy jest mozliwe nawiazanie polaczenia z baza - domyslnie ustawiony jako false.
        
        //Testujemy polaczenie z baza danych.
        isConnectionAvailable = connector.testConnection();
        
    //Alerty: 
    //1. Alert do braku lacznosci z baza.
        //1.1 Brak polaczenia z baza danych spowodowany brakiem dostepu/brakiem Internetu.
        if (isConnectionAvailable == false)
        {
            errorMessage += "Brak połączenia z bazą danych!" + "\n";
        }
        else
        {              
            if (nameField.getText() == null || nameField.getText().length() == 0) 
            {
                errorMessage += "Nie podano imienia!" + "\n";
            }
            
            if (surnameField.getText() == null || surnameField.getText().length() == 0) 
            {
                errorMessage += "Nie podano nazwiska!" + "\n";
            }

            if (emailField.getText() == null || emailField.getText().length() == 0) 
            {
                errorMessage += "Nie podano adresu email!" + "\n"; 
            }
            
            if (passwordField.getText() == null || passwordField.getText().length() == 0) 
            {
                errorMessage += "Nie podano hasła!" + "\n"; 
            }
            
            if (chosenLevel == null || chosenLevel.length() == 0) 
            {
                errorMessage += "Nie podano poziomu konta!" + "\n"; 
            }
            
            if (phoneNumberField.getText() == null || phoneNumberField.getText().length() == 0) 
            {
                errorMessage += "Nie podano numeru telefonu!" + "\n"; 
            }
            
            if (phoneNumberField.getText().length() != 9) 
            {
                errorMessage += "Proszę podać poprawny numeru telefonu! (9 cyfr)" + "\n"; 
            }
            
            //2. Inne alerty.
        
            Pattern numbersOnly = Pattern.compile("^[0-9]*$"); //Wzorzec, w ktorym nie wystepuja litery ani znaki specjalne.
            Pattern charsOnly = Pattern.compile("\\D+"); //Wzorzec, w ktorym nie wystepuja znaki specjalne.
            Pattern initialChar = Pattern.compile("^[A-Z].*"); //Wzorzec, w ktorym pierwsza litera jest duza.  
            Pattern validatedEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE); //Wzorzec adresu email.

            Matcher matchName = charsOnly.matcher(nameField.getText()); //Matcher pobiera nazwe miasta i porownuje , czy zgadza sie ze wzorcem.
            Boolean nameWithoutSpecials = matchName.matches(); //Logika dla matchera od imienia.

            //2.1 Imie ze znakami specjalnymi.
            if (!(nameWithoutSpecials))
            {
                errorMessage += "Imię nie może zawierać znaków specjalnych ani cyfr!" + "\n";
            }

            Matcher matchNameInitial = initialChar.matcher(nameField.getText()); //Matcher pobiera imie i porownuje , czy zgadza sie ze wzorcem.
            Boolean nameWithBigInitial = matchNameInitial.matches(); //Logika dla matchera od imienia.

            //2.2 Imie nie zaczyna sie na duza litere.
            if (!(nameWithBigInitial))
            {
                errorMessage += "Imię musi zaczynać się z dużej litery!" + "\n";
            }

            Matcher matchSurname = charsOnly.matcher(surnameField.getText()); //Matcher pobiera nazwe miasta i porownuje , czy zgadza sie ze wzorcem.
            Boolean surnameWithoutSpecials = matchSurname.matches(); //Logika dla matchera od nazwiska.

            //2.3 Nazwisko ze znakami specjalnymi.
            if (!(surnameWithoutSpecials))
            {
                errorMessage += "Nazwisko nie może zawierać znaków specjalnych ani cyfr!" + "\n";
            }

            Matcher matchSurnameInitial = initialChar.matcher(surnameField.getText()); //Matcher pobiera imie i porownuje , czy zgadza sie ze wzorcem.
            Boolean surnameWithBigInitial = matchSurnameInitial.matches(); //Logika dla matchera od nazwiska.

            //2.4 Nazwisko nie zaczyna sie na duza litere.
            if (!(surnameWithBigInitial))
            {
                errorMessage += "Imię musi zaczynać się z dużej litery!" + "\n";
            }

            Matcher matchEmail = validatedEmail.matcher(emailField.getText()); //Matcher pobiera podanego maila i porownuje, czy zgadza sie ze wzorcem.
            Boolean emailValidated = matchEmail.matches(); //Logika dla matchera od adresu email. 

            //2.5 Niepoprawny adres mailowy.
            if (!(emailValidated))
            {
                errorMessage += "Niepoprawny adres mailowy!" + "\n";
            }
            
            //2.6 Email w uzyciu.
            if(connector.isEmailExists(emailField.getText()))
            {
                errorMessage += "Email jest już w użyciu!" + "\n";
            }
            
            //2.7 Za krotkie haslo.
            if (passwordField.getText().length() < 6) 
            {
                errorMessage += "Proszę podać minimum 6 znakowe hasło!" + "\n"; 
            }

            Matcher matchPhoneNumber = numbersOnly.matcher(phoneNumberField.getText()); //Matcher pobiera numer telefonu i porownuje , czy zgadza sie ze wzorcem.
            Boolean phoneNumberWithoutChars = matchPhoneNumber.matches(); //Logika dla matchera od numeru telefonu.

            //2.8 Numer telefonu ze zawartoscia inna niz cyfry.
            if (!(phoneNumberWithoutChars))
            {
                errorMessage += "Numer telefonu musi składać się tylko z cyfr!" + "\n";
            }
        }
        
    //Gdy liczba bledow jest rowna zero:
        if (errorMessage.length() == 0) 
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(dialogStage);
            alert.setTitle("Sukces!");
            alert.setHeaderText(null);
            connector.addAccount(nameField.getText(), surnameField.getText(), emailField.getText(), passwordField.getText(), phoneNumberField.getText(), chosenLevel);
            alert.setContentText("Dodawanie konta zakończone powodzeniem!");
            alert.showAndWait();
            return true;
        } 
        
    //Gdy liczba bledow jest rozna od zera:
        else 
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(dialogStage);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Błąd dodawania konta!");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
    
    //Skrypt dla guzika Cancel.
    @FXML
    private void handleCancel() 
    {
        dialogStage.close();
    }
}
