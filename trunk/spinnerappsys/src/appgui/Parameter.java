// SpinnerSys\src\gui\Parameter.java
// Each parameterPanel provides a button (and label) for its
// Spinner, and listens for button events to update the panel screen
//////////////////////////////////////////////////////////////////////////
package appgui;

import interfaces.*;
import java.awt.event.*;
import javax.swing.*;           // for Timer etc
import java.awt.*;		// for GridLayout

///////////////////////////////////////////////////////////////////////////
// Roger Prowse
// History:
//		6 Dec 2001:	* Built objects in a hierarchy:
//		26 Sept 2002: 		Tested OK
//		27 Nov 2002: v2: 	Issued in gui package
///////////////////////////////////////////////////////////////////////////

public class Parameter extends JPanel implements ActionListener{
    // Set up a parameter panel comprising one button and one label
    // Lemay & Cadenhead p547
    int parameterType;
    int parameterId;
    JToggleButton theButton;
    char theButtonComd;
    
    JLabel theLabel;
    
    Parameter(IClient theClient, int i){
        parameterType = 1;
        parameterId = i;
        // Grid of two rows of as many columns as necessary, (at least 1)
        // (and in this case 1) with gaps of 10 along (horizontal), and
        // gaps of 20 down (vertical).
        // Controls are added in
        setBorder(BorderFactory.createEtchedBorder(Color.white, Color.blue));
        setBackground(Color.white);
        setLayout(new GridLayout(2,1,10,20));	//rows, cols, gap/h, gap/v
        ////////////////////////////////////
        // Instantiate the objects in the Parameter Panel
        // Define a Button with text and colour
        String initString = "Click to run";
        theButtonComd = 'r';
        theButton = new JToggleButton(initString);
        theButton.setBackground(Color.cyan);
        theButton.setBorder
                (BorderFactory.createEtchedBorder(Color.white, Color.blue));
        // actionString defines the type of action and its identity
        // It can be used by a listener to interpret an action
        String actionString =
                String.valueOf(parameterType) + String.valueOf(parameterId)
                + theButtonComd;
        theButton.setActionCommand(actionString);
        // Add this Parameter panel as a listener to the button
        theButton.addActionListener(this);
        theButton.addActionListener(theClient.getListener(i));
        // Add this button to its panel
        add(theButton);
        
        // and a label as well
        if (theClient.getSysMode() == 3){
            if (parameterId == 0){
                theLabel = new JLabel("Producer", JLabel.CENTER);
            } else {//parameterId == 1
                theLabel = new JLabel("Consumer", JLabel.CENTER);
            }
        }else {
            theLabel = new JLabel("Spinner "+ parameterId, JLabel.CENTER);
        }
        theLabel.setBorder
                (BorderFactory.createEtchedBorder(Color.white, Color.blue));
        add(theLabel);
        
        System.out.println("Parameter: Add Button and Label " + parameterId);
    }
    
    public Insets getInsets(){
        //////////////////////////////
        return new Insets(20,20,20,20);
    }
    
    public void actionPerformed(ActionEvent e){
        //////////////////////////////
        // Get Command from the button's 'actionString', update the text on
        // the button and change the command for the next click. (Smith p110)
        //**
        String actionString = e.getActionCommand();
        // Extract command character
        char theComd = actionString.charAt(2);
        if (theComd == 'r'){
            theButton.setText("Click to stop");
            theButtonComd = 's';
        } else {
            theButton.setText("Click to run");
            theButtonComd = 'r';
        }
        actionString = String.valueOf(parameterType) 
                + String.valueOf(parameterId) + theButtonComd;        
        theButton.setActionCommand(actionString);
    }
}//Parameter
////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////


