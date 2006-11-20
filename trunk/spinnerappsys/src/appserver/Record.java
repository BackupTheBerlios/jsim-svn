// spinners_netbeans\appsys\server\Record.java
//
// A 'record' object is maintained by each spinner/shader and
// supplies parameters defining state.
// There are no facilities for processing the Record here, only 
// set() and get().
////////////////////////////////////////////////////////////////////
package appserver;

import interfaces.IRecord;

//import interfaces.IRecord;
///////////////////////////////////////////////////////////////////////////
// Roger Prowse
// History:
//		30 Oct 2003 v3:		Moved from Spinner file.
///////////////////////////////////////////////////////////////////////////

public class Record implements IRecord{// , Cloneable{
    int id;
    int mode;
    int posIndex;
    int colorIndex;

    public Record(){
        id = 0;
        mode = 0;
        // Initialise the relative position index, direction and colour index
        // (Absolute position and size determined in Display object)
        posIndex = 0;
        colorIndex = 0;     
    }
    
    public Record(int i){
        id = i;
        mode = 0;
        // Initialise the relative position index, direction and colour index
        // (Absolute position and size determined in Display object)
        posIndex = 0;
        colorIndex = 0;        
    }
 
    public Record(int newId, int newMode, int newPosIndex, 
                        int newColorIndex){
        id = newId;
        mode = newMode;
        // Initialise the relative position, direction and colour
        // (Absolute position and size determined in Display object)
        posIndex = newPosIndex;
        colorIndex = newColorIndex;
    }
    
     public synchronized IRecord getRecord(){
        Record toRecord = new Record(0);
        toRecord.setId(id);
        toRecord.setMode(mode);
        toRecord.setPosIndex(posIndex);
        toRecord.setColorIndex(colorIndex);
        return toRecord;
     }     
    public synchronized void setRecord(IRecord fromRecord){
        id = fromRecord.getId();
        mode = fromRecord.getMode();
        posIndex = fromRecord.getPosIndex();
        colorIndex = fromRecord.getColorIndex();
    }
    public synchronized void setRecord(int i){
        id = i;
        mode = 0;
        // Initialise the relative position index, direction and colour index
        // (Absolute position and size determined in Display object)
        posIndex = 0;
        colorIndex = 0;        
    }   
    public synchronized void copyRecord(IRecord fromRecord, IRecord toRecord){
        toRecord.setId(fromRecord.getId());
        toRecord.setMode(fromRecord.getMode());
//        System.out.println("Record: copyRecord"+fromRecord.getId());
        toRecord.setPosIndex(fromRecord.getPosIndex());
        toRecord.setColorIndex(fromRecord.getColorIndex());
    }
    
    public synchronized int getId(){
        return id;
    }
    public synchronized void setId(int newId){
        id = newId;
    }
   
    public synchronized int getMode(){
        return mode;
    }
    public synchronized void setMode(int newMode){
        mode = newMode;
    }
          
    public synchronized int getPosIndex(){
        return posIndex;
    }        
    public synchronized void setPosIndex(int newPosIndex){
        posIndex = newPosIndex;
    }
    public synchronized int incPosIndex(){
        posIndex += 1;
        return posIndex;
    }        
    
    public synchronized int getColorIndex(){
        return colorIndex;
    }    
    public synchronized void setColorIndex(int newIndex){
        colorIndex = newIndex;
    }    
    public synchronized int incColorIndex(){
        colorIndex += 1;
        return colorIndex;
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


