package timersys;
////////////////////////////////////////////////////////////////////////////
/* netbeans\spinners\trunk\timersys\src\timersys\TimeSys.java
 *
 * Created on 07 August 2006, 13:19
 *
 * @author Roger
 */
// Roger Prowse
// History:
//		6 Dec 2001:	* Built objects in a hierarchy:
//
//	Application -->	GUI --> (spinnerPanels)*4 --> Parameter --> JToggleButton
//			\                         \             \-->JLabel
//			 \			   \--> Display
//			  \--> Model --> (Spinner || appTimer)*4
//
//			* appTimer[i] is an ActionListener for theButton[i]
//				appTimer[i] controls Spinner[i]
//				Display[i] observes Spinner[i]
//			* Each layer declares and defines the layer below.
//			* The lines on the DisplayCanvas are of absolute length
//			and position, defined from the origin of the canvas, not
//			the screen.
//			* The applet Application does not throw IOException
//
//		26 Sept 2002: 		Tested OK
//		 3 Oct 2002:		Application or applet selectable
//		27 Nov 2002: v2: 	Issued in 'application' package
//               8 Aug 06:              replace show() with setVisible()
//              25 Sep 06:              Imported to  Berlios SVN
///////////////////////////////////////////////////////////////////////////

//For a standalone system define 'TimerSys' and construct an object of
//class 'Application', and show it (Smith p410):
/**/
//________________________________________________
//						  |
public class TimerSys {	   	        	//|
//________________________________________________|
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) /*throws IOException*/ {
        // First interpret the required Mode
        int Mode = 0;
/*        try{Mode = Integer.parseInt(args[0]);
        if(Mode > 3) {Mode = 0;}
        } catch(NumberFormatException e){
            System.out.println("Invalid format -- select 0");
            Mode = 0;}
 */
        //Activate the Application i.e. the GUI/Model system
        new Application(Mode).setVisible(true);
    }
}    
//For an Applet, construct the object of class 'Application', run the
//applet function 'init()' and 'show' the Application.
/*
//________________________________________________
//						  |
public class TimerApp extends Applet { 	    	//|
//________________________________________________|
	Application theApplication = new Application(Mode);
        public void init(){
                theApplication.setVisible(true);
        }
 } 
 */


