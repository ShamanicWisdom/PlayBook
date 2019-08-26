/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska rezerwacji miejsc w malej sali - AddReservationForSmallAuditoriumController.java.
 * 
 */

package szaman.playbook.view.menu.show.reservation;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.Core;
import szaman.playbook.utility.HibernateConnector;
import szaman.playbook.entity.Account;
import szaman.playbook.entity.Auditorium;
import szaman.playbook.entity.Play;
import szaman.playbook.entity.Reservation;
import szaman.playbook.entity.Show;

// Reszta klas //

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.beans.PropertyVetoException;

/**
 *
 * @author Szaman
 */

public class AddReservationForSmallAuditoriumController 
{
    private Core mainApp;
    
    private String email = null;
    private String userName = null;
    
    private Show chosenShow;
        
    List<String> missingSlotList = new ArrayList<>();
    List<String> reservedSlotList = new ArrayList<>();
    
    List<String> chosenSlotList = new ArrayList<>();
    
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
                
    /**************************************************************************/    
    
    public void setMainApp(Core mainApp, String email, String userName, Show chosenShow) throws SQLException, ClassNotFoundException 
    {
        this.mainApp = mainApp;
        this.email = email;
        this.userName = userName;
        this.chosenShow = chosenShow;
        auditorium = connector.pullSingleAuditorium(chosenShow.getAuditorium().getAuditoriumId());
        reservationList = connector.pullReservationList(chosenShow);
        account = connector.pullSingleAccountViaEmail(email);
        
        play = connector.pullSinglePlay(chosenShow.getPlay().getPlayId());
        
        auditoriumInfoLabel.setText("Rezerwacja na: " + play.getName() + " w sali: " + auditorium.getAuditoriumNumber());
        
        //Pull brakujacych miejsc w sali - odwzorowanie wygladu sali.
        List<String> catchedMissingSlotList = Arrays.asList(auditorium.getMissingSlots().split(",\\s*"));
        for(int i = 0; i < catchedMissingSlotList.size(); i++)
        {
            missingSlotList.add(catchedMissingSlotList.get(i));
        }
        
        //Pull zarezerwowanych miejsc w sali - pomoze w odhaczeniu miejsc na wizualizacji sali.
        for(Reservation r : reservationList)
        {
            List<String> catchedReservedSlotList = Arrays.asList(r.getSlotsId().split(",\\s*"));
            for(int i = 0; i < catchedReservedSlotList.size(); i++)
            {
                reservedSlotList.add(catchedReservedSlotList.get(i));
            }
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
                A1.setOnAction((ActionEvent e) -> 
                {
                    if(A1flag == false) 
                    {
                        chosenSlotList.add("A1");
                        A1.setStyle("-fx-background-color: #44c767");
                        A1flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("A1");
                        A1.setStyle("-fx-background-color: #a9a9a9");
                        A1flag = false;
                    }
                });
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
                A2.setOnAction((ActionEvent e) -> 
                {
                    if(A2flag == false) 
                    {
                        chosenSlotList.add("A2");
                        A2.setStyle("-fx-background-color: #44c767");
                        A2flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("A2");
                        A2.setStyle("-fx-background-color: #a9a9a9");
                        A2flag = false;
                    }
                });
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
                A3.setOnAction((ActionEvent e) -> 
                {
                    if(A3flag == false) 
                    {
                        chosenSlotList.add("A3");
                        A3.setStyle("-fx-background-color: #44c767");
                        A3flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("A3");
                        A3.setStyle("-fx-background-color: #a9a9a9");
                        A3flag = false;
                    }
                });
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
                A4.setOnAction((ActionEvent e) -> 
                {
                    if(A4flag == false) 
                    {
                        chosenSlotList.add("A4");
                        A4.setStyle("-fx-background-color: #44c767");
                        A4flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("A4");
                        A4.setStyle("-fx-background-color: #a9a9a9");
                        A4flag = false;
                    }
                });
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
                A5.setOnAction((ActionEvent e) -> 
                {
                    if(A5flag == false) 
                    {
                        chosenSlotList.add("A5");
                        A5.setStyle("-fx-background-color: #44c767");
                        A5flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("A5");
                        A5.setStyle("-fx-background-color: #a9a9a9");
                        A5flag = false;
                    }
                });
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
                A6.setOnAction((ActionEvent e) -> 
                {
                    if(A6flag == false) 
                    {
                        chosenSlotList.add("A6");
                        A6.setStyle("-fx-background-color: #44c767");
                        A6flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("A6");
                        A6.setStyle("-fx-background-color: #a9a9a9");
                        A6flag = false;
                    }
                });
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
                A7.setOnAction((ActionEvent e) -> 
                {
                    if(A7flag == false) 
                    {
                        chosenSlotList.add("A7");
                        A7.setStyle("-fx-background-color: #44c767");
                        A7flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("A7");
                        A7.setStyle("-fx-background-color: #a9a9a9");
                        A7flag = false;
                    }
                });
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
                A8.setOnAction((ActionEvent e) -> 
                {
                    if(A8flag == false) 
                    {
                        chosenSlotList.add("A8");
                        A8.setStyle("-fx-background-color: #44c767");
                        A8flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("A8");
                        A8.setStyle("-fx-background-color: #a9a9a9");
                        A8flag = false;
                    }
                });
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
                A9.setOnAction((ActionEvent e) -> 
                {
                    if(A9flag == false) 
                    {
                        chosenSlotList.add("A9");
                        A9.setStyle("-fx-background-color: #44c767");
                        A9flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("A9");
                        A9.setStyle("-fx-background-color: #a9a9a9");
                        A9flag = false;
                    }
                });
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
                A10.setOnAction((ActionEvent e) -> 
                {
                    if(A10flag == false) 
                    {
                        chosenSlotList.add("A10");
                        A10.setStyle("-fx-background-color: #44c767");
                        A10flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("A10");
                        A10.setStyle("-fx-background-color: #a9a9a9");
                        A10flag = false;
                    }
                });
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
                B1.setOnAction((ActionEvent e) -> 
                {
                    if(B1flag == false) 
                    {
                        chosenSlotList.add("B1");
                        B1.setStyle("-fx-background-color: #44c767");
                        B1flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("B1");
                        B1.setStyle("-fx-background-color: #a9a9a9");
                        B1flag = false;
                    }
                });
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
                B2.setOnAction((ActionEvent e) -> 
                {
                    if(B2flag == false) 
                    {
                        chosenSlotList.add("B2");
                        B2.setStyle("-fx-background-color: #44c767");
                        B2flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("B2");
                        B2.setStyle("-fx-background-color: #a9a9a9");
                        B2flag = false;
                    }
                });
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
                B3.setOnAction((ActionEvent e) -> 
                {
                    if(B3flag == false) 
                    {
                        chosenSlotList.add("B3");
                        B3.setStyle("-fx-background-color: #44c767");
                        B3flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("B3");
                        B3.setStyle("-fx-background-color: #a9a9a9");
                        B3flag = false;
                    }
                });
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
                B4.setOnAction((ActionEvent e) -> 
                {
                    if(B4flag == false) 
                    {
                        chosenSlotList.add("B4");
                        B4.setStyle("-fx-background-color: #44c767");
                        B4flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("B4");
                        B4.setStyle("-fx-background-color: #a9a9a9");
                        B4flag = false;
                    }
                });
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
                B5.setOnAction((ActionEvent e) -> 
                {
                    if(B5flag == false) 
                    {
                        chosenSlotList.add("B5");
                        B5.setStyle("-fx-background-color: #44c767");
                        B5flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("B5");
                        B5.setStyle("-fx-background-color: #a9a9a9");
                        B5flag = false;
                    }
                });
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
                B6.setOnAction((ActionEvent e) -> 
                {
                    if(B6flag == false) 
                    {
                        chosenSlotList.add("B6");
                        B6.setStyle("-fx-background-color: #44c767");
                        B6flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("B6");
                        B6.setStyle("-fx-background-color: #a9a9a9");
                        B6flag = false;
                    }
                });
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
                B7.setOnAction((ActionEvent e) -> 
                {
                    if(B7flag == false) 
                    {
                        chosenSlotList.add("B7");
                        B7.setStyle("-fx-background-color: #44c767");
                        B7flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("B7");
                        B7.setStyle("-fx-background-color: #a9a9a9");
                        B7flag = false;
                    }
                });
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
                B8.setOnAction((ActionEvent e) -> 
                {
                    if(B8flag == false) 
                    {
                        chosenSlotList.add("B8");
                        B8.setStyle("-fx-background-color: #44c767");
                        B8flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("B8");
                        B8.setStyle("-fx-background-color: #a9a9a9");
                        B8flag = false;
                    }
                });
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
                B9.setOnAction((ActionEvent e) -> 
                {
                    if(B9flag == false) 
                    {
                        chosenSlotList.add("B9");
                        B9.setStyle("-fx-background-color: #44c767");
                        B9flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("B9");
                        B9.setStyle("-fx-background-color: #a9a9a9");
                        B9flag = false;
                    }
                });
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
                B10.setOnAction((ActionEvent e) -> 
                {
                    if(B10flag == false) 
                    {
                        chosenSlotList.add("B10");
                        B10.setStyle("-fx-background-color: #44c767");
                        B10flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("B10");
                        B10.setStyle("-fx-background-color: #a9a9a9");
                        B10flag = false;
                    }
                });
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
                C1.setOnAction((ActionEvent e) -> 
                {
                    if(C1flag == false) 
                    {
                        chosenSlotList.add("C1");
                        C1.setStyle("-fx-background-color: #44c767");
                        C1flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("C1");
                        C1.setStyle("-fx-background-color: #a9a9a9");
                        C1flag = false;
                    }
                });
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
                C2.setOnAction((ActionEvent e) -> 
                {
                    if(C2flag == false) 
                    {
                        chosenSlotList.add("C2");
                        C2.setStyle("-fx-background-color: #44c767");
                        C2flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("C2");
                        C2.setStyle("-fx-background-color: #a9a9a9");
                        C2flag = false;
                    }
                });
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
                C3.setOnAction((ActionEvent e) -> 
                {
                    if(C3flag == false) 
                    {
                        chosenSlotList.add("C3");
                        C3.setStyle("-fx-background-color: #44c767");
                        C3flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("C3");
                        C3.setStyle("-fx-background-color: #a9a9a9");
                        C3flag = false;
                    }
                });
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
                C4.setOnAction((ActionEvent e) -> 
                {
                    if(C4flag == false) 
                    {
                        chosenSlotList.add("C4");
                        C4.setStyle("-fx-background-color: #44c767");
                        C4flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("C4");
                        C4.setStyle("-fx-background-color: #a9a9a9");
                        C4flag = false;
                    }
                });
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
                C5.setOnAction((ActionEvent e) -> 
                {
                    if(C5flag == false) 
                    {
                        chosenSlotList.add("C5");
                        C5.setStyle("-fx-background-color: #44c767");
                        C5flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("C5");
                        C5.setStyle("-fx-background-color: #a9a9a9");
                        C5flag = false;
                    }
                });
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
                C6.setOnAction((ActionEvent e) -> 
                {
                    if(C6flag == false) 
                    {
                        chosenSlotList.add("C6");
                        C6.setStyle("-fx-background-color: #44c767");
                        C6flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("C6");
                        C6.setStyle("-fx-background-color: #a9a9a9");
                        C6flag = false;
                    }
                });
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
                C7.setOnAction((ActionEvent e) -> 
                {
                    if(C7flag == false) 
                    {
                        chosenSlotList.add("C7");
                        C7.setStyle("-fx-background-color: #44c767");
                        C7flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("C7");
                        C7.setStyle("-fx-background-color: #a9a9a9");
                        C7flag = false;
                    }
                });
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
                C8.setOnAction((ActionEvent e) -> 
                {
                    if(C8flag == false) 
                    {
                        chosenSlotList.add("C8");
                        C8.setStyle("-fx-background-color: #44c767");
                        C8flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("C8");
                        C8.setStyle("-fx-background-color: #a9a9a9");
                        C8flag = false;
                    }
                });
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
                C9.setOnAction((ActionEvent e) -> 
                {
                    if(C9flag == false) 
                    {
                        chosenSlotList.add("C9");
                        C9.setStyle("-fx-background-color: #44c767");
                        C9flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("C9");
                        C9.setStyle("-fx-background-color: #a9a9a9");
                        C9flag = false;
                    }
                });
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
                C10.setOnAction((ActionEvent e) -> 
                {
                    if(C10flag == false) 
                    {
                        chosenSlotList.add("C10");
                        C10.setStyle("-fx-background-color: #44c767");
                        C10flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("C10");
                        C10.setStyle("-fx-background-color: #a9a9a9");
                        C10flag = false;
                    }
                });
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
                D1.setOnAction((ActionEvent e) -> 
                {
                    if(D1flag == false) 
                    {
                        chosenSlotList.add("D1");
                        D1.setStyle("-fx-background-color: #44c767");
                        D1flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("D1");
                        D1.setStyle("-fx-background-color: #a9a9a9");
                        D1flag = false;
                    }
                });
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
                D2.setOnAction((ActionEvent e) -> 
                {
                    if(D2flag == false) 
                    {
                        chosenSlotList.add("D2");
                        D2.setStyle("-fx-background-color: #44c767");
                        D2flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("D2");
                        D2.setStyle("-fx-background-color: #a9a9a9");
                        D2flag = false;
                    }
                });
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
                D3.setOnAction((ActionEvent e) -> 
                {
                    if(D3flag == false) 
                    {
                        chosenSlotList.add("D3");
                        D3.setStyle("-fx-background-color: #44c767");
                        D3flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("D3");
                        D3.setStyle("-fx-background-color: #a9a9a9");
                        D3flag = false;
                    }
                });
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
                D4.setOnAction((ActionEvent e) -> 
                {
                    if(D4flag == false) 
                    {
                        chosenSlotList.add("D4");
                        D4.setStyle("-fx-background-color: #44c767");
                        D4flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("D4");
                        D4.setStyle("-fx-background-color: #a9a9a9");
                        D4flag = false;
                    }
                });
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
                D5.setOnAction((ActionEvent e) -> 
                {
                    if(D5flag == false) 
                    {
                        chosenSlotList.add("D5");
                        D5.setStyle("-fx-background-color: #44c767");
                        D5flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("D5");
                        D5.setStyle("-fx-background-color: #a9a9a9");
                        D5flag = false;
                    }
                });
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
                D6.setOnAction((ActionEvent e) -> 
                {
                    if(D6flag == false) 
                    {
                        chosenSlotList.add("D6");
                        D6.setStyle("-fx-background-color: #44c767");
                        D6flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("D6");
                        D6.setStyle("-fx-background-color: #a9a9a9");
                        D6flag = false;
                    }
                });
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
                D7.setOnAction((ActionEvent e) -> 
                {
                    if(D7flag == false) 
                    {
                        chosenSlotList.add("D7");
                        D7.setStyle("-fx-background-color: #44c767");
                        D7flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("D7");
                        D7.setStyle("-fx-background-color: #a9a9a9");
                        D7flag = false;
                    }
                });
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
                D8.setOnAction((ActionEvent e) -> 
                {
                    if(D8flag == false) 
                    {
                        chosenSlotList.add("D8");
                        D8.setStyle("-fx-background-color: #44c767");
                        D8flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("D8");
                        D8.setStyle("-fx-background-color: #a9a9a9");
                        D8flag = false;
                    }
                });
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
                D9.setOnAction((ActionEvent e) -> 
                {
                    if(D9flag == false) 
                    {
                        chosenSlotList.add("D9");
                        D9.setStyle("-fx-background-color: #44c767");
                        D9flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("D9");
                        D9.setStyle("-fx-background-color: #a9a9a9");
                        D9flag = false;
                    }
                });
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
                D10.setOnAction((ActionEvent e) -> 
                {
                    if(D10flag == false) 
                    {
                        chosenSlotList.add("D10");
                        D10.setStyle("-fx-background-color: #44c767");
                        D10flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("D10");
                        D10.setStyle("-fx-background-color: #a9a9a9");
                        D10flag = false;
                    }
                });
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
                E1.setOnAction((ActionEvent e) -> 
                {
                    if(E1flag == false) 
                    {
                        chosenSlotList.add("E1");
                        E1.setStyle("-fx-background-color: #44c767");
                        E1flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("E1");
                        E1.setStyle("-fx-background-color: #a9a9a9");
                        E1flag = false;
                    }
                });
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
                E2.setOnAction((ActionEvent e) -> 
                {
                    if(E2flag == false) 
                    {
                        chosenSlotList.add("E2");
                        E2.setStyle("-fx-background-color: #44c767");
                        E2flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("E2");
                        E2.setStyle("-fx-background-color: #a9a9a9");
                        E2flag = false;
                    }
                });
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
                E3.setOnAction((ActionEvent e) -> 
                {
                    if(E3flag == false) 
                    {
                        chosenSlotList.add("E3");
                        E3.setStyle("-fx-background-color: #44c767");
                        E3flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("E3");
                        E3.setStyle("-fx-background-color: #a9a9a9");
                        E3flag = false;
                    }
                });
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
                E4.setOnAction((ActionEvent e) -> 
                {
                    if(E4flag == false) 
                    {
                        chosenSlotList.add("E4");
                        E4.setStyle("-fx-background-color: #44c767");
                        E4flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("E4");
                        E4.setStyle("-fx-background-color: #a9a9a9");
                        E4flag = false;
                    }
                });
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
                E5.setOnAction((ActionEvent e) -> 
                {
                    if(E5flag == false) 
                    {
                        chosenSlotList.add("E5");
                        E5.setStyle("-fx-background-color: #44c767");
                        E5flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("E5");
                        E5.setStyle("-fx-background-color: #a9a9a9");
                        E5flag = false;
                    }
                });
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
                E6.setOnAction((ActionEvent e) -> 
                {
                    if(E6flag == false) 
                    {
                        chosenSlotList.add("E6");
                        E6.setStyle("-fx-background-color: #44c767");
                        E6flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("E6");
                        E6.setStyle("-fx-background-color: #a9a9a9");
                        E6flag = false;
                    }
                });
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
                E7.setOnAction((ActionEvent e) -> 
                {
                    if(E7flag == false) 
                    {
                        chosenSlotList.add("E7");
                        E7.setStyle("-fx-background-color: #44c767");
                        E7flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("E7");
                        E7.setStyle("-fx-background-color: #a9a9a9");
                        E7flag = false;
                    }
                });
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
                E8.setOnAction((ActionEvent e) -> 
                {
                    if(E8flag == false) 
                    {
                        chosenSlotList.add("E8");
                        E8.setStyle("-fx-background-color: #44c767");
                        E8flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("E8");
                        E8.setStyle("-fx-background-color: #a9a9a9");
                        E8flag = false;
                    }
                });
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
                E9.setOnAction((ActionEvent e) -> 
                {
                    if(E9flag == false) 
                    {
                        chosenSlotList.add("E9");
                        E9.setStyle("-fx-background-color: #44c767");
                        E9flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("E9");
                        E9.setStyle("-fx-background-color: #a9a9a9");
                        E9flag = false;
                    }
                });
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
                E10.setOnAction((ActionEvent e) -> 
                {
                    if(E10flag == false) 
                    {
                        chosenSlotList.add("E10");
                        E10.setStyle("-fx-background-color: #44c767");
                        E10flag = true;
                    }
                    else
                    {
                        chosenSlotList.remove("E10");
                        E10.setStyle("-fx-background-color: #a9a9a9");
                        E10flag = false;
                    }
                });
            }
        }    
    }
    
