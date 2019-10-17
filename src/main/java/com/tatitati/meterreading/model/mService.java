package com.tatitati.meterreading.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "service")
public class mService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phone_number;

    @JsonIgnore
    @ManyToMany(mappedBy = "user_service")
    private Set<User> user_services;

    @JsonIgnore
    @OneToMany(mappedBy = "service")
    private Set<Meter> meters;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Set<User> getUser_services() {
        return user_services;
    }

    public void setUser_services(Set<User> user_services) {
        this.user_services = user_services;
    }

    public void addUser_service(User user){
        if (this.user_services == null){
            user_services = new HashSet<>();
        }
        user_services.add(user);
    }

    public Set<Meter> getMeters() {
        return meters;
    }

    public void setMeters(Set<Meter> meters) {
        this.meters = meters;
    }
}