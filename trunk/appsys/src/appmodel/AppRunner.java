//  netbeans\spinners\spinner_parts\src\thread_spinners\AppRunner.java
//
//  The Spin rate is defined by the DELAY_FACTOR in line 20.  Set
//  it to 5 for high speed spinning, or to 500 to see actions more
//  clearly.
/////////////////////////////////////////////////////////////////////
package appmodel;

import java.awt.event.ActionListener;
import java.util.Observable;
import server.*;

//////////////////////////////////////////////////////////////////////
// Roger Prowse
// History:
//	30 Oct 2003 v3:	Uses Record/Status in status
//			Separated from Spinners as for appTimers
//////////////////////////////////////////////////////////////////////

public class AppRunner implements Runnable {
    // Attributes  
    Spinner theSpinner;
    buttonSensor theButtonSensor;
    Status status;
    int id;
    int mode;
    int delay;
    int delayFactor;
    int period;
    Record record;
    
    // Constructor
    public AppRunner(AppServer appServer,/* Status newStatus, Record newRecord,*/ int i){
        status = appServer.getStartupStatus(i);
        id = status.getId();
        mode = status.getMode();
        //Define running speeds for each spinner
        delay = status.getDelay();
        delayFactor = status.getDelayFactor();
        period = delay*delayFactor;
        record = appServer.getRunningRecord(i);
       
        theSpinner = new Spinner(appServer, status, record/*, id*/);
        theButtonSensor = new buttonSensor(id);

        System.out.println("Made appRunner "+id+": " + delay+" * "+delayFactor+" = "+period);       
    }

    /////////////////////////////////////////////////////////////////
    public void run(){
        try{
            while (true) {
                // If button has been pressed to stop, then wait
                theButtonSensor.checkGoingState();

                theSpinner.executeOneStep();
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

