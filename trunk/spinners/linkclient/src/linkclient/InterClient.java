package linkclient;

//import appserver.*;
import appdata.*;
import interfaces.*;
import java.awt.*;
import liner.Record;
import liner.Status;

/**
 * 'InterClient' stub
 *      Methods calling SimpleClient, to make calls to InterClient
 *      as though to a local server
 * 
 * @author Roger Prowse
 * @version "%I%, %G%"
 */
public class InterClient implements IServer{

    private List liste;
    private SimpleClient simpleClient;
    
    private int clientCount;
    int sysMode;

    String[] refArgs;
    String h;
    int p;
    
    public InterClient(String[] args){
        if (args.length<=2){
            h = "localhost";
            p = 12345;}
        if (args.length==3){
            h = "localhost";
            p = Integer.parseInt(args[2]);}
        if (args.length==4){
            h = args[2];
            p = Integer.parseInt(args[3]);}
        this.liste = new List(4);
        simpleClient = new SimpleClient(h,p,liste);
    }
   
    public String[] getArgs(){
        return refArgs;
    }
    
    public SimpleClient getSimpleClient(){
        return simpleClient;
    }    

    public List getList(){
        return liste;
    }
    
    public void initServer(int c, int m){
        //Here we provide the count and mode to the server
        setClientCount(c);
        setSysMode(m);
        makeDatabase();
    }
 
////////////////////////////////////////////////////
    public synchronized IStatus getStatus(int i){
        /*Make connection
         *Send message to get status*/
        IStatus status = new Status();
        try {
            ICommand command = new Command('s', i);
            char c = command.getName();
            simpleClient.checkedSendToServer(command);
            status = (Status)simpleClient.getReply();
//            status.setStatus((IStatus)simpleClient.getReply());                         
        }
        catch (Exception ex)
        {
          liste.add(ex.toString());
          liste.makeVisible(liste.getItemCount()-1);
          liste.setBackground(Color.yellow);
        }
        System.out.println("link|InterClient getStartupStatus() = "+ status);
        return status;
    }
    
    public synchronized IRecord getRecord(int i){
        /*Make connection
         *Send message to get record*/
        Record record = new Record();
        try {
            Command command = new Command('r', i);
            simpleClient.checkedSendToServer(command);            
            record = (Record)simpleClient.getReply();
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
    
    public synchronized IAppObj getAppObj(int i){
//        /*Make connection
//         *Send message to get record
        Object appObj = new Object();
        try {
            Command command = new Command('a', i);
            simpleClient.checkedSendToServer(command);            
            appObj = (IAppObj)simpleClient.getReply();
        }
        catch (Exception ex)
        {
          liste.add(ex.toString());
          liste.makeVisible(liste.getItemCount()-1);
          liste.setBackground(Color.yellow);
        }         
         //Receive record        
        return (IAppObj)appObj;
    }   
    
    public int getClientCount(){
        return clientCount;
    }
    public synchronized void setClientCount(int count) {
        /*Send message to set clientCount*/
        try {
            Command command = new Command('c', count);
            char c = command.getName();
            System.out.println("link|InterClient notifyClientCount = "+ c);            
            simpleClient.checkedSendToServer(command);
            //no need to do anything with this return count
            int clientCount = (Integer)simpleClient.getReply();            
        }
        catch (Exception ex)
        {
          liste.add(ex.toString());
          liste.makeVisible(liste.getItemCount()-1);
          liste.setBackground(Color.yellow);
        }         
    }
    
    public int getSysMode(){
        return IStatus.SAFE;
    }   
    public synchronized void setSysMode(int mode) {
        /*Send message to set sysMode*/
        try {
            Command command = new Command('y', mode);
            char c = command.getName();
            System.out.println("link|InterClient notifySysMode = "+ c);            
            simpleClient.checkedSendToServer(command);
            //no need to do anything with this return mode
            int sysMode = (Integer)simpleClient.getReply();
        }
        catch (Exception ex)
        {
          liste.add(ex.toString());
          liste.makeVisible(liste.getItemCount()-1);
          liste.setBackground(Color.yellow);
        }         
    }
    public synchronized void makeDatabase() {
        /*Send message to populate Database*/
        try {
            Command command = new Command('d',0);
            char c = command.getName();
            System.out.println("link|InterClient makeDatabase = "+ c);            
            simpleClient.checkedSendToServer(command);
            //no need to do anything with this return mode
            //int sysMode = (Integer)simpleClient.getReply();
        }
        catch (Exception ex)
        {
          liste.add(ex.toString());
          liste.makeVisible(liste.getItemCount()-1);
          liste.setBackground(Color.yellow);
        }         
    }
        
    ///////////////////////////////////////
    public synchronized IRecord cycleEnded(int i){
        /*Make connection
         *Send message to indicate spinner id*/
        Record record = new Record();
        try {
            Command command = new Command('e', i);
            char c = command.getName();
            System.out.println("InterClient cycleEnded() = "+c);//+"  "+
            simpleClient.checkedSendToServer(command);
            record = (Record)simpleClient.getReply();
            System.out.println("InterClient cycleEnded(): "+record);//+"  "+
//                record.getName()+record.getId()+
//                record.getPosIndex()+record.getColorIndex());            
        }
        catch (Exception ex)
        {
          liste.add(ex.toString());
          liste.makeVisible(liste.getItemCount()-1);
          liste.setBackground(Color.yellow);
        }         
        return record;
    }
    
    public synchronized String messageSent(String message){
        String reply = new String();
        try {
            simpleClient.checkedSendToServer(/*'m'+*/message);
            System.out.println("InterClient messageSent(): "+message+" ");
            reply = (String)simpleClient.getReply();
            liste.add(reply);
            liste.makeVisible(liste.getItemCount()-1);
        }
        catch (Exception ex)
        {
          liste.add(ex.toString());
          liste.makeVisible(liste.getItemCount()-1);
          liste.setBackground(Color.yellow);
        }         
        return reply;
    }
 
}

/* Made all calls through stub go to the full local server
 * rather than through the socket to the remote server, as 
 * a test:
 *      server.InterClient fullServer;
 *
 *      status = fullServer.getStartupStatus(i);
 *      record = fullServer.getRunningRecord(i);
 *      sysMode = fullServer.getSysMode();
 *      clientCount = fullServer.getClientCount();
 *      record = fullServer.cycleEnded(i);
 */
