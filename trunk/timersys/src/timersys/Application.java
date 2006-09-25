package timersys;
// netbeans\spinners\trunk\timesys\src\timesys\Application.java
//
//	A 'model' comprising four event-triggered spinner threads, each
//	running to completion on events from four independent timers.
//  The GUI shows the spinners on the screen, and allows user control.
//
import java.awt.event.*;
import javax.swing.*;           // for Timer etc

import gui.*;
import database.Database;
import timed_spinners.Model;
///////////////////////////////////////////////////////////////////////////
// Roger Prowse
// History:
//		6 Dec 2001:	* Built objects in a hierarchy:
//		26 Sept 2002: 		Tested OK
//		27 Nov 2002: v2: 	Issued in application package
//              25 Sep 06:        Import to SVN
///////////////////////////////////////////////////////////////////////////

public class Application extends JFrame{
    /////////////////////////////////
    // The system implements the 'Model/View/Controller'(MVC) pattern
    // The 'Model' objects -- including Spinners:
    Model theModel;
    // The 'View/Controller' objects -- the Graphical User Interface (GUI):
    GUI theGUI;
    Database theDatabase;
    
    /////////////////////////////////
    // The Constructor
    public Application(int Mode) {
        super("Spinners TimeSys: Independent Event driven: v2.3 060808");
        setSize(800,600);
        // Add listener for 'close' command from title bar
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        theDatabase = new Database(Mode);
        theModel = new Model(theDatabase);
        theGUI = new GUI(theModel);
        // Start the model and refer to the GUI now it is instantiated
        theModel.startModel(theGUI);
        // and make the GUI object the Window to be displayed
        setContentPane(theGUI);
    }
}//Application
//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////
/*
// A Dummy class for testing out ideas
class Dummy implements IModel{
 
    boolean Stop = false;
 
    final static int FIVE_SEC = 5000;
    final static int ONE_SEC = 1000;
    final static int A100MS = 100;
    final static int A10MS = 10;
    final static int A1MS = 1;
 
    public Dummy(){
        System.out.println("Made Dummy");
        ////////////////////////////////////
        // Instantiate the Spinners and Timers
        for (int i = 0; i < 4; i++){
            System.out.println("i = " + i);
        }
    }
 
    public void startModel(IGUI theGUI){
    }
 
    public void stopDummy(){
    }
 
    //////////////////////////////////
    // Return something as an observable object
    public Observable getObservable(int i){
        return new Observable();
    }
}
 */
/////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////

