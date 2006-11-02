// ***Sys\src\application\DBManager.java
// Provides linking objects among Spinners and between Spinners and their Displays
//////////////////////////////////////////////////////////////////////////
package server;

import java.awt.*;		
import interfaces.*;

///////////////////////////////////////////////////////////////////////////
// Roger Prowse
// History:
//		6 Dec 2001:	* Built objects in a hierarchy:
//		26 Sept 2002: 		Tested OK
//		27 Nov 2002 v2: 	Issued in application package
//		25 Jan 2003:		Applied to RMI, implements IStatus interface
//		30 Oct 2003 v3:		Introduce 'cloneable' to ensure clean display
///////////////////////////////////////////////////////////////////////////
/** This class manages data records in the database.
 */
public class DBManager implements IManager{   
    IDatabase refDatabase;
    int theMode;
    boolean spaceAv;

    
    public DBManager(IDatabase aDatabase, int Mode){
        refDatabase = aDatabase;
        theMode = Mode;
        spaceAv = true;      
    }
    
    public synchronized void setStatus(IStatus aStatus, int i){
        refDatabase.setStatus(aStatus, i);
    }
 
    public synchronized IStatus getStatus(int i){
        return refDatabase.getStatus(i);        

    };
      
    public synchronized void setRecord(IRecord aRecord, int i){
        refDatabase.setRecord(aRecord, i);
        //might be best to have:
        //aRecord.copyRecord(aRecord, refDatabase.theRecords[i]);
        // or
        //
        //refDatabase.theRecords[i].setRecord(aRecord.getRecord())
        //More to look at here
    }
    
    public synchronized IRecord getRecord(int i){
       return refDatabase.getRecord(i);        
    ;
    }
    
    public void writeRecord(IRecord aRecord, int i){
        // Save a record in the Data area
        aRecord.copyRecord((Record)aRecord, refDatabase.theRecords[i]);
    }//writeRecord
    
    public IRecord readRecord(){
          return refDatabase.theRecords[i].getRecord();        
    }//readRecord

    public synchronized void Put(IRecord aRecord){
        if (!spaceAv)
            try{
                System.out.println("Put:  Waiting  ");
                wait();
            }catch(InterruptedException e) {
                System.out.println("Int Exception caught ");
            }                  
        System.out.println("    Put:  continue  ");
        aRecord.copyRecord((Record)aRecord, refDatabase.theData[theManagerId]);
        spaceAv = false;
        notify();    
    }

    public synchronized IRecord Get(){
        if (spaceAv)
            try{
                System.out.println("Get:  Waiting  ");
                wait();
            }catch(InterruptedException e) {
                System.out.println("Int Exception caught ");
            }                  
        spaceAv = true;
        System.out.println("    Get:  continue  ");
        notify();
        return refDatabase.theData[theManagerId].getRecord();           
    }
    
    
    
    
    
    
    
    
    
    
    /*    
    public synchronized DBManager transferStatus(int theStep, boolean theDirection, int theIndex){
        setPosition(theStep);
        setDirection(theDirection);       
        setColor(theIndex);
        try{
            return (DBManager) super.clone();
        }catch(CloneNotSupportedException e){
            System.out.println("Cloning not allowed");
            return this;
        }
    }//transferStatus
    
    // The Producer waits for the Consumer before producing again
    public synchronized DBManager produceStatus(int theStep, boolean theDirection, int theIndex){
        //***Uses while... and notifyAll()
        while (newStatus == true) {
            try{wait();} catch(InterruptedException e){
                System.out.println("Interrupted wait");
            }
        }
        setPosition(theStep);
        setDirection(theDirection);        
        setColor(theIndex);
        newStatus = true;
        notifyAll();
        return this;
    }//produceStatus
    
    public synchronized DBManager consumeStatus(){
        while (newStatus == false) {
            try{ wait();} catch(InterruptedException e){
                System.out.println("Interrupted wait");
            }
        }
        newStatus = false;
        notifyAll();
        try{
            return (DBManager) super.clone();
        }catch(CloneNotSupportedException e){
            System.out.println("Cloning not allowed");
            return this;
        }
    }//consumeStatus
*/
 }
//////////////////////////////////////////////////////////////////////////

