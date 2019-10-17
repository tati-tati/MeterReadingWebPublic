package com.tatitati.meterreading.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @ManyToMany(targetEntity = mService.class)
    @JoinTable(
            name = "user_service",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "serviceid"))
    private Set<mService> user_service;

    @OneToMany(targetEntity = Meter.class, mappedBy = "user", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Meter> meters = new HashSet<>();

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<mService> getUserServices() {
        return user_service;
    }

    public void setUserServices(Set<mService> userServices) {
        this.user_service = userServices;
    }

    public void addUserService(mService service){
        if (user_service == null){
            user_service = new HashSet<>();
        }
        user_service.add(service);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Meter> getMeters() {
        return meters;
    }

    public void addMeter(Meter meter){
        if (meters == null){
            meters = new HashSet<Meter>();
        }
        meter.setUser(this);
        meters.add(meter);
    }

    public void removeMeter(Meter meter) {
        meters.remove(meter);
        meter.setUser(null);
    }

    public void setMeters(Set<Meter> meters) {
        this.meters = meters;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userServices=" + user_service +
                ", meters=" + meters +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", registrationDate=" + registrationDate +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<mService> getUser_service() {
        return user_service;
    }

    public void setUser_service(Set<mService> user_service) {
        this.user_service = user_service;
    }
}