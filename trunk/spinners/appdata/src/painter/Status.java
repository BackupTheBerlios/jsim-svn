/*
 * painter\Status.java
 *
 * Created on 05 November 2006, 20:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package painter;

import interfaces.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Roger
 */
public class Status implements IStatus, Serializable{
    int index;
    String name;
    String id;
    String text;
    int function;
    int mode;
    private int increment;
    int delay;
    int delayFactor;
    int maxFrameIndex;
    ArrayList fileList;
    boolean blackOut;
    
    /**
     * Creates a new instance of Status
     */
    public Status(int i) {
        index = i;
    }
 
    public Status(){
        index = 0;
        name = "";
        id = "";
        text = "";
        function = 0;
        mode = 0;
        increment = 6;          //angle increment/step
        delay = 1;
        delayFactor = 5;
        maxFrameIndex = 1;
        blackOut = false;
        System.out.println("Painter.Status: "+name+" Id = "+id);
    }
    public Status(int i, String name, String id, String text, int function,
            int mode, int increment, int delay, 
            int delayFactor, int maxFrameIndex, ArrayList fileList,
            boolean blackOut){
        this.index = i;    
        this.name = name;
        this.id = id;
        this.text = text;
        this.function = function;
        this.mode = mode;
        this.maxFrameIndex = maxFrameIndex;
        this.fileList = fileList;
        this.increment = increment;
        this.delay = delay;
        this.delayFactor = delayFactor;
        this.blackOut = blackOut;
    }

    public IStatus getStatus(){
        Status toStatus = new Status();
        toStatus.setIndex(index);
        toStatus.setName(name);
        toStatus.setId(id);
        toStatus.setText(text);
        toStatus.setFunction(function);
        toStatus.setMode(mode);
        toStatus.setIncrement(increment);
        toStatus.setDelay(delay);
        toStatus.setDelayFactor(delayFactor);
        toStatus.setMaxFrameIndex(maxFrameIndex);
        toStatus.setFileList(fileList);
        toStatus.setBlackOut(blackOut);        
        return toStatus;
    }
    
//    public void setStatus(int i, String name, String id, String text, int function,
//            int mode, int coOpMode,
//                int increment, boolean direction, int delay, int delayFactor,
//                     int maxPosIndex, int maxFrameIndex, boolean blackOut){
//        this.index = i;
//        this.name = name;
//        this.id = id;
//        this.text = text;
//        this.function = function;
//        this.mode = mode;
//        this.increment = increment;
//        this.delay = delay;
//        this.delayFactor = delayFactor;
//        this.maxPosIndex = maxPosIndex;
//        this.maxFrameIndex = maxFrameIndex;
//        this.blackOut = blackOut;
//    }
//
//    public void copyStatus(IStatus fromStatus){
//        index = fromStatus.getIndex();
//        name = fromStatus.getName();
//        id = fromStatus.getId();
//        text = fromStatus.getText();
//        mode = fromStatus.getMode();
//        function = fromStatus.getFunction();
//        increment = fromStatus.getIncrement();
//        delay = fromStatus.getDelay();
//        delayFactor = fromStatus.getDelayFactor();
//        maxPosIndex = fromStatus.getMaxPosIndex();
//        maxFrameIndex = fromStatus.getMaxFrameIndex();
//        blackOut = fromStatus.getBlackOut();
//    }    

    public int getIndex(){
        return index;
    }  
    public void setIndex(int val) {
        index = val;
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
    
    public String getText(){
        return text;
    }
    public void setText(String val){
        text = val;
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
    
    public ArrayList getFileList(){
        return fileList;
    }
    public void setFileList(ArrayList val){
        fileList = val;
    }
         
    public int getIncrement() {
        return increment;
    }
    public void setIncrement(int val) {
        this.increment = val;
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
    
    public int getMaxFrameIndex(){
        return maxFrameIndex;
    }
    public void setMaxFrameIndex(int val){
        maxFrameIndex = val;
    }    

    public boolean getBlackOut(){
        return blackOut;
    }
    public void setBlackOut(boolean val) {
        blackOut = val;
    }

    //This allows the status values to be printed 
    public String toString(){
        String s = "Status "+name+" "+id+" "+text+" "+function+" "+
                mode+" "+maxFrameIndex+" "+increment+" "+delay+
                    " "+delayFactor+" "+blackOut;
        return s;
    }
    
    public int getMaxPosIndex(){//Dummy
        return maxFrameIndex;
    }
    public void setMaxPosIndex(int val){//Dummy
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
