package com.haulmont.testtask;

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
