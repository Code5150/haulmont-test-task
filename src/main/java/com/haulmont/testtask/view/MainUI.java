package com.haulmont.testtask.view;

import com.haulmont.testtask.dao.DBManager;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {
    public enum OPTIONS{
        UPDATE,
        ADD
    }

    @Override
    protected void init(VaadinRequest request) {
        AbsoluteLayout layout = new AbsoluteLayout();
        layout.setSizeFull();

        DBManager manager = new DBManager();

        Label MedBase = new Label("Medic Database");
        MedBase.addStyleName(ValoTheme.LABEL_H1);

        HorizontalLayout Buttons = new HorizontalLayout();
        Button Patients = new Button("Пациенты");
        Button Doctors = new Button("Врачи");
        Button Prescriptions = new Button("Рецепты");

        //Click handling
        Patients.addClickListener(clickEvent -> {
            addWindow(new PatientsWindow());
        });
        Doctors.addClickListener(clickEvent -> {
            addWindow(new DoctorsWindow());
        });
        Prescriptions.addClickListener(clickEvent -> {
            addWindow(new PrescriptionsWindow());
        });

        layout.addComponent(MedBase, "left: 50px");
        Buttons.addComponent(Patients);
        Buttons.addComponent(Doctors);
        Buttons.addComponent(Prescriptions);
        layout.addComponent(Buttons, "left: 50px; top: 130px");

        //layout.
        TextField field = new TextField();

        setContent(layout);
    }
}