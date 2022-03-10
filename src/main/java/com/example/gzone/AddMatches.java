package com.example.gzone;

import com.example.entity.*;
import com.example.service.JoinRequests;
import com.example.service.Matches;
import com.example.service.Teams;
import com.example.service.Tournaments;
import com.example.util.TournamentMatches;
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
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class AddMatches implements Initializable {

    private int round;

    private Integer team1Id;
    private Integer team2Id;

    private Teams teams;
    private List<JoinRequest> jrl;

    @FXML
    private AnchorPane apAddMatches;

    @FXML
    private Button bAddMatch;

    @FXML
    private Button bReturn;

    @FXML
    private DatePicker dpStartTime;

    @FXML
    private MenuButton mbRound;

    @FXML
    private MenuButton mbTeam1;

    @FXML
    private MenuButton mbTeam2;

    @FXML
    private Label tTournamentName;

    @FXML
    void addMatch(ActionEvent event) {
        if (dpStartTime.getValue() != null
                && team1Id != null
                && team2Id != null
                && team1Id != team2Id) {
            new Matches().insert(new Match(
                    null,
                    Id.tournament,
                    new java.util.Date(java.sql.Date.valueOf(dpStartTime.getValue()).getTime()),
                    round,
                    team1Id,
                    team2Id,
                    null
            ));
            String title = "Added Successfully!";
            String message = "Match added !";
            NotificationType notification = NotificationType.SUCCESS;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(3));
        } else {
            String title = "Not adding !";
            String message = "Fill the form correctly !";
            NotificationType notification = NotificationType.ERROR;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(3));
        }

    }

    @FXML
    void returnToTournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewTournament.fxml"));
        apAddMatches.getChildren().setAll(pane);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teams = new Teams();
        Tournament t = new Tournaments().findById(Id.tournament);
        tTournamentName.setText(t.getName());
        for (int i = TournamentMatches.firstRoundOf(t); i > 0; --i) {
            MenuItem mi = new MenuItem("Round " + i);
            int finalI = i;
            mi.setOnAction(actionEvent -> {
                round = finalI;
                mbRound.setText(mi.getText());
            });
            mbRound.getItems().add(mi);
        }

        jrl = new JoinRequests().findAll("`tournament_id`=" + Id.tournament + " AND `accepted`=true");

        refresh1();
        refresh2();
    }

    private void refresh1() {
        mbTeam1.getItems().clear();
        jrl.stream()
                .filter(jr -> !jr.getTeamId().equals(team1Id))
                .forEach(jr -> {
                    Team team = teams.findById(jr.getTeamId());
                    MenuItem mi = new MenuItem((team.getName()));
                    mi.setOnAction(actionEvent -> {
                        team2Id = jr.getTeamId();
                        mbTeam1.setText(mi.getText());
                        refresh2();
                    });
                    mbTeam1.getItems().add(mi);
                });
    }

    private void refresh2() {
        mbTeam2.getItems().clear();
        jrl.stream()
                .filter(jr -> !jr.getTeamId().equals(team2Id))
                .forEach(jr -> {
                    Team team = teams.findById(jr.getTeamId());
                    MenuItem mi = new MenuItem((team.getName()));
                    mi.setOnAction(actionEvent -> {
                        team1Id = jr.getTeamId();
                        mbTeam2.setText(mi.getText());
                        refresh1();
                    });
                    mbTeam2.getItems().add(mi);
                });
    }
}
