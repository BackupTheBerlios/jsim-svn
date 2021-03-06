/*
 * DisplayCanvas.java
 *
 * Created on 12 October 2006, 20:09
 *      Set up to be as independent of the pattern to be drawn as possible.
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package appgui;

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
    Point centerPoint = new Point();    
//    Dimension initialSize;
    Dimension size;
    IAppController refAppController;
    
    /**
     * Creates a new instance of displayCanvas
     */
    public DisplayCanvas(IAppController appController) {
        setBackground(Color.black);
        size = getSize();
        
        // 'this' displayCanvas needs to observe changes in the relevant
        // 'Observable' activity or a clientInLink using
        //	getObservable() as defined in class 'appController'
        refAppController = appController;
        Observable theObservable = (Observable)appController.getObservable();
        theObservable.addObserver(this);        
    }

    /////////////////////////////////////////////
    public void update(Observable aObservable, Object/*IAppController*/ appController){
        //called by notify() in Observable
        repaint();
    }
    /////////////////////////////////////////////            
    public void update(Graphics g){
        // Called by repaint()
//        Dimension size;
        size = getSize();
        refAppController.generateNextImage(size,g);
    }
}

