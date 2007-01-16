/*
 * DisplayCanvas.java
 *
 * Created on 12 October 2006, 20:09
 *      Set up to be as independent of the pattern to be drawn as possible.
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package appgui;

import appclient.AppClient;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import interfaces.*;
import java.awt.Dimension;
/**
 *
 * @author Roger
 */
public class DisplayCanvas extends Canvas implements Observer{
    IClient refClient;
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
    public DisplayCanvas(IClient appClient, int id) {
        refClient = appClient;
        setBackground(Color.black);
        initialSize = getSize();
        blackOut = false;
        
        // 'this' displayCanvas needs to observe changes in the relevant
        // 'Observable' e.g. a spinner or a clientInLink using
        //	getObservable(i) as defined in class 'appModel'
        Observable theObservable = (Observable)appClient.getObservable(id);
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

        double radius = Math.min(size.height, size.width)/2 - 5;	//5 pixel inset
        /////////////////////////////////////////
        // Observe the line from the Observable
        // Note: we must define the Object argument of 'update' as an 'ILine'
        theLine = (ILine)lineSpec;
        // Set the details of the line for display        
//        startPoint.x = theLine.getStartPoint().x;
//        startPoint.y = theLine.getStartPoint().y;
        startPoint.x = centerPoint.x;         //This moves to center
        startPoint.y = centerPoint.y;
//        endPoint.x = theLine.getEndPoint().x;
//        endPoint.y = theLine.getEndPoint().y; 
		endPoint.x = (int) (startPoint.x + radius * Math.sin(theLine.getRadians()));
		endPoint.y = (int) (startPoint.y + radius * Math.cos(theLine.getRadians()));		
        
        theColor = theLine.getColor();
        blackOut = theLine.getBlackOut();
//        System.out.println("Update endPoint" + theLine.getEndPoint()+" startPoint" + theLine.getStartPoint()+" color "+theLine.getColor());

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
/*
    public SpinnerPanel getSpinnerPanel() {
        return mSpinnerPanel;
    }

    public void setSpinnerPanel(SpinnerPanel val) {
        this.mSpinnerPanel = val;
    }
*/
}

