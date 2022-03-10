package com.example.gzone;

import com.example.entity.Role;
import com.example.entity.User;
import com.example.service.Users;
import static com.example.util.Gzon.checkFile;
import static com.example.util.Gzon.getPassword;
import static com.example.util.Gzon.getUsername;
import static com.example.util.Gzon.saveSession;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class LoginController {

    @FXML
    private Button SignIN;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    public Hyperlink toRegister;
    @FXML
    public AnchorPane signinpane;
    public AnchorPane homepagepane;
    @FXML
    private Label reglb1;
    @FXML
    private Hyperlink forgot;

    @FXML
    public void ToRegister(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Register.fxml"));
        signinpane.getChildren().setAll(pane);
    }

    public void initialize() {
        if (checkFile() == true) {
            username.setText(getUsername());
            password.setText(getPassword());
        }
    }

    @FXML
    private void checkUser(ActionEvent event) throws IOException {
        if ((Id.user = new Users().checklogin(username.getText(), password.getText())) != null) {
            User u = new Users().findById(Id.user);
            if (u.getRole().equals(Role.admin)) {
                AnchorPane panee = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                signinpane.getChildren().setAll(panee);
                String title = "Welcome!";
                String message = "";
                NotificationType notification = NotificationType.SUCCESS;
                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(notification);
                tray.showAndDismiss(Duration.seconds(4));
                if (checkFile() == false) {
                    saveSession(username.getText(), password.getText());
                }

            } else {
                AnchorPane panee = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                signinpane.getChildren().setAll(panee);
                String title = "Welcome!";
                String message = "";
                NotificationType notification = NotificationType.SUCCESS;

                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(notification);
                tray.showAndDismiss(Duration.seconds(4));
                if (checkFile() == false) {
                    saveSession(username.getText(), password.getText());
                }
            }
        } else {
            String title = "Something went wrong!";
            String message = "Verify your informations !";
            NotificationType notification = NotificationType.ERROR;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(4));
        }
    }

    @FXML
    private void ForgotPassword(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ForgotPassword.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Recover Password");
        newWindow.setScene(scene);
        newWindow.show();
    }

}
