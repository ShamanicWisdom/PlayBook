/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska tworzenia nowej sredniej sali - AddMediumAuditoriumController.java.
 * 
 */

package szaman.playbook.view.menu.theatre.auditorium;

// Laczenie z reszta wlasnych klas //

import java.beans.PropertyVetoException;
import szaman.playbook.Core;
import szaman.playbook.entity.Theatre;
import szaman.playbook.utility.HibernateConnector;

// Reszta klas //

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Szaman
 */

public class AddMediumAuditoriumController 
{
    private Core mainApp;
    
    private String email = null;
    private String userName = null;
    
    private Theatre chosenTheatre;
        
    List<String> missingSlotList = new ArrayList<>();
    List<Integer> auditoriumNumbers = new ArrayList<>();
    
    HibernateConnector connector = new HibernateConnector();
    
    @FXML
    private GridPane grid;
    
    /********************************RZAD 1************************************/
    
    @FXML 
    final Button A1 = new Button("A1");
    private boolean A1flag = false;
    @FXML 
    final Button A2 = new Button("A2");
    private boolean A2flag = false;
    @FXML 
    final Button A3 = new Button("A3");
    private boolean A3flag = false;
    @FXML 
    final Button A4 = new Button("A4");
    private boolean A4flag = false;
    @FXML 
    final Button A5 = new Button("A5");
    private boolean A5flag = false;
    @FXML 
    final Button A6 = new Button("A6");
    private boolean A6flag = false;
    @FXML 
    final Button A7 = new Button("A7");
    private boolean A7flag = false;
    @FXML 
    final Button A8 = new Button("A8");
    private boolean A8flag = false;
    @FXML 
    final Button A9 = new Button("A9");
    private boolean A9flag = false;
    @FXML 
    final Button A10 = new Button("A10");
    private boolean A10flag = false;
    
    /********************************RZAD 2************************************/
    
    @FXML 
    final Button B1 = new Button("B1");
    private boolean B1flag = false;
    @FXML 
    final Button B2 = new Button("B2");
    private boolean B2flag = false;
    @FXML 
    final Button B3 = new Button("B3");
    private boolean B3flag = false;
    @FXML 
    final Button B4 = new Button("B4");
    private boolean B4flag = false;
    @FXML 
    final Button B5 = new Button("B5");
    private boolean B5flag = false;
    @FXML 
    final Button B6 = new Button("B6");
    private boolean B6flag = false;
    @FXML 
    final Button B7 = new Button("B7");
    private boolean B7flag = false;
    @FXML 
    final Button B8 = new Button("B8");
    private boolean B8flag = false;
    @FXML 
    final Button B9 = new Button("B9");
    private boolean B9flag = false;
    @FXML 
    final Button B10 = new Button("B10");
    private boolean B10flag = false;
    
    /********************************RZAD 3************************************/
    
    @FXML 
    final Button C1 = new Button("C1");
    private boolean C1flag = false;
    @FXML 
    final Button C2 = new Button("C2");
    private boolean C2flag = false;
    @FXML 
    final Button C3 = new Button("C3");
    private boolean C3flag = false;
    @FXML 
    final Button C4 = new Button("C4");
    private boolean C4flag = false;
    @FXML 
    final Button C5 = new Button("C5");
    private boolean C5flag = false;
    @FXML 
    final Button C6 = new Button("C6");
    private boolean C6flag = false;
    @FXML 
    final Button C7 = new Button("C7");
    private boolean C7flag = false;
    @FXML 
    final Button C8 = new Button("C8");
    private boolean C8flag = false;
    @FXML 
    final Button C9 = new Button("C9");
    private boolean C9flag = false;
    @FXML 
    final Button C10 = new Button("C10");
    private boolean C10flag = false;
    
    /********************************RZAD 4************************************/
    
    @FXML 
    final Button D1 = new Button("D1");
    private boolean D1flag = false;
    @FXML 
    final Button D2 = new Button("D2");
    private boolean D2flag = false;
    @FXML 
    final Button D3 = new Button("D3");
    private boolean D3flag = false;
    @FXML 
    final Button D4 = new Button("D4");
    private boolean D4flag = false;
    @FXML 
    final Button D5 = new Button("D5");
    private boolean D5flag = false;
    @FXML 
    final Button D6 = new Button("D6");
    private boolean D6flag = false;
    @FXML 
    final Button D7 = new Button("D7");
    private boolean D7flag = false;
    @FXML 
    final Button D8 = new Button("D8");
    private boolean D8flag = false;
    @FXML 
    final Button D9 = new Button("D9");
    private boolean D9flag = false;
    @FXML 
    final Button D10 = new Button("D10");
    private boolean D10flag = false;
    
    /********************************RZAD 5************************************/
    
    @FXML 
    final Button E1 = new Button("E1");
    private boolean E1flag = false;
    @FXML 
    final Button E2 = new Button("E2");
    private boolean E2flag = false;
    @FXML 
    final Button E3 = new Button("E3");
    private boolean E3flag = false;
    @FXML 
    final Button E4 = new Button("E4");
    private boolean E4flag = false;
    @FXML 
    final Button E5 = new Button("E5");
    private boolean E5flag = false;
    @FXML 
    final Button E6 = new Button("E6");
    private boolean E6flag = false;
    @FXML 
    final Button E7 = new Button("E7");
    private boolean E7flag = false;
    @FXML 
    final Button E8 = new Button("E8");
    private boolean E8flag = false;
    @FXML 
    final Button E9 = new Button("E9");
    private boolean E9flag = false;
    @FXML 
    final Button E10 = new Button("E10");
    private boolean E10flag = false;
    
    /********************************RZAD 6************************************/
    
    @FXML 
    final Button F1 = new Button("F1");
    private boolean F1flag = false;
    @FXML 
    final Button F2 = new Button("F2");
    private boolean F2flag = false;
    @FXML 
    final Button F3 = new Button("F3");
    private boolean F3flag = false;
    @FXML 
    final Button F4 = new Button("F4");
    private boolean F4flag = false;
    @FXML 
    final Button F5 = new Button("F5");
    private boolean F5flag = false;
    @FXML 
    final Button F6 = new Button("F6");
    private boolean F6flag = false;
    @FXML 
    final Button F7 = new Button("F7");
    private boolean F7flag = false;
    @FXML 
    final Button F8 = new Button("F8");
    private boolean F8flag = false;
    @FXML 
    final Button F9 = new Button("F9");
    private boolean F9flag = false;
    @FXML 
    final Button F10 = new Button("F10");
    private boolean F10flag = false;
    
    /********************************RZAD 7************************************/
    
    @FXML 
    final Button G1 = new Button("G1");
    private boolean G1flag = false;
    @FXML 
    final Button G2 = new Button("G2");
    private boolean G2flag = false;
    @FXML 
    final Button G3 = new Button("G3");
    private boolean G3flag = false;
    @FXML 
    final Button G4 = new Button("G4");
    private boolean G4flag = false;
    @FXML 
    final Button G5 = new Button("G5");
    private boolean G5flag = false;
    @FXML 
    final Button G6 = new Button("G6");
    private boolean G6flag = false;
    @FXML 
    final Button G7 = new Button("G7");
    private boolean G7flag = false;
    @FXML 
    final Button G8 = new Button("G8");
    private boolean G8flag = false;
    @FXML 
    final Button G9 = new Button("G9");
    private boolean G9flag = false;
    @FXML 
    final Button G10 = new Button("G10");
    private boolean G10flag = false;
    
    /********************************RZAD 8************************************/
    
    @FXML 
    final Button H1 = new Button("H1");
    private boolean H1flag = false;
    @FXML 
    final Button H2 = new Button("H2");
    private boolean H2flag = false;
    @FXML 
    final Button H3 = new Button("H3");
    private boolean H3flag = false;
    @FXML 
    final Button H4 = new Button("H4");
    private boolean H4flag = false;
    @FXML 
    final Button H5 = new Button("H5");
    private boolean H5flag = false;
    @FXML 
    final Button H6 = new Button("H6");
    private boolean H6flag = false;
    @FXML 
    final Button H7 = new Button("H7");
    private boolean H7flag = false;
    @FXML 
    final Button H8 = new Button("H8");
    private boolean H8flag = false;
    @FXML 
    final Button H9 = new Button("H9");
    private boolean H9flag = false;
    @FXML 
    final Button H10 = new Button("H10");
    private boolean H10flag = false;
        
    /**************************************************************************/    
    
    public void setMainApp(Core mainApp, String email, String userName, Theatre chosenTheatre, List<Integer> auditoriumNumbers) throws SQLException, ClassNotFoundException 
    {
        this.mainApp = mainApp;
        this.email = email;
        this.userName = userName;
        this.chosenTheatre = chosenTheatre;
        this.auditoriumNumbers = auditoriumNumbers;
        
    /********************************RZAD 1************************************/
        
        grid.add(A1, 0,0);
        A1.setPrefWidth(60);
        A1.setPrefHeight(50);
        A1.setStyle("-fx-background-color: #a9a9a9");
        A1.setOnAction((ActionEvent e) -> 
        {
            if(A1flag == false) 
            {
                missingSlotList.add("A1");
                A1.setStyle("-fx-background-color: #FF0000");
                A1flag = true;
            }
            else
            {
                missingSlotList.remove("A1");
                A1.setStyle("-fx-background-color: #a9a9a9");
                A1flag = false;
            }
        });
        
        grid.add(A2, 1,0);
        A2.setPrefWidth(60);
        A2.setPrefHeight(50);
        A2.setStyle("-fx-background-color: #a9a9a9");
        A2.setOnAction((ActionEvent e) -> 
        {
            if(A2flag == false) 
            {
                missingSlotList.add("A2");
                A2.setStyle("-fx-background-color: #FF0000");
                A2flag = true;
            }
            else
            {
                missingSlotList.remove("A2");
                A2.setStyle("-fx-background-color: #a9a9a9");
                A2flag = false;
            }
        });
        
        grid.add(A3, 2,0);
        A3.setPrefWidth(60);
        A3.setPrefHeight(50);
        A3.setStyle("-fx-background-color: #a9a9a9");
        A3.setOnAction((ActionEvent e) -> 
        {
            if(A3flag == false) 
            {
                missingSlotList.add("A3");
                A3.setStyle("-fx-background-color: #FF0000");
                A3flag = true;
            }
            else
            {
                missingSlotList.remove("A3");
                A3.setStyle("-fx-background-color: #a9a9a9");
                A3flag = false;
            }
        });
        
        grid.add(A4, 3,0);
        A4.setPrefWidth(60);
        A4.setPrefHeight(50);
        A4.setStyle("-fx-background-color: #a9a9a9");
        A4.setOnAction((ActionEvent e) -> 
        {
            if(A4flag == false) 
            {
                missingSlotList.add("A4");
                A4.setStyle("-fx-background-color: #FF0000");
                A4flag = true;
            }
            else
            {
                missingSlotList.remove("A4");
                A4.setStyle("-fx-background-color: #a9a9a9");
                A4flag = false;
            }
        });
        
        grid.add(A5, 4,0);
        A5.setPrefWidth(60);
        A5.setPrefHeight(50);
        A5.setStyle("-fx-background-color: #a9a9a9");
        A5.setOnAction((ActionEvent e) -> 
        {
            if(A5flag == false) 
            {
                missingSlotList.add("A5");
                A5.setStyle("-fx-background-color: #FF0000");
                A5flag = true;
            }
            else
            {
                missingSlotList.remove("A5");
                A5.setStyle("-fx-background-color: #a9a9a9");
                A5flag = false;
            }
        });
        
        grid.add(A6, 5,0);
        A6.setPrefWidth(60);
        A6.setPrefHeight(50);
        A6.setStyle("-fx-background-color: #a9a9a9");
        A6.setOnAction((ActionEvent e) -> 
        {
            if(A6flag == false) 
            {
                missingSlotList.add("A6");
                A6.setStyle("-fx-background-color: #FF0000");
                A6flag = true;
            }
            else
            {
                missingSlotList.remove("A6");
                A6.setStyle("-fx-background-color: #a9a9a9");
                A6flag = false;
            }
        });
        
        grid.add(A7, 6,0);
        A7.setPrefWidth(60);
        A7.setPrefHeight(50);
        A7.setStyle("-fx-background-color: #a9a9a9");
        A7.setOnAction((ActionEvent e) -> 
        {
            if(A7flag == false) 
            {
                missingSlotList.add("A7");
                A7.setStyle("-fx-background-color: #FF0000");
                A7flag = true;
            }
            else
            {
                missingSlotList.remove("A7");
                A7.setStyle("-fx-background-color: #a9a9a9");
                A7flag = false;
            }
        });
        
        grid.add(A8, 7,0);
        A8.setPrefWidth(60);
        A8.setPrefHeight(50);
        A8.setStyle("-fx-background-color: #a9a9a9");
        A8.setOnAction((ActionEvent e) -> 
        {
            if(A8flag == false) 
            {
                missingSlotList.add("A8");
                A8.setStyle("-fx-background-color: #FF0000");
                A8flag = true;
            }
            else
            {
                missingSlotList.remove("A8");
                A8.setStyle("-fx-background-color: #a9a9a9");
                A8flag = false;
            }
        });
        
        grid.add(A9, 8,0);
        A9.setPrefWidth(60);
        A9.setPrefHeight(50);
        A9.setStyle("-fx-background-color: #a9a9a9");
        A9.setOnAction((ActionEvent e) -> 
        {
            if(A9flag == false) 
            {
                missingSlotList.add("A9");
                A9.setStyle("-fx-background-color: #FF0000");
                A9flag = true;
            }
            else
            {
                missingSlotList.remove("A9");
                A9.setStyle("-fx-background-color: #a9a9a9");
                A9flag = false;
            }
        });
        
        grid.add(A10, 9,0);
        A10.setPrefWidth(60);
        A10.setPrefHeight(50);
        A10.setStyle("-fx-background-color: #a9a9a9");
        A10.setOnAction((ActionEvent e) -> 
        {
            if(A10flag == false) 
            {
                missingSlotList.add("A10");
                A10.setStyle("-fx-background-color: #FF0000");
                A10flag = true;
            }
            else
            {
                missingSlotList.remove("A10");
                A10.setStyle("-fx-background-color: #a9a9a9");
                A10flag = false;
            }
        });
        
    /********************************RZAD 2************************************/
        
        grid.add(B1, 0,1);
        B1.setPrefWidth(60);
        B1.setPrefHeight(50);
        B1.setStyle("-fx-background-color: #a9a9a9");
        B1.setOnAction((ActionEvent e) -> 
        {
            if(B1flag == false) 
            {
                missingSlotList.add("B1");
                B1.setStyle("-fx-background-color: #FF0000");
                B1flag = true;
            }
            else
            {
                missingSlotList.remove("B1");
                B1.setStyle("-fx-background-color: #a9a9a9");
                B1flag = false;
            }
        });
        
        grid.add(B2, 1,1);
        B2.setPrefWidth(60);
        B2.setPrefHeight(50);
        B2.setStyle("-fx-background-color: #a9a9a9");
        B2.setOnAction((ActionEvent e) -> 
        {
            if(B2flag == false) 
            {
                missingSlotList.add("B2");
                B2.setStyle("-fx-background-color: #FF0000");
                B2flag = true;
            }
            else
            {
                missingSlotList.remove("B2");
                B2.setStyle("-fx-background-color: #a9a9a9");
                B2flag = false;
            }
        });
        
        grid.add(B3, 2,1);
        B3.setPrefWidth(60);
        B3.setPrefHeight(50);
        B3.setStyle("-fx-background-color: #a9a9a9");
        B3.setOnAction((ActionEvent e) -> 
        {
            if(B3flag == false) 
            {
                missingSlotList.add("B3");
                B3.setStyle("-fx-background-color: #FF0000");
                B3flag = true;
            }
            else
            {
                missingSlotList.remove("B3");
                B3.setStyle("-fx-background-color: #a9a9a9");
                B3flag = false;
            }
        });
        
        grid.add(B4, 3,1);
        B4.setPrefWidth(60);
        B4.setPrefHeight(50);
        B4.setStyle("-fx-background-color: #a9a9a9");
        B4.setOnAction((ActionEvent e) -> 
        {
            if(B4flag == false) 
            {
                missingSlotList.add("B4");
                B4.setStyle("-fx-background-color: #FF0000");
                B4flag = true;
            }
            else
            {
                missingSlotList.remove("B4");
                B4.setStyle("-fx-background-color: #a9a9a9");
                B4flag = false;
            }
        });
        
        grid.add(B5, 4,1);
        B5.setPrefWidth(60);
        B5.setPrefHeight(50);
        B5.setStyle("-fx-background-color: #a9a9a9");
        B5.setOnAction((ActionEvent e) -> 
        {
            if(B5flag == false) 
            {
                missingSlotList.add("B5");
                B5.setStyle("-fx-background-color: #FF0000");
                B5flag = true;
            }
            else
            {
                missingSlotList.remove("B5");
                B5.setStyle("-fx-background-color: #a9a9a9");
                B5flag = false;
            }
        });
        
        grid.add(B6, 5,1);
        B6.setPrefWidth(60);
        B6.setPrefHeight(50);
        B6.setStyle("-fx-background-color: #a9a9a9");
        B6.setOnAction((ActionEvent e) -> 
        {
            if(B6flag == false) 
            {
                missingSlotList.add("B6");
                B6.setStyle("-fx-background-color: #FF0000");
                B6flag = true;
            }
            else
            {
                missingSlotList.remove("B6");
                B6.setStyle("-fx-background-color: #a9a9a9");
                B6flag = false;
            }
        });
        
        grid.add(B7, 6,1);
        B7.setPrefWidth(60);
        B7.setPrefHeight(50);
        B7.setStyle("-fx-background-color: #a9a9a9");
        B7.setOnAction((ActionEvent e) -> 
        {
            if(B7flag == false) 
            {
                missingSlotList.add("B7");
                B7.setStyle("-fx-background-color: #FF0000");
                B7flag = true;
            }
            else
            {
                missingSlotList.remove("B7");
                B7.setStyle("-fx-background-color: #a9a9a9");
                B7flag = false;
            }
        });
        
        grid.add(B8, 7,1);
        B8.setPrefWidth(60);
        B8.setPrefHeight(50);
        B8.setStyle("-fx-background-color: #a9a9a9");
        B8.setOnAction((ActionEvent e) -> 
        {
            if(B8flag == false) 
            {
                missingSlotList.add("B8");
                B8.setStyle("-fx-background-color: #FF0000");
                B8flag = true;
            }
            else
            {
                missingSlotList.remove("B8");
                B8.setStyle("-fx-background-color: #a9a9a9");
                B8flag = false;
            }
        });
        
        grid.add(B9, 8,1);
        B9.setPrefWidth(60);
        B9.setPrefHeight(50);
        B9.setStyle("-fx-background-color: #a9a9a9");
        B9.setOnAction((ActionEvent e) -> 
        {
            if(B9flag == false) 
            {
                missingSlotList.add("B9");
                B9.setStyle("-fx-background-color: #FF0000");
                B9flag = true;
            }
            else
            {
                missingSlotList.remove("B9");
                B9.setStyle("-fx-background-color: #a9a9a9");
                B9flag = false;
            }
        });
        
        grid.add(B10, 9,1);
        B10.setPrefWidth(60);
        B10.setPrefHeight(50);
        B10.setStyle("-fx-background-color: #a9a9a9");
        B10.setOnAction((ActionEvent e) -> 
        {
            if(B10flag == false) 
            {
                missingSlotList.add("B10");
                B10.setStyle("-fx-background-color: #FF0000");
                B10flag = true;
            }
            else
            {
                missingSlotList.remove("B10");
                B10.setStyle("-fx-background-color: #a9a9a9");
                B10flag = false;
            }
        });
        
    /********************************RZAD 3************************************/
    
        grid.add(C1, 0,2);
        C1.setPrefWidth(60);
        C1.setPrefHeight(50);
        C1.setStyle("-fx-background-color: #a9a9a9");
        C1.setOnAction((ActionEvent e) -> 
        {
            if(C1flag == false) 
            {
                missingSlotList.add("C1");
                C1.setStyle("-fx-background-color: #FF0000");
                C1flag = true;
            }
            else
            {
                missingSlotList.remove("C1");
                C1.setStyle("-fx-background-color: #a9a9a9");
                C1flag = false;
            }
        });
        
        grid.add(C2, 1,2);
        C2.setPrefWidth(60);
        C2.setPrefHeight(50);
        C2.setStyle("-fx-background-color: #a9a9a9");
        C2.setOnAction((ActionEvent e) -> 
        {
            if(C2flag == false) 
            {
                missingSlotList.add("C2");
                C2.setStyle("-fx-background-color: #FF0000");
                C2flag = true;
            }
            else
            {
                missingSlotList.remove("C2");
                C2.setStyle("-fx-background-color: #a9a9a9");
                C2flag = false;
            }
        });
        
        grid.add(C3, 2,2);
        C3.setPrefWidth(60);
        C3.setPrefHeight(50);
        C3.setStyle("-fx-background-color: #a9a9a9");
        C3.setOnAction((ActionEvent e) -> 
        {
            if(C3flag == false) 
            {
                missingSlotList.add("C3");
                C3.setStyle("-fx-background-color: #FF0000");
                C3flag = true;
            }
            else
            {
                missingSlotList.remove("C3");
                C3.setStyle("-fx-background-color: #a9a9a9");
                C3flag = false;
            }
        });
        
        grid.add(C4, 3,2);
        C4.setPrefWidth(60);
        C4.setPrefHeight(50);
        C4.setStyle("-fx-background-color: #a9a9a9");
        C4.setOnAction((ActionEvent e) -> 
        {
            if(C4flag == false) 
            {
                missingSlotList.add("C4");
                C4.setStyle("-fx-background-color: #FF0000");
                C4flag = true;
            }
            else
            {
                missingSlotList.remove("C4");
                C4.setStyle("-fx-background-color: #a9a9a9");
                C4flag = false;
            }
        });
        
        grid.add(C5, 4,2);
        C5.setPrefWidth(60);
        C5.setPrefHeight(50);
        C5.setStyle("-fx-background-color: #a9a9a9");
        C5.setOnAction((ActionEvent e) -> 
        {
            if(C5flag == false) 
            {
                missingSlotList.add("C5");
                C5.setStyle("-fx-background-color: #FF0000");
                C5flag = true;
            }
            else
            {
                missingSlotList.remove("C5");
                C5.setStyle("-fx-background-color: #a9a9a9");
                C5flag = false;
            }
        });
        
        grid.add(C6, 5,2);
        C6.setPrefWidth(60);
        C6.setPrefHeight(50);
        C6.setStyle("-fx-background-color: #a9a9a9");
        C6.setOnAction((ActionEvent e) -> 
        {
            if(C6flag == false) 
            {
                missingSlotList.add("C6");
                C6.setStyle("-fx-background-color: #FF0000");
                C6flag = true;
            }
            else
            {
                missingSlotList.remove("C6");
                C6.setStyle("-fx-background-color: #a9a9a9");
                C6flag = false;
            }
        });
        
        grid.add(C7, 6,2);
        C7.setPrefWidth(60);
        C7.setPrefHeight(50);
        C7.setStyle("-fx-background-color: #a9a9a9");
        C7.setOnAction((ActionEvent e) ->
        {
            if(C7flag == false) 
            {
                missingSlotList.add("C7");
                C7.setStyle("-fx-background-color: #FF0000");
                C7flag = true;
            }
            else
            {
                missingSlotList.remove("C7");
                C7.setStyle("-fx-background-color: #a9a9a9");
                C7flag = false;
            }
        });
        
        grid.add(C8, 7,2);
        C8.setPrefWidth(60);
        C8.setPrefHeight(50);
        C8.setStyle("-fx-background-color: #a9a9a9");
        C8.setOnAction((ActionEvent e) -> 
        {
            if(C8flag == false) 
            {
                missingSlotList.add("C8");
                C8.setStyle("-fx-background-color: #FF0000");
                C8flag = true;
            }
            else
            {
                missingSlotList.remove("C8");
                C8.setStyle("-fx-background-color: #a9a9a9");
                C8flag = false;
            }
        });
        
        grid.add(C9, 8,2);
        C9.setPrefWidth(60);
        C9.setPrefHeight(50);
        C9.setStyle("-fx-background-color: #a9a9a9");
        C9.setOnAction((ActionEvent e) -> 
        {
            if(C9flag == false) 
            {
                missingSlotList.add("C9");
                C9.setStyle("-fx-background-color: #FF0000");
                C9flag = true;
            }
            else
            {
                missingSlotList.remove("C9");
                C9.setStyle("-fx-background-color: #a9a9a9");
                C9flag = false;
            }
        });
        
        grid.add(C10, 9,2);
        C10.setPrefWidth(60);
        C10.setPrefHeight(50);
        C10.setStyle("-fx-background-color: #a9a9a9");
        C10.setOnAction((ActionEvent e) -> 
        {
            if(C10flag == false) 
            {
                missingSlotList.add("C10");
                C10.setStyle("-fx-background-color: #FF0000");
                C10flag = true;
            }
            else
            {
                missingSlotList.remove("C10");
                C10.setStyle("-fx-background-color: #a9a9a9");
                C10flag = false;
            }
        });
        
        
    /********************************RZAD 4************************************/
    
        grid.add(D1, 0,3);
        D1.setPrefWidth(60);
        D1.setPrefHeight(50);
        D1.setStyle("-fx-background-color: #a9a9a9");
        D1.setOnAction((ActionEvent e) -> 
        {
            if(D1flag == false) 
            {
                missingSlotList.add("D1");
                D1.setStyle("-fx-background-color: #FF0000");
                D1flag = true;
            }
            else
            {
                missingSlotList.remove("D1");
                D1.setStyle("-fx-background-color: #a9a9a9");
                D1flag = false;
            }
        });
        
        grid.add(D2, 1,3);
        D2.setPrefWidth(60);
        D2.setPrefHeight(50);
        D2.setStyle("-fx-background-color: #a9a9a9");
        D2.setOnAction((ActionEvent e) -> 
        {
            if(D2flag == false) 
            {
                missingSlotList.add("D2");
                D2.setStyle("-fx-background-color: #FF0000");
                D2flag = true;
            }
            else
            {
                missingSlotList.remove("D2");
                D2.setStyle("-fx-background-color: #a9a9a9");
                D2flag = false;
            }
        });
        
        grid.add(D3, 2,3);
        D3.setPrefWidth(60);
        D3.setPrefHeight(50);
        D3.setStyle("-fx-background-color: #a9a9a9");
        D3.setOnAction((ActionEvent e) -> 
        {
            if(D3flag == false) 
            {
                missingSlotList.add("D3");
                D3.setStyle("-fx-background-color: #FF0000");
                D3flag = true;
            }
            else
            {
                missingSlotList.remove("D3");
                D3.setStyle("-fx-background-color: #a9a9a9");
                D3flag = false;
            }
        });
        
        grid.add(D4, 3,3);
        D4.setPrefWidth(60);
        D4.setPrefHeight(50);
        D4.setStyle("-fx-background-color: #a9a9a9");
        D4.setOnAction((ActionEvent e) -> {
            if(D4flag == false) 
            {
                missingSlotList.add("D4");
                D4.setStyle("-fx-background-color: #FF0000");
                D4flag = true;
            }
            else
            {
                missingSlotList.remove("D4");
                D4.setStyle("-fx-background-color: #a9a9a9");
                D4flag = false;
            }
        });
        
        grid.add(D5, 4,3);
        D5.setPrefWidth(60);
        D5.setPrefHeight(50);
        D5.setStyle("-fx-background-color: #a9a9a9");
        D5.setOnAction((ActionEvent e) -> 
        {
            if(D5flag == false) 
            {
                missingSlotList.add("D5");
                D5.setStyle("-fx-background-color: #FF0000");
                D5flag = true;
            }
            else
            {
                missingSlotList.remove("D5");
                D5.setStyle("-fx-background-color: #a9a9a9");
                D5flag = false;
            }
        });
        
        grid.add(D6, 5,3);
        D6.setPrefWidth(60);
        D6.setPrefHeight(50);
        D6.setStyle("-fx-background-color: #a9a9a9");
        D6.setOnAction((ActionEvent e) -> 
        {
            if(D6flag == false) 
            {
                missingSlotList.add("D6");
                D6.setStyle("-fx-background-color: #FF0000");
                D6flag = true;
            }
            else
            {
                missingSlotList.remove("D6");
                D6.setStyle("-fx-background-color: #a9a9a9");
                D6flag = false;
            }
        });
        
        grid.add(D7, 6,3);
        D7.setPrefWidth(60);
        D7.setPrefHeight(50);
        D7.setStyle("-fx-background-color: #a9a9a9");
        D7.setOnAction((ActionEvent e) -> 
        {
            if(D7flag == false) 
            {
                missingSlotList.add("D7");
                D7.setStyle("-fx-background-color: #FF0000");
                D7flag = true;
            }
            else
            {
                missingSlotList.remove("D7");
                D7.setStyle("-fx-background-color: #a9a9a9");
                D7flag = false;
            }
        });
        
        grid.add(D8, 7,3);
        D8.setPrefWidth(60);
        D8.setPrefHeight(50);
        D8.setStyle("-fx-background-color: #a9a9a9");
        D8.setOnAction((ActionEvent e) -> 
        {
            if(D8flag == false) 
            {
                missingSlotList.add("D8");
                D8.setStyle("-fx-background-color: #FF0000");
                D8flag = true;
            }
            else
            {
                missingSlotList.remove("D8");
                D8.setStyle("-fx-background-color: #a9a9a9");
                D8flag = false;
            }
        });
        
        grid.add(D9, 8,3);
        D9.setPrefWidth(60);
        D9.setPrefHeight(50);
        D9.setStyle("-fx-background-color: #a9a9a9");
        D9.setOnAction((ActionEvent e) -> 
        {
            if(D9flag == false) 
            {
                missingSlotList.add("D9");
                D9.setStyle("-fx-background-color: #FF0000");
                D9flag = true;
            }
            else
            {
                missingSlotList.remove("D9");
                D9.setStyle("-fx-background-color: #a9a9a9");
                D9flag = false;
            }
        });
        
        grid.add(D10, 9,3);
        D10.setPrefWidth(60);
        D10.setPrefHeight(50);
        D10.setStyle("-fx-background-color: #a9a9a9");
        D10.setOnAction((ActionEvent e) -> 
        {
            if(D10flag == false) 
            {
                missingSlotList.add("D10");
                D10.setStyle("-fx-background-color: #FF0000");
                D10flag = true;
            }
            else
            {
                missingSlotList.remove("D10");
                D10.setStyle("-fx-background-color: #a9a9a9");
                D10flag = false;
            }
        });
        
    /********************************RZAD 5************************************/
    
        grid.add(E1, 0,4);
        E1.setPrefWidth(60);
        E1.setPrefHeight(50);
        E1.setStyle("-fx-background-color: #a9a9a9");
        E1.setOnAction((ActionEvent e) -> 
        {
            if(E1flag == false) 
            {
                missingSlotList.add("E1");
                E1.setStyle("-fx-background-color: #FF0000");
                E1flag = true;
            }
            else
            {
                missingSlotList.remove("E1");
                E1.setStyle("-fx-background-color: #a9a9a9");
                E1flag = false;
            }
        });
        
        grid.add(E2, 1,4);
        E2.setPrefWidth(60);
        E2.setPrefHeight(50);
        E2.setStyle("-fx-background-color: #a9a9a9");
        E2.setOnAction((ActionEvent e) -> 
        {
            if(E2flag == false) 
            {
                missingSlotList.add("E2");
                E2.setStyle("-fx-background-color: #FF0000");
                E2flag = true;
            }
            else
            {
                missingSlotList.remove("E2");
                E2.setStyle("-fx-background-color: #a9a9a9");
                E2flag = false;
            }
        });
        
        grid.add(E3, 2,4);
        E3.setPrefWidth(60);
        E3.setPrefHeight(50);
        E3.setStyle("-fx-background-color: #a9a9a9");
        E3.setOnAction((ActionEvent e) -> 
        {
            if(E3flag == false) 
            {
                missingSlotList.add("E3");
                E3.setStyle("-fx-background-color: #FF0000");
                E3flag = true;
            }
            else
            {
                missingSlotList.remove("E3");
                E3.setStyle("-fx-background-color: #a9a9a9");
                E3flag = false;
            }
        });
        
        grid.add(E4, 3,4);
        E4.setPrefWidth(60);
        E4.setPrefHeight(50);
        E4.setStyle("-fx-background-color: #a9a9a9");
        E4.setOnAction((ActionEvent e) -> 
        {
            if(E4flag == false) 
            {
                missingSlotList.add("E4");
                E4.setStyle("-fx-background-color: #FF0000");
                E4flag = true;
            }
            else
            {
                missingSlotList.remove("E4");
                E4.setStyle("-fx-background-color: #a9a9a9");
                E4flag = false;
            }
        });
        
        grid.add(E5, 4,4);
        E5.setPrefWidth(60);
        E5.setPrefHeight(50);
        E5.setStyle("-fx-background-color: #a9a9a9");
        E5.setOnAction((ActionEvent e) -> 
        {
            if(E5flag == false) 
            {
                missingSlotList.add("E5");
                E5.setStyle("-fx-background-color: #FF0000");
                E5flag = true;
            }
            else
            {
                missingSlotList.remove("E5");
                E5.setStyle("-fx-background-color: #a9a9a9");
                E5flag = false;
            }
        });
        
        grid.add(E6, 5,4);
        E6.setPrefWidth(60);
        E6.setPrefHeight(50);
        E6.setStyle("-fx-background-color: #a9a9a9");
        E6.setOnAction((ActionEvent e) -> 
        {
            if(E6flag == false) 
            {
                missingSlotList.add("E6");
                E6.setStyle("-fx-background-color: #FF0000");
                E6flag = true;
            }
            else
            {
                missingSlotList.remove("E6");
                E6.setStyle("-fx-background-color: #a9a9a9");
                E6flag = false;
            }
        });
        
        grid.add(E7, 6,4);
        E7.setPrefWidth(60);
        E7.setPrefHeight(50);
        E7.setStyle("-fx-background-color: #a9a9a9");
        E7.setOnAction((ActionEvent e) -> 
        {
            if(E7flag == false) 
            {
                missingSlotList.add("E7");
                E7.setStyle("-fx-background-color: #FF0000");
                E7flag = true;
            }
            else
            {
                missingSlotList.remove("E7");
                E7.setStyle("-fx-background-color: #a9a9a9");
                E7flag = false;
            }
        });
        
        grid.add(E8, 7,4);
        E8.setPrefWidth(60);
        E8.setPrefHeight(50);
        E8.setStyle("-fx-background-color: #a9a9a9");
        E8.setOnAction((ActionEvent e) -> 
        {
            if(E8flag == false) 
            {
                missingSlotList.add("E8");
                E8.setStyle("-fx-background-color: #FF0000");
                E8flag = true;
            }
            else
            {
                missingSlotList.remove("E8");
                E8.setStyle("-fx-background-color: #a9a9a9");
                E8flag = false;
            }
        });
        
        grid.add(E9, 8,4);
        E9.setPrefWidth(60);
        E9.setPrefHeight(50);
        E9.setStyle("-fx-background-color: #a9a9a9");
        E9.setOnAction((ActionEvent e) -> 
        {
            if(E9flag == false) 
            {
                missingSlotList.add("E9");
                E9.setStyle("-fx-background-color: #FF0000");
                E9flag = true;
            }
            else
            {
                missingSlotList.remove("E9");
                E9.setStyle("-fx-background-color: #a9a9a9");
                E9flag = false;
            }
        });
        
        grid.add(E10, 9,4);
        E10.setPrefWidth(60);
        E10.setPrefHeight(50);
        E10.setStyle("-fx-background-color: #a9a9a9");
        E10.setOnAction((ActionEvent e) -> 
        {
            if(E10flag == false) 
            {
                missingSlotList.add("E10");
                E10.setStyle("-fx-background-color: #FF0000");
                E10flag = true;
            }
            else
            {
                missingSlotList.remove("E10");
                E10.setStyle("-fx-background-color: #a9a9a9");
                E10flag = false;
            }
        });
        
    /********************************RZAD 6************************************/
    
        grid.add(F1, 0,5);
        F1.setPrefWidth(60);
        F1.setPrefHeight(50);
        F1.setStyle("-fx-background-color: #a9a9a9");
        F1.setOnAction((ActionEvent e) -> 
        {
            if(F1flag == false) 
            {
                missingSlotList.add("F1");
                F1.setStyle("-fx-background-color: #FF0000");
                F1flag = true;
            }
            else
            {
                missingSlotList.remove("F1");
                F1.setStyle("-fx-background-color: #a9a9a9");
                F1flag = false;
            }
        });
        
        grid.add(F2, 1,5);
        F2.setPrefWidth(60);
        F2.setPrefHeight(50);
        F2.setStyle("-fx-background-color: #a9a9a9");
        F2.setOnAction((ActionEvent e) -> 
        {
            if(F2flag == false) 
            {
                missingSlotList.add("F2");
                F2.setStyle("-fx-background-color: #FF0000");
                F2flag = true;
            }
            else
            {
                missingSlotList.remove("F2");
                F2.setStyle("-fx-background-color: #a9a9a9");
                F2flag = false;
            }
        });
        
        grid.add(F3, 2,5);
        F3.setPrefWidth(60);
        F3.setPrefHeight(50);
        F3.setStyle("-fx-background-color: #a9a9a9");
        F3.setOnAction((ActionEvent e) -> 
        {
            if(F3flag == false) 
            {
                missingSlotList.add("F3");
                F3.setStyle("-fx-background-color: #FF0000");
                F3flag = true;
            }
            else
            {
                missingSlotList.remove("F3");
                F3.setStyle("-fx-background-color: #a9a9a9");
                F3flag = false;
            }
        });
        
        grid.add(F4, 3,5);
        F4.setPrefWidth(60);
        F4.setPrefHeight(50);
        F4.setStyle("-fx-background-color: #a9a9a9");
        F4.setOnAction((ActionEvent e) -> 
        {
            if(F4flag == false) 
            {
                missingSlotList.add("F4");
                F4.setStyle("-fx-background-color: #FF0000");
                F4flag = true;
            }
            else
            {
                missingSlotList.remove("F4");
                F4.setStyle("-fx-background-color: #a9a9a9");
                F4flag = false;
            }
        });
        
        grid.add(F5, 4,5);
        F5.setPrefWidth(60);
        F5.setPrefHeight(50);
        F5.setStyle("-fx-background-color: #a9a9a9");
        F5.setOnAction((ActionEvent e) -> 
        {
            if(F5flag == false) 
            {
                missingSlotList.add("F5");
                F5.setStyle("-fx-background-color: #FF0000");
                F5flag = true;
            }
            else
            {
                missingSlotList.remove("F5");
                F5.setStyle("-fx-background-color: #a9a9a9");
                F5flag = false;
            }
        });
        
        grid.add(F6, 5,5);
        F6.setPrefWidth(60);
        F6.setPrefHeight(50);
        F6.setStyle("-fx-background-color: #a9a9a9");
        F6.setOnAction((ActionEvent e) ->
        {
            if(F6flag == false) 
            {
                missingSlotList.add("F6");
                F6.setStyle("-fx-background-color: #FF0000");
                F6flag = true;
            }
            else
            {
                missingSlotList.remove("F6");
                F6.setStyle("-fx-background-color: #a9a9a9");
                F6flag = false;
            }
        });
        
        grid.add(F7, 6,5);
        F7.setPrefWidth(60);
        F7.setPrefHeight(50);
        F7.setStyle("-fx-background-color: #a9a9a9");
        F7.setOnAction((ActionEvent e) -> 
        {
            if(F7flag == false) 
            {
                missingSlotList.add("F7");
                F7.setStyle("-fx-background-color: #FF0000");
                F7flag = true;
            }
            else
            {
                missingSlotList.remove("F7");
                F7.setStyle("-fx-background-color: #a9a9a9");
                F7flag = false;
            }
        });
        
        grid.add(F8, 7,5);
        F8.setPrefWidth(60);
        F8.setPrefHeight(50);
        F8.setStyle("-fx-background-color: #a9a9a9");
        F8.setOnAction((ActionEvent e) -> 
        {
            if(F8flag == false) 
            {
                missingSlotList.add("F8");
                F8.setStyle("-fx-background-color: #FF0000");
                F8flag = true;
            }
            else
            {
                missingSlotList.remove("F8");
                F8.setStyle("-fx-background-color: #a9a9a9");
                F8flag = false;
            }
        });
        
        grid.add(F9, 8,5);
        F9.setPrefWidth(60);
        F9.setPrefHeight(50);
        F9.setStyle("-fx-background-color: #a9a9a9");
        F9.setOnAction((ActionEvent e) -> 
        {
            if(F9flag == false) 
            {
                missingSlotList.add("F9");
                F9.setStyle("-fx-background-color: #FF0000");
                F9flag = true;
            }
            else
            {
                missingSlotList.remove("F9");
                F9.setStyle("-fx-background-color: #a9a9a9");
                F9flag = false;
            }
        });
        
        grid.add(F10, 9,5);
        F10.setPrefWidth(60);
        F10.setPrefHeight(50);
        F10.setStyle("-fx-background-color: #a9a9a9");
        F10.setOnAction((ActionEvent e) -> 
        {
            if(F10flag == false) 
            {
                missingSlotList.add("F10");
                F10.setStyle("-fx-background-color: #FF0000");
                F10flag = true;
            }
            else
            {
                missingSlotList.remove("F10");
                F10.setStyle("-fx-background-color: #a9a9a9");
                F10flag = false;
            }
        });
        
    /********************************RZAD 7************************************/
    
        grid.add(G1, 0,6);
        G1.setPrefWidth(60);
        G1.setPrefHeight(50);
        G1.setStyle("-fx-background-color: #a9a9a9");
        G1.setOnAction((ActionEvent e) -> 
        {
            if(G1flag == false) 
            {
                missingSlotList.add("G1");
                G1.setStyle("-fx-background-color: #FF0000");
                G1flag = true;
            }
            else
            {
                missingSlotList.remove("G1");
                G1.setStyle("-fx-background-color: #a9a9a9");
                G1flag = false;
            }
        });
        
        grid.add(G2, 1,6);
        G2.setPrefWidth(60);
        G2.setPrefHeight(50);
        G2.setStyle("-fx-background-color: #a9a9a9");
        G2.setOnAction((ActionEvent e) -> 
        {
            if(G2flag == false) 
            {
                missingSlotList.add("G2");
                G2.setStyle("-fx-background-color: #FF0000");
                G2flag = true;
            }
            else
            {
                missingSlotList.remove("G2");
                G2.setStyle("-fx-background-color: #a9a9a9");
                G2flag = false;
            }
        });
        
        grid.add(G3, 2,6);
        G3.setPrefWidth(60);
        G3.setPrefHeight(50);
        G3.setStyle("-fx-background-color: #a9a9a9");
        G3.setOnAction((ActionEvent e) -> 
        {
            if(G3flag == false) 
            {
                missingSlotList.add("G3");
                G3.setStyle("-fx-background-color: #FF0000");
                G3flag = true;
            }
            else
            {
                missingSlotList.remove("G3");
                G3.setStyle("-fx-background-color: #a9a9a9");
                G3flag = false;
            }
        });
        
        grid.add(G4, 3,6);
        G4.setPrefWidth(60);
        G4.setPrefHeight(50);
        G4.setStyle("-fx-background-color: #a9a9a9");
        G4.setOnAction((ActionEvent e) -> 
        {
            if(G4flag == false) 
            {
                missingSlotList.add("G4");
                G4.setStyle("-fx-background-color: #FF0000");
                G4flag = true;
            }
            else
            {
                missingSlotList.remove("G4");
                G4.setStyle("-fx-background-color: #a9a9a9");
                G4flag = false;
            }
        });
        
        grid.add(G5, 4,6);
        G5.setPrefWidth(60);
        G5.setPrefHeight(50);
        G5.setStyle("-fx-background-color: #a9a9a9");
        G5.setOnAction((ActionEvent e) -> 
        {
            if(G5flag == false) 
            {
                missingSlotList.add("G5");
                G5.setStyle("-fx-background-color: #FF0000");
                G5flag = true;
            }
            else
            {
                missingSlotList.remove("G5");
                G5.setStyle("-fx-background-color: #a9a9a9");
                G5flag = false;
            }
        });
        
        grid.add(G6, 5,6);
        G6.setPrefWidth(60);
        G6.setPrefHeight(50);
        G6.setStyle("-fx-background-color: #a9a9a9");
        G6.setOnAction((ActionEvent e) -> 
        {
            if(G6flag == false) 
            {
                missingSlotList.add("G6");
                G6.setStyle("-fx-background-color: #FF0000");
                G6flag = true;
            }
            else
            {
                missingSlotList.remove("G6");
                G6.setStyle("-fx-background-color: #a9a9a9");
                G6flag = false;
            }
        });
        
        grid.add(G7, 6,6);
        G7.setPrefWidth(60);
        G7.setPrefHeight(50);
        G7.setStyle("-fx-background-color: #a9a9a9");
        G7.setOnAction((ActionEvent e) -> 
        {
            if(G7flag == false) 
            {
                missingSlotList.add("G7");
                G7.setStyle("-fx-background-color: #FF0000");
                G7flag = true;
            }
            else
            {
                missingSlotList.remove("G7");
                G7.setStyle("-fx-background-color: #a9a9a9");
                G7flag = false;
            }
        });
        
        grid.add(G8, 7,6);
        G8.setPrefWidth(60);
        G8.setPrefHeight(50);
        G8.setStyle("-fx-background-color: #a9a9a9");
        G8.setOnAction((ActionEvent e) -> 
        {
            if(G8flag == false) 
            {
                missingSlotList.add("G8");
                G8.setStyle("-fx-background-color: #FF0000");
                G8flag = true;
            }
            else
            {
                missingSlotList.remove("G8");
                G8.setStyle("-fx-background-color: #a9a9a9");
                G8flag = false;
            }
        });
        
        grid.add(G9, 8,6);
        G9.setPrefWidth(60);
        G9.setPrefHeight(50);
        G9.setStyle("-fx-background-color: #a9a9a9");
        G9.setOnAction((ActionEvent e) ->
        {
            if(G9flag == false) 
            {
                missingSlotList.add("G9");
                G9.setStyle("-fx-background-color: #FF0000");
                G9flag = true;
            }
            else
            {
                missingSlotList.remove("G9");
                G9.setStyle("-fx-background-color: #a9a9a9");
                G9flag = false;
            }
        });
        
        grid.add(G10, 9,6);
        G10.setPrefWidth(60);
        G10.setPrefHeight(50);
        G10.setStyle("-fx-background-color: #a9a9a9");
        G10.setOnAction((ActionEvent e) -> 
        {
            if(G10flag == false) 
            {
                missingSlotList.add("G10");
                G10.setStyle("-fx-background-color: #FF0000");
                G10flag = true;
            }
            else
            {
                missingSlotList.remove("G10");
                G10.setStyle("-fx-background-color: #a9a9a9");
                G10flag = false;
            }
        });
        
    /********************************RZAD 8************************************/
    
        grid.add(H1, 0,7);
        H1.setPrefWidth(60);
        H1.setPrefHeight(50);
        H1.setStyle("-fx-background-color: #a9a9a9");
        H1.setOnAction((ActionEvent e) -> 
        {
            if(H1flag == false) 
            {
                missingSlotList.add("H1");
                H1.setStyle("-fx-background-color: #FF0000");
                H1flag = true;
            }
            else
            {
                missingSlotList.remove("H1");
                H1.setStyle("-fx-background-color: #a9a9a9");
                H1flag = false;
            }
        });
        
        grid.add(H2, 1,7);
        H2.setPrefWidth(60);
        H2.setPrefHeight(50);
        H2.setStyle("-fx-background-color: #a9a9a9");
        H2.setOnAction((ActionEvent e) -> 
        {
            if(H2flag == false) 
            {
                missingSlotList.add("H2");
                H2.setStyle("-fx-background-color: #FF0000");
                H2flag = true;
            }
            else
            {
                missingSlotList.remove("H2");
                H2.setStyle("-fx-background-color: #a9a9a9");
                H2flag = false;
            }
        });
        
        grid.add(H3, 2,7);
        H3.setPrefWidth(60);
        H3.setPrefHeight(50);
        H3.setStyle("-fx-background-color: #a9a9a9");
        H3.setOnAction((ActionEvent e) -> 
        {
            if(H3flag == false) 
            {
                missingSlotList.add("H3");
                H3.setStyle("-fx-background-color: #FF0000");
                H3flag = true;
            }
            else
            {
                missingSlotList.remove("H3");
                H3.setStyle("-fx-background-color: #a9a9a9");
                H3flag = false;
            }
        });
        
        grid.add(H4, 3,7);
        H4.setPrefWidth(60);
        H4.setPrefHeight(50);
        H4.setStyle("-fx-background-color: #a9a9a9");
        H4.setOnAction((ActionEvent e) -> 
        {
            if(H4flag == false) 
            {
                missingSlotList.add("H4");
                H4.setStyle("-fx-background-color: #FF0000");
                H4flag = true;
            }
            else
            {
                missingSlotList.remove("H4");
                H4.setStyle("-fx-background-color: #a9a9a9");
                H4flag = false;
            }
        });
        
        grid.add(H5, 4,7);
        H5.setPrefWidth(60);
        H5.setPrefHeight(50);
        H5.setStyle("-fx-background-color: #a9a9a9");
        H5.setOnAction((ActionEvent e) -> 
        {
            if(H5flag == false) 
            {
                missingSlotList.add("H5");
                H5.setStyle("-fx-background-color: #FF0000");
                H5flag = true;
            }
            else
            {
                missingSlotList.remove("H5");
                H5.setStyle("-fx-background-color: #a9a9a9");
                H5flag = false;
            }
        });
        
        grid.add(H6, 5,7);
        H6.setPrefWidth(60);
        H6.setPrefHeight(50);
        H6.setStyle("-fx-background-color: #a9a9a9");
        H6.setOnAction((ActionEvent e) ->
        {
            if(H6flag == false) 
            {
                missingSlotList.add("H6");
                H6.setStyle("-fx-background-color: #FF0000");
                H6flag = true;
            }
            else
            {
                missingSlotList.remove("H6");
                H6.setStyle("-fx-background-color: #a9a9a9");
                H6flag = false;
            }
        });
        
        grid.add(H7, 6,7);
        H7.setPrefWidth(60);
        H7.setPrefHeight(50);
        H7.setStyle("-fx-background-color: #a9a9a9");
        H7.setOnAction((ActionEvent e) ->
        {
            if(H7flag == false) 
            {
                missingSlotList.add("H7");
                H7.setStyle("-fx-background-color: #FF0000");
                H7flag = true;
            }
            else
            {
                missingSlotList.remove("H7");
                H7.setStyle("-fx-background-color: #a9a9a9");
                H7flag = false;
            }
        });
        
        grid.add(H8, 7,7);
        H8.setPrefWidth(60);
        H8.setPrefHeight(50);
        H8.setStyle("-fx-background-color: #a9a9a9");
        H8.setOnAction((ActionEvent e) ->
        {
            if(H8flag == false) 
            {
                missingSlotList.add("H8");
                H8.setStyle("-fx-background-color: #FF0000");
                H8flag = true;
            }
            else
            {
                missingSlotList.remove("H8");
                H8.setStyle("-fx-background-color: #a9a9a9");
                H8flag = false;
            }
        });
        
        grid.add(H9, 8,7);
        H9.setPrefWidth(60);
        H9.setPrefHeight(50);
        H9.setStyle("-fx-background-color: #a9a9a9");
        H9.setOnAction((ActionEvent e) -> 
        {
            if(H9flag == false) 
            {
                missingSlotList.add("H9");
                H9.setStyle("-fx-background-color: #FF0000");
                H9flag = true;
            }
            else
            {
                missingSlotList.remove("H9");
                H9.setStyle("-fx-background-color: #a9a9a9");
                H9flag = false;
            }
        });
        
        grid.add(H10, 9,7);
        H10.setPrefWidth(60);
        H10.setPrefHeight(50);
        H10.setStyle("-fx-background-color: #a9a9a9");
        H10.setOnAction((ActionEvent e) -> 
        {
            if(H10flag == false) 
            {
                missingSlotList.add("H10");
                H10.setStyle("-fx-background-color: #FF0000");
                H10flag = true;
            }
            else
            {
                missingSlotList.remove("H10");
                H10.setStyle("-fx-background-color: #a9a9a9");
                H10flag = false;
            }
        });
    }
    
/*************************************************************************************************************************************/
    
