/*
 * displayCanvas.java
 *
 * Created on 12 October 2006, 20:09
 *      Set up to be as independent of the pattern to be drawn as possible.
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package appgui;

import appmodel.AppModel;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

//import interfaces.IModel;
import interfaces.ILine;
import java.awt.Dimension;
//import interfaces.IDisplayController;
/**
 *
 * @author Roger
 */
public class displayCanvas extends Canvas implements Observer{
    AppModel refModel;
    ILine theLine;
    Point centerPoint = new Point();
    
    Point startPoint = new Point();
    Point oldStart = new Point();
    
    Point endPoint = new Point();
    Point oldEnd = new Point();
    Dimension initialSize;
    Color theColor;
    boolean blackOut;
    
    /**
     * Creates a new instance of displayCanvas
     */
    public displayCanvas(AppModel appModel,/*displayController aDisplayController,*/ int id) {
        refModel = appModel;
//        refDisplayController = aDisplayController;
//        theId = id;
        setBackground(Color.black);
        initialSize = getSize();
        blackOut = false;
        
        // 'this' displayCanvas needs to observe changes in the relevant
        // 'Observable' e.g. a spinner or a clientInLink using
        //	getObservable(i) as defined in class 'appModel'
        //**********
        Observable theObservable = (Observable)appModel.getObservable(id);
        theObservable.addObserver(this);
        
    }
        
        /////////////////////////////////////////////
    public void update(Observable aObservable, Object/*ILine*/ lineSpec){
        //PRE:  aRecord from the Observable object
        //      This has all the data needed to draw lines of a chosen colour:
        //         startPoint(x,y), endPoint(x,y), colour, blackOut
        //      Some parameters such as direction are used by higher layers
        //      and are not needed here.
        //POST: Canvas repainted with line defined in Record
        // Resizing technique defined in 'Java2 Complete' p362
        Dimension size;
        size = getSize();
        centerPoint.x = size.width/2;
        centerPoint.y = size.height/2;
/*        startPoint.x = Math.min(startPoint.x, size.width - 5);
        startPoint.y = Math.min(startPoint.y, size.height - 5);
        endPoint.x = Math.min(endPoint.x, size.width - 5);
        endPoint.y = Math.min(endPoint.y, size.height - 5); 
 */            
        //Radius = Math.min(size.height, size.width)/2 - 5;	//5 pixel inset
        /////////////////////////////////////////
        // Observe the line from the Observable
        // Note: we must define the Object argument of 'update' as an 'ILine'
        theLine = (ILine)lineSpec;
        // Set the details of the line for display
        startPoint.x = theLine.getStartPoint().x;
        startPoint.y = theLine.getStartPoint().y;
        endPoint.x = theLine.getEndPoint().x;
        endPoint.y = theLine.getEndPoint().y; 
        theColor = theLine.getColor();
        blackOut = theLine.getBlackOut();
        //Automatic sizing here just for locating spinner lines
        //within the display canvas
        //In the general case sizing needs a proper transformation for
        //start and end points
        startPoint.x = Math.min(startPoint.x, size.width - 5);
        startPoint.y = Math.min(startPoint.y, size.height - 5);
        endPoint.x = Math.min(endPoint.x, size.width - 5);
        endPoint.y = Math.min(endPoint.y, size.height - 5);
        System.out.println("Update endPoint" + theLine.getEndPoint()+" startPoint" + theLine.getStartPoint()+" color "+theLine.getColor());

        repaint();        
    }
    
    public void update(Graphics g){
        // Update to paint
        paint(g);
    }
    
    public void paint(Graphics g){ 
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

}
