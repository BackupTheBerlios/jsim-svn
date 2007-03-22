// netbeans\spinners.timersys\spinner_parts\gui\GUIPanel.java
// Sets up four spinnerPanels and allows their buttons to be
// seen as event sources.

package appgui;

import interfaces.*;
import javax.swing.*;	// for Timer etc
import java.awt.*;	// for GridLayout

///////////////////////////////////////////////////////////////////////////
// Roger Prowse
// History:
//		6 Dec 2001:	* Built objects in a hierarchy:
//		26 Sept 2002: 		Tested OK
//		27 Nov 2002: v2: 	Issued in gui package
///////////////////////////////////////////////////////////////////////////

public class GUIPanel extends JPanel{
    // Set up the GUIPanel panel comprising N view panels for the client activities
    JFrame refFrame;
    int clientCount;
    private ViewPanel[] theViewPanels;
    
    public GUIPanel(JFrame aFrame, IClient appClient){
        refFrame = aFrame;
        clientCount = appClient.getCount();
        System.out.println("GUIPanel = "+this+" clientCount = " + clientCount);
        theViewPanels = new ViewPanel[clientCount];

        ////////////////////////////////////////
        // Now set up the mainPane,
        // and setBackground, size and border
        // Set grid 2 rows, 2 columns, spacing 10 horiz, 50 vert
        setBorder(BorderFactory.createEtchedBorder(Color.white, Color.blue));
        setBackground(Color.white);
        setLayout(new GridLayout(2,2,20,20));
        for (int i = 0; i < clientCount; i++){
            // Instantiate the objects in the GUIPanel
            // First build the ViewPanels, passing to them a reference
            // to the associated 'Client.Controller' object
            theViewPanels[i] = new ViewPanel(i, appClient.getViewable(i));
            System.out.println("GUI: Add viewPanel " + i);
            // Add this panel to the GUIPanel
            this.add(theViewPanels[i]);
        }
    }
    
    public int getClientCount() {
        return clientCount;
    }
    public void setClientCount(int val) {
        this.clientCount = val;
    }
}// GUIPanel


