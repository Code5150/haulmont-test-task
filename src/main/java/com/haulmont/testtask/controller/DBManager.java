package com.haulmont.testtask.controller;

import org.hsqldb.jdbc.JDBCDataSource;

import java.io.*;
import java.sql.*;

public class DBManager {
    private final String DBSCRIPT_PATH = "src/main/resources/createDatabase.sql";
    private final String DB_NAME = "taskdb";
    private final String DB_URL = "jdbc:hsqldb:mem:" + DB_NAME;

    private Connection connection = null;

    public Connection getConnection(){
        return this.connection;
    }

    public DBManager(){
        //Проверка наличия драйвера БД
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //Установка значений
        JDBCDataSource dataSource = new JDBCDataSource();
        dataSource.setDatabaseName(DB_NAME);
        dataSource.setURL(DB_URL);
        dataSource.setUser("User");
        dataSource.setPassword("");
        //Соединение
        try {
            connection = dataSource.getConnection();
            createDatabase();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Считывает и выполняет SQL-скрипт
    public void createDatabase(){
        InputStream input;
        try {
            input = new FileInputStream(DBSCRIPT_PATH);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String str = reader.readLine();
            StringBuilder script = new StringBuilder(str);
            while ((str = reader.readLine()) != null) {
                script.append(str);
            }
            String[] statements = script.toString().split(";");

            for (String s : statements) {
                PreparedStatement statement = connection.prepareStatement(s);
                statement.execute();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
