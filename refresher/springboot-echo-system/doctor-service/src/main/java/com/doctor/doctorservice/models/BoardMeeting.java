package com.doctor.doctorservice.models;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class BoardMeeting {
    @Id
    long id;
    @ManyToMany
    List<Doctor> invitedDoctors;
    String meetingAgenda;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Doctor> getInvitedDoctors() {
        return invitedDoctors;
    }

    public void setInvitedDoctors(List<Doctor> invitedDoctors) {
        this.invitedDoctors = invitedDoctors;
    }

    public String getMeetingAgenda() {
        return meetingAgenda;
    }

    public void setMeetingAgenda(String meetingAgenda) {
        this.meetingAgenda = meetingAgenda;
    }
}
