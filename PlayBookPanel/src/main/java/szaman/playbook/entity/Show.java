/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa modelu Show (mapowanie Hibernate) - Show.java.
 * 
 */

// Generated 2017-11-16 02:39:57 by Hibernate Tools 4.3.1

package szaman.playbook.entity;

// Laczenie z reszta wlasnych klas //



// Reszta klas //

import java.util.Date;
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

public class Show implements java.io.Serializable 
{
    private int showId;
    private Auditorium auditorium;
    private Play play;
    private float price;
    private Date date;
    private String startHour;
    private int length;
    private int slotsLeft;
    private Set reservations = new HashSet(0);
    
    private String theatreAndAuditoriumData;
    private String playName;
     
    private IntegerProperty showIdProperty = null;
    private StringProperty priceProperty = null;
    private StringProperty dateProperty = null;
    private StringProperty startHourProperty = null;
    private StringProperty lengthProperty = null;
    private StringProperty slotsLeftProperty = null;
    private StringProperty theatreAndAuditoriumDataProperty = null;
    private StringProperty playNameProperty = null;

    public Show() 
    {
        
    }
	
    public Show(int showId, Auditorium auditorium, Play play, float price, Date date, String startHour, int length, int slotsLeft) 
    {
        this.showId = showId;
        this.auditorium = auditorium;
        this.play = play;
        this.price = price;
        this.date = date;
        this.startHour = startHour;
        this.length = length;
        this.slotsLeft = slotsLeft;
    }
    
    public Show(int showId, Auditorium auditorium, Play play, float price, Date date, String startHour, int length, int slotsLeft, Set reservations) 
    {
       this.showId = showId;
       this.auditorium = auditorium;
       this.play = play;
       this.price = price;
       this.date = date;
       this.startHour = startHour;
       this.length = length;
       this.slotsLeft = slotsLeft;
       this.reservations = reservations;
    }
    
    public Show(int showId, float price, Date date, String startHour, int length, int slotsLeft, String theatreAndAuditoriumData, String playName) 
    {
       this.showId = showId;
       this.price = price;
       this.date = date;
       this.startHour = startHour;
       this.length = length;
       this.slotsLeft = slotsLeft;
       this.theatreAndAuditoriumData = theatreAndAuditoriumData;
       this.playName = playName;
    }
   
    public int getShowId() 
    {
        return this.showId;
    }
    
    public void setShowId(int showId) 
    {
        this.showId = showId;
    }
    
    public Auditorium getAuditorium()
    {
        return this.auditorium;
    }
    
    public void setAuditorium(Auditorium auditorium) 
    {
        this.auditorium = auditorium;
    }
    
    public Play getPlay() 
    {
        return this.play;
    }
    
    public void setPlay(Play play) 
    {
        this.play = play;
    }
    
    public float getPrice() 
    {
        return this.price;
    }
    
    public void setPrice(float price) 
    {
        this.price = price;
    }
    
    public Date getDate() 
    {
        return this.date;
    }
    
    public void setDate(Date date) 
    {
        this.date = date;
    }
    
    public String getStartHour() 
    {
        return this.startHour;
    }
    
    public void setStartHour(String startHour) 
    {
        this.startHour = startHour;
    }
    
    public String getTheatreAndAuditoriumData() 
    {
        return this.theatreAndAuditoriumData;
    }
    
    public void setTheatreAndAuditoriumData(String theatreAndAuditoriumData) 
    {
        this.theatreAndAuditoriumData = theatreAndAuditoriumData;
    }
    
    public String getPlayName() 
    {
        return this.playName;
    }
    
    public void setPlayName(String playName) 
    {
        this.playName = playName;
    }
    
    public int getLength() 
    {
        return this.length;
    }
    
    public void setLength(int length) 
    {
        this.length = length;
    }
    
    public int getSlotsLeft() 
    {
        return this.slotsLeft;
    }
    
    public void setSlotsLeft(int slotsLeft) 
    {
        this.slotsLeft = slotsLeft;
    }
    
    public Set getReservations() 
    {
        return this.reservations;
    }
    
    public void setReservations(Set reservations) 
    {
        this.reservations = reservations;
    }

    /*Properties*/
    
    public IntegerProperty showIdProperty() 
    {
        showIdProperty  = new SimpleIntegerProperty(showId);
        return showIdProperty;
    }
    
    public StringProperty priceProperty() 
    {
        String stringedPrice = Float.toString(price);
        priceProperty  = new SimpleStringProperty(stringedPrice);
        return priceProperty;
    }
    
    public StringProperty dateProperty() 
    {
        String stringedDate = date.toString();
        dateProperty  = new SimpleStringProperty(stringedDate);
        return dateProperty;
    }
    
    public StringProperty startHourProperty() 
    {
        startHourProperty  = new SimpleStringProperty(startHour);
        return startHourProperty;
    }
    
    public StringProperty lengthProperty() 
    {
        String stringedLength = Integer.toString(length);
        lengthProperty  = new SimpleStringProperty(stringedLength);
        return lengthProperty;
    }

    public StringProperty slotsLeftProperty() 
    {
        String stringedSlotsLeft = Integer.toString(slotsLeft);
        slotsLeftProperty  = new SimpleStringProperty(stringedSlotsLeft);
        return slotsLeftProperty;
    }
    
    public StringProperty theatreAndAuditoriumDataProperty() 
    {
        theatreAndAuditoriumDataProperty  = new SimpleStringProperty(theatreAndAuditoriumData);
        return theatreAndAuditoriumDataProperty;
    }
    
    public StringProperty playNameProperty() 
    {
        playNameProperty  = new SimpleStringProperty(playName);
        return playNameProperty;
    }    
}


