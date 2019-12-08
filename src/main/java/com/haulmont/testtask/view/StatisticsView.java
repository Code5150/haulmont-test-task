package com.haulmont.testtask.view;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Doctor;
import com.haulmont.testtask.model.Prescription;
import com.haulmont.testtask.model.Statistics;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Window;

public class StatisticsView extends Window {
    public StatisticsView(){
        //Тут и без комментариев все понятно
        super("Статистика рецептов");
        setHeight("460px");
        setWidth("520px");

        AbsoluteLayout content = new AbsoluteLayout();
        content.setSizeFull();

        Grid<Statistics> grid = new Grid<>();
        ListDataProvider<Statistics> statList = DataProvider.ofCollection(Controller.getDoctorPrescriptionStat());

        grid.setDataProvider(statList);

        grid.addColumn(Statistics::getFullDocName).setCaption("Врач");
        grid.addColumn(Statistics::getNum).setCaption("Кол-во рецептов");

        content.addComponent(grid,"top: 2%; left: 2%;");

        center();
        setContent(content);
    }
}

