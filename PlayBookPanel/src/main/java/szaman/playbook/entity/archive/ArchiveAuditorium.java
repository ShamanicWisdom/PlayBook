/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa modelu ArchiveAuditorium (mapowanie Hibernate) - ArchiveAuditorium.java.
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

public class ArchiveAuditorium  implements java.io.Serializable 
{
    private int auditoriumId;
    private int theatreId;
    private int auditoriumNumber;
    private int noSlots;
    private String missingSlots;
    private String auditoriumType;
    private String deleteDate;
     
    private IntegerProperty auditoriumIdProperty = null;
    private StringProperty theatreIdProperty = null;
    private StringProperty missingSlotsProperty = null;
    private StringProperty auditoriumTypeProperty = null;
    private StringProperty auditoriumNumberProperty = null;
    private StringProperty noSlotsProperty = null;
    private StringProperty deleteDateProperty = null;

    public ArchiveAuditorium() 
    {
        
    }

    public ArchiveAuditorium(int auditoriumId, int theatreId, int auditoriumNumber, int noSlots, String missingSlots, String auditoriumType, String deleteDate) 
    {
        this.auditoriumId = auditoriumId;
        this.theatreId = theatreId;
        this.auditoriumNumber = auditoriumNumber;
        this.noSlots = noSlots;
        this.missingSlots = missingSlots;
        this.auditoriumType = auditoriumType;
        this.deleteDate = deleteDate;
    }
   
    public int getAuditoriumId() 
    {
        return this.auditoriumId;
    }
    
    public void setAuditoriumId(int auditoriumId) 
    {
        this.auditoriumId = auditoriumId;
    }
        
    public int getTheatreId() 
    {
        return this.theatreId;
    }
    
    public void setTheatreId(int theatreId) 
    {
        this.theatreId = theatreId;
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
    
    public String getDeleteDate() 
    {
        return this.deleteDate;
    }
    
    public void setDeleteDate(String deleteDate) 
    {
        this.deleteDate = deleteDate;
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
    
    public StringProperty theatreIdProperty() 
    {
        String stringedTheatreId = Integer.toString(theatreId);
        theatreIdProperty  = new SimpleStringProperty(stringedTheatreId);
        return theatreIdProperty;
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
    
    public StringProperty deleteDateProperty() 
    {
        deleteDateProperty  = new SimpleStringProperty(deleteDate);
        return deleteDateProperty;
    }   
}


