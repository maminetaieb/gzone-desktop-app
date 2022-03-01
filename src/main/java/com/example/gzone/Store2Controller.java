package com.example.gzone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Store2Controller {

    @FXML
    public AnchorPane store2pane;

    @FXML
    public Button viewbutton;

    @FXML
    void Createstore(MouseEvent event) {

    }

    @FXML
    void Forum(MouseEvent event) {

    }

    @FXML
    void HomePage(MouseEvent event) {

    }

    @FXML
    void Store(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Store.fxml"));
        store2pane.getChildren().setAll(pane);
    }

    @FXML
    void Team(MouseEvent event) {

    }

    @FXML
    void Tournament(MouseEvent event) {

    }

    @FXML
    void viewStore(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("StoreProfile.fxml"));
        store2pane.getChildren().setAll(pane);
    }

}