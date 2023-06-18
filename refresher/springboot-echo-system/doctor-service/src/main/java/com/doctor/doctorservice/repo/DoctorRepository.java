package com.doctor.doctorservice.repo;

import com.doctor.doctorservice.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("from Doctor where id=?1")
    List<Doctor> someMethodToGetDoctor(long id);
}
