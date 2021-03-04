/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asa368fxmlstopwatchmvc;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 *
 * @author Professor Wergeles <Professor Wergeles at cs3330@missouri.edu>
 */
public class StopwatchModel extends AbstractModel{
    
    private double rotation;
    private double oldRotation;
    
    public StopwatchModel(){
    }  

    @Override
    void update() {
        oldRotation = secondsElapsed * angleDeltaPerSeconds;  
        secondsElapsed += tickTimeInSeconds;
        rotation = secondsElapsed * angleDeltaPerSeconds;
        firePropertyChange("Rotate", oldRotation, rotation);
}
      
}
