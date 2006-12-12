/*
 * AppServerSys.java
 *
 * Created on 08 November 2006, 13:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package appserversys;

import appserver.AppServer;
import interfaces.*;
import java.io.IOException;
import linkserver.InterServer;

/**
 * 
 * 
 * @author Roger
 * 
 */
public class AppServerSys {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        /////////////////////////////////
        String title1 = "AppServer: Mode = ";
        String title2 = "Spinners Concurrent Client/Server: v5 0611120.  ";
        //The Client stub
        InterServer interServer;
        // The system implements the 'Server' pattern
        // b) The appServer provides the data for the client
        AppServer appServer;
        //      as parameters to implement the app's e.g. Spinners
        //              Spinner spinner;
        // c) The 'View/Controller' objects -- the GUI for local server control:
        ServerFrame sf;
        
        //Activate the Server
        appServer = new AppServer(args);
        interServer = new InterServer(appServer);
        sf = new ServerFrame(interServer,appServer,title1,title2);

    }
}