    //Skrypt dla guzika Ok.
    @FXML
    private void handleOk(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        Boolean operationFinished = false;
        String missingSlots = "";
        for(int i = 0; i < missingSlotList.size(); i++) //Zapis wszystkich brakujacych miejsc do jednego stringa przekazywanego do bazy.
        {
            if(i == missingSlotList.size() - 1)
            {
                missingSlots += missingSlotList.get(i);
            }
            else
            {
                missingSlots += missingSlotList.get(i) + ", ";
            }
        }
        ButtonType okButton = new ButtonType("Tak", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", okButton, cancelButton);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
        alert.setTitle("Dodanie nowej sali");
        alert.setHeaderText(null);
        alert.setContentText("Na pewno chcesz dodać taką salę?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == okButton)
        {
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.close();
            
            int auditoriumNumber;
            for(auditoriumNumber = 1; auditoriumNumber <= auditoriumNumbers.size(); auditoriumNumber++) //Szukanie pierwszego wolnego numeru sali, ktory nie wystepuje w liscie.
            {
                if(!auditoriumNumbers.contains(auditoriumNumber))
                {
                    break;
                }
            }
            int highestNumber = 0;
            for(int i = 0; i < auditoriumNumbers.size(); i ++)
            {
                if(auditoriumNumbers.get(i) > highestNumber)
                {
                    highestNumber = auditoriumNumbers.get(i);
                }
            }
            if(auditoriumNumber == highestNumber)
            {
                auditoriumNumber = highestNumber + 1;
            }
            
            System.out.println("New Auditorium Number: " + auditoriumNumber);
            
            connector.addAuditorium(chosenTheatre.getTheatreId(), auditoriumNumber, (80 - missingSlotList.size()), missingSlots, "Medium");
            confirmationAlert.setTitle("Sukces!");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Dodanie sali zakończone powodzeniem! ");
            confirmationAlert.showAndWait();
            operationFinished = true;
        } 
        else 
        {
            System.out.println("AddMediumAuditoriumCancel detected!"); //kontrola, czy dziala interakcja.
            alert.close();
        }
        if(operationFinished == true)
        {
            mainApp.showAuditoriumDatabase(email, userName, chosenTheatre);
        }
    }
    
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("AddMediumAuditoriumBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showAuditoriumDatabase(email, userName, chosenTheatre);
    }
}
