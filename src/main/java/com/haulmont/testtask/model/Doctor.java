package com.haulmont.testtask.model;

public class Doctor extends Person {
    private String specialization;
    public Doctor(String s, String n, String p, String spec){
        setName(n);
        setSurname(s);
        setPatronymic(p);
        setSpecialization(spec);
    }
    public void setSpecialization(String spec){
        this.specialization = spec;
    }
    public String getSpecialization(){
        return this.specialization;
    }
}
