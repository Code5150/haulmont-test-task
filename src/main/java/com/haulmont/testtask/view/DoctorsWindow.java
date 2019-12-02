package com.haulmont.testtask.view;

import com.haulmont.testtask.model.Doctor;
import com.haulmont.testtask.view.DoctorEditorWindow;
import com.vaadin.annotations.Theme;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Theme(ValoTheme.THEME_NAME)
public class DoctorsWindow extends Window{

    private static ListDataProvider<Doctor> docList;
    private static Grid<Doctor> grid;

    public DoctorsWindow(){
        super("Врачи");
        setHeight("400px");
        setWidth("600px");

        AbsoluteLayout content = new AbsoluteLayout();
        content.setSizeFull();
        HorizontalLayout buttons = new HorizontalLayout();

        Button add = new Button("Добавить");
        Button change = new Button("Изменить");
        Button delete = new Button("Удалить");

        AtomicReference<Doctor> selectedDoctor = new AtomicReference<Doctor>();
        change.setEnabled(false);
        delete.setEnabled(false);

        //Test code for doctors list
        ArrayList<Doctor> testDoctorsList = new ArrayList<>();
        testDoctorsList.add(new Doctor("Цой", "Виктор", "Робертович", "Коновал"));
        testDoctorsList.add(new Doctor("Никонов", "Алексей", "Альбертович", "Эскулап"));

        docList = DataProvider.ofCollection(testDoctorsList);

        grid = new Grid<>();
        grid.setDataProvider(docList);

        grid.setHeight("80%");
        grid.setWidth("98%");
        grid.addColumn(Doctor::getSurname).setCaption("Фамилия");
        grid.addColumn(Doctor::getName).setCaption("Имя");
        grid.addColumn(Doctor::getPatronymic).setCaption("Отчество");
        grid.addColumn(Doctor::getSpecialization).setCaption("Специализация");

        grid.asSingleSelect().addValueChangeListener(valueChangeEvent -> {
            selectedDoctor.set(valueChangeEvent.getValue());
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
            getUI().addWindow(new DoctorEditorWindow(selectedDoctor.get()));
        });

        delete.addClickListener(clickEvent -> {
            testDoctorsList.remove(selectedDoctor.get());
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
        grid.setDataProvider(docList);
    }
}
