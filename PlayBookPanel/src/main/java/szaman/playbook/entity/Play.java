/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa modelu Play (mapowanie Hibernate) - Play.java.
 * 
 */

// Generated 2017-11-16 02:39:57 by Hibernate Tools 4.3.1

package szaman.playbook.entity;

// Laczenie z reszta wlasnych klas //



// Reszta klas //

import java.util.HashSet;
import java.util.Set;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Szaman
 */

public class Play  implements java.io.Serializable 
{
    private int playId;
    private String genre;
    private String name;
    private String description;
    private Set shows = new HashSet(0);
     
    private IntegerProperty playIdProperty = null;
    private StringProperty nameProperty = null;
    private StringProperty descriptionProperty = null;
    private StringProperty genreProperty = null;
    private StringProperty actorsProperty = null;
    

    public Play() 
    {
        
    }
	
    public Play(int playId, String genre, String name, String description) 
    {
        this.playId = playId;
        this.genre = genre;
        this.name = name;
        this.description = description;
    }
    
    public Play(int playId, String genre, String name, String description, Set shows) 
    {
       this.playId = playId;
       this.genre = genre;
       this.name = name;
       this.description = description;
       this.shows = shows;
    }
   
    public int getPlayId() 
    {
        return this.playId;
    }
    
    public void setPlayId(int playId) 
    {
        this.playId = playId;
    }
    
    public String getGenre() 
    {
        return this.genre;
    }
    
    public void setGenre(String genre) 
    {
        this.genre = genre;
    }
    
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
    
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
    
    public Set getShows() 
    {
        return this.shows;
    }
    
    public void setShows(Set shows) 
    {
        this.shows = shows;
    }

    /*Properties*/
    
    public IntegerProperty playIdProperty() 
    {
        playIdProperty  = new SimpleIntegerProperty(playId);
        return playIdProperty;
    }
    
    public StringProperty nameProperty() 
    {
        nameProperty  = new SimpleStringProperty(name);
        return nameProperty;
    }
    
    public StringProperty descriptionProperty() 
    {
        descriptionProperty  = new SimpleStringProperty(description);
        return descriptionProperty;
    }
    
    public StringProperty genreProperty() 
    {
        genreProperty  = new SimpleStringProperty(genre);
        return genreProperty;
    }
}


