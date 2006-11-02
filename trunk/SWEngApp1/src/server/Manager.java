// ***Sys\src\application\Manager.java
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
public class Manager implements IManager{   
    Database refDatabase;
    int theManagerId;
    int theMode;
    boolean spaceAv;

    
    public Manager(Database parent, int Id, int Mode){
        refDatabase = parent;
        theManagerId = Id;
        theMode = Mode;
        spaceAv = true;      
    }
       
    public synchronized void setId(int Id){
        theManagerId = Id;
    }
    
    public synchronized int getId(){
        return theManagerId;
    }

    public void writeRecord(IRecord aRecord){
        // Save a record in the Data area
        aRecord.copyRecord((Record)aRecord, refDatabase.theData[theManagerId]);
    }//writeRecord
    
    public IRecord readRecord(){
          return refDatabase.theData[theManagerId].getRecord();        
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
    public synchronized Manager transferStatus(int theStep, boolean theDirection, int theIndex){
        setPosition(theStep);
        setDirection(theDirection);       
        setColor(theIndex);
        try{
            return (Manager) super.clone();
        }catch(CloneNotSupportedException e){
            System.out.println("Cloning not allowed");
            return this;
        }
    }//transferStatus
    
    // The Producer waits for the Consumer before producing again
    public synchronized Manager produceStatus(int theStep, boolean theDirection, int theIndex){
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
    
    public synchronized Manager consumeStatus(){
        while (newStatus == false) {
            try{ wait();} catch(InterruptedException e){
                System.out.println("Interrupted wait");
            }
        }
        newStatus = false;
        notifyAll();
        try{
            return (Manager) super.clone();
        }catch(CloneNotSupportedException e){
            System.out.println("Cloning not allowed");
            return this;
        }
    }//consumeStatus
*/
 }
//////////////////////////////////////////////////////////////////////////

