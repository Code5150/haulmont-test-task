package com.haulmont.testtask.view;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Doctor;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class DoctorEditorWindow extends Window{
    public DoctorEditorWindow(Doctor doctor, MainUI.OPTIONS opt){
        super();
        setHeight("390px");
        setWidth("290px");

        //Установка заголовка
        String caption;
        if(opt == MainUI.OPTIONS.ADD) caption = "Добавить";
        else caption = "Изменить";
        setCaption(caption);

        //Установка разметки содержимого
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        HorizontalLayout buttons = new HorizontalLayout();

        //Binder для переданного объекта
        Binder<Doctor> binder = new Binder<>(Doctor.class);

        //Поля редактирования
        TextField name = new TextField();
        name.setCaption("Имя");
        TextField surname = new TextField();
        surname.setCaption("Фамилия");
        TextField patronymic = new TextField();
        patronymic.setCaption("Отчество");
        TextField spec = new TextField();
        spec.setCaption("Специализация");

        //Кнопки
        Button ok = new Button("OK");
        Button cancel = new Button("Отмена");

        //Валидация полей для изменяемого объекта
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

        //Установка значений переданного объекта в поля редактирования
        binder.readBean(doctor);

        //Логика для кнопок
        ok.addClickListener(clickEvent -> {
            try {
                binder.writeBean(doctor);
                if(opt == MainUI.OPTIONS.UPDATE) Controller.updateDoctor(doctor);
                if(opt == MainUI.OPTIONS.ADD) Controller.addDoctor(doctor);
                DoctorsWindow.RefreshList();
                this.close();
            }
            catch (ValidationException e){
                e.printStackTrace();
            }
        });

        cancel.addClickListener(clickEvent -> {
            this.close();
        });

        //Добавление компонентов
        buttons.addComponent(ok);
        buttons.addComponent(cancel);

        content.addComponent(surname);
        content.addComponent(name);
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
