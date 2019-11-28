package com.haulmont.testtask;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

@Theme(ValoTheme.THEME_NAME)
public class PatientsUI extends Window{
    public PatientsUI(){
        super("Пациенты");
        setHeight("400px");
        setWidth("600px");

        AbsoluteLayout content = new AbsoluteLayout();
        content.setSizeFull();
        VerticalLayout subContent = new VerticalLayout();
        subContent.setSizeFull();
        HorizontalLayout buttons = new HorizontalLayout();


        //Test code for patients list

        ArrayList<Patient> patientsList = new ArrayList<>();
        patientsList.add(new Patient("Горшенев", "Михаил", "Юрьевич", "1337228"));
        patientsList.add(new Patient("Жмышенко", "Валерий", "Альбертович", "1488228"));
        patientsList.add(new Patient("Жмышенко", "Валерий", "Альбертович", "1488228"));
        patientsList.add(new Patient("Жмышенко", "Валерий", "Альбертович", "1488228"));
        patientsList.add(new Patient("Жмышенко", "Валерий", "Альбертович", "1488228"));
        Grid<Patient> grid = new Grid<>();
        grid.setHeight("80%");
        grid.setWidth("98%");
        grid.setItems(patientsList);
        grid.addColumn(Patient::getSurname).setCaption("Фамилия");
        grid.addColumn(Patient::getName).setCaption("Имя");
        grid.addColumn(Patient::getPatronymic).setCaption("Отчество");
        grid.addColumn(Patient::getPhoneNumber).setCaption("Номер телефона");

        Button add = new Button("Добавить");
        Button change = new Button("Изменить");
        Button delete = new Button("Удалить");

        content.addComponent(grid,"top: 2%; left: 2%;");
        buttons.addComponent(add);
        buttons.addComponent(change);
        buttons.addComponent(delete);
        content.addComponent(buttons, "top: 86%; left: 2%;");

        center();
        setContent(content);
    }
}
