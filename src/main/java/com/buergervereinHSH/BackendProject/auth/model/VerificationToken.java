package com.buergervereinHSH.BackendProject.auth.model;

//baeldung, 24h token:
/*
import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
*/

import java.util.Date;
import java.util.UUID;
import javax.persistence.*;

@Entity   //Angabe table?
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="verification_token")
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public VerificationToken() {
    }

    public VerificationToken(User user) {
        this.user = user;
        createdDate = new Date();
        token = UUID.randomUUID().toString();
    }

    public long getTokenid() {return tokenid;}
    public void setTokenid(long tokenid) { this.tokenid = tokenid;}

    public String getToken() {return token;}
    public void setToken(String token) {this.token = token;}

    public Date getCreatedDate() {return createdDate;}
    public void setCreatedDate(Date createdDate) {this.createdDate = createdDate; }

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}


    //baeldung, 24h Token
   /* private static final int EXPIRATION = 60 * 24;

    @Id //specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.AUTO)  //provides for the specification of generation strategies for the values of primary keys
    private Long vt_id;

    private String token;  //@column name?

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER) //eins-zu-eins Beziehung zw Token und User
    @JoinColumn(nullable = false, name = "user_id")   //grün: Name der Spalte des Fremdschlüssels in der UserTabelle
    private User user;

    private Date expiryDate;

    public VerificationToken() {  //nötig?
        super();
    }

    public VerificationToken(final String token) {
        super();

        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public VerificationToken(final String token, final User user) {
        super();

        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public Long getVt_id() {return vt_id;}

    public String getToken() {return token;}

    public void setToken(final String token) {this.token = token;}

    public User getUser() {return user;}

    public void setUser(final User user) {this.user = user; }

    public Date getExpiryDate() {return expiryDate;}

    public void setExpiryDate(final Date expiryDate) {this.expiryDate = expiryDate;}

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (getClass() != obj.getClass()) {return false;}
        final VerificationToken vt = (VerificationToken) obj;

        if (expiryDate == null) {
            if (vt.expiryDate != null) {return false;}
        }
        else if (!expiryDate.equals(vt.expiryDate)) {return false;}

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

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Token [String=").append(token).append("]").append("[Expires").append(expiryDate).append("]");
        return builder.toString();
    }*/
}