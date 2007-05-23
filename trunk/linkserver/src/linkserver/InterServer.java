// netbeans\spinners\thread_spinners\src\thread_spinners\InterServer.java

package linkserver;

//import appserver.*;
import appdata.*;
import interfaces.*;
import java.awt.*;
import liner.Record;
import liner.Status;

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
public class InterServer{
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
            int i = command.getIndex();
            System.out.println("InterServer getMessage() Command: "+ name + i);            
            switch(name){
                 case 'a': {
                    Object appController = refServer.getAppController(i);
                    System.out.println("link|InterServer 'a' getAppController()");
                    liste.add("getAppController()");                              
                    simpleServer.sendToAllClients((IAppController)appController);
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
                 case 'd': {
                    refServer.makeDatabase();
                    System.out.println("link|InterServer 'd' makeDatabase()");
                    liste.add("makeDatabase()");                              
                    break;
                 }
                 case 'e': {
//                    record = refServer.cycleEnded(i);
                    record.setRecord(refServer.cycleEnded(i, 's'));                    
//                    record.setPosIndex(incr);//rwp
                    System.out.println("link|InterServer get(e) Record:"+record+
                            " getPosIndex() "+record.getPosIndex());                    
                    simpleServer.sendToAllClients(record);
                    break;
                 }
                 case 'f': {
                    record.setRecord(refServer.cycleEnded(i, 'a'));                    
                    System.out.println("link|InterServer get(f) Record:"+record+
                            " getPosIndex() "+record.getPosIndex());                    
                    simpleServer.sendToAllClients(record);
                    break;
                 }
//                case 's': {
//                    status = refServer.getStatus(i);
////                    status.setIncrement(incr);//rwp - updates status for link
//                    System.out.println("link|InterServer get(s) Status: "+status);
//                    simpleServer.sendToAllClients(status);
//                    break;
//                }
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
}//InterServer
/////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////


