package appserver;

import interfaces.*;
public class DBManager {
    
//    OldDatabase database;
    Database database;
   
    public DBManager(int clientCount) {
        database = new Database(clientCount);        
    }
    
    public void saveSetup(int i, IAppObj appObj) {
        database.setUp(i,appObj);
    }

    public IAppModel getAppModel(int i){
        return database.getAppObj(i).getAppModel();
    }    
    public void setAppModel(int i, IAppModel appModel){
        database.getAppObj(i).setAppModel(appModel);
    }    
    
    public IAppController getAppController(int i){
        return database.getAppObj(i).getAppController();
    }    
    public void setAppController(int i, IAppController appController){
        database.getAppObj(i).setAppController(appController);
    }    
    
    public IStatus getStatus(int i){
        return database.getAppObj(i).getStatus();
    }    
    public void setStatus(int i, IStatus status){
        database.getAppObj(i).setStatus(status);
    }    
 
    public IRecord getRecord(int i){
        return database.getAppObj(i).getRecord();
    }
    public void setRecord(int i, IRecord record){
        database.getAppObj(i).setRecord(record);
    }

/*
    public Database getDatabase(){
        return database;
    }
*/    
    public IAppObj getAppObj(int i){
        return database.getAppObj(i);
    }
    public void setAppObj(int i, IAppObj val){
        database.setAppObj(i, val);
    }   
}

