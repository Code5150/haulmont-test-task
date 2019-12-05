package com.haulmont.testtask.model;

import java.time.LocalDate;

public class Prescription {
    //Значения приоритета
    public enum priorityValues{
        NORMAL,
        CITO,
        STATIM
    }
    //Поля
    private long id;
    private String description;
    private Patient patient;
    private Doctor doctor;
    private LocalDate creationDate;
    private LocalDate validity;
    private priorityValues priority;
    //Методы
    /*public Prescription(long id, String desc, Patient pat, Doctor doc, LocalDate crDate, LocalDate val, priorityValues prio){
        setId(id);
        setDescription(desc);
        setPatient(pat);
        setDoctor(doc);
        setCreationDate(crDate);
        setValidity(val);
        setPriorityValue(prio);
    }*/
    public Prescription(long id, String desc, Patient pat, Doctor doc, LocalDate crDate, LocalDate val, String prio){
        setId(id);
        setDescription(desc);
        setPatient(pat);
        setDoctor(doc);
        setCreationDate(crDate);
        setValidity(val);
        setPriority(prio);
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

    /*public void setPriorityValue(priorityValues pr){
        this.priority = pr;
    }*/

    public void setPriority(String pr){
        if(pr.toLowerCase().equals("нормальный")) this.priority = priorityValues.NORMAL;
        if(pr.toLowerCase().equals("срочный")) this.priority = priorityValues.CITO;
        if(pr.toLowerCase().equals("немедленный")) this.priority = priorityValues.STATIM;
    }

    /*public priorityValues getPriorityValue(){
        return this.priority;
    }*/

    public String getPriority(){
        if (priority == priorityValues.NORMAL) return "Нормальный";
        if (priority == priorityValues.CITO) return "Срочный";
        if (priority == priorityValues.STATIM) return "Немедленный";
        return null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
