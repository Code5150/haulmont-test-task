package com.haulmont.testtask.view;

import com.haulmont.testtask.model.Doctor;
import com.haulmont.testtask.model.Patient;
import com.haulmont.testtask.model.Prescription;
import com.vaadin.annotations.Theme;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Theme(ValoTheme.THEME_NAME)
public class PrescriptionsWindow extends Window{

    private static ListDataProvider<Prescription> prescriptionList;
    private static Grid<Prescription> grid;

    public PrescriptionsWindow(){
        super("Рецепты");
        setHeight("400px");
        setWidth("1340px");

        AbsoluteLayout content = new AbsoluteLayout();
        content.setSizeFull();
        HorizontalLayout buttons = new HorizontalLayout();

        Button add = new Button("Добавить");
        Button change = new Button("Изменить");
        Button delete = new Button("Удалить");

        AtomicReference<Prescription> selectedPrescription = new AtomicReference<Prescription>();
        change.setEnabled(false);
        delete.setEnabled(false);

        //Test code for prescriptions list
        ArrayList<Prescription> testPrescriptionsList = new ArrayList<>();
        testPrescriptionsList.add(new Prescription("Метамфетамин",
                new Patient("Жмышенко", "Валерий", "Альбертович", "1488228"),
                new Doctor("Цой", "Виктор", "Робертович", "Коновал"),
                LocalDate.of(1488, 8, 22),
                LocalDate.of(8228, 8, 14),
                Prescription.priorityValues.STATIM
                ));

        prescriptionList = DataProvider.ofCollection(testPrescriptionsList);

        grid = new Grid<>();
        grid.setDataProvider(prescriptionList);

        grid.setHeight("80%");
        grid.setWidth("98%");
        grid.addColumn(Prescription::getDescription).setCaption("Описание");
        grid.addColumn(Prescription::getFullPatientName).setCaption("Пациент");
        grid.addColumn(Prescription::getFullDoctorName).setCaption("Врач");
        grid.addColumn(Prescription::getCreationDate).setCaption("Дата назначения");
        grid.addColumn(Prescription::getValidity).setCaption("Дата окончания действия");
        grid.addColumn(Prescription::getPriorityName).setCaption("Приоритет");

        grid.asSingleSelect().addValueChangeListener(valueChangeEvent -> {
            selectedPrescription.set(valueChangeEvent.getValue());
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
            getUI().addWindow(new PrescriptionEditorWindow(selectedPrescription.get()));
        });

        delete.addClickListener(clickEvent -> {
            testPrescriptionsList.remove(selectedPrescription.get());
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
        grid.setDataProvider(prescriptionList);
    }
}
