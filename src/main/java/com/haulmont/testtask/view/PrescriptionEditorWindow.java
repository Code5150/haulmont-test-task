package com.haulmont.testtask.view;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Doctor;
import com.haulmont.testtask.model.Patient;
import com.haulmont.testtask.model.Prescription;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

@Theme(ValoTheme.THEME_NAME)

public class PrescriptionEditorWindow extends Window{

    public PrescriptionEditorWindow(Prescription prescription, MainUI.OPTIONS opt){
        super();
        setHeight("490px");
        setWidth("410px");

        String caption;
        if(opt == MainUI.OPTIONS.ADD) caption = "Добавить";
        else caption = "Изменить";
        setCaption(caption);

        AbsoluteLayout content = new AbsoluteLayout();
        content.setSizeFull();
        HorizontalLayout buttons = new HorizontalLayout();
        HorizontalLayout persons = new HorizontalLayout();
        HorizontalLayout dates = new HorizontalLayout();

        Binder<Prescription> binder = new Binder<>(Prescription.class);

        TextArea desc = new TextArea();
        desc.setCaption("Описание");
        ComboBox<Patient> patient = new ComboBox();
        patient.setCaption("Пациент");
        ComboBox<Doctor> doctor = new ComboBox();
        doctor.setCaption("Врач");
        DateField creation = new DateField();
        creation.setCaption("Дата назначения");
        DateField validity = new DateField();
        validity.setCaption("Дата окончания действия");
        ComboBox<String> priority = new ComboBox();
        priority.setCaption("Приоритет");

        desc.setWidth("96%");

        Button ok = new Button("OK");
        Button cancel = new Button("Отмена");

        //ComboBoxes item
        patient.setItems(Controller.getPatientList());
        patient.setItemCaptionGenerator(item -> item.getFullName());
        patient.setEmptySelectionAllowed(true);
        patient.setEmptySelectionCaption("---");
        doctor.setItems(Controller.getDoctorList());
        doctor.setItemCaptionGenerator(item -> item.getFullName());
        doctor.setEmptySelectionAllowed(true);
        doctor.setEmptySelectionCaption("---");
        priority.setItems("Нормальный", "Срочный", "Немедленный");
        priority.setEmptySelectionAllowed(false);
        priority.setValue("Нормальный");

        binder.forField(desc)
                .asRequired("Обязательное значение")
                .bind("description");

        binder.forField(patient)
                .asRequired("Обязательное значение")
                .bind("patient");

        binder.forField(doctor)
                .asRequired("Обязательное значение")
                .bind("doctor");

        binder.forField(creation)
                .asRequired("Обязательное значение")
                .bind("creationDate");

        binder.forField(validity)
                .asRequired("Обязательное значение")
                .bind("validity");

        binder.forField(priority)
                .asRequired("Обязательное значение")
                .bind("priority");//Здесь нужно вручную прописать геттер и сеттер

        ok.setEnabled(false);

        binder.addStatusChangeListener(statusChangeEvent -> {
            ok.setEnabled(!statusChangeEvent.hasValidationErrors());
        });

        binder.readBean(prescription);

        buttons.addComponent(ok);
        buttons.addComponent(cancel);

        ok.addClickListener(clickEvent -> {
            try {
                binder.writeBean(prescription);
                System.out.println(prescription.getPriority());
                if(opt == MainUI.OPTIONS.UPDATE) Controller.updatePrescription(prescription);
                if(opt == MainUI.OPTIONS.ADD) Controller.addPrescription(prescription);
                PrescriptionsWindow.RefreshList();
                this.close();
            }
            catch (ValidationException e){
                System.out.println(e);
            }
        });

        cancel.addClickListener(clickEvent -> {
            this.close();
        });

        persons.addComponent(patient);
        persons.addComponent(doctor);

        dates.addComponent(creation);
        dates.addComponent(validity);


        content.addComponent(desc, "top: 5%; left: 2%;");
        content.addComponent(persons, "top: 40%; left: 2%;");
        content.addComponent(dates, "top: 55%; left: 2%;");
        content.addComponent(priority, "top: 75%; left: 2%;");
        content.addComponent(buttons, "top: 86%; left: 2%;");

        setModal(true);
        setResizable(false);
        //setClosable(false);
        setContent(content);
        center();
    }
}
