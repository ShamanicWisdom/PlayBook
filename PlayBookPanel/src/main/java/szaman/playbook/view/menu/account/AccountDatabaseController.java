/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska bazy danych uzytkownikow - AccountDatabaseController.java.
 * 
 */

package szaman.playbook.view.menu.account;

// Laczenie z reszta wlasnych klas //

import java.beans.PropertyVetoException;
import szaman.playbook.Core;
import szaman.playbook.entity.Account;
import szaman.playbook.utility.HibernateConnector;

// Reszta klas //

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.HibernateException;

/**
 * 
 *
 * @author Szaman
 */

public class AccountDatabaseController
{
    @FXML
    private Label nameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label userRoleLabel;
    
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    
    @FXML
    private TableView<Account> accountDatabaseTable;
    @FXML
    private TableColumn<Account, String> emailColumn;
    @FXML
    private TableColumn<Account, String> nameColumn;
    @FXML
    private TableColumn<Account, String> surnameColumn;
    
    private final ObservableList<Account> dataGatherer = FXCollections.observableArrayList();
    
    HibernateConnector connector = new HibernateConnector();
    
    Boolean connectionValidation = false;
       
    private Core mainApp;
    
    Account account;
    
    private int accountID;
    
    private String email = null; //Przechowuje informacje o zalogowanym koncie (w formie adresu mailowego)
    private String userName = null;   
    private String userRole = null;
    private String userEmail = null;
    
    private String accountRole = null;
    
    //Inicjalizacja danych do tabeli.
    @FXML
    private void initialize() 
    {
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty()); 
        accountDatabaseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showAccountDetails(newValue));

    }
    
    public void setMainApp(Core mainApp, String email, String userName) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        this.mainApp = mainApp;
        this.email = email; //Przypisanie adresu mailowego z poprzedniego okna.
        this.userName = userName;
        accountRole = connector.getAccountRole(email);
        if(accountRole.equals("operator")) //jesli zalogowane jest konto operatora - chowamy opcje dodawania i edycji kont.
        {
            addButton.setDisable(true);
            addButton.setVisible(false);
            editButton.setDisable(true);
            editButton.setVisible(false);
        }
        dataHarvester();
        accountDatabaseTable.setItems(dataGatherer);
    }
        
    /*************************************************************************************************************************************/

    public void dataHarvester() throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        List<Account> accountList = null;
        try
        {
            connectionValidation = connector.testConnection();
            if(connectionValidation == true)
            {
                System.out.println("DB Connected!");
                accountList = connector.pullAccountList();
                if(accountList.isEmpty())
                {
                    System.out.println("Brak danych");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setHeaderText("Brak danych!");
                    alert.setContentText("Brak danych w bazie!" );
                    alert.showAndWait();
                }
                else
                {
                    for(Account a : accountList)
                    {
                        dataGatherer.add(a);
                    }
                }
            }
            else
            {
                throw new SQLException();
            }       
        }
        //W przypadku braku polaczenia wyrzuci SQLException (ping) lub HibernateException (pobieracz danych)
        catch (HibernateException | SQLException e) 
        {
            System.out.println("DB Connection could not been established!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Problem!");
            alert.setContentText("Nie można pobrać danych z bazy! \nSpróbuj ponownie później." );
            alert.showAndWait();
        }     
    }
    
    
    /*************************************************************************************************************************************/  
    
    //Metoda do wyswietlenia wszystkich danych o uzytkowniku.
    private void showAccountDetails(Account account) 
    {
        if (account != null) 
        {
            nameLabel.setText(account.getName());
            surnameLabel.setText(account.getSurname());
            emailLabel.setText(account.getEmail());
            if(accountRole.equals("admin"))
            {
                phoneNumberLabel.setText(account.getPhoneNumber().toString());
            }
            else
            {
                if(account.getRole().equals("admin"))
                {     
                    phoneNumberLabel.setText("Zawartość ukryta");
                }
                else
                {
                    if(!(email.equals(account.getEmail())) && account.getRole().equals("operator"))
                    {                
                        phoneNumberLabel.setText("Zawartość ukryta");
                    }
                    else
                    {
                        phoneNumberLabel.setText(account.getPhoneNumber().toString());
                    }
                }
            }
            if(account.getRole().equals("admin"))
            {
                userRoleLabel.setText("(Poziom 2) - " + account.getRole());
            }
            else
            {
                if(account.getRole().equals("operator"))
                {
                    userRoleLabel.setText("(Poziom 1) - " + account.getRole());
                }
                else
                {
                    userRoleLabel.setText("(Poziom 0) - " + account.getRole());
                }
            }    
            userRole = account.getRole();
            userEmail = account.getEmail();
            accountID = account.getAccountId();
        } 
        else 
        {
            nameLabel.setText("");
            surnameLabel.setText("");
            emailLabel.setText("");
            phoneNumberLabel.setText("");
            userRoleLabel.setText(""); 
            userRole = null;
            userEmail = null;
            accountID = 0;
        }
    }
    
    //Skrypt dla guzika Add.
    @FXML
    private void handleAdd(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException
    {
        System.out.println("AddAccount detected!"); //kontrola, czy dziala interakcja.
        Account temp = new Account();
        boolean okClicked = mainApp.showAddAccount(temp);
        if (okClicked) 
        {
            System.out.println("AddAccountOK detected!"); //kontrola, czy dziala interakcja.
            accountDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
            dataHarvester(); //Pobranie swiezych danych.
            accountDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
            okClicked = false;
        }
        System.out.println("AddAccountCancel detected!"); //kontrola, czy dziala interakcja.
    }
    
    //Skrypt dla guzika Edit.
    @FXML
    private void handleEdit(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException
    {
        boolean okClicked = false;
        int selectedIndex = accountDatabaseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) 
        {
            int persistedID = 0;
            if(userEmail.equals(email))
            {
                persistedID = accountID;
            }
            System.out.println("AccountEdit detected!");
            okClicked = mainApp.showEditAccount(accountID);    
            accountDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
            dataHarvester(); //Pobranie swiezych danych.
            accountDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
            if(persistedID != 0)
            {
                List<Account> accountList = connector.pullAccountData(persistedID);
                for(Account accountData : accountList)
                {
                    email = accountData.getEmail();
                    userName = accountData.getName();
                    if(!accountData.getRole().equals("admin"))
                    {
                        System.out.println("You demoted your account level!");
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                        alert.initOwner(mainApp.getPrimaryStage());
                        alert.setHeaderText("Zmiana Poziomu Konta!");
                        alert.setContentText("W wyniku utraty poziomu administratora następuje wylogowanie!");
                        alert.showAndWait();
                        mainApp.showWelcome();
                    }
                }
            }
            okClicked = false;
        }
        else 
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
            alert.setHeaderText("Brak konta do aktualizacji danych!");
            alert.setContentText("Proszę wybrać konto z tabeli!");
            alert.showAndWait();
        }
    } 
    
    //Skrypt dla guzika PasswordReset.
    @FXML
    private void handlePasswordReset(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("PasswordReset detected!"); //kontrola, czy dziala interakcja.
        boolean okClicked = false;
        System.out.println(email);
        {
            int selectedIndex = accountDatabaseTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) 
            {
                if(userRole.equals("user"))
                {
                    System.out.println("User Clicked!");
                    okClicked = mainApp.changeUserPassword(userEmail, email);
                    if(okClicked)
                    {              
                        accountDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
                        dataHarvester(); //Pobranie swiezych danych.
                        accountDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
                        okClicked = false;
                    }
                    else
                    {
                        System.out.println("ResetUserPasswordCancel detected!"); //kontrola, czy dziala interakcja.   
                    }      
                }
                else
                {
                    if(accountRole.equals("operator"))
                    {
                        if(userRole.equals("operator"))
                        {
                            System.out.println("Operator Clicked!");
                            if(userEmail.equals(email))
                            {
                                System.out.println("Operator Self Account Targetted!");
                                okClicked = mainApp.showSelfChangePassword(email);
                                if(okClicked)
                                {              
                                    accountDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
                                    dataHarvester(); //Pobranie swiezych danych.
                                    accountDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
                                    okClicked = false;
                                }
                                else
                                {
                                    System.out.println("Operator SelfChangePasswordCancel detected!"); //kontrola, czy dziala interakcja.   
                                }                        
                            }
                            else
                            {
                                System.out.println("Other Operator Account Targetted!");
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                                alert.initOwner(mainApp.getPrimaryStage());
                                alert.setHeaderText("Brak dostępu!");
                                alert.setContentText("Nie masz uprawnień do zmiany hasła innego operatora!");
                                alert.showAndWait();
                            }
                        }
                        else
                        {
                                System.out.println("Admin Account Targetted!");
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                                alert.initOwner(mainApp.getPrimaryStage());
                                alert.setHeaderText("Brak dostępu!");
                                alert.setContentText("Nie masz uprawnień do zmiany hasła administratora!");
                                alert.showAndWait();
                        }
                    }
                    else
                    {
                        System.out.println("Admin Clicked!");
                        if(userEmail.equals(email))
                        {
                            System.out.println("Admin Self Account Targetted!");
                            okClicked = mainApp.showSelfChangePassword(email);
                            if(okClicked)
                            {              
                                accountDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
                                dataHarvester(); //Pobranie swiezych danych.
                                accountDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
                                okClicked = false;
                            }
                            else
                            {
                                System.out.println("AdminSelfChangePasswordCancel detected!"); //kontrola, czy dziala interakcja.   
                            }                        
                        }
                        else
                        {
                            System.out.println("Admin Clicked Other User!");
                            okClicked = mainApp.changeUserPassword(userEmail, email);
                            if(okClicked)
                            {              
                                accountDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
                                dataHarvester(); //Pobranie swiezych danych.
                                accountDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
                                okClicked = false;
                            }
                            else
                            {
                                System.out.println("ResetUserPasswordCancel detected!"); //kontrola, czy dziala interakcja.   
                            } 
                        }
                    }
                }
            } 
            else 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setHeaderText("Brak konta do modyfikacji!");
                alert.setContentText("Proszę wybrać konto z tabeli!");
                alert.showAndWait();
            }
        }
    } 
    
    //Skrypt dla guzika Delete.
    @FXML
    private void handleDelete(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, PropertyVetoException 
    {
        System.out.println("DeleteAccount detected!"); //kontrola, czy dziala interakcja.
        boolean okClicked = false;
        System.out.println(email);
        {
            int selectedIndex = accountDatabaseTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) 
            {
                if(userRole.equals("user"))
                {
                    System.out.println("User Clicked!");
                    okClicked = mainApp.deleteAccount(userEmail, email);
                    if(okClicked)
                    {              
                        accountDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
                        dataHarvester(); //Pobranie swiezych danych.
                        accountDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
                        okClicked = false;
                    }
                    else
                    {
                        System.out.println("DeleteUserCancel detected!"); //kontrola, czy dziala interakcja.   
                    }      
                }
                else
                {
                    if(accountRole.equals("operator"))
                    {
                        if(userRole.equals("operator"))
                        {
                            System.out.println("Operator Clicked!");
                            if(userEmail.equals(email))
                            {
                                System.out.println("Operator Self Account Targetted!");
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                                alert.initOwner(mainApp.getPrimaryStage());
                                alert.setHeaderText("Brak dostępu!");
                                alert.setContentText("Nie masz uprawnień do usunięcia własnego konta!");
                                alert.showAndWait();                     
                            }
                            else
                            {
                                System.out.println("Other Operator Account Targetted!");
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                                alert.initOwner(mainApp.getPrimaryStage());
                                alert.setHeaderText("Brak dostępu!");
                                alert.setContentText("Nie masz uprawnień do usunięcia konta innego operatora!");
                                alert.showAndWait();
                            }
                        }
                        else
                        {
                                System.out.println("Admin Account Targetted!");
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                                alert.initOwner(mainApp.getPrimaryStage());
                                alert.setHeaderText("Brak dostępu!");
                                alert.setContentText("Nie masz uprawnień do usunięcia konta administratora!");
                                alert.showAndWait();
                        }
                    }
                    else
                    {
                        System.out.println("Admin Clicked!");
                        if(userEmail.equals(email))
                        {
                            System.out.println("Admin Self Account Targetted!");
                            okClicked = mainApp.selfDeleteAccount(email);
                            if(okClicked)
                            {              
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                                alert.initOwner(mainApp.getPrimaryStage());
                                alert.setHeaderText("Konto zostało usunięte!");
                                alert.setContentText("Następuje wylogowanie...");
                                alert.showAndWait();
                                mainApp.showWelcome();
                            }
                            else
                            {
                                System.out.println("AdminSelfDeleteCancel detected!"); //kontrola, czy dziala interakcja.   
                            }                        
                        }
                        else
                        {
                            System.out.println("Admin Clicked Operator Or Other Admin!");
                            okClicked = mainApp.deleteAccount(userEmail, email);
                            if(okClicked)
                            {              
                                accountDatabaseTable.getItems().clear(); //Oczyszczenie tabeli.
                                dataHarvester(); //Pobranie swiezych danych.
                                accountDatabaseTable.setItems(dataGatherer); //Wczytanie swiezych danych.
                                okClicked = false;
                            }
                            else
                            {
                                System.out.println("AdminDeleteOtherUser - Cancel detected!"); //kontrola, czy dziala interakcja.   
                            } 
                        }
                    }
                }
            } 
            else 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setHeaderText("Brak konta do modyfikacji!");
                alert.setContentText("Proszę wybrać konto z tabeli!");
                alert.showAndWait();
            }
        }
    }
    
    //Skrypt dla guzika Back.
    @FXML
    private void handleBack(ActionEvent event) throws IOException 
    {
        System.out.println("MenuBack detected!"); //kontrola, czy dziala interakcja.
        mainApp.showMenu(email, userName);
    }
}
