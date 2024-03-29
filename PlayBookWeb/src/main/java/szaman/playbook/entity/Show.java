package szaman.playbook.entity;
// Generated 2018-01-01 03:41:49 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Show generated by hbm2java
 */
@Entity
@Table(name="show"
)
public class Show  implements java.io.Serializable {


     private int showId;
     private Auditorium auditorium;
     private Play play;
     private float price;
     private Date date;
     private String startHour;
     private int length;
     private int slotsLeft;
     private Set reservations = new HashSet(0);

    public Show() {
    }

	
    public Show(int showId, Auditorium auditorium, Play play, float price, Date date, String startHour, int length, int slotsLeft) {
        this.showId = showId;
        this.auditorium = auditorium;
        this.play = play;
        this.price = price;
        this.date = date;
        this.startHour = startHour;
        this.length = length;
        this.slotsLeft = slotsLeft;
    }
    public Show(int showId, Auditorium auditorium, Play play, float price, Date date, String startHour, int length, int slotsLeft, Set reservations) {
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
   
     @Id 

    
    @Column(name="show_id", unique=true, nullable=false)
    public int getShowId() {
        return this.showId;
    }
    
    public void setShowId(int showId) {
        this.showId = showId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="auditorium_id", nullable=false)
    public Auditorium getAuditorium() {
        return this.auditorium;
    }
    
    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="play_id", nullable=false)
    public Play getPlay() {
        return this.play;
    }
    
    public void setPlay(Play play) {
        this.play = play;
    }

    
    @Column(name="price", nullable=false, precision=8, scale=8)
    public float getPrice() {
        return this.price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="date", nullable=false, length=13)
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    
    @Column(name="start_hour", nullable=false, length=100)
    public String getStartHour() {
        return this.startHour;
    }
    
    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    
    @Column(name="length", nullable=false)
    public int getLength() {
        return this.length;
    }
    
    public void setLength(int length) {
        this.length = length;
    }

    
    @Column(name="slots_left", nullable=false)
    public int getSlotsLeft() {
        return this.slotsLeft;
    }
    
    public void setSlotsLeft(int slotsLeft) {
        this.slotsLeft = slotsLeft;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="show")
    public Set getReservations() {
        return this.reservations;
    }
    
    public void setReservations(Set reservations) {
        this.reservations = reservations;
    }




}


