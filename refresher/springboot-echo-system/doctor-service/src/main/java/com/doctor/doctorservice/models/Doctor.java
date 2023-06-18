package com.doctor.doctorservice.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Doctor {
    @Id
    long id;
    String name;
    @Column(name="highest_degree")
    String specialization;

    @NotNull
    Degree degree;

//    @OneToOne(mappedBy = "doctorId")
    @OneToOne
    Stethescope steth;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    List<ConsultationSlot> consultationSlots;

    @ManyToMany(mappedBy = "invitedDoctors")
    List<BoardMeeting> invitedMeetings;


    public List<ConsultationSlot> getConsultationSlots() {
        return consultationSlots;
    }

    public void setConsultationSlots(List<ConsultationSlot> consultationSlots) {
        this.consultationSlots = consultationSlots;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Stethescope getSteth() {
        return steth;
    }

    public void setSteth(Stethescope steth) {
        this.steth = steth;
    }
}

