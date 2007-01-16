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
import interfaces.IServer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import linkclient.*;

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
  private AppClient refClient;
  private IServer interClient;
  private SimpleClient simpleClient;
  private List liste;
  private String refTitle1, refTitle2;
  private boolean init;
  
  private Button closeB =     new Button("Close");
  private Button openB =      new Button("Open");
  private Button sendB =      new Button("Send");
  private Button quitB =      new Button("Quit");
  private TextField port =    new TextField("12345");
  private TextField host =    new TextField("localhost");
  private TextField count =   new TextField("12");
  private TextField mode =    new TextField("2");  
  private TextField message = new TextField();
  private Label portLB =      new Label("Port: ", Label.RIGHT);
  private Label hostLB =      new Label("Host: ", Label.RIGHT);
  private Label countLB =     new Label("Clients(<=12):", Label.RIGHT);
  private Label modeLB =      new Label("Mode(<=3): ", Label.RIGHT);    
  private Label messageLB =   new Label("Message: ", Label.RIGHT);
  private GUIPanel theGUIPanel;
  
  public ClientFrame(AppClient appClient, InterClient interClient,
          String title1, String title2){
    refClient = appClient;
    refTitle1 = title1;
    refTitle2 = title2;
    this.interClient = interClient;
    simpleClient = interClient.getSimpleClient();
    liste = interClient.getList();
    init = true;
    System.out.println("ClientFrame().init = "+init+
            " appClient = "+appClient+" theGUIPanel = "+theGUIPanel);            
    
    setSize(250,600);    //**Initial size before opening network
    //Overwrite defaults with args if any
    port.setText(String.valueOf(simpleClient.getPort()));
    host.setText(simpleClient.getHost());
    
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
    //4 rows, 4 columns, spacing: H, V  NB: rows define size
    bottom.setLayout(new GridLayout(4,4,5,5));

    bottom.add(hostLB);
    bottom.add(host);
    bottom.add(portLB);
    bottom.add(port);
    bottom.add(countLB);
    bottom.add(count);
    bottom.add(modeLB);
    bottom.add(mode);        
    bottom.add(messageLB);
    bottom.add(message);
    bottom.add(openB);
    bottom.add(sendB);
    bottom.add(closeB);
    bottom.add(quitB);

    setLayout(new BorderLayout(5,5));
    add("North", liste);
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
  
  private void initGUIFrame(){
    int n = refClient.getCount()<2?2:refClient.getCount()+1; 
    setSize(200*n/2,600);    
    theGUIPanel = new GUIPanel(refClient);
    
    add("Center", theGUIPanel);
    setVisible(true);
  
    super.setTitle(refTitle1+" "+refClient.getSysMode()+".  "+refTitle2);
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
      System.out.println("clientFrame.open()");
  
      readFields();
      simpleClient.openConnection();
      simpleClient.checkConnection();
      if (init == true){
          int clientCount = Integer.parseInt(count.getText());
          int sysMode = Integer.parseInt(mode.getText());
          refClient.setCount(clientCount);
          refClient.setSysMode(sysMode);
          
          //server is remote -- set it up via network
          interClient.initServer(clientCount, sysMode);
   
          refClient.initClient();
          initGUIFrame();
          refClient.startClient();
          liste.add("Mode = "+sysMode+"; Count = "+clientCount);          

          init = false;
          System.out.println("clientFrame.open()");
      }
    }
    catch (Exception ex)
    {
      liste.add(ex.toString()+": on open");
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
}

