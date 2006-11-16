package interfaces;

import java.io.Serializable;
import java.awt.*;		// for Color
    
/**
 * This interface defines operations on the state of a Model element, 
 * such as a Spinner.
 */
public interface IRecord extends Serializable{
    public IRecord getRecord();
    public void setRecord(IRecord aRecord);
    public void setRecord(int i);
    public void copyRecord(IRecord fromRecord, IRecord toRecord);
    
    public int getId();
    public void setId(int Id);
    
    public int getMode();
    public void setMode(int newMode);
    
    public int getPosIndex();
    public void setPosIndex(int newValue);
    public int incPosIndex();
    
//    public boolean getDirection();
//    public void setDirection(boolean newValue);
    
    public int getColorIndex();
    public void setColorIndex(int newIndex);
    public int incColorIndex();
    
//    public boolean getBlackOut(); 
//    public void setBlackOut(boolean newValue);
    
    
}

