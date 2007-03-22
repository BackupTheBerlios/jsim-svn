package appserver;

import appdata.AppFactory;
import interfaces.*;

public class AppServer implements IServer{

    private DBManager dbManager;
    private AppFactory theAppFactory;
    IAppObj appObj;
    IAppModel appModel;
    IAppController appController;
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
        System.out.println("AppServer.initServer(): ");
        makeDatabase();
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
    

    public IAppObj selectApp(int i, String name) {
    ///////////////////
    //we use a map factory comprising <name/object> pairs:
    //Using name as key[i]
    //we instantiate the appropriate IAppController activity object[i]
    //specified in theAppFactory map.
    //For every activity we will add it into database[i] and refer to it
    //in the corresponding database[i].status.  Then we pass it to
    //theAppRunner[i], and start the thread. 
        theAppFactory = new AppFactory(); 
        appObj = theAppFactory.getAppObj(name);
        return appObj;
    }
 
    public void makeDatabase() {
        dbManager = new DBManager();
        String name;
        IAppObj appObj;
//        IStatus status;
        int increment = 6;
        int delay = 1;
        for (int i = 0; i < clientCount; i++) {
            int coOpMode = IStatus.NORMAL;
            switch (i){
                case 0: {
                    increment = 3;
                    delay = 1;
                    //Status(name,id,IStatus.FUNCTION,mode,IStatus.COOPMODE,increment,
                    //      direction,delay,delayFactor,maxPosIndex,maxColorIndex,blackout);
                    aStatus = new spinner.Status("Spinner","0",IStatus.SPINNER,
                            sysMode,coOpMode,increment,true,delay,5,360/increment,14,false);
                    appObj = selectApp(i, aStatus.getName());
                    appObj.setStatus(aStatus);
                    appObj.makeApp(aStatus);
                }break;
                case 2: {
                    increment = 6;
                    delay = 3;
                    aStatus = new spinner.Status("Spinner","1",IStatus.SPINNER,
                            sysMode,coOpMode,increment,true,delay,5,360/increment,14,false);
                    appObj = selectApp(i, aStatus.getName());
                    appObj.setStatus(aStatus);
                    appObj.makeApp(aStatus);
                }break;
                case 4: {
                    increment = 6;
                    delay = 9;
                    aStatus = new spinner.Status("Spinner","2",IStatus.SPINNER,
                            sysMode,coOpMode,increment,true,delay,5,360/increment,14,false);
                    appObj = selectApp(i, aStatus.getName());                    
                    appObj.setStatus(aStatus);
                    appObj.makeApp(aStatus);
                }break;
                case 6: {
                    increment = 6;
                    delay = 27;                    
                    aStatus = new spinner.Status("Spinner","3",IStatus.SPINNER,
                            sysMode,coOpMode,increment,true,delay,5,360/increment,14,false);
                    appObj = selectApp(i, aStatus.getName());                    
                    appObj.setStatus(aStatus);
                    appObj.makeApp(aStatus);
                }break;
                case 1: {
                    name = "Liner";
                    increment = 6;
                    delay = 1;
                    aStatus = new liner.Status("Liner","0",'a',IStatus.LINER,
                            sysMode,coOpMode,increment,true,delay,5,200,14,false);
                    appObj = selectApp(i, aStatus.getName());
                    appObj.setStatus(aStatus);
                    appObj.makeApp(aStatus);
                }break;
                case 3: {
                    increment = 6;
                    delay = 3;
                    aStatus = new liner.Status("Liner","1",'b',IStatus.LINER,
                            sysMode,coOpMode,increment,true,delay,5,100,14,false);
                    appObj = selectApp(i, aStatus.getName());                    
                    appObj.setStatus(aStatus);
                    appObj.makeApp(aStatus);
                }break;
                case 5: {
                    increment = 6;
                    delay = 9;
                    aStatus = new liner.Status("Liner","2",'c',IStatus.LINER,
                            sysMode,coOpMode,increment,true,delay,5,100,14,false);
                    appObj = selectApp(i, aStatus.getName());                    
                    appObj.setStatus(aStatus);
                    appObj.makeApp(aStatus);
                }break;
                case 7: {
                    increment = 6;
                    delay = 27;
                    aStatus = new liner.Status("Liner","3",'d',IStatus.LINER,
                            sysMode,coOpMode,increment,true,delay,5,100,14,false);
                    appObj = selectApp(i, aStatus.getName());                    
                    appObj.setStatus(aStatus);
                    appObj.makeApp(aStatus);
                }break;
                default:{
                    increment = 6;
                    delay = 1;
                    aStatus = new liner.Status("Liner","n",'e',IStatus.LINER,
                            sysMode,coOpMode,increment,true,delay,5,100,14,false);
                    appObj = selectApp(i, aStatus.getName());                    
                    appObj.setStatus(aStatus);
                    appObj.makeApp(aStatus);
                }
            }  
            System.out.println("AppServer.makeDatabase(): "+i+" "+appObj);
            dbManager.saveSetup(i,appObj);
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
    }
    //?synchronized?
    public IAppObj getAppObj(int i) {
        return dbManager.getAppObj(i);
    }
    
    public IRecord cycleEnded(int i){
        //Now we do the job requested by the appController.
        //PRE:  index 'i' from the appController to locate appObj[i] in the
        //      database.  appObj[i].record has all the data needed to define
        //      the current state of app(i), which can be used together 
        //      with Status(i) to update the record.
        //POST: A new record is returned
        // First note the sysMode
        // Different modes show problems of mutual exclusion etc
        // Non-zero sleep times before starting a new cycle help to
        // ensure that clashes will occur if the system is unsafe.
        int sleepTime = 0;
        IAppModel appModel;
//        IRecord record;
        switch(sysMode){
            // Decide whether to do anything special depending on mode
            case IStatus.NORMAL:{
                sleepTime = 0;
                appModel = dbManager.getAppModel(i);
                aRecord = appModel.update(dbManager.getRecord(i));
                dbManager.setRecord(i, aRecord);
                return aRecord;
            }//break;    
            case IStatus.CONFLICT:{
                sleepTime = 10;
                appModel = dbManager.getAppModel(i);
                aRecord = appModel.update(dbManager.getRecord(i)); 
                    try{
                        Thread.sleep(sleepTime);
                    }catch(InterruptedException e){
                        System.out.println("Interrupted sleep");
                    }
                dbManager.setRecord(i, aRecord);
                return aRecord;
            }// break;
            case IStatus.SAFE:{
                synchronized (this){
                    sleepTime = 20;
                    appModel = dbManager.getAppModel(i);
                    aRecord = appModel.update(dbManager.getRecord(i));                                       
                        try{
                            Thread.sleep(sleepTime);
                        }catch(InterruptedException e){
                            System.out.println("Interrupted sleep");
                        }
                    dbManager.setRecord(i, aRecord);
                    return aRecord;
                }
            }// break;
            case IStatus.PRODUCER_CONSUMER:{
                synchronized (this){
                    sleepTime = 0;
                    appModel = dbManager.getAppModel(i);
                    aRecord = appModel.update(dbManager.getRecord(i));                                       
                    dbManager.setRecord(i, aRecord);
                }
            } break;
            default:                 
                synchronized (this){
                    sleepTime = 0;
                    appModel = dbManager.getAppModel(i);
                    aRecord = appModel.update(dbManager.getRecord(i));                                       
                    dbManager.setRecord(i, aRecord);
                }
        }
        System.out.println("appServer cycleEnded: "+i+" "+aRecord+" dbMan: "+dbManager.getRecord(i));
    return aRecord;            
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


