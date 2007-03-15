/*
 * Liner.java
 *
 * Created on 12 February 2007, 12:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
// netbeans\spinners\spinner_parts\src\thread_spinners\Spinner.java
package liner;

import interfaces.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;

/**
 * 
 * 
 * 
 * 
 * @author Roger Prowse
 */
public class Liner implements IAppObj, Serializable{
    IAppModel linerModel;
    IAppController linerController;
    IStatus status;
    IRecord record;

    // Constructor
    public Liner(){
        linerModel = new LinerModel();
        linerController = new LinerController(); 
        status = new Status();
        record = new Record();    
    }

    public void makeApp(IStatus status){
        setStatus(status);
        makeAppModel(status);
        makeAppController();
        //make runningRecord: name,id,mode,posIndex,colorIndex
        record = new Record(status.getName(),status.getId(),
                status.getMode(),8,9);
        System.out.println("Liner makeApp(): "+
                " "+linerModel+" "+linerController+" "+status+" "+record);
    }

    public IAppModel getAppModel(){
        return linerModel;
    }
    public void setAppModel(IAppModel linerModel){
        this.linerModel = linerModel;
    }
    public void makeAppModel(IStatus status){
        this.status = status;
        linerModel.setMaxColorIndex(status.getMaxColorIndex());
    }

    public IAppController getAppController(){
        return linerController;
    }    
    public void setAppController(IAppController linerController){
        this.linerController = linerController;
    }
    public void makeAppController(){
    }
    
    public IStatus getStatus(){
        return status;
    }
    public void setStatus(IStatus status){
        this.status = status;
    }
    
    public IRecord getRecord(){
        return record;
    }
    public void setRecord(IRecord record){
        this.record = record;
    }
    
////////////////////////////////////////////
class LinerModel implements IAppModel, Serializable{
    int maxColorIndex;
    
    LinerModel(){                        
    }

//    void makeLinerModel(IStatus status){
//        maxColorIndex = status.getMaxColorIndex();                        
//    }

    public IRecord update(IRecord record){
        // Called at endOfCycle
        // where endOfCycle = maxPosIndex for forward and 0 for reverse
        // What goes in here depends on the nature of this application.
        // First reset the posIndex
        record.setPosIndex(0); //depending on direction  //***
        // then increment the colour index
        record.setColorIndex(record.getColorIndex() + 1);
        if (record.getColorIndex() == maxColorIndex){ 
            // if all colours done, start again
            record.setColorIndex(0);
            //if ((record).incCount|2 == 0){
            // (record).setDirection(true)}
            //else (record).setDirection(false);
        }
        return record;
    }
    
