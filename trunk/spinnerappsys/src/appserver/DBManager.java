package appserver;

import interfaces.*;
public class DBManager {
    
    Database database;
    
    public DBManager(int clientCount) {
        database = new Database(clientCount);
    }

    public IStatus getStatus(int i){
        return database.getStartupStatuses(i);
    }
    
    public void saveSetup(int i, IStatus status, IRecord record) {
        database.setStartupStatuses(i, status);
        database.setRunningRecords(i, record);
    }
 
    public IRecord getRecord(int i){
        return database.getRunningRecords(i);
    }
    public void setRecord(int i, IRecord record){
        database.setRunningRecords(i, record);
    }
    public Database getDatabase(){
        return database;
    }
    
}

