/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska bazy danych rezerwacji - ReservationDatabaseController.java.
 * 
 */

package szaman.playbook.view.menu.show.reservation;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.Core;
import szaman.playbook.utility.HibernateConnector;
import szaman.playbook.entity.Show;
import szaman.playbook.entity.Reservation;

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
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import szaman.playbook.entity.Account;
import szaman.playbook.entity.Auditorium;
import szaman.playbook.entity.Play;

/**
 * 
 *
 * @author Szaman
 */

public class ReservationDatabaseController
{
    @FXML
    private Label showDataLabel;
    @FXML
    private Label accountDataLabel;
    @FXML
    private Label noSlotsLabel;
    @FXML
    private TextArea slotsIdTextArea;
    @FXML
    private Label noNormalLabel;
    @FXML
    private Label noHalfLabel;
    @FXML
    private Label priceLabel;
    
    @FXML
    private Button showSlots;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    
    @FXML
    private TableView<Reservation> reservationDatabaseTable;
    @FXML
    private TableColumn<Reservation, String> reservationIdColumn;
    @FXML
    private TableColumn<Reservation, String> noSlotsColumn;
    @FXML
    private TableColumn<Reservation, String> slotsIdColumn;
    
    private final ObservableList<Reservation> dataGatherer = FXCollections.observableArrayList();
       
    private Core mainApp;
    
    Auditorium chosenAuditorium;    
    
    Show chosenShow;
    
    Reservation chosenReservation;
    
    List<Reservation> detailedReservationList = null;
    
    HibernateConnector connector = new HibernateConnector();
    
    Boolean connectionValidation = false;
    
    private String email = null; //Przechowuje informacje o zalogowanym koncie (w formie adresu mailowego)
    private String userName = null;
    
