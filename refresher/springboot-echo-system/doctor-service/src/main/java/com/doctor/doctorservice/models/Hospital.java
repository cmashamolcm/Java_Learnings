package com.doctor.doctorservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hospital {
    @Id
    int registrationId;
    String name;
    String revenue;

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }
}
