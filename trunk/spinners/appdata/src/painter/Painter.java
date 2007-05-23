package painter;
/*
 * Painter.java
 *
 * Created on 27 March 2007, 15:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import interfaces.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Observable;
//////////////////////
import java.io.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.imageio.*;
/////////////////////
/**
 * 
 * 
 * 
 * 
 * @author Roger Prowse
 */
public class Painter implements IAppObj, Serializable{
    IAppModel painterModel;
    IAppController painterController;
    IStatus status;
    IRecord record;
    
    // Constructor
    public Painter(){
        status = new Status();
    }

    public void makeApp(IStatus status){
        setStatus(status);
        painterModel = new PainterModel();
        //make runningRecord: name,id,mode,posIndex,colorIndex
        record = new Record(status.getName(),status.getId(),
                status.getMode(),0,0);
        painterController = new PainterController(status,record);
        
        System.out.println("Painter.makeApp(): "+
                " "+painterModel+" "+painterController+" "+status+" "+record);
    }

    public IAppModel getAppModel(){
        return painterModel;
    }
    public void setAppModel(IAppModel painterModel){
        this.painterModel = painterModel;
    }

    public IAppController getAppController(){
        return painterController;
    }    
    public void setAppController(IAppController painterController){
        this.painterController = painterController;
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
class PainterModel implements IAppModel, Serializable{
    int maxFrameIndex;
    
    PainterModel(){
        maxFrameIndex = status.getMaxFrameIndex();
    }

    public IRecord update(IRecord record){
        // Called at endOfCycle
        record.setFrameIndex(record.getFrameIndex() + 1);
        if (record.getFrameIndex() > maxFrameIndex){ 
            // if all frames done, start again after background image
            record.setFrameIndex(1);
            /////
//            for (int i = 0; i < 50; i++){
//                Thread.yield();
//                System.out.println("yielded"+i);
                try{
                    Thread.sleep(status.getDelay()*40);
                }catch(InterruptedException e){
                    System.out.println("Interrupted sleep");
                }
//            }
            /////            
        }
        return record;
    }
    
    public void setMaxFrameIndex(int val){
        maxFrameIndex = val;
    }
}    
////////////////////////////////////////////
class PainterController extends Observable 
        implements IAppController, Runnable, Serializable{
    
    IClient refClient;
    int i;
    Status status;
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
    
    int increment;
    Point first = new Point();    
    Point start = new Point();
    Point startPoint = new Point();
    Point endPoint = new Point();

    Point oldStart = new Point();
    Point oldEnd = new Point();
    
    Dimension size;
    Color theColor;
    boolean blackOut;

    boolean theDirection;
    Color [] ColorTable = new Color[15];
    
    int stepNumber;
    boolean newCycle;
    ////////////////////////////////////
    String imageFileName;
    BufferedImage img = null;
    String text;
    Font font;
    
    int w, h;
    boolean flicker = true;
    ////////////////////////////////////
    // Constructor
    public PainterController(IStatus status, IRecord record){        
        this.status = (Status)status;
        this.record = record;
        System.out.println("Making PainterController ");        
        startController(status);
        System.out.println("Made PainterController ");
    }
      
    public void startController(IStatus status){
        name = status.getName();
        id = status.getId();
        mode = status.getMode();
////////////////////////
        imageFileName = (String)(((Status)status).getFileList()).get(0);
        text = ((Status)status).getText();
        font = new Font("Serif", Font.PLAIN, 9);        
/////////////////////////        
        maxPosIndex =  status.getMaxPosIndex();
        increment = status.getIncrement();

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
        
        System.out.println("Made Painter " + id);
    }
/*
    public Graphics2D createDemoGraphics2D(Graphics g) {
        Graphics2D g2 = null;
        int width = w;//getSize().width; 
        int height = h;//getSize().height; 

        if (offImg == null || offImg.getWidth() != width ||
                        offImg.getHeight() != height) {
            offImg = (BufferedImage) createImage(width, height);
        } 

        if (offImg != null) {
            g2 = offImg.createGraphics();
            g2.setBackground(getBackground());
        }

        // .. clear canvas ..
        g2.clearRect(0, 0, width, height);

        return g2;
    }
*/    
    /////////////////////////////////////////////////////////////////
    public void run(){
        try {
            img = ImageIO.read(new File(imageFileName));
            System.out.println("Image is "+imageFileName+" "+img);                        
        } catch (IOException e) {
            System.out.println("EXCEPTION: Did not read file ");            
        }      
        //Define running speed for this activity
        delay = status.getDelay();
        delayFactor = status.getDelayFactor();
        period = delay*delayFactor;
        try{
            while (true) {
                if(executeOneStep(record)){
                    //IF execute() returns TRUE, then call the Server,
                    //which goes to the appModel to 
                    //get a new Record for the next cycle.
                    record = update(status.getIndex());
                    System.out.println("AppController: run().update");
//                    Thread.sleep(period);
                }
                Thread.sleep(period);
            }
        }catch(InterruptedException e){
            System.out.println("Interrupted sleep");
        }
    }
//        imageFileName = (String)(((Status)status).getFileList()).get(0);

    //////////////////////////////////////////////////////////////
    public boolean executeOneStep(IRecord record){
        // If button has been pressed to stop, then wait.   
        buttonSensor.checkGoingState();
        // Update the Painter 
        // and tell display about the new image
        setChanged();
        notifyObservers(this);
        int frameIndex = record.getFrameIndex();
        System.out.println("Record.frameIndex= "+frameIndex);            
        ////////////////////////////////
        imageFileName = 
                (String)(((Status)status).getFileList()).get(frameIndex);                   
        try {
            img = ImageIO.read(new File(imageFileName));
            System.out.println("Image is "+imageFileName+" "+img);                        
        } catch (IOException e) {
            System.out.println("EXCEPTION: Did not read file ");            
        }      
        return newCycle = true;    
    }//executeOneStep

    public IClient getClient(){
        return refClient;
    }
    public void setClient(IClient appClient){
        refClient = appClient;
    }
    public IRecord update(int i){
        //Painters use asynchronous communication: 'a'
        return refClient.update(i, 'a'); 
    }
    
    //////////////////////////////////////////////////////////////////
/*
 * Copyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.
*/
    public void generateNextImage(Dimension size, Graphics g){        
//    Called by paint(Graphics g){
        /* We might wish to draw the image stretched to exactly cover the size of the
         * drawing area, but that distorts Duke.  So we look at the size of the
         * rectangle into which the image is to be scaled and drawn, and 
         * arrange that it will be square, within the canvas size.  
         */
        Dimension rect = new Dimension(size.width,size.height);
        if (size.height > size.width){
            rect.height = size.width;
        }
        else {rect.width = size.height;}
        g.drawImage(img, 
                    0, 0, rect.width, rect.height,
                    0, 0, img.getWidth(null), img.getHeight(null),
                    null);                    

        /* Fill a rounded rectangle centered in the drawing area.
         * Calculate the size of the rectangle from the size of the text
         */
        g.setFont(font);
        FontRenderContext frc = ((Graphics2D)g).getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(text, frc);

        int wText = (int)bounds.getWidth();
        int hText = (int)bounds.getHeight();

        int rX = (size.width-wText)/2;
        int rY = (size.height-hText)/2;
        g.setColor(Color.yellow);
        g.fillRoundRect(rX, rY, wText, hText, hText/2, hText/2);

        /* Draw text positioned in the rectangle.
         * Since the rectangle is sized based on the bounds of
         * the String we can position it using those bounds.
         */
        int xText = rX-(int)bounds.getX();
        int yText = rY-(int)bounds.getY();
        g.setColor(Color.black);
        g.setFont(font);
        g.drawString(text, xText, yText);
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
  }//PainterController

//////////////////////////////////////////////////////////
/*
 * Copyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

public class ImageLabel extends Component {

    BufferedImage img;
    String text;
    Font font;
    
    public ImageLabel(BufferedImage img, String text) {
        
        this.img = img;
        this.text = text;
        font = new Font("Serif", Font.PLAIN, 36);
    }
    
    /* We want to size the component around the text.  */
    public Dimension getPreferredSize() {
        FontMetrics metrics = img.getGraphics().getFontMetrics(font);
        int width = metrics.stringWidth(text)*2;
        int height = metrics.getHeight()*2;
        return new Dimension(width, height);
    }
}


}//Painter
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////
