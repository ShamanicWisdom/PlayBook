/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa modelu Theatre (mapowanie Hibernate) - Theatre.java.
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

public class Theatre  implements java.io.Serializable 
{


    private int theatreId;
    private String name;
    private String voivodeship;
    private String city;
    private String street;
    private Integer phoneNumber;
    private Set auditoriums = new HashSet(0);
    
    private IntegerProperty theatreIdProperty = null;
    private StringProperty nameProperty = null;
    private StringProperty voivodeshipProperty = null;
    private StringProperty cityProperty = null;
    private StringProperty streetProperty = null;
    private StringProperty phoneNumberProperty = null;

    public Theatre() 
    {
        
    }

	
    public Theatre(int theatreId, String name, String voivodeship, String city, String street, int phoneNumber) 
    {
        this.theatreId = theatreId;
        this.name = name;
        this.voivodeship = voivodeship;
        this.city = city;
        this.street = street;
        this.phoneNumber = phoneNumber;
    }
    
    public Theatre(int theatreId, String name, String voivodeship, String city, String street, int phoneNumber, Set auditoriums) 
    {
       this.theatreId = theatreId;
       this.name = name;
       this.voivodeship = voivodeship;
       this.city = city;
       this.street = street;
       this.phoneNumber = phoneNumber;
       this.auditoriums = auditoriums;
    }
   
    public int getTheatreId() 
    {
        return this.theatreId;
    }
    
    public void setTheatreId(int theatreId) 
    {
        this.theatreId = theatreId;
    }
    
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
    
    public String getVoivodeship() 
    {
        return this.voivodeship;
    }
    
    public void setVoivodeship(String voivodeship) 
    {
        this.voivodeship = voivodeship;
    }
    
    public String getCity() 
    {
        return this.city;
    }
    
    public void setCity(String city) 
    {
        this.city = city;
    }
    
    public String getStreet() 
    {
        return this.street;
    }
    
    public void setStreet(String street) 
    {
        this.street = street;
    }
    
    public int getPhoneNumber() 
    {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(int phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }
    
    public Set getAuditoriums() 
    {
        return this.auditoriums;
    }
    
    public void setAuditoriums(Set auditoriums)
    {
        this.auditoriums = auditoriums;
    }

    /*Properties*/
    
    public IntegerProperty theatreIdProperty() 
    {
        theatreIdProperty  = new SimpleIntegerProperty(theatreId);
        return theatreIdProperty;
    }
    
    public StringProperty nameProperty() 
    {
        nameProperty  = new SimpleStringProperty(name);
        return nameProperty;
    }
    
    public StringProperty voivodeshipProperty() 
    {
        voivodeshipProperty  = new SimpleStringProperty(voivodeship);
        return voivodeshipProperty;
    }
    
    public StringProperty cityProperty() 
    {
        cityProperty  = new SimpleStringProperty(city);
        return cityProperty;
    }
    
    public StringProperty streetProperty() 
    {
        streetProperty  = new SimpleStringProperty(street);
        return streetProperty;
    }

    public StringProperty phoneNumberProperty() 
    {
        String stringedPhoneNumber = Integer.toString(phoneNumber);
        phoneNumberProperty  = new SimpleStringProperty(stringedPhoneNumber);
        return phoneNumberProperty;
    }
    
}


