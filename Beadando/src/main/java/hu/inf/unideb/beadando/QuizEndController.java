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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;




/**
 *
 * @author balogh
 */
public class QuizEndController implements Initializable {
    
     @FXML
    Button againButton;
     
    @FXML
    Button exitButtonl;
    
    @FXML
    Label scoreLabel;

    
    @FXML
    public void endGame(ActionEvent event){
        Platform.exit();
    }
    QuizViewController quiz = new QuizViewController();
    
    
    @FXML
    public void again(ActionEvent event){
         try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/startScene.fxml"));
        
        Stage stage = (Stage) againButton.getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/startStyle.css");
        stage.setResizable(false);
        stage.setTitle("Start");
        stage.setScene(scene);
        stage.show();
            
            

        } catch (IOException ex) {
            Logger.getLogger(QuizStartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    scoreLabel.setText("Your best score was: " + quiz.getScore()+"/"+quiz.getRandomNumbersSize() );

    }
    
}
