package com.doctor.doctorservice.service;

import com.doctor.doctorservice.models.Doctor;
import com.doctor.doctorservice.models.Hospital;
import com.doctor.doctorservice.repo.DoctorRepository;
import com.doctor.doctorservice.repo.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository repo;

    @Autowired
    HospitalRepo hospitalRepo;
    @Transactional // with this, rollback happens on error. No entry comes to Hospital.
    //But if @Transactional is not there, Hospital entry is added and rest fails. Cannot rollback properly.
    // @Transactional(dontRollbackOn = {RuntimeException.class})  if we add this, rollback will not happen for runtime exceptions.
    public Doctor getDoctor(Long id){
        var result = repo.findById(id).orElse(new Doctor());// just to try 2 queries to database.

        var hospital = new Hospital();
        hospital.setRegistrationId(10);
        hospitalRepo.save(hospital);

        hospitalRepo.findById(1);
        //hospitalRepo.findById(100).orElseThrow(()->new RuntimeException("error"));
        //hospitalRepo.findById(100).orElseThrow(()->new RuntimeException("error"));// rolls back Hospital also on @Transaction

        System.out.println(repo.someMethodToGetDoctor(id));// gives empty array if nothing is there.


        return repo.findById(id).orElse(new Doctor());
    }
}

