/*
 * LineSpec.java
 *
 * Created on 30 October 2006, 12:48
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package appclient;

import java.awt.Color;
import java.awt.Point;

import interfaces.ILine;
/**
 *
 * @author Roger
 */
public class LineSpec implements ILine{
    Point startPoint;
    Point endPoint;
    Color color;
    boolean blackOut;
    
    /**
     * Creates a new instance of LineSpec
     */
    public LineSpec() {
        startPoint = new Point();
        endPoint = new Point();
        color = new Color(0,0,0);
        blackOut = false;
    }
    
    public void setStartPoint(Point val) {
        this.startPoint.x = val.x;
        this.startPoint.y = val.y;
    }
    public Point getStartPoint() {
        return startPoint;
    }

    public void setEndPoint(Point val) {
        this.endPoint.x = val.x;
        this.endPoint.y = val.y;
    }
    public Point getEndPoint() {
        return endPoint;
    }
    
    public void setColor(Color val) {
        color = val;
    }
    public Color getColor() {
        return color;
    }

    public boolean getBlackOut() {
        return blackOut;
    }
    public void setBlackOut(boolean val) {
        this.blackOut = val;
    }
    
}

