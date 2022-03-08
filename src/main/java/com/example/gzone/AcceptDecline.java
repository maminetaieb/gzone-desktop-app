package com.example.gzone;

import com.example.entity.JoinRequest;
import com.example.entity.Team;
import com.example.entity.Tournament;
import com.example.service.JoinRequests;
import com.example.service.Teams;
import com.example.service.Tournaments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AcceptDecline implements Initializable {

    @FXML
    private Button decline;

    @FXML
    private Button accept;

    @FXML
    private ListView<JoinRequest> listinvitation;

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
    void actionaccept(ActionEvent event) {

    }

    @FXML
    void actiondeccline(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JoinRequests jrs = new JoinRequests();
        Teams teams = new Teams();
        Tournaments tournaments = new Tournaments();
        List<JoinRequest> ljr = new ArrayList<>();
        List<Integer> myTeamIds = teams.findAll("`admin_id`=" + Id.user).stream().map(t -> t.getId()).collect(Collectors.toList());
        List<Integer> myTournamentIds = tournaments.findAll("`admin_id`=" + Id.user).stream().map(t -> t.getId()).collect(Collectors.toList());

        // Add User Join Requests to My Teams
        ljr.addAll(
                jrs.findAll("`accepted` IS NULL AND `user_id` IS NOT NULL AND `invitation`=false").stream()
                        .filter(jr -> myTeamIds.indexOf(jr.getTeamId()) != -1)
                        .collect(Collectors.toList())
        );

        // Add Team Join Requests To My Tournaments
        ljr.addAll(
                jrs.findAll("`accepted` IS NULL AND `team_id` IS NOT NULL AND `invitation`=false").stream()
                        .filter(jr -> myTournamentIds.indexOf(jr.getTournamentId()) != -1)
                        .collect(Collectors.toList())
        );

        // Add Team Invitations
        ljr.addAll(
                jrs.findAll("`accepted` IS NULL AND `invitation`=true AND `user_id`=" + Id.user)
        );

        // Add Tournament Invitations To My Teams
        ljr.addAll(
                jrs.findAll("`accepted` IS NULL AND `tournament_id` IS NOT NULL AND `invitation`=true").stream()
                        .filter(jr -> myTeamIds.indexOf(jr.getTeamId()) != -1)
                        .collect(Collectors.toList())
        );

        listinvitation.getItems().addAll(ljr);

    }
}
