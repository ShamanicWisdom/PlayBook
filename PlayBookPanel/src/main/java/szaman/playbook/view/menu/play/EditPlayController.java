/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska edycji sztuki - EditPlayController.java.
 * 
 */


package szaman.playbook.view.menu.play;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.entity.Play;
import szaman.playbook.utility.HibernateConnector;

// Reszta klas //

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 
 *
 * @author Szaman
 */

public class EditPlayController
{
    @FXML
    private TextField nameField;
    @FXML
    private TextField genreField;
    @FXML
    private TextArea descriptionTextArea;
    
    private boolean okClicked = false;
    
    private Stage dialogStage;
    
    Play playData;
    
    HibernateConnector connector = new HibernateConnector();
        
    private int playID;
 

    //Wyswietlenie okna i informacji.
    public void setDialogStage(Stage dialogStage, int playID) throws SQLException, ClassNotFoundException 
    {   
        List<Play> playList = null;
        this.dialogStage = dialogStage; 
        this.playID = playID;
        playList = connector.pullPlayData(playID);
        for(Play playData : playList)
        {
            nameField.setText(playData.getName());
            nameField.setPromptText("Nazwa sztuki");
            genreField.setText(playData.getGenre());
            genreField.setPromptText("Nazwa gatunku");
            descriptionTextArea.setText(playData.getDescription());
            descriptionTextArea.setPromptText("Opis sztuki");
            descriptionTextArea.setWrapText(true);
            descriptionTextArea.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
        }
    }
    
    //Logika, czy guzik OK zostanie klikniety.
    public boolean isOkClicked() 
    {
        return okClicked;
    }
        
    /*************************************************************************************************************************************/
    
    //Skrypt dla guzika OK.
    @FXML
    private void handleOk(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("PlayEditOK detected!"); //kontrola, czy dziala interakcja.
        if (validator()) 
        {
            okClicked = true;
            dialogStage.close();
        }
    }
    
    //Walidator danych.
    private boolean validator() throws SQLException, ClassNotFoundException, PropertyVetoException 
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
        
            if (nameField.getText() == null || nameField.getText().length() == 0) 
            {
                errorMessage += "Nie podano nazwy sztuki!" + "\n";
            }

            if (genreField.getText() == null || genreField.getText().length() == 0) 
            {
                errorMessage += "Nie podano nazwy gatunku!" + "\n"; 
            }

            if (descriptionTextArea.getText() == null || descriptionTextArea.getText().length() == 0) 
            {
                errorMessage += "Nie podano opisu sztuki!" + "\n";
            }

            else
            {
                Pattern noNumbers = Pattern.compile("\\D+"); //Wzorzec, ktory nie zawiera cyfr.
        //2. Alerty do zle wypelnionego pola genreField:
                Matcher matchGenre = noNumbers.matcher(genreField.getText());
                Boolean boolNoNumbersInGenre = matchGenre.matches();

                //5.2 Gatunek z cyframi.
                if (!(boolNoNumbersInGenre))
                {
                    errorMessage += "Nazwa gatunku nie może zawierać cyfr!" + "\n";
                }

            }
        }
        
    //Gdy liczba bledow jest rowna zero:
        if (errorMessage.length() == 0) 
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(dialogStage);
            alert.setTitle("Sukces!");
            alert.setHeaderText(null);
            connector.editPlayData(playID, nameField.getText(), genreField.getText(), descriptionTextArea.getText());
            alert.setContentText("Aktualizacja danych zakończona powodzeniem!");
            alert.showAndWait();
            return true;
        } 
        
    //Gdy liczba bledow jest rozna od zera:
        else 
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(dialogStage);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Błąd aktualizacji danych!");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
    
    //Skrypt dla guzika Cancel.
    @FXML
    private void handleCancel(ActionEvent event) throws SQLException, ClassNotFoundException 
    {
        System.out.println("PlayEditCancel detected!"); //kontrola, czy dziala interakcja.
        dialogStage.close();
    }
}