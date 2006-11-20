/*
 * AppClientSys.java
 *
 * Created on 08 November 2006, 13:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package appclientsys;

import appclient.AppClient;
//import appserver.AppServer;
import interfaces.*;
import java.io.IOException;
import linkclient.AppServer;

/**
 * 
 * 
 * @author Roger
 * 
 */
public class AppClientSys {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        /////////////////////////////////
        String title = "AppClient: Spinners Concurrent Client/Server: v5 0611120.  Mode = ";
        // The system implements the 'Model/View/Controller'(MVC) pattern
        // The 'Model' objects are defined by status/records and are
        // found in the appModel. 
        AppClient appClient;
        // a) The appRunners use these objects to execute the task threads
        //              AppRunner appRunner
        // b) The appServer provides this data
        AppServer appServer;
        //      as parameters to implement the app's e.g. Spinners
        //              Spinner spinner;
        // c) The 'View/Controller' objects -- the GUI:
        ClientFrame cf;
        
        String host = "localhost";
        int port = 12345;
        if (args.length==1){//**
            host = "localhost";
            port = 12345;}
        if (args.length==2){
            host = "localhost";
            port = Integer.parseInt(args[1]);}
        if (args.length==3){
            host = args[1];
            port = Integer.parseInt(args[2]);}
        //Activate the Server and the Model i.e. the Server/Model/GUI system
        appServer = new AppServer(host, port);
        appClient = new AppClient((IServer)appServer,title);
        cf = new ClientFrame(appClient, title, (IComm)appServer);
        // and start the model        
        appClient.startClient();
    }
}

