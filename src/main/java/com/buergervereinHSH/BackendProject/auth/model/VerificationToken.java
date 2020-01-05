package com.buergervereinHSH.BackendProject.auth.model;

import javax.persistence.*;

@Entity
@Table(name = "VerificationTokens")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vt_id;
    private String token;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public VerificationToken() {
        super();
    }

    public VerificationToken(String token) {
        super();
        this.token = token;
    }

    public VerificationToken(final User user, String token) {
        super();
        this.token = token;
        this.user = user;
    }

    public Long getVt_id() {return vt_id;}

    public String getToken() {return token;}
    public void setToken(final String token) {this.token = token;}

    public User getUser() {return user;}

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (getClass() != obj.getClass()) {return false;}
        final VerificationToken vt = (VerificationToken) obj;

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
}
