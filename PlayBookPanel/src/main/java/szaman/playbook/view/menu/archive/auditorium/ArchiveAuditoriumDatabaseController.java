/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska archiwalnej bazy danych sal - ArchiveAuditoriumDatabaseController.java.
 * 
 */

package szaman.playbook.view.menu.archive.auditorium;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.Core;
import szaman.playbook.utility.HibernateConnector;
import szaman.playbook.entity.archive.ArchiveAuditorium;

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
import javafx.scene.control.TextArea;

/**
 * 
 *
 * @author Szaman
 */

public class ArchiveAuditoriumDatabaseController
{
    @FXML
    private Label theatreIdLabel;
    @FXML
    private Label auditoriumNumberLabel;
    @FXML
    private Label auditoriumTypeLabel;
    @FXML
    private Label noSlotsLabel;
    @FXML
    private TextArea missingSlotsTextArea;
    @FXML
    private Label deleteLabel;
    
    @FXML
    private TableView<ArchiveAuditorium> auditoriumDatabaseTable;
    @FXML
    private TableColumn<ArchiveAuditorium, String> auditoriumNumberColumn;
    @FXML
    private TableColumn<ArchiveAuditorium, String> auditoriumTypeColumn;
    @FXML
    private TableColumn<ArchiveAuditorium, String> deleteColumn;
    
    private final ObservableList<ArchiveAuditorium> dataGatherer = FXCollections.observableArrayList();
    
    HibernateConnector connector = new HibernateConnector();
    
    Boolean connectionValidation = false;
       
    private Core mainApp;
    
    ArchiveAuditorium auditorium;
        
    private String getMissingSlots;
    private String getAuditoriumInfo;
    
    private String email = null; //Przechowuje informacje o zalogowanym koncie (w formie adresu mailowego)
    private String userName = null;   
    
    //Inicjalizacja danych do tabeli.
    @FXML
    private void initialize() 
    {
        auditoriumNumberColumn.setCellValueFactory(cellData -> cellData.getValue().auditoriumNumberProperty());
        auditoriumTypeColumn.setCellValueFactory(cellData -> cellData.getValue().auditoriumTypeProperty());
        deleteColumn.setCellValueFactory(cellData -> cellData.getValue().deleteDateProperty()); 
        auditoriumDatabaseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showAuditoriumDetails(newValue));

    }
    
    public void setMainApp(Core mainApp, String email, String userName) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        this.mainApp = mainApp;
        dataHarvester();
        this.email = email; //Przypisanie adresu mailowego z poprzedniego okna.
        this.userName = userName;
        auditoriumDatabaseTable.setItems(dataGatherer);
        missingSlotsTextArea.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
    }
        
    /*************************************************************************************************************************************/

    public void dataHarvester() throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        List<ArchiveAuditorium> auditoriumList = null;
        try
        {
            connectionValidation = connector.testConnection();
            if(connectionValidation == true)
            {
                System.out.println("DB Connected!");
                auditoriumList = connector.pullArchiveAuditoriumList();
                if(auditoriumList.isEmpty())
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
                    for(ArchiveAuditorium a : auditoriumList)
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
            alert.setContentText("Nie można pobrać danych z bazy! \nSpróbuj ponownie później.");
            alert.showAndWait();
        }     
    }
    
    
    /*************************************************************************************************************************************/  
    
    //Metoda do wyswietlenia wszystkich danych o teatrze.
    private void showAuditoriumDetails(ArchiveAuditorium auditorium) 
    {
        if (auditorium != null) 
        {
            theatreIdLabel.setText(Integer.toString(auditorium.getTheatreId()));
            auditoriumNumberLabel.setText(Integer.toString(auditorium.getAuditoriumNumber()));
            auditoriumTypeLabel.setText(auditorium.getAuditoriumType());
            noSlotsLabel.setText(Integer.toString(auditorium.getNoSlots()));
            missingSlotsTextArea.setText(auditorium.getMissingSlots());
            deleteLabel.setText(auditorium.getDeleteDate());
            getMissingSlots = missingSlotsTextArea.getText();
            getAuditoriumInfo = auditoriumNumberLabel.getText();
        } 
        else 
        {
            theatreIdLabel.setText("");
            auditoriumNumberLabel.setText("");
            auditoriumTypeLabel.setText("");
            noSlotsLabel.setText("");
            missingSlotsTextArea.setText("");
            deleteLabel.setText("");
            getMissingSlots = new String();
            getAuditoriumInfo = new String();
        }
    }
    
    //Skrypt dla guzika Show.
    @FXML
    private void handleShow(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException
    {
        
        int selectedIndex = auditoriumDatabaseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) 
        {
            System.out.println("ShowArchiveAuditorium detected!");
            String auditoriumChoice = auditoriumTypeLabel.getText();
            try
            {
                if (auditoriumChoice.equals("Small")) 
                {
                    System.out.println("ShowSmallArchiveAuditorium detected!");
                    mainApp.showShowSmallArchiveAuditorium(email, userName, getMissingSlots, getAuditoriumInfo);
                }
                if (auditoriumChoice.equals("Medium")) 
                {
                    System.out.println("ShowMediumArchiveAuditorium detected!");
                    mainApp.showShowMediumArchiveAuditorium(email, userName, getMissingSlots, getAuditoriumInfo);
                }
                if (auditoriumChoice.equals("Big")) 
                {
                    System.out.println("ShowBigArchiveAuditorium detected!");
                    mainApp.showShowBigArchiveAuditorium(email, userName, getMissingSlots, getAuditoriumInfo);
                }
            }
            catch(NullPointerException e)
            {
                System.out.println("ShowArchiveAuditoriumCancel detected!");
            }
        }
        else 
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Brak sali do pokazania!");
            alert.setContentText("Proszę wybrać salę z tabeli!");
            alert.showAndWait();
        }
    }
        
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws IOException 
    {
        System.out.println("ArchiveAuditoriumDatabaseBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showArchiveMenu(email, userName);
    }
}
