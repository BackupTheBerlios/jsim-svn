// netbeans\spinners\threadsys\src\threadsys\MainForm.java
//
//	This application uses a model having four independent
//	asynchronous threads, which may compete for resources.
////////////////////////////////////////////////////////////////////////
package appgui;

import appmodel.AppModel;
import java.awt.event.*;
import javax.swing.*;           // for Timer etc

///////////////////////////////////////////////////////////////////////////
public class MainForm extends JFrame {//implements IMainForm{
    GUIPanel theGUIPanel;
    // The Constructor
    public MainForm(AppModel appModel, String title, int clientCount) {
        super(title + clientCount);
        if (clientCount > 2){
            setSize(800,600);
        }else {
            setSize(400,600);}
        // Add listener for 'close' command from title bar
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        theGUIPanel = new GUIPanel(appModel);
        //Make the GUIPanel object the Window to be displayed      
        setContentPane(theGUIPanel);
    }
    
    //////////////////////////////////
    // Identify Button[i] as an event source
    public JToggleButton getEventSource(int i){
        return theGUIPanel.getEventSource(i);
    }

}//MainForm
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////


