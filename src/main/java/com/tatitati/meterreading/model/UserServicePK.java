package com.tatitati.meterreading.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

public class UserServicePK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "serviceid")
    private mService service;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public mService getService() {
        return service;
    }

    public void setService(mService service) {
        this.service = service;
    }
}