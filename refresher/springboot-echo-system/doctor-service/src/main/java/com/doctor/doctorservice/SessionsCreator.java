package com.doctor.doctorservice;

import com.doctor.doctorservice.models.Doctor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SessionsCreator {
//    public void createHibernateSession(){
//        Configuration config = new Configuration().configure().addAnnotatedClass(Doctor.class);
//        Session session = config.buildSessionFactory().openSession();
//        Transaction tx = session.getTransaction();
//        tx.begin();
//        session.save(new Doctor());
//        session.get(Doctor.class, 100);
//        session.get(Doctor.class, 100);// second time, fetches from cache instead if query database
//        tx.commit();
//        // in session, there is level 1 cache
//        session.close();
//
//        Session s = config.buildSessionFactory().openSession();
//        session.beginTransaction();
//        session.get(Doctor.class, 100);// this queries database as previous get was in another session.
//    }
}
