// netbeans\spinners\spinner_parts\src\thread_spinners\Spinner.java
package spinner;

import interfaces.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;

/**
 * 
 * 
 * @author Roger Prowse
 */
public class Spinner implements IAppObj, Serializable{
    IClient refClient;
    IAppModel spinnerModel;
    IAppController spinnerController;
    IStatus status;
    IRecord record;

    // Constructor
    public Spinner(){
        status = new Status();   
    }

    public void makeApp(IStatus status){
        setStatus(status);
        spinnerModel = new SpinnerModel(((Status)status).getMaxFrameIndex());
        //make runningRecord: name,id,mode,posIndex,colorIndex
        record = new Record(status.getName(),status.getId(),
                status.getMode(),8,9);
        spinnerController = new SpinnerController(status,record); 
        System.out.println("Spinner makeApp(): "+
                " "+spinnerModel+" "+spinnerController+" "+status+" "+record);
    }
    
    public IAppModel getAppModel(){
        return spinnerModel;
    }
    public void setAppModel(IAppModel spinnerModel){
        this.spinnerModel = spinnerModel;
    }

    public IAppController getAppController(){
        return spinnerController;
    }
    public void setAppController(IAppController spinnerController){
        this.spinnerController = spinnerController;
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
class SpinnerModel implements IAppModel, Serializable{
    int maxColorIndex;
    
    SpinnerModel(int val){
        maxColorIndex = val;
    }

    public IRecord update(IRecord record){
        // Called at endOfCycle
        // where endOfCycle = maxPosIndex for forward and 0 for reverse
        // What goes in here depends on the nature of this application.
        // First reset the posIndex
        record.setPosIndex(0); //depending on direction
        // then increment the colour index
        record.setFrameIndex(record.getFrameIndex() + 1);
        if (record.getFrameIndex() == maxColorIndex){ 
            // all colours done, start again
            record.setFrameIndex(0);
            //if ((record).incCount|2 == 0){
            // (record).setDirection(true)}
            //else (record).setDirection(false);
        }
        return record;
    }
    
    public void setMaxFrameIndex(int val){
        maxColorIndex = val;
    }
}
/////////////////////////////////////////////    
class SpinnerController extends Observable 
        implements IAppController, Runnable, Serializable{
    IClient refClient;
    int i;
    IStatus status;
    IRecord record;
    ButtonSensor buttonSensor;
    // Attributes
    String name;
    String id;
    int mode;
    int delay;
    int delayFactor;
    int period;    
    int maxPosIndex;
    int maxColorIndex;

    int radius;
    int increment;
    Point center = new Point();
    Point startPoint = new Point();
    Point endPoint = new Point();

    Point oldStart = new Point();
    Point oldEnd = new Point();

    Dimension initialSize;
    Color theColor;
    boolean blackOut;

    private double theRadians;
    boolean theDirection;
    Color [] ColorTable = new Color[15];

    boolean newCycle;
    
    // Constructor
    public SpinnerController(IStatus status, IRecord record){
        this.status = status;
        this.record = record;
        startController(status);
        System.out.println("Made SpinnerController "/*+i*/);
    }
    
    public void startController(IStatus status){
        name = status.getName();
        id = status.getId();
        mode = status.getMode();
        increment = status.getIncrement();
        maxPosIndex = status.getMaxPosIndex(); 
        maxColorIndex = ((Status)status).getMaxFrameIndex();       
        theDirection = ((Status)status).getDirection();
        blackOut = status.getBlackOut();
        
        buttonSensor = new ButtonSensor(name, id);
        
        center.x = 80;
        center.y = 110;
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
        System.out.println("Made SpinnerController " + id);
    }

    /////////////////////////////////////////////////////////////////
    public void run(){
        try{
            //Define running speed for this activity
            delay = status.getDelay();
            delayFactor = status.getDelayFactor();
            period = delay*delayFactor;
            while (true) {
                if(executeOneStep(record)){
                    //IF execute() returns TRUE, then call the Server,
                    //which goes to the appModel to 
                    //get a new Record for the next cycle.
                    record = update(status.getIndex());
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
        // Update the spinner record.getPosIndex());
        // Increment the angle for direction true (anti-clockwise)
        //      if Direction == true then inc else dec         
        record.incPosIndex();
        theRadians = getRadians(record.getPosIndex());        
        theColor = getColor(record.getFrameIndex());
        // Tell display about the new image
        setChanged();
        notifyObservers(this);
        if (record.getPosIndex() >= maxPosIndex){//NB: different for reverse
            // the app has completed a full cycle,
            // so indicate that a new cycle record is required
            newCycle = true;}
        else newCycle = false;
        return newCycle;    
    }//executeOneStep

    public IClient getClient(){
        return refClient;
    }
    public void setClient(IClient appClient){
        refClient = appClient;
    }
    
    public IRecord update(int i){
        //Spinners use synchronous communication: 's'
        return refClient.update(i, 's');    
//           = refClient.getServer().getModel(i).update()
//               = refServer.getModel(i).update()        
    }    
    //////////////////////////////////////////////////////////////////
    public void generateNextImage(Dimension size, Graphics g){         
        center.x = size.width/2;
        center.y = size.height/2;
        setStartPoint(center);

        double radius = Math.min(size.height, size.width)/2 - 5;	//5 pixel inset        
		endPoint.x = (int) (startPoint.x + radius * Math.sin(theRadians));
		endPoint.y = (int) (startPoint.y + radius * Math.cos(theRadians));		
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

    ///////////////////////////////////////
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
    
    /////////////////////////////////////////////
    public synchronized double getRadians(int newStep){
        theRadians = ((double)(newStep * increment) * 0.017453292);
        return theRadians;
    }
    
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

    public int getRadius() {
        return radius;
    }
    public void setRadius(int val) {
        this.radius = val;
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
    public void setBlackOut(boolean val) {
        this.blackOut = val;
    }

    public boolean getNewCycle() {
        return newCycle;
    }
    public void setNewCycle(boolean val) {
        this.newCycle = val;
    }

    public Point getOldStart() {
        return oldStart;
    }
    public void setOldStart(Point val) {
        this.oldStart = val;
    }

    public Point getOldEnd() {
        return oldEnd;
    }
    public void setOldEnd(Point val) {
        this.oldEnd = val;
    }

    public Dimension getInitialSize() {
        return initialSize;
    }
    public void setInitialSize(Dimension val) {
        this.initialSize = val;
    }

    public int getMaxColorIndex() {
        return maxColorIndex;
    }

    public void setMaxColorIndex(int val) {
        this.maxColorIndex = val;
    }
  }//SpinnerController
}//Spinner
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////



