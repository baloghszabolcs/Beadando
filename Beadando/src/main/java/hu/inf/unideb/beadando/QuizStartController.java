/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.inf.unideb.beadando;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author balogh
 */
public class QuizStartController implements Initializable{
//    @FXML
//    private  AnchorPane anchorpane;
    
    @FXML
    Button playButton;
    
    @FXML
    Button exitFxId;
    
    
    
    
   
    
    @FXML
    public void startGame(ActionEvent event) {
    
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/gameScene.fxml"));
        
        Stage stage = (Stage) playButton.getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/gameStyle.css");
        stage.setResizable(false);
        stage.setTitle("Start");
        stage.setScene(scene);
        stage.show();
            
            

        } catch (IOException ex) {
            Logger.getLogger(QuizStartController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    public void endGame(ActionEvent event){
        Platform.exit();
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
}
