/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska menu dostepnego po zalogowaniu sie - MenuController.java.
 * 
 */

package szaman.playbook.view.menu.archive;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.Core;

// Reszta klas //

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * 
 *
 * @author Szaman
 */

public class ArchiveMenuController
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
    
    //Skrypt dla guzika ArchiveAccount.
    @FXML
    private void handleArchiveAccounts(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("ArchiveAccountDatabase detected!"); //kontrola, czy dziala interakcja.
        mainApp.showArchiveAccountDatabase(email, userName);
    }
    
    //Skrypt dla guzika ArchivePlay.
    @FXML
    private void handleArchivePlays(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("ArchivePlayDatabase detected!"); //kontrola, czy dziala interakcja.
        mainApp.showArchivePlayDatabase(email, userName);
    }
    
    //Skrypt dla guzika ArchiveTheatre.
    @FXML
    private void handleArchiveTheatres(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("ArchiveTheatreDatabase detected!"); //kontrola, czy dziala interakcja.
        mainApp.showArchiveTheatreDatabase(email, userName);
    }
    
    //Skrypt dla guzika ArchiveAuditorium.
    @FXML
    private void handleArchiveAuditoriums(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("ArchiveAuditoriumDatabase detected!"); //kontrola, czy dziala interakcja.
        mainApp.showArchiveAuditoriumDatabase(email, userName);
    }
    
    //Skrypt dla guzika ArchiveShow.
    @FXML
    private void handleArchiveShows(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("ArchiveShowDatabase detected!"); //kontrola, czy dziala interakcja.
        mainApp.showArchiveShowDatabase(email, userName);
    }
    
    //Skrypt dla guzika ArchiveReservation.
    @FXML
    private void handleArchiveReservations(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("ArchiveReservationDatabase detected!"); //kontrola, czy dziala interakcja.
        mainApp.showArchiveReservationDatabase(email, userName);
    }
       
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws IOException 
    {
        System.out.println("ArchiveMenuBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showMenu(email, userName);
    }
}
