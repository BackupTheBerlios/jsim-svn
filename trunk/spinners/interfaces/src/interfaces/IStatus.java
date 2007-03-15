/*
 * IStatus.java
 *
 * Created on 30 October 2006, 21:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package interfaces;

/**
 *
 * @author Roger
 */
public interface IStatus {
    //the CoOp modes:
    final static int NORMAL = 0;
    final static int CONFLICT = 1;
    final static int SAFE = 2;
    final static int PRODUCER_CONSUMER = 3;
    //the Functions:
    final static int SPINNER = 0;
    final static int LINER = 1;
    //the Parameters        
    final static int INCREMENT = 1;     //Constants identifying parameters
    final static int DIRECTION = 2;
    final static int DELAY = 3;
    final static int DELAY_FACTOR = 4;   
    final static int SEQUENCER = 5;
   
//    public void makeStatus(String name, String id, int mode);
    public IStatus getStatus();
//    public void setStatus(String name, String id, int function,
//            int mode, int coOpMode,
//                int increment, boolean direction, int delay, int delayFactor,
//                     int maxPosIndex, int maxColorIndex, boolean blackOut);
    public void copyStatus(IStatus fromStatus);

    public String getName();
    public void setName(String val);
    
    public String getId();
    public void setId(String val);    

    public int getFunction();
    public void setFunction(int val); 
    
    public int getMode();
    public void setMode(int val);        

    public int getCoOpMode();
    public void setCoOpMode(int val);    
    
    public int getIncrement();
    public void setIncrement(int val);    
    
    public boolean getDirection();
    public void setDirection(boolean val);       

    public int getDelay();
    public void setDelay(int val);    

    public int getDelayFactor();
    public void setDelayFactor(int val);    
    
    public boolean getBlackOut();
    public void setBlackOut(boolean val);           
    
    public int getMaxPosIndex();
    public void setMaxPosIndex(int val);    
    
    public int getMaxColorIndex();
    public void setMaxColorIndex(int val);
}            

