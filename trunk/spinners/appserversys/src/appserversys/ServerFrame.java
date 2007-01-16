// This file contains material supporting section 10.9 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

/*
 * ServerFrame.java   2001-02-08
 *
 * Copyright (c) 2001 Robert Laganiere and Timothy C. Lethbridge.
 * All Rights Reserved.
 *
 */
package appserversys;

import appserver.AppServer;
import interfaces.*;
import java.awt.*;
import java.awt.event.*;
import linkserver.*;
import server.*;


/**
* The <code> ServerFrame </code> class is a simple interactive
* application made to exercise the OCSF framework.<p>
* Type <code>java ocsftester.ServerFrame port_number</code> to start
* the server.<p>
* The window is red
* when the server is closed, yellow when the server is stopped
* and green when open.
*
* @author Dr. Robert Lagani&egrave;re
* @version February 2001
* @see ocsftester.SimpleServer
*/
public class ServerFrame extends Frame{
  private AppServer refServer;
  private SimpleServer simpleServer;
  private List liste;
  private String refTitle1;
  private String refTitle2;
//  private boolean init;

  private Button closeB =     new Button("Close");
  private Button listenB =    new Button("Listen");
  private Button stopB =      new Button("Stop");
  private Button quitB =      new Button("Quit");
  private TextField port =    new TextField("12345");
  private TextField backlog = new TextField("5");
  private TextField timeout = new TextField("500");
  private Label portLB =      new Label("Port: ", Label.RIGHT);
  private Label timeoutLB =   new Label("Timeout: ", Label.RIGHT);
  private Label backlogLB =   new Label("Backlog: ", Label.RIGHT);


  public ServerFrame(InterServer interServer, AppServer appServer,
                        String title1, String title2){
    refServer = appServer;
    simpleServer = interServer.getSimpleServer();
    liste = interServer.getList();
    refTitle1 = title1;
    refTitle2 = title2;
    
    port.setText(String.valueOf(simpleServer.getPort()));

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e)
      {
        quit();
      }
    });

    quitB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        quit();
      }
    });

    closeB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        close();
      }
    });

    listenB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        listen();
      }
    });

    stopB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        stop();
      }
    });

    Panel bottom = new Panel();
    bottom.setLayout(new GridLayout(4,4,5,5));

    bottom.add(portLB);
    bottom.add(port);
    bottom.add(backlogLB);
    bottom.add(backlog);
    bottom.add(timeoutLB);
    bottom.add(timeout);
    bottom.add(listenB);
    bottom.add(stopB);
    bottom.add(closeB);
    bottom.add(quitB);

    setLayout(new BorderLayout(5,5));
    add("Center", liste);
    add("South", bottom);
    setSize(350,400);
    setVisible(true);
  }

  private void readFields()
  {
    int p = Integer.parseInt(port.getText());
    int t = Integer.parseInt(timeout.getText());
    int b = Integer.parseInt(backlog.getText());
    
    simpleServer.setPort(p);
    simpleServer.setBacklog(b);
    simpleServer.setTimeout(t);
  }  

  public void close()
  {
    System.out.println("SvrFrame close:Number of clients = " + simpleServer.getNumberOfClients());
    try {
      readFields();
      simpleServer.close();
    }
    catch (Exception ex)
    {
      liste.add(ex.toString());
      liste.makeVisible(liste.getItemCount()-1);
      liste.setBackground(Color.red);
    }
  }

  public void listen()
  {
    System.out.println("SvrFrame listen:Number of clients = " + simpleServer.getNumberOfClients());
    try {
      readFields();
      simpleServer.listen();
    }
    catch (Exception ex)
    {
      liste.add(ex.toString());
      liste.makeVisible(liste.getItemCount()-1);
      liste.setBackground(Color.red);
    }
  }

  public void stop()
  {
    System.out.println("SvrFrame stop:Number of clients = " + simpleServer.getNumberOfClients());
    readFields();
    simpleServer.stopListening();
  }

  public void quit()
  {
    System.exit(0);
  }


}


