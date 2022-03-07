package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.Tournament;
import com.example.service.Games;
import com.example.service.Tournaments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListTournaments implements Initializable {

    Tournaments tournaments;

    @FXML
    private AnchorPane apListTournaments;

    @FXML
    private Button bRefresh;

    @FXML
    private Button bViewTournament;

    @FXML
    private Button bCreateNewTournament;

    @FXML
    private MenuButton mbGame;

    @FXML
    private TextField tfSearchName;
    
    @FXML
    private ListView<Tournament> lvTournaments;

    @FXML
    void refreshList(ActionEvent event) {
        lvTournaments.getItems().clear();

        if (Id.game == null) {
            for (Tournament t : tournaments.findAll("`name` REGEXP '" + tfSearchName.getText() + "' AND `game_id` IS NULL")) {
                lvTournaments.getItems().add(t);
                System.out.println(t);
            }
        } else if (Id.game == 0) {
            for (Tournament t : tournaments.findAll("`name` REGEXP '" + tfSearchName.getText() + "'")) {
                lvTournaments.getItems().add(t);
                System.out.println(t);
            }
        } else {
            for (Tournament t : tournaments.findAll("`name` REGEXP '" + tfSearchName.getText() + "' AND `game_id`=" + Id.game)) {
                lvTournaments.getItems().add(t);
                System.out.println(t);
            }
        }
        lvTournaments.refresh();
    }


    @FXML
    void viewTournament(ActionEvent event) throws IOException {
        if (lvTournaments.getSelectionModel().getSelectedItem() != null) {
            Id.tournament = ((Tournament) lvTournaments.getSelectionModel().getSelectedItem()).getId();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewTournament.fxml"));
            apListTournaments.getChildren().setAll(pane);
        }
    }

    @FXML
    void goToCreateTournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("CreateTournament.fxml"));
        apListTournaments.getChildren().setAll(pane);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tournaments = new Tournaments();
        List<Game> gameList = new Games().findAll();
        MenuItem mbgall = new MenuItem("Any Game");
        mbGame.getItems().add(mbgall);
        mbgall.setOnAction(e -> {
            Id.game = 0;
            refreshList(null);
            mbGame.setText("Any Game");
        });
        MenuItem mbgnone = new MenuItem("No Game");
        mbGame.getItems().add(mbgnone);
        mbgnone.setOnAction(e -> {
            Id.game = null;
            refreshList(null);
            mbGame.setText("No Game");
        });
        for (Game g : gameList) {
            MenuItem mi = new MenuItem(g.getName());
            mi.setOnAction(e -> {
                Id.game = g.getId();
                refreshList(null);
                mbGame.setText(g.getName());
            });
            mbGame.getItems().add(mi);
        }
        refreshList(null);
    }
}