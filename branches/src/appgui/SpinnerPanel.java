// SpinnerSys\src\gui\SpinnerPanel.java
// Each SpinnerPanel provides a parameter panel and the display
// canvas for its Spinner
//////////////////////////////////////////////////////////////////////////
package appgui;

import interfaces.IModel;
import javax.swing.*;       // for Timer etc
import java.awt.*;          // for GridLayout

//import interfaces.IModel;

///////////////////////////////////////////////////////////////////////////
// Roger Prowse
// History:
//		 6 Dec 2001:            Built objects in a hierarchy:
//		26 Sept 2002: 		Tested OK
//		27 Nov 2002: v2: 	Issued in gui package
//               8 Aug 06:              Moved to net_beans
///////////////////////////////////////////////////////////////////////////

public class SpinnerPanel extends JPanel{
    public Parameter theParameterPanel;
    displayCanvas theDisplayCanvas;
    
    SpinnerPanel(IModel theModel, int id){
        ////////////////////////////////////////
        // Now set up a Spinner panel,
        // and setBackground, size and border
        // Set grid 1 row, 2 columns, spacing 10 horiz, 50 vert
        setBorder(BorderFactory.createEtchedBorder(Color.white, Color.blue));
        setLayout(new GridLayout(1,2,20,20));
        // Instantiate the objects in the Spinner Panel
        // and add to the Spinner Panel
        theParameterPanel = new Parameter(theModel,id);
        add(theParameterPanel);
        System.out.println("spinnerPanel: Add parameterPanel " + id);
        
        // Build the display canvas and add to the Spinner Panel
        theDisplayCanvas = new displayCanvas(theModel, id);
        add(theDisplayCanvas);
        System.out.println("spinnerPanel: Add display canvas " + id);
    }
    
    public Insets getInsets(){// n,w,s,e
        return new Insets(20,20,20,20);
    }
}// SpinnerPanel


