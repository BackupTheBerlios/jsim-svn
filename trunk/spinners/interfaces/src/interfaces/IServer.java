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
//    public void makeDatabase();
    public String [] getArgs();
    public void initServer(int c, int m);
    
    public int getSysMode();
    public void setSysMode(int i);
    
    public int getClientCount();
    public void setClientCount(int i);

    public IAppObj getAppObj(int i);
//    public IAppController getAppController(int i);    
//    
//    public IStatus getStatus(int i);    
//    public IRecord getRecord(int i);

    public IRecord cycleEnded(int i);
    
    public void makeDatabase();
    
    public String messageSent(String m);
    
}

