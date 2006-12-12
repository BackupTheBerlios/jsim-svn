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
    public String [] getArgs();
    public int getSysMode();
    public int getClientCount();

    public IStatus getStartupStatus(int i);    
    public IRecord getRunningRecord(int i);

    public IRecord cycleEnded(int i);
    
}

