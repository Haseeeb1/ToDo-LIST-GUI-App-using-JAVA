package com.todolist;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Vector;

import MainCode.ToDoList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class searchController implements Initializable {

    static DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    static DateFormat newTimeForamt = new SimpleDateFormat("hh:mm a");
    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    static DateFormat newDateFormat = new SimpleDateFormat("EEEE, MMM d, yyyy");

    @FXML
    private Button addTask, searchTask, email, list, search;

    @FXML
    private Label heading, errorLabel, emptyField;

    @FXML
    private AnchorPane leftPane;

    @FXML
    private AnchorPane rightPane;

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

    @FXML
    private TextField txt;

    @FXML
    private ImageView notFoundimg;

    @FXML
    private Label Date_time_label;

    public void addObservableListToTable(Vector<ToDoList> tasks) {
        ObservableList<ToDoList> observableList = FXCollections.observableArrayList(tasks);

        subject1.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("subject"));
        description1.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("description"));
        location1.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("location"));
        date1.setCellValueFactory(ToDoList -> {
            try {
                return new ReadOnlyStringWrapper(newDateFormat.format(
                        newDateFormat.parse(newDateFormat.format(dateFormat.parse(ToDoList.getValue().getDate())))));
            } catch (ParseException e) {
                return new ReadOnlyStringWrapper("Error");
            }
        });
        time1.setCellValueFactory(ToDoList -> {
            try {
                return new ReadOnlyStringWrapper(newTimeForamt.format(
                        newTimeForamt.parse(newTimeForamt.format(timeFormat.parse(ToDoList.getValue().getTime())))));
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
    public void searchTask() {
        String s = txt.getText();
        Boolean found = false;
        Vector<ToDoList> t = new Vector<ToDoList>();
        for (ToDoList task : App.tasks) {
            if (s.compareTo(task.getSubject()) == 0) {
                t.add(task);
                found = true;
                errorLabel.setVisible(false);
                notFoundimg.setVisible(false);
            }
        }
        if (!found) {
            errorLabel.setVisible(true);
            notFoundimg.setVisible(true);
        }
        addObservableListToTable(t);
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
    public void addTask() throws IOException {

        App.setRoot("addTask");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Date_time_label
                        .setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE-MMM-d-yyyy HH:mm:ss")));
            }
        };
        timer.start();


        emptyField.setVisible(false);
        errorLabel.setVisible(false);
        notFoundimg.setVisible(false);
        searchTask.setOnMouseEntered(event1 ->{
            if(txt.getText()==null||txt.getText()=="")
                emptyField.setVisible(true);
        });
        searchTask.setOnMouseExited(event2 ->{
                emptyField.setVisible(false);
        });

    }

}