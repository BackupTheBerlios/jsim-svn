// spinners_netbeans\spinner_parts\status\Record.java
//
// A 'record' object is maintained by each spinner/shader and
// supplies parameters defining state.
// There are no facilities for processing the Record here, only 
// set() and get().
////////////////////////////////////////////////////////////////////
package server;

import interfaces.IRecord;
import java.awt.Color;

///////////////////////////////////////////////////////////////////////////
// Roger Prowse
// History:
//		30 Oct 2003 v3:		Moved from Spinner file.
///////////////////////////////////////////////////////////////////////////

public class Record implements IRecord, Cloneable{
    int theRecordId;
    int theMode;
    int thePosIndex;
    boolean theDirection;
    int theColorIndex;
    boolean theBlackOut;

    public Record(int newId){
        theRecordId = newId;
        theMode = 0;
        // Initialise the relative position index, direction and colour index
        // (Absolute position and size determined in Display object)
        thePosIndex = 0;
        theDirection = true;	//true --> anticlockwise/up
        theColorIndex = 0;
        theBlackOut = false;    //false --> no blackOut
        
        }
 
    public Record(int newId, int newMode, int newPosIndex, 
            boolean newDirection, int newColorIndex, boolean newBlackOut){
        theRecordId = newId;
        theMode = newMode;
        // Initialise the relative position, direction and colour
        // (Absolute position and size determined in Display object)
        thePosIndex = newPosIndex;
        theDirection = newDirection;	//true --> anticlockwise/up
        theColorIndex = newColorIndex;
        theBlackOut = newBlackOut;
    }
    
    public synchronized void setId(int newId){
        theRecordId = newId;
    }
    
    public synchronized int getId(){
        return theRecordId;
    }
    
    public synchronized void setMode(int newMode){
        theMode = newMode;
    }
    
    public synchronized int getMode(){
        return theMode;
    }
    
    public synchronized void setRecord(IRecord fromRecord){
        theRecordId = fromRecord.getId();
        theMode = fromRecord.getMode();
        thePosIndex = fromRecord.getPosIndex();
        theDirection = fromRecord.getDirection();
        theColorIndex = fromRecord.getColorIndex();
        theBlackOut = fromRecord.getBlackOut();
    }
    
     public synchronized Record getRecord(){
        Record toRecord = new Record(0);
        toRecord.setId(theRecordId);
        toRecord.setMode(theMode);
        toRecord.setPosIndex(thePosIndex);
        toRecord.setDirection(theDirection);
        toRecord.setColorIndex(theColorIndex);
        toRecord.setBlackOut(theBlackOut);
        return toRecord;
     }
     
    public synchronized void copyRecord(IRecord fromRecord, IRecord toRecord){
        toRecord.setId(fromRecord.getId());
        toRecord.setMode(fromRecord.getMode());
//        System.out.println("Record: copyRecord"+fromRecord.getId());
        toRecord.setPosIndex(fromRecord.getPosIndex());
        toRecord.setDirection(fromRecord.getDirection());
        toRecord.setColorIndex(fromRecord.getColorIndex());
        toRecord.setBlackOut(fromRecord.getBlackOut());        
    }
      
    public synchronized void setPosIndex(int newPosIndex){
        thePosIndex = newPosIndex;
    }
    
    public synchronized int getPosIndex(){
        return thePosIndex;
    }
    
    public synchronized void setDirection(boolean newDirection){
        theDirection = newDirection;
    }
    
    public synchronized boolean getDirection(){
        return theDirection;
    }
    
    public synchronized void setColorIndex(int newIndex){
        theColorIndex = newIndex;
    }
    
    public synchronized int getColorIndex(){
        return theColorIndex;
    }
    
    public synchronized int incColorIndex(){
        theColorIndex += 1;
        return theColorIndex;
    }
    
    public synchronized void setBlackOut(boolean newBlackOut){
        theBlackOut = newBlackOut;
    }
    
    public synchronized boolean getBlackOut(){
        return theBlackOut;
    }

}//Record
/*      ///////////////////////////////////////////////////////////////////////
 * Note on Cloning:
        // NB: The following technique returns a clone of the stored record,
        // since the original record may be changed by any other process sharing
        // the record memory, before the process actually gets round to sending
        // it to the Observer.
        // This applies to mode 2, and to the Consumer in mode 3 (but not to the
        // Producer, as it is the only one to change the shared record).
        ///////////////////////////////////////////////////////////////////////
 *      public synchronized Record getRecord(){
            try{
                return (Record) super.clone();
            }catch(CloneNotSupportedException e){
                System.out.println("Cloning not allowed");
                return this;
            }
        {
 */

