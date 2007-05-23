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

    public String getName();
    public void setName(String name);
    
    public String getId();
    public void setId(String id);
    
    public int getMode();
    public void setMode(int mode);
    
    public int getPosIndex();
    public void setPosIndex(int newValue);
    public int incPosIndex();
    
//    public boolean getDirection();
//    public void setDirection(boolean newValue);
    
    public int getFrameIndex();
    public void setFrameIndex(int newIndex);
    public int incFrameIndex();
    
//    public boolean getBlackOut(); 
//    public void setBlackOut(boolean newValue);
    
}


