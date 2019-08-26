/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa glowna - Core.java
 * 
 */

package szaman.playbook;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.view.menu.account.ResetUserPasswordController;
import szaman.playbook.utility.Utility;
import szaman.playbook.view.MainWindowController;
import szaman.playbook.view.WelcomeController;
import szaman.playbook.entity.Account;
import szaman.playbook.view.LoginController;
import szaman.playbook.view.menu.MenuController;
import szaman.playbook.view.menu.account.AccountDatabaseController;
import szaman.playbook.view.menu.account.SelfChangePasswordController;
import szaman.playbook.view.menu.account.DeleteAccountController;
import szaman.playbook.view.menu.archive.ArchiveMenuController;
import szaman.playbook.entity.Play;
import szaman.playbook.view.menu.archive.account.ArchiveAccountDatabaseController;
import szaman.playbook.view.menu.play.PlayDatabaseController;
import szaman.playbook.view.menu.play.AddPlayController;
import szaman.playbook.view.menu.archive.play.ArchivePlayDatabaseController;
import szaman.playbook.entity.Theatre;
import szaman.playbook.view.menu.archive.theatre.ArchiveTheatreDatabaseController;
import szaman.playbook.view.menu.play.DeletePlayController;
import szaman.playbook.view.menu.play.EditPlayController;
import szaman.playbook.view.menu.theatre.AddTheatreController;
import szaman.playbook.view.menu.theatre.DeleteTheatreController;
import szaman.playbook.view.menu.theatre.EditTheatreController;
import szaman.playbook.view.menu.theatre.TheatreDatabaseController;
import szaman.playbook.view.menu.archive.auditorium.ArchiveAuditoriumDatabaseController;
import szaman.playbook.view.menu.archive.auditorium.ShowBigArchiveAuditoriumController;
import szaman.playbook.view.menu.archive.auditorium.ShowMediumArchiveAuditoriumController;
import szaman.playbook.view.menu.archive.auditorium.ShowSmallArchiveAuditoriumController;
import szaman.playbook.view.menu.theatre.auditorium.AddBigAuditoriumController;
import szaman.playbook.view.menu.theatre.auditorium.AddMediumAuditoriumController;
import szaman.playbook.view.menu.theatre.auditorium.AddSmallAuditoriumController;
import szaman.playbook.view.menu.theatre.auditorium.AuditoriumChooserController;
import szaman.playbook.view.menu.theatre.auditorium.AuditoriumDatabaseController;
import szaman.playbook.view.menu.theatre.auditorium.DeleteAuditoriumController;
import szaman.playbook.view.menu.theatre.auditorium.EditBigAuditoriumController;
import szaman.playbook.view.menu.theatre.auditorium.EditMediumAuditoriumController;
import szaman.playbook.view.menu.theatre.auditorium.EditSmallAuditoriumController;
import szaman.playbook.view.menu.theatre.auditorium.ShowBigAuditoriumController;
import szaman.playbook.view.menu.theatre.auditorium.ShowMediumAuditoriumController;
import szaman.playbook.view.menu.theatre.auditorium.ShowSmallAuditoriumController;
import szaman.playbook.entity.Reservation;
import szaman.playbook.entity.Show;
import szaman.playbook.view.menu.account.AddAccountController;
import szaman.playbook.view.menu.account.AdminSelfDeleteAccountController;
import szaman.playbook.view.menu.account.EditAccountController;
import szaman.playbook.view.menu.archive.show.ArchiveShowDatabaseController;
import szaman.playbook.view.menu.show.AddShowController;
import szaman.playbook.view.menu.show.DeleteShowController;
import szaman.playbook.view.menu.show.EditShowController;
import szaman.playbook.view.menu.show.ShowDatabaseController;
import szaman.playbook.view.menu.show.reservation.AddReservationForBigAuditoriumController;
import szaman.playbook.view.menu.show.reservation.AddReservationForMediumAuditoriumController;
import szaman.playbook.view.menu.show.reservation.AddReservationForSmallAuditoriumController;
import szaman.playbook.view.menu.show.reservation.ConfirmEditReservationController;
import szaman.playbook.view.menu.show.reservation.ConfirmReservationController;
import szaman.playbook.view.menu.show.reservation.EditReservationForBigAuditoriumController;
import szaman.playbook.view.menu.show.reservation.EditReservationForMediumAuditoriumController;
import szaman.playbook.view.menu.show.reservation.EditReservationForSmallAuditoriumController;
import szaman.playbook.view.menu.show.reservation.ReservationDatabaseController;
import szaman.playbook.view.menu.show.reservation.ShowReservationsInBigAuditoriumController;
import szaman.playbook.view.menu.show.reservation.ShowReservationsInMediumAuditoriumController;
import szaman.playbook.view.menu.show.reservation.ShowReservationsInSmallAuditoriumController;

// Reszta klas //

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import org.hibernate.HibernateException;
import org.postgresql.util.PSQLException;
import szaman.playbook.view.menu.archive.reservation.ArchiveReservationDatabaseController;
import szaman.playbook.view.menu.show.reservation.DeleteReservationController;

/**
 *
 * @author Szaman
 */

public class Core extends Application 
{
    private Stage primaryStage;
    
    public BorderPane root;
    
    //Start aplikacji.
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        
        this.primaryStage = primaryStage;

        initiallizeMainWindow(); 
    }

    /********************************************************************************************************************************************/
    /*                                                             GLOWNE OKNO                                                                  */
    /********************************************************************************************************************************************/
    
    //Inicjalizacja glownego okna.
    public void initiallizeMainWindow() throws SQLException, HibernateException, PSQLException, PropertyVetoException
    {
        try //Proba wczytania pliku, stworzenia obiektu BorderPane, wskazania kontrolera do FXML i ustanowienie pliku jako glowne okno aplikacji.
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/MainWindowFXML.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            Utility utility = new Utility();
            MainWindowController controller;
            controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();                                                                            //Wyswietlenie primaryStage.
            primaryStage.setResizable(false);                                                               //Zablokowanie opcji rozciagania okna.
            primaryStage.setTitle("Panel Administracyjny PlayBook " + utility.pullVersionWithDate());       //Nadanie nazwy okna.
            showWelcome();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /********************************************************************************************************************************************/
    /*                                                            MENU POWITALNE                                                                */
    /********************************************************************************************************************************************/
    
    //Ekran startowy.
    public void showWelcome() throws SQLException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji.
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/WelcomeFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            WelcomeController controller = loader.getController();
            controller.setMainApp(this);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno logowania.
    public boolean loginAccount(Account account) 
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/LoginFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Logowanie");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            LoginController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAccount(account);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
 
    /********************************************************************************************************************************************/
    /*                                                                    MENU                                                                  */
    /********************************************************************************************************************************************/
 
    //Okno startowe zalogowanego administratora.
    public void showMenu(String email, String name) 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/MenuFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            MenuController controller = loader.getController();
            controller.setMainApp(this, email, name);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    /********************************************************************************************************************************************/
    /*                                                                 DANE KONT                                                                */
    /********************************************************************************************************************************************/
    
    //Okno z danymi uzytkownikow
    public void showAccountDatabase(String email, String name) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/account/AccountDatabaseFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            AccountDatabaseController controller = loader.getController();
            controller.setMainApp(this, email, name);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno dodawania konta.
    public boolean showAddAccount(Account account) 
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/account/AddAccountFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Dodawanie Nowego Konta");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            AddAccountController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAccount(account);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    //Okno dodawania konta.
    public boolean showEditAccount(int accountID) 
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/account/EditAccountFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Aktualizacja Danych Konta");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            EditAccountController controller = loader.getController();
            controller.setDialogStage(dialogStage, accountID);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    //Okno resetu hasla do konta uzytkownika.
    public boolean changeUserPassword(String userEmail, String adminEmail) throws SQLException, ClassNotFoundException
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/account/ResetUserPasswordFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Reset Hasła");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            ResetUserPasswordController controller = loader.getController();
            controller.setDialogStage(dialogStage, userEmail, adminEmail);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    //Okno zmiany hasla wlasnego konta.
    public boolean showSelfChangePassword(String email) throws SQLException, ClassNotFoundException
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/account/SelfChangePasswordFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Zmiana Hasła");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            SelfChangePasswordController controller = loader.getController();
            controller.setDialogStage(dialogStage, email);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    //Okno usuwania konta uzytkownika.
    public boolean deleteAccount(String userEmail, String adminEmail) throws SQLException, ClassNotFoundException
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/account/DeleteAccountFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Usuwanie Konta");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            DeleteAccountController controller = loader.getController();
            controller.setDialogStage(dialogStage, userEmail, adminEmail);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    //Okno usuwania konta uzytkownika.
    public boolean selfDeleteAccount(String adminEmail) throws SQLException, ClassNotFoundException
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/account/AdminSelfDeleteAccountFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Usuwanie Własnego Konta");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            AdminSelfDeleteAccountController controller = loader.getController();
            controller.setDialogStage(dialogStage, adminEmail);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    /********************************************************************************************************************************************/
    /*                                                               ARCHIWUM                                                                   */
    /********************************************************************************************************************************************/
    
    //Okno startowe archiwum.
    public void showArchiveMenu(String email, String name) 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/archive/ArchiveMenuFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ArchiveMenuController controller = loader.getController();
            controller.setMainApp(this, email, name);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno z archiwalnymi danymi uzytkownikow
    public void showArchiveAccountDatabase(String email, String name) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/archive/account/ArchiveAccountDatabaseFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ArchiveAccountDatabaseController controller = loader.getController();
            controller.setMainApp(this, email, name);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno z archiwalnymi danymi sztuk
    public void showArchivePlayDatabase(String email, String name) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/archive/play/ArchivePlayDatabaseFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ArchivePlayDatabaseController controller = loader.getController();
            controller.setMainApp(this, email, name);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno z archiwalnymi danymi teatrow
    public void showArchiveTheatreDatabase(String email, String name) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/archive/theatre/ArchiveTheatreDatabaseFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ArchiveTheatreDatabaseController controller = loader.getController();
            controller.setMainApp(this, email, name);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno z archiwalnymi danymi sal
    public void showArchiveAuditoriumDatabase(String email, String name) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/archive/auditorium/ArchiveAuditoriumDatabaseFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ArchiveAuditoriumDatabaseController controller = loader.getController();
            controller.setMainApp(this, email, name);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno wyswietlania duzej archiwalnej sali.
    public void showShowBigArchiveAuditorium(String email, String name, String missingSlots, String auditoriumInfo) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/archive/auditorium/ShowBigArchiveAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ShowBigArchiveAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, missingSlots, auditoriumInfo);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno wyswietlania sredniej archiwalnej sali.
    public void showShowMediumArchiveAuditorium(String email, String name, String missingSlots, String auditoriumInfo) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/archive/auditorium/ShowMediumArchiveAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ShowMediumArchiveAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, missingSlots, auditoriumInfo);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno wyswietlania malej archiwalnej sali.
    public void showShowSmallArchiveAuditorium(String email, String name, String missingSlots, String auditoriumInfo) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/archive/auditorium/ShowSmallArchiveAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ShowSmallArchiveAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, missingSlots, auditoriumInfo);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno z archiwalnymi danymi uzytkownikow
    public void showArchiveShowDatabase(String email, String name) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/archive/show/ArchiveShowDatabaseFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ArchiveShowDatabaseController controller = loader.getController();
            controller.setMainApp(this, email, name);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno z archiwalnymi danymi rezerwacji.
    public void showArchiveReservationDatabase(String email, String name) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/archive/reservation/ArchiveReservationDatabaseFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ArchiveReservationDatabaseController controller = loader.getController();
            controller.setMainApp(this, email, name);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    /********************************************************************************************************************************************/
    /*                                                                SZTUKI                                                                    */
    /********************************************************************************************************************************************/
    
    //Okno z danymi sztuk
    public void showPlayDatabase(String email, String name) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/play/PlayDatabaseFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            PlayDatabaseController controller = loader.getController();
            controller.setMainApp(this, email, name);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno dodawania sztuki.
    public boolean showAddPlay(Play play) 
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/play/AddPlayFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Dodawanie Nowej Sztuki");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            AddPlayController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPlay(play);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    //Okno edycji sztuki.
    public boolean showEditPlay(int playID) throws SQLException, ClassNotFoundException
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/play/EditPlayFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edycja Sztuki");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            EditPlayController controller = loader.getController();
            controller.setDialogStage(dialogStage, playID);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    //Okno usuwania sztuki.
    public boolean showDeletePlay(String userEmail, int playID) throws SQLException, ClassNotFoundException
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/play/DeletePlayFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Usuwanie Sztuki");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            DeletePlayController controller = loader.getController();
            controller.setDialogStage(dialogStage, playID, userEmail);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    /********************************************************************************************************************************************/
    /*                                                                  TEATRY                                                                  */
    /********************************************************************************************************************************************/
    
    //Okno z danymi teatrów.
    public void showTheatreDatabase(String email, String name) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/TheatreDatabaseFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            TheatreDatabaseController controller = loader.getController();
            controller.setMainApp(this, email, name);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno dodawania teatru.
    public boolean showAddTheatre(Theatre theatre) 
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/AddTheatreFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Dodawanie Nowego Teatru");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            AddTheatreController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTheatre(theatre);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    //Okno edycji danych teatru.
    public boolean showEditTheatre(int theatreID) throws SQLException, ClassNotFoundException
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/EditTheatreFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edycja Teatru");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            EditTheatreController controller = loader.getController();
            controller.setDialogStage(dialogStage, theatreID);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    //Okno usuwania sztuki.
    public boolean showDeleteTheatre(String userEmail, int theatreID) throws SQLException, ClassNotFoundException
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/DeleteTheatreFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Usuwanie Teatru");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            DeleteTheatreController controller = loader.getController();
            controller.setDialogStage(dialogStage, theatreID, userEmail);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    /********************************************************************************************************************************************/
    /*                                                                 SALE                                                                     */
    /********************************************************************************************************************************************/
    
    //Okno z danymi sal.
    public void showAuditoriumDatabase(String email, String name, Theatre theatre) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/auditorium/AuditoriumDatabaseFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            AuditoriumDatabaseController controller = loader.getController();
            controller.setMainApp(this, email, name, theatre);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno wyboru dodawanego typu sali.
    public String showAuditoriumChooser() throws SQLException, ClassNotFoundException
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/auditorium/AuditoriumChooserFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Wybór typu sali");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            AuditoriumChooserController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            return controller.userResponse();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return "IO EXCEPTION";
        }
    }
    
    //Okno dodawania duzej sali.
    public void showAddBigAuditorium(String email, String name, Theatre chosenTheatre, List<Integer> auditoriumNumbers) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/auditorium/AddBigAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            AddBigAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenTheatre, auditoriumNumbers);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno dodawania sredniej sali.
    public void showAddMediumAuditorium(String email, String name, Theatre chosenTheatre, List<Integer> auditoriumNumbers) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/auditorium/AddMediumAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            AddMediumAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenTheatre, auditoriumNumbers);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno dodawania malej sali.
    public void showAddSmallAuditorium(String email, String name, Theatre chosenTheatre, List<Integer> auditoriumNumbers) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/auditorium/AddSmallAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            AddSmallAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenTheatre, auditoriumNumbers);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno edycji duzej sali.
    public void showEditBigAuditorium(String email, String name, Theatre chosenTheatre, String getMissingSlots, String auditoriumInfo) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/auditorium/EditBigAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            EditBigAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenTheatre, getMissingSlots, auditoriumInfo);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno edycji sredniej sali.
    public void showEditMediumAuditorium(String email, String name, Theatre chosenTheatre, String getMissingSlots, String auditoriumInfo) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/auditorium/EditMediumAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            EditMediumAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenTheatre, getMissingSlots, auditoriumInfo);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno edycji malej sali.
    public void showEditSmallAuditorium(String email, String name, Theatre chosenTheatre, String getMissingSlots, String auditoriumInfo) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/auditorium/EditSmallAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            EditSmallAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenTheatre, getMissingSlots, auditoriumInfo);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno wyswietlania duzej sali.
    public void showShowBigAuditorium(String email, String name, Theatre chosenTheatre, String missingSlots, String auditoriumInfo) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/auditorium/ShowBigAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ShowBigAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenTheatre, missingSlots, auditoriumInfo);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno wyswietlania sredniej sali.
    public void showShowMediumAuditorium(String email, String name, Theatre chosenTheatre, String missingSlots, String auditoriumInfo) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/auditorium/ShowMediumAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ShowMediumAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenTheatre, missingSlots, auditoriumInfo);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno wyswietlania malej sali.
    public void showShowSmallAuditorium(String email, String name, Theatre chosenTheatre, String missingSlots, String auditoriumInfo) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/auditorium/ShowSmallAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ShowSmallAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenTheatre, missingSlots, auditoriumInfo);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno usuwania sztuki.
    public boolean showDeleteAuditorium(String userEmail, int auditoriumID) throws SQLException, ClassNotFoundException
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/theatre/auditorium/DeleteAuditoriumFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Usuwanie Sali");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            DeleteAuditoriumController controller = loader.getController();
            controller.setDialogStage(dialogStage, auditoriumID, userEmail);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    /********************************************************************************************************************************************/
    /*                                                                    SEANS                                                                 */
    /********************************************************************************************************************************************/
    
    //Okno z danymi sal.
    public void showShowDatabase(String email, String name) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/ShowDatabaseFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ShowDatabaseController controller = loader.getController();
            controller.setMainApp(this, email, name);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno dodawania teatru.
    public boolean showAddShow(Show show) throws PropertyVetoException, SQLException 
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/AddShowFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Dodawanie Nowego Seansu");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            AddShowController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    //Okno edycji danych seansu.
    public boolean showEditShow(int showID) throws SQLException, ClassNotFoundException, PropertyVetoException, ParseException
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/EditShowFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edycja Seansu");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            EditShowController controller = loader.getController();
            controller.setDialogStage(dialogStage, showID);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    //Okno usuwania seansu.
    public boolean showDeleteShow(String adminEmail, int showID) throws SQLException, ClassNotFoundException
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/DeleteShowFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Usuwanie Seansu");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            DeleteShowController controller = loader.getController();
            controller.setDialogStage(dialogStage, adminEmail, showID);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    /********************************************************************************************************************************************/
    /*                                                                 RESERVATION                                                               */
    /********************************************************************************************************************************************/
    
    //Okno z danymi rezerwacji.
    public void showReservationDatabase(String email, String name, Show chosenShow) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/reservation/ReservationDatabaseFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ReservationDatabaseController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenShow);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno wyswietlania dodawania rezerwacji dla duzej sali.
    public void showAddReservationForBigAuditorium(String email, String name, Show chosenShow) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/reservation/AddReservationForBigAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            AddReservationForBigAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenShow);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno wyswietlania dodawania rezerwacji dla sredniej sali.
    public void showAddReservationForMediumAuditorium(String email, String name, Show chosenShow) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/reservation/AddReservationForMediumAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            AddReservationForMediumAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenShow);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno wyswietlania dodawania rezerwacji dla malej sali.
    public void showAddReservationForSmallAuditorium(String email, String name, Show chosenShow) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/reservation/AddReservationForSmallAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            AddReservationForSmallAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenShow);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno wyswietlania edycji rezerwacji dla duzej sali.
    public void showEditReservationForBigAuditorium(String email, String name, Show chosenShow, Reservation chosenReservation) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/reservation/EditReservationForBigAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            EditReservationForBigAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenShow, chosenReservation);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno wyswietlania edycji rezerwacji dla sredniej sali.
    public void showEditReservationForMediumAuditorium(String email, String name, Show chosenShow, Reservation chosenReservation) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/reservation/EditReservationForMediumAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            EditReservationForMediumAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenShow, chosenReservation);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno wyswietlania edycji rezerwacji dla malej sali.
    public void showEditReservationForSmallAuditorium(String email, String name, Show chosenShow, Reservation chosenReservation) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/reservation/EditReservationForSmallAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            EditReservationForSmallAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenShow, chosenReservation);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }
    
    //Okno wyswietlenia rezerwacji w duzej sali.
    public void showShowReservationsInBigAuditorium(String email, String name, Show chosenShow, List<String> reservedSlotList) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/reservation/ShowReservationsInBigAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ShowReservationsInBigAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenShow, reservedSlotList);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }  
    
    //Okno wyswietlenia rezerwacji w sredniej sali.
    public void showShowReservationsInMediumAuditorium(String email, String name, Show chosenShow, List<String> reservedSlotList) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/reservation/ShowReservationsInMediumAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ShowReservationsInMediumAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenShow, reservedSlotList);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }  
    
    //Okno wyswietlenia rezerwacji w malej sali.
    public void showShowReservationsInSmallAuditorium(String email, String name, Show chosenShow, List<String> reservedSlotList) throws SQLException, ClassNotFoundException, PropertyVetoException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/reservation/ShowReservationsInSmallAuditoriumFXML.fxml"));
            AnchorPane welcome = (AnchorPane) loader.load();
            root.setCenter(welcome);
            ShowReservationsInSmallAuditoriumController controller = loader.getController();
            controller.setMainApp(this, email, name, chosenShow, reservedSlotList);
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
        }
    }  
    
    //Potwierdzenie rezerwacji.
    public boolean showConfirmReservation(int accountID, int showID, int chosenSlotsCount, String chosenSlotsID) throws PropertyVetoException, SQLException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/reservation/ConfirmReservationFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Finalizowanie Rezerwacji");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            ConfirmReservationController controller = loader.getController();
            controller.setDialogStage(dialogStage, accountID, showID, chosenSlotsCount, chosenSlotsID);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    //Potwierdzenie edycji rezerwacji.
    public boolean showConfirmEditReservation(Reservation chosenReservation, int showID, int chosenSlotsCount, String chosenSlotsID) throws PropertyVetoException, SQLException 
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/reservation/ConfirmEditReservationFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Finalizowanie Edycji Rezerwacji");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            ConfirmEditReservationController controller = loader.getController();
            controller.setDialogStage(dialogStage, chosenReservation, showID, chosenSlotsCount, chosenSlotsID);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    //Okno usuwania rezerwacji.
    public boolean showDeleteReservation(String adminEmail, int reservationID) throws SQLException, ClassNotFoundException
    {
        try //Proba wczytania pliku, stworzenia obiektu AnchorPane, wskazania kontrolera do FXML i rzutowania pliku na glowne okno aplikacji
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Core.class.getResource("/fxml/view/menu/show/reservation/DeleteReservationFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Usuwanie Rezerwacji");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            DeleteReservationController controller = loader.getController();
            controller.setDialogStage(dialogStage, adminEmail, reservationID);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    /********************************************************************************************************************************************/
    /*                                                                   RESZTA                                                                 */
    /********************************************************************************************************************************************/
        
    public Stage getPrimaryStage()
    {
        return primaryStage;
    }
     
    public Core() 
    {
    }
    
    //Metoda main.
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
