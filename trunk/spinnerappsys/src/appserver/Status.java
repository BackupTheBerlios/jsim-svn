/*
 * Status.java
 *
 * Created on 05 November 2006, 20:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package appserver;

import interfaces.*;

/**
 *
 * @author Roger
 */
public class Status implements IStatus{    
    int id;
    int mode;
    int function;
    int coOpMode;
    private int increment;
    boolean direction;
    int delay;
    int delayFactor;
    int maxPosIndex;
    int maxColorIndex;
    boolean blackOut;
    
    /**
     * Creates a new instance of Status
     */
    public Status(int i) {
    }
 
    public Status(){
        
    }
    
    public IStatus getStatus(){
        Status toStatus = new Status();
        toStatus.setId(id);
        toStatus.setMode(mode);
        toStatus.setFunction(function);
        toStatus.setCoOpMode(coOpMode);
        toStatus.setIncrement(increment);
        toStatus.setDirection(direction);
        toStatus.setDelay(delay);
        toStatus.setDelayFactor(delayFactor);
        toStatus.setMaxPosIndex(maxPosIndex);
        toStatus.setMaxColorIndex(maxColorIndex);
        toStatus.setBlackOut(blackOut);        
        return toStatus;
    }
    public void setStatus(IStatus fromStatus){
        id = fromStatus.getId();
        mode = fromStatus.getMode();
        function = fromStatus.getFunction();
        coOpMode = fromStatus.getCoOpMode();
        increment = fromStatus.getIncrement();
        direction = fromStatus.getDirection();
        delay = fromStatus.getDelay();
        delayFactor = fromStatus.getDelayFactor();
        maxPosIndex = fromStatus.getMaxPosIndex();
        maxColorIndex = fromStatus.getMaxColorIndex();
        blackOut = fromStatus.getBlackOut();
    }    
    public void makeStatus(int i, int sysMode){
        id = i;
        function = SPINNER;
        mode = sysMode;
        switch(mode){
            // Decide whether to do anything special depending on mode
            case NORMAL: break;                     
            case CONFLICT: break;
            case SYNCHRONIZED: break;
            case PRODUCER_CONSUMER: break;
            default: ;
        }
        coOpMode = NORMAL;
        increment = 6;          //angle increment/step
        direction = true;
        switch(id){
            // High speed for spinner[0], slower for others: 1/3/9/27
            case 0: delay = 1; break;
            case 1: delay = 3; break;
            case 2: delay = 9; break;
            case 3: delay = 27; break;
            default: delay = 1;
        }
        delayFactor = 5;
        if (direction == true) 
            maxPosIndex = 360/increment;
        else maxPosIndex = 0;
        maxColorIndex = 14;
        if ((i == 0)&&(mode == PRODUCER_CONSUMER))
            blackOut = true;
        else blackOut = false;
        System.out.println("Status: "+i+" fn: "+function+" mode: "+mode);
    }
    
    public int getId(){
        return id;
    }
    public void setId(int val){
        id = val;
    }    
    
     public int getMode(){
        return mode;
    }  
    public void setMode(int val) {
        mode = val;
    }
    
    public int getCoOpMode(){
        return coOpMode;
    }
    public void setCoOpMode(int val){
        coOpMode = val;
    }    
    
    public int getFunction(){
        return function;
    }
    public void setFunction(int val){
        function = val;
    }
         
    public int getIncrement() {
        return increment;
    }
    public void setIncrement(int val) {
        this.increment = val;
    }

    public boolean getDirection() {
        return direction;
    }
    public void setDirection(boolean val) {
        direction = val;
    }

    public int getDelay(){
        return delay;
    }
    public void setDelay(int val){
        delay = val;
    }

    public int getDelayFactor(){
        return delayFactor;
    }
    public void setDelayFactor(int val){
        delayFactor = val;
    }

    public boolean getBlackOut(){
        return blackOut;
    }
    public void setBlackOut(boolean val) {
        blackOut = val;
    }

    public int getMaxPosIndex() {
        return maxPosIndex;
    }
    public void setMaxPosIndex(int val) {
        maxPosIndex = val;
    }

    public int getMaxColorIndex() {
        return maxColorIndex;
    }
    public void setMaxColorIndex(int val) {
        maxColorIndex = val;
    }


}            
    
