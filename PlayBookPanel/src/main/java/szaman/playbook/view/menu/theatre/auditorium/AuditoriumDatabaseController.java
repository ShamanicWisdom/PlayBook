/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska bazy danych sal - AuditoriumDatabaseController.java.
 * 
 */

package szaman.playbook.view.menu.theatre.auditorium;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.Core;
import szaman.playbook.utility.HibernateConnector;
import szaman.playbook.entity.Auditorium;
import szaman.playbook.entity.Theatre;

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
import javafx.scene.control.TextArea;
import org.hibernate.HibernateException;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import javafx.scene.control.Button;
import szaman.playbook.entity.Account;

/**
 * 
 *
 * @author Szaman
 */

public class AuditoriumDatabaseController
{
    @FXML
    private Label theatreLabel;
    @FXML
    private Label auditoriumNumberLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label slotsLabel;
    @FXML
    private TextArea missingSlotsTextArea;
    
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    
    @FXML
    private TableView<Auditorium> auditoriumDatabaseTable;
    @FXML
    private TableColumn<Auditorium, String> auditoriumNumberColumn;
    @FXML
    private TableColumn<Auditorium, String> slotsColumn;
    @FXML
    private TableColumn<Auditorium, String> auditoriumTypeColumn;
    
    private final ObservableList<Auditorium> dataGatherer = FXCollections.observableArrayList();
    
    HibernateConnector connector = new HibernateConnector();
    
    Boolean connectionValidation = false;
       
    private Core mainApp;
    
    private int auditoriumID;
    
    Auditorium auditorium;
    
    Theatre chosenTheatre;
    
    private String getMissingSlots;
    private String getAuditoriumInfo;
    
    List<Integer> auditoriumNumbers = new ArrayList<>();
    
    private String email = null; //Przechowuje informacje o zalogowanym koncie (w formie adresu mailowego)
    private String userName = null;   
    
