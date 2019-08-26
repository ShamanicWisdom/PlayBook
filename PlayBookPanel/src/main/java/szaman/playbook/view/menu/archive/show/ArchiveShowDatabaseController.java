/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska archiwalnej bazy danych seansow - ArchiveShowDatabaseController.java.
 * 
 */

package szaman.playbook.view.menu.archive.show;

// Laczenie z reszta wlasnych klas //

import java.beans.PropertyVetoException;
import szaman.playbook.Core;
import szaman.playbook.utility.HibernateConnector;
import szaman.playbook.entity.archive.ArchiveShow;

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

/**
 * 
 *
 * @author Szaman
 */

public class ArchiveShowDatabaseController
{
    @FXML
    private Label auditoriumLabel;
    @FXML
    private Label playLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label startHourLabel;
    @FXML
    private Label lengthLabel;
    @FXML
    private Label slotsLeftLabel;
    
    @FXML
    private TableView<ArchiveShow> showDatabaseTable;
    @FXML
    private TableColumn<ArchiveShow, String> playColumn;
    @FXML
    private TableColumn<ArchiveShow, String> dateColumn;
    @FXML
    private TableColumn<ArchiveShow, String> slotsLeftColumn;
    
    private final ObservableList<ArchiveShow> dataGatherer = FXCollections.observableArrayList();
       
    private Core mainApp;
        
    ArchiveShow show;
    
    HibernateConnector connector = new HibernateConnector();
    
    Boolean connectionValidation = false;
    
    private String email = null; //Przechowuje informacje o zalogowanym koncie (w formie adresu mailowego)
    private String userName = null;
    
    //Inicjalizacja danych do tabeli.
    @FXML
    private void initialize() 
    {
        playColumn.setCellValueFactory(cellData -> cellData.getValue().playNameProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        slotsLeftColumn.setCellValueFactory(cellData -> cellData.getValue().slotsLeftProperty());
        showDatabaseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showArchiveShowDetails(newValue));
    }
    
    public void setMainApp(Core mainApp, String email, String userName) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        this.mainApp = mainApp;
        this.email = email; //Przypisanie adresu mailowego z poprzedniego okna.
        this.userName = userName;   
        dataHarvester();
        showDatabaseTable.setItems(dataGatherer);
    }
        
    /*************************************************************************************************************************************/
    
    public void dataHarvester() throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        List<ArchiveShow> showList = null;
        try
        {
            connectionValidation = connector.testConnection();
            if(connectionValidation == true)
            {
                System.out.println("DB Connected!");
                showList = connector.pullArchiveShowList();
                if(showList.isEmpty())
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
                    for(ArchiveShow s : showList)
                    {
                        dataGatherer.add(s);
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
    
    //Metoda do wyswietlenia wszystkich danych o seansie.
    private void showArchiveShowDetails(ArchiveShow show) 
    {
        if (show != null) 
        {
            auditoriumLabel.setText(show.getTheatreAndAuditoriumData());
            playLabel.setText(show.getPlayName());
            priceLabel.setText(String.format("%.2f", show.getPrice()) + "zł");
            dateLabel.setText(show.getDate().toString());
            startHourLabel.setText(show.getStartHour());
            lengthLabel.setText(Integer.toString(show.getLength()) + "min.");
            slotsLeftLabel.setText(Integer.toString(show.getSlotsLeft()));
        } 
        else 
        {
            auditoriumLabel.setText("");
            playLabel.setText("");
            priceLabel.setText("");
            dateLabel.setText(""); 
            startHourLabel.setText(""); 
            lengthLabel.setText(""); 
            slotsLeftLabel.setText(""); 
        }
    }
    
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws IOException 
    {
        System.out.println("ArchiveShowDatabaseBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showArchiveMenu(email, userName);
    }
}
