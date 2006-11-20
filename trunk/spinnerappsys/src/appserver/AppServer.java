package appserver;

import interfaces.*;

public class AppServer implements IServer{

    private DBManager dbManager;
    IStatus aStatus;
    IRecord aRecord;
    private int clientCount = 4;
    int sysMode;
    
    public AppServer(String[] args) {
        dbManager = new DBManager(clientCount);
        aStatus = new Status();
        aRecord = new Record();
        // Now interpret the required mode
        try{sysMode = Integer.parseInt(args[0]);
            if(sysMode > 3) {sysMode = 0;}
        } catch(NumberFormatException e){
            System.out.println("Invalid format -- select 0");
            sysMode = 0;
        }
        for (int i = 0; i < clientCount; i++) {
            makeSetup(i);
            dbManager.saveSetup(i, aStatus, aRecord);
        }              
    }
    
    public void makeSetup(int i) {
        //make aStatus
        aStatus.makeStatus(i, sysMode);
        //make runningRecord
        aRecord.setRecord(i);
    }
    
    public DBManager getDBManager() {
        return dbManager;
    }
    public void setDBManager(DBManager val) {
        this.dbManager = val;
    }
    
    //////////////////////////////////////////////////////////////////
    // These methods are called by the client, either directly or via stubs
    
    public IStatus getStartupStatus(int i){
        IStatus status = new Status();
        status = dbManager.getStatus(i);
        System.out.println("appServer getStartupStatus: "+status.getId());
        return status;
    }   
    public IRecord getRunningRecord(int i){
        return dbManager.getRecord(i);
    }        
    public int getSysMode() {
        return sysMode;
    }    
    public int getClientCount() {
        return clientCount;
    }
   
    public IRecord cycleEnded(int i){
        //PRE:  aRecord from the app object
        //      This has all the data needed to define the current state of
        //      the app(i), which can be used together with Status(i)
        //      to update the record.
        //POST: A new record is returned
        // First get the record and note the mode
        // Different modes show problems of mutual exclusion etc
        switch(sysMode){
            // Decide whether to do anything special depending on mode
            case IStatus.NORMAL:
                update(i, 0);
                return aRecord;                     
            case IStatus.CONFLICT: 
                update(i, 500);
                return aRecord;
            case IStatus.SYNCHRONIZED:
                synchronized (this){
                    update(i,50);
                    return aRecord;
                }
            case IStatus.PRODUCER_CONSUMER:
                synchronized (this){
                    update(i,500);
                    return aRecord;
                }
            default:                 
                synchronized (this){
                    update(i,500);
                    return aRecord;
                }
        }
    }
    
    private void update(int i, int sleepTime){        
        //Now we do the job requested by the app client.
        //  called with: record.getPosIndex() == endOfCycle
        //  where endOfCycle = maxPosIndex for forward and 0 for reverse
         aRecord = dbManager.getRecord(i);
        //  Reset the posIndex
        aRecord.setPosIndex(0); //depending on direction
        // increment the colour index
        aRecord.incColorIndex();
        if (aRecord.getColorIndex() == aStatus.getMaxColorIndex()){ 
            //also may depend on app
            // if all colours done, start again
            aRecord.setColorIndex(0);
            //if ((record).incCount|2 == 0){
            // (record).setDirection(true)}
            //else (record).setDirection(false);
        }
        try{
            Thread.sleep(sleepTime);
        }catch(InterruptedException e){
            System.out.println("Interrupted sleep");
        }
        dbManager.setRecord(i, aRecord);
        System.out.println("appServer cycleEnded: "+i);
    }
    
//--------------------------------------------------------    
    public void setSysMode(int val) {
        this.sysMode = val;
    }
    public void setClientCount(int val) {
        this.clientCount = val;
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


