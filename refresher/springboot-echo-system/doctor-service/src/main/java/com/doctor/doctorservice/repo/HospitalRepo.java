package com.doctor.doctorservice.repo;

import com.doctor.doctorservice.models.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepo extends JpaRepository<Hospital, Integer> {
}
