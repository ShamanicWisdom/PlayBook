/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska wyswietlania duzej sali - ShowBigAuditoriumController.java.
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
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Szaman
 */

public class ShowBigAuditoriumController 
{
    private Core mainApp;
    
    private String email = null;
    private String userName = null;
    
    private Theatre chosenTheatre;
        
    List<String> missingSlotList = new ArrayList<>();
    
    HibernateConnector connector = new HibernateConnector();
    
    @FXML
    private GridPane grid;
    
    @FXML
    private Label auditoriumInfoLabel;
    
    /********************************RZAD 1************************************/
    
    @FXML 
    final Button A1 = new Button("A1");
    @FXML 
    final Button A2 = new Button("A2");
    @FXML 
    final Button A3 = new Button("A3");
    @FXML 
    final Button A4 = new Button("A4");
    @FXML 
    final Button A5 = new Button("A5");
    @FXML 
    final Button A6 = new Button("A6");
    @FXML 
    final Button A7 = new Button("A7");
    @FXML 
    final Button A8 = new Button("A8");
    @FXML 
    final Button A9 = new Button("A9");
    @FXML 
    final Button A10 = new Button("A10");
    
    /********************************RZAD 2************************************/
    
    @FXML 
    final Button B1 = new Button("B1");
    @FXML 
    final Button B2 = new Button("B2");
    @FXML 
    final Button B3 = new Button("B3");
    @FXML 
    final Button B4 = new Button("B4");
    @FXML 
    final Button B5 = new Button("B5");
    @FXML 
    final Button B6 = new Button("B6");
    @FXML 
    final Button B7 = new Button("B7");
    @FXML 
    final Button B8 = new Button("B8");
    @FXML 
    final Button B9 = new Button("B9");
    @FXML 
    final Button B10 = new Button("B10");
    
    /********************************RZAD 3************************************/
    
    @FXML 
    final Button C1 = new Button("C1");
    @FXML 
    final Button C2 = new Button("C2");
    @FXML 
    final Button C3 = new Button("C3");
    @FXML 
    final Button C4 = new Button("C4");
    @FXML 
    final Button C5 = new Button("C5");
    @FXML 
    final Button C6 = new Button("C6");
    @FXML 
    final Button C7 = new Button("C7");
    @FXML 
    final Button C8 = new Button("C8");
    @FXML 
    final Button C9 = new Button("C9");
    @FXML 
    final Button C10 = new Button("C10");
    
    /********************************RZAD 4************************************/
    
    @FXML 
    final Button D1 = new Button("D1");
    @FXML 
    final Button D2 = new Button("D2");
    @FXML 
    final Button D3 = new Button("D3");
    @FXML 
    final Button D4 = new Button("D4");
    @FXML 
    final Button D5 = new Button("D5");
    @FXML 
    final Button D6 = new Button("D6");
    @FXML 
    final Button D7 = new Button("D7");
    @FXML 
    final Button D8 = new Button("D8");
    @FXML 
    final Button D9 = new Button("D9");
    @FXML 
    final Button D10 = new Button("D10");
    
    /********************************RZAD 5************************************/
    
    @FXML 
    final Button E1 = new Button("E1");
    @FXML 
    final Button E2 = new Button("E2");
    @FXML 
    final Button E3 = new Button("E3");
    @FXML 
    final Button E4 = new Button("E4");
    @FXML 
    final Button E5 = new Button("E5");
    @FXML 
    final Button E6 = new Button("E6");
    @FXML 
    final Button E7 = new Button("E7");
    @FXML 
    final Button E8 = new Button("E8");
    @FXML 
    final Button E9 = new Button("E9");
    @FXML 
    final Button E10 = new Button("E10");
    
    /********************************RZAD 6************************************/
    
    @FXML 
    final Button F1 = new Button("F1");
    @FXML 
    final Button F2 = new Button("F2");
    @FXML 
    final Button F3 = new Button("F3");
    @FXML 
    final Button F4 = new Button("F4");
    @FXML 
    final Button F5 = new Button("F5");
    @FXML 
    final Button F6 = new Button("F6");
    @FXML 
    final Button F7 = new Button("F7");
    @FXML 
    final Button F8 = new Button("F8");
    @FXML 
    final Button F9 = new Button("F9");
    @FXML 
    final Button F10 = new Button("F10");
    
    /********************************RZAD 7************************************/
    
    @FXML 
    final Button G1 = new Button("G1");
    @FXML 
    final Button G2 = new Button("G2");
    @FXML 
    final Button G3 = new Button("G3");
    @FXML 
    final Button G4 = new Button("G4");
    @FXML 
    final Button G5 = new Button("G5");
    @FXML 
    final Button G6 = new Button("G6");
    @FXML 
    final Button G7 = new Button("G7");
    @FXML 
    final Button G8 = new Button("G8");
    @FXML 
    final Button G9 = new Button("G9");
    @FXML 
    final Button G10 = new Button("G10");
    
    /********************************RZAD 8************************************/
    
    @FXML 
    final Button H1 = new Button("H1");
    @FXML 
    final Button H2 = new Button("H2");
    @FXML 
    final Button H3 = new Button("H3");
    @FXML 
    final Button H4 = new Button("H4");
    @FXML 
    final Button H5 = new Button("H5");
    @FXML 
    final Button H6 = new Button("H6");
    @FXML 
    final Button H7 = new Button("H7");
    @FXML 
    final Button H8 = new Button("H8");
    @FXML 
    final Button H9 = new Button("H9");
    @FXML 
    final Button H10 = new Button("H10");
    
    /********************************RZAD 9************************************/
    
    @FXML 
    final Button I1 = new Button("I1");
    @FXML 
    final Button I2 = new Button("I2");
    @FXML 
    final Button I3 = new Button("I3");
    @FXML 
    final Button I4 = new Button("I4");
    @FXML 
    final Button I5 = new Button("I5");
    @FXML 
    final Button I6 = new Button("I6");
    @FXML 
    final Button I7 = new Button("I7");
    @FXML 
    final Button I8 = new Button("I8");
    @FXML 
    final Button I9 = new Button("I9");
    @FXML 
    final Button I10 = new Button("I10");
    
    /*******************************RZAD 10************************************/
    
    @FXML 
    final Button J1 = new Button("J1");
    @FXML 
    final Button J2 = new Button("J2");
    @FXML 
    final Button J3 = new Button("J3");
    @FXML 
    final Button J4 = new Button("J4");
    @FXML 
    final Button J5 = new Button("J5");
    @FXML 
    final Button J6 = new Button("J6");
    @FXML 
    final Button J7 = new Button("J7");
    @FXML 
    final Button J8 = new Button("J8");
    @FXML 
    final Button J9 = new Button("J9");
    @FXML 
    final Button J10 = new Button("J10");
        
    /**************************************************************************/    
    
    public void setMainApp(Core mainApp, String email, String userName, Theatre chosenTheatre, String missingSlots, String auditoriumInfo) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        this.mainApp = mainApp;
        this.email = email;
        this.userName = userName;
        this.chosenTheatre = chosenTheatre;
        missingSlotList = Arrays.asList(missingSlots.split(",\\s*"));
        
        auditoriumInfoLabel.setText("Wizualizacja sali " + auditoriumInfo + " (Typ Big)");
        
        System.out.println("Missing Slots: " + missingSlotList);
        
    /********************************RZAD 1************************************/
        
        if(!missingSlotList.contains("A1"))
        {
            grid.add(A1, 0,0);
            A1.setPrefWidth(60);
            A1.setPrefHeight(50);
            A1.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("A2"))
        {
            grid.add(A2, 1,0);
            A2.setPrefWidth(60);
            A2.setPrefHeight(50);
            A2.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("A3"))
        {
            grid.add(A3, 2,0);
            A3.setPrefWidth(60);
            A3.setPrefHeight(50);
            A3.setStyle("-fx-background-color: #a9a9a9");
        }
                
        if(!missingSlotList.contains("A4"))
        {
            grid.add(A4, 3,0);
            A4.setPrefWidth(60);
            A4.setPrefHeight(50);
            A4.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("A5"))
        {
            grid.add(A5, 4,0);
            A5.setPrefWidth(60);
            A5.setPrefHeight(50);
            A5.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("A6"))
        {
            grid.add(A6, 5,0);
            A6.setPrefWidth(60);
            A6.setPrefHeight(50);
            A6.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("A7"))
        {
            grid.add(A7, 6,0);
            A7.setPrefWidth(60);
            A7.setPrefHeight(50);
            A7.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("A8"))
        {
            grid.add(A8, 7,0);
            A8.setPrefWidth(60);
            A8.setPrefHeight(50);
            A8.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("A9"))
        {
            grid.add(A9, 8,0);
            A9.setPrefWidth(60);
            A9.setPrefHeight(50);
            A9.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("A10"))
        {
            grid.add(A10, 9,0);
            A10.setPrefWidth(60);
            A10.setPrefHeight(50);
            A10.setStyle("-fx-background-color: #a9a9a9");
        }
        
    /********************************RZAD 2************************************/
        
        if(!missingSlotList.contains("B1"))
        {
            grid.add(B1, 0,1);
            B1.setPrefWidth(60);
            B1.setPrefHeight(50);
            B1.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("B2"))
        {
            grid.add(B2, 1,1);
            B2.setPrefWidth(60);
            B2.setPrefHeight(50);
            B2.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("B3"))
        {
            grid.add(B3, 2,1);
            B3.setPrefWidth(60);
            B3.setPrefHeight(50);
            B3.setStyle("-fx-background-color: #a9a9a9");
        }
                
        if(!missingSlotList.contains("B4"))
        {
            grid.add(B4, 3,1);
            B4.setPrefWidth(60);
            B4.setPrefHeight(50);
            B4.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("B5"))
        {
            grid.add(B5, 4,1);
            B5.setPrefWidth(60);
            B5.setPrefHeight(50);
            B5.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("B6"))
        {
            grid.add(B6, 5,1);
            B6.setPrefWidth(60);
            B6.setPrefHeight(50);
            B6.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("B7"))
        {
            grid.add(B7, 6,1);
            B7.setPrefWidth(60);
            B7.setPrefHeight(50);
            B7.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("B8"))
        {
            grid.add(B8, 7,1);
            B8.setPrefWidth(60);
            B8.setPrefHeight(50);
            B8.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("B9"))
        {
            grid.add(B9, 8,1);
            B9.setPrefWidth(60);
            B9.setPrefHeight(50);
            B9.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("B10"))
        {
            grid.add(B10, 9,1);
            B10.setPrefWidth(60);
            B10.setPrefHeight(50);
            B10.setStyle("-fx-background-color: #a9a9a9");
        }
        
    /********************************RZAD 3************************************/
        
        if(!missingSlotList.contains("C1"))
        {
            grid.add(C1, 0,2);
            C1.setPrefWidth(60);
            C1.setPrefHeight(50);
            C1.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("C2"))
        {
            grid.add(C2, 1,2);
            C2.setPrefWidth(60);
            C2.setPrefHeight(50);
            C2.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("C3"))
        {
            grid.add(C3, 2,2);
            C3.setPrefWidth(60);
            C3.setPrefHeight(50);
            C3.setStyle("-fx-background-color: #a9a9a9");
        }
                
        if(!missingSlotList.contains("C4"))
        {
            grid.add(C4, 3,2);
            C4.setPrefWidth(60);
            C4.setPrefHeight(50);
            C4.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("C5"))
        {
            grid.add(C5, 4,2);
            C5.setPrefWidth(60);
            C5.setPrefHeight(50);
            C5.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("C6"))
        {
            grid.add(C6, 5,2);
            C6.setPrefWidth(60);
            C6.setPrefHeight(50);
            C6.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("C7"))
        {
            grid.add(C7, 6,2);
            C7.setPrefWidth(60);
            C7.setPrefHeight(50);
            C7.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("C8"))
        {
            grid.add(C8, 7,2);
            C8.setPrefWidth(60);
            C8.setPrefHeight(50);
            C8.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("C9"))
        {
            grid.add(C9, 8,2);
            C9.setPrefWidth(60);
            C9.setPrefHeight(50);
            C9.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("C10"))
        {
            grid.add(C10, 9,2);
            C10.setPrefWidth(60);
            C10.setPrefHeight(50);
            C10.setStyle("-fx-background-color: #a9a9a9");
        }
        
    /********************************RZAD 4************************************/
        
        if(!missingSlotList.contains("D1"))
        {
            grid.add(D1, 0,3);
            D1.setPrefWidth(60);
            D1.setPrefHeight(50);
            D1.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("D2"))
        {
            grid.add(D2, 1,3);
            D2.setPrefWidth(60);
            D2.setPrefHeight(50);
            D2.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("D3"))
        {
            grid.add(D3, 2,3);
            D3.setPrefWidth(60);
            D3.setPrefHeight(50);
            D3.setStyle("-fx-background-color: #a9a9a9");
        }
                
        if(!missingSlotList.contains("D4"))
        {
            grid.add(D4, 3,3);
            D4.setPrefWidth(60);
            D4.setPrefHeight(50);
            D4.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("D5"))
        {
            grid.add(D5, 4,3);
            D5.setPrefWidth(60);
            D5.setPrefHeight(50);
            D5.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("D6"))
        {
            grid.add(D6, 5,3);
            D6.setPrefWidth(60);
            D6.setPrefHeight(50);
            D6.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("D7"))
        {
            grid.add(D7, 6,3);
            D7.setPrefWidth(60);
            D7.setPrefHeight(50);
            D7.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("D8"))
        {
            grid.add(D8, 7,3);
            D8.setPrefWidth(60);
            D8.setPrefHeight(50);
            D8.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("D9"))
        {
            grid.add(D9, 8,3);
            D9.setPrefWidth(60);
            D9.setPrefHeight(50);
            D9.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("D10"))
        {
            grid.add(D10, 9,3);
            D10.setPrefWidth(60);
            D10.setPrefHeight(50);
            D10.setStyle("-fx-background-color: #a9a9a9");
        }
        
    /********************************RZAD 5************************************/
        
        if(!missingSlotList.contains("E1"))
        {
            grid.add(E1, 0,4);
            E1.setPrefWidth(60);
            E1.setPrefHeight(50);
            E1.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("E2"))
        {
            grid.add(E2, 1,4);
            E2.setPrefWidth(60);
            E2.setPrefHeight(50);
            E2.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("E3"))
        {
            grid.add(E3, 2,4);
            E3.setPrefWidth(60);
            E3.setPrefHeight(50);
            E3.setStyle("-fx-background-color: #a9a9a9");
        }
                
        if(!missingSlotList.contains("E4"))
        {
            grid.add(E4, 3,4);
            E4.setPrefWidth(60);
            E4.setPrefHeight(50);
            E4.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("E5"))
        {
            grid.add(E5, 4,4);
            E5.setPrefWidth(60);
            E5.setPrefHeight(50);
            E5.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("E6"))
        {
            grid.add(E6, 5,4);
            E6.setPrefWidth(60);
            E6.setPrefHeight(50);
            E6.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("E7"))
        {
            grid.add(E7, 6,4);
            E7.setPrefWidth(60);
            E7.setPrefHeight(50);
            E7.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("E8"))
        {
            grid.add(E8, 7,4);
            E8.setPrefWidth(60);
            E8.setPrefHeight(50);
            E8.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("E9"))
        {
            grid.add(E9, 8,4);
            E9.setPrefWidth(60);
            E9.setPrefHeight(50);
            E9.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("E10"))
        {
            grid.add(E10, 9,4);
            E10.setPrefWidth(60);
            E10.setPrefHeight(50);
            E10.setStyle("-fx-background-color: #a9a9a9");
        }
        
    /********************************RZAD 6************************************/
        
        if(!missingSlotList.contains("F1"))
        {
            grid.add(F1, 0,5);
            F1.setPrefWidth(60);
            F1.setPrefHeight(50);
            F1.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("F2"))
        {
            grid.add(F2, 1,5);
            F2.setPrefWidth(60);
            F2.setPrefHeight(50);
            F2.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("F3"))
        {
            grid.add(F3, 2,5);
            F3.setPrefWidth(60);
            F3.setPrefHeight(50);
            F3.setStyle("-fx-background-color: #a9a9a9");
        }
                
