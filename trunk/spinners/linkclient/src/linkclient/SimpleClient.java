// This file contains material supporting section 10.9 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

/*
 * SimpleClient.java   2001-02-08
 *
 * Copyright (c) 2001 Robert Laganiere and Timothy C. Lethbridge.
 * All Rights Reserved.
 *
 */
package linkclient;

import java.awt.List;
import java.awt.Color;
import client.*;
import java.io.IOException;
import appdata.*;           //rwp...

/**
* The <code> SimpleClient </code> class is a simple subclass
* of the <code> ocsf.server.AbstractClient </code> class.
* It allows testing of the functionalities offered by the
* OCSF framework. The <code> java.awt.List </code> instance
* is used to display informative messages.
* This list is
* pink when the connection has been closed, red
* when an exception is received,
* and green when connected to the server.
*
* @author Dr. Robert Lagani&egrave;re
* @version February 2001
* @see ocsf.server.AbstractServer
*/
public class SimpleClient extends AbstractClient
{
  private List liste;
  Connection connection;
  Reply reply;
//  Mutex mutex;

  public SimpleClient(List liste)
  {
    super("localhost",12345);
    this.liste = liste;
    connection = new Connection();
    reply = new Reply();
//    mutex = new Mutex();
  }

  public SimpleClient(int port, List liste)
  {
    super("localhost",port);
    this.liste = liste;
    connection = new Connection();
    reply = new Reply();
//    mutex = new Mutex();
  }

  public SimpleClient(String host, int port, List liste)
  {
    super(host,port);
    this.liste = liste;
    connection = new Connection();
    reply = new Reply();
//    mutex = new Mutex();
  }

  /**
   * Hook method called after the connection has been closed.
   */
  protected void connectionClosed()
  {
    connection.connectionClosed();
    liste.add("**Connection closed**");
    liste.makeVisible(liste.getItemCount()-1);
    liste.setBackground(Color.pink);
  }

  /**
   * Hook method called each time an exception is thrown by the
   * client's thread that is waiting for messages from the server.
   *
   * @param exception the exception raised.
   */
  protected void connectionException(Exception exception)
  {
    liste.add("**Connection exception: " + exception);
    liste.makeVisible(liste.getItemCount()-1);
    liste.setBackground(Color.red);
  }

  /**
   * Hook method called after a connection has been established.
   */
  protected void connectionEstablished()
  {
    connection.connectionEstablished();
    liste.add("--Connection established");
    liste.makeVisible(liste.getItemCount()-1);
    liste.setBackground(Color.green);
  }

  /**
   * Handles a message sent to the server from this client.
   *
   * @param msg   the message sent.
   */
  public void checkedSendToServer(Object msg) throws IOException{
//      mutex.waitM();
      connection.checkConnection();
      super.sendToServer(msg);
  }

  /**
   * Handles a message sent from the server to this client.
   *
   * @param msg   the message sent.
   */
  protected void handleMessageFromServer(Object msg){
    System.out.println("SimpleClient.handleMessageFromServer = "+msg);
    reply.replyMade(msg);
//    mutex.signalM();
  }
  
  public Object getReply(){
    Object ob = new Object();
    ob = reply.getReply();
//    mutex.signalM();
    return ob;
  }
}
////////////////////////////////////////////////////////////
class Connection{
    boolean connectionMade;
    
    public Connection(){
        connectionMade = false;
    }
 
    public synchronized void connectionEstablished(){
        connectionMade = true;
        System.out.println("Connection: connectionMade = "+connectionMade);
//        notifyAll();
        notify();
    }
    
    public synchronized void connectionClosed(){
        connectionMade = false;
        System.out.println("Connection: connectionMade = "+connectionMade);
    }
    
    public synchronized void checkConnection(){
        try{
            while (!connectionMade){
                System.out.println("Connection: Waiting ");
                wait();
            }
        }catch(InterruptedException e){
            System.out.println("Interrupted wait");
        }
    }
}
////////////////////////////////////////////////////////////
class Reply{
    boolean replyMade;
    Object msg1;
    int mutex;
    
    public Reply(){
        replyMade = false;
        msg1 = new Object();
        mutex = 1;
    }
     
    public synchronized void replyMade(Object msg){
        replyMade = true;
        this.msg1 = msg;
        notify();    //notify() by itself does not necessarily notify the right one
    }
    
    public synchronized Object getReply(){
        try{
            while (!replyMade)
            {
                System.out.println("Reply: Waiting ");
                wait();
                System.out.println("Reply: Done Wait ");                
            }
        }catch(InterruptedException e){
            System.out.println("Interrupted wait");
        }

        replyMade = false;
        System.out.println("Reply: getReply =" +replyMade);
        return msg1;
    }
 }   
//////////////////////////////////////////////////////////////
/*Test code for receiving messages by activating the 'Get' button
 *in the TestPackages|linkclient|ClientFrame.  Used instead of
 *replyMade()/getReply() above.
 *Global object
      Object messageObject;
 *Instantiated in constructor  
      messageObject = new Object();
 *Method to save the message from the link
     protected void handleMessageFromServer(Object msg)
      { messageObject = msg;
        System.out.println("SimpleClient_1.handleMessageFromServer = "+msg);
      }
 *Method to get the saved message using the 'Get' button
      public Object getReply(){
          return messageObject;
      }
*/
