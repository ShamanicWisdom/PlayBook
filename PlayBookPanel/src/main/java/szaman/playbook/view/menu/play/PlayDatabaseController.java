/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska bazy danych sztuk - PlayDatabaseController.java.
 * 
 */

package szaman.playbook.view.menu.play;

// Laczenie z reszta wlasnych klas //

import java.beans.PropertyVetoException;
import szaman.playbook.Core;
import szaman.playbook.entity.Play;
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
import javafx.scene.control.TextArea;
import org.hibernate.HibernateException;

/**
 * 
 *
 * @author Szaman
 */

public class PlayDatabaseController
{
    @FXML
    private Label nameLabel;
    @FXML
    private Label genreLabel;
    @FXML
    private TextArea playDescriptionTextArea;
    
    @FXML
    private TableView<Play> playDatabaseTable;
    @FXML
    private TableColumn<Play, String> playNameColumn;
    @FXML
    private TableColumn<Play, String> genreColumn;
    
    private final ObservableList<Play> dataGatherer = FXCollections.observableArrayList();
       
    private Core mainApp;
        
    Play play;
    
    HibernateConnector connector = new HibernateConnector();
    
    Boolean connectionValidation = false;
    
    private String email = null; //Przechowuje informacje o zalogowanym koncie (w formie adresu mailowego)
    private String userName = null;
    private Integer playID;
    
    //Inicjalizacja danych do tabeli.
    @FXML
    private void initialize() 
    {
        playNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
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
        List<Play> playList = null;
        try
        {
            connectionValidation = connector.testConnection();
            if(connectionValidation == true)
            {
                System.out.println("DB Connected!");
                playList = connector.pullPlayList();
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
                    for(Play p : playList)
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
    private void showPlayDetails(Play play) 
    {
        if (play != null) 
        {
            nameLabel.setText(play.getName());
            playDescriptionTextArea.setText(play.getDescription());
            genreLabel.setText(play.getGenre());
            playID = play.getPlayId();
        } 
        else 
        {
            nameLabel.setText("");
            playDescriptionTextArea.setText("");
            genreLabel.setText("");      
        }
    }
    
    //Skrypt dla guzika Add.
    @FXML
    private void handleAdd(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("AddPlay detected!"); //kontrola, czy dziala interakcja.
        Play temp = new Play();
        boolean okClicked = mainApp.showAddPlay(temp);
        if (okClicked) 
        {
            System.out.println("AddPlayOK detected!"); //kontrola, czy dziala interakcja.
            playDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
            dataHarvester(); //Pobranie swiezych danych.
            playDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
            okClicked = false;
        }
        System.out.println("AddPlayCancel detected!"); //kontrola, czy dziala interakcja.
    }
    
    //Skrypt dla guzika Edit.
    @FXML
    private void handleEdit(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException
    {
        boolean okClicked = false;
        int selectedIndex = playDatabaseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) 
        {
            System.out.println("PlayEdit detected!");
            okClicked = mainApp.showEditPlay(playID);    
            playDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
            dataHarvester(); //Pobranie swiezych danych.
            playDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
            okClicked = false;
        }
        else 
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Brak sztuki do edytowania danych!");
            alert.setContentText("Proszę wybrać sztukę z tabeli!");
            alert.showAndWait();
        }
    } 
    
    //Skrypt dla guzika Delete.
    @FXML
    private void handleDelete(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("PlayDelete detected!"); //kontrola, czy dziala interakcja.
        boolean okClicked = false;
        {
            int selectedIndex = playDatabaseTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) 
            {
                System.out.println("Play Clicked!");
                okClicked = mainApp.showDeletePlay(email, playID);
                if(okClicked)
                {
                    System.out.println("PlayDeleteOK detected!"); //kontrola, czy dziala interakcja.   
                    playDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
                    dataHarvester(); //Pobranie swiezych danych.
                    playDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
                    okClicked = false;
                }
                else
                {
                    System.out.println("PlayDeleteCancel detected!"); //kontrola, czy dziala interakcja.   
                }
            } 
            else 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setHeaderText("Brak sztuki do usunięcia!");
                alert.setContentText("Proszę wybrać sztukę z tabeli!");
                alert.showAndWait();
            }
        }
    }
    
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws IOException 
    {
        System.out.println("PlayDatabaseBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showMenu(email, userName);
    }
}
