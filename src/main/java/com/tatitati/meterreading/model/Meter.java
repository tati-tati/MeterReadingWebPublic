package com.tatitati.meterreading.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "meter")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(targetEntity = MeterType.class)
    @MapsId("meter_typeid")
    @JoinColumn(name = "meter_typeid")
    private MeterType meterType;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class)
    @MapsId("userid")
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne(targetEntity = mService.class)
    @MapsId("serviceid")
    @JoinColumn(name = "serviceid")
    private mService service;

    @OneToMany(targetEntity = MeterReading.class, mappedBy = "meter")
    private Set<MeterReading> meterReadings;

    @Column(name = "userid")
    private Integer userid;

    @Column(name = "serviceid")
    private Integer serviceid;

    @Column(name = "meter_typeid")
    private Integer meter_typeid;

    @Column(name = "meter_number")
    private String meterNumber;

    @Column(name = "create_date")
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MeterType getMeterType() {
        return meterType;
    }

    public void setMeterType(MeterType meterType) {
        this.meterType = meterType;
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

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Set<MeterReading> getMeterReadings() {
        return meterReadings;
    }

    public void setMeterReadings(Set<MeterReading> meterReadings) {
        this.meterReadings = meterReadings;
    }

    public void addMeterReading(MeterReading meterReading){
        if (meterReadings == null){
            meterReadings = new HashSet<MeterReading>();
        }
        meterReading.setMeter(this);
        meterReadings.add(meterReading);
    }

    @Override
    public String toString() {
        return "Meter{" +
                "id=" + id +
                ", meterType=" + meterType +
                ", meterReadings=" + meterReadings +
                ", meterNumber='" + meterNumber + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public User getUser() {
        return user;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getServiceid() {
        return serviceid;
    }

    public void setServiceid(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public Integer getMeter_typeid() {
        return meter_typeid;
    }

    public void setMeter_typeid(Integer meter_typeid) {
        this.meter_typeid = meter_typeid;
    }
}
