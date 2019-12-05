package com.haulmont.testtask.model;

public abstract class Person {

    private long id;
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

    public String getFullName(){
        return surname + " " +  name + " " +  patronymic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
