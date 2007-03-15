package appdata;

import interfaces.IAppObj;
import java.util.HashMap;
import java.util.Map;
import liner.Liner;
import spinner.Spinner;
/*
 * AppFactory.java
 *
 * Created on 02 February 2007, 21:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Roger
 */
public class AppFactory {
    Map theMap;
    /**
     * Creates a new instance of AppFactory
     */
    public AppFactory() {
        theMap = new HashMap();
        theMap.put("Spinner", new Spinner());
        theMap.put("Liner", new Liner());
        // etc
    }

    public Map getTheMap() {
        return theMap;
    }
    public void setTheMap(Map val) {
        this.theMap = val;
    }
 
//Use the map to get an object based on the supplied key 
    public IAppObj getAppObj(String key){
        IAppObj selectedApp;
        selectedApp = (IAppObj)theMap.get(key);
        System.out.println("selectedApp = " + selectedApp);        
        return selectedApp;
    }
    
}
