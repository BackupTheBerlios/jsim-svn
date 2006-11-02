/*
 * SWEngApp1.java
 *      On startup, get a message from a setup client
 *      to supply:
 *
 *      No. of clients
 *      For each client
 *          Client Id
 *          Protocol for CoOpMode: e.g. Prod/Con, Conflict, mutex         
 *          Function to be displayed e.g. spinner
 *          Parameters for Drawing: Speed of operation
 *                                  Resolution/incr per step, Direction
 *                                  Delay/incr
 *                                  Sequence at end of each cycle
 *
 *      Once this is set up, then save each status to the
 *      database for each client, and let each client read
 *      its own.
 *
 *      The clients will then start and will request a Record 
 *      from SWEngApp1.  This will pass the message to the
 *      DBManager, which will pass the record back to the 
 *      client, which will give it the info needed for the
 *      next cycle.
 *          
 * Created on 30 October 2006, 21:35
 *
 * To change this template, choose Tools | Template DBManager
 * and open the template in the editor.
 */

package server;

import interfaces.*;
import java.util.List;

/**
 *
 * @author Roger
 */
public class SWEngApp1{
    int theMode;
    int clientCount;
    
    List theStatusList;
    IStatus aClientStatus;
    
    IDatabase theDatabase;
    IManager theDBManager;
    
    public SWEngApp1(int aMode){
        theMode = aMode;
        if (theMode == IStatus.PRODUCER_CONSUMER){
            clientCount = 2;
        } else clientCount = 4;
        
        theDatabase = new Database(theMode);
        theDBManager = new DBManager(theDatabase, theMode);
                
        for (int i = 0; i < clientCount; i++){
            /*      for each new status, call the DBManager to
             *      save it into the Database.
             */
            aClientStatus = getClientStatus(i);
            theDBManager.setStatus(aClientStatus, i);
            IRecord newRecord = makeRecord(aClientStatus, i);
            theDBManager.setRecord(newRecord, i);
            
//            theStatusList.add(aClientStatus); 

        }

        

    }
        
    /** Get a new Status, either from outside, or make it here
     */
    public IStatus getClientStatus(int id){
        return aClientStatus;
    }
    
    /** Use a Status to prepare a 'Run' record
     */
    IRecord makeRecord(IStatus aStatus, int i){
        IRecord newRecord;
        return newRecord;
    }
    
    /**
     * Allows the Mode to be passed to other objects
     * @return Mode
     */
    public int getMode(){
        return theMode;
    }
    
    /**
     * Allows the number of spinners to be passed to other objects
     * @return spinnerCount
     */
    public int getCount(){
        return clientCount;
    }     
}
