package com.todolist;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import MainCode.EmailList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class emailController implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    private TextField recieverAddress;

    @FXML
    private TextField senderAddress;

    @FXML
    private Label errorLable;

    @FXML
    void sendEmail(ActionEvent event) {
        String from, pass, to;
        try {
            from = senderAddress.getText();
            pass = password.getText();
            to = recieverAddress.getText();
            if ((!from.endsWith("@gmail.com")) || (!to.endsWith("@gmail.com"))) {
                errorLable.setVisible(true);
            }
            EmailList.send(from, pass, to);
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Email Not Sent");
            alert.setContentText("An error occured while sending email.\nPlease Try Later.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorLable.setVisible(false);
        senderAddress.setText("your email");
        recieverAddress.setText("reciever's email");
        password.setText("account password");
    }

}