        if(!missingSlotList.contains("F4"))
        {
            grid.add(F4, 3,5);
            F4.setPrefWidth(60);
            F4.setPrefHeight(50);
            F4.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("F5"))
        {
            grid.add(F5, 4,5);
            F5.setPrefWidth(60);
            F5.setPrefHeight(50);
            F5.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("F6"))
        {
            grid.add(F6, 5,5);
            F6.setPrefWidth(60);
            F6.setPrefHeight(50);
            F6.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("F7"))
        {
            grid.add(F7, 6,5);
            F7.setPrefWidth(60);
            F7.setPrefHeight(50);
            F7.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("F8"))
        {
            grid.add(F8, 7,5);
            F8.setPrefWidth(60);
            F8.setPrefHeight(50);
            F8.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("F9"))
        {
            grid.add(F9, 8,5);
            F9.setPrefWidth(60);
            F9.setPrefHeight(50);
            F9.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("F10"))
        {
            grid.add(F10, 9,5);
            F10.setPrefWidth(60);
            F10.setPrefHeight(50);
            F10.setStyle("-fx-background-color: #a9a9a9");
        }
        
    /********************************RZAD 7************************************/
        
        if(!missingSlotList.contains("G1"))
        {
            grid.add(G1, 0,6);
            G1.setPrefWidth(60);
            G1.setPrefHeight(50);
            G1.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("G2"))
        {
            grid.add(G2, 1,6);
            G2.setPrefWidth(60);
            G2.setPrefHeight(50);
            G2.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("G3"))
        {
            grid.add(G3, 2,6);
            G3.setPrefWidth(60);
            G3.setPrefHeight(50);
            G3.setStyle("-fx-background-color: #a9a9a9");
        }
                
        if(!missingSlotList.contains("G4"))
        {
            grid.add(G4, 3,6);
            G4.setPrefWidth(60);
            G4.setPrefHeight(50);
            G4.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("G5"))
        {
            grid.add(G5, 4,6);
            G5.setPrefWidth(60);
            G5.setPrefHeight(50);
            G5.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("G6"))
        {
            grid.add(G6, 5,6);
            G6.setPrefWidth(60);
            G6.setPrefHeight(50);
            G6.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("G7"))
        {
            grid.add(G7, 6,6);
            G7.setPrefWidth(60);
            G7.setPrefHeight(50);
            G7.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("G8"))
        {
            grid.add(G8, 7,6);
            G8.setPrefWidth(60);
            G8.setPrefHeight(50);
            G8.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("G9"))
        {
            grid.add(G9, 8,6);
            G9.setPrefWidth(60);
            G9.setPrefHeight(50);
            G9.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("G10"))
        {
            grid.add(G10, 9,6);
            G10.setPrefWidth(60);
            G10.setPrefHeight(50);
            G10.setStyle("-fx-background-color: #a9a9a9");
        }
        
    /********************************RZAD 8************************************/
        
        if(!missingSlotList.contains("H1"))
        {
            grid.add(H1, 0,7);
            H1.setPrefWidth(60);
            H1.setPrefHeight(50);
            H1.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("H2"))
        {
            grid.add(H2, 1,7);
            H2.setPrefWidth(60);
            H2.setPrefHeight(50);
            H2.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("H3"))
        {
            grid.add(H3, 2,7);
            H3.setPrefWidth(60);
            H3.setPrefHeight(50);
            H3.setStyle("-fx-background-color: #a9a9a9");
        }
                
        if(!missingSlotList.contains("H4"))
        {
            grid.add(H4, 3,7);
            H4.setPrefWidth(60);
            H4.setPrefHeight(50);
            H4.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("H5"))
        {
            grid.add(H5, 4,7);
            H5.setPrefWidth(60);
            H5.setPrefHeight(50);
            H5.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("H6"))
        {
            grid.add(H6, 5,7);
            H6.setPrefWidth(60);
            H6.setPrefHeight(50);
            H6.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("H7"))
        {
            grid.add(H7, 6,7);
            H7.setPrefWidth(60);
            H7.setPrefHeight(50);
            H7.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("H8"))
        {
            grid.add(H8, 7,7);
            H8.setPrefWidth(60);
            H8.setPrefHeight(50);
            H8.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("H9"))
        {
            grid.add(H9, 8,7);
            H9.setPrefWidth(60);
            H9.setPrefHeight(50);
            H9.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("H10"))
        {
            grid.add(H10, 9,7);
            H10.setPrefWidth(60);
            H10.setPrefHeight(50);
            H10.setStyle("-fx-background-color: #a9a9a9");
        }
        
