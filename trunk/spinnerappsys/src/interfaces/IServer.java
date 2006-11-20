/*
 * IServer.java
 *
 * Created on 17 November 2006, 15:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package interfaces;

/**
 *
 * @author Roger
 */
public interface IServer {
    public IStatus getStartupStatus(int i);    
    public IRecord getRunningRecord(int i);
    public int getSysMode();

    public int getClientCount();

    public IRecord cycleEnded(int i);
    
}

