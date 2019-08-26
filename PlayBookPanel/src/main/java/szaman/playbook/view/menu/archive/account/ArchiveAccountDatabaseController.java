/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska archiwalnej bazy danych uzytkownikow - ArchiveAccountDatabaseController.java.
 * 
 */

package szaman.playbook.view.menu.archive.account;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.Core;
import szaman.playbook.entity.archive.ArchiveAccount;
import szaman.playbook.utility.HibernateConnector;

// Reszta klas //

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.HibernateException;
import java.beans.PropertyVetoException;

/**
 * 
 *
 * @author Szaman
 */

public class ArchiveAccountDatabaseController
{
    @FXML
    private Label nameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label userRoleLabel;
    @FXML
    private Label deleteDateLabel;
    
    @FXML
    private TableView<ArchiveAccount> accountDatabaseTable;
    @FXML
    private TableColumn<ArchiveAccount, String> emailColumn;
    @FXML
    private TableColumn<ArchiveAccount, String> nameColumn;
    @FXML
    private TableColumn<ArchiveAccount, String> surnameColumn;
    @FXML
    private TableColumn<ArchiveAccount, String> deleteDateColumn;
    
    private final ObservableList<ArchiveAccount> dataGatherer = FXCollections.observableArrayList();
    
    HibernateConnector connector = new HibernateConnector();
    
    Boolean connectionValidation = false;
       
    private Core mainApp;
    
    ArchiveAccount account;
    
    private String accountRole = null;
    
    private String email = null; //Przechowuje informacje o zalogowanym koncie (w formie adresu mailowego)
    private String userName = null;   
    private String userRole = null;
    private String userEmail = null;
    
    //Inicjalizacja danych do tabeli.
    @FXML
    private void initialize() 
    {
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty()); 
        deleteDateColumn.setCellValueFactory(cellData -> cellData.getValue().deleteDateProperty()); 
        accountDatabaseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showAccountDetails(newValue));

    }
    
    public void setMainApp(Core mainApp, String email, String userName) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        this.mainApp = mainApp;
        
        this.email = email; //Przypisanie adresu mailowego z poprzedniego okna.
        this.userName = userName;
        accountRole = connector.getAccountRole(email);
        dataHarvester();
        accountDatabaseTable.setItems(dataGatherer);
    }
        
    /*************************************************************************************************************************************/

    public void dataHarvester() throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        List<ArchiveAccount> accountList = null;
        try
        {
            connectionValidation = connector.testConnection();
            if(connectionValidation == true)
            {
                System.out.println("DB Connected!");
                accountList = connector.pullArchiveAccountList();
                if(accountList.isEmpty())
                {
                    System.out.println("Brak danych");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setHeaderText("Brak danych!");
                    alert.setContentText("Brak danych w bazie!" );
                    alert.showAndWait();
                }
                else
                {
                    for(ArchiveAccount a : accountList)
                    {
                        dataGatherer.add(a);
                    }
                }
            }
            else
            {
                throw new SQLException();
            }       
        }
        //W przypadku braku polaczenia wyrzuci SQLException (ping) lub HibernateException (pobieracz danych)
        catch (HibernateException | SQLException e) 
        {
            System.out.println("DB Connection could not been established!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Problem!");
            alert.setContentText("Nie można pobrać danych z bazy! \nSpróbuj ponownie później." );
            alert.showAndWait();
        }     
    }
    
    
    /*************************************************************************************************************************************/  
    
    //Metoda do wyswietlenia wszystkich danych o uzytkowniku.
    private void showAccountDetails(ArchiveAccount account) 
    {
        if (account != null) 
        {
            nameLabel.setText(account.getName());
            surnameLabel.setText(account.getSurname());
            emailLabel.setText(account.getEmail());
            deleteDateLabel.setText(account.getDeleteDate());
            if(accountRole.equals("admin"))
            {
                phoneNumberLabel.setText(account.getPhoneNumber().toString());
            }
            else
            {
                if(account.getRole().equals("admin"))
                {     
                    phoneNumberLabel.setText("Zawartość ukryta");
                }
                else
                {
                    if(!(email.equals(account.getEmail())) && account.getRole().equals("operator"))
                    {                
                        phoneNumberLabel.setText("Zawartość ukryta");
                    }
                    else
                    {
                        phoneNumberLabel.setText(account.getPhoneNumber().toString());
                    }
                }
            }
            if(account.getRole().equals("admin"))
            {
                userRoleLabel.setText("(Poziom 2) - " + account.getRole());
            }
            else
            {
                if(account.getRole().equals("operator"))
                {
                    userRoleLabel.setText("(Poziom 1) - " + account.getRole());
                }
                else
                {
                    userRoleLabel.setText("(Poziom 0) - " + account.getRole());
                }
            }    
            userRole = account.getRole();
            userEmail = account.getEmail();
        } 
        else 
        {
            nameLabel.setText("");
            surnameLabel.setText("");
            emailLabel.setText("");
            phoneNumberLabel.setText("");
            userRoleLabel.setText(""); 
            deleteDateLabel.setText("");
        }
    }
        
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws IOException 
    {
        System.out.println("Menu-Back detected!"); //kontrola, czy dziala interakcja.
        mainApp.showArchiveMenu(email, userName);
    }
}
