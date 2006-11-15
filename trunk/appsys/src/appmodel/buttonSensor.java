//  netbeans\spinners\spinner_parts\src\thread_spinners\buttonSensor.java
//
/////////////////////////////////////////////////////////////////////
package appmodel;

import java.awt.event.*;

/** @Author: Roger Prowse
 */
public class buttonSensor implements ActionListener{
    // Attributes
    int theId;
    boolean goingState;			// Controlled by button
    
    // Constructor
    public buttonSensor(int id){
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
//        System.out.println("checkGoingState"+theId+" State = "+goingState);
    }

}//buttonSensor
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////

