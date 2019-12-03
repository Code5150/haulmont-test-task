package com.haulmont.testtask.dao;

import org.hsqldb.jdbc.JDBCDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private final String DBSCRIPT_PATH = "";
    private final String DB_NAME = "taskdb";
    private final String DB_URL = "jdbc:hsqldb:mem:" + DB_NAME;

    private Connection connection = null;

    public DBManager(){
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        JDBCDataSource dataSource = new JDBCDataSource();
        dataSource.setDatabaseName(DB_NAME);
        dataSource.setURL(DB_URL);
        dataSource.setUser("User");
        dataSource.setPassword("");
        try {
            connection = dataSource.getConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void createDatabase(){

    }
}
