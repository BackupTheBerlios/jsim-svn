package appserver;

import interfaces.*;
import appdata.*;

public class Database {

    private Status[] startupStatuses;
    private Record[] runningRecords;
    
    public Database(int clientCount) {
        startupStatuses = new Status[clientCount];
        runningRecords = new Record[clientCount];
        for (int i = 0; i < clientCount; i++) {
           startupStatuses[i] = new Status();
           runningRecords[i] = new Record();
//           System.out.println("DB constructor id = "+i);
        }              
    }

    public IStatus getStartupStatuses(int i) {
        IStatus status = new Status();
        status = startupStatuses[i].getStatus();
        return status;
    }
    public void setStartupStatuses(int i, IStatus val) {
        startupStatuses[i].setStatus(val);
        System.out.println("DB setStartupStatuses id = "+
                startupStatuses[i].getStatus().getId()+
                    startupStatuses[i].getIncrement());
       
    }


    
     public IRecord getRunningRecords(int i) {
        IRecord record = new Record();
        record = runningRecords[i].getRecord();
        return record;
//        return runningRecords[i];
    }
    public void setRunningRecords(int i, IRecord val) {
        runningRecords[i].setRecord(val);
    }


}

