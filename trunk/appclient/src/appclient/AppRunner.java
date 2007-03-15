//  netbeans\spinners\spinner_parts\src\thread_spinners\AppRunner.java
//
//  The Spin rate is defined by the DELAY_FACTOR in line 20.  Set
//  it to 5 for high speed spinning, or to 500 to see actions more
//  clearly.
/////////////////////////////////////////////////////////////////////
package appclient;

//import appserver.Record;
import interfaces.*;
import spinner.Spinner;

//////////////////////////////////////////////////////////////////////
// Roger Prowse
// History:
//	30 Oct 2003 v3:	Uses Record/Status in status
//			Separated from Spinners as for appTimers
//////////////////////////////////////////////////////////////////////

public class AppRunner implements IAppRunner {
    // Attributes
    IServer refServer;
    IAppObj appObj;
    IAppController theAppController;
    IStatus theStatus;
    IRecord theRecord;
    int i;
    
    // Constructor
    public AppRunner(IServer appServer, int i){        
        refServer = appServer;
        this.i = i;
        appObj = appServer.getAppObj(i);
        theAppController = appObj.getAppController();
        theStatus = appObj.getStatus();
        theRecord = appObj.getRecord();
        theAppController.startController(this, theStatus);
        System.out.println("Made appRunner "+i);
    }
    
    public IAppController getAppController(){
        return theAppController;        
    }
    
    public IRecord update(){
        return refServer.cycleEnded(i);                
    }
}//Spinner
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////


