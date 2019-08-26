/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa kontrolerska glownego okna aplikacji - MainWindowController.java.
 * 
 */

package szaman.playbook.view;

// Laczenie z reszta wlasnych klas //

import szaman.playbook.Core;

// Reszta klas //

/**
 *
 * @author Szaman
 */

public class MainWindowController 
{
    //Referencja do glownej aplikacji.
    private Core mainApp;

    //Referencja glownego pliku do samego siebie.
    public void setMainApp(Core mainApp) 
    {
        this.mainApp = mainApp;
    }
}