/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asa368fxmlstopwatchmvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 *
 * @author a3yko
 * 
 * This code was used from 
 * https://www.oracle.com/technetwork/articles/javase/mvc-136693.html
 */

public abstract class AbstractModel
{
    protected Timeline timeline;
    protected KeyFrame keyFrame;
    protected double secondsElapsed = 0.0;
    protected double tickTimeInSeconds = 1.0;
    protected double angleDeltaPerSeconds = 6.0;
    protected String timew = "--:--:--";

    
    
    protected PropertyChangeSupport propertyChangeSupport;

    public AbstractModel()
    {
        propertyChangeSupport = new PropertyChangeSupport(this);
        
        if(isRunning()){
        timeline.stop();
        }
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds*1000), 
            (ActionEvent event)->{
                update();
            });       
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
    
    public void start(){
    timeline.play();
    }

    public void stop(){
    timeline.stop();
    }
   
    public double getTime(){
    return secondsElapsed;
    }
    
    public void setTickTimeInSeconds(double tickTimeInSeconds){
     this.tickTimeInSeconds = tickTimeInSeconds;
    }
    
    public void reset(){
    this.secondsElapsed = 0.0;
    this.timew = "--:--.--";
    timeline.stop();
    update();
    }
    
    
    abstract void update();
    public boolean isRunning(){

      if(timeline != null){
          if(timeline.getStatus() == Animation.Status.RUNNING){
              return true;
          }
      }
      return false;
    }
    
}