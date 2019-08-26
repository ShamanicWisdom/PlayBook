/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska zmiany własnego hasła - SelfChangePasswordController.java.
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

public class SelfChangePasswordController
{
    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmNewPasswordField;
    
    HibernateConnector connector = new HibernateConnector();
    
    Utility utility = new Utility();
    
    private boolean okClicked = false;
    
    private Stage dialogStage;
    
    private String email = null;
    
    private String oldAdminPassword = null;
    private String confirmOldAdminPassword = null;
    private String newAdminPassword = null;
    private String confirmNewPassword = null;

    //Wyswietlenie okna i informacji.
    public void setDialogStage(Stage dialogStage, String email) throws SQLException, ClassNotFoundException 
    {
        this.dialogStage = dialogStage;
        this.email = email;
        confirmOldAdminPassword = oldPasswordField.getText();
        newAdminPassword = newPasswordField.getText();
        confirmNewPassword = confirmNewPasswordField.getText();
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
        System.out.println("SelfChangePasswordOK detected!"); //kontrola, czy dziala interakcja.
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
            oldAdminPassword = connector.pullUserPassword(email);
            if (oldPasswordField.getText() == null || oldPasswordField.getText().length() == 0) 
            {
                errorMessage += "Nie podano aktualnego hasła!" + "\n";
            }

            if (newPasswordField.getText() == null || newPasswordField.getText().length() == 0) 
            {
                errorMessage += "Nie podano nowego hasła!" + "\n"; 
            }

            if (confirmNewPasswordField.getText() == null || confirmNewPasswordField.getText().length() == 0) 
            {
                errorMessage += "Nie podano ponownie nowego hasła!" + "\n";
            }

            else
            {
                String encryptedPassword = utility.sha1Encryptor(oldPasswordField.getText());
                
            //2. Alert do zle wypelnionego pola oldPasswordField.
                if(!(encryptedPassword.equals(oldAdminPassword)))
                {
                    errorMessage += "Podane aktualne hasło jest nieprawidłowe!" + "\n";
                }

            //3. Alert do zle wypelnionego pola newPasswordField.
                //3.1 Haslo za krotkie.
                if (newPasswordField.getText().length() < 6) 
                {
                    errorMessage += "Nowe hasło jest za krótkie!" + "\n";
                }

            //4. Alert do zle wypelnionego pola confirmNewPasswordField.
                if(!(confirmNewPasswordField.getText().equals(newPasswordField.getText())))
                {
                    errorMessage += "Podane nowe hasła są niezgodne ze sobą!" + "\n";
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
            connector.selfChangePassword(email, newPasswordField.getText());
            alert.setContentText("Aktualizacja hasła zakończona powodzeniem!");
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
            alert.setHeaderText("Błąd aktualizacji hasła!");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
    
    //Skrypt dla guzika Cancel.
    @FXML
    private void handleCancel(ActionEvent event) throws SQLException, ClassNotFoundException 
    {
        System.out.println("SelfChangePasswordCancel detected!"); //kontrola, czy dziala interakcja.
        dialogStage.close();
    }
}