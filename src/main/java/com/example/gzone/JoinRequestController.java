package com.example.gzone;

import com.example.entity.JoinRequest;
import com.example.entity.User;
import com.example.service.JoinRequests;
import com.example.service.Teams;
import com.example.service.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class JoinRequestController implements Initializable {
    final JoinRequests joinRequests =new JoinRequests();
    @FXML
    private TextArea messagetxt;

    @FXML
    private Button sendrequest;

    @FXML
    private TextField tfuser;

    @FXML
    private TextField tfteam;

    @FXML
    void Forum(MouseEvent event) {

    }

    @FXML
    void HomePage(MouseEvent event) {

    }

    @FXML
    void Store(MouseEvent event) {

    }

    @FXML
    void Team(MouseEvent event) {

    }

    @FXML
    void Tournament(MouseEvent event) {

    }

    @FXML
    void sendrequest(ActionEvent event) throws IOException {
        if (!messagetxt.getText().isBlank()) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Send Request");
            a.setContentText("Send request to join this tournament");
            Optional<ButtonType> buttonType = a.showAndWait();


        if (buttonType.get().equals(ButtonType.OK)) {
            JoinRequest jr = new JoinRequest(null, Id.user, Id.team, null, messagetxt.getText(), new Date(), null, null, false);
           joinRequests.insert(jr);
            // sendrequest.getScene().setRoot(FXMLLoader.load(getClass().getResource("team-view.fxml")));
        }}else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Fill the form");
            a.setContentText("Form has empty fields");
            a.show();
        }
    }


        @Override
        public void initialize (URL location, ResourceBundle resources){
            tfuser.setText(new Users().findById(Id.user).getFullName());
            tfuser.setEditable(false);
            tfteam.setText(new Teams().findById(Id.team).getName());
            tfteam.setEditable(false);
        }
    }

