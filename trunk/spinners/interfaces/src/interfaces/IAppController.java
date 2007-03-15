/*
 * IAppController.java
 *
 * Created on 10 February 2007, 20:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package interfaces;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 *
 * @author Roger
 */
public interface IAppController{    
    public void startController(IAppRunner appRunner, IStatus status);
    public boolean executeOneStep(IRecord record);
    public void generateNextImage(Dimension size, Graphics g);
    public ActionListener getListener();
    public Observable getObservable();    
    public String getName();
    public String getId();
    public int getMode();  
}
