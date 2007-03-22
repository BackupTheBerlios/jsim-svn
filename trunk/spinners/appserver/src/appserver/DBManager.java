package appserver;

import interfaces.*;
public class DBManager {
    
    Database database;
   
    public DBManager() {
        database = new Database();        
    }
    
    public void saveSetup(int i, IAppObj appObj) {
        database.setUp(i,appObj);
    }

    public IAppObj getAppObj(int i){
        return database.getAppObj(i);
    }
    public void setAppObj(int i, IAppObj val){
        database.setAppObj(i, val);
    }
    
    public IAppModel getAppModel(int i){
        return database.getAppObj(i).getAppModel();
    }    
    public void setAppModel(int i, IAppModel appModel){
        database.getAppObj(i).setAppModel(appModel);
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


}

