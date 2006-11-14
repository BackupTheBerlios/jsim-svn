package server;

import interfaces.*;

public class AppServer{

    private DBManager dbManager;
    Status aStatus;
    Record aRecord;
    private int clientCount = 4;
    
    public AppServer() {
        dbManager = new DBManager(clientCount);
        aStatus = new Status();
        aRecord = new Record();
        for (int i = 0; i < clientCount; i++) {
            makeSetup(i);
            dbManager.saveSetup(i, aStatus, aRecord);
        }              
    }
    
    public Status getStartupStatus(int i){
        Status status = new Status();
        status = dbManager.getStatus(i);
        System.out.println("appServer getStartupStatus: "+status.getId());
        return status;
    }
    
    public Record getRunningRecord(int i){
        return dbManager.getRecord(i);
    }
        
    public void makeSetup(int i) {
        //make aStatus
        aStatus.makeStatus(i);
        //make runningRecord
        aRecord.setRecord(i);
    }

    public int getClientCount() {
        return clientCount;
    }

    public void setClientCount(int val) {
        this.clientCount = val;
    }

    public DBManager getDBManager() {
        return dbManager;
    }

    public void setDBManager(DBManager val) {
        this.dbManager = val;
    }

    
    ///////////////////////////////////////////////////////////////////////
    // Here is the 'Business Model':AppModel, which controls the parameters
    // for a pattern such as a Spinner.
        /** Write(), then Read()

    IRecord accessDatabase(DBManager dbManager, IRecord aRecord){
        dbManager.writeRecord(aRecord);
        return dbManager.readRecord();
    }

    void putToDatabase(DBManager dbManager, IRecord aRecord){               
        dbManager.Put(aRecord);
    }

    IRecord getFromDatabase(DBManager dbManager, IRecord aRecord){
        aRecord = dbManager.Get();
        return aRecord;
    }
    */
    ///////////////////////////////////////
    public Record cycleEnded(int i){
        //PRE:  aRecord from the app object
        //      This has all the data needed to define the current state of
        //      the app(i), which can be used together with Status(i)
        //      to update the record.
        //POST: A new record is returned
        // First get the record and note the mode
//        aRecord = getRunningRecord(i);
        aRecord = dbManager.getRecord(i);
       //Now we do the job requested by the app client.
        //  called with: record.getPosIndex() == endOfCycle
        //  where endOfCycle = maxPosIndex for forward and 0 for reverse
        //  That is, we have completed one cycle.
        //  Reset the posIndex
        aRecord.setPosIndex(0); //depending on direction
        // increment the colour index
        aRecord.incColorIndex();
        if (aRecord.getColorIndex() == 14){ //also may depend on app
            // if all colours done, start again
            aRecord.setColorIndex(0);
            //if ((record).incCount|2 == 0){
            // (record).setDirection(true)}
            //else (record).setDirection(false);
        }
        dbManager.setRecord(i, aRecord);
        // Different modes show problems of mutual exclusion etc
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
        System.out.println("appServer cycleEnded: "+i);
        return aRecord;
    }


}
