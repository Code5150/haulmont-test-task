package com.haulmont.testtask.dao;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.controller.DBManager;
import com.haulmont.testtask.model.Prescription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDao implements Dao<Prescription> {
    private DBManager manager;

    private final String TABLE_NAME = "Prescription";

    public PrescriptionDao(DBManager manager){
        this.manager = manager;
    }

    @Override
    public void setDBManager(DBManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Prescription> getAll() {
        PreparedStatement statement;
        List<Prescription> resultList = new ArrayList<Prescription>();
        try {
            statement = manager.getConnection().prepareStatement("SELECT * FROM " + TABLE_NAME);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                resultList.add(new Prescription(
                        resultSet.getLong("Prescription_Id"),
                        resultSet.getString("Description"),
                        Controller.getPatientById(resultSet.getLong("Patient")),
                        Controller.getDoctorById(resultSet.getLong("Doctor")),
                        resultSet.getDate("Creation_Date").toLocalDate(),
                        resultSet.getDate("Validity").toLocalDate(),
                        resultSet.getString("Priority")
                ));
            }
            System.out.println("All prescriptions selected successfully");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public Prescription getById(long id) {
        PreparedStatement statement;
        Prescription result;
        try {
            statement = manager.getConnection().prepareStatement("SELECT * FROM " + TABLE_NAME);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            result = new Prescription(
                        resultSet.getLong("Prescription_Id"),
                        resultSet.getString("Description"),
                        Controller.getPatientById(resultSet.getLong("Patient")),
                        Controller.getDoctorById(resultSet.getLong("Doctor")),
                        resultSet.getDate("Creation_Date").toLocalDate(),
                        resultSet.getDate("Validity").toLocalDate(),
                        resultSet.getString("Priority")
                );
            System.out.println("Prescription " + id + " selected successfully");
            return result;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Prescription prescription) {
        PreparedStatement statement;
        try{
            String s = "INSERT INTO " + TABLE_NAME
                    + " (Description, Patient, Doctor, Creation_date, Validity, Priority) VALUES ('" + prescription.getDescription()
                    + "', '" + prescription.getPatient().getId()
                    + "', '" + prescription.getDoctor().getId()
                    + "', '" + Date.valueOf(prescription.getCreationDate())
                    + "', '" + Date.valueOf(prescription.getValidity())
                    + "', '" + prescription.getPriority()
                    + "');";
            System.out.println(s);
            statement = manager.getConnection().prepareStatement(s);
            statement.execute();
            System.out.println("Prescription " + prescription.getId() + " added successfully");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        PreparedStatement statement;
        try{
            String s = "DELETE FROM " + TABLE_NAME
                    + " WHERE Prescription_Id = " + id;
            statement = manager.getConnection().prepareStatement(s);
            statement.execute();
            System.out.println("Prescription " + id + " deleted successfully");
        }
        catch(SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Prescription prescription) {
        PreparedStatement statement;
        try{
            String s = "UPDATE " + TABLE_NAME
                    + " SET Description='" + prescription.getDescription()
                    + "', Patient='" + prescription.getPatient().getId()
                    + "', Doctor='" + prescription.getDoctor().getId()
                    + "', Creation_Date='" + Date.valueOf(prescription.getCreationDate())
                    + "', Validity='" + Date.valueOf(prescription.getValidity())
                    + "', Priority='" + prescription.getPriority()
                    + "' WHERE Prescription_Id=" + prescription.getId();
            System.out.println(s);
            statement = manager.getConnection().prepareStatement(s);
            statement.execute();
            System.out.println("Prescription " + prescription.getId() + " updated successfully");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
