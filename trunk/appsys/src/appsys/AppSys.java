/*
 * AppSys.java
 *
 * Created on 08 November 2006, 13:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package appsys;

import appmodel.*;
import java.io.IOException;
import server.*;

/**
 * 
 * 
 * @author Roger
 * 
 */
public class AppSys {

        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        /////////////////////////////////
        String title = "AppSys: Spinners Concurrent Threads: v4 061111  Mode = ";
        // The system implements the 'Model/View/Controller'(MVC) pattern
        // The 'MVC' objects are defined by status/records and are
        // found in the appModel. 
        AppModel appModel;
        // a) The 'Model' objects are found in the 'appRunner' threads
        //              AppRunner appRunner
        //      and use the appServer 
        AppServer appServer;
        //      to get parameters to implement the app's e.g. Spinners
        //              Spinner spinner;
        // b) The 'View/Controller' objects -- the Graphical User Interface (GUI):
        //              MainForm mainForm;
        
        // Now interpret the required mode
        int sysMode;
        try{sysMode = Integer.parseInt(args[0]);
        if(sysMode > 3) {sysMode = 0;}
        } catch(NumberFormatException e){
            System.out.println("Invalid format -- select 0");
            sysMode = 0;}
        //Activate the Server and the Model i.e. the App/Model/GUI system
        appServer = new AppServer(sysMode);
        appModel = new AppModel(appServer, title, sysMode);
        // and start the model        
        appModel.startModel();
    }
}
