// This file contains material supporting section 10.9 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

/*
 * ClientFrame.java   2001-02-08
 *
 * Copyright (c) 2001 Robert Laganiere and Timothy C. Lethbridge.
 * All Rights Reserved.
 *
 */
package appclientsys;

import appclient.AppClient;
import appgui.GUIPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import linkclient.*;
import client.*;

/**
* The <code> ClientFrame </code> class is a simple interactive
* application made to exercise the OCSF framework.<p>
* Type <code>java ocsftester.ClientFrame host port_number</code> to start
* one client.<p>
* The window is
* pink when the connection has been closed, red
* when an exception is received,
* and green when connected to the server.
*
* @author Dr. Robert Lagani&egrave;re
* @version February 2001
* @see ocsftester.SimpleClient
*/
public class ClientFrame extends JFrame{
  private InterClient interClient;
  private SimpleClient simpleClient;
  private List liste;
  
  private Button closeB =     new Button("Close");
  private Button openB =      new Button("Open");
  private Button sendB =      new Button("Send");
  private Button quitB =      new Button("Quit");
  private TextField port =    new TextField("12345");
  private TextField host =    new TextField("localhost");
  private TextField message = new TextField();
  private Label portLB =      new Label("Port: ", Label.RIGHT);
  private Label hostLB =      new Label("Host: ", Label.RIGHT);
  private Label messageLB =   new Label("Message: ", Label.RIGHT);
  private GUIPanel theGUIPanel;
  
  public ClientFrame(AppClient appClient, InterClient interClient, String title){
    this.interClient = interClient;
    simpleClient = interClient.getSimpleClient();
    liste = interClient.getList();
    
    int sysMode = appClient.getSysMode();
    super.setTitle(title+sysMode);
    int clientCount = appClient.getCount();
    if (clientCount > 2){
//        setSize(850,800);
        setSize(400,600);        
    }else {
        setSize(400,800);}
    
    port.setText(String.valueOf(simpleClient.getPort()));
    host.setText(simpleClient.getHost());

    theGUIPanel = new GUIPanel(appClient);
    
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

    openB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        open();
      }
    });

    sendB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        send();
      }
    });

    Panel bottom = new Panel();
    bottom.setLayout(new GridLayout(5,2,5,5));

    bottom.add(hostLB);
    bottom.add(host);
    bottom.add(portLB);
    bottom.add(port);
    bottom.add(messageLB);
    bottom.add(message);
    bottom.add(openB);
    bottom.add(sendB);
    bottom.add(closeB);
    bottom.add(quitB);

    setLayout(new BorderLayout(5,5));
    add("North", liste);
    add("Center", theGUIPanel);
    add("South", bottom);

    setVisible(true);
    System.out.println("GUI: made ClientFrame ");            
  }

  private void readFields()
  {
    int p = Integer.parseInt(port.getText());

    simpleClient.setPort(p);
    simpleClient.setHost(host.getText());
  }

  public void close()
  {
    try {
      readFields();
      simpleClient.closeConnection();
    }
    catch (Exception ex)
    {
      liste.add(ex.toString());
      liste.makeVisible(liste.getItemCount()-1);
      liste.setBackground(Color.red);
    }
  }

  public void open()
  {
    try {
      System.out.println("clientFrame");
  
      readFields();
      
      simpleClient.openConnection();
    }
    catch (Exception ex)
    {
      liste.add(ex.toString());
      liste.makeVisible(liste.getItemCount()-1);
      liste.setBackground(Color.red);
    }
  }

  public void send()
  {
    try {
      readFields();
      interClient.messageSent(message.getText());
//      simpleClient.sendToServer('m'+ message.getText());
    }
    catch (Exception ex)
    {
      liste.add(ex.toString());
      liste.makeVisible(liste.getItemCount()-1);
      liste.setBackground(Color.yellow);
    }
  }

  public void quit()
  {
    System.exit(0);
  }
    //////////////////////////////////
    // Identify Button[i] as an event source
    public JToggleButton getEventSource(int i){
        return theGUIPanel.getEventSource(i);
    }

}

