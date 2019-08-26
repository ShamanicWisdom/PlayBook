/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska resetu hasła uzytkownika - ResetUserPasswordController.java.
 * 
 */

package szaman.playbook.view.menu.account;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.utility.HibernateConnector;
import szaman.playbook.utility.Utility;

// Reszta klas //

import java.beans.PropertyVetoException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * 
 *
 * @author Szaman
 */

public class ResetUserPasswordController
{
    @FXML
    private PasswordField passwordField;
    
    HibernateConnector connector = new HibernateConnector();
    
    private boolean okClicked = false;
    
    private Stage dialogStage;
    
    private String userEmail = null;
    private String adminEmail = null;
    private String adminPassword = null;
    private String confirmPassword = null;
    
    Utility utility = new Utility();

    //Wyswietlenie okna i informacji.
    public void setDialogStage(Stage dialogStage, String userEmail, String adminEmail) throws SQLException, ClassNotFoundException 
    {
        this.dialogStage = dialogStage;
        this.userEmail = userEmail;
        this.adminEmail = adminEmail;
        confirmPassword = passwordField.getText();
    }
    
    //Logika, czy guzik OK zostanie klikniety.
    public boolean isOkClicked() 
    {
        return okClicked;
    }
    
    /*************************************************************************************************************************************/
    
    //Skrypt dla guzika OK.
    @FXML
    private void handleOk(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException, NoSuchAlgorithmException 
    {
        System.out.println("ResetUserPasswordOK detected!"); //kontrola, czy dziala interakcja.
        if (validator()) 
        {
            okClicked = true;
            dialogStage.close();
        }
    }
    
    //Walidator danych.
    private boolean validator() throws SQLException, ClassNotFoundException, PropertyVetoException, NoSuchAlgorithmException 
    {
        String errorMessage = ""; //Pusty string, do ktorego beda dodawane poszczegolne errory.
        Boolean isConnectionAvailable = false; //Boolean przetrzymujacy informacje, czy jest mozliwe nawiazanie polaczenia z baza - domyslnie ustawiony jako false.
        
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
            adminPassword = connector.pullUserPassword(adminEmail);  
            
            //1.2 Brak hasla.
            if (passwordField.getText() == null || passwordField.getText().length() == 0) 
            {
                errorMessage += "Nie podano hasła!" + "\n";
            }
            else
            {
                String encryptedPassword = utility.sha1Encryptor(passwordField.getText());
                
            //2. Alert do zle wypelnionego pola passwordField.                
                if(!(encryptedPassword.equals(adminPassword)))
                {
                    errorMessage += "Podane hasło jest nieprawidłowe!" + "\n";
                }
            }
        }
    //Gdy liczba bledow jest rowna zero:
        if (errorMessage.length() == 0) 
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(dialogStage);
            alert.setTitle("Sukces!");
            alert.setHeaderText(null);
            connector.resetUserPassword(userEmail);
            alert.setContentText("Reset hasła zakończony powodzeniem!");
            alert.showAndWait();
            return true;
        } 
        
    //Gdy liczba bledow jest rozna od zera:
        else 
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(dialogStage);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Błąd resetu hasła!");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
    
    //Skrypt dla guzika Cancel.
    @FXML
    private void handleCancel(ActionEvent event) throws SQLException, ClassNotFoundException 
    {
        System.out.println("ResetUserPasswordCancel detected!"); //kontrola, czy dziala interakcja.
        dialogStage.close();
    }
}