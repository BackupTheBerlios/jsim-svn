// netbeans\spinners\threadsys\src\threadsys\MainForm1.java
//
//	This application uses a model having four independent
//	asynchronous threads, which may compete for resources.
////////////////////////////////////////////////////////////////////////
package appstandalonesys;

import appgui.*;
import interfaces.*;
import java.awt.event.*;
import javax.swing.*;           // for Timer etc
//import JFrame.*;

///////////////////////////////////////////////////////////////////////////
/**
 * for Timer etc
 * /
 */
public class MainForm1 extends JFrame implements IMainForm{
    IClient refClient;
    private String refTitle1, refTitle2;
    GUIPanel theGUIPanel;

    // The Constructor
    public MainForm1(IClient appClient, IServer appServer,
                                        String title1,String title2) {
        refClient = appClient;
        refTitle1 = title1;
        refTitle2 = title2;      
        // Add listener for 'close' command from title bar
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        initGUIFrame();
    }
    
    private void initGUIFrame(){
        int n = refClient.getCount()<2?2:refClient.getCount()+1; 
        setSize(200*n/2,600);
        theGUIPanel = new GUIPanel(this, refClient);
        //Make the GUIPanel object the Window to be displayed
        add("Center", theGUIPanel);

        setContentPane(theGUIPanel);
        setVisible(true);
        super.setTitle(refTitle1+" "+refClient.getSysMode()+". "+refTitle2);

        System.out.println("initGUIFrame(): made mainForm ");
    }
        
    public IGUIPanel getGUIPanel(){
        return theGUIPanel;
    }
}//MainForm1
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////



