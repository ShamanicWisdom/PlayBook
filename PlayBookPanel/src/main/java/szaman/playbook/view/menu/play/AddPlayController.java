/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska dodawania nowej sztuki - AddPlayController.java.
 * 
 */

package szaman.playbook.view.menu.play;

// Laczenie z reszta wlasnych klas //

import java.beans.PropertyVetoException;
import szaman.playbook.entity.Play;
import szaman.playbook.utility.HibernateConnector;

// Reszta klas // 

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Szaman
 */

public class AddPlayController 
{
    @FXML
    private TextField nameLabel;
    @FXML
    private TextField genreLabel;
    @FXML
    private TextArea playDescriptionTextArea;
    
    private Stage dialogStage;
    private Play play;
    private boolean okClicked = false;
    
    HibernateConnector connector = new HibernateConnector();

    //Inicjalizacja.
    @FXML
    private void initialize() 
    {
    }

    //Przypisanie okna dialogowego.
    public void setDialogStage(Stage dialogStage) 
    {
        this.dialogStage = dialogStage;
    }

    //Zbieranie danych odnosnie nowego konta.
    public void setPlay(Play play) 
    {
        this.play = play;
        nameLabel.setPromptText("Nazwa sztuki");
        genreLabel.setPromptText("Nazwa gatunku");
        playDescriptionTextArea.setPromptText("Opis sztuki");
        playDescriptionTextArea.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
    }

    //Logika, czy guzik OK zostanie klikniety.
    public boolean isOkClicked() 
    {
        return okClicked;
    }

    //Skrypt dla guzika OK.
    @FXML
    private void handleOk() throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        if (validator()) 
        {
            play.setName(nameLabel.getText());
            play.setDescription(playDescriptionTextArea.getText());
            play.setGenre(genreLabel.getText());
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
            if (nameLabel.getText() == null || nameLabel.getText().length() == 0) 
            {
                errorMessage += "Nie podano nazwy sztuki!" + "\n";
            }
            
            if (genreLabel.getText() == null || genreLabel.getText().length() == 0) 
            {
                errorMessage += "Nie podano nazwy gatunku!" + "\n";
            }

            if (playDescriptionTextArea.getText() == null || playDescriptionTextArea.getText().length() == 0) 
            {
                errorMessage += "Nie podano opisu sztuki!" + "\n"; 
            }

            else
            { 

        //2. Alerty do zle wypelnionego pola nameField:
                Pattern charOnly = Pattern.compile("\\D+"); //Wzorzec, w ktorym nie wystepuja znaki specjalne.
                Matcher matchName = charOnly.matcher(genreLabel.getText()); //Matcher pobiera imie i porownuje , czy zgadza sie ze wzorcem.
                Boolean genreWithoutSpecials = matchName.matches(); //Logika dla matchera od imienia.
            
                //2.2 Imie ze znakami specjalnymi.
                if (!(genreWithoutSpecials))
                {
                    errorMessage += "Nazwa nie może zawierać znaków specjalnych ani cyfr!" + "\n";
                }
                
                //2.1 Tytul juz figuruje w bazie danych.
                if (connector.isPlayExists(nameLabel.getText()))
                {
                    errorMessage += "Taka sztuka już istnieje!" + "\n";
                }   
            }
        }
        
    //Gdy liczba bledow jest rowna zero:
        if (errorMessage.length() == 0) 
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(dialogStage);
            alert.setTitle("Sukces!");
            alert.setHeaderText(null);
            connector.addPlay(nameLabel.getText(), playDescriptionTextArea.getText(), genreLabel.getText());
            alert.setContentText("Dodawanie sztuki zakończone powodzeniem!");
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
            alert.setHeaderText("Błąd dodawania sztuki!");
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
