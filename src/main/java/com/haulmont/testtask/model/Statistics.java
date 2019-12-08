package com.haulmont.testtask.model;

public class Statistics{
    private int num;
    private Doctor doc;
    public Statistics(int num, Doctor doc){
        this.num = num;
        this.doc = doc;
    }
    public int getNum(){
        return num;
    }
    public Doctor getDoc(){
        return doc;
    }
    public void setNum(int n){
        num = n;
    }
    public void setDoc(Doctor d){
        doc = d;
    }
    public String getFullDocName(){
        return doc.getFullName();
    }
}
