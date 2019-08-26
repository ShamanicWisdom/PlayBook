/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska menu dostepnego po zalogowaniu sie - MenuController.java.
 * 
 */

package szaman.playbook.view.menu;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.Core;

// Reszta klas //

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 * 
 *
 * @author Szaman
 */

public class MenuController
{
    
    @FXML
    private Label welcomeLabel;
    
    private Core mainApp; 
    private String email = null; //Przechowuje informacje o zalogowanym koncie (w formie adresu mailowego)
    private String userName = null;
        
    public void setMainApp(Core mainApp, String email, String userName) 
    {
        this.mainApp = mainApp;
        this.email = email; //Przypisanie adresu mailowego z poprzedniego okna.
        this.userName = userName;
        welcomeLabel.setText("Witaj, " + userName + "!");
    }
    
    //Skrypt dla guzika Accounts.
    @FXML
    private void handleAccounts(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("AccountDatabase detected!"); //kontrola, czy dziala interakcja.
        mainApp.showAccountDatabase(email, userName);
    }
    
    //Skrypt dla guzika Genres.
    @FXML
    private void handlePlays(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("PlayDatabase detected!"); //kontrola, czy dziala interakcja.
        mainApp.showPlayDatabase(email, userName);
    }
    
    //Skrypt dla guzika Genres.
    @FXML
    private void handleTheatres(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("TheatreDatabase detected!"); //kontrola, czy dziala interakcja.
        mainApp.showTheatreDatabase(email, userName);
    }
    
    //Skrypt dla guzika Shows.
    @FXML
    private void handleShows(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("ShowMenu detected!"); //kontrola, czy dziala interakcja.
        mainApp.showShowDatabase(email, userName);
    }
    
    //Skrypt dla guzika Archives.
    @FXML
    private void handleArchive(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("ArchiveMenu detected!"); //kontrola, czy dziala interakcja.
        mainApp.showArchiveMenu(email, userName);
    }
      
    //Skrypt dla guzika Logout.
    @FXML
    private void handleLogout(ActionEvent event) throws IOException, SQLException, PropertyVetoException 
    {
        System.out.println("SU-Logout detected!"); //kontrola, czy dziala interakcja.
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", okButton, cancelButton);
        alert.setTitle("Wylogowanie");
        alert.setHeaderText(null);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
        alert.setContentText("Na pewno?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == okButton)
        {
            System.out.println("AdminLogoutOK detected!"); //kontrola, czy dziala interakcja.
            mainApp.showWelcome();
        } 
        else 
        {
            System.out.println("AdminLogoutCancel detected!"); //kontrola, czy dziala interakcja.
            alert.close();
        } 
    } 
    
    //Skrypt dla guzika Exit.
    @FXML
    public void handleExit()
    {
        System.out.println("Exit detected!"); //kontrola, czy dziala interakcja.
        
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", okButton, cancelButton);
        alert.setTitle("Wyjście");
        alert.setHeaderText(null);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
        alert.setContentText("Na pewno?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == okButton)
        {
            System.out.println("ExitOK detected!"); //kontrola, czy dziala interakcja.
            System.exit(0);
        } 
        else 
        {
            System.out.println("ExitCancel detected!"); //kontrola, czy dziala interakcja.
            alert.close();
        }
    }
    
}
