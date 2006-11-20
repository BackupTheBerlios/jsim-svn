/*
 * appsys.java
 *
 * Created on 08 November 2006, 13:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package appsys;

import appclient.*;
import interfaces.*;
import java.io.IOException;
import appserver.*;

/**
 * 
 * 
 * @author Roger
 * 
 */
public class appsys {      
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        /////////////////////////////////
        String title = "AppSys: Spinners Concurrent Threads: v4 061111  Mode = ";
        // The system implements the 'Model/View/Controller'(MVC) pattern
        // The 'MVC' objects are defined by status/records and are
        // found in the appModel. 
        IClient appClient;
        // a) The 'Model' objects are found in the 'appRunner' threads
        //              AppRunner appRunner
        //      and use the appServer 
        AppServer appServer;
        //      to get parameters to implement the app's e.g. Spinners
        //              Spinner spinner;
        // b) The 'View/Controller' objects -- the Graphical User Interface (GUI):
        MainForm mainForm;
        //Activate the Server and the Model i.e. the App/Model/GUI system
        appServer = new AppServer(args);
        appClient = new AppClient((IServer)appServer,title);
        mainForm = new MainForm(appClient, title);
        // and start the model        
        appClient.startClient();

    }
}