    /********************************RZAD 9************************************/
        
        if(!missingSlotList.contains("I1"))
        {
            grid.add(I1, 0,8);
            I1.setPrefWidth(60);
            I1.setPrefHeight(50);
            I1.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("I2"))
        {
            grid.add(I2, 1,8);
            I2.setPrefWidth(60);
            I2.setPrefHeight(50);
            I2.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("I3"))
        {
            grid.add(I3, 2,8);
            I3.setPrefWidth(60);
            I3.setPrefHeight(50);
            I3.setStyle("-fx-background-color: #a9a9a9");
        }
                
        if(!missingSlotList.contains("I4"))
        {
            grid.add(I4, 3,8);
            I4.setPrefWidth(60);
            I4.setPrefHeight(50);
            I4.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("I5"))
        {
            grid.add(I5, 4,8);
            I5.setPrefWidth(60);
            I5.setPrefHeight(50);
            I5.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("I6"))
        {
            grid.add(I6, 5,8);
            I6.setPrefWidth(60);
            I6.setPrefHeight(50);
            I6.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("I7"))
        {
            grid.add(I7, 6,8);
            I7.setPrefWidth(60);
            I7.setPrefHeight(50);
            I7.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("I8"))
        {
            grid.add(I8, 7,8);
            I8.setPrefWidth(60);
            I8.setPrefHeight(50);
            I8.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("I9"))
        {
            grid.add(I9, 8,8);
            I9.setPrefWidth(60);
            I9.setPrefHeight(50);
            I9.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("I10"))
        {
            grid.add(I10, 9,8);
            I10.setPrefWidth(60);
            I10.setPrefHeight(50);
            I10.setStyle("-fx-background-color: #a9a9a9");
        }
        
    /********************************RZAD 10***********************************/
        
        if(!missingSlotList.contains("J1"))
        {
            grid.add(J1, 0,9);
            J1.setPrefWidth(60);
            J1.setPrefHeight(50);
            J1.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("J2"))
        {
            grid.add(J2, 1,9);
            J2.setPrefWidth(60);
            J2.setPrefHeight(50);
            J2.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("J3"))
        {
            grid.add(J3, 2,9);
            J3.setPrefWidth(60);
            J3.setPrefHeight(50);
            J3.setStyle("-fx-background-color: #a9a9a9");
        }
                
        if(!missingSlotList.contains("J4"))
        {
            grid.add(J4, 3,9);
            J4.setPrefWidth(60);
            J4.setPrefHeight(50);
            J4.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("J5"))
        {
            grid.add(J5, 4,9);
            J5.setPrefWidth(60);
            J5.setPrefHeight(50);
            J5.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("J6"))
        {
            grid.add(J6, 5,9);
            J6.setPrefWidth(60);
            J6.setPrefHeight(50);
            J6.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("J7"))
        {
            grid.add(J7, 6,9);
            J7.setPrefWidth(60);
            J7.setPrefHeight(50);
            J7.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("J8"))
        {
            grid.add(J8, 7,9);
            J8.setPrefWidth(60);
            J8.setPrefHeight(50);
            J8.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("J9"))
        {
            grid.add(J9, 8,9);
            J9.setPrefWidth(60);
            J9.setPrefHeight(50);
            J9.setStyle("-fx-background-color: #a9a9a9");
        }
        
        if(!missingSlotList.contains("J10"))
        {
            grid.add(J10, 9,9);
            J10.setPrefWidth(60);
            J10.setPrefHeight(50);
            J10.setStyle("-fx-background-color: #a9a9a9");
        }
    }
    
/*************************************************************************************************************************************/

    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("ShowBigAuditoriumBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showAuditoriumDatabase(email, userName, chosenTheatre);
    }
}
