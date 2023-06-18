package com.doctor.doctorservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.print.Doc;

@Entity
public class Stethescope {
    @Id
    int id;

    String companyName;

//    @OneToOne
//    Doctor doctorId;
//
//    public Doctor getDoctorId() {
//        return doctorId;
//    }
//
//    public void setDoctorId(Doctor doctorId) {
//        this.doctorId = doctorId;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
