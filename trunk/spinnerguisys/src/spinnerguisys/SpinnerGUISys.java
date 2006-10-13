/*
 * SpinnerGUISys.java
 *
 * Created on 08 October 2006, 12:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package spinnerguisys;

import database.*;              // for Data
import gui.nbMainForm;
import thread_spinners.Model;

/**
 *
 * @author Roger
 */
public class SpinnerGUISys {

    /**
     * Creates a new instance of SpinnerGUISys
     */
    public SpinnerGUISys() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String theTitle = "Spinners Matisse GUISys: Concurrent Threads: v4 061011  Mode = ";
        /////////////////////////////////
        // The system implements the 'Model/View/Controller'(MVC) pattern
        // The 'Model' objects defined by records in the database: 
        Database theDatabase;
        // The 'Model' processes using the database to implement the Spinners:
        Model theModel;
        // The 'View/Controller' objects -- the Graphical User Interface (GUI):
        nbMainForm theMainForm;
   
        // Now interpret the required Mode
        int Mode;
        try{Mode = Integer.parseInt(args[0]);
        if(Mode > 3) {Mode = 0;}
        } catch(NumberFormatException e){
            System.out.println("Invalid format -- select 0");
            Mode = 0;}
        //Activate the nbMainForm i.e. the GUI/Model system
        theDatabase = new Database(Mode);
        theModel = new Model(theDatabase);
        theMainForm = new nbMainForm(Mode, theModel, theTitle);
        theMainForm.setVisible(true);
        // and start the model        
        theModel.startModel(theMainForm);
    }   
}
