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

import appgui.GUIPanel;
import interfaces.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import linkclient.SimpleClient;

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
public class ClientFrame extends JFrame
{
  IComm refServer;
  private SimpleClient refClient;
  private List refListe;
  
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
  
  public ClientFrame(IClient appClient, String title, IComm appServer)
  {
    refServer = appServer;
    refClient = refServer.getClient();
    refListe = refServer.getList();
    
    int sysMode = appClient.getSysMode();
    super.setTitle(title+sysMode);
    int clientCount = appClient.getCount();
    if (clientCount > 2){
        setSize(850,800);
    }else {
        setSize(400,800);}
    
    port.setText(String.valueOf(refClient.getPort()));
    host.setText(refClient.getHost());

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
    add("North", refListe);
    add("Center", theGUIPanel);
    add("South", bottom);

    setVisible(true);
    System.out.println("GUI: made ClientFrame ");            
  }

  private void readFields()
  {
    int p = Integer.parseInt(port.getText());

    refServer.setPort(p);
    refServer.setHost(host.getText());
  }

  public void close()
  {
    try {
      readFields();
      refClient.closeConnection();
    }
    catch (Exception ex)
    {
      refListe.add(ex.toString());
      refListe.makeVisible(refListe.getItemCount()-1);
      refListe.setBackground(Color.red);
    }
  }

  public void open()
  {
    try {
      System.out.println("clientFrame: refServer = "+refServer);//**
  
      readFields();
      
      refClient.openConnection();
    }
    catch (Exception ex)
    {
      refListe.add(ex.toString());
      refListe.makeVisible(refListe.getItemCount()-1);
      refListe.setBackground(Color.red);
    }
  }

  public void send()
  {
    try {
      readFields();
      refClient.sendToServer('m'+ message.getText());
    }
    catch (Exception ex)
    {
      refListe.add(ex.toString());
      refListe.makeVisible(refListe.getItemCount()-1);
      refListe.setBackground(Color.yellow);
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

