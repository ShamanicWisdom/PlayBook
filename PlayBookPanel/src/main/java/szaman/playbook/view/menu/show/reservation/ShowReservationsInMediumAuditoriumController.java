/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska wyswietlenia wszystkich zarezerwowanych miejsc w sredniej sali - ShowReservationsInMediumAuditoriumController.java.
 * 
 */

package szaman.playbook.view.menu.show.reservation;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.entity.Account;
import szaman.playbook.entity.Auditorium;
import szaman.playbook.entity.Play;
import szaman.playbook.entity.Reservation;
import szaman.playbook.entity.Show;
import szaman.playbook.Core;
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
import java.beans.PropertyVetoException;

/**
 *
 * @author Szaman
 */

public class ShowReservationsInMediumAuditoriumController 
{
    private Core mainApp;
    
    private String email = null;
    private String userName = null;
    
    private Show chosenShow;
        
    List<String> missingSlotList = new ArrayList<>();
    
    List<Reservation> reservationList;
    
    HibernateConnector connector = new HibernateConnector();
    
    Auditorium auditorium;
    Account account;
    Play play;
    
    @FXML
    private GridPane grid;
    
    @FXML
    private Label auditoriumInfoLabel;
    
    private int auditoriumNumber;
    
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
            
    /**************************************************************************/    
    
    public void setMainApp(Core mainApp, String email, String userName, Show chosenShow, List<String> reservedSlotList) throws SQLException, ClassNotFoundException 
    {
        this.mainApp = mainApp;
        this.email = email;
        this.userName = userName;
        this.chosenShow = chosenShow;
        auditorium = connector.pullSingleAuditorium(chosenShow.getAuditorium().getAuditoriumId());
        
        play = connector.pullSinglePlay(chosenShow.getPlay().getPlayId());
        
        auditoriumInfoLabel.setText("Rezerwacje na: " + play.getName() + " w sali: " + auditorium.getAuditoriumNumber());
        
        //Pull brakujacych miejsc w sali - odwzorowanie wygladu sali.
        List<String> catchedMissingSlotList = Arrays.asList(auditorium.getMissingSlots().split(",\\s*"));
        for(int i = 0; i < catchedMissingSlotList.size(); i++)
        {
            missingSlotList.add(catchedMissingSlotList.get(i));
        }
        
        System.out.println("Missing slots: " + missingSlotList);
        System.out.println("Reserved slots: " + reservedSlotList);
        
    /********************************RZAD 1************************************/
        
        if(!missingSlotList.contains("A1"))
        {
            grid.add(A1, 0,0);
            A1.setPrefWidth(60);
            A1.setPrefHeight(50);
            if(reservedSlotList.contains("A1"))
            {
                A1.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                A1.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("A2"))
        {
            grid.add(A2, 1,0);
            A2.setPrefWidth(60);
            A2.setPrefHeight(50);
            if(reservedSlotList.contains("A2"))
            {
                A2.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                A2.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("A3"))
        {
            grid.add(A3, 2,0);
            A3.setPrefWidth(60);
            A3.setPrefHeight(50);
            if(reservedSlotList.contains("A3"))
            {
                A3.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                A3.setStyle("-fx-background-color: #a9a9a9");
            }
        }
                
        if(!missingSlotList.contains("A4"))
        {
            grid.add(A4, 3,0);
            A4.setPrefWidth(60);
            A4.setPrefHeight(50);
            if(reservedSlotList.contains("A4"))
            {
                A4.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                A4.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("A5"))
        {
            grid.add(A5, 4,0);
            A5.setPrefWidth(60);
            A5.setPrefHeight(50);
            if(reservedSlotList.contains("A5"))
            {
                A5.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                A5.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("A6"))
        {
            grid.add(A6, 5,0);
            A6.setPrefWidth(60);
            A6.setPrefHeight(50);
            if(reservedSlotList.contains("A6"))
            {
                A6.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                A6.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("A7"))
        {
            grid.add(A7, 6,0);
            A7.setPrefWidth(60);
            A7.setPrefHeight(50);
            if(reservedSlotList.contains("A7"))
            {
                A7.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                A7.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("A8"))
        {
            grid.add(A8, 7,0);
            A8.setPrefWidth(60);
            A8.setPrefHeight(50);
            if(reservedSlotList.contains("A8"))
            {
                A8.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                A8.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("A9"))
        {
            grid.add(A9, 8,0);
            A9.setPrefWidth(60);
            A9.setPrefHeight(50);
            if(reservedSlotList.contains("A9"))
            {
                A9.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                A9.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("A10"))
        {
            grid.add(A10, 9,0);
            A10.setPrefWidth(60);
            A10.setPrefHeight(50);
            if(reservedSlotList.contains("A10"))
            {
                A10.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                A10.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
    /********************************RZAD 2************************************/
  
        if(!missingSlotList.contains("B1"))
        {
            grid.add(B1, 0,1);
            B1.setPrefWidth(60);
            B1.setPrefHeight(50);
            if(reservedSlotList.contains("B1"))
            {
                B1.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                B1.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("B2"))
        {
            grid.add(B2, 1,1);
            B2.setPrefWidth(60);
            B2.setPrefHeight(50);
            if(reservedSlotList.contains("B2"))
            {
                B2.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                B2.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("B3"))
        {
            grid.add(B3, 2,1);
            B3.setPrefWidth(60);
            B3.setPrefHeight(50);
            if(reservedSlotList.contains("B3"))
            {
                B3.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                B3.setStyle("-fx-background-color: #a9a9a9");
            }
        }
                
        if(!missingSlotList.contains("B4"))
        {
            grid.add(B4, 3,1);
            B4.setPrefWidth(60);
            B4.setPrefHeight(50);
            if(reservedSlotList.contains("B4"))
            {
                B4.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                B4.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("B5"))
        {
            grid.add(B5, 4,1);
            B5.setPrefWidth(60);
            B5.setPrefHeight(50);
            if(reservedSlotList.contains("B5"))
            {
                B5.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                B5.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("B6"))
        {
            grid.add(B6, 5,1);
            B6.setPrefWidth(60);
            B6.setPrefHeight(50);
            if(reservedSlotList.contains("B6"))
            {
                B6.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                B6.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("B7"))
        {
            grid.add(B7, 6,1);
            B7.setPrefWidth(60);
            B7.setPrefHeight(50);
            if(reservedSlotList.contains("B7"))
            {
                B7.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                B7.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("B8"))
        {
            grid.add(B8, 7,1);
            B8.setPrefWidth(60);
            B8.setPrefHeight(50);
            if(reservedSlotList.contains("B8"))
            {
                B8.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                B8.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("B9"))
        {
            grid.add(B9, 8,1);
            B9.setPrefWidth(60);
            B9.setPrefHeight(50);
            if(reservedSlotList.contains("B9"))
            {
                B9.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                B9.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("B10"))
        {
            grid.add(B10, 9,1);
            B10.setPrefWidth(60);
            B10.setPrefHeight(50);
            if(reservedSlotList.contains("B10"))
            {
                B10.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                B10.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
    /********************************RZAD 3************************************/
    
    if(!missingSlotList.contains("C1"))
        {
            grid.add(C1, 0,2);
            C1.setPrefWidth(60);
            C1.setPrefHeight(50);
            if(reservedSlotList.contains("C1"))
            {
                C1.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                C1.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("C2"))
        {
            grid.add(C2, 1,2);
            C2.setPrefWidth(60);
            C2.setPrefHeight(50);
            if(reservedSlotList.contains("C2"))
            {
                C2.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                C2.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("C3"))
        {
            grid.add(C3, 2,2);
            C3.setPrefWidth(60);
            C3.setPrefHeight(50);
            if(reservedSlotList.contains("C3"))
            {
                C3.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                C3.setStyle("-fx-background-color: #a9a9a9");
            }
        }
                
        if(!missingSlotList.contains("C4"))
        {
            grid.add(C4, 3,2);
            C4.setPrefWidth(60);
            C4.setPrefHeight(50);
            if(reservedSlotList.contains("C4"))
            {
                C4.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                C4.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("C5"))
        {
            grid.add(C5, 4,2);
            C5.setPrefWidth(60);
            C5.setPrefHeight(50);
            if(reservedSlotList.contains("C5"))
            {
                C5.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                C5.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("C6"))
        {
            grid.add(C6, 5,2);
            C6.setPrefWidth(60);
            C6.setPrefHeight(50);
            if(reservedSlotList.contains("C6"))
            {
                C6.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                C6.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("C7"))
        {
            grid.add(C7, 6,2);
            C7.setPrefWidth(60);
            C7.setPrefHeight(50);
            if(reservedSlotList.contains("C7"))
            {
                C7.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                C7.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("C8"))
        {
            grid.add(C8, 7,2);
            C8.setPrefWidth(60);
            C8.setPrefHeight(50);
            if(reservedSlotList.contains("C8"))
            {
                C8.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                C8.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("C9"))
        {
            grid.add(C9, 8,2);
            C9.setPrefWidth(60);
            C9.setPrefHeight(50);
            if(reservedSlotList.contains("C9"))
            {
                C9.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                C9.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("C10"))
        {
            grid.add(C10, 9,2);
            C10.setPrefWidth(60);
            C10.setPrefHeight(50);
            if(reservedSlotList.contains("C10"))
            {
                C10.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                C10.setStyle("-fx-background-color: #a9a9a9");
            }
        }    
        
    /********************************RZAD 4************************************/
    
    if(!missingSlotList.contains("D1"))
        {
            grid.add(D1, 0,3);
            D1.setPrefWidth(60);
            D1.setPrefHeight(50);
            if(reservedSlotList.contains("D1"))
            {
                D1.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                D1.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("D2"))
        {
            grid.add(D2, 1,3);
            D2.setPrefWidth(60);
            D2.setPrefHeight(50);
            if(reservedSlotList.contains("D2"))
            {
                D2.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                D2.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("D3"))
        {
            grid.add(D3, 2,3);
            D3.setPrefWidth(60);
            D3.setPrefHeight(50);
            if(reservedSlotList.contains("D3"))
            {
                D3.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                D3.setStyle("-fx-background-color: #a9a9a9");
            }
        }
                
        if(!missingSlotList.contains("D4"))
        {
            grid.add(D4, 3,3);
            D4.setPrefWidth(60);
            D4.setPrefHeight(50);
            if(reservedSlotList.contains("D4"))
            {
                D4.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                D4.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("D5"))
        {
            grid.add(D5, 4,3);
            D5.setPrefWidth(60);
            D5.setPrefHeight(50);
            if(reservedSlotList.contains("D5"))
            {
                D5.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                D5.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("D6"))
        {
            grid.add(D6, 5,3);
            D6.setPrefWidth(60);
            D6.setPrefHeight(50);
            if(reservedSlotList.contains("D6"))
            {
                D6.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                D6.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("D7"))
        {
            grid.add(D7, 6,3);
            D7.setPrefWidth(60);
            D7.setPrefHeight(50);
            if(reservedSlotList.contains("D7"))
            {
                D7.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                D7.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("D8"))
        {
            grid.add(D8, 7,3);
            D8.setPrefWidth(60);
            D8.setPrefHeight(50);
            if(reservedSlotList.contains("D8"))
            {
                D8.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                D8.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("D9"))
        {
            grid.add(D9, 8,3);
            D9.setPrefWidth(60);
            D9.setPrefHeight(50);
            if(reservedSlotList.contains("D9"))
            {
                D9.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                D9.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("D10"))
        {
            grid.add(D10, 9,3);
            D10.setPrefWidth(60);
            D10.setPrefHeight(50);
            if(reservedSlotList.contains("D10"))
            {
                D10.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                D10.setStyle("-fx-background-color: #a9a9a9");
            }
        }    
        
    /********************************RZAD 5************************************/
    
    if(!missingSlotList.contains("E1"))
        {
            grid.add(E1, 0,4);
            E1.setPrefWidth(60);
            E1.setPrefHeight(50);
            if(reservedSlotList.contains("E1"))
            {
                E1.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                E1.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("E2"))
        {
            grid.add(E2, 1,4);
            E2.setPrefWidth(60);
            E2.setPrefHeight(50);
            if(reservedSlotList.contains("E2"))
            {
                E2.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                E2.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("E3"))
        {
            grid.add(E3, 2,4);
            E3.setPrefWidth(60);
            E3.setPrefHeight(50);
            if(reservedSlotList.contains("E3"))
            {
                E3.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                E3.setStyle("-fx-background-color: #a9a9a9");
            }
        }
                
        if(!missingSlotList.contains("E4"))
        {
            grid.add(E4, 3,4);
            E4.setPrefWidth(60);
            E4.setPrefHeight(50);
            if(reservedSlotList.contains("E4"))
            {
                E4.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                E4.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("E5"))
        {
            grid.add(E5, 4,4);
            E5.setPrefWidth(60);
            E5.setPrefHeight(50);
            if(reservedSlotList.contains("E5"))
            {
                E5.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                E5.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("E6"))
        {
            grid.add(E6, 5,4);
            E6.setPrefWidth(60);
            E6.setPrefHeight(50);
            if(reservedSlotList.contains("E6"))
            {
                E6.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                E6.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("E7"))
        {
            grid.add(E7, 6,4);
            E7.setPrefWidth(60);
            E7.setPrefHeight(50);
            if(reservedSlotList.contains("E7"))
            {
                E7.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                E7.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("E8"))
        {
            grid.add(E8, 7,4);
            E8.setPrefWidth(60);
            E8.setPrefHeight(50);
            if(reservedSlotList.contains("E8"))
            {
                E8.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                E8.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("E9"))
        {
            grid.add(E9, 8,4);
            E9.setPrefWidth(60);
            E9.setPrefHeight(50);
            if(reservedSlotList.contains("E9"))
            {
                E9.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                E9.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("E10"))
        {
            grid.add(E10, 9,4);
            E10.setPrefWidth(60);
            E10.setPrefHeight(50);
            if(reservedSlotList.contains("E10"))
            {
                E10.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                E10.setStyle("-fx-background-color: #a9a9a9");
            }
        }   
        
    /********************************RZAD 6************************************/
    
    if(!missingSlotList.contains("F1"))
        {
            grid.add(F1, 0,5);
            F1.setPrefWidth(60);
            F1.setPrefHeight(50);
            if(reservedSlotList.contains("F1"))
            {
                F1.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                F1.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("F2"))
        {
            grid.add(F2, 1,5);
            F2.setPrefWidth(60);
            F2.setPrefHeight(50);
            if(reservedSlotList.contains("F2"))
            {
                F2.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                F2.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("F3"))
        {
            grid.add(F3, 2,5);
            F3.setPrefWidth(60);
            F3.setPrefHeight(50);
            if(reservedSlotList.contains("F3"))
            {
                F3.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                F3.setStyle("-fx-background-color: #a9a9a9");
            }
        }
                
        if(!missingSlotList.contains("F4"))
        {
            grid.add(F4, 3,5);
            F4.setPrefWidth(60);
            F4.setPrefHeight(50);
            if(reservedSlotList.contains("F4"))
            {
                F4.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                F4.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("F5"))
        {
            grid.add(F5, 4,5);
            F5.setPrefWidth(60);
            F5.setPrefHeight(50);
            if(reservedSlotList.contains("F5"))
            {
                F5.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                F5.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("F6"))
        {
            grid.add(F6, 5,5);
            F6.setPrefWidth(60);
            F6.setPrefHeight(50);
            if(reservedSlotList.contains("F6"))
            {
                F6.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                F6.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("F7"))
        {
            grid.add(F7, 6,5);
            F7.setPrefWidth(60);
            F7.setPrefHeight(50);
            if(reservedSlotList.contains("F7"))
            {
                F7.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                F7.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("F8"))
        {
            grid.add(F8, 7,5);
            F8.setPrefWidth(60);
            F8.setPrefHeight(50);
            if(reservedSlotList.contains("F8"))
            {
                F8.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                F8.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("F9"))
        {
            grid.add(F9, 8,5);
            F9.setPrefWidth(60);
            F9.setPrefHeight(50);
            if(reservedSlotList.contains("F9"))
            {
                F9.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                F9.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("F10"))
        {
            grid.add(F10, 9,5);
            F10.setPrefWidth(60);
            F10.setPrefHeight(50);
            if(reservedSlotList.contains("F10"))
            {
                F10.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                F10.setStyle("-fx-background-color: #a9a9a9");
            }
        }   
        
    /********************************RZAD 7************************************/
    
    if(!missingSlotList.contains("G1"))
        {
            grid.add(G1, 0,6);
            G1.setPrefWidth(60);
            G1.setPrefHeight(50);
            if(reservedSlotList.contains("G1"))
            {
                G1.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                G1.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("G2"))
        {
            grid.add(G2, 1,6);
            G2.setPrefWidth(60);
            G2.setPrefHeight(50);
            if(reservedSlotList.contains("G2"))
            {
                G2.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                G2.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("G3"))
        {
            grid.add(G3, 2,6);
            G3.setPrefWidth(60);
            G3.setPrefHeight(50);
            if(reservedSlotList.contains("G3"))
            {
                G3.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                G3.setStyle("-fx-background-color: #a9a9a9");
            }
        }
                
        if(!missingSlotList.contains("G4"))
        {
            grid.add(G4, 3,6);
            G4.setPrefWidth(60);
            G4.setPrefHeight(50);
            if(reservedSlotList.contains("G4"))
            {
                G4.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                G4.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("G5"))
        {
            grid.add(G5, 4,6);
            G5.setPrefWidth(60);
            G5.setPrefHeight(50);
            if(reservedSlotList.contains("G5"))
            {
                G5.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                G5.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("G6"))
        {
            grid.add(G6, 5,6);
            G6.setPrefWidth(60);
            G6.setPrefHeight(50);
            if(reservedSlotList.contains("G6"))
            {
                G6.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                G6.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("G7"))
        {
            grid.add(G7, 6,6);
            G7.setPrefWidth(60);
            G7.setPrefHeight(50);
            if(reservedSlotList.contains("G7"))
            {
                G7.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                G7.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("G8"))
        {
            grid.add(G8, 7,6);
            G8.setPrefWidth(60);
            G8.setPrefHeight(50);
            if(reservedSlotList.contains("G8"))
            {
                G8.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                G8.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("G9"))
        {
            grid.add(G9, 8,6);
            G9.setPrefWidth(60);
            G9.setPrefHeight(50);
            if(reservedSlotList.contains("G9"))
            {
                G9.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                G9.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("G10"))
        {
            grid.add(G10, 9,6);
            G10.setPrefWidth(60);
            G10.setPrefHeight(50);
            if(reservedSlotList.contains("G10"))
            {
                G10.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                G10.setStyle("-fx-background-color: #a9a9a9");
            }
        }   
        
    /********************************RZAD 8************************************/
    
    if(!missingSlotList.contains("H1"))
        {
            grid.add(H1, 0,7);
            H1.setPrefWidth(60);
            H1.setPrefHeight(50);
            if(reservedSlotList.contains("H1"))
            {
                H1.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                H1.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("H2"))
        {
            grid.add(H2, 1,7);
            H2.setPrefWidth(60);
            H2.setPrefHeight(50);
            if(reservedSlotList.contains("H2"))
            {
                H2.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                H2.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("H3"))
        {
            grid.add(H3, 2,7);
            H3.setPrefWidth(60);
            H3.setPrefHeight(50);
            if(reservedSlotList.contains("H3"))
            {
                H3.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                H3.setStyle("-fx-background-color: #a9a9a9");
            }
        }
                
        if(!missingSlotList.contains("H4"))
        {
            grid.add(H4, 3,7);
            H4.setPrefWidth(60);
            H4.setPrefHeight(50);
            if(reservedSlotList.contains("H4"))
            {
                H4.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                H4.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("H5"))
        {
            grid.add(H5, 4,7);
            H5.setPrefWidth(60);
            H5.setPrefHeight(50);
            if(reservedSlotList.contains("H5"))
            {
                H5.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                H5.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("H6"))
        {
            grid.add(H6, 5,7);
            H6.setPrefWidth(60);
            H6.setPrefHeight(50);
            if(reservedSlotList.contains("H6"))
            {
                H6.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                H6.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("H7"))
        {
            grid.add(H7, 6,7);
            H7.setPrefWidth(60);
            H7.setPrefHeight(50);
            if(reservedSlotList.contains("H7"))
            {
                H7.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                H7.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("H8"))
        {
            grid.add(H8, 7,7);
            H8.setPrefWidth(60);
            H8.setPrefHeight(50);
            if(reservedSlotList.contains("H8"))
            {
                H8.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                H8.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("H9"))
        {
            grid.add(H9, 8,7);
            H9.setPrefWidth(60);
            H9.setPrefHeight(50);
            if(reservedSlotList.contains("H9"))
            {
                H9.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                H9.setStyle("-fx-background-color: #a9a9a9");
            }
        }
        
        if(!missingSlotList.contains("H10"))
        {
            grid.add(H10, 9,7);
            H10.setPrefWidth(60);
            H10.setPrefHeight(50);
            if(reservedSlotList.contains("H10"))
            {
                H10.setStyle("-fx-background-color: #FF0000");
            }
            else
            {
                H10.setStyle("-fx-background-color: #a9a9a9");
            }
        }   
    }
    
/*************************************************************************************************************************************/
    
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("ShowReservationsInMediumAuditoriumBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showReservationDatabase(email, userName, chosenShow);
    }
}
