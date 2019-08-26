/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska archiwalnej bazy danych rezerwacji - ArchiveReservationDatabaseController.java.
 * 
 */

package szaman.playbook.view.menu.archive.reservation;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.Core;
import szaman.playbook.utility.HibernateConnector;
import szaman.playbook.entity.archive.ArchiveReservation;

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

public class ArchiveReservationDatabaseController
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
    private Label deleteLabel;
    
    
    @FXML
    private TableView<ArchiveReservation> reservationDatabaseTable;
    @FXML
    private TableColumn<ArchiveReservation, String> reservationIdColumn;
    @FXML
    private TableColumn<ArchiveReservation, String> noSlotsColumn;
    @FXML
    private TableColumn<ArchiveReservation, String> slotsIdColumn;
    
    private final ObservableList<ArchiveReservation> dataGatherer = FXCollections.observableArrayList();
       
    private Core mainApp;
    
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
    
    public void setMainApp(Core mainApp, String email, String userName) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        this.mainApp = mainApp;
        this.email = email; //Przypisanie adresu mailowego z poprzedniego okna.
        this.userName = userName;  
        dataHarvester();
        slotsIdTextArea.setWrapText(true);
        slotsIdTextArea.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
        reservationDatabaseTable.setItems(dataGatherer);
    }
        
    /*************************************************************************************************************************************/
    
    public void dataHarvester() throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        List<ArchiveReservation> reservationList = null;
        try
        {
            connectionValidation = connector.testConnection();
            if(connectionValidation == true)
            {
                System.out.println("DB Connected!");
                reservationList = connector.pullArchiveReservationList();
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
                    for(ArchiveReservation r : reservationList)
                    {
                        dataGatherer.add(r);
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
    private void showReservationDetails(ArchiveReservation reservation) 
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
            deleteLabel.setText(reservation.getDeleteDate());
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
            deleteLabel.setText("");
        }
    }
        
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("ArchiveReservationDatabaseBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showArchiveMenu(email, userName);
    }
}
