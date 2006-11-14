package server;

import interfaces.IStatus;

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

    public void setStartupStatuses(int i, Status val) {
        startupStatuses[i].setStatus(val);
        System.out.println("DB setStartupStatuses id = "+startupStatuses[i].getStatus().getId()+startupStatuses[i].id);
       
    }

    public Status getStartupStatuses(int i) {
        Status status = new Status();
        status = startupStatuses[i].getStatus();
        System.out.println("Database getStartupStatuses id = "+status.getId()+ startupStatuses[i].id);
        return status;

//        return startupStatuses[i];
    }
    
     public Record getRunningRecords(int i) {
        return runningRecords[i];
    }
    public void setRunningRecords(int i, Record val) {
        runningRecords[i].setRecord(val);
    }


}
