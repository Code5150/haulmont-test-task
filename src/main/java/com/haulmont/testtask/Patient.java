package com.haulmont.testtask;

public class Patient extends Person{
    private String phoneNumber;
    public Patient(String s, String n, String p, String num){
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
