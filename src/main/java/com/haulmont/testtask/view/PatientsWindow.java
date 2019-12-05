package com.haulmont.testtask.view;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.dao.DBManager;
import com.haulmont.testtask.model.Doctor;
import com.haulmont.testtask.model.Patient;
import com.vaadin.annotations.Theme;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Theme(ValoTheme.THEME_NAME)
public class PatientsWindow extends Window{

    private static ListDataProvider<Patient> patientList;
    private Grid<Patient> grid = new Grid<>();
    private static ArrayList<Grid<Patient>> gridList = new ArrayList<>();

    public PatientsWindow(){
        super("Пациенты");
        setHeight("400px");
        setWidth("600px");

        AbsoluteLayout content = new AbsoluteLayout();
        content.setSizeFull();
        HorizontalLayout buttons = new HorizontalLayout();

        Button add = new Button("Добавить");
        Button change = new Button("Изменить");
        Button delete = new Button("Удалить");

        AtomicReference<Patient> selectedPatient = new AtomicReference<Patient>();
        change.setEnabled(false);
        delete.setEnabled(false);

        patientList = DataProvider.ofCollection(Controller.getPatientList());
        grid.setDataProvider(patientList);

        grid.setHeight("80%");
        grid.setWidth("98%");
        grid.addColumn(Patient::getSurname).setCaption("Фамилия");
        grid.addColumn(Patient::getName).setCaption("Имя");
        grid.addColumn(Patient::getPatronymic).setCaption("Отчество");
        grid.addColumn(Patient::getPhoneNumber).setCaption("Номер телефона");

        gridList.add(grid);

        grid.asSingleSelect().addValueChangeListener(valueChangeEvent -> {
            selectedPatient.set(valueChangeEvent.getValue());
            if (valueChangeEvent.getValue() != null) {
                change.setEnabled(true);
                delete.setEnabled(true);
                System.out.println("Selected patient: " + valueChangeEvent.getValue().getId());
            }
            else {
                change.setEnabled(false);
                delete.setEnabled(false);
            }
        });

        add.addClickListener(clickEvent -> {
            selectedPatient.set(new Patient(-1,"", "", "", ""));
            getUI().addWindow(new PatientEditorWindow(selectedPatient.get(), MainUI.OPTIONS.ADD));
        });

        change.addClickListener(clickEvent -> {
            getUI().addWindow(new PatientEditorWindow(selectedPatient.get(), MainUI.OPTIONS.UPDATE));
        });

        delete.addClickListener(clickEvent -> {
            Controller.detetePatient(selectedPatient.get().getId());
            RefreshList();
        });

        addCloseListener(closeEvent -> {
            gridList.remove(grid);
        });

        content.addComponent(grid,"top: 2%; left: 2%;");
        buttons.addComponent(add);
        buttons.addComponent(change);
        buttons.addComponent(delete);
        content.addComponent(buttons, "top: 86%; left: 2%;");

        center();
        setContent(content);
    }
    public static void RefreshList(){
        patientList = DataProvider.ofCollection(Controller.getPatientList());
        gridList.forEach(grid -> {grid.setDataProvider(patientList);});
    }
}
