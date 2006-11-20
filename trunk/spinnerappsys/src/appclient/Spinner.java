// netbeans\spinners\spinner_parts\src\thread_spinners\Spinner.java
package appclient;

import interfaces.*;
import java.awt.*;
import java.util.Observable;
import appserver.*;

/**
 * 
 * 
 * 
 * @author Roger Prowse
 */
public class Spinner extends Observable{
    // Attributes
//    IServer refServer;
    IStatus startupStatus;
//    IRecord runningRecord;		// Spinner state
    
    int id;
    int mode;
    int maxPosIndex;
    
    int radius;
    int increment;
    Point Center = new Point();
    Point startPoint = new Point();
    Point endPoint = new Point();
    
    double theRadians;
    boolean theDirection;
    Color [] ColorTable = new Color[15];
    Color theColor;
    boolean blackOut;
    
    boolean newCycle;
    
    // Constructor
    public Spinner(int i){
        startupStatus = new Status();
//        runningRecord = new Record();
    }
        
    public void startSpinner(IStatus status/*, IRecord record*/){
        id = status.getId();
        mode = status.getMode();
        maxPosIndex =  status.getMaxPosIndex();
        increment = status.getIncrement();
        theDirection = status.getDirection();
        blackOut = status.getBlackOut();
        
        Center.x = 80;
        Center.y = 110;
        radius = 70;
        
        theRadians = 0;
        theColor = new Color(0,0,0);
        //Color table
        ColorTable[0] = new Color(255, 255,   0);	//Yellow
        ColorTable[1] = new Color(128, 128, 128);	//Shadow
        ColorTable[2] = new Color(  0,   0, 255);	//Blue
        ColorTable[3] = new Color(128, 128, 128);	//Shadow
        ColorTable[4] = new Color(  0, 255,   0);	//Green
        ColorTable[5] = new Color(128, 128, 128);	//Shadow
        ColorTable[6] = new Color(255,   0,   0);	//Red
        ColorTable[7] = new Color(128, 128, 128);	//Shadow
        ColorTable[8] = new Color(  0, 255, 255);	//Cyan
        ColorTable[9] = new Color(128, 128, 128);	//Shadow
        ColorTable[10] = new Color(255,   0, 255);	//Magenta
        ColorTable[11] = new Color(128, 128, 128);	//Shadow
        ColorTable[12] = new Color(255, 255, 255);	//White
        ColorTable[13] = new Color(128, 128, 128);	//Shadow

        newCycle = false;
        System.out.println("Made Spinner " + id);
    }
    
    //////////////////////////////////////////////////////////////
    public boolean executeOneStep(IRecord record){
        // Update the spinner record.getPosIndex());
        // Increment the angle for direction true (anti-clockwise)
        //      if Direction == true then inc else dec         
        record.incPosIndex();
        // Run the SpinStepper to make a lineSpec and send it to the displayCanvas
        makeNextLine(record);
        if (record.getPosIndex() == maxPosIndex){//NB: different for reverse
            // the spinner app has completed a full cycle,
            // so indicate that a new cycle record is required
            newCycle = true;}
        else newCycle = false;
        return newCycle;    
    }//executeOneStep
    
    /////////////////////////////////////////////
    public void makeNextLine(IRecord aRecord){
        //PRE:  aRecord from the Spinner
        //POST: Calculations done to define the line for the next step
        //          Direction is irrelevant: it is used by Spinner
        LineSpec lineSpec = new LineSpec();
//        System.out.println("Update " + aRecord.getId()+" pos "+aRecord.getPosIndex());
        startPoint.x = Center.x;
        startPoint.y = Center.y;        
        lineSpec.setStartPoint(startPoint);
        
        theRadians = getRadians(aRecord.getPosIndex());      
        endPoint.x = (int) (Center.x + radius * Math.sin(theRadians));
        endPoint.y = (int) (Center.y + radius * Math.cos(theRadians));
        lineSpec.setEndPoint(endPoint);
        
        theColor = getColor(aRecord.getColorIndex());
        lineSpec.setColor(theColor);
        lineSpec.setBlackOut(blackOut);
        
        // Tell display about the new lineSpec
        setChanged();
        notifyObservers(lineSpec);
    }
        
    public synchronized double getRadians(int newStep){
        theRadians = ((double)(newStep * increment) * 0.017453292);
        return theRadians;
    }
    
    public synchronized Color getColor(int newIndex){
        theColor = ColorTable[newIndex];
        return theColor;
    }    
}//Spinner
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////



