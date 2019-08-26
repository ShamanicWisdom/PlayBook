/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska edycji teatru - EditTheatreController.java.
 * 
 */

package szaman.playbook.view.menu.theatre;

// Laczenie z reszta wlasnych klas //

import java.beans.PropertyVetoException;
import szaman.playbook.utility.HibernateConnector;

// Reszta klas // 

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import szaman.playbook.entity.Theatre;

/**
 *
 * @author Szaman
 */

public class EditTheatreController 
{
    @FXML
    private TextField nameField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private ChoiceBox voivodeshipBox;
    
    private Stage dialogStage;
    
    private Theatre theatre;
    
    private int theatreID;
    
    private boolean okClicked = false;
    
    HibernateConnector connector = new HibernateConnector();
    
    List<String> voivodeshipList = new ArrayList<String>();
    
    String chosenVoivodeship = null;
    
    //Inicjalizacja.
    @FXML
    private void initialize() 
    {
    }

    //Przypisanie okna dialogowego.
    public void setDialogStage(Stage dialogStage, int theatreID) throws SQLException, ClassNotFoundException 
    {
        this.dialogStage = dialogStage;
        List<Theatre> theatreList = null;
        this.theatreID = theatreID;
        voivodeshipList.addAll(Arrays.asList("Dolnośląskie",
                                             "Kujawsko-Pomorskie",
                                             "Lubelskie",
                                             "Lubuskie",
                                             "Łódzkie",
                                             "Małopolskie",
                                             "Mazowieckie",
                                             "Opolskie",
                                             "Podkarpackie",
                                             "Podlaskie",
                                             "Pomorskie",
                                             "Śląskie",
                                             "Świętokrzyskie",
                                             "Warmińsko-Mazurskie",
                                             "Wielkopolskie",
                                             "Zachodniopomorskie"));
        
        theatreList = connector.pullTheatreData(theatreID);
        for(Theatre theatreData : theatreList)
        {
            nameField.setText(theatreData.getName());
            nameField.setPromptText("Nazwa teatru");
            streetField.setText(theatreData.getStreet());
            streetField.setPromptText("Nazwa ulicy");
            cityField.setText(theatreData.getCity());
            cityField.setPromptText("Nazwa miasta");
            phoneNumberField.setText(Integer.toString(theatreData.getPhoneNumber()));
            phoneNumberField.setPromptText("Numer telefonu");
            chosenVoivodeship = theatreData.getVoivodeship();
        }
        
        //int actualVoivodeship = voivodeshipList.indexOf(chosenVoivodeship); //przypisanie indeksu aktualnie wybranego wojewodztwa
        
        voivodeshipBox.setTooltip(new Tooltip("Wybierz województwo"));
        voivodeshipBox.getSelectionModel().clearSelection(); //Czyszczenie wyboru Choice Boxa
        voivodeshipBox.getItems().clear(); //Czyszczenie Choice Boxa.

        voivodeshipBox.getItems().addAll(voivodeshipList); //Wypelnienie Choice Boxa danymi z bazy danych.
        
        voivodeshipBox.getSelectionModel().select(voivodeshipList.indexOf(chosenVoivodeship)); //wybranie aktualnego wojewodztwa.
                
        voivodeshipBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() //Nasluch wybranej rzeczy z Choice Boxa
        { 
            public void changed(ObservableValue observableValue, Number voivodeshipValue, Number newVoivodeshipValue) //observableValue - wybrana rzecz, value - stary indeks, newValue - nowy indeks (przy ewentualnej zmianie wyboru)
            {
                if(newVoivodeshipValue.equals(-1))
                {
                    chosenVoivodeship = null;
                    Number fixer = 1;
                    newVoivodeshipValue = newVoivodeshipValue.intValue() + fixer.intValue();
                    observableValue.removeListener(this); //Usuniecie nasluchu powoduje zaprzestanie obserwacji listy - pozwala to nam na ewentualna zmiane nie zawiesi uzycia listy.
                }
                chosenVoivodeship = voivodeshipList.get(newVoivodeshipValue.intValue());
                System.out.println("Wybrane województwo: " + chosenVoivodeship); 
            }
        }); 
        
        
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
                errorMessage += "Nie podano nazwy teatru!" + "\n";
            }
            
            if (cityField.getText() == null || cityField.getText().length() == 0) 
            {
                errorMessage += "Nie podano miasta!" + "\n";
            }

            if (streetField.getText() == null || streetField.getText().length() == 0) 
            {
                errorMessage += "Nie podano ulicy!" + "\n"; 
            }
            
            if (chosenVoivodeship == null || chosenVoivodeship.length() == 0) 
            {
                errorMessage += "Nie podano województwa!" + "\n"; 
            }
            
            if (phoneNumberField.getText() == null || phoneNumberField.getText().length() == 0) 
            {
                errorMessage += "Nie podano numeru telefonu!" + "\n"; 
            }
            
            if (phoneNumberField.getText().length() != 9) 
            {
                errorMessage += "Proszę podać poprawny numeru telefonu! (9 cyfr)" + "\n"; 
            }

            else
            { 

        //2. Alerty do zle wypelnionego pola nameField:
                Pattern numbersOnly = Pattern.compile("^[0-9]*$"); //Wzorzec, w ktorym nie wystepuja litery ani znaki specjalne.
                Pattern charsOnly = Pattern.compile("\\D+"); //Wzorzec, w ktorym nie wystepuja znaki specjalne.
                
                Matcher matchCityName = charsOnly.matcher(cityField.getText()); //Matcher pobiera nazwe miasta i porownuje , czy zgadza sie ze wzorcem.
                Boolean cityWithoutSpecials = matchCityName.matches(); //Logika dla matchera od imienia.
            
                //2.1 Nazwa miasta ze znakami specjalnymi.
                if (!(cityWithoutSpecials))
                {
                    errorMessage += "Nazwa nie może zawierać znaków specjalnych ani cyfr!" + "\n";
                }
                
                Matcher matchPhoneNumber = numbersOnly.matcher(phoneNumberField.getText()); //Matcher pobiera numer telefonu i porownuje , czy zgadza sie ze wzorcem.
                Boolean phoneNumberWithoutChars = matchPhoneNumber.matches(); //Logika dla matchera od imienia.
                
                //2.2 Numer telefonu ze zawartoscia inna niz cyfry.
                if (!(phoneNumberWithoutChars))
                {
                    errorMessage += "Numer telefonu musi składać się tylko z cyfr!" + "\n";
                }
                else
                {
                    //2.3 Teatr juz figuruje w bazie danych.
                    if(connector.isTheatreExists(nameField.getText(), phoneNumberField.getText()))
                    {
                        errorMessage += "Teatr jest już w bazie danych!" + "\n";
                    }
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
            connector.editTheatreData(theatreID, nameField.getText(), cityField.getText(), streetField.getText(), chosenVoivodeship, phoneNumberField.getText());
            alert.setContentText("Edycja danych teatru zakończona powodzeniem!");
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
            alert.setHeaderText("Błąd edycji danych teatru!");
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
