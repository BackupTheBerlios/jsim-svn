/*
 * MainFormTest.java
 * JUnit based test
 *  This test verifies that the GUI can be produced and a toggleButton
 *  identified.
 *      The GUI test should show a static view of the GUI screen, but at 
 *      present it only flashes.
 * Created on 16 November 2006, 16:51
 */

package appgui;

import interfaces.IModel;
import java.awt.Container;
import junit.framework.*;
import javax.swing.*;

/**
 *
 * @author Roger
 */
public class MainFormTest extends TestCase /*implements IModel*/{
    int sysMode;
    int clientCount;
    String title;
    IModel appModelStub;
    
    public MainFormTest(String testName) {
        super(testName);

    }

    protected void setUp() throws Exception {
        sysMode = 0;
        clientCount = 4;
        title = "appgui test.  sysMode = ";
        appModelStub = new AppModelStub();
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of class appgui.MainForm.
     * This test depends on the GUI being seen to show correctly.
     */
    public void testMainForm() {
        System.out.println("test MainForm");
        
        int i = 0;
        MainForm mainForm = new MainForm(appModelStub, title, sysMode, clientCount);
        mainForm.setVisible(true);
        //This is not a complete verification: it only checks that the GUIPanel
        //is correctly made.  
        Container expResult = mainForm.theGUIPanel;
        Container result = mainForm.getContentPane();
        assertEquals(expResult, result);
    }
    /**
     * Test of getEventSource method, of class appgui.MainForm. The test 
     *should show that a particular toggleButton is located as a source
     */
    public void testGetEventSource() {
        System.out.println("getEventSource");
        
        int i = 0;
        MainForm mainForm = new MainForm(appModelStub, title, sysMode, clientCount);
        mainForm.setVisible(true);
        
        JToggleButton expResult = mainForm.theGUIPanel.theSpinnerPanels[i].theParameterPanel.theButton;;
        JToggleButton result = mainForm.getEventSource(i);
        if (expResult == result){
            System.out.println("SUCCESS MainForm.");
        } 
        else {System.out.println("FAIL MainForm. expRes: "+expResult+" result: "+result);
        }
        boolean done;
        assertEquals(expResult, result);
       
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
