/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska ekranu powitalnego aplikacji - WelcomeController.java.
 * 
 */

package szaman.playbook.view;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.Core;
import szaman.playbook.utility.Utility;

// Reszta klas //

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import org.hibernate.HibernateException;
import org.postgresql.util.PSQLException;
import szaman.playbook.entity.Account;
import szaman.playbook.utility.HibernateConnector;
import java.beans.PropertyVetoException;
import javafx.scene.control.ButtonBar;

/**
 *
 * @author Szaman
 */

public class WelcomeController
{
    int accountValidation;
    
    @FXML
    private Label connectionInformer;
    @FXML
    private Label versionInformer;
    
    Utility utility = new Utility();
    
    Boolean connectionValidation = false;
    HibernateConnector connector = new HibernateConnector();
    
    //Referencja do glownej aplikacji.
    private Core mainApp;

    //Referencja glownego pliku do samego siebie.
    public void setMainApp(Core mainApp) throws HibernateException, PSQLException, SQLException, PropertyVetoException 
    {
        this.mainApp = mainApp;
        
        showVersionInfo();
        connectionTest();        
    }
    
    public void showVersionInfo()
    {
        versionInformer.setText(utility.pullVersion());
    }
    
    public void connectionTest() throws HibernateException, PSQLException, PropertyVetoException, SQLException
    {
        connectionValidation = connector.testConnection();
        if(connectionValidation == true)
        {
            connectionInformer.setText("Tryb online");
            connectionInformer.setTextFill(Color.web("#006400"));
        }
        else
        {
            connectionInformer.setText("Tryb offline");
            connectionInformer.setTextFill(Color.web("#be0000"));
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Brak połączenia z bazą danych!");
            alert.setContentText
            (
                "Wystąpił błąd połączenia z bazą danych!" + "\n" +
                "Sprawdź połączenie z Internetem."
            );
            alert.showAndWait();
        }
    }  
    
    //Konstruktor WelcomeFXMLController().
    public WelcomeController() 
    {
    }
    
    //Skrypt dla guzika Login.
    @FXML
    private void handleLogin(ActionEvent event) throws IOException, SQLException, ClassNotFoundException 
    {
        System.out.println("Login detected!"); //kontrola, czy dziala interakcja.
        Account temp = new Account();
        boolean okClicked = mainApp.loginAccount(temp);
        if (okClicked) 
        {
            System.out.println("Login-OK detected!"); //kontrola, czy dziala interakcja.
            mainApp.showMenu(temp.getEmail(), temp.getName()); //showMenu inicjalizowane jest w Core.java. getEmail oddaje maila, z ktorego sie logujemy.
        }
        else
        {
            System.out.println("Login-Cancel detected!"); //kontrola, czy dziala interakcja.   
        }
    }
    
    //Skrypt dla guzika About.
    @FXML
    private void handleAbout(ActionEvent event) throws HibernateException, PSQLException, PropertyVetoException, SQLException
    {
        System.out.println("About detected!"); //kontrola, czy dziala interakcja.
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("O mnie");
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
        alert.setHeaderText("O aplikacji:");
        alert.setContentText
        (
            "Panel Administracyjny Systemu PlayBook" + "\n" + 
            utility.pullVersionWithDate() + "\n" + "\n" +
            "Twórca:" + "\n" +
            "Szymon Zawadzki 187852"
        );
        alert.showAndWait();
        System.out.println("About-OK detected!"); //kontrola, czy dziala interakcja.                
    }
    
    //Skrypt dla guzika Exit.
    @FXML
    public void handleExit()
    {
        System.out.println("Exit detected!"); //kontrola, czy dziala interakcja.
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(AlertType.CONFIRMATION, "", okButton, cancelButton);
        alert.setTitle("Wyjście");
        alert.setHeaderText(null);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
        alert.setContentText("Na pewno?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == okButton)
        {
            System.out.println("Exit-OK detected!"); //kontrola, czy dziala interakcja.
            System.exit(0);
        } 
        else 
        {
            System.out.println("Exit-Cancel detected!"); //kontrola, czy dziala interakcja.
            alert.close();
        }
    }
}
