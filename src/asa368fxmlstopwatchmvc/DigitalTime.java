package asa368fxmlstopwatchmvc;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author a3yko
 */
public class DigitalTime extends AbstractModel {
    
    private String temp = "--:--:--";
    private int sec, milis, min; 
    
    public DigitalTime(){
    } 

    @Override
    void update() {
    if(isRunning()){
        secondsElapsed += tickTimeInSeconds;
        temp = timew;
        min = (int)secondsElapsed / 60;
        sec = (int)secondsElapsed - (min * 60);
        milis = (int)((secondsElapsed- sec) * 100);
        timew = String.valueOf(min) + ":" + String.valueOf(sec) + "." + String.valueOf(milis);
        firePropertyChange("Time", temp , timew);
    }else{
      firePropertyChange("Time", temp , timew);   
      }
    }
}
