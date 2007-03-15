/*
 * spinner\Status.java
 *
 * Created on 05 November 2006, 20:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package spinner;

import interfaces.*;
import java.io.Serializable;

/**
 *
 * @author Roger
 */
public class Status implements IStatus, Serializable{
    String name;
    String id;
    int function;
    int mode;
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
        name = "";
        id = "";
        function = 0;
        mode = 0;
        coOpMode = NORMAL;
        increment = 6;          //angle increment/step
        direction = true;
        delay = 1;
        delayFactor = 5;
        maxPosIndex = 0;
        maxColorIndex = 0;
        blackOut = false;
        System.out.println("Status: "+name+" Id = "+id);
    }
    public Status(String name, String id, int function,
            int mode, int coOpMode,
            int increment, boolean direction, int delay, int delayFactor,
            int maxPosIndex, int maxColorIndex, boolean blackOut){
        this.name = name;
        this.id = id;
        this.function = function;
        this.mode = mode;
        this.coOpMode = coOpMode;
        this.increment = increment;
        this.direction = direction;
        this.delay = delay;
        this.delayFactor = delayFactor;
        this.maxPosIndex = maxPosIndex;
        this.maxColorIndex = maxColorIndex;
        this.blackOut = blackOut;
    }
    public IStatus getStatus(){
        Status toStatus = new Status();
        toStatus.setName(name);
        toStatus.setId(id);
        toStatus.setFunction(function);
        toStatus.setMode(mode);
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
    public void setStatus(String name, String id, int function,
            int mode, int coOpMode,
                int increment, boolean direction, int delay, int delayFactor,
                     int maxPosIndex, int maxColorIndex, boolean blackOut){
        this.name = name;
        this.id = id;
        this.function = function;
        this.mode = mode;
        this.coOpMode = coOpMode;
        this.increment = increment;
        this.direction = direction;
        this.delay = delay;
        this.delayFactor = delayFactor;
        this.maxPosIndex = maxPosIndex;
        this.maxColorIndex = maxColorIndex;
        this.blackOut = blackOut;
    }
    public void copyStatus(IStatus fromStatus){
        name = fromStatus.getName();
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
    
    public String getName(){
        return name;
    }
    public void setName(String val){
        name = val;
    }
    
    public String getId(){
        return id;
    }
    public void setId(String val){
        id = val;
    }    
    
    public int getFunction(){
        return function;
    }
    public void setFunction(int val){
        function = val;
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
    
    //This allows the status values to be printed 
    public String toString(){
        String s = "Status "+name+" "+id+" "+function+" "+mode+" "+coOpMode+" "+increment+" "+
                direction+" "+delay+" "+delayFactor+" "+maxPosIndex+" "+
                maxColorIndex+" "+blackOut;
        return s;
    }
/*
    public void fromString(String s){
        String[] subStr;
        subStr = serializer.ExtractSubstrings(s);

        name = subStr[0].charAt(0);
        id = Integer.parseInt(subStr[1]);
        mode = Integer.parseInt(subStr[2]);
        function = Integer.parseInt(subStr[3]);
        coOpMode = Integer.parseInt(subStr[4]);
        increment = Integer.parseInt(subStr[5]);
        direction = Boolean.parseBoolean(subStr[6]);
        delay = Integer.parseInt(subStr[7]);
        delayFactor = Integer.parseInt(subStr[8]);
        maxPosIndex = Integer.parseInt(subStr[9]);
        maxColorIndex = Integer.parseInt(subStr[10]);
        blackOut = Boolean.parseBoolean(subStr[11]);        
    }
*/ 

   
      
   } 
