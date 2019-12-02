package com.haulmont.testtask.model;

import com.haulmont.testtask.model.Doctor;
import com.haulmont.testtask.model.Patient;

import java.time.LocalDate;

public class Prescription {
    //Значения приоритета
    public enum priorityValues{
        NORMAL,
        CITO,
        STATIM
    }
    //Поля
    private String description;
    private Patient patient;
    private Doctor doctor;
    private LocalDate creationDate;
    private LocalDate validity;
    private priorityValues priority;
    //Методы
    public Prescription(String desc, Patient pat, Doctor doc, LocalDate crDate, LocalDate val, priorityValues prio){
        this.description = desc;
        this.patient = pat;
        this.doctor = doc;
        this.creationDate = crDate;
        this.validity = val;
        this.priority = prio;
    }
    public String getFullPatientName(){
        return this.patient.getSurname() + " " + this.patient.getName() + " " + this.patient.getPatronymic();
    }
    public String getFullDoctorName(){
        return this.doctor.getSurname() + " " + this.doctor.getName() + " " + this.doctor.getPatronymic();
    }
    public void setDescription(String d){
        this.description = d;
    }
    public String getDescription(){
        return this.description;
    }
    public void setPatient(Patient p){
        this.patient = p;
    }
    public Patient getPatient(){
        return this.patient;
    }
    public void setDoctor(Doctor doc){
        this.doctor = doc;
    }
    public Doctor getDoctor(){
        return this.doctor;
    }
    public void setCreationDate(LocalDate dat){
        this.creationDate = dat;
    }
    public LocalDate getCreationDate(){
        return this.creationDate;
    }
    public void setValidity(LocalDate dat){
        this.validity = dat;
    }
    public LocalDate getValidity(){
        return this.validity;
    }
    public void setPriority(priorityValues pr){
        this.priority = pr;
    }
    public void setPriority(String pr){
        if(pr.toLowerCase() == "normal") this.priority = priorityValues.NORMAL;
        if(pr.toLowerCase() == "cito") this.priority = priorityValues.CITO;
        if(pr.toLowerCase() == "statim") this.priority = priorityValues.STATIM;
    }
    public priorityValues getPriorityValue(){
        return this.priority;
    }
    public String getPriorityName(){
        if (this.priority == priorityValues.NORMAL) return "Нормальный";
        if (this.priority == priorityValues.CITO) return "Срочный";
        if (this.priority == priorityValues.STATIM) return "Немедленный";
        return null;
    }
}
