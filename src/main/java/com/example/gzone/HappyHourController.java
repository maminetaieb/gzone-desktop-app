/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.HappyHour;
import com.example.service.Games;
import com.example.service.HappyHours;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author chayma
 */
public class HappyHourController implements Initializable {

    @FXML
    private TableView<?> tbview;
    @FXML
    private TableColumn<?, ?> clStartDate;
    @FXML
    private TableColumn<?, ?> clEndDate;
    @FXML
    private TableColumn<?, ?> clBadge;
    @FXML
    private Button btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HomePage(MouseEvent event) {
    }

    @FXML
    private void Team(MouseEvent event) {
    }

    @FXML
    private void Tournament(MouseEvent event) {
    }

    @FXML
    private void Store(MouseEvent event) {
    }

    @FXML
    private void Forum(MouseEvent event) {
    }

    
    
    @FXML
    private void DeleteHappyHour(ActionEvent event) {
        int id = ((HappyHour) tbview.getSelectionModel().getSelectedItem()).getId();
        new HappyHours().deleteById(id);
        find(event);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setHeaderText("Success");
        alert.setContentText("HappyHour is delete successefully");
        alert.show();
    }
    
    private void find(ActionEvent event) {
        tbview.getItems().clear();
        HappyHours h = new HappyHours();
        
            }   



 

   
}
