//  netbeans\spinners\spinner_parts\src\thread_spinners\buttonSensor.java
//
/////////////////////////////////////////////////////////////////////
package appclient;

import java.awt.event.*;

/** @Author: Roger Prowse
 */
public class ButtonSensor implements ActionListener{
    // Attributes
    int theId;
    boolean goingState;			// Controlled by button
    
    // Constructor
    public ButtonSensor(int id){
        theId = id;
        goingState = false;
        System.out.println("Made buttonSensor "+theId);
    }
        
    /////////////////////////////////////////////
    public void actionPerformed(ActionEvent e){
        // Must be from button so no need to determine source
        changeGoingState();
    }
    
    public synchronized void changeGoingState(){
        goingState = !goingState;
        System.out.println("changeGoingState "+theId+" goingState = "+goingState);
        if (goingState == true) {notify();}
    }
    
    public synchronized void checkGoingState(){
        try{
            while (!goingState){
//                System.out.println("Spinner"+theId+": Waiting ");
                wait();
            }
        }catch(InterruptedException e){
            System.out.println("Interrupted wait");
        }
    }

}//buttonSensor
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////


