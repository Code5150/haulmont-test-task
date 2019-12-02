package com.haulmont.testtask.view;

import com.haulmont.testtask.model.Patient;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.*;

public class PatientEditorWindow extends Window {
    public PatientEditorWindow(Patient patient){
        super("Изменить");
        setHeight("390px");
        setWidth("290px");

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        HorizontalLayout buttons = new HorizontalLayout();

        Binder<Patient> binder = new Binder<>(Patient.class);

        TextField name = new TextField();
        name.setCaption("Имя");
        TextField surname = new TextField();
        surname.setCaption("Фамилия");
        TextField patronymic = new TextField();
        patronymic.setCaption("Отчество");
        TextField num = new TextField();
        num.setCaption("Номер телефона");

        Button ok = new Button("OK");
        Button cancel = new Button("Отмена");

        binder.forField(name)
                .asRequired("Обязательное значение")
                .bind("name");

        binder.forField(surname)
                .asRequired("Обязательное значение")
                .bind("surname");

        binder.forField(patronymic)
                .asRequired("Обязательное значение")
                .bind("patronymic");

        binder.forField(num)
                .asRequired("Обязательное значение")
                .bind("phoneNumber");

        ok.setEnabled(false);

        binder.addStatusChangeListener(statusChangeEvent -> {
            ok.setEnabled(!statusChangeEvent.hasValidationErrors());
        });

        binder.readBean(patient);

        buttons.addComponent(ok);
        buttons.addComponent(cancel);

        ok.addClickListener(clickEvent -> {
            try {
                binder.writeBean(patient);
                PatientsWindow.RefreshList();
                this.close();
            }
            catch (ValidationException e){
                System.out.println(e);
            }
        });

        cancel.addClickListener(clickEvent -> {
            this.close();
        });

        content.addComponent(name);
        content.addComponent(surname);
        content.addComponent(patronymic);
        content.addComponent(num);
        content.addComponent(buttons);

        setModal(true);
        setResizable(false);
        setClosable(false);
        setContent(content);
        center();
    }
}
