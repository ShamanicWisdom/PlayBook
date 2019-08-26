/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska finalizowania edycji rezerwacji - ConfirmEditReservationController.java
 * 
 */

package szaman.playbook.view.menu.show.reservation;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.utility.HibernateConnector;
import szaman.playbook.entity.Show;

// Reszta klas // 

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import szaman.playbook.entity.Reservation;

/**
 *
 * @author Szaman
 */

public class ConfirmEditReservationController 
{
    @FXML
    private Label chosenSlotCount;
    @FXML
    private Label priceLabel;
    @FXML
    private TextField noNormalField;
    @FXML
    private TextField noHalfField;
    @FXML
    private TextField codeField;
    
    private Stage dialogStage;
    private Show show;
    private boolean okClicked = false;
    
    HibernateConnector connector = new HibernateConnector();
    
    Boolean connectionValidation = false;
    
    private int showID;
    private int chosenSlotsCount;
    
    private int normalSlots;
    private int halfSlots;
    
    private float countedPrice;
    
    private String chosenSlotsID;
    
    Reservation chosenReservation;
    
    String correctHour;

    //Inicjalizacja.
    @FXML
    private void initialize() 
    {
    }

    //Przypisanie okna dialogowego.
    public void setDialogStage(Stage dialogStage, Reservation chosenReservation, int showID, int chosenSlotsCount, String chosenSlotsID)  throws PropertyVetoException, SQLException 
    {
        this.dialogStage = dialogStage;
        this.showID = showID;
        this.chosenReservation = chosenReservation;
        this.chosenSlotsCount = chosenSlotsCount;
        this.chosenSlotsID = chosenSlotsID;
        chosenSlotCount.setText("Ilość miejsc: " + chosenSlotsCount);
        priceLabel.setVisible(false);        
    }

    //Logika, czy guzik OK zostanie klikniety.
    public boolean isOkClicked() 
    {
        return okClicked;
    }

    //Skrypt dla guzika Count.
    @FXML
    private void handleCount() throws SQLException, ClassNotFoundException, PropertyVetoException, ParseException 
    {
        if (countingValidator()) 
        {
            priceLabel.setVisible(true); 
            priceLabel.setText(String.format("%.2f", countedPrice) + "zł");
        }
        else
        {
            priceLabel.setVisible(false); 
            priceLabel.setText("");
        }
    }
    
