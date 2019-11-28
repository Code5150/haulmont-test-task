package com.haulmont.testtask;

public class Doctor extends Person {
    private String specialization;
    public Doctor(String n, String s, String p, String spec){
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
