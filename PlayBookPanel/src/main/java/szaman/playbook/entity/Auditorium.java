/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa modelu Auditorium (mapowanie Hibernate) - Auditorium.java.
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

public class Auditorium  implements java.io.Serializable 
{
    private int auditoriumId;
    private Theatre theatre;
    private int auditoriumNumber;
    private int noSlots;
    private String missingSlots;
    private String auditoriumType;
    private Set shows = new HashSet(0);
     
    private IntegerProperty auditoriumIdProperty = null;
    private StringProperty missingSlotsProperty = null;
    private StringProperty auditoriumTypeProperty = null;
    private StringProperty auditoriumNumberProperty = null;
    private StringProperty noSlotsProperty = null;

    public Auditorium() 
    {
        
    }

    public Auditorium(int auditoriumId, Theatre theatre, int auditoriumNumber, int noSlots, String missingSlots, String auditoriumType) 
    {
        this.auditoriumId = auditoriumId;
        this.theatre = theatre;
        this.auditoriumNumber = auditoriumNumber;
        this.noSlots = noSlots;
        this.missingSlots = missingSlots;
        this.auditoriumType = auditoriumType;
    }
    
    public Auditorium(int auditoriumId, Theatre theatre, int auditoriumNumber, int noSlots, String missingSlots, String auditoriumType, Set shows) 
    {
       this.auditoriumId = auditoriumId;
       this.theatre = theatre;
       this.auditoriumNumber = auditoriumNumber;
       this.noSlots = noSlots;
       this.missingSlots = missingSlots;
       this.shows = shows;
       this.auditoriumType = auditoriumType;
    }
   
    public int getAuditoriumId() 
    {
        return this.auditoriumId;
    }
    
    public void setAuditoriumId(int auditoriumId) 
    {
        this.auditoriumId = auditoriumId;
    }
        
    public Theatre getTheatre() 
    {
        return this.theatre;
    }
    
    public void setTheatre(Theatre theatre) 
    {
        this.theatre = theatre;
    }
    
    public int getAuditoriumNumber() 
    {
        return this.auditoriumNumber;
    }
    
    public void setAuditoriumNumber(int auditoriumNumber) 
    {
        this.auditoriumNumber = auditoriumNumber;
    }
    
    public int getNoSlots() 
    {
        return this.noSlots;
    }
    
    public void setNoSlots(int noSlots) 
    {
        this.noSlots = noSlots;
    }
    
    public String getMissingSlots() 
    {
        return this.missingSlots;
    }
    
    public void setMissingSlots(String missingSlots) 
    {
        this.missingSlots = missingSlots;
    }
    
    public String getAuditoriumType() 
    {
        return this.auditoriumType;
    }
    
    public void setAuditoriumType(String auditoriumType) 
    {
        this.auditoriumType = auditoriumType;
    }
    
    public Set getShows() 
    {
        return this.shows;
    }
    
    public void setShows(Set shows) 
    {
        this.shows = shows;
    }

    //Pull danych teatru dla sali.
    public final String pullTheatreData(Theatre theatre)
    {
        String theatreName = theatre.getName();
        String theatreCity = theatre.getCity();
        return theatreName + ", " + theatreCity;
    }
    
    /*Properties*/

    public IntegerProperty auditoriumIdProperty() 
    {
        auditoriumIdProperty  = new SimpleIntegerProperty(auditoriumId);
        return auditoriumIdProperty;
    }
    
    public StringProperty missingSlotsProperty() 
    {
        missingSlotsProperty  = new SimpleStringProperty(missingSlots);
        return missingSlotsProperty;
    }
    
    public StringProperty auditoriumTypeProperty() 
    {
        auditoriumTypeProperty  = new SimpleStringProperty(auditoriumType);
        return auditoriumTypeProperty;
    }
    
    public StringProperty auditoriumNumberProperty() 
    {
        String stringedAuditoriumNumber = Integer.toString(auditoriumNumber);
        auditoriumNumberProperty  = new SimpleStringProperty(stringedAuditoriumNumber);
        return auditoriumNumberProperty;
    }

    public StringProperty noSlotsProperty() 
    {
        String stringedNOSlots = Integer.toString(noSlots);
        noSlotsProperty  = new SimpleStringProperty(stringedNOSlots);
        return noSlotsProperty;
    }
}


