/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska edycji seansu - EditShowController.java.
 * 
 */

package szaman.playbook.view.menu.show;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.entity.Play;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import org.hibernate.HibernateException;
import szaman.playbook.entity.Auditorium;
import szaman.playbook.entity.Theatre;

/**
 *
 * @author Szaman
 */

public class EditShowController 
{
    @FXML
    private ChoiceBox theatreBox;
    @FXML
    private ChoiceBox auditoriumBox;
    @FXML
    private ChoiceBox playBox;
    @FXML
    private TextField priceField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField startHourField;
    @FXML
    private TextField lengthField;
    @FXML 
    private ChoiceBox preselectedAuditoriumBox;
        
    private Stage dialogStage;
    private Show show;
    private boolean okClicked = false;
    
    HibernateConnector connector = new HibernateConnector();
    
    Boolean connectionValidation = false;
    
    List<Theatre> theatreList;
    List<Auditorium> auditoriumList;
    List<Auditorium> preselectedAuditoriumList;
    List<Auditorium> editedAuditoriumList;
    List<Play> playList;
    
    List<String> theatreDataList = new ArrayList<>();
    List<String> auditoriumDataList = new ArrayList<>();
    List<String> preselectedAuditoriumDataList = new ArrayList<>();
    List<String> playDataList = new ArrayList<>();
        
    Theatre chosenTheatre;
    Auditorium chosenAuditorium;
    Play chosenPlay;
    
    Theatre currentTheatre;
    Auditorium currentAuditorium;
    Play currentPlay;
    
    String correctHour;
    
    
    Boolean freshEdit = true;
    
    private int showID;
    
    private int chosenTheatreID;
    private int chosenAuditoriumID;
    private int chosenPlayID;

    //Inicjalizacja.
    @FXML
    private void initialize() 
    {
    }

    //Przypisanie okna dialogowego.
    public void setDialogStage(Stage dialogStage, int showID) throws PropertyVetoException, SQLException, ParseException 
    {
        this.dialogStage = dialogStage;
        theatreBox.setTooltip(new Tooltip("Lista dostępnych teatrów")); //Po najechaniu na Choice Box dostaniemy informacje, czego sie on tyczy.
        auditoriumBox.setTooltip(new Tooltip("Lista dostępnych sal w teatrach")); //Po najechaniu na Choice Box dostaniemy informacje, czego sie on tyczy.
        playBox.setTooltip(new Tooltip("Lista dostępnych sztuk")); //Po najechaniu na Choice Box dostaniemy informacje, czego sie on tyczy.
        
        List<Show> showList = null;
        this.dialogStage = dialogStage; 
        this.showID = showID;
        showList = connector.pullShowData(showID);
        
        for(Show showData : showList)
        {
            priceField.setText(Float.toString(showData.getPrice()));
            String convertedDate = new SimpleDateFormat("dd-MM-yyyy").format(showData.getDate());
            dateField.setText(convertedDate);
            startHourField.setText(showData.getStartHour());
            lengthField.setText(Integer.toString(showData.getLength()));
            currentAuditorium = connector.pullSingleAuditorium(showData.getAuditorium().getAuditoriumId());
            currentTheatre = connector.pullSingleTheatre(currentAuditorium.getTheatre().getTheatreId());
            currentPlay = connector.pullSinglePlay(showData.getPlay().getPlayId());
            auditoriumBox.setVisible(false);
            auditoriumBox.setDisable(true);
        }
        dataHarvester();
                
        for(Theatre t : theatreList)
        {
            theatreDataList.add(t.getName() + ", " + t.getCity());
            if(currentTheatre.getName().equals(t.getName()) && currentTheatre.getCity().equals(t.getCity()))
            {
                chosenTheatre = currentTheatre;
                chosenTheatreID = theatreDataList.size() - 1;
            }
        }
        theatreBox.getItems().addAll(theatreDataList); //Wypelnienie Choice Boxa danymi z bazy danych.
        theatreBox.getSelectionModel().select(theatreDataList.get(chosenTheatreID)); //wybranie aktualnej sali.
        theatreBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() //Nasluch wybranej rzeczy z Choice Boxa
        { 
            public void changed(ObservableValue observableValue, Number theatreValue, Number newTheatreValue) //observableValue - wybrana rzecz, value - stary indeks, newValue - nowy indeks (przy ewentualnej zmianie wyboru)
            { 
                if(newTheatreValue.equals(-1))
                {
                    chosenTheatre = null;
                    Number fixer = 1;
                    newTheatreValue = newTheatreValue.intValue() + fixer.intValue();
                    observableValue.removeListener(this); //Usuniecie nasluchu powoduje zaprzestanie obserwacji listy - pozwala to nam na ewentualna zmiane teatru bez narazania sie na wyjatki spowodowane nieaktualna lista.
                }
                chosenTheatre = theatreList.get(newTheatreValue.intValue()); //Przypisanie obiektu o ID takim, jaki jest w liscie do obiektu wyboru.
                
                preselectedAuditoriumBox.setVisible(false);
                preselectedAuditoriumBox.setDisable(true);
                auditoriumBox.setVisible(true);
                auditoriumBox.setDisable(false);
                
                chosenAuditorium = null; //reset wybranej sali po kazdej zmianie teatru.
                auditoriumDataList.clear();
                
                List<Auditorium> auditoriumList = connector.pullAuditoriumList(chosenTheatre); //Pull listy sal zgodnie z wybranym teatrem
                for(Auditorium a : auditoriumList)
                {
                    auditoriumDataList.add(a.getAuditoriumNumber() + ", Typ " + a.getAuditoriumType());
                }
                
                auditoriumBox.setValue(null); //Reset wartosci observera auditoriumBoxa.
                auditoriumBox.getSelectionModel().clearSelection(); //Czyszczenie wyboru Choice Boxa
                auditoriumBox.getItems().clear(); //Czyszczenie Choice Boxa.
                auditoriumBox.getItems().addAll(auditoriumDataList); //Wypelnienie Choice Boxa danymi z bazy danych.
                
                auditoriumBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() //Nasluch wybranej rzeczy z Choice Boxa
                { 
                    public void changed(ObservableValue observableValue, Number auditoriumValue, Number newAuditoriumValue) //observableValue - wybrana rzecz, value - stary indeks, newValue - nowy indeks (przy ewentualnej zmianie wyboru)
                    {
                        if(newAuditoriumValue.equals(-1))
                        {
                            chosenAuditorium = null;
                            Number fixer = 1;
                            newAuditoriumValue = newAuditoriumValue.intValue() + fixer.intValue();
                            observableValue.removeListener(this); //Usuniecie nasluchu powoduje zaprzestanie obserwacji listy - pozwala to nam na ewentualna zmiane sali bez narazania sie na wyjatki spowodowane nieaktualna lista.
                        }
                        chosenAuditorium = auditoriumList.get(newAuditoriumValue.intValue()); //Przypisanie obiektu o ID takim, jaki jest w liscie do obiektu wyboru.
                    }
                });        
            }
        });
        
        for(Auditorium a : preselectedAuditoriumList)
        {
            preselectedAuditoriumDataList.add(a.getAuditoriumNumber() + ", Typ " + a.getAuditoriumType());
            if(currentAuditorium.getAuditoriumNumber() == a.getAuditoriumNumber() && currentAuditorium.getAuditoriumType().equals(a.getAuditoriumType()))
            {
                chosenAuditorium = currentAuditorium;
                chosenAuditoriumID = preselectedAuditoriumDataList.size() - 1;
            }
        }
        
        preselectedAuditoriumBox.getItems().addAll(preselectedAuditoriumDataList); //Wypelnienie Choice Boxa danymi z bazy danych.
        preselectedAuditoriumBox.getSelectionModel().select(preselectedAuditoriumDataList.get(chosenAuditoriumID)); //wybranie aktualnej sali.
        
        preselectedAuditoriumBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() //Nasluch wybranej rzeczy z Choice Boxa
        { 
            public void changed(ObservableValue observableValue, Number auditoriumValue, Number newAuditoriumValue) //observableValue - wybrana rzecz, value - stary indeks, newValue - nowy indeks (przy ewentualnej zmianie wyboru)
            {
                if(newAuditoriumValue.equals(-1))
                {
                    chosenAuditorium = null;
                    Number fixer = 1;
                    newAuditoriumValue = newAuditoriumValue.intValue() + fixer.intValue();
                    observableValue.removeListener(this); //Usuniecie nasluchu powoduje zaprzestanie obserwacji listy - pozwala to nam na ewentualna zmiane sali bez narazania sie na wyjatki spowodowane nieaktualna lista.
                }
                chosenAuditorium = preselectedAuditoriumList.get(newAuditoriumValue.intValue()); //Przypisanie obiektu o ID takim, jaki jest w liscie do obiektu wyboru.
            }
        });

        for(Play p : playList)
        {
            playDataList.add(p.getName() + ", " + p.getGenre());
            if(currentPlay.getName().equals(p.getName()) && currentPlay.getGenre().equals(p.getGenre()))
            {
                chosenPlay = currentPlay;
                chosenPlayID = playDataList.size() - 1;
            }
        }
        playBox.getItems().addAll(playDataList); //Wypelnienie Choice Boxa danymi z bazy danych.
        playBox.getSelectionModel().select(playDataList.get(chosenPlayID)); //wybranie aktualnej sali.
        playBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() //Nasluch wybranej rzeczy z Choice Boxa
        { 
            public void changed(ObservableValue observableValue, Number playValue, Number newPlayValue) //observableValue - wybrana rzecz, value - stary indeks, newValue - nowy indeks (przy ewentualnej zmianie wyboru)
            {
                playValue = chosenPlayID;
                if(newPlayValue.equals(-1))
                {
                    chosenPlay = null;
                    Number fixer = 1;
                    newPlayValue = newPlayValue.intValue() + fixer.intValue();
                    observableValue.removeListener(this); //Usuniecie nasluchu powoduje zaprzestanie obserwacji listy - pozwala to nam na ewentualna zmiane sztuki bez narazania sie na wyjatki spowodowane nieaktualna lista.
                }
                chosenPlay = playList.get(newPlayValue.intValue());
                
            }
        });  
        priceField.setPromptText("Cena");
        dateField.setPromptText("Data - DD/MM/YYYY, DD-MM-YYYY, DD.MM.YYYY");
        startHourField.setPromptText("Godzina Rozpoczęcia - HH:MM");
        lengthField.setPromptText("Czas trwania sztuki (w minutach)");
        
    }
    
    //Pull teatrow.
    public void dataHarvester() throws SQLException, PropertyVetoException 
    {
        try
        {
            connectionValidation = connector.testConnection();
            if(connectionValidation == true)
            {
                System.out.println("DB Connected!");
                theatreList = connector.pullTheatreList();
                playList = connector.pullPlayList();
                preselectedAuditoriumList = connector.pullAuditoriumList(currentTheatre); //Pull listy sal zgodnie z wybranym teatrem
            }
            else
            {
                throw new SQLException();
            }  
        }
        catch(HibernateException | SQLException e) 
        {
            System.out.println("DB Connection could not been established!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.setHeaderText("Problem!");
            alert.setContentText("Nie można pobrać danych z bazy! \nSpróbuj ponownie później." );
            alert.showAndWait();
        }
    }

    //Logika, czy guzik OK zostanie klikniety.
    public boolean isOkClicked() 
    {
        return okClicked;
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
    private boolean validator() throws SQLException, ClassNotFoundException, PropertyVetoException, ParseException 
    {        
        String errorMessage = ""; //Pusty string, do ktorego beda dodawane poszczegolne errory.
        Boolean isConnectionAvailable = false; //Boolean przetrzymujacy informacje, czy jest mozliwe nawiazanie polaczenia z baza - domyslnie ustawiony jako false.
        Boolean validatedDate = false;
        Boolean validatedHour = false;
        Boolean validatedLength = false;
        
        LocalDate givenDate = null;
        
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
            if (chosenTheatre == null) 
            {
                errorMessage += "Nie wybrano teatru!" + "\n";
            }
            
            if (chosenAuditorium == null || (preselectedAuditoriumBox.getSelectionModel().isEmpty() && auditoriumBox.getSelectionModel().isEmpty())) //Test wynikajacy z user story - wybierzemy teatr i sale, a potem zmienimy teatr (a sali juz nie).
            {
                errorMessage += "Nie wybrano sali!" + "\n";
            }

            if (chosenPlay == null) 
            {
                errorMessage += "Nie wybrano sztuki!" + "\n"; 
            }
            
            if(priceField.getText() == null || priceField.getText().length() == 0)
            {
                errorMessage += "Nie podano ceny biletu!" + "\n";
            }
            
            if(startHourField.getText() == null || startHourField.getText().length() == 0)
            {
                errorMessage += "Nie podano godziny rozpoczęcia seansu!" + "\n";
            }
            
            if(lengthField.getText() == null || lengthField.getText().length() == 0)
            {
                errorMessage += "Nie podano długości seansu!" + "\n";
            }
            
            //2. Alerty do zle wypelnionych pol:
            else
            { 
                //Wszystkie mozliwosci podania ceny.
                //Patterny
                Pattern numbersOnly = Pattern.compile("^[0-9]*$");
                Pattern moneyWithDotAndMaxTwoDecimalPlaces = Pattern.compile("^[0-9]+.[0-9]{1,2}$");
                Pattern moneyWithCommaAndMaxTwoDecimalPlaces = Pattern.compile("^[0-9]+,[0-9]{1,2}$");
                //Matchery
                Matcher matchNumbersOnly = numbersOnly.matcher(priceField.getText());
                Matcher matchMoneyWithDotAndMaxTwoDecimalPlaces = moneyWithDotAndMaxTwoDecimalPlaces.matcher(priceField.getText());
                Matcher matchMoneyWithCommaAndMaxTwoDecimalPlaces = moneyWithCommaAndMaxTwoDecimalPlaces.matcher(priceField.getText());
                //Logika
                Boolean numbersOnlyValidator = matchNumbersOnly.matches();
                Boolean moneyWithDotAndMaxTwoDecimalPlacesValidator = matchMoneyWithDotAndMaxTwoDecimalPlaces.matches();
                Boolean moneyWithCommaAndMaxTwoDecimalPlacesValidator = matchMoneyWithCommaAndMaxTwoDecimalPlaces.matches();
                
                //2.1 Sprawdzenie, czy cena zostala podana poprawnie.
                if (!(numbersOnlyValidator))
                {
                    if (!moneyWithDotAndMaxTwoDecimalPlacesValidator)
                    {
                        if(!moneyWithCommaAndMaxTwoDecimalPlacesValidator)
                        {
                            errorMessage += "Podano niepoprawną cenę!" + "\n";
                        }
                    }
                }
                
                //Data
                //Przepuszczanie zapisu dat: dd-mm-yyyy, dd.mm.yyyy, dd/mm/yyyy
                Pattern correctDateInput = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)"
                                                         + "(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\"
                                                         + "3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))"
                                                         + "$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
                Matcher matchCorrectDateInput = correctDateInput.matcher(dateField.getText());
                Boolean correctDateInputValidator = matchCorrectDateInput.matches();
                
                if (!correctDateInputValidator)
                {
                    errorMessage += "Podano niepoprawną datę!" + "\n";
                }
                else
                {   
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String convertedDate = dateField.getText();
                    convertedDate = convertedDate.replace(".", "-");
                    convertedDate = convertedDate.replace("/", "-");
                    LocalDate actualDate = LocalDate.now();
                    actualDate = actualDate.plusDays(1);
                    givenDate = LocalDate.parse(convertedDate, dateTimeFormatter);
                    if(actualDate.isAfter(givenDate))
                    {
                        errorMessage += "Podano przestarzałą datę, lub data jest zbyt bliska aktualnej!" + "\n";
                    }
                    else
                    {
                        validatedDate = true;
                    }
                }
                
                //Godzina
                Pattern correctHourInput = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
                Matcher matchCorrectHourInput = correctHourInput.matcher(startHourField.getText());
                Boolean correctHourInputValidator = matchCorrectHourInput.matches();
                
                if(!correctHourInputValidator)
                {
                    errorMessage += "Podano błędną godzinę!" + "\n";
                }
                else
                {
                    validatedHour = true;
                    if(startHourField.getText().length() == 4)
                    {
                        correctHour = "0" + startHourField.getText();
                    }
                    else
                    {
                        correctHour = startHourField.getText();
                    }
                }
                
                Matcher matchLength = numbersOnly.matcher(lengthField.getText());
                Boolean lengthValidator = matchLength.matches();
                if(!lengthValidator)
                {
                    errorMessage += "Długość seansu musi składać się tylko z cyfr!" + "\n";
                }
                else
                {
                    validatedLength = true;
                }
            }
            
            /*Wazne! Miedzy seansami musi zostac zapewniona przerwa 30 minut na sprzatniecie sali / zmiane dekoracji itp!*/
            
            //Test, czy nie ma kolizji w sali.
            if(validatedDate == true && validatedHour == true && validatedLength && !(preselectedAuditoriumBox.getSelectionModel().isEmpty() && auditoriumBox.getSelectionModel().isEmpty()))
            {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                List<Show> showList = connector.pullSpecificDateShowList(givenDate, chosenAuditorium);
                String stringedDate = givenDate.toString();
                stringedDate += " " + correctHour;
                LocalDateTime givenDateAndHour = LocalDateTime.parse(stringedDate, dateTimeFormatter);
                for(Show s : showList)
                {
                    Boolean pulledDateStartIsAfterGivenDateFinish = false;
                    Boolean pulledDateFinishIsBeforeGivenDateStart = false;
                    String pulledData = s.getDate() + " " + s.getStartHour();
                    LocalDateTime pulledDateAndHour = LocalDateTime.parse(pulledData, dateTimeFormatter);
                    
                    givenDateAndHour = givenDateAndHour.plusMinutes(Integer.parseInt(lengthField.getText()) + 30); //do startu dodawany jest czas trwania + 30 min przerwy
                    
                    if(showID != s.getShowId())
                    {          
                        //User Story: modyfikowany seans konczy sie przed pobranym z bazy.
                        if(givenDateAndHour.isBefore(pulledDateAndHour))
                        {
                            pulledDateStartIsAfterGivenDateFinish = true;
                        }
                            
                        //odjeto czas trwania + 30 minut przerwy (by dojsc do punktu wyjscia) + kolejne 30 min na przerwe.
                        givenDateAndHour = givenDateAndHour.minusMinutes(Integer.parseInt(lengthField.getText()) + 60); 
                        pulledDateAndHour = pulledDateAndHour.plusMinutes(s.getLength()); //dodano czas trwania pobranego seansu.

                        //User Story: pobrany z bazy seans konczy sie przed modyfikowanym.
                        if(pulledDateAndHour.isBefore(givenDateAndHour))
                        {
                            pulledDateFinishIsBeforeGivenDateStart = true;
                        }

                        //jesli zaden User Story nie zostal zrealizowany, to nastepuje kolizja.
                        if(pulledDateStartIsAfterGivenDateFinish == false && pulledDateFinishIsBeforeGivenDateStart == false)
                        {
                            if(!errorMessage.contains("Występuje kolizja z innym seansem!"))
                            {
                                errorMessage += "Występuje kolizja z innym seansem!" + "\n";
                            }
                        }
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
            
            connector.editShow(showID, chosenTheatre, chosenAuditorium, chosenPlay, priceField.getText(), givenDate, correctHour, lengthField.getText());
            
            alert.setContentText("Edycja seansu zakończone powodzeniem!");
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
            alert.setHeaderText("Błąd edycji seansu!");
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
