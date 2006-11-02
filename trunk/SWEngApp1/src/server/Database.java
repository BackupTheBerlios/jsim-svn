/*
 * Database.java
 *
 * Created on 11 September 2006, 17:00
 */

package server;

import interfaces.*;

/**
 *  This Database provides for Data records and Managers.
 *  DBManager can be selected by a client, such as a Spinner.
 *  The Managers are responsible for accessing Records.
 * 
 * @author Roger
 * @version 
 */
public class Database implements IDatabase{
    int Mode;
    int spinnerCount;
    
    IStatus[] theStatuses;
    IRecord[] theRecords;
    
    boolean spaceAv;

    public Database(int aMode){
        Mode = aMode;
        if (Mode == IStatus.PRODUCER_CONSUMER){
            spinnerCount = 2;
        } else spinnerCount = 4;       
        theStatuses = new ClientStatus[spinnerCount];
        theRecords = new Record[spinnerCount];
        for (int i = 0; i < spinnerCount; i++){          
            theStatuses[i] = new ClientStatus(); 
            theRecords[i] = new Record(i); 
        }
        spaceAv = true;
    }
        
    /**
     */
    public void setStatus(IStatus aStatus, int i){
        theStatuses[i] = aStatus;
    }
    
    public IStatus getStatus(int i){
        return theStatuses[i];
    }
    
    /**
     */
    public void setRecord(IRecord aRecord, int i){
        theRecords[i] = aRecord;
    }
    
    public IRecord getRecord(int i){
        return theRecords[i];
    }


    /**
     * Allows the Mode to be passed to other objects
     * @return Mode
     */
    public int getMode(){
        return Mode;
    }
    
    /**
     * Allows the number of spinners to be passed to other objects
     * @return spinnerCount
     */
    public int getCount(){
        return spinnerCount;
    }     
}

