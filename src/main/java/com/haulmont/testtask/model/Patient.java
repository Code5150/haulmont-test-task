package com.haulmont.testtask.model;

import com.vaadin.annotations.PropertyId;

public class Patient extends Person {
    private String phoneNumber;
    public Patient(long id, String s, String n, String p, String num){
        setId(id);
        setName(n);
        setSurname(s);
        setPatronymic(p);
        setPhoneNumber(num);
    }
    public void setPhoneNumber(String num){
        this.phoneNumber = num;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
}
