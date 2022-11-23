package com.todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import MainCode.ToDoList;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static Vector<ToDoList> tasks = new Vector<ToDoList>();
    public static Vector<ToDoList> tasksHistory = new Vector<ToDoList>();
    static DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    static DateFormat newTimeForamt = new SimpleDateFormat("hh:mm a");
    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    static DateFormat newDateFormat = new SimpleDateFormat("EEEE, MMM d, yyyy");

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        scene.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        Image icon = new Image(getClass().getResourceAsStream("to-do_icon.png"));
        stage.getIcons().add(icon);
        stage.setTitle("To Do List");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void email() throws IOException {
        Stage stg = new Stage();
        FXMLLoader fxml = new FXMLLoader(App.class.getResource("emailpopup.fxml"));
        Scene sc = new Scene(fxml.load());
        stg.setScene(sc);
        stg.setResizable(false);
        Image icon = new Image(App.class.getResourceAsStream("to-do_icon.png"));
        stg.getIcons().add(icon);
        stg.setResizable(false);
        stg.setTitle("Email");
        stg.show();
    }

    public static void main(String[] args) {
        launch();
    }


    


}