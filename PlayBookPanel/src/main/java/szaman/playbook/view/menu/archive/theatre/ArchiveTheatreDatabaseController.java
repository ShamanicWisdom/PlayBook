/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska archiwalnej bazy danych teatrow - ArchiveTheatreDatabaseController.java.
 * 
 */

package szaman.playbook.view.menu.archive.theatre;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.Core;
import szaman.playbook.utility.HibernateConnector;
import szaman.playbook.entity.archive.ArchiveTheatre;

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

public class ArchiveTheatreDatabaseController
{
    @FXML
    private Label nameLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label voivodeshipLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label deleteLabel;
    
    @FXML
    private TableView<ArchiveTheatre> theatreDatabaseTable;
    @FXML
    private TableColumn<ArchiveTheatre, String> nameColumn;
    @FXML
    private TableColumn<ArchiveTheatre, String> cityColumn;
    @FXML
    private TableColumn<ArchiveTheatre, String> deleteColumn;
    
    private final ObservableList<ArchiveTheatre> dataGatherer = FXCollections.observableArrayList();
    
    HibernateConnector connector = new HibernateConnector();
    
    Boolean connectionValidation = false;
       
    private Core mainApp;
    
    ArchiveTheatre theatre;
    
    private String email = null; //Przechowuje informacje o zalogowanym koncie (w formie adresu mailowego)
    private String userName = null;   
    
    //Inicjalizacja danych do tabeli.
    @FXML
    private void initialize() 
    {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        deleteColumn.setCellValueFactory(cellData -> cellData.getValue().deleteDateProperty()); 
        theatreDatabaseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTheatreDetails(newValue));

    }
    
    public void setMainApp(Core mainApp, String email, String userName) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        this.mainApp = mainApp;
        dataHarvester();
        this.email = email; //Przypisanie adresu mailowego z poprzedniego okna.
        this.userName = userName;
        theatreDatabaseTable.setItems(dataGatherer);
    }
        
    /*************************************************************************************************************************************/

    public void dataHarvester() throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        List<ArchiveTheatre> theatreList = null;
        try
        {
            connectionValidation = connector.testConnection();
            if(connectionValidation == true)
            {
                System.out.println("DB Connected!");
                theatreList = connector.pullArchiveTheatreList();
                if(theatreList.isEmpty())
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
                    for(ArchiveTheatre t : theatreList)
                    {
                        dataGatherer.add(t);
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
            alert.setContentText("Nie można pobrać danych z bazy! \nSpróbuj ponownie później.");
            alert.showAndWait();
        }     
    }
    
    
    /*************************************************************************************************************************************/  
    
    //Metoda do wyswietlenia wszystkich danych o teatrze.
    private void showTheatreDetails(ArchiveTheatre theatre) 
    {
        if (theatre != null) 
        {
            nameLabel.setText(theatre.getName());
            streetLabel.setText(theatre.getStreet());
            cityLabel.setText(theatre.getCity());
            voivodeshipLabel.setText(theatre.getVoivodeship());
            phoneLabel.setText(Integer.toString(theatre.getPhoneNumber()));
            deleteLabel.setText(theatre.getDeleteDate());
        } 
        else 
        {
            nameLabel.setText("");
            streetLabel.setText("");
            cityLabel.setText("");
            voivodeshipLabel.setText("");
            phoneLabel.setText("");
            deleteLabel.setText("");
        }
    }
        
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws IOException 
    {
        System.out.println("ArchiveTheatreDatabaseBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showArchiveMenu(email, userName);
    }
}
