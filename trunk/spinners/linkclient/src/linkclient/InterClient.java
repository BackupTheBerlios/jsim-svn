package linkclient;

//import appserver.*;
import appdata.*;
import interfaces.*;
import java.awt.*;

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
    
    private int clientCount = 4;
    int sysMode = 0;

    String[] refArgs;
    String h;
    int p;
    
    public InterClient(String[] args){
        if (args.length==0){
            h = "localhost";
            p = 12345;}
        if (args.length==1){//**
            h = "localhost";
            p = 12345;}
        if (args.length==2){
            h = "localhost";
            p = Integer.parseInt(args[1]);}
        if (args.length==3){
            h = args[1];
            p = Integer.parseInt(args[2]);}
        this.liste = new List(4);
        simpleClient = new SimpleClient(h,p,liste);

        sysMode = getSysMode();
        clientCount = getClientCount();        
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

////////////////////////////////////////////////////
    public synchronized IStatus getStartupStatus(int i){
        /*Make connection
         *Send message to get status*/
        IStatus status = new Status();
        try {
            ICommand command = new Command('s', i);
            char c = command.getName();
            System.out.println("link|InterClient Status.getStartupStatus = "+ c);
            simpleClient.checkedSendToServer(command);
            status = (Status)simpleClient.getReply();                         
        }
        catch (Exception ex)
        {
          liste.add(ex.toString());
          liste.makeVisible(liste.getItemCount()-1);
          liste.setBackground(Color.yellow);
        }
        return status;
    }
    
    public synchronized IRecord getRunningRecord(int i){
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
    
    public synchronized int getSysMode() {
        /*Make connection
         *Send message to get sysMode*/
        try {
            int i = 0;
            Command command = new Command('y', i);
//            simpleClient.checkedSendToServer(command);
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
        
    public synchronized int getClientCount() {
        /*Make connection
         *Send message to get clientCount*/
        try {
            int i = 0;
            Command command = new Command('c', i);
//            simpleClient.checkedSendToServer(command);
            clientCount = 4; //Fixed count
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
    public synchronized IRecord cycleEnded(int i){
        /*Make connection
         *Send message to indicate spinner id*/
        Record record = new Record();
        try {
            Command command = new Command('e', i);
            simpleClient.checkedSendToServer(command);
            record = (Record)simpleClient.getReply();
            System.out.println("InterClient cycleEnded(): "+record+"  "+
                record.getName()+record.getId()+
                record.getPosIndex()+record.getColorIndex());            
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
    
    public synchronized String messageSent(String message){
        String reply = new String();
        try {
//            Command command = new Command('m', i);
            simpleClient.checkedSendToServer('m'+message);
            reply = (String)simpleClient.getReply();
            System.out.println("InterClient messageSent(): "+reply);
            liste.add(reply);
            liste.makeVisible(liste.getItemCount()-1);
        }
        catch (Exception ex)
        {
          liste.add(ex.toString());
          liste.makeVisible(liste.getItemCount()-1);
          liste.setBackground(Color.yellow);
        }         
        //Receive message
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
