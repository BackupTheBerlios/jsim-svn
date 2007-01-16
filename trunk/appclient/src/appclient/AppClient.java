// netbeans\spinners\thread_spinners\src\thread_spinners\AppClient.java

package appclient;

import interfaces.*;
import java.awt.event.ActionListener;
import java.util.*; 	// for Date and Observable/Observer

/*
 *  A 'AppClient' comprising four (pseudo-)concurrent spinner threads.
 *
 *  Each runs under control of an independent button controlled 'switch'. 
 *
 *  Each Spinner[i] records its current state in a AppClient.Manager[i] object. 
 *  The AppClient's 'Mode' is set by entering the Mode number
 *  on start up in the:
 *          'Run Java application' window,
 *              and determines how statuses will be updated:<p>
 * 	********Mode 0: All Spinner statuses are independent	**************
 * 
 * 	********Mode 1: There is just one shared Manager		**************
 *                          Spinners record State in the same State class
 *                          but Write/Read access to the class is not atomic,
 *                          so there is no mutual exclusion, and spinners
 *                          interact in unpredictable ways.
 * 
 *      ********Mode 2: There is just one shared Manager		**************
 *                          but Write/Read access is atomic by using a single
 *                          synchronized method transferRecord(), so
 *                          spinners wait for the Manager object to become free
 *                          and thus achieve mutual exclusion.
 * 
 *      ********Mode 3: Spinners[0] & [1] share Manager		**************
 *                          in a Producer/Consumer pattern.
 *                          Spinner[0] produces the state, and Spinner[1]
 *                          consumes it.  Each Spinner depends on the other
 *                          for alternate access to the Manager object.
 * 
 *  History:
 *               6 Dec 2001:            Built objects in a hierarchy
 *              26 Sept 2002: 		Tested OK                   
 * 		27 Nov 2002 v2: 	Issued in application package
 * 		 8 Dec 2002 v2.1:	Introduced Modes 0-3.
 * 		30 Oct 2003 v3:		Uses Record/Manager in status
 *
 * @author Roger Prowse
 * @version "%I%, %G%"
 */
/**
 * 
 * A 'AppClient' comprising four (pseudo-)concurrent spinner threads.<p>
 * 
 * Each runs under control of an independent button controlled 'switch'.<p>
 * Each Spinner[i] records its current state in a AppClient.Manager[i] object.<p>
 * 
 * <textarea name="" rows="4" cols="80">
 * The AppClient's 'Mode' is set by entering the Mode number on start up in the:
 *        'Run Java application' window,
 *            and determines how statuses will be updated:            
 * </textarea>                  
 *        <ul>
 *            <li>Mode 0: All Spinner statuses are independent</li><p>     
 *            <li>Mode 1: There is just one shared Manager</li><p>
 *            <textarea name="" rows="4" cols="70">
 *                Spinners record State in the same State class
 *                but Write/Read access to the class is not atomic, 
 *                so there is no mutual exclusion, and spinners interact 
 *                in unpredictable ways.            
 *            </textarea>          
 *            <li>Mode 2: There is just one shared Manager</li><p>
 *            <textarea name="" rows="4" cols="70">
 *                but Write/Read access is atomic by using a single
 *                synchronized method transferRecord(), so
 *                spinners wait for the Manager object to become free
 *                and thus achieve mutual exclusion.
 *            </textarea>
 *            <li>Mode 3: Spinners[0] & [1] share Manager</li><p>
 *            <textarea name="" rows="4" cols="70">
 *                in a Producer/Consumer pattern.
 *                Spinner[0] produces the state, and Spinner[1]
 *                consumes it.  Each Spinner depends on the other
 *                for alternate access to the Manager object.
 *            </textarea>                
 *        </ul>
 * 
 * History:<p>
 * 6 Dec 2001:             Built objects in a hierarchy<p>
 * 26 Sept 2002: 		Tested OK  <p>
 * 27 Nov 2002 v2: 	Issued in application package<p>
 * 8 Dec 2002 v2.1:	Introduced Modes 0-3.<p>
 * 30 Oct 2003 v3:		Uses Record/Manager in status<p>
 * 
 * 
 * 
 * 
 * 
 * @author Roger Prowse
 * @version "%I%, %G%"
 */
public class AppClient implements IClient{
//    String[] refArgs;
    IServer refServer;
    int clientCount;
    int sysMode;
    
    AppRunner[] appRunners;
    Thread [] runnerThreads;

    /**
     * Instantiate the appRunners and attach to threads
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
    
    public void initSys(){
            //server is local -- set it up at once
            refServer.initServer(clientCount, sysMode);
        }
   
     /* 
     * Then set up the threads.
     */
    public void initClient(){        
        System.out.println("AppClient.initClient(): clientCount = "+clientCount);//**
        makeThreads();        
    }
    
    public void makeThreads(){
        appRunners = new AppRunner[clientCount];        
        runnerThreads = new Thread[clientCount];
        for (int i = 0; i < clientCount; i++){
            appRunners[i] = new AppRunner(refServer,i);            
            //Attach runner to threads and give names to help debugging
            String name = i+"";
            runnerThreads[i] = new Thread(appRunners[i],name);
            System.out.println("appClient.madeThreads() "+i);
        }       
    }
    
    public void startClient(){
        for (int i = 0; i < clientCount; i++){
            //Start the Threads: execute thread[i].run()
            System.out.println("appClient.startClient() "+i);
            runnerThreads[i].start();
        }        
//        for (int i = 0; i < clientCount; i++){
//            appRunners[i].initRunner(i);
//        }
    }
    
    public AppClient getClient(){
        return this;
    }
    
    /**
     * The AppClient knows what it wants to listen to -- the
     *      Buttons do not, so we run 'getListener()' to identify the Listener to 
     *      be added to the GUI Button event sources. (This is done here so that
     *      we are certain that the mainForm has already been instantiated in the
     *      constructor.
     */
    public ActionListener getListener(int i){
        return appRunners[i].getListener();
    }

    /**
     * Identifies appRunners[i].theSpinner.stepper as an observable object 
     * @param i = spinner Id
     * @return reference to stepper[i]
     */
    public Observable getObservable(int i){        
        return appRunners[i].getObservable();
    }

    /**
     * Allows the number of spinners to be passed to GUI
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

    public IServer getRefServer() {
        return refServer;
    }

    public void setRefServer(IServer val) {
        this.refServer = val;
    }

    public int getClientCount() {
        return clientCount;
    }

    public void setClientCount(int val) {
        this.clientCount = val;
    }

    public AppRunner[] getAppRunners() {
        return appRunners;
    }

    public void setAppRunners(AppRunner[] val) {
        this.appRunners = val;
    }


}//AppClient
/////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////