    public void setMaxColorIndex(int val){
        maxColorIndex = val;
    }    
}    
////////////////////////////////////////////
class LinerController extends Observable 
        implements IAppController, Runnable, Serializable{
    ButtonSensor buttonSensor;
    IAppRunner appRunner;
    // Attributes
    String name;
    String id;
    int mode;
    int delay;
    int delayFactor;
    int period;    
    int maxPosIndex;
    
    int increment;
    Point first = new Point();    
    Point start = new Point();
    Point startPoint = new Point();
    Point endPoint = new Point();

    Point oldStart = new Point();
    Point oldEnd = new Point();
    
    Dimension initialSize;
    Color theColor;
    boolean blackOut;

    boolean theDirection;
    Color [] ColorTable = new Color[15];
    
    int stepNumber;
    boolean newCycle;
    
    // Constructor
    public LinerController(){        
    }
      
    public void startController(IAppRunner appRunner, IStatus status){
        this.appRunner = appRunner;
        name = status.getName();
        id = status.getId();
        mode = status.getMode();
        maxPosIndex =  status.getMaxPosIndex();
        increment = status.getIncrement();
        theDirection = status.getDirection();
        blackOut = status.getBlackOut();

        buttonSensor = new ButtonSensor(name, id);
        
        first.x = 5;
        first.y = 5;
        setStartPoint(start);
        stepNumber = 0;

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
    
    /////////////////////////////////////////////////////////////////
    public void run(){
        try{
            //-------------------
            //Define running speed for this activity
            delay = status.getDelay();
            delayFactor = status.getDelayFactor();
            period = delay*delayFactor;
            //-------------------
            while (true) {
                if(executeOneStep(record)){
                    //IF execute() returns TRUE, then call the Server,
                    //which goes to the appModel to 
                    //get a new Record for the next cycle.
                    record = appRunner.update();
                    System.out.println("AppController: run().update");               
                }
                Thread.sleep(period);
            }
        }catch(InterruptedException e){
            System.out.println("Interrupted sleep");
        }
    }

    //////////////////////////////////////////////////////////////
    public boolean executeOneStep(IRecord record){
        // If button has been pressed to stop, then wait.   
        buttonSensor.checkGoingState();
        // Update the Liner record.getPosIndex());
        // Increment the start for direction true (anti-clockwise)
        //      if Direction == true then inc else dec         
        stepNumber = record.incPosIndex();
        theColor = getColor(record.getColorIndex());
        // Tell display about the new image
        setChanged();
        notifyObservers(this);
        if (stepNumber >= maxPosIndex){//NB: different for reverse
            // the app has completed a full cycle,
            // so indicate that a new cycle record is required
            newCycle = true;}
        else newCycle = false;
        return newCycle;    
    }//executeOneStep
    
    //////////////////////////////////////////////////////////////////
    public void generateNextImage(Dimension size, Graphics g){         
        start.x = first.x;
        start.y = first.y + stepNumber*(size.height-10)/maxPosIndex;
        setStartPoint(start);

        double length = size.width - 10;	//5 pixel inset        
		endPoint.x = (int) (startPoint.x + length);
		endPoint.y = (int) (startPoint.y);		
        setEndPoint(endPoint);

        if (blackOut){   //blackOut the previous line     
//        if ((theId == 0)&&(theMode == 3)){
            g.setColor(Color.black);
            g.drawLine(oldEnd.x, oldEnd.y, oldStart.x, oldStart.y);
        }
        g.setColor(theColor);
        g.drawLine(endPoint.x, endPoint.y, startPoint.x, startPoint.y);
        
        oldEnd.x = endPoint.x; oldEnd.y = endPoint.y;        
        oldStart.x = startPoint.x; oldStart.y = startPoint.y;        
    }
    ////////////////////////////////////////
    public ButtonSensor getButtonSensor(){
        return buttonSensor;
    }

    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public int getMode(){
        return mode;
    }
    /**
     * Identifies the appController as an observable object
     * @return reference to the appController
     */
    public Observable getObservable(){        
        return this;
    }    
    /**
     * Identifies buttonSensor as a Listener
     * 
     * @return reference to buttonSensor[i]
     */
    public ActionListener getListener(){        
        return buttonSensor;
    }
    
    ////////////////////////////////////////////////
    public synchronized Color getColor(int newIndex){
        theColor = ColorTable[newIndex];
        return theColor;
    }
    
    public int getMaxPosIndex() {
        return maxPosIndex;
    }
    public void setMaxPosIndex(int val) {
        this.maxPosIndex = val;
    }

    public int getIncrement() {
        return increment;
    }
    public void setIncrement(int val) {
        this.increment = val;
    }

    public Point getStartPoint() {
        return startPoint;
    }
    public void setStartPoint(Point val)  {
        this.startPoint = val;
    } 
    
    public Point getEndPoint() {
        return endPoint;
    }
    public void setEndPoint(Point val) {
        this.endPoint = val;
    }

    public boolean getTheDirection() {
        return theDirection;
    }
    public void setTheDirection(boolean val) {
        this.theDirection = val;
    }

    public Color[] getColorTable() {
        return ColorTable;
    }
    public void setColorTable(Color[] val) {
        this.ColorTable = val;
    }

    public Color getTheColor() {
        return theColor;
    }
    public void setTheColor(Color val) {
        this.theColor = val;
    }

    public boolean getBlackOut() {
        return blackOut;
    }

    public boolean getNewCycle() {
        return newCycle;
    }

    public Point getOldStart() {
        return oldStart;
    }

    public Point getOldEnd() {
        return oldEnd;
    }
    public void setOldEnd(Point val) {
        this.oldEnd = val;
    }
  }//LinerController
}//Liner
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////
