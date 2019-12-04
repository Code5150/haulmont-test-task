package com.haulmont.testtask.controller;

import com.haulmont.testtask.dao.DBManager;
import com.haulmont.testtask.dao.DoctorDao;
import com.haulmont.testtask.dao.PatientDao;
import com.haulmont.testtask.model.Doctor;
import com.haulmont.testtask.model.Patient;

import java.util.List;

public class Controller {
    private static DBManager manager = new DBManager();

    private static PatientDao patientDao = new PatientDao(manager);
    private static DoctorDao doctorDao = new DoctorDao(manager);

    public static List<Patient> getPatientList(){
        return patientDao.getAll();
    }

    public static void detetePatient(long id){
        patientDao.delete(id);
    }

    public static void updatePatient(Patient patient){
        patientDao.update(patient);
    }

    public static void addPatient(Patient patient){
        patientDao.add(patient);
    }

    public static List<Doctor> getDoctorList(){
        return doctorDao.getAll();
    }

    public static void deteteDoctor(long id){
        doctorDao.delete(id);
    }

    public static void updateDoctor(Doctor doctor){
        doctorDao.update(doctor);
    }

    public static void addDoctor(Doctor doctor){
        doctorDao.add(doctor);
    }
}
