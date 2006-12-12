/*
 * AppServerTest.java
 * JUnit based test
 *
 * Created on 07 December 2006, 17:11
 */

package appserver;

import appserver.AppServer;
import appserver.DBManager;
import junit.framework.*;
import interfaces.*;

/**
 *
 * @author Roger
 */
public class AppServerTest extends TestCase {
    
    public AppServerTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of getArgs method, of class appserver.AppServer.
     */
    public void testGetArgs() {
        System.out.println("getArgs");
        
        AppServer instance = null;
        
        String[] expResult = null;
        String[] result = instance.getArgs();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeSetup method, of class appserver.AppServer.
     */
    public void testMakeSetup() {
        System.out.println("makeSetup");
        
        int i = 0;
        AppServer instance = null;
        
        instance.makeSetup(i);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDBManager method, of class appserver.AppServer.
     */
    public void testGetDBManager() {
        System.out.println("getDBManager");
        
        AppServer instance = null;
        
        DBManager expResult = null;
        DBManager result = instance.getDBManager();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDBManager method, of class appserver.AppServer.
     */
    public void testSetDBManager() {
        System.out.println("setDBManager");
        
        DBManager val = null;
        AppServer instance = null;
        
        instance.setDBManager(val);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartupStatus method, of class appserver.AppServer.
     */
    public void testGetStartupStatus() {
        System.out.println("getStartupStatus");
        
        int i = 0;
        AppServer instance = null;
        
        IStatus expResult = null;
        IStatus result = instance.getStartupStatus(i);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRunningRecord method, of class appserver.AppServer.
     */
    public void testGetRunningRecord() {
        System.out.println("getRunningRecord");
        
        int i = 0;
        AppServer instance = null;
        
        IRecord expResult = null;
        IRecord result = instance.getRunningRecord(i);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSysMode method, of class appserver.AppServer.
     */
    public void testGetSysMode() {
        System.out.println("getSysMode");
        
        AppServer instance = null;
        
        int expResult = 0;
        int result = instance.getSysMode();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientCount method, of class appserver.AppServer.
     */
    public void testGetClientCount() {
        System.out.println("getClientCount");
        
        AppServer instance = null;
        
        int expResult = 0;
        int result = instance.getClientCount();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cycleEnded method, of class appserver.AppServer.
     */
    public void testCycleEnded() {
        System.out.println("cycleEnded");
        
        int i = 0;
        AppServer instance = null;
        
        IRecord expResult = null;
        IRecord result = instance.cycleEnded(i);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSysMode method, of class appserver.AppServer.
     */
    public void testSetSysMode() {
        System.out.println("setSysMode");
        
        int val = 0;
        AppServer instance = null;
        
        instance.setSysMode(val);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClientCount method, of class appserver.AppServer.
     */
    public void testSetClientCount() {
        System.out.println("setClientCount");
        
        int val = 0;
        AppServer instance = null;
        
        instance.setClientCount(val);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
