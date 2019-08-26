/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska bazy danych seansow - ShowDatabaseController.java.
 * 
 */

package szaman.playbook.view.menu.show;

// Laczenie z reszta wlasnych klas //

import java.beans.PropertyVetoException;
import szaman.playbook.Core;
import szaman.playbook.utility.HibernateConnector;
import szaman.playbook.entity.Show;

// Reszta klas //

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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
import szaman.playbook.entity.Auditorium;
import szaman.playbook.entity.Play;
import szaman.playbook.entity.Reservation;
import szaman.playbook.entity.Theatre;

/**
 * 
 *
 * @author Szaman
 */

public class ShowDatabaseController
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
    private TableView<Show> showDatabaseTable;
    @FXML
    private TableColumn<Show, String> playColumn;
    @FXML
    private TableColumn<Show, String> dateColumn;
    @FXML
    private TableColumn<Show, String> slotsLeftColumn;
    
    private final ObservableList<Show> dataGatherer = FXCollections.observableArrayList();
       
    private Core mainApp;
        
    Show show;
    
    List<Show> detailedShowList = null;
    
    HibernateConnector connector = new HibernateConnector();
    
    Boolean connectionValidation = false;
    
    private String email = null; //Przechowuje informacje o zalogowanym koncie (w formie adresu mailowego)
    private String userName = null;
    private Integer showID;
    
    //Inicjalizacja danych do tabeli.
    @FXML
    private void initialize() 
    {
        playColumn.setCellValueFactory(cellData -> cellData.getValue().playNameProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        slotsLeftColumn.setCellValueFactory(cellData -> cellData.getValue().slotsLeftProperty());
        showDatabaseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showShowDetails(newValue));
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
        List<Show> showList = null;
        try
        {
            connectionValidation = connector.testConnection();
            if(connectionValidation == true)
            {
                System.out.println("DB Connected!");
                showList = connector.pullShowList();
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
                    for(Show s : showList)
                    {
                        //Aktualizacja wolnych miejsc.
                        int reservationCount = 0;
                        List<Reservation> reservationList = connector.pullReservationList(s);
                        if(reservationList.isEmpty())
                        {
                            connector.updateShowSlotsLeft(s, reservationCount);
                        }
                        else
                        {
                            for(Reservation r : reservationList)
                            {
                                reservationCount += r.getNoNormal() + r.getNoHalf();
                            }
                            connector.updateShowSlotsLeft(s, reservationCount);
                        }
                        
                        //Pull danych sali i teatru.
                        Auditorium auditorium = connector.pullSingleAuditorium(s.getAuditorium().getAuditoriumId());
                        Theatre theatre = connector.pullSingleTheatre(auditorium.getTheatre().getTheatreId());
                        String theatreAndAuditoriumData = theatre.getCity() + ", " + theatre.getName() + ", Sala: " + auditorium.getAuditoriumNumber();
                        //Pull danych sztuki.
                        Play play = connector.pullSinglePlay(s.getPlay().getPlayId());
                        String playName = play.getName();
                        //Budowanie nowego obiektu Show na podstawie danych z aktualnego obiektu z fora + wzbogacamy go o pullniete dane.
                        Show detailedShow = new Show(s.getShowId(), s.getPrice(), s.getDate(), s.getStartHour(), s.getLength(), auditorium.getNoSlots() - reservationCount, theatreAndAuditoriumData, playName);
                        dataGatherer.add(detailedShow);
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
    private void showShowDetails(Show show) 
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
            showID = show.getShowId();
            this.show = show;
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
            this.show = null;
        }
    }
    
    //Skrypt dla guzika Add.
    @FXML
    private void handleAdd(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("AddShow detected!"); //kontrola, czy dziala interakcja.
        Show temp = new Show();
        boolean okClicked = mainApp.showAddShow(temp);
        if (okClicked) 
        {
            System.out.println("AddShowOK detected!"); //kontrola, czy dziala interakcja.
            showDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
            dataHarvester(); //Pobranie swiezych danych.
            showDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
            okClicked = false;
        }
        System.out.println("AddShowCancel detected!"); //kontrola, czy dziala interakcja.
    }
    
    //Skrypt dla guzika Edit.
    @FXML
    private void handleEdit(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException, ParseException
    {
        boolean okClicked = false;
        int selectedIndex = showDatabaseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) 
        {
            System.out.println("ShowEdit detected!");
            okClicked = mainApp.showEditShow(showID);    
            showDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
            dataHarvester(); //Pobranie swiezych danych.
            showDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
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
    
    
    //Skrypt dla guzika Reservation.
    @FXML
    private void handleReservation(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException
    {
        int selectedIndex = showDatabaseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) 
        {
            System.out.println("ReservationDatabaase detected!");
            mainApp.showReservationDatabase(email, userName, show); 
        }
        else 
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Brak seansu do pokazania rezerwacji!");
            alert.setContentText("Proszę wybrać seans z tabeli!");
            alert.showAndWait();
        }
    }
    
    //Skrypt dla guzika Delete.
    @FXML
    private void handleDelete(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("ShowDelete detected!"); //kontrola, czy dziala interakcja.
        boolean okClicked = false;
        {
            int selectedIndex = showDatabaseTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) 
            {
                System.out.println("Show Clicked!");
                okClicked = mainApp.showDeleteShow(email, showID);
                if(okClicked)
                {
                    System.out.println("ShowDeleteOK detected!"); //kontrola, czy dziala interakcja.   
                    showDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
                    dataHarvester(); //Pobranie swiezych danych.
                    showDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
                    okClicked = false;
                }
                else
                {
                    System.out.println("ShowDeleteCancel detected!"); //kontrola, czy dziala interakcja.   
                }
            } 
            else 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setHeaderText("Brak seansu do usunięcia!");
                alert.setContentText("Proszę wybrać seans z tabeli!");
                alert.showAndWait();
            }
        }
    }
    
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws IOException 
    {
        System.out.println("ShowDatabaseBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showMenu(email, userName);
    }
}
