package linkclient;

import linkclient.SimpleClient;
import interfaces.*;
import java.awt.Color;
import java.awt.List;
import appserver.*;

public class AppServer implements IServer, IComm{

    private List liste;
    private SimpleClient client;
    
    private int clientCount = 4;
    int sysMode = 0;
    IStatus status;
    IRecord record;

    String h;
    int p;
    
    public AppServer(String h, int p)
    {
        liste = new List(4);
        client = new SimpleClient(h,p,liste);
        
        sysMode = getSysMode();
        clientCount = getClientCount();        
        status = new Status();
        status.makeStatus(0,sysMode); //sets initial values for definiteness
        record = new Record();
    }
   
    public SimpleClient getClient(){
        return client;
    }
    
    public int getPort(){
        return p;
    }
    public void setPort(int p){
        client.setPort(p);
    }
    
    public String getHost(){
        return h;
    }
    public void setHost(String h){
        client.setHost(h);
    }

    public List getList(){
        return liste;
    }
    public void setList(List list){
        liste = list;
    }
////////////////////////////////////////////////////
    public IStatus getStartupStatus(int i){
        /*Make connection
         *Send message to get status*/
        try {
            client.checkedSendToServer("s" + i);
        }
        catch (Exception ex)
        {
          liste.add(ex.toString());
          liste.makeVisible(liste.getItemCount()-1);
          liste.setBackground(Color.yellow);
        }
        return status;
    }
    
    public IRecord getRunningRecord(int i){
        /*Make connection
         *Send message to get record*/
        try {
            client.checkedSendToServer("r" + i);
        }
        catch (Exception ex)
        {
          liste.add(ex.toString());
          liste.makeVisible(liste.getItemCount()-1);
          liste.setBackground(Color.yellow);
        }         
         //Receive record        
        return record;
    }
    
    public int getSysMode() {
        /*Make connection
         *Send message to get sysMode*/
        try {
//            client.checkedSendToServer("y");
            sysMode = 2;
        }
        catch (Exception ex)
        {
          liste.add(ex.toString());
          liste.makeVisible(liste.getItemCount()-1);
          liste.setBackground(Color.yellow);
        }         
         //Receive sysMode        
        return sysMode;
    }
        
    public int getClientCount() {
        /*Make connection
         *Send message to get clientCount*/
        try {
//            client.checkedSendToServer("c");
            clientCount = 4;
        }
        catch (Exception ex)
        {
          liste.add(ex.toString());
          liste.makeVisible(liste.getItemCount()-1);
          liste.setBackground(Color.yellow);
        }         
        //Receive clientCount
        return clientCount;
    }

    ///////////////////////////////////////
    public IRecord cycleEnded(int i){
        /*Make connection
         *Send message to indicate spinner id*/
        try {
            client.checkedSendToServer("e" + i);
        }
        catch (Exception ex)
        {
          liste.add(ex.toString());
          liste.makeVisible(liste.getItemCount()-1);
          liste.setBackground(Color.yellow);
        }         
        //Receive record
        return record;
    }

}
/* Made all calls through stub go to the full local server
 * rather than through the socket to the remote server, as 
 * a test:
 *      server.AppServer fullServer;
 *
 *      status = fullServer.getStartupStatus(i);
 *      record = fullServer.getRunningRecord(i);
 *      sysMode = fullServer.getSysMode();
 *      clientCount = fullServer.getClientCount();
 *      record = fullServer.cycleEnded(i);
 */
