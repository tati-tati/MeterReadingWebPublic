package com.tatitati.meterreading.model;

import javax.persistence.*;

@Entity
@IdClass(UserServicePK.class)
@Table(name = "user_service")
public class mUserService {

    @Id
    User user;

    @Id
    mService service;

//    @Column(name = "userid")
//    private Integer userid;
//
//    @Column(name = "serviceid")
//    private Integer serviceid;
//
//    public Integer getUserid() {
//        return userid;
//    }
//
//    public void setUserid(Integer userid) {
//        this.userid = userid;
//    }
//
//    public Integer getServiceid() {
//        return serviceid;
//    }
//
//    public void setServiceid(Integer serviceid) {
//        this.serviceid = serviceid;
//    }

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
