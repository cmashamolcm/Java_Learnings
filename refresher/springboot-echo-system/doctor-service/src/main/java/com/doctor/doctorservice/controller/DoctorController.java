package com.doctor.doctorservice.controller;


import com.doctor.doctorservice.models.Doctor;
import com.doctor.doctorservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorService service;
    @GetMapping("/{d_id}")
    public Doctor getDoctor(@PathVariable("d_id") long id){
        return service.getDoctor(id);
    }

}
