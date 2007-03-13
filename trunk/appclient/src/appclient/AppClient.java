/*
 * AppClient.java
 *
 * Created on 09 March 2007, 16:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package appclient;

import interfaces.IAppObj;
import interfaces.IClient;
import interfaces.IGUIPanel;
import interfaces.IMainForm;
import interfaces.IServer;

/**
 *
 * @author Roger
 */
public class AppClient implements IClient{
    IServer refServer;
    int clientCount;
    int sysMode;
    
    AppRunner[] appRunners;
    Thread [] runnerThreads;

    /**
     * 
     * @param Mode selects mode of interaction between Spinners
     */
    public AppClient(String[] args, IServer appServer){
        // There are at least 2 and up to 4 arguments:
        // clientCount, sysMode 
        // and others if remote which are not needed here
        // First interpret the number of clients
        try{clientCount = Integer.parseInt(args[0]);
            if(clientCount > 12) {clientCount = 4;}
        } catch(NumberFormatException e){
            System.out.println("Invalid format -- select 4");
        }
        // Now interpret the required mode
        try{sysMode = Integer.parseInt(args[1]);
            if(sysMode > 3) {sysMode = 0;}
        } catch(NumberFormatException e){
            System.out.println("Invalid format -- select 0");
        }
        refServer = appServer;
}
       
     /* 
     * Instantiate the appRunners and attach to threads
     * Link the GUI to each ViewPanel and start the threads
     */
     public void startClient(IMainForm mainForm){
        System.out.println("AppClient.startClient(): clientCount = "+clientCount);//**
        //make Threads;
        appRunners = new AppRunner[clientCount];                
        runnerThreads = new Thread[clientCount];
        for (int i = 0; i < clientCount; i++){
            appRunners[i] = new AppRunner(refServer,i,sysMode);
            //Attach appRunners[i].AppController to threads
            //and give names to help debugging
            //Use java.lang.Integer.toString(int i)
            String threadName = Integer.toString(i);
            runnerThreads[i] = 
                    new Thread((Runnable)appRunners[i].getAppController(),threadName);
            
            IGUIPanel aGUIPanel = mainForm.getGUIPanel();
            aGUIPanel.addViewPanel(i,appRunners[i]);            
            //Start the Threads: execute thread[i].run()
            System.out.println("appClient.startClient() "+threadName);
            runnerThreads[i].start();
        }        
    }
    
    /**
     * Allows the number of activities to be passed to GUI
     * 
     * @return clientCount
     */
    public int getCount(){
        return clientCount;
    }
    public void setCount(int val){
        clientCount = val;
    }
    
    /**
     * Allows the system mode to be passed to GUI
     * 
     * @return status[i]
     */
    public int getSysMode(){
        return sysMode;
    }
    public void setSysMode(int val){
        this.sysMode = val;
    }
}//AppClient
/////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
