package com.embero.jfx;

import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

/**
 * OverviewController
 * Description
 *
 * @author Robin Rowinski
 * created on 09.11.17
 */
public class OverviewController implements Initializable {


    private static String[] nameChars = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");
    private static Long nextId = 0L;

    @FXML private TableView<Person> tableView;
    @FXML private final ObservableList<Person> personList = FXCollections.observableArrayList();


    public void initialize(URL location, ResourceBundle resources) {
        personList.add(new Person(1L, "Rob", "Row"));
        personList.add(new Person(2L, "Bob", "Rob"));
        personList.add(new Person(3L, "Sue", "Erte"));
        personList.add(new Person(4L, "Sam", "Wise"));
        nextId = 5L;

        tableView.getColumns().add(createColumn("ID", Person::idProperty));
        tableView.getColumns().add(createColumn("First Name", Person::firstNameProperty));
        tableView.getColumns().add(createColumn("Last Name", Person::lastNameProperty));
    }

    public void handleAddPerson() {
        personList.add(new Person(nextId++, generateName(5), generateName(5)));
    }

    public void handleRemoveRandomPerson() {
        if (personList.size() == 0) return;
        personList.remove((int) Math.floor(Math.random() * personList.size()));
    }

    public void handleChangeRandomPerson() {
        int index = (int) Math.floor(Math.random() * personList.size());
        Person p = personList.get(index);
        p.setFirstName(generateName(10));
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }

    private String generateName(int len) {
        if (len <= 0) len = 1;
        StringBuilder b = new StringBuilder();
        b.append(randomChar().toUpperCase());
        while (b.length() < len) {
            b.append(randomChar());
        }
        return b.toString();
    }


    private String randomChar() {
        return nameChars[(int) Math.floor(Math.random() * nameChars.length)];
    }

    private static <S, T> TableColumn<S, T> createColumn(String name, Function<S, Property<T>> property) {
        TableColumn<S, T> column = new TableColumn<>(name);
        column.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
        column.setOnEditCommit(edit -> {
            S rowValue = edit.getRowValue();
            property.apply(rowValue).setValue(edit.getNewValue());
        });
        return column;
    }


}