    //Inicjalizacja danych do tabeli.
    @FXML
    private void initialize() 
    {
        auditoriumNumberColumn.setCellValueFactory(cellData -> cellData.getValue().auditoriumNumberProperty());
        slotsColumn.setCellValueFactory(cellData -> cellData.getValue().noSlotsProperty());
        auditoriumTypeColumn.setCellValueFactory(cellData -> cellData.getValue().auditoriumTypeProperty()); 
        auditoriumDatabaseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showAuditoriumDetails(newValue)); 
    }
    
    public void setMainApp(Core mainApp, String email, String userName, Theatre chosenTheatre) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        this.mainApp = mainApp;
        this.chosenTheatre = chosenTheatre;
        this.email = email; //Przypisanie adresu mailowego z poprzedniego okna.
        this.userName = userName;
        Account account = connector.pullSingleAccountViaEmail(email);
        missingSlotsTextArea.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
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
        auditoriumDatabaseTable.setItems(dataGatherer);
    }
        
    /*************************************************************************************************************************************/

    public void dataHarvester() throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        List<Auditorium> auditoriumList = null;
        try
        {
            connectionValidation = connector.testConnection();
            if(connectionValidation == true)
            {
                System.out.println("DB Connected!");
                auditoriumList = connector.pullAuditoriumList(chosenTheatre);
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
                    auditoriumNumbers = new ArrayList<>();
                    for(Auditorium a : auditoriumList)
                    {
                        auditoriumNumbers.add(a.getAuditoriumNumber());
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
    
    //Metoda do wyswietlenia wszystkich danych o sali.
    private void showAuditoriumDetails(Auditorium auditorium) 
    {
        if (auditorium != null) 
        {
            theatreLabel.setText(auditorium.pullTheatreData(chosenTheatre));
            auditoriumNumberLabel.setText(Integer.toString(auditorium.getAuditoriumNumber()));
            typeLabel.setText(auditorium.getAuditoriumType());
            slotsLabel.setText(Integer.toString(auditorium.getNoSlots()));
            missingSlotsTextArea.setText(auditorium.getMissingSlots());
            getMissingSlots = missingSlotsTextArea.getText();
            getAuditoriumInfo = auditoriumNumberLabel.getText();
            auditoriumID = auditorium.getAuditoriumId();
        } 
        else 
        {
            theatreLabel.setText("");
            auditoriumNumberLabel.setText("");
            typeLabel.setText("");
            slotsLabel.setText("");
            missingSlotsTextArea.setText("");
            getMissingSlots = new String();
            getAuditoriumInfo = new String();
        }
    }
    
    //Skrypt dla guzika Add.
    @FXML
    private void handleAdd(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("AddAuditorium detected!");
        String auditoriumChoice = "";
        try
        {
            auditoriumChoice = mainApp.showAuditoriumChooser();
            if(auditoriumChoice.equals("Cancel"))
            {
                System.out.println("AddAuditoriumCancel detected!");
            }
            else
            {
                if (auditoriumChoice.equals("Small")) 
                {
                    System.out.println("AddSmallAuditorium detected!");
                    mainApp.showAddSmallAuditorium(email, userName, chosenTheatre, auditoriumNumbers);
                }
                if (auditoriumChoice.equals("Medium")) 
                {
                    System.out.println("AddMediumAuditorium detected!");
                    mainApp.showAddMediumAuditorium(email, userName, chosenTheatre, auditoriumNumbers);
                }
                if (auditoriumChoice.equals("Big")) 
                {
                    System.out.println("AddBigAuditorium detected!");
                    mainApp.showAddBigAuditorium(email, userName, chosenTheatre, auditoriumNumbers);
                }
            }
        }
        catch(NullPointerException e)
        {
            System.out.println("AddAuditoriumCancel detected!");
        }
    }
    
    //Skrypt dla guzika Show.
    @FXML
    private void handleShow(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException
    {
        
        int selectedIndex = auditoriumDatabaseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) 
        {
            System.out.println("ShowAuditorium detected!");
            String auditoriumChoice = typeLabel.getText();
            try
            {
                if (auditoriumChoice.equals("Small")) 
                {
                    System.out.println("ShowSmallAuditorium detected!");
                    mainApp.showShowSmallAuditorium(email, userName, chosenTheatre, getMissingSlots, getAuditoriumInfo);
                }
                if (auditoriumChoice.equals("Medium")) 
                {
                    System.out.println("ShowMediumAuditorium detected!");
                    mainApp.showShowMediumAuditorium(email, userName, chosenTheatre, getMissingSlots, getAuditoriumInfo);
                }
                if (auditoriumChoice.equals("Big")) 
                {
                    System.out.println("ShowBigAuditorium detected!");
                    mainApp.showShowBigAuditorium(email, userName, chosenTheatre, getMissingSlots, getAuditoriumInfo);
                }
            }
            catch(NullPointerException e)
            {
                System.out.println("ShowAuditoriumCancel detected!");
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
    
    //Skrypt dla guzika Edit.
    @FXML
    private void handleEdit(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException
    {
        
        int selectedIndex = auditoriumDatabaseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) 
        {
            System.out.println("EditAuditorium detected!");
            String auditoriumChoice = typeLabel.getText();
            try
            {
                if (auditoriumChoice.equals("Small")) 
                {
                    System.out.println("EditSmallAuditorium detected!");
                    mainApp.showEditSmallAuditorium(email, userName, chosenTheatre, getMissingSlots, getAuditoriumInfo);
                }
                if (auditoriumChoice.equals("Medium")) 
                {
                    System.out.println("EditMediumAuditorium detected!");
                    mainApp.showEditMediumAuditorium(email, userName, chosenTheatre, getMissingSlots, getAuditoriumInfo);
                }
                if (auditoriumChoice.equals("Big")) 
                {
                    System.out.println("EditBigAuditorium detected!");
                    mainApp.showEditBigAuditorium(email, userName, chosenTheatre, getMissingSlots, getAuditoriumInfo);
                }
            }
            catch(NullPointerException e)
            {
                System.out.println("AddAuditoriumCancel detected!");
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
    
    //Skrypt dla guzika Delete.
    @FXML
    private void handleDelete(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("DeleteAuditorium detected!"); //kontrola, czy dziala interakcja.
        boolean okClicked = false;
        {
            int selectedIndex = auditoriumDatabaseTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) 
            {
                System.out.println("Auditorium Clicked!");
                okClicked = mainApp.showDeleteAuditorium(email, auditoriumID);
                if(okClicked)
                {
                    System.out.println("DeleteAuditoriumOK detected!"); //kontrola, czy dziala interakcja.   
                    auditoriumDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
                    dataHarvester(); //Pobranie swiezych danych.
                    auditoriumDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
                    okClicked = false;
                }
                else
                {
                    System.out.println("DeleteAuditoriumCancel detected!"); //kontrola, czy dziala interakcja.   
                }
            } 
            else 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setHeaderText("Brak sali do usunięcia!");
                alert.setContentText("Proszę wybrać salę z tabeli!");
                alert.showAndWait();
            }
        }
    }
    
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("MenuBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showTheatreDatabase(email, userName);
    }
}
