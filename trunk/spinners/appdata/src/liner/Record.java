// spinners_netbeans\appsys\server\Record.java
//
// A 'record' object is maintained by each application and
// supplies parameters defining state.
// There are no facilities for processing the Record here, only 
// set() and get().
////////////////////////////////////////////////////////////////////
package liner;

import interfaces.IRecord;
import java.io.Serializable;

//import interfaces.IRecord;
///////////////////////////////////////////////////////////////////////////
// Roger Prowse
// History:
//		30 Oct 2003 v3:		Moved from Spinner file.
///////////////////////////////////////////////////////////////////////////

public class Record implements IRecord, Serializable{// , Cloneable{
    String name;
    String id;
    int mode;
    int posIndex;
    int frameIndex;

    public Record(){
        name = " ";
        id = " ";
        mode = 0;
        // Initialise the relative position index and colour index
        // (Absolute position and size determined in Display object
        // and other parameters, which do not change during a run, are
        // defined in Status)
        posIndex = 0;
        frameIndex = 0;
    }
    
    public Record(String name, String id, int mode, int posIndex, int frameIndex){
        this.name = name;
        this.id = id;
        this.mode = mode;
        this.posIndex = posIndex;
        this.frameIndex = frameIndex;
    }
    
     public synchronized IRecord getRecord(){
        Record toRecord = new Record();
        toRecord.setId(id);
        toRecord.setMode(mode);
        toRecord.setPosIndex(posIndex);
        toRecord.setFrameIndex(frameIndex);
        return toRecord;
     }     
    public synchronized void setRecord(IRecord fromRecord){
        id = fromRecord.getId();
        mode = fromRecord.getMode();
        posIndex = fromRecord.getPosIndex();
        frameIndex = fromRecord.getFrameIndex();
    }
     
    public String getName(){
        return name;
    }
    public void setName(String val){
        name = val;
    }
    
    public synchronized String getId(){
        return id;
    }
    public synchronized void setId(String newId){
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
    
    public synchronized int getFrameIndex(){
        return frameIndex;
    }    
    public synchronized void setFrameIndex(int newIndex){
        frameIndex = newIndex;
    }    
    public synchronized int incFrameIndex(){
        frameIndex += 1;
        return frameIndex;
    }
    
//    public String toString(){
//      String s = "Record: "/*+this+"  "*/+name+id+" "+mode+"  "+posIndex+" "+frameIndex;
//        return s;
//    }

}//Record
