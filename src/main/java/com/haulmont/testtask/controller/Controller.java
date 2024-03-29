package com.haulmont.testtask.controller;

import com.haulmont.testtask.dao.DoctorDao;
import com.haulmont.testtask.dao.PatientDao;
import com.haulmont.testtask.dao.PrescriptionDao;
import com.haulmont.testtask.model.Doctor;
import com.haulmont.testtask.model.Patient;
import com.haulmont.testtask.model.Prescription;
import com.haulmont.testtask.model.Statistics;

import java.util.List;

public class Controller {
    private static DBManager manager = new DBManager();

    private static PatientDao patientDao = new PatientDao(manager);
    private static DoctorDao doctorDao = new DoctorDao(manager);
    private static PrescriptionDao prescriptionDao = new PrescriptionDao(manager);

    public static List<Patient> getPatientList(){
        return patientDao.getAll();
    }

    public static Patient getPatientById(long id){
        return patientDao.getById(id);
    }

    public static int deletePatient(long id){
        return patientDao.delete(id);
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

    public static int deleteDoctor(long id){
        return doctorDao.delete(id);
    }

    public static Doctor getDoctorById(long id){
        return doctorDao.getById(id);
    }

    public static void updateDoctor(Doctor doctor){
        doctorDao.update(doctor);
    }

    public static void addDoctor(Doctor doctor){
        doctorDao.add(doctor);
    }

    public static List<Prescription> getPrescriptionList(){
        return prescriptionDao.getAll();
    }

    public static void deletePrescription(long id){
        prescriptionDao.delete(id);
    }

    public static void updatePrescription(Prescription pres){
        prescriptionDao.update(pres);
    }

    public static void addPrescription(Prescription pres){
        prescriptionDao.add(pres);
    }

    public static List<Statistics> getDoctorPrescriptionStat(){
        return prescriptionDao.getPrescriptionStatistics();
    }
}
