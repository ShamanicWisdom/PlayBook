/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska archiwalnej bazy danych sztuk - ArchivePlayDatabaseController.java.
 * 
 */

package szaman.playbook.view.menu.archive.play;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.Core;
import szaman.playbook.utility.HibernateConnector;
import szaman.playbook.entity.archive.ArchivePlay;

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
import javafx.scene.control.TextArea;
import org.hibernate.HibernateException;
import java.beans.PropertyVetoException;

/**
 * 
 *
 * @author Szaman
 */

public class ArchivePlayDatabaseController
{
    @FXML
    private Label nameLabel;
    @FXML
    private Label genreLabel;
    @FXML
    private TextArea playDescriptionTextArea;
    @FXML
    private Label deleteLabel;
    
    @FXML
    private TableView<ArchivePlay> playDatabaseTable;
    @FXML
    private TableColumn<ArchivePlay, String> playNameColumn;
    @FXML
    private TableColumn<ArchivePlay, String> genreColumn;
    @FXML
    private TableColumn<ArchivePlay, String> deleteColumn;
    
    private final ObservableList<ArchivePlay> dataGatherer = FXCollections.observableArrayList();
       
    private Core mainApp;
        
    ArchivePlay play;
    
    HibernateConnector connector = new HibernateConnector();
    
    Boolean connectionValidation = false;
    
    private String email = null; //Przechowuje informacje o zalogowanym koncie (w formie adresu mailowego)
    private String userName = null;
    private String playID = null;
    
    //Inicjalizacja danych do tabeli.
    @FXML
    private void initialize() 
    {
        playNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        deleteColumn.setCellValueFactory(cellData -> cellData.getValue().deleteDateProperty());
        playDatabaseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPlayDetails(newValue));
    }
    
    public void setMainApp(Core mainApp, String email, String userName) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        this.mainApp = mainApp;
        dataHarvester();
        this.email = email; //Przypisanie adresu mailowego z poprzedniego okna.
        this.userName = userName;   
        playDescriptionTextArea.setWrapText(true);
        playDescriptionTextArea.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
        playDatabaseTable.setItems(dataGatherer);
    }
        
    /*************************************************************************************************************************************/
    
    public void dataHarvester() throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        List<ArchivePlay> playList = null;
        try
        {
            connectionValidation = connector.testConnection();
            if(connectionValidation == true)
            {
                System.out.println("DB Connected!");
                playList = connector.pullArchivePlayList();
                if(playList.isEmpty())
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
                    for(ArchivePlay p : playList)
                    {
                        dataGatherer.add(p);
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
    
    //Metoda do wyswietlenia wszystkich danych o sztuce.
    private void showPlayDetails(ArchivePlay play) 
    {
        if (play != null) 
        {
            nameLabel.setText(play.getName());
            playDescriptionTextArea.setText(play.getDescription());
            genreLabel.setText(play.getGenre());
            deleteLabel.setText(play.getDeleteDate());
        } 
        else 
        {
            nameLabel.setText("");
            playDescriptionTextArea.setText("");
            genreLabel.setText("");   
            deleteLabel.setText("");
        }
    }
    
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws IOException 
    {
        System.out.println("ArchivePlayDatabaseBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showArchiveMenu(email, userName);
    }
}
