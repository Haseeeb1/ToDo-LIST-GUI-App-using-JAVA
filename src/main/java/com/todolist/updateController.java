package com.todolist;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import MainCode.File_Writer;
import MainCode.ToDoList;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class updateController implements Initializable {

    @FXML
    private Label Date_time_label;

    @FXML
    private TextField subjectField, descriptionField, locationField, timeField;

    @FXML
    private Label subjectError, timeError, dateError, success;

    @FXML
    private DatePicker dateField;

    @FXML
    private ImageView subject_error_img, time_error_img, success_img;

    @FXML
    private Button update;

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
    public void updateTask() {
        String sub, des, loc, date, time;
        sub = subjectField.getText();
        des = descriptionField.getText();
        loc = locationField.getText();
        date = dateField.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        time = timeField.getText();

        try {
            App.timeFormat.parse(time);
        } catch (ParseException e) {

            return;
        }

        try {
            App.tasks.elementAt(PrimaryController.index).setSubject(sub);
            App.tasks.elementAt(PrimaryController.index).setDescription(des);
            App.tasks.elementAt(PrimaryController.index).setLocation(loc);
            App.tasks.elementAt(PrimaryController.index).setDate(date);
            App.tasks.elementAt(PrimaryController.index).setTime(time);

            App.tasks = ToDoList.sortSchedule(App.tasks);
            File_Writer.saveToFile(App.tasks);
            File_Writer.saveData(App.tasks);
            success_img.setVisible(true);
            success.setVisible(true);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

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
        
        subjectError.setVisible(false);
        timeError.setVisible(false);
        success.setVisible(false);
        subject_error_img.setVisible(false);
        time_error_img.setVisible(false);
        success_img.setVisible(false);

        subjectField.setText(App.tasks.elementAt(PrimaryController.index).getSubject());
        descriptionField.setText(App.tasks.elementAt(PrimaryController.index).getDescription());
        locationField.setText(App.tasks.elementAt(PrimaryController.index).getLocation());

        try {
            timeField.setText(App.tasks.elementAt(PrimaryController.index).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            dateField.setValue(LocalDate.parse(App.tasks.elementAt(PrimaryController.index).getDate(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        update.setOnMouseEntered(event1 -> {
            if (subjectField.getText() == "" || subjectField.getText() == null) {
                subjectError.setVisible(true);
                subject_error_img.setVisible(true);
            }

            if (timeField.getText() == "" || timeField.getText() == null) {
                timeError.setVisible(true);
                time_error_img.setVisible(true);
            }
            try {
                App.timeFormat.parse(timeField.getText());
            } catch (Exception e) {
                timeError.setVisible(true);
                time_error_img.setVisible(true);
            }
        });

        update.setOnMouseExited(event2 -> {
            subjectError.setVisible(false);
            subject_error_img.setVisible(false);
            timeError.setVisible(false);
            time_error_img.setVisible(false);
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