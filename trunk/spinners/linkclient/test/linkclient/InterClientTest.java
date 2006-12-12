/*
 * InterClientTest.java
 * JUnit based test
 *
 * Created on 24 November 2006, 13:51
 */

package linkclient;

import junit.framework.*;
import appdata.*;
import interfaces.*;
import java.awt.*;

/**
 *
 * @author Roger
 */
public class InterClientTest extends TestCase {
    
    public InterClientTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(InterClientTest.class);
        
        return suite;
    }

    /**
     * Test of getArgs method, of class linkclient.InterClient.
     */
    public void testGetArgs() {
        System.out.println("getArgs");
        
        InterClient instance = null;
        
        String[] expResult = null;
        String[] result = instance.getArgs();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSimpleClient method, of class linkclient.InterClient.
     */
    public void testGetSimpleClient() {
        System.out.println("getSimpleClient");
        
        InterClient instance = null;
        
        SimpleClient expResult = null;
        SimpleClient result = instance.getSimpleClient();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getList method, of class linkclient.InterClient.
     */
    public void testGetList() {
        System.out.println("getList");
        
        InterClient instance = null;
        
        List expResult = null;
        List result = instance.getList();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartupStatus method, of class linkclient.InterClient.
     */
    public void testGetStartupStatus() {
        System.out.println("getStartupStatus");
        
        int i = 0;
        InterClient instance = null;
        
        IStatus expResult = null;
        IStatus result = instance.getStartupStatus(i);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRunningRecord method, of class linkclient.InterClient.
     */
    public void testGetRunningRecord() {
        System.out.println("getRunningRecord");
        
        int i = 0;
        InterClient instance = null;
        
        IRecord expResult = null;
        IRecord result = instance.getRunningRecord(i);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSysMode method, of class linkclient.InterClient.
     */
    public void testGetSysMode() {
        System.out.println("getSysMode");
        
        InterClient instance = null;
        
        int expResult = 0;
        int result = instance.getSysMode();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientCount method, of class linkclient.InterClient.
     */
    public void testGetClientCount() {
        System.out.println("getClientCount");
        
        InterClient instance = null;
        
        int expResult = 0;
        int result = instance.getClientCount();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cycleEnded method, of class linkclient.InterClient.
     */
    public void testCycleEnded() {
        System.out.println("cycleEnded");
        
        int i = 0;
        InterClient instance = null;
        
        IRecord expResult = null;
        IRecord result = instance.cycleEnded(i);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
