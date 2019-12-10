package com.buergervereinHSH.BackendProject.auth.model;

import javax.persistence.*;
/*import java.util.Calendar;  //nur mit Ablaufdatum wichtig
import java.util.Date;*/

//nur nötig wenn Konstruktor nach StackAbuse:
/*import java.util.Date;
import java.util.UUID;*/


@Entity
@Table(name = "VerificationTokens") //nötig?
public class VerificationToken {

    //baeldung, 24h Token:
   // private static final int EXPIRATION = 60 * 24;

    @Id //specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vt_id;
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER) //eins-zu-eins Beziehung zw Token und User
    @JoinColumn(nullable = false, name = "user_id")   //grün: Name der Spalte des Fremdschlüssels in der UserTabelle
    private User user;

    //private Date expiryDate;

    public VerificationToken() {  //nötig?
        super();
    }

    public VerificationToken(final String token) {
        super();
        this.token = token;
       //this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public VerificationToken(final User user, final String token) {
        super();
        this.token = token;
        this.user = user;
        //this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    //oder token direkt im Konstruktor erzeugen? (StackAbuse) Token hier Attribut created Date
/*    public VerificationToken(User user) {
        this.user = user;
        createdDate = new Date();
        token = UUID.randomUUID().toString();
    }*/

    public Long getVt_id() {return vt_id;}

    public String getToken() {return token;}
    public void setToken(final String token) {this.token = token;}

    public User getUser() {return user;}
    public void setUser(final User user) {this.user = user; }

    //nur wenn mit Ablaufdatum:
/*    public Date getExpiryDate() {return expiryDate;}
    public void setExpiryDate(final Date expiryDate) {this.expiryDate = expiryDate;}*/

/*    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }*/

/*    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }*/

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (getClass() != obj.getClass()) {return false;}
        final VerificationToken vt = (VerificationToken) obj;

        //mit Ablaufdatum:
/*        if (expiryDate == null) {
            if (vt.expiryDate != null) {return false;}
        }
        else if (!expiryDate.equals(vt.expiryDate)) {return false;}*/

        if (token == null) {
            if (vt.token != null) {return false;}
        }
        else if (!token.equals(vt.token)) {return false;}

        if (user == null) {
            if (vt.user != null) {return false;}
        }
        else if (!user.equals(vt.user)) {return false;}

        return true;
    }

    //ausgeklammert wegen ExpiryDate:
 /*   @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Token [String=").append(token).append("]").append("[Expires").append(expiryDate).append("]");
        return builder.toString();
    }*/
}
