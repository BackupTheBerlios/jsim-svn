//  netbeans\spinners\spinner_parts\src\thread_spinners\buttonSensor.java
//
/////////////////////////////////////////////////////////////////////
package liner;

import java.awt.event.*;

/** @Author: Roger Prowse
 */
public class ButtonSensor implements ActionListener{
    // Attributes
    String name;
    String id;
    boolean goingState;			// Controlled by button
    
    // Constructor
    public ButtonSensor(String name, String id){
        this.name = name;
        this.id = id;
        goingState = false;
        System.out.println("Made buttonSensor "+this.name+this.id);
    }

    public ButtonSensor getButtonSensor(/*String name, String id*/){
//        this.name = name;
//        this.id = id;
        return this;
    }
    /////////////////////////////////////////////
    public void actionPerformed(ActionEvent e){
        // Must be from the button to which it was added as a listener,
        // so no need to determine source
            changeGoingState();
    }
    
    public synchronized void changeGoingState(){
        goingState = !goingState;
        System.out.println("changeGoingState "+name+id+" goingState = "+goingState);
        if (goingState == true) {notify();}
    }
    
    public synchronized void checkGoingState(){
        try{
            while (!goingState){
//                System.out.println("Spinner"+name+id+": Waiting ");
                wait();
            }
        }catch(InterruptedException e){
            System.out.println("Interrupted wait");
        }
    }
}//buttonSensor
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////


