package appserver;

import interfaces.*;

public class Database {

    private Status[] startupStatuses;
    private Record[] runningRecords;
    
    public Database(int clientCount) {
        startupStatuses = new Status[clientCount];
        runningRecords = new Record[clientCount];
        for (int i = 0; i < clientCount; i++) {
           startupStatuses[i] = new Status();
           runningRecords[i] = new Record();
           System.out.println("DB constructor id = "+i);
        }              
    }

    public void setStartupStatuses(int i, IStatus val) {
        startupStatuses[i].setStatus(val);
        System.out.println("DB setStartupStatuses id = "+startupStatuses[i].getStatus().getId());
       
    }

    public IStatus getStartupStatuses(int i) {
        IStatus status = new Status();
        status = startupStatuses[i].getStatus();
        System.out.println("Database getStartupStatuses id = "+status.getId());
        return status;

//        return startupStatuses[i];
    }
    
     public IRecord getRunningRecords(int i) {
        return runningRecords[i];
    }
    public void setRunningRecords(int i, IRecord val) {
        runningRecords[i].setRecord(val);
    }


}

