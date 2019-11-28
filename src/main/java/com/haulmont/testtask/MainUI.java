package com.haulmont.testtask;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        AbsoluteLayout layout = new AbsoluteLayout();
        layout.setSizeFull();
        
        Label MedBase = new Label("Medic Database");

        HorizontalLayout Buttons = new HorizontalLayout();
        Button Patients = new Button("Пациенты");
        Button Doctors = new Button("Врачи");
        Button Prescriptions = new Button("Рецепты");

        //Patients.addClickListener(clickEvent -> )
        Patients.addClickListener(clickEvent -> {
            addWindow(new PatientsUI());

            //System.out.println(UI.getCurrent());
        });

        layout.addComponent(MedBase, "left: 50px; top: 50px");
        Buttons.addComponent(Patients);
        Buttons.addComponent(Doctors);
        Buttons.addComponent(Prescriptions);
        layout.addComponent(Buttons, "left: 50px; top: 80px");

        //layout.
        TextField field = new TextField();

        setContent(layout);
    }
}