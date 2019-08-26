/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska usuwania rezerwacji - DeleteReservationController.java.
 * 
 */

package szaman.playbook.view.menu.show.reservation;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.utility.Utility;
import szaman.playbook.utility.HibernateConnector;

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

public class DeleteReservationController
{
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    
    HibernateConnector connector = new HibernateConnector();
    Utility utility = new Utility();
    
    private boolean okClicked = false;
    
    private Stage dialogStage;
    
    private int reservationID;
    private String adminEmail = null;
    
    private String adminPassword = null;
    private String confirmAdminPassword = null;
    private String reconfirmAdminPassword = null;
    

    //Wyswietlenie okna i informacji.
    public void setDialogStage(Stage dialogStage, String adminEmail, int reservationID) throws SQLException, ClassNotFoundException 
    {
        this.dialogStage = dialogStage;
        this.reservationID = reservationID;
        this.adminEmail = adminEmail;
        confirmAdminPassword = passwordField.getText();
        reconfirmAdminPassword = confirmPasswordField.getText();
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
        System.out.println("DeleteReservation detected!"); //kontrola, czy dziala interakcja.
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
            if (passwordField.getText() == null || passwordField.getText().length() == 0) 
            {
                errorMessage += "Nie podano aktualnego hasła!" + "\n";
            }

            if (confirmPasswordField.getText() == null || confirmPasswordField.getText().length() == 0) 
            {
                errorMessage += "Nie podano ponownie hasła!" + "\n"; 
            }

            else
            {
                String encryptedPassword = utility.sha1Encryptor(passwordField.getText());
            //2. Alert do zle wypelnionego pola passwordField.
                //2.1 Bledne haslo.
                if(!(encryptedPassword.equals(adminPassword)))
                {
                    errorMessage += "Podane hasło jest nieprawidłowe!" + "\n";
                }

            //3. Alert do zle wypelnionego pola confirmPasswordField.
                //3.1 Bledne haslo.
                if(!(confirmPasswordField.getText().equals(passwordField.getText())))
                {
                    errorMessage += "Podane hasła różnią się od siebie!" + "\n";
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
            connector.deleteReservation(reservationID);
            alert.setContentText("Rezerwacja została usunięta!");
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
            alert.setHeaderText("Błedne hasła!");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
    
    //Skrypt dla guzika Cancel.
    @FXML
    private void handleCancel(ActionEvent event) throws SQLException, ClassNotFoundException 
    {
        System.out.println("ShowDeleteCancel detected!"); //kontrola, czy dziala interakcja.
        dialogStage.close();
    }
}