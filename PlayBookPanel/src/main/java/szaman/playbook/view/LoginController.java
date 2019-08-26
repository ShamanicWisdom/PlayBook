/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska ekranu logowania aplikacji - LoginController.java.
 * 
 */

package szaman.playbook.view;

// Laczenie z reszta wlasnych klas //

import java.beans.PropertyVetoException;
import szaman.playbook.entity.Account;

// Reszta klas // 

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import szaman.playbook.utility.HibernateConnector;

/**
 *
 * @author Szaman
 */

public class LoginController 
{
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    
    private String userName = null;
    
    private Stage dialogStage;
    private Account account;
    private int accountValidationResult;
    private boolean okClicked = false;
    

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
        
        account.setEmail(null);             //Znullowanie wartosci poczatkowych pozwoli nam uniknac
        account.setPassword(null);          //Bledow zwiazanych z poczatkowym brakiem danych.
        account.setName(null);              //

        emailField.setText(account.getEmail());
        emailField.setPromptText("Adres Email");
        passwordField.setText(account.getPassword());
        passwordField.setPromptText("Hasło");
    }
    
    //Logika, czy guzik OK zostanie klikniety.
    public boolean isOkClicked() 
    {
        return okClicked;
    }
    
    //Skrypt dla guzika OK.
    @FXML
    private void handleOk(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException, NoSuchAlgorithmException 
    {
        if (validator()) 
        {
            account.setEmail(emailField.getText()); //zapis do modelu Account informacji o mailu.
            okClicked = true;
            dialogStage.close();
        }
    }
    
    private boolean validator() throws IOException, SQLException, HibernateException, PropertyVetoException, NoSuchAlgorithmException 
    {
        HibernateConnector connector = new HibernateConnector(); //obiekt HibernateConnector.
        Boolean isConnectionAvailable = false; //Boolean przetrzymujacy informacje, czy jest mozliwe nawiazanie polaczenia z baza - domyslnie ustawiony jako false.
        String errorMessage = ""; //Pusty string, do ktorego beda dodawane poszczegolne errory.
        Boolean emailGiven = true;
        Boolean passwordGiven = true;
        //Test polaczenia z baza danych.
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
    //2. Alert do zle wypelnionego pola emailField.
            //2.1 Puste pole.
            if (emailField.getText() == null || emailField.getText().length() == 0) 
            {
                errorMessage += "Nie podano adresu Email!" + "\n";
                emailGiven = false;
            }

    //3. Alert do zle wypelnionego pola passwordField.
            //3.1 Puste pole.
            if (passwordField.getText() == null || passwordField.getText().length() == 0) 
            {
                errorMessage += "Nie podano hasła!" + "\n"; 
                passwordGiven = false;
            }            
            
            if(passwordGiven == true && emailGiven == true)
            {
                //do publicznego stringa przypisujemy wartosc metody AccountValidation (0 - brak konta, 1 - konto istnieje). 
                accountValidationResult = connector.accountValidation(emailField.getText(), passwordField.getText());
        
    //4. Brak konta o okreslonych parametrach.
                //4.1 Bledne dane.
                if (accountValidationResult == 0)
                {
                    errorMessage += "Podano błędne dane logowania!" + "\n";
                }

                //4.1 Konto nizszego poziomu (nieadministratorskie)
                if (accountValidationResult == 1)
                {
                    errorMessage += "Konto nie posiada uprawnień dostępu do panelu!" + "\n";
                }
            }
        }        
        
    //Gdy liczba bledow jest rowna zero:
        if (errorMessage.length() == 0) 
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(dialogStage);
            account.setName(connector.getUserName(emailField.getText()));
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.setTitle("Sukces!");
            alert.setHeaderText(null);
            alert.setContentText("Logowanie zakończone powodzeniem! ");
            alert.showAndWait();
            return true;
        }
        
    //Gdy liczba bledow jest rozna od zera:
        else 
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Błąd!");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.setHeaderText("Błąd logowania!");
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
