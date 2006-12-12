/*
 * DatabaseTest.java
 * JUnit based test
 *
 * Created on 07 December 2006, 17:12
 */

package appserver;

import appserver.Database;
import junit.framework.*;
import interfaces.*;
import appdata.*;

/**
 *
 * @author Roger
 */
public class DatabaseTest extends TestCase {
    
    public DatabaseTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of setStartupStatuses method, of class appserver.Database.
     */
    public void testSetStartupStatuses() {
        System.out.println("setStartupStatuses");
        
        int i = 0;
        IStatus val = null;
        Database instance = null;
        
        instance.setStartupStatuses(i, val);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartupStatuses method, of class appserver.Database.
     */
    public void testGetStartupStatuses() {
        System.out.println("getStartupStatuses");
        int clientCount = 4;
        int i = 0;
        Database instance = new Database(clientCount);
        IStatus expResult = new Status(i);
         
        for (i = 0;i<4;i++){           
            expResult.setId(i);
            for (int j = 0; j < 4; j++) {
            System.out.println("i:"+i+" j:"+j);
                expResult.setMaxPosIndex(j);
                instance.setStartupStatuses(i, expResult);
                System.out.println("expResult:"+expResult+"  "+
                        expResult.getName()+" "+expResult.getId()+" "+
                        expResult.getMaxPosIndex());

                IStatus result = instance.getStartupStatuses(i);
                assertEquals(expResult.getName(), result.getName());
                assertEquals(expResult.getId(), result.getId());
                assertEquals(expResult.getMode(), result.getMode());
                assertEquals(expResult.getMaxPosIndex(), result.getMaxPosIndex());
                assertEquals(expResult.getMaxColorIndex(), result.getMaxColorIndex());
//                System.out.println("result:"+result+"  "+
//                        result.getName()+" "+result.getId()+" "+
//                        result.getPosIndex()+" "+result.getColorIndex());
            }
         }
//        assertEquals(expResult, result);
      }

    /**
     * Test of getRunningRecords method, of class appserver.Database.
     */
    public void testGetRunningRecords() {
        System.out.println("getRunningRecords");
        int clientCount = 4;
        int i = 0;
        Database instance = new Database(clientCount);
        IRecord expResult = new Record(i);
         
        for (i = 0;i<4;i++){           
            expResult.setId(i);
            for (int j = 0; j < 4; j++) {
            System.out.println("i:"+i+" j:"+j);
                expResult.setPosIndex(j);
                instance.setRunningRecords(i, expResult);
                System.out.println("expResult:"+expResult+"  "+
                        expResult.getName()+" "+expResult.getId()+" "+
                        expResult.getPosIndex()+" "+expResult.getColorIndex());

                IRecord result = instance.getRunningRecords(i);
                assertEquals(expResult.getName(), result.getName());
                assertEquals(expResult.getId(), result.getId());
                assertEquals(expResult.getMode(), result.getMode());
                assertEquals(expResult.getPosIndex(), result.getPosIndex());
                assertEquals(expResult.getColorIndex(), result.getColorIndex());
//                System.out.println("result:"+result+"  "+
//                        result.getName()+" "+result.getId()+" "+
//                        result.getPosIndex()+" "+result.getColorIndex());
            }
         }
//        assertEquals(expResult, result);
        
    }

    /**
     * Test of setRunningRecords method, of class appserver.Database.
     */
    public void testSetRunningRecords() {
        System.out.println("setRunningRecords");
        
        int i = 0;
        IRecord val = null;
        Database instance = null;
        
        instance.setRunningRecords(i, val);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
