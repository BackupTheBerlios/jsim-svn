package appserver;

import interfaces.*;
import appdata.*;

public class AppServer implements IServer{

    private DBManager dbManager;
    IStatus aStatus;
    IRecord aRecord;
    private int clientCount;
    int sysMode;
    String[] refArgs;
    
    public AppServer(){
        //No arguments passed in when using local server
    }
    public AppServer(String[] args) {
        // There are is one argument when remote: the port number
        refArgs = new String[1];
        if (args.length == 1){
            refArgs[0] = args[0];
        }
    }

    public void initServer(int c, int m){
        //Here we set up the database, having provided the count and mode
        setClientCount(c);
        setSysMode(m);
    }
    
    public String[] getArgs(){ 
        return refArgs;
    }
    
    public DBManager getDBManager() {
        return dbManager;
    }
    public void setDBManager(DBManager val) {
        this.dbManager = val;
    }
 
    public void makeSetup() {
        dbManager = new DBManager(clientCount);
        aStatus = new Status();
        aRecord = new Record();
        System.out.println("AppServer.makeSetup().clientCount = "+clientCount);
        for (int i = 0; i < clientCount; i++) {
            //make aStatus
            aStatus.makeStatus(i, sysMode);
            //make runningRecord
            aRecord = new Record(i,0,8,9);
            dbManager.saveSetup(i, aStatus, aRecord);
        }
    }
    
    //////////////////////////////////////////////////////////////////
    // These methods are called by the client, either directly or via stubs    
    public int getClientCount() {
        return clientCount;
    }
    public void setClientCount(int val) {
        clientCount = val;
    }
    public int getSysMode() {
        return sysMode;
    }
    public void setSysMode(int val) {
        sysMode = val;
        makeSetup();
    }    
 //--------------------------------------------------------    
   public synchronized IStatus getStartupStatus(int i){
        IStatus status = new Status();
        status = dbManager.getStatus(i);
        System.out.println("AppServer getStartupStatus(): "+status);
        return status;
    }   
    public synchronized IRecord getRunningRecord(int i){
        IRecord record = new Record();
        record = dbManager.getRecord(i);
        System.out.println("AppServer getRunningRecord(): "+record);
        return record;
    }
  
    public IRecord cycleEnded(int i){
        //PRE:  aRecord from the app object
        //      This has all the data needed to define the current state of
        //      the app(i), which can be used together with Status(i)
        //      to update the record.
        //POST: A new record is returned
        // First get the record and note the mode
        // Different modes show problems of mutual exclusion etc
        Record record = new Record();
        switch(sysMode){
            // Decide whether to do anything special depending on mode
            case IStatus.NORMAL:
                record = update(i,0);
                return record;                     
            case IStatus.CONFLICT: 
                record = update(i,100);
                return record;
            case IStatus.SAFE:
                synchronized (this){
                    record = update(i,0);
                    return record;
                }
            case IStatus.PRODUCER_CONSUMER:
                synchronized (this){
                    record = update(i,500);
                    return record;
                }
            default:                 
                synchronized (this){
                    record = update(i,500);
                    return record;
                }
        }
    }
    
    private Record update(int i, int sleepTime){        
        //Now we do the job requested by the app client.
        //  called with: record.getPosIndex() == endOfCycle
        //  where endOfCycle = maxPosIndex for forward and 0 for reverse
         Record record = new Record();
         record = (Record)dbManager.getRecord(i);
        // Reset the posIndex
        record.setPosIndex(0); //depending on direction
        // increment the colour index
        record.setColorIndex(record.getColorIndex() + 1);
        if (record.getColorIndex() == aStatus.getMaxColorIndex()){ 
            //also may depend on app
            // if all colours done, start again
            record.setColorIndex(0);
            //if ((record).incCount|2 == 0){
            // (record).setDirection(true)}
            //else (record).setDirection(false);
        }
        try{
            Thread.sleep(sleepTime);
        }catch(InterruptedException e){
            System.out.println("Interrupted sleep");
        }
        dbManager.setRecord(i, record);
        System.out.println("appServer cycleEnded: "+i);
    return record;
    }    

    //Used in remote mode
    public String messageSent(String m){
        return m;
    }
    
}

///////////////////////////////////////
/*        int mode = dbManager.getStatus(id).getMode();
        if (mode != IStatus.PRODUCER_CONSUMER){// not Mode 3
            // write and update the database record
            if (mode != IStatus.SYNCHRONIZED){ // not Mode 2               
                record = accessDatabase(theManager, record);
            } else{ //or in Mode 2,
                synchronized (theManager){
                    record = accessDatabase(theManager, record);
               }
            }
        } else { // Mode == 3: Producer/Consumer share Record[0]
            switch (id){
                case 0:
                    // produce aStatus and notify observers for display
                    //  synchronized inside theManager
                          putToDatabase(theManager, record);
                    break;
                case 1:
                    // consume aStatus and notify observers for display
                    //  synchronized inside theManager
                          record = getFromDatabase(theManager, record);
            }
        }
 */