    //Skrypt dla guzika OK.
    @FXML
    private void handleOk() throws SQLException, ClassNotFoundException, PropertyVetoException, ParseException 
    {
        if (validator()) 
        {
            okClicked = true;
            dialogStage.close();
        }
    }
    
    
    //Walidator danych.
    private boolean countingValidator() throws SQLException, ClassNotFoundException, PropertyVetoException, ParseException 
    {        
        String errorMessage = ""; //Pusty string, do ktorego beda dodawane poszczegolne errory.
        Boolean isConnectionAvailable = false; //Boolean przetrzymujacy informacje, czy jest mozliwe nawiazanie polaczenia z baza - domyslnie ustawiony jako false.
        
        //Testujemy polaczenie z baza danych.
        isConnectionAvailable = connector.testConnection();
        
    //Alerty: 
    //1. Alert do braku lacznosci z baza.
        //1.1 Brak polaczenia z baza danych spowodowany brakiem dostepu/brakiem Internetu.
        if (isConnectionAvailable == false)
        {
            errorMessage += "Brak połączenia z bazą danych!" + "\n";
        }
        else
        {  
            Pattern numbersOnly = Pattern.compile("^[0-9]*$");
            //pole ilosci biletow normalnych jest puste.
            if(noNormalField.getText() == null || noNormalField.getText().length() == 0)
            {
                if(noHalfField.getText() == null || noHalfField.getText().length() == 0)
                {
                    errorMessage += "Podano błędną liczbę miejsc!" + "\n";
                }
                else
                {
                    try
                    {
                        int parseHalf = Integer.parseInt(noHalfField.getText());
                    }
                    catch(NumberFormatException e)
                    {
                        errorMessage += "Podano błędną liczbę miejsc!" + "\n";
                    }
                }
            }
            //pole ilosci biletow ulgowych jest puste.
            else if(noHalfField.getText() == null || noHalfField.getText().length() == 0)
            {
                if(noNormalField.getText() == null || noNormalField.getText().length() == 0)
                {
                    errorMessage += "Podano błędną liczbę miejsc!" + "\n";
                }
                else
                {
                    try
                    {
                        int parseNormal = Integer.parseInt(noNormalField.getText());
                    }
                    catch(NumberFormatException e)
                    {
                        errorMessage += "Podano błędną liczbę miejsc!" + "\n";
                    }
                }
            }

            else
            {
                //Matchery
                Matcher matchNoNormal = numbersOnly.matcher(noNormalField.getText());
                Matcher matchNoHalf = numbersOnly.matcher(noHalfField.getText());
                //Logika
                Boolean noNormalValidator = matchNoNormal.matches();
                Boolean NoHalfValidator = matchNoHalf.matches();
                
                if(!noNormalValidator)
                {
                    if(!errorMessage.contains("Poszę usunąć wszystkie znaki, które nie są cyframi!"))
                    {
                        errorMessage += "Poszę usunąć wszystkie znaki, które nie są cyframi!";
                    }
                }
                
                else if(!NoHalfValidator)
                {
                    if(!errorMessage.contains("Poszę usunąć wszystkie znaki, które nie są cyframi!"))
                    {
                        errorMessage += "Poszę usunąć wszystkie znaki, które nie są cyframi!";
                    }
                }
                else
                {
                    int countedSlotsGivenFromFields = Integer.parseInt(noNormalField.getText()) + Integer.parseInt(noHalfField.getText());
                    System.out.println("Podana ilosc: " + countedSlotsGivenFromFields);
                    if(countedSlotsGivenFromFields != chosenSlotsCount)
                    {
                        errorMessage += "Podano błędną liczbę miejsc!" + "\n";
                    }
                }
            }
            
            
            //!!!!!!!!!!!!!!!!!!BRAK OBSLUGI KODOW!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!//
        }
        
    //Gdy liczba bledow jest rowna zero:
        if (errorMessage.length() == 0) 
        {
            show = connector.pullSingleShow(showID);
                       
            if(noNormalField.getText() == null || noNormalField.getText().length() == 0)
            {
                normalSlots = 0;
            }
            else
            {
                normalSlots = Integer.parseInt(noNormalField.getText());
            }
            if(noHalfField.getText() == null || noHalfField.getText().length() == 0)
            {
                halfSlots = 0;
            }
            else
            {
                halfSlots = Integer.parseInt(noHalfField.getText());
            }
            if(codeField.getText() == null || codeField.getText().length() == 0)
            {
                countedPrice = (normalSlots * show.getPrice()) + ((halfSlots * show.getPrice()) / 2);
            }
            else
            {
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!JAK BEDA KODY!!!!!!!!!!!!!
                countedPrice = (normalSlots * show.getPrice()) + ((halfSlots * show.getPrice()) / 2);
            }
            return true;
        } 
        
    //Gdy liczba bledow jest rozna od zera:
        else 
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(dialogStage);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Błąd podliczenia ceny rezerwacji!");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    //Walidator danych.
    private boolean validator() throws SQLException, ClassNotFoundException, PropertyVetoException, ParseException 
    {        
        String errorMessage = ""; //Pusty string, do ktorego beda dodawane poszczegolne errory.
        Boolean isConnectionAvailable = false; //Boolean przetrzymujacy informacje, czy jest mozliwe nawiazanie polaczenia z baza - domyslnie ustawiony jako false.
                
        //Testujemy polaczenie z baza danych.
        isConnectionAvailable = connector.testConnection();
        
    //Alerty: 
    //1. Alert do braku lacznosci z baza.
        //1.1 Brak polaczenia z baza danych spowodowany brakiem dostepu/brakiem Internetu.
        if (isConnectionAvailable == false)
        {
            errorMessage += "Brak połączenia z bazą danych!" + "\n";
        }
        else
        {  
            if(priceLabel.getText() == null || priceLabel.getText().length() == 0)
            {
                errorMessage += "Proszę podliczyc cenę!" + "\n";
            }
            //!!!!!!!!!!!!!!!!!!BRAK OBSLUGI KODOW!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!//
        }
        
    //Gdy liczba bledow jest rowna zero:
        if (errorMessage.length() == 0) 
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(dialogStage);
            alert.setTitle("Sukces!");
            alert.setHeaderText(null);
            
            connector.editReservation(chosenReservation.getReservationId(), chosenSlotsCount, chosenSlotsID, normalSlots, halfSlots, countedPrice);
            
            alert.setContentText("Edycja rezerwacji zakończona powodzeniem!");
            alert.showAndWait();
            return true;
        } 
        
    //Gdy liczba bledow jest rozna od zera:
        else 
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(dialogStage);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Błąd edycji rezerwacji!");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
    //Skrypt dla guzika Cancel.
    @FXML
    private void handleCancel() 
    {
        dialogStage.close();
    }
}
