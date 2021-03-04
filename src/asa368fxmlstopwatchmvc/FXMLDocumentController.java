/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asa368fxmlstopwatchmvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 *
 * @author a3yko
 */
public class FXMLDocumentController implements Initializable, PropertyChangeListener {
    @FXML
    Text time = new Text();
    
    @FXML
    Button start = new Button();
    
    @FXML
    Button lap = new Button();
    
    @FXML
    Text lapOne = new Text();
    
    @FXML
    Text lapTwo = new Text();
    
    @FXML
    Text lapThree = new Text();
    
    @FXML
    ImageView hand = new ImageView();
    
    private int lapCount = 1;
    private int a = 1;
    
    StopwatchModel model;
    DigitalTime timer;
    
    public void initialize(URL url, ResourceBundle rb) {
        model = new StopwatchModel();
        timer = new DigitalTime();
        hand.setRotate(0);
        time.setText("--:--:--"); 
        timer.addPropertyChangeListener(this);
        model.addPropertyChangeListener(this);
       
    }    
    public void handleLap(ActionEvent event){
        if (model.isRunning()){
            switch(a){
            case 1:
            lapOne.setText("Lap" + lapCount++ + ": " + model.getTime());
            a++;
            break;
            case 2:
            lapTwo.setText("Lap" + lapCount++ + ": " + model.getTime());
            a++;
            break;
            case 3:
            lapThree.setText("Lap" + lapCount++ + ": " + model.getTime());
            a++;
            break;
            default: 
            a = 1;
            break;
            }
        } else {
            lapOne.setText("--:--.--");
            lapTwo.setText("--:--.--");
            lapThree.setText("--:--.--"); 
            hand.setRotate(0);
            model.reset();
            timer.reset();
            lapCount = 1;
        }
    }
    
    public void handleStart(ActionEvent event){
        if(!model.isRunning()){
            start.setText("Stop");
            lap.setText("Record");
            model.start();
            timer.start();
        }
        else if (model.isRunning()){
            lap.setText("Reset");
            start.setText("Start");
            model.stop();
            timer.stop();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Rotate")){
        hand.setRotate((double) evt.getNewValue());
        }else if(evt.getPropertyName().equals("Time")){
        time.setText((String) evt.getNewValue());
        }
      }   
    }
