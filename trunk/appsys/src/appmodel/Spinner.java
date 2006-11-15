// netbeans\spinners\spinner_parts\src\thread_spinners\Spinner.java
package appmodel;

import interfaces.*;
import java.awt.*;
import java.util.Observable;
import server.*;

/**
 * 
 * 
 * 
 * @author Roger Prowse
 */
public class Spinner extends Observable{
    // Attributes
    AppServer refServer;
    Status startupStatus;
    Record runningRecord;		// Spinner state
    
    int id;
    int mode;
    int maxPosIndex;
    int theManagerId;		// Identity of Manager object
    
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
    public Spinner(AppServer appServer, Status status, Record record/*, int id*/){
        refServer = appServer;
        startupStatus = status;
        runningRecord = record;
        
        id = status.getId();
        mode = status.getMode();
        maxPosIndex =  status.getMaxPosIndex();
        increment = status.getIncrement();
        theDirection = status.getDirection();
        blackOut = status.getBlackOut();
        // Depending on the Mode, allocate a Manager to each Spinner
        switch (mode){
            // Separate statuses
            case IStatus.NORMAL:
                theManagerId = id; //theManagerId = id;
                break;
            // Same startupStatus for all, but not synchronised
            case IStatus.CONFLICT:
                theManagerId = 0; //theManagerId = 0;
                break;
            // Same startupStatus for all, but synchronised for mutual exclusion
            case IStatus.SYNCHRONIZED:
                theManagerId = 0; //theManagerId = 0;
                break;
            // Producer/Consumer:
            // Only two Spinners 0 & 1 and they share Status 0
            case IStatus.PRODUCER_CONSUMER:
                theManagerId = id; //theManagerId = id;
                if (id == 1) {
                    theManagerId = 0;//theManagerId = 0;
                }
                break;
        }
        
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
        System.out.println("Made Spinner " + status.getId());
    }
    
    //////////////////////////////////////////////////////////////
    void executeOneStep(){
        // Update the spinner runningRecord        
         System.out.println("Spinner" +id+" pos: "+runningRecord.getPosIndex());
        // Increment the angle for direction true (anti-clockwise)
        //      if Direction == true then inc else dec         
        runningRecord.incPosIndex();
        // Run the SpinStepper to make a lineSpec and send it to the displayCanvas
        makeNextLine(runningRecord);
        if (runningRecord.getPosIndex() == maxPosIndex){//NB: different for reverse
            // the spinner app has completed a full cycle,
            // so send the id to the server which controls the parameters
            // for a pattern such as a Spinner.
            // Tell server to generate a new runningRecord
            runningRecord = refServer.cycleEnded(id); 
        }
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
    
    public int getTheManagerId() {
        return theManagerId;
    }
    public void setTheManagerId(int val) {
        this.theManagerId = val;
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


