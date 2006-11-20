//  netbeans\spinners\spinner_parts\src\thread_spinners\AppRunner.java
//
//  The Spin rate is defined by the DELAY_FACTOR in line 20.  Set
//  it to 5 for high speed spinning, or to 500 to see actions more
//  clearly.
/////////////////////////////////////////////////////////////////////
package appclient;

import interfaces.*;
import java.awt.event.ActionListener;
import java.util.Observable;

//////////////////////////////////////////////////////////////////////
// Roger Prowse
// History:
//	30 Oct 2003 v3:	Uses Record/Status in status
//			Separated from Spinners as for appTimers
//////////////////////////////////////////////////////////////////////

public class AppRunner implements Runnable {
    // Attributes
    IServer refServer;
    Spinner theSpinner;
    ButtonSensor theButtonSensor;
    IStatus status;
    int id;
    int mode;
    int delay;
    int delayFactor;
    int period;
    IRecord record;
    
    // Constructor
    public AppRunner(IServer appServer, int i){
        refServer = appServer;
        theSpinner = new Spinner(i/*status, record*/);
        theButtonSensor = new ButtonSensor(i);
        System.out.println("Made appRunner "+id);       
    }
        
    public void initRunner(int i){       
        status = refServer.getStartupStatus(i);
        id = status.getId();
        mode = status.getMode();
        //Define running speeds for each spinner
        delay = status.getDelay();
        delayFactor = status.getDelayFactor();
        period = delay*delayFactor;
        record = refServer.getRunningRecord(i);
        theSpinner.startSpinner(status);
        System.out.println("initialised appRunner "+id+": " + delay+" * "+delayFactor+" = "+period);       
    }

    /////////////////////////////////////////////////////////////////
    public void run(){
        try{
            while (true) {
                // If button has been pressed to stop, then wait
                theButtonSensor.checkGoingState();
                
                if(theSpinner.executeOneStep(record)){
                    //send the id to the server which controls the parameters
                    // for a pattern such as a Spinner.
                    // Tell server to generate a new runningRecord
                    record = refServer.cycleEnded(id);
                }
                Thread.sleep(period);
            }
        }catch(InterruptedException e){
            System.out.println("Interrupted sleep");
        }
    }
    
    public int getMode(){
        return mode;
    }

    /**
     * Identifies the spinner as an observable object
     * @return reference to spinner[i]
     */
    public Observable getObservable(){        
        return theSpinner;
    }

    /**
     * Identifies theButtonSensor as a Listener
     * @return reference to theButtonSensor[i]
     */
    public ActionListener getListener(){        
        return theButtonSensor;
    }

}//Spinner
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////


