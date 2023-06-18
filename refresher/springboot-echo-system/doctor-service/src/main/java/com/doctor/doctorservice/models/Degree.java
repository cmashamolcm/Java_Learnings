package com.doctor.doctorservice.models;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class Degree {
    String baseDegree;
    String recentDegree;

    int experience;

    public String getBaseDegree() {
        return baseDegree;
    }

    public void setBaseDegree(String baseDegree) {
        this.baseDegree = baseDegree;
    }

    public String getRecentDegree() {
        return recentDegree;
    }

    public void setRecentDegree(String recentDegree) {
        this.recentDegree = recentDegree;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
