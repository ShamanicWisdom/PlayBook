/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa modelu ArchiveReservation (mapowanie Hibernate) - ArchiveReservation.java.
 * 
 */

// Generated 2017-11-16 02:39:57 by Hibernate Tools 4.3.1

package szaman.playbook.entity.archive;

// Laczenie z reszta wlasnych klas //



// Reszta klas //

import szaman.playbook.entity.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Szaman
 */

public class ArchiveReservation  implements java.io.Serializable 
{
    private int reservationId;
    private String accountData;
    private String showData;
    private int noSlots;
    private String slotsId;
    private int noNormal;
    private int noHalf;
    private float price;
    private String deleteDate;
    
    private StringProperty reservationIdProperty = null;
    private StringProperty noSlotsProperty = null;
    private StringProperty slotsIdProperty = null;
    private StringProperty noNormalProperty = null;
    private StringProperty noHalfProperty = null;
    private StringProperty priceProperty = null;
    private StringProperty showDataProperty = null;
    private StringProperty accountDataProperty = null;
    private StringProperty deleteDateProperty = null;
    
    public ArchiveReservation() 
    {
        
    }

    public ArchiveReservation(int reservationId, String accountData, String showData, int noSlots, String slotsId, int noNormal, int noHalf, float price, String deleteDate) 
    {
       this.reservationId = reservationId;
       this.accountData = accountData;
       this.showData = showData;
       this.noSlots = noSlots;
       this.slotsId = slotsId;
       this.noNormal = noNormal;
       this.noHalf = noHalf;
       this.price = price;
       this.deleteDate = deleteDate;
    }
    
    public int getReservationId() 
    {
        return this.reservationId;
    }
    
    public void setReservationId(int reservationId) 
    {
        this.reservationId = reservationId;
    }
    
    public int getNoSlots() 
    {
        return this.noSlots;
    }
    
    public void setNoSlots(int noSlots) 
    {
        this.noSlots = noSlots;
    }
    
    public String getSlotsId() 
    {
        return this.slotsId;
    }
    
    public void setSlotsId(String slotsId) 
    {
        this.slotsId = slotsId;
    }
    
    public int getNoNormal() 
    {
        return this.noNormal;
    }
    
    public void setNoNormal(int noNormal) 
    {
        this.noNormal = noNormal;
    }
    
    public int getNoHalf() 
    {
        return this.noHalf;
    }
    
    public void setNoHalf(int noHalf) 
    {
        this.noHalf = noHalf;
    }
    
    public float getPrice() 
    {
        return this.price;
    }
    
    public void setPrice(float price) 
    {
        this.price = price;
    }

    public String getAccountData() 
    {
        return this.accountData;
    }
    
    public void setAccountData(String accountData) 
    {
        this.accountData = accountData;
    }
    public String getShowData() 
    {
        return this.showData;
    }
    
    public void setShowData(String showData) 
    {
        this.showData = showData;
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
    
    public StringProperty reservationIdProperty() 
    {
        String stringedReservationId = Integer.toString(reservationId);
        reservationIdProperty  = new SimpleStringProperty(stringedReservationId);
        return reservationIdProperty;
    }
    
    public StringProperty noSlotsProperty() 
    {
        String stringedNoSlots = Integer.toString(noSlots);
        noSlotsProperty  = new SimpleStringProperty(stringedNoSlots);
        return noSlotsProperty;
    }
    
    public StringProperty slotsIdProperty() 
    {
        slotsIdProperty  = new SimpleStringProperty(slotsId);
        return slotsIdProperty;
    } 
    
    public StringProperty noNormalProperty() 
    {
        String stringedNoNormal = Integer.toString(noNormal);
        noNormalProperty  = new SimpleStringProperty(stringedNoNormal);
        return noNormalProperty;
    }
    
    public StringProperty noHalfProperty() 
    {
        String stringedNoHalf = Integer.toString(noHalf);
        noHalfProperty  = new SimpleStringProperty(stringedNoHalf);
        return noHalfProperty;
    }
   
    public StringProperty priceProperty() 
    {
        String stringedPrice = Float.toString(price);
        priceProperty  = new SimpleStringProperty(stringedPrice);
        return priceProperty;
    }
    
    public StringProperty accountDataProperty() 
    {
        accountDataProperty  = new SimpleStringProperty(accountData);
        return accountDataProperty;
    } 
    
    public StringProperty showDataProperty() 
    {
        showDataProperty  = new SimpleStringProperty(showData);
        return showDataProperty;
    }  
    
    public StringProperty deleteDateProperty() 
    {
        deleteDateProperty  = new SimpleStringProperty(deleteDate);
        return deleteDateProperty;
    }
}


