/*
 * SimpleClientCommandTest_1.java
 * JUnit based test
 *
 * Created on 24 November 2006, 13:51
 */

package linkclient;

import junit.framework.*;
import appdata.*;
import java.awt.List;

/**
 *
 * @author Roger
 */
public class SimpleClientCommandTest_1 extends TestCase {
     ClientFrame cf;
     List liste;
     
    public SimpleClientCommandTest_1(String testName) {
        super(testName);
        this.liste = new List(4);
        cf = new ClientFrame("localhost",12345);
    }

    protected void setUp() throws Exception {
//        cf.open();
    }

    protected void tearDown() throws Exception {
//        cf.close();
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(SimpleClientCommandTest_1.class);
        
        return suite;
    }

    /**
     * Test of connectionClosed method, of class linkclient.SimpleClient.
    
    public void testConnectionClosed() {
        System.out.println("connectionClosed");
        
        SimpleClient instance = new SimpleClient(liste);
        cf.close();

        instance.connectionClosed();
        System.out.println("Done testConnectionClosed");
        
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of connectionException method, of class linkclient.SimpleClient.

    public void testConnectionException() {
        System.out.println("connectionException");
        
        Exception exception = null;
        SimpleClient instance = null;
        
        instance.connectionException(exception);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of connectionEstablished method, of class linkclient.SimpleClient.

    public void testConnectionEstablished() {
        System.out.println("connectionEstablished");
        
        SimpleClient instance = null;
        
        instance.connectionEstablished();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of checkedSendToServer method, of class linkclient.SimpleClient.
     */
    public void testCheckedSendToServer() throws Exception {
        
        Command command = new Command('m', 2);
        System.out.println("testCheckedSendToServer command: "+
                command.getName()+command.getId());
        SimpleClient instance = cf.client;
        
        instance.checkedSendToServer(command);
    }
    /**
     * Test of handleMessageFromServer method, of class linkclient.SimpleClient.

    public void testHandleMessageFromServer(Object msg) {
        System.out.println("testHandleMessageFromServer: msg = "+msg);
        
        Object msg = null;
        SimpleClient instance = cf.client;
        
        instance.handleMessageFromServer(msg);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of getReply method, of class linkclient.SimpleClient.
     */   
    public void testGetReply() {
        
//        SimpleClient_1 instance = cf.client;
        
    }
 
}
