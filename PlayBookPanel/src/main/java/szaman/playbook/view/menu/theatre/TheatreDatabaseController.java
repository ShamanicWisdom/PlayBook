/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska bazy danych teatrow - TheatreDatabaseController.java.
 * 
 */

package szaman.playbook.view.menu.theatre;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.entity.Theatre;
import szaman.playbook.Core;
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
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import org.hibernate.HibernateException;
import java.beans.PropertyVetoException;
import javafx.scene.control.Button;
import szaman.playbook.entity.Account;
/**
 * 
 *
 * @author Szaman
 */

public class TheatreDatabaseController
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
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    
    @FXML
    private TableView<Theatre> theatreDatabaseTable;
    @FXML
    private TableColumn<Theatre, String> nameColumn;
    @FXML
    private TableColumn<Theatre, String> cityColumn;
    @FXML
    private TableColumn<Theatre, String> phoneColumn;
    
    private final ObservableList<Theatre> dataGatherer = FXCollections.observableArrayList();
    
    HibernateConnector connector = new HibernateConnector();
    
    Boolean connectionValidation = false;
       
    private Core mainApp;
    
    Theatre theatre;
    Theatre chosenTheatre;
    
    private int theatreID;
    
    private String email = null; //Przechowuje informacje o zalogowanym koncie (w formie adresu mailowego)
    private String userName = null;   
    
    //Inicjalizacja danych do tabeli.
    @FXML
    private void initialize() 
    {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty()); 
        theatreDatabaseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTheatreDetails(newValue));

    }
    
    public void setMainApp(Core mainApp, String email, String userName) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        this.mainApp = mainApp;
        this.email = email; //Przypisanie adresu mailowego z poprzedniego okna.
        this.userName = userName;
        Account account = connector.pullSingleAccountViaEmail(email);
        if(account.getRole().equals("operator")) //jesli zalogowane jest konto operatora - chowamy opcje dodawania i edycji kont.
        {
            addButton.setDisable(true);
            addButton.setVisible(false);
            editButton.setDisable(true);
            editButton.setVisible(false);
            deleteButton.setDisable(true);
            deleteButton.setVisible(false);
        }
        dataHarvester();
        theatreDatabaseTable.setItems(dataGatherer);
    }
        
    /*************************************************************************************************************************************/

    public void dataHarvester() throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        List<Theatre> theatreList = null;
        try
        {
            connectionValidation = connector.testConnection();
            if(connectionValidation == true)
            {
                System.out.println("DB Connected!");
                theatreList = connector.pullTheatreList();
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
                    for(Theatre t : theatreList)
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
    private void showTheatreDetails(Theatre theatre) 
    {
        if (theatre != null) 
        {
            nameLabel.setText(theatre.getName());
            streetLabel.setText(theatre.getStreet());
            cityLabel.setText(theatre.getCity());
            voivodeshipLabel.setText(theatre.getVoivodeship());
            phoneLabel.setText(Integer.toString(theatre.getPhoneNumber()));
            theatreID = theatre.getTheatreId();
            chosenTheatre = theatre;
        } 
        else 
        {
            nameLabel.setText("");
            streetLabel.setText("");
            cityLabel.setText("");
            voivodeshipLabel.setText("");
            phoneLabel.setText("");
        }
    }
    
    //Skrypt dla guzika Add.
    @FXML
    private void handleAdd(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("AddTheatre detected!"); //kontrola, czy dziala interakcja.
        Theatre temp = new Theatre();
        boolean okClicked = mainApp.showAddTheatre(temp);
        if (okClicked) 
        {
            System.out.println("AddTheatreOK detected!"); //kontrola, czy dziala interakcja.
            theatreDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
            dataHarvester(); //Pobranie swiezych danych.
            theatreDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
            okClicked = false;
        }
        System.out.println("AddTheatreCancel detected!"); //kontrola, czy dziala interakcja.
    }
    
    //Skrypt dla guzika Auditorium.
    @FXML
    private void handleAuditoriums(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException
    {
        int selectedIndex = theatreDatabaseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) 
        {
            System.out.println("AuditoriumDatabase detected!");
            mainApp.showAuditoriumDatabase(email, userName, chosenTheatre); 
        }
        else 
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Brak teatru do pokazania sal!");
            alert.setContentText("Proszę wybrać teatr z tabeli!");
            alert.showAndWait();
        }
    }
    
    //Skrypt dla guzika Edit.
    @FXML
    private void handleEdit(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException
    {
        boolean okClicked = false;
        int selectedIndex = theatreDatabaseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) 
        {
            System.out.println("TheatreEdit detected!");
            okClicked = mainApp.showEditTheatre(theatreID);    
            theatreDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
            dataHarvester(); //Pobranie swiezych danych.
            theatreDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
            okClicked = false;
        }
        else 
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Brak teatru do edytowania danych!");
            alert.setContentText("Proszę wybrać teatr z tabeli!");
            alert.showAndWait();
        }
    } 
    
    //Skrypt dla guzika Delete.
    @FXML
    private void handleDelete(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("DeleteTheatre detected!"); //kontrola, czy dziala interakcja.
        boolean okClicked = false;
        {
            int selectedIndex = theatreDatabaseTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) 
            {
                System.out.println("Theatre Clicked!");
                okClicked = mainApp.showDeleteTheatre(email, theatreID);
                if(okClicked)
                {
                    System.out.println("DeleteTheatreOK detected!"); //kontrola, czy dziala interakcja.   
                    theatreDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
                    dataHarvester(); //Pobranie swiezych danych.
                    theatreDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
                    okClicked = false;
                }
                else
                {
                    System.out.println("DeleteTheatreCancel detected!"); //kontrola, czy dziala interakcja.   
                }
            } 
            else 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setHeaderText("Brak teatru do usunięcia!");
                alert.setContentText("Proszę wybrać teatr z tabeli!");
                alert.showAndWait();
            }
        }
    }
    
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws IOException 
    {
        System.out.println("MenuBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showMenu(email, userName);
    }
}
