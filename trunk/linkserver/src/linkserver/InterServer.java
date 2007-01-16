// netbeans\spinners\thread_spinners\src\thread_spinners\InterServer.java

package linkserver;

//import appserver.*;
import appdata.*;
import interfaces.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;

/**
 * 'InterServer' stub
 *      Methods called by SimpleServer, to make calls to AppServer
 *      as though from a local client
 *
 *      Args: clientCount; sysMode; port
 * 
 * @author Roger Prowse
 * @version "%I%, %G%"
 */
public class InterServer implements IClient{
    IServer refServer;
    int clientCount;
    int sysMode;
    
    String[] args;
    SimpleServer simpleServer;
    List liste;
    
    int incr;//rwp - allows data objects to be updated for test.

    int p;
    /**
     */
    public InterServer(IServer appServer){
        refServer = appServer;
        //Use default 'p' unless there is an argument
        args = new String[1];
        args = appServer.getArgs();
        if (args[0] == null){p = 12345;}
        else p = Integer.parseInt(args[0]);
        
        liste = new List(4);        
        simpleServer = new SimpleServer(p,liste,this);
        System.out.println("InterServer getArgs - p: "+p);
    }

    public SimpleServer getSimpleServer(){
        return simpleServer;
    }
    public List getList(){
        return liste;
    }
//////////////////////////////////////////////////////
    public void getMessage(Object msg){
        String message = msg.toString();
        if (message.charAt(0)=='m'){
            String newString = message.replace('m',' ');//rwp
            simpleServer.sendToAllClients(newString);//rwp
            System.out.println("InterServer getMessage - sent as: "+newString);
        }
        else {
            incr += 1; //Provide updated value when testing data sent across link
            Command command = (Command)msg;
            IStatus status = new Status();
            IRecord record = new Record();            
            char name = command.getName();  
            int i = command.getId();
            System.out.println("InterServer getMessage() Command: "+ name + i);            
            switch(name){
                case 's': {
                    status = refServer.getStartupStatus(i);
//                    status.setIncrement(incr);//rwp - updates status for link
                    System.out.println("link|InterServer get(s) Status: "+status);
                    simpleServer.sendToAllClients(status);
                    break;
                }
                case 'r': {
                    record = refServer.getRunningRecord(i);
//                    record.setPosIndex(incr);//rwp
                    System.out.println("link|InterServer get(r) Record:"+record);
                    simpleServer.sendToAllClients(record);
                    break;
                }
                 case 'e': {
                    record = refServer.cycleEnded(i);
//                    record.setPosIndex(incr);//rwp
                    System.out.println("link|InterServer get(e) Record:"+record);
                    simpleServer.sendToAllClients(record);
                    break;
                 }
                 case 'c': {
                    refServer.setClientCount(i);
                    clientCount = refServer.getClientCount();
                    System.out.println("link|InterServer get(c) " +
                            "setClientCount:"+clientCount);
                    simpleServer.sendToAllClients(clientCount);
                    break;
                 }
                 case 'y': {
                    refServer.setSysMode(i);
                    sysMode = refServer.getSysMode();
                    System.out.println("link|InterServer get(y) setSysMode:"+
                            sysMode+" clientCount = "+clientCount);
                    liste.add("Mode = "+sysMode+"; Count = "+clientCount);                              
                    simpleServer.sendToAllClients(sysMode);
                    break;
                 }
                 
                default: {
                    simpleServer.sendToAllClients(msg); 
                    break;
                }
            }
        }
    }  

//---------------------------------------------------------------    
    //Dummy methods to satisfy IClient interface
    public void initSys(){}
    public void initClient(){}
    public void startClient(){}
    public int getSysMode(){
        return 0;}
    public int getCount(){
        return clientCount;}
    public Observable getObservable(int i){
        return new Observable();}
    public ActionListener getListener(int i){
            class Listener implements ActionListener{
                public Listener(){}
                public void actionPerformed(ActionEvent e){}
            }
        return new Listener();
    }

}//InterServer
/////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////

