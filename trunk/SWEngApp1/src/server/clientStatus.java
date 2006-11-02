/*
 * clientStatus.java
 *
 * Created on 31 October 2006, 15:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package server;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import interfaces.IStatus;

/**
 *
 * @author Roger
 */
public class clientStatus implements IStatus{
    int theId;
    int theCoOpMode;
    int theSpeed;
    int theFunction;
    int [] theFnParameters;
    int theParamId;
    
    /**
     * Creates a new instance of clientStatus
     */
    public clientStatus() {
    theId = 0;
    theCoOpMode = 0;
    theSpeed =1;
    theFunction = SPINNER;
    theFnParameters = new int[4];
        
    }
    public void setStatus(IStatus newStatus){}
    public IStatus getStatus(){
        return this;
    }

    public void setId(int newId){}    
    public int getId(){
        return theId;
    }
    
    public void setCoOpMode(int newCoOpMode){}    
    public int getCoOpMode(){
        return theCoOpMode;
    }
        
    public void setFunction(int newFunction){}   
    public int getFunction(){
        return theFunction;
    }
    
    public void setFnParameter(int paramId, int newParameter){}  
    public int getFnParameter(int paramId){
        return theFnParameters[paramId];
    }
    
}
