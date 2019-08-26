/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa modelu ArchivePlay (mapowanie Hibernate) - ArchivePlay.java.
 * 
 */

package szaman.playbook.entity.archive;

// Laczenie z reszta wlasnych klas //

// Reszta klas //

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Szaman
 */

public class ArchivePlay  implements java.io.Serializable 
{
    private int playId;
    private String genre;
    private String name;
    private String description;
    private String deleteDate;
     
    private IntegerProperty playIdProperty = null;
    private StringProperty nameProperty = null;
    private StringProperty descriptionProperty = null;
    private StringProperty genreProperty = null;
    private StringProperty deleteDateProperty = null;

    public ArchivePlay() 
    {
        
    }
	
    public ArchivePlay(int playId, String genre, String name, String description, String deleteDate) 
    {
        this.playId = playId;
        this.genre = genre;
        this.name = name;
        this.description = description;
        this.deleteDate = deleteDate;
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
    
    public String getDeleteDate() 
    {
        return this.deleteDate;
    }
    
    public void setDeleteDate(String deleteDate) 
    {
        this.deleteDate = deleteDate;
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
    
    public StringProperty deleteDateProperty() 
    {
        deleteDateProperty  = new SimpleStringProperty(deleteDate);
        return deleteDateProperty;
    }
}


