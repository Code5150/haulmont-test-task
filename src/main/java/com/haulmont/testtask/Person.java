package com.haulmont.testtask;

public abstract class Person {
    private String name;
    private String surname;
    private String patronymic;
    public void setName(String n){
        this.name = n;
    }
    public String getName(){
        return name;
    }
    public void setSurname(String n){
        this.surname = n;
    }
    public String getSurname(){
        return surname;
    }
    public void setPatronymic(String n){
        this.patronymic = n;
    }
    public String getPatronymic(){
        return patronymic;
    }
}
