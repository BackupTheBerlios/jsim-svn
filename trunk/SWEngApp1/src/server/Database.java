/*
 * Database.java
 *
 * Created on 11 September 2006, 17:00
 */

package server;

import interfaces.*;

/**
 *  This Database provides for Data records and Managers.
 *  Manager can be selected by a client, such as a Spinner.
 *  The Managers are responsible for accessing Records.
 * @author Roger
 * @version
 */
public class Database implements IDatabase{
    int Mode;
    int spinnerCount;
    
    Manager[] theManagers;
    Record[] theData;
    
    boolean spaceAv;

    public Database(int aMode){
        Mode = aMode;
        if (Mode == IDatabase.PRODUCER_CONSUMER){
            spinnerCount = 2;
        } else spinnerCount = 4;       
        theManagers = new Manager[spinnerCount];
        theData = new Record[spinnerCount];
        for (int i = 0; i < spinnerCount; i++){          
            theManagers[i] = new Manager(this, i, this.Mode); 
            theData[i] = new Record(i); 
        }
        spaceAv = true;
    }
        
    public IManager getManager(int Id){
        return theManagers[Id];
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