/*************************************************************************************************************************************/
    
    //Skrypt dla guzika Ok.
    @FXML
    private void handleOk(ActionEvent event) throws ClassNotFoundException, SQLException, PropertyVetoException
    {
        System.out.println("ReservationConfirmation detected!"); //kontrola, czy dziala interakcja.
        String chosenSlots = "";
        for(int i = 0; i < chosenSlotList.size(); i++) //Zapis wszystkich brakujacych miejsc do jednego stringa przekazywanego do bazy.
        {
            if(i == chosenSlotList.size() - 1)
            {
                chosenSlots += chosenSlotList.get(i);
            }
            else
            {
                chosenSlots += chosenSlotList.get(i) + ", ";
            }
        }
        if(chosenSlotList.isEmpty())
        {
            String errorMessage = "Proszę zaznaczyć minimum jedno miejsce do rezerwacji!";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Błąd!");
            alert.setHeaderText("Błąd podliczenia ceny rezerwacji!");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }
        else
        {
            boolean okClicked = mainApp.showConfirmReservation(account.getAccountId(), chosenShow.getShowId(), chosenSlotList.size(), chosenSlots);
            if (okClicked) 
            {
                mainApp.showReservationDatabase(email, userName, chosenShow);
            }
            System.out.println("ReservationConfirmationCancel detected!"); //kontrola, czy dziala interakcja.
        }
    }
    
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("AddReservationForSmallAuditoriumBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showReservationDatabase(email, userName, chosenShow);
    }
}
