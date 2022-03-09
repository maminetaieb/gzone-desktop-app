/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Post;
import com.example.service.Posts;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;

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
    @FXML
    private Button btncapture;
    @FXML
    private ImageView imgCapture;

    
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
        if(!tftitle.getText().isBlank() || (!tfcontent.getText().isBlank())){
        Posts ps = new Posts();
        Post p = new Post(null, 3, false, tftitle.getText(), tfcontent.getText(), "", new Date());
        ps.insert(p);
        }   else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("One of your Text fields is empty");
            a.show();
        }
    }
    
    @FXML
    public void cancel (ActionEvent event) throws IOException{
        
    AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
    dashforumback.getChildren().setAll(pane);
    }
    void Forum(ActionEvent event) throws IOException {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
                dashforumback.getChildren().setAll(pane);
        }
    @FXML
    void capture(ActionEvent event) throws AWTException, IOException {
        Robot robot = new Robot();
        Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage image = robot.createScreenCapture(rectangle);
        Image myImage = SwingFXUtils.toFXImage(image, null);
        ImageIO.write(image, "jpg",new File("out.jpg"));
             
        
        imgCapture.setImage(myImage);
    }


}
