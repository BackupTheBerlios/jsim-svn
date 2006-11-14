// netbeans\spinners.timersys\spinner_parts\gui\GUIPanel.java
// Sets up four spinnerPanels and allows their buttons to be
// seen as event sources.

package appgui;

import appmodel.AppModel;
import javax.swing.*;	// for Timer etc
import java.awt.*;	// for GridLayout
import java.io.Serializable;


///////////////////////////////////////////////////////////////////////////
// Roger Prowse
// History:
//		6 Dec 2001:	* Built objects in a hierarchy:
//		26 Sept 2002: 		Tested OK
//		27 Nov 2002: v2: 	Issued in gui package
///////////////////////////////////////////////////////////////////////////

public class GUIPanel extends JPanel implements Serializable/*, IGUIPanel*/{
    // Set up the GUIPanel panel comprising four Spinner panels
    int spinnerCount;
    SpinnerPanel[] theSpinnerPanels;
    
    public GUIPanel(AppModel aModel){
        spinnerCount = aModel.getCount();
        System.out.println("GUI.spinnerCount " + spinnerCount);
        theSpinnerPanels = new SpinnerPanel[spinnerCount];

        ////////////////////////////////////////
        // Now set up the mainPane,
        // and setBackground, size and border
        // Set grid 2 rows, 2 columns, spacing 10 horiz, 50 vert
        setBorder(BorderFactory.createEtchedBorder(Color.white, Color.blue));
        setBackground(Color.white);
        setLayout(new GridLayout(2,2,20,20));
        ////////////////////////////////////
        // Instantiate the objects in the GUIPanel
        // First build the spinnerPanels, passing to them a reference
        // to the associated 'Model' object
        //// to the parent 'Application' object
        for (int i = 0; i < spinnerCount; i++){
            theSpinnerPanels[i] = new SpinnerPanel(aModel, i);
            //Add these Panels to the GUIPanel panel
            add(theSpinnerPanels[i]);
            System.out.println("GUI: Add spinnerPanel " + i);
        }
    }
    
    //////////////////////////////////
    // Identify Button[i] as an event source
    public JToggleButton getEventSource(int i){
        return theSpinnerPanels[i].theParameterPanel.theButton;
    }
}// GUIPanel

