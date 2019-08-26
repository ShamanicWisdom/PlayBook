/*
 * Praca Inzynierska - Czesc 3 - Aplikacja Webowa do obslugi sieci Teatrow
 *
 * Klasa modelu Account (mapowanie Hibernate) - Account.java.
 * 
 */

package szaman.playbook.entity;
// Generated 2017-12-15 00:56:10 by Hibernate Tools 4.3.1

// Laczenie z reszta wlasnych klas //



// Reszta klas //

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Account generated by hbm2java
 */

@Entity
@Table(name="account", uniqueConstraints = @UniqueConstraint(columnNames="email"))

public class Account  implements java.io.Serializable 
{
    private int accountId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Integer phoneNumber;
    private String role;
    private Set reservations = new HashSet(0);


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
   
    @Id 
    
    @Column(name="account_id", unique=true, nullable=false)
    public int getAccountId() 
    {
        return this.accountId;
    }
    
    public void setAccountId(int accountId) 
    {
        this.accountId = accountId;
    }
    
    @Column(name="name", nullable=false, length=50)
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
    
    @Column(name="surname", nullable=false, length=50)
    public String getSurname() 
    {
        return this.surname;
    }
    
    public void setSurname(String surname) 
    {
        this.surname = surname;
    }
    
    @Column(name="email", unique=true, nullable=false)
    public String getEmail() 
    {
        return this.email;
    }
    
    public void setEmail(String email) 
    {
        this.email = email;
    }

    @Column(name="password", nullable=false)
    public String getPassword() 
    {
        return this.password;
    }
    
    public void setPassword(String password) 
    {
        this.password = password;
    }

    @Column(name="phone_number")
    public Integer getPhoneNumber() 
    {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(Integer phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }
    
    @Column(name="role", nullable=false, length=20)
    public String getRole() 
    {
        return this.role;
    }
    
    public void setRole(String role) 
    {
        this.role = role;
    }
    
@OneToMany(fetch=FetchType.LAZY, mappedBy="account")
    public Set getReservations() 
    {
        return this.reservations;
    }
    
    public void setReservations(Set reservations) 
    {
        this.reservations = reservations;
    }
}

