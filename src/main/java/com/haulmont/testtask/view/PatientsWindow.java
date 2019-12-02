package com.haulmont.testtask.view;

import com.haulmont.testtask.model.Doctor;
import com.haulmont.testtask.model.Patient;
import com.vaadin.annotations.Theme;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Theme(ValoTheme.THEME_NAME)
public class PatientsWindow extends Window{

    private static ListDataProvider<Patient> patientList;
    private static Grid<Patient> grid;

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

        //Test code for patients list
        ArrayList<Patient> testPatientsList = new ArrayList<>();
        testPatientsList.add(new Patient("Горшенев", "Михаил", "Юрьевич", "1337228"));
        testPatientsList.add(new Patient("Жмышенко", "Валерий", "Альбертович", "1488228"));
        testPatientsList.add(new Patient("Жмышенко", "Валерий", "Альбертович", "1488228"));
        testPatientsList.add(new Patient("Жмышенко", "Валерий", "Альбертович", "1488228"));
        testPatientsList.add(new Patient("Жмышенко", "Валерий", "Альбертович", "1488228"));

        patientList = DataProvider.ofCollection(testPatientsList);

        grid = new Grid<>();
        grid.setDataProvider(patientList);

        grid.setHeight("80%");
        grid.setWidth("98%");
        grid.setItems(testPatientsList);
        grid.addColumn(Patient::getSurname).setCaption("Фамилия");
        grid.addColumn(Patient::getName).setCaption("Имя");
        grid.addColumn(Patient::getPatronymic).setCaption("Отчество");
        grid.addColumn(Patient::getPhoneNumber).setCaption("Номер телефона");

        grid.asSingleSelect().addValueChangeListener(valueChangeEvent -> {
            selectedPatient.set(valueChangeEvent.getValue());
            if (valueChangeEvent.getValue() != null) {
                change.setEnabled(true);
                delete.setEnabled(true);
            }
            else {
                change.setEnabled(false);
                delete.setEnabled(false);
            }
        });

        change.addClickListener(clickEvent -> {
            getUI().addWindow(new PatientEditorWindow(selectedPatient.get()));
        });

        delete.addClickListener(clickEvent -> {
            testPatientsList.remove(selectedPatient.get());
            RefreshList();
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
        grid.setDataProvider(patientList);
    }
}
