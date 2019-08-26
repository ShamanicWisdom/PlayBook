/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska wybierania typu dodawanej sali - AuditoriumChooserController.java.
 * 
 */

package szaman.playbook.view.menu.theatre.auditorium;

// Laczenie z reszta wlasnych klas //



// Reszta klas // 

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.stage.Stage;
/**
 *
 * @author Szaman
 */

public class AuditoriumChooserController 
{
    
    private Stage dialogStage;
    
    List<String> voivodeshipList = new ArrayList<String>();
    
    String response = null;
    
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

    //Zwrot wyboru usera.
    public String userResponse() 
    {
        return response;
    }
    
    //Skrypt dla guzika Small.
    @FXML
    private void handleSmall() 
    {
        response = "Small";
        dialogStage.close();
    }
    
    //Skrypt dla guzika Medium.
    @FXML
    private void handleMedium() 
    {
        response = "Medium";
        dialogStage.close();
    }
    
    //Skrypt dla guzika Big.
    @FXML
    private void handleBig() 
    {
        response = "Big";
        dialogStage.close();
    }
    
    //Skrypt dla guzika Cancel.
    @FXML
    private void handleCancel() 
    {
        response = "Cancel";
        dialogStage.close();
    }
}