    //Inicjalizacja danych do tabeli.
    @FXML
    private void initialize() 
    {
        reservationIdColumn.setCellValueFactory(cellData -> cellData.getValue().reservationIdProperty());
        noSlotsColumn.setCellValueFactory(cellData -> cellData.getValue().noSlotsProperty());
        slotsIdColumn.setCellValueFactory(cellData -> cellData.getValue().slotsIdProperty());
        reservationDatabaseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showReservationDetails(newValue));
    }
    
    public void setMainApp(Core mainApp, String email, String userName, Show show) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        this.mainApp = mainApp;
        this.email = email; //Przypisanie adresu mailowego z poprzedniego okna.
        this.userName = userName;  
        
        chosenShow = connector.pullSingleShow(show.getShowId());
        chosenAuditorium = connector.pullSingleAuditorium(chosenShow.getAuditorium().getAuditoriumId());
        
        showSlots.setDisable(true);
        showSlots.setVisible(false);
        
        String accountRole = connector.getAccountRole(email);
        if(accountRole.equals("operator")) //jesli zalogowane jest konto operatora - chowamy opcje dodawania i edycji kont.
        {
            addButton.setDisable(true);
            addButton.setVisible(false);
            editButton.setDisable(true);
            editButton.setVisible(false);
        }
        dataHarvester();
        reservationDatabaseTable.setItems(dataGatherer);
    }
        
    /*************************************************************************************************************************************/
    
    public void dataHarvester() throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        List<Reservation> reservationList = null;
        try
        {
            connectionValidation = connector.testConnection();
            if(connectionValidation == true)
            {
                System.out.println("DB Connected!");
                reservationList = connector.pullReservationList(chosenShow);
                if(reservationList.isEmpty())
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
                    for(Reservation r : reservationList)
                    {
                        //Pull danych konta.
                        Account account = connector.pullSingleAccount(r.getAccount().getAccountId());
                        String accountData = account.getName() + " " + account.getSurname() + ", ID: " + account.getAccountId();
                        //Pull danych seansu.
                        Show show = connector.pullSingleShow(r.getShow().getShowId());
                        Play play = connector.pullSinglePlay(show.getPlay().getPlayId());
                        String showData = play.getName() + " (ID: " + show.getShowId() + ") Data: " + show.getDate().toString() + " " + show.getStartHour();
                        //Budowanie nowego obiektu Reservation na podstawie danych z aktualnego obiektu z fora + wzbogacamy go o pullniete dane.
                        Reservation detailedReservation = new Reservation(r.getReservationId(), accountData, showData, r.getNoSlots(), r.getSlotsId(), r.getNoNormal(), r.getNoHalf(), r.getPrice());
                        dataGatherer.add(detailedReservation);
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
    
    //Metoda do wyswietlenia wszystkich danych o rezerwacji.
    private void showReservationDetails(Reservation reservation) 
    {
        if (reservation != null) 
        {
            showDataLabel.setText(reservation.getShowData());
            accountDataLabel.setText(reservation.getAccountData());
            noSlotsLabel.setText(Integer.toString(reservation.getNoSlots()));
            slotsIdTextArea.setText(reservation.getSlotsId());
            noNormalLabel.setText(Integer.toString(reservation.getNoNormal()));
            noHalfLabel.setText(Integer.toString(reservation.getNoHalf()));
            priceLabel.setText(String.format("%.2f", reservation.getPrice()) + "zł");
            chosenReservation = reservation;
            showSlots.setDisable(false);
            showSlots.setVisible(true);
        } 
        else 
        {
            showDataLabel.setText("");
            accountDataLabel.setText("");
            noSlotsLabel.setText("");
            slotsIdTextArea.setText(""); 
            noNormalLabel.setText(""); 
            noHalfLabel.setText(""); 
            priceLabel.setText(""); 
            showSlots.setDisable(true);
            showSlots.setVisible(false);
            chosenReservation = null;
        }
    }
    
    //Skrypt dla guzika Add.
    @FXML
    private void handleAdd(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("AddReservationForShow Detected!");
        
        if (chosenAuditorium.getAuditoriumType().equals("Small")) 
        {
            System.out.println("AddReservationForSmallAuditorium detected!");
            mainApp.showAddReservationForSmallAuditorium(email, userName, chosenShow);
        }
        if (chosenAuditorium.getAuditoriumType().equals("Medium")) 
        {
            System.out.println("AddReservationForMediumAuditorium detected!");
            mainApp.showAddReservationForMediumAuditorium(email, userName, chosenShow);
        }
        if (chosenAuditorium.getAuditoriumType().equals("Big")) 
        {
            System.out.println("AddReservationForBigAuditorium detected!");
            mainApp.showAddReservationForBigAuditorium(email, userName, chosenShow);
        }
    }
    //Skrypt dla guzika Edit.
    @FXML
    private void handleEdit(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException
    {
        boolean okClicked = false;
        int selectedIndex = reservationDatabaseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) 
        {
            System.out.println("ShowEdit detected!");
            if (chosenAuditorium.getAuditoriumType().equals("Small")) 
            {
                System.out.println("EditReservationForSmallAuditorium detected!");
                mainApp.showEditReservationForSmallAuditorium(email, userName, chosenShow, chosenReservation);
            }
            if (chosenAuditorium.getAuditoriumType().equals("Medium")) 
            {
                System.out.println("EditReservationForMediumAuditorium detected!");
                mainApp.showEditReservationForMediumAuditorium(email, userName, chosenShow, chosenReservation);
            }
            if (chosenAuditorium.getAuditoriumType().equals("Big")) 
            {
                System.out.println("EditReservationForBigAuditorium detected!");
                mainApp.showEditReservationForBigAuditorium(email, userName, chosenShow, chosenReservation);
            }
        }
        else 
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Brak rezerwacji do edytowania!");
            alert.setContentText("Proszę wybrać rezerwację z tabeli!");
            alert.showAndWait();
        }
    } 
    
    @FXML
    private void handleShowAll(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("AllReservationsShow detected!"); //kontrola, czy dziala interakcja.
        List<Reservation> reservationList = connector.pullReservationList(chosenShow); 
        List<String> reservedSlotList = new ArrayList<>();
        for(Reservation r : reservationList)
        {
            List<String> catchedReservedSlotList = Arrays.asList(r.getSlotsId().split(",\\s*"));
            for(int i = 0; i < catchedReservedSlotList.size(); i++)
            {
                reservedSlotList.add(catchedReservedSlotList.get(i));
            }
        }
        if (chosenAuditorium.getAuditoriumType().equals("Small")) 
        {
            System.out.println("ShowReservationsInSmallAuditorium detected!");
            mainApp.showShowReservationsInSmallAuditorium(email, userName, chosenShow, reservedSlotList);
        }
        if (chosenAuditorium.getAuditoriumType().equals("Medium")) 
        {
            System.out.println("ShowReservationsInMediumAuditorium detected!");
            mainApp.showShowReservationsInMediumAuditorium(email, userName, chosenShow, reservedSlotList);
        }
        if (chosenAuditorium.getAuditoriumType().equals("Big")) 
        {
            System.out.println("ShowReservationsInBigAuditorium detected!");
            mainApp.showShowReservationsInBigAuditorium(email, userName, chosenShow, reservedSlotList);
        }
    }
    
    @FXML
    private void handleShowSpecific(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("SpecificReservationsShow detected!"); //kontrola, czy dziala interakcja.
        List<String> reservedSlotList = new ArrayList<>();
        List<String> catchedReservedSlotList = Arrays.asList(slotsIdTextArea.getText().split(",\\s*"));
        for(int i = 0; i < catchedReservedSlotList.size(); i++)
        {
            reservedSlotList.add(catchedReservedSlotList.get(i));
        }
        if (chosenAuditorium.getAuditoriumType().equals("Small")) 
        {
            System.out.println("ShowReservationsInSmallAuditorium detected!");
            mainApp.showShowReservationsInSmallAuditorium(email, userName, chosenShow, reservedSlotList);
        }
        if (chosenAuditorium.getAuditoriumType().equals("Medium")) 
        {
            System.out.println("ShowReservationsInMediumAuditorium detected!");
            mainApp.showShowReservationsInMediumAuditorium(email, userName, chosenShow, reservedSlotList);
        }
        if (chosenAuditorium.getAuditoriumType().equals("Big")) 
        {
            System.out.println("ShowReservationsInBigAuditorium detected!");
            mainApp.showShowReservationsInBigAuditorium(email, userName, chosenShow, reservedSlotList);
        }
    }
    
    //Skrypt dla guzika Delete.
    @FXML
    private void handleDelete(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("ShowDelete detected!"); //kontrola, czy dziala interakcja.
        boolean okClicked = false;
        {
            int selectedIndex = reservationDatabaseTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) 
            {
                System.out.println("Reservation Clicked!");
                okClicked = mainApp.showDeleteReservation(email, chosenReservation.getReservationId());
                if(okClicked)
                {
                    System.out.println("DeleteReservationOK detected!"); //kontrola, czy dziala interakcja.   
                    reservationDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
                    dataHarvester(); //Pobranie swiezych danych.
                    reservationDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
                    okClicked = false;
                }
                else
                {
                    System.out.println("DeleteReservationCancel detected!"); //kontrola, czy dziala interakcja.   
                }
            } 
            else 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setHeaderText("Brak rezerwacji do usunięcia!");
                alert.setContentText("Proszę wybrać rezerwację z tabeli!");
                alert.showAndWait();
            }
        }
    }
    
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("ReservationDatabaseBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showShowDatabase(email, userName);
    }
}
