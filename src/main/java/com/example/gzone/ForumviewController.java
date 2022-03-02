/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Post;
import com.example.service.Posts;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author iheb
 */
public class ForumviewController implements Initializable {


    @FXML
    private TextArea tfcontent;
    @FXML
    public Button btninsert;
    
    @FXML
    public Button btncancel;
    @FXML
    private TextField tftitle;
    @FXML
    public AnchorPane dashforumback;
    
    /*ValidationSupport validationsupport = new ValidationSupport();*/
    
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
    private void addpost(ActionEvent event) throws IOException {
        Posts ps = new Posts();
        Post p = new Post(null, 3, false, tftitle.getText(), tfcontent.getText(), "", new Date());
        ps.insert(p);
        
    }
    
    public void cancel (ActionEvent event) throws IOException{
        
    AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
    dashforumback.getChildren().setAll(pane);
    }
        


}
