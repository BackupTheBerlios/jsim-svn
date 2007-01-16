//  netbeans\spinners\spinner_parts\src\thread_spinners\AppRunner.java
//
//  The Spin rate is defined by the DELAY_FACTOR in line 20.  Set
//  it to 5 for high speed spinning, or to 500 to see actions more
//  clearly.
/////////////////////////////////////////////////////////////////////
package appclient;

//import appserver.Record;
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
    boolean initRunner;
    
    // Constructor
    public AppRunner(IServer appServer, int i){
        refServer = appServer;
        id = i; //then overwritten by getStartupStatus
        theSpinner = new Spinner(i);
        theButtonSensor = new ButtonSensor(i);
//        record = new Record();
        System.out.println("Made appRunner "+id);
        initRunner = true;
    }
        
    public void setupRunner(int i){       
        status = refServer.getStartupStatus(i);
        System.out.println("AppRunner: setupRunner(): startupStatus "+i+" "+status);               
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
//                System.out.println("AppRunner: run() "+id);               
                theButtonSensor.checkGoingState();
                if (initRunner == true){
                    setupRunner(id);
                    initRunner = false;
                }               
                if(theSpinner.executeOneStep(record)){
                    //send the id to the server which controls the parameters
                    // for a pattern such as a Spinner.
                    // Tell server to generate a new runningRecord
//                    IRecord record1 = new Record();
                    System.out.println("AppRunner: run().cycleEnded "+id);               
                    record = refServer.cycleEnded(id);
//                    record3.setRecord(record1);
//                    record.setRecord(record3);
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


