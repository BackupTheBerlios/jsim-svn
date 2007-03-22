// SpinnerSys\src\gui\ViewPanel.java
// Each ViewPanel provides a parameter panel and the display
// canvas for its Spinner
//////////////////////////////////////////////////////////////////////////
package appgui;

import interfaces.IAppController;
import javax.swing.*;       // for Timer etc
import java.awt.*;          // for GridLayout
import java.awt.event.ActionListener;


///////////////////////////////////////////////////////////////////////////
// Roger Prowse
// History:
//		 6 Dec 2001:            Built objects in a hierarchy:
//		26 Sept 2002: 		Tested OK
//		27 Nov 2002: v2: 	Issued in gui package
//               8 Aug 06:              Moved to net_beans
///////////////////////////////////////////////////////////////////////////

public class ViewPanel extends JPanel{
    IAppController appController;
    ParameterPanel theParameterPanel;
    DisplayCanvas theDisplayCanvas;
    String name;
    String id;
    int mode;
    ActionListener listener;
    
    ViewPanel(int i, IAppController appController){
        this.appController = appController;
        name = appController.getName();
        id = appController.getId();
        mode = appController.getMode();
        listener = appController.getListener();
        ////////////////////////////////////////
        // Now set up a view panel,
        // and setBackground, size and border
        // Set grid 1 row, 2 columns, spacing 10 horiz, 50 vert
        setBorder(BorderFactory.createEtchedBorder(Color.white, Color.blue));
        setLayout(new GridLayout(1,2,20,20));
        // Instantiate the objects in the viewPanel
        // and add to the viewPanel
        theParameterPanel = new ParameterPanel(name, id, mode, listener);
        add(theParameterPanel);
        System.out.println("viewPanel: Add parameterPanel " + i);
        
        // Build the display canvas and add to the viewPanel
        theDisplayCanvas = new DisplayCanvas(appController);
        add(theDisplayCanvas);
        System.out.println("viewPanel: Add display canvas " + i);
    }
    
    public Insets getInsets(){// n,w,s,e
        return new Insets(20,20,20,20);
    }

    public String getName() {
        return name;
    }
    public void setName(String val) {
        this.name = val;
    }

    public String getId() {
        return id;
    }
    public void setId(String val) {
        this.id = val;
    }

    public ActionListener getListener() {
        return listener;
    }

    public void setListener(ActionListener val) {
        this.listener = val;
    }
}// ViewPanel



