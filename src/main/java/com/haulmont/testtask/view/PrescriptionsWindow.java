package com.haulmont.testtask.view;

import com.haulmont.testtask.controller.Controller;
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
    private Grid<Prescription> grid = new Grid<>();
    private static ArrayList<Grid<Prescription>> gridList = new ArrayList<>();

    public PrescriptionsWindow(){
        super("Рецепты");
        setHeight("500px");
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

        HorizontalLayout filter = new HorizontalLayout();
        TextField desc = new TextField();
        desc.setCaption("Описание");
        ComboBox<Patient> patient = new ComboBox();
        patient.setCaption("Пациент");
        ComboBox<String> priority = new ComboBox();
        priority.setCaption("Приоритет");
        Button apply = new Button("Применить");

        patient.setItems(Controller.getPatientList());
        patient.setItemCaptionGenerator(item -> item.getFullName());
        patient.setEmptySelectionAllowed(true);
        patient.setEmptySelectionCaption("---");
        priority.setItems("Нормальный", "Срочный", "Немедленный");
        priority.setEmptySelectionAllowed(true);
        priority.setEmptySelectionCaption("---");

        filter.addComponent(desc);
        filter.addComponent(patient);
        filter.addComponent(priority);
        filter.addComponent(apply);

        prescriptionList = DataProvider.ofCollection(Controller.getPrescriptionList());
        grid.setDataProvider(prescriptionList);

        grid.setHeight("75%");
        grid.setWidth("98%");
        grid.addColumn(Prescription::getDescription).setCaption("Описание");
        grid.addColumn(Prescription::getFullPatientName).setCaption("Пациент");
        grid.addColumn(Prescription::getFullDoctorName).setCaption("Врач");
        grid.addColumn(Prescription::getCreationDate).setCaption("Дата назначения");
        grid.addColumn(Prescription::getValidity).setCaption("Дата окончания действия");
        grid.addColumn(Prescription::getPriority).setCaption("Приоритет");

        gridList.add(grid);

        grid.asSingleSelect().addValueChangeListener(valueChangeEvent -> {
            selectedPrescription.set(valueChangeEvent.getValue());
            if (valueChangeEvent.getValue() != null) {
                change.setEnabled(true);
                delete.setEnabled(true);
                System.out.println("Selected prescription: " + valueChangeEvent.getValue().getPriority());
            }
            else {
                change.setEnabled(false);
                delete.setEnabled(false);
            }
        });

        add.addClickListener(clickEvent -> {
            selectedPrescription.set(new Prescription(-1, "", null, null, null, null, "Нормальный"));
            getUI().addWindow(new PrescriptionEditorWindow(selectedPrescription.get(), MainUI.OPTIONS.ADD));
        });

        change.addClickListener(clickEvent -> {
            getUI().addWindow(new PrescriptionEditorWindow(selectedPrescription.get(), MainUI.OPTIONS.UPDATE));
        });

        delete.addClickListener(clickEvent -> {
            Controller.deletePrescription(selectedPrescription.get().getId());
            RefreshList();
        });

        addCloseListener(closeEvent -> {
            gridList.remove(grid);
        });

        content.addComponent(filter, "top: 2%; left: 2%;");
        content.addComponent(grid,"top: 17%; left: 2%;");
        buttons.addComponent(add);
        buttons.addComponent(change);
        buttons.addComponent(delete);
        content.addComponent(buttons, "top: 86%; left: 2%;");

        //System.out.println("bshabf".contains(""));

        center();
        setContent(content);
    }
    public static void RefreshList(){
        prescriptionList = DataProvider.ofCollection(Controller.getPrescriptionList());
        gridList.forEach(grid -> {grid.setDataProvider(prescriptionList);});
    }
}
