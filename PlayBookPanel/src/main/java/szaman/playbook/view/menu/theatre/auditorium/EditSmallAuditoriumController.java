/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska edycji malej sali - EditSmallAuditoriumController.java.
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
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Szaman
 */

public class EditSmallAuditoriumController 
{
    private Core mainApp;
    
    private String email = null;
    private String userName = null;
    
    private Theatre chosenTheatre;
        
    List<String> missingSlotList = new ArrayList<>();
    List<String> actualMissingSlotList = new ArrayList<>();
    
    HibernateConnector connector = new HibernateConnector();
    
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
    
    public void setMainApp(Core mainApp, String email, String userName, Theatre chosenTheatre, String getMissingSlots, String auditoriumNumber) throws SQLException, ClassNotFoundException 
    {
        this.mainApp = mainApp;
        this.email = email;
        this.userName = userName;
        this.chosenTheatre = chosenTheatre;
        this.auditoriumNumber = Integer.parseInt(auditoriumNumber);
                
        auditoriumInfoLabel.setText("Edycja sali " + auditoriumNumber);
        List<String> catchedSlotList = Arrays.asList(getMissingSlots.split(",\\s*"));
        for(int i = 0; i < catchedSlotList.size(); i++)
        {
            missingSlotList.add(catchedSlotList.get(i));
        }
        
        System.out.println("Missing slots: " + missingSlotList);
        
    /********************************RZAD 1************************************/
        
        grid.add(A1, 0,0);
        A1.setPrefWidth(60);
        A1.setPrefHeight(50);
        if(missingSlotList.contains("A1"))
        {
            A1.setStyle("-fx-background-color: #FF0000");
            A1flag = true;
        }
        else
        {
            A1.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("A2"))
        {
            A2.setStyle("-fx-background-color: #FF0000");
            A2flag = true;
        }
        else
        {
            A2.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("A3"))
        {
            A3.setStyle("-fx-background-color: #FF0000");
            A3flag = true;
        }
        else
        {
            A3.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("A4"))
        {
            A4.setStyle("-fx-background-color: #FF0000");
            A4flag = true;
        }
        else
        {
            A4.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("A5"))
        {
            A5.setStyle("-fx-background-color: #FF0000");
            A5flag = true;
        }
        else
        {
            A5.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("A6"))
        {
            A6.setStyle("-fx-background-color: #FF0000");
            A6flag = true;
        }
        else
        {
            A6.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("A7"))
        {
            A7.setStyle("-fx-background-color: #FF0000");
            A7flag = true;
        }
        else
        {
            A7.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("A8"))
        {
            A8.setStyle("-fx-background-color: #FF0000");
            A8flag = true;
        }
        else
        {
            A8.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("A9"))
        {
            A9.setStyle("-fx-background-color: #FF0000");
            A9flag = true;
        }
        else
        {
            A9.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("A10"))
        {
            A10.setStyle("-fx-background-color: #FF0000");
            A10flag = true;
        }
        else
        {
            A10.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("B1"))
        {
            B1.setStyle("-fx-background-color: #FF0000");
            B1flag = true;
        }
        else
        {
            B1.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("B2"))
        {
            B2.setStyle("-fx-background-color: #FF0000");
            B2flag = true;
        }
        else
        {
            B2.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("B3"))
        {
            B3.setStyle("-fx-background-color: #FF0000");
            B3flag = true;
        }
        else
        {
            B3.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("B4"))
        {
            B4.setStyle("-fx-background-color: #FF0000");
            B4flag = true;
        }
        else
        {
            B4.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("B5"))
        {
            B5.setStyle("-fx-background-color: #FF0000");
            B5flag = true;
        }
        else
        {
            B5.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("B6"))
        {
            B6.setStyle("-fx-background-color: #FF0000");
            B6flag = true;
        }
        else
        {
            B6.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("B7"))
        {
            B7.setStyle("-fx-background-color: #FF0000");
            B7flag = true;
        }
        else
        {
            B7.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("B8"))
        {
            B8.setStyle("-fx-background-color: #FF0000");
            B8flag = true;
        }
        else
        {
            B8.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("B9"))
        {
            B9.setStyle("-fx-background-color: #FF0000");
            B9flag = true;
        }
        else
        {
            B9.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("B10"))
        {
            B10.setStyle("-fx-background-color: #FF0000");
            B10flag = true;
        }
        else
        {
            B10.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("C1"))
        {
            C1.setStyle("-fx-background-color: #FF0000");
            C1flag = true;
        }
        else
        {
            C1.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("C2"))
        {
            C2.setStyle("-fx-background-color: #FF0000");
            C2flag = true;
        }
        else
        {
            C2.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("C3"))
        {
            C3.setStyle("-fx-background-color: #FF0000");
            C3flag = true;
        }
        else
        {
            C3.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("C4"))
        {
            C4.setStyle("-fx-background-color: #FF0000");
            C4flag = true;
        }
        else
        {
            C4.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("C5"))
        {
            C5.setStyle("-fx-background-color: #FF0000");
            C5flag = true;
        }
        else
        {
            C5.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("C6"))
        {
            C6.setStyle("-fx-background-color: #FF0000");
            C6flag = true;
        }
        else
        {
            C6.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("C7"))
        {
            C7.setStyle("-fx-background-color: #FF0000");
            C7flag = true;
        }
        else
        {
            C7.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("C8"))
        {
            C8.setStyle("-fx-background-color: #FF0000");
            C8flag = true;
        }
        else
        {
            C8.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("C9"))
        {
            C9.setStyle("-fx-background-color: #FF0000");
            C9flag = true;
        }
        else
        {
            C9.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("C10"))
        {
            C10.setStyle("-fx-background-color: #FF0000");
            C10flag = true;
        }
        else
        {
            C10.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("D1"))
        {
            D1.setStyle("-fx-background-color: #FF0000");
            D1flag = true;
        }
        else
        {
            D1.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("D2"))
        {
            D2.setStyle("-fx-background-color: #FF0000");
            D2flag = true;
        }
        else
        {
            D2.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("D3"))
        {
            D3.setStyle("-fx-background-color: #FF0000");
            D3flag = true;
        }
        else
        {
            D3.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("D4"))
        {
            D4.setStyle("-fx-background-color: #FF0000");
            D4flag = true;
        }
        else
        {
            D4.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("D5"))
        {
            D5.setStyle("-fx-background-color: #FF0000");
            D5flag = true;
        }
        else
        {
            D5.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("D6"))
        {
            D6.setStyle("-fx-background-color: #FF0000");
            D6flag = true;
        }
        else
        {
            D6.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("D7"))
        {
            D7.setStyle("-fx-background-color: #FF0000");
            D7flag = true;
        }
        else
        {
            D7.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("D8"))
        {
            D8.setStyle("-fx-background-color: #FF0000");
            D8flag = true;
        }
        else
        {
            D8.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("D9"))
        {
            D9.setStyle("-fx-background-color: #FF0000");
            D9flag = true;
        }
        else
        {
            D9.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("D10"))
        {
            D10.setStyle("-fx-background-color: #FF0000");
            D10flag = true;
        }
        else
        {
            D10.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("E1"))
        {
            E1.setStyle("-fx-background-color: #FF0000");
            E1flag = true;
        }
        else
        {
            E1.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("E2"))
        {
            E2.setStyle("-fx-background-color: #FF0000");
            E2flag = true;
        }
        else
        {
            E2.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("E3"))
        {
            E3.setStyle("-fx-background-color: #FF0000");
            E3flag = true;
        }
        else
        {
            E3.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("E4"))
        {
            E4.setStyle("-fx-background-color: #FF0000");
            E4flag = true;
        }
        else
        {
            E4.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("E5"))
        {
            E5.setStyle("-fx-background-color: #FF0000");
            E5flag = true;
        }
        else
        {
            E5.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("E6"))
        {
            E6.setStyle("-fx-background-color: #FF0000");
            E6flag = true;
        }
        else
        {
            E6.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("E7"))
        {
            E7.setStyle("-fx-background-color: #FF0000");
            E7flag = true;
        }
        else
        {
            E7.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("E8"))
        {
            E8.setStyle("-fx-background-color: #FF0000");
            E8flag = true;
        }
        else
        {
            E8.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("E9"))
        {
            E9.setStyle("-fx-background-color: #FF0000");
            E9flag = true;
        }
        else
        {
            E9.setStyle("-fx-background-color: #a9a9a9");
        }
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
        if(missingSlotList.contains("E10"))
        {
            E10.setStyle("-fx-background-color: #FF0000");
            E10flag = true;
        }
        else
        {
            E10.setStyle("-fx-background-color: #a9a9a9");
        }
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
        alert.setTitle("Edycja Sali");
        alert.setHeaderText(null);
        alert.setContentText("Na pewno chcesz zapisać zmiany?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == okButton)
        {
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.close();
            connector.editAuditorium(chosenTheatre.getTheatreId(), auditoriumNumber, (50 - missingSlotList.size()), missingSlots);
            confirmationAlert.setTitle("Sukces!");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Edycja sali zakończone powodzeniem! ");
            confirmationAlert.showAndWait();
            operationFinished = true;
        } 
        else 
        {
            System.out.println("EditSmallAuditoriumCancel detected!"); //kontrola, czy dziala interakcja.
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
        System.out.println("EditSmallAuditoriumBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showAuditoriumDatabase(email, userName, chosenTheatre);
    }
}
