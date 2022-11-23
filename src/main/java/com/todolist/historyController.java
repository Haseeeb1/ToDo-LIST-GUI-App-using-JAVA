package com.todolist;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.Vector;

import MainCode.File_Reader;
import MainCode.File_Writer;
import MainCode.ToDoList;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.AnimationTimer;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class historyController implements Initializable {

    @FXML
    private Label Date_time_label;
    
    @FXML
    private Button list, completed;

    @FXML
    private TableColumn<ToDoList, String> time1;

    @FXML
    private TableColumn<ToDoList, String> completed1;

    @FXML
    private TableColumn<ToDoList, String> subject1;

    @FXML
    private TableColumn<ToDoList, String> location1;

    @FXML
    private TableView<ToDoList> currentTable;

    @FXML
    private TableColumn<ToDoList, String> date1;

    @FXML
    private TableColumn<ToDoList, String> description1;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Date_time_label
                        .setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE-MMM-d-yyyy HH:mm:ss")));
            }
        };
        timer.start();


        App.tasksHistory = File_Reader.getDataFromFiles("historyData.txt");

        addObservableListToTable(App.tasksHistory);

    }

    public void addObservableListToTable(Vector<ToDoList> tasks) {
        ObservableList<ToDoList> observableList = FXCollections.observableArrayList(tasks);

        subject1.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("subject"));
        description1.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("description"));
        location1.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("location"));
        date1.setCellValueFactory(ToDoList -> {
            try {
                return new ReadOnlyStringWrapper(App.newDateFormat.format(
                        App.newDateFormat
                                .parse(App.newDateFormat.format(App.dateFormat.parse(ToDoList.getValue().getDate())))));
            } catch (ParseException e) {
                return new ReadOnlyStringWrapper("Error");
            }
        });
        time1.setCellValueFactory(ToDoList -> {
            try {
                return new ReadOnlyStringWrapper(App.newTimeForamt.format(
                        App.newTimeForamt
                                .parse(App.newTimeForamt.format(App.timeFormat.parse(ToDoList.getValue().getTime())))));
            } catch (ParseException e) {
                return new ReadOnlyStringWrapper("Error");
            }
        });
        completed1.setCellValueFactory(ToDoList -> {
            String s;
            if (Boolean.valueOf(ToDoList.getValue().getIsDoneValue())) {
                s = "Completed";
            } else
                s = "Not Completed";
            return new ReadOnlyStringWrapper(s);

        });

        currentTable.setItems(observableList);
    }

    @FXML
    public void searhTask() throws IOException {
        App.setRoot("searchTask");
    }

    @FXML
    public void emailList() throws IOException {
        App.email();
    }

    @FXML
    public void showCurrentList() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    public void showCompletedTask() throws IOException {
        App.setRoot("completed");
    }

    @FXML
    public void addTask(ActionEvent event) throws IOException {

        App.setRoot("addTask");
    }

}
