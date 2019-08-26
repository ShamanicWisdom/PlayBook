/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa modelu Account (mapowanie Hibernate) - Account.java.
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

public class Account implements java.io.Serializable 
{
    private Integer accountId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Integer phoneNumber;
    private String role;
    private Set reservations = new HashSet(0);

    private IntegerProperty accountIdProperty = null;
    private StringProperty nameProperty = null;
    private StringProperty surnameProperty = null;
    private StringProperty emailProperty = null;
    private StringProperty passwordProperty = null;
    private IntegerProperty phoneNumberProperty = null;
    private StringProperty roleProperty = null;
    
    public Account() 
    {
        
    }
    
    public Account(int accountId, String name, String surname, String email, String password, String role) 
    {
        this.accountId = accountId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public Account(int accountId, String name, String surname, String email, String password, Integer phoneNumber, String role, Set reservations) 
    {
       this.accountId = accountId;
       this.name = name;
       this.surname = surname;
       this.email = email;
       this.password = password;
       this.phoneNumber = phoneNumber;
       this.role = role;
       this.reservations = reservations;
    }
       
    public int getAccountId() 
    {
        return this.accountId;
    }
    
    public void setAccountId(int accountId) 
    {
        this.accountId = accountId;
    }
    
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
    public String getSurname() 
    {
        return this.surname;
    }
    
    public void setSurname(String surname) 
    {
        this.surname = surname;
    }
    public String getEmail() 
    {
        return this.email;
    }
    
    public void setEmail(String email) 
    {
        this.email = email;
    }
    public String getPassword() 
    {
        return this.password;
    }
    
    public void setPassword(String password) 
    {
        this.password = password;
    }
    public Integer getPhoneNumber() 
    {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(Integer phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }
    public String getRole() 
    {
        return this.role;
    }
    
    public void setRole(String role) 
    {
        this.role = role;
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
    
    public IntegerProperty accountIdProperty() 
    {
        accountIdProperty  = new SimpleIntegerProperty(accountId);
        return accountIdProperty;
    }
    
    public StringProperty nameProperty() 
    {
        nameProperty  = new SimpleStringProperty(name);
        return nameProperty;
    }
    
    public StringProperty surnameProperty() 
    {
        surnameProperty  = new SimpleStringProperty(surname);
        return surnameProperty;
    }
    
    public StringProperty emailProperty() 
    {
        emailProperty  = new SimpleStringProperty(email);
        return emailProperty;
    }
    
    public StringProperty passwordProperty() 
    {
        passwordProperty  = new SimpleStringProperty(password);
        return passwordProperty;
    }
    
    public IntegerProperty phoneNumberProperty() 
    {
        phoneNumberProperty = new SimpleIntegerProperty(phoneNumber);
        return phoneNumberProperty;
    }
    
    public StringProperty roleProperty() 
    {
        roleProperty  = new SimpleStringProperty(role);
        return roleProperty;
    }
}


