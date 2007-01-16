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
//    public void makeSetup();
    public String [] getArgs();
    public void initServer(int c, int m);
    
    public int getSysMode();
    public void setSysMode(int i);
    
    public int getClientCount();
    public void setClientCount(int i);

    public IStatus getStartupStatus(int i);    
    public IRecord getRunningRecord(int i);

    public IRecord cycleEnded(int i);
    
    public String messageSent(String m);
    
}

