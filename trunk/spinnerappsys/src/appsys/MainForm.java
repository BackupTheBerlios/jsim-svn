// netbeans\spinners\threadsys\src\threadsys\MainForm.java
//
//	This application uses a model having four independent
//	asynchronous threads, which may compete for resources.
////////////////////////////////////////////////////////////////////////
package appsys;

import appgui.*;
import interfaces.*;
import java.awt.event.*;
import javax.swing.*;           // for Timer etc

///////////////////////////////////////////////////////////////////////////
public class MainForm extends JFrame {//implements IMainForm{
    GUIPanel theGUIPanel;
    // The Constructor
    public MainForm(IClient appClient,String title) {
        int sysMode = appClient.getSysMode();
        super.setTitle(title + sysMode);
        int clientCount = appClient.getCount();
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
        theGUIPanel = new GUIPanel(appClient);
        //Make the GUIPanel object the Window to be displayed      
        setContentPane(theGUIPanel);
        setVisible(true);
        System.out.println("GUI: made mainForm ");        
    }
    
    //////////////////////////////////
    // Identify Button[i] as an event source
    public JToggleButton getEventSource(int i){
        return theGUIPanel.getEventSource(i);
    }
}//MainForm
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////



