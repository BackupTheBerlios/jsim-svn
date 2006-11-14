package server;

public class DBManager {
    
    Database database;
    
    public DBManager(int clientCount) {
        database = new Database(clientCount);
    }

    public Status getStatus(int i){
        return database.getStartupStatuses(i);
    }
    
    public void saveSetup(int i, Status status, Record record) {
        database.setStartupStatuses(i, status);
        database.setRunningRecords(i, record);
    }
 
    public Record getRecord(int i){
        return database.getRunningRecords(i);
    }
    public void setRecord(int i, Record record){
        database.setRunningRecords(i, record);
    }
    public Database getDatabase(){
        return database;
    }
    
}
