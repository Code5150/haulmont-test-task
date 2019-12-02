package com.haulmont.testtask.view;

import com.haulmont.testtask.model.Doctor;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class DoctorEditorWindow extends Window{
    public DoctorEditorWindow(Doctor doctor){
        super("Изменить");
        setHeight("390px");
        setWidth("290px");

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        HorizontalLayout buttons = new HorizontalLayout();

        Binder<Doctor> binder = new Binder<>(Doctor.class);

        TextField name = new TextField();
        name.setCaption("Имя");
        TextField surname = new TextField();
        surname.setCaption("Фамилия");
        TextField patronymic = new TextField();
        patronymic.setCaption("Отчество");
        TextField spec = new TextField();
        spec.setCaption("Специализация");

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

        binder.forField(spec)
                .asRequired("Обязательное значение")
                .bind("specialization");

        ok.setEnabled(false);

        binder.addStatusChangeListener(statusChangeEvent -> {
            ok.setEnabled(!statusChangeEvent.hasValidationErrors());
        });

        binder.readBean(doctor);

        buttons.addComponent(ok);
        buttons.addComponent(cancel);

        ok.addClickListener(clickEvent -> {
            try {
                binder.writeBean(doctor);
                DoctorsWindow.RefreshList();
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
        content.addComponent(spec);
        content.addComponent(buttons);

        setModal(true);
        setResizable(false);
        setClosable(false);
        setContent(content);
        center();
    }
}
