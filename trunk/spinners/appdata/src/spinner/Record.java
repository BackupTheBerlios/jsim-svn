// spinners_netbeans\appsys\server\Record.java
//
// A 'record' object is maintained by each spinner/shader and
// supplies parameters defining state.
// There are no facilities for processing the Record here, only 
// set() and get().
////////////////////////////////////////////////////////////////////
package spinner;

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
    int colorIndex;

    public Record(){
        name = " ";
        id = " ";
        mode = 0;
        // Initialise the relative position index and colour index
        // (Absolute position and size determined in Display object
        // and other parameters, which do not change during a run, are
        // defined in Status)
        posIndex = 0;
        colorIndex = 0;
    }
    
    public Record(String name, String id, int mode, int posIndex, int colorIndex){
        this.name = name;
        this.id = id;
        this.mode = mode;
        this.posIndex = posIndex;
        this.colorIndex = colorIndex;
    }
    
     public synchronized IRecord getRecord(){
        Record toRecord = new Record();
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
}


