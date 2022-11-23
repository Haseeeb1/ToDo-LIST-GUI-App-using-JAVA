package com.todolist;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import MainCode.File_Writer;
import MainCode.ToDoList;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;

public class addTaskController implements Initializable {


    @FXML
    private Label Date_time_label;

    @FXML
    private TextField subjectField, descriptionField, locationField, timeField;

    @FXML
    private Label subjectError, timeError, success;

    @FXML
    private ImageView subjectErrorimg, timeErrorimg, success_img;

    @FXML
    private DatePicker dateField;

    @FXML
    private Button add;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
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
    public void showcurrentList() throws IOException {

        App.setRoot("primary");
    }

    @FXML
    public void add() {
        String sub, des, loc, date, time;
        sub = subjectField.getText();
        des = descriptionField.getText();
        loc = locationField.getText();
        date = dateField.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        time = timeField.getText();

        try {
            App.timeFormat.parse(time);
            App.dateFormat.parse(date);
        } catch (ParseException e) {
            return;
        }

        try {
            ToDoList task = new ToDoList(time, date, sub, des, loc);

            App.tasks.add(task);
            File_Writer.saveDataToHistory(App.tasks.lastElement());
            File_Writer.saveToHistory(App.tasks.lastElement());
            App.tasks = ToDoList.sortSchedule(App.tasks);
            File_Writer.saveToFile(App.tasks);
            File_Writer.saveData(App.tasks);

            subjectField.setText("");
            descriptionField.setText("");
            locationField.setText("");
            timeField.setText("HH:mm");
            try {
                dateField.setValue(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            success.setVisible(true);
            success_img.setVisible(true);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void cancel() throws IOException {
        App.setRoot("primary");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        subjectError.setVisible(false);
        timeError.setVisible(false);
        subjectErrorimg.setVisible(false);
        timeErrorimg.setVisible(false);
        success_img.setVisible(false);
        success.setVisible(false);


            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    Date_time_label
                            .setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE-MMM-d-yyyy HH:mm:ss")));
                }
            };
            timer.start();



        add.setOnMouseEntered(event1 -> {
            if (subjectField.getText() == "" || subjectField.getText() == null) {
                subjectError.setVisible(true);
                subjectErrorimg.setVisible(true);
            }
            if (timeField.getText() == "" || timeField.getText() == null) {
                timeError.setVisible(true);
                timeErrorimg.setVisible(true);
            }
            try {
                App.timeFormat.parse(timeField.getText());
            } catch (Exception e) {
                timeError.setVisible(true);
                timeErrorimg.setVisible(true);
            }
        });

        add.setOnMouseExited(event2 -> {

            subjectError.setVisible(false);
            subjectErrorimg.setVisible(false);
            timeError.setVisible(false);
            timeErrorimg.setVisible(false);

        });

        dateField.setOnMouseClicked(event1 -> {
            success.setVisible(false);
            success_img.setVisible(false);
        });

        descriptionField.setOnMouseClicked(event1 -> {
            success.setVisible(false);
            success_img.setVisible(false);
        });

        locationField.setOnMouseClicked(event1 -> {
            success.setVisible(false);
            success_img.setVisible(false);
        });

        timeField.setOnMouseClicked(event1 -> {
            success.setVisible(false);
            success_img.setVisible(false);
        });

        subjectField.setOnMouseClicked(event1 -> {
            success.setVisible(false);
            success_img.setVisible(false);
        });
    }

}
