// netbeans\spinners\thread_spinners\src\thread_spinners\AppModel.java

package appmodel;

// use Matisse gui
import appmatissegui.*;
import appgui.displayCanvas;
// use original gui
//import appgui.*;
import java.util.*; 	// for Date and Observable/Observer
import server.*;

/*
 *  A 'AppModel' comprising four (pseudo-)concurrent spinner threads.
 *
 *  Each runs under control of an independent button controlled 'switch'. 
 *
 *  Each Spinner[i] records its current state in a AppModel.Manager[i] object. 
 *  The AppModel's 'Mode' is set by entering the Mode number
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
 * A 'AppModel' comprising four (pseudo-)concurrent spinner threads.<p>
 * 
 * Each runs under control of an independent button controlled 'switch'.<p>
 * Each Spinner[i] records its current state in a AppModel.Manager[i] object.<p>
 * 
 * <textarea name="" rows="4" cols="80">
 * The AppModel's 'Mode' is set by entering the Mode number on start up in the:
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
 * @author Roger Prowse
 * @version "%I%, %G%"
 */
public class AppModel {// implements IModel{
//    AppServer refAppServer;
//    Status sysStatus;
//    int mode;
    int clientCount;
    int sysMode;
    
    AppRunner[] appRunners;
    Thread [] runnerThreads;
    MainForm mainForm;
    /**
     * Instantiate the appRunners and attach to threads
     * 
     * @param Mode selects mode of interaction between Spinners
     */
    public AppModel(AppServer appServer, String title, int sysMode){
//        refAppServer = appServer;
        clientCount = appServer.getClientCount();
        this.sysMode = sysMode;
        System.out.println("appmodel: clientCount = "+clientCount);//**
        /*
        status = appServer.getStartupStatus();
        Mode = aDatabase.getMode();
        clientCount = aDatabase.getCount();
        */
        appRunners = new AppRunner[clientCount];        
        runnerThreads = new Thread[clientCount];
//        Status status = new Status();
//        Record record = new Record();
        for (int i = 0; i < clientCount; i++){
            appRunners[i] = new AppRunner(appServer/*, status, record*/,i);            
            //Attach runner to threads and give names to help debugging
            String name = i+"";
            runnerThreads[i] = new Thread(appRunners[i],name);
//            status = appServer.getStartupStatus(i);
//            System.out.println("appModel status.id = "+status.getId());

//            record = appServer.getRunningRecord(i);
        }
        mainForm = new MainForm(this, title, sysMode, clientCount);
        mainForm.setVisible(true);
}
    
    /**
     * The AppModel knows what it wants to listen to -- the
     *      Buttons do not, so we run 'getListener()' to identify the Listener to 
     *      be added to the GUI Button event sources. (This is done here so that
     *      we are certain that the mainForm has already been instantiated in the
     *      constructor.  
     * 
     * The appModel knows about the appServer, so it can tell it to add
     * the spinners as observables.
     *
     * Then start the threads.
     * 
     * @param aMainForm provides reference through to the Buttons
     */
    public void startModel(/*IMainForm aMainForm*/){
        for (int i = 0; i < clientCount; i++){
            mainForm.getEventSource(i).
                    addActionListener(appRunners[i].getListener());
            //Start the Threads: execute thread[i].run()
            runnerThreads[i].start();
        }
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
     * Allows the status[i] to be passed to GUI
     * 
     * @return status[i]
     */
    public int getMode(int i){
        return appRunners[i].getMode();
    }

    /**
     * Allows the number of spinners to be passed to GUI
     * 
     * @return clientCount
     */
    public int getCount(){
        return clientCount;
    }
    
}//AppModel
/////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////


