package com.haulmont.testtask.view;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Doctor;
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
    private Grid<Doctor> grid = new Grid<>();
    private static ArrayList<Grid<Doctor>> gridList = new ArrayList<>();

    public DoctorsWindow(){
        super("Врачи");
        setHeight("400px");
        setWidth("600px");

        //Содержимое окна: кнопки и таблица
        AbsoluteLayout content = new AbsoluteLayout();
        content.setSizeFull();
        HorizontalLayout buttons = new HorizontalLayout();

        Button add = new Button("Добавить");
        Button change = new Button("Изменить");
        Button delete = new Button("Удалить");

        //Текущий выбранный объект
        AtomicReference<Doctor> selectedDoctor = new AtomicReference<Doctor>();

        //Кнопки изменить/удалить заблокированы до выбора значения
        change.setEnabled(false);
        delete.setEnabled(false);

        //Установка значений в таблице
        docList = DataProvider.ofCollection(Controller.getDoctorList());
        grid.setDataProvider(docList);

        //Установка колонок в таблице
        grid.setHeight("80%");
        grid.setWidth("98%");
        grid.addColumn(Doctor::getSurname).setCaption("Фамилия");
        grid.addColumn(Doctor::getName).setCaption("Имя");
        grid.addColumn(Doctor::getPatronymic).setCaption("Отчество");
        grid.addColumn(Doctor::getSpecialization).setCaption("Специализация");

        //Добавление таблицы в список обновляемых таблиц
        gridList.add(grid);

        //Выбор элемента таблицы: если выбран какой-либо элемент
        //Есть возможность отредактировать или удалить его
        grid.asSingleSelect().addValueChangeListener(valueChangeEvent -> {
            selectedDoctor.set(valueChangeEvent.getValue());
            if (valueChangeEvent.getValue() != null) {
                change.setEnabled(true);
                delete.setEnabled(true);
                System.out.println("Selected doctor: " + valueChangeEvent.getValue().getId());
            }
            else {
                change.setEnabled(false);
                delete.setEnabled(false);
            }
        });

        //Логика кнопок добавления/изменения/удаления
        add.addClickListener(clickEvent -> {
            selectedDoctor.set(new Doctor(-1,"", "", "", ""));
            getUI().addWindow(new DoctorEditorWindow(selectedDoctor.get(), MainUI.OPTIONS.ADD));
        });

        change.addClickListener(clickEvent -> {
            getUI().addWindow(new DoctorEditorWindow(selectedDoctor.get(), MainUI.OPTIONS.UPDATE));
        });

        delete.addClickListener(clickEvent -> {
            Controller.deleteDoctor(selectedDoctor.get().getId());
            RefreshList();
        });

        //Удаление таблицы из списка при закрытии окна
        addCloseListener(closeEvent -> {
            gridList.remove(grid);
        });

        //Установка содержимого
        content.addComponent(grid,"top: 2%; left: 2%;");
        buttons.addComponent(add);
        buttons.addComponent(change);
        buttons.addComponent(delete);
        content.addComponent(buttons, "top: 86%; left: 2%;");

        center();
        setContent(content);
    }
    //Обновить содержимое
    public static void RefreshList(){
        //Обновление DataProvider
        docList = DataProvider.ofCollection(Controller.getDoctorList());
        //Установка обновленного DataProvider для каждой таблицы
        gridList.forEach(grid -> {grid.setDataProvider(docList);});
    }
}
