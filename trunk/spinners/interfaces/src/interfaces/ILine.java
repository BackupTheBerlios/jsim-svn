/*
 * ILine.java
 *
 * Created on 12 October 2006, 20:17
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package interfaces;

import java.awt.*;

/**
 *
 * @author Roger
 */
public interface ILine {

    public Point getStartPoint();
    public void setStartPoint(Point aPoint);
    public Point getEndPoint();
    public void setEndPoint(Point aPoint);
    public double getRadians();
    public void setRadians(double val);
    public Color getColor();
    public void setColor(Color aColor);
    public boolean getBlackOut();
    public void setBlackOut(boolean aBlackOut);
}
