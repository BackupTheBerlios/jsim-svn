/*
 * AppClientSys2.java
 *
 *      The system implements the 'Model/View/Controller'(MVC) pattern
     The 'MVC' objects are defined by status/records and are
     found in the appModel. 
     a) The 'Model' objects are found in the Server
          and use the appServer 

     b) The 'Controller' objects are found in the Client:
          get parameters from the model to control the views e.g. Spinners

     c) The 'View' objects are located in the GUI panels, and
          display according to algorithms in the controller, as required
          by the model.

 * Created on 19 February 2007, 15:59
//
// This file contains material supporting section 10.9 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

/*
 * Based on ClientFrame.java   2001-02-08
 *
 * Copyright (c) 2001 Robert Laganiere and Timothy C. Lethbridge.
 * All Rights Reserved.
 *
 */
package appclientsys;

import appclient.AppClient;
import appgui.GUIPanel;
import interfaces.IClient;
import interfaces.IMainForm;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
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
public class AppClientSys2 extends JFrame implements IMainForm{
  private IClient appClient;
  private InterClient interClient;
  private boolean init;
  
  private SimpleClient simpleClient;
  private List liste;
  
  private Button closeB =     new Button("Close");
  private Button openB =      new Button("Open");
  private Button sendB =      new Button("Send");
  private Button quitB =      new Button("Quit");
  private TextField port =    new TextField("12345");
  private TextField host =    new TextField("localhost");
  private TextField count =   new TextField("8");
  private TextField mode =    new TextField("2");  
  private TextField message = new TextField();
  private Label portLB =      new Label("Port: ", Label.RIGHT);
  private Label hostLB =      new Label("Host: ", Label.RIGHT);
  private Label countLB =     new Label("Clients(<=12):", Label.RIGHT);
  private Label modeLB =      new Label("Mode(<=3): ", Label.RIGHT);    
  private Label messageLB =   new Label("Message: ", Label.RIGHT);

  GUIPanel theGUIPanel;
  private String title1, title2;

//----------------------------------------------------
    public static void main(String[] args) throws IOException{
        IMainForm appClientSys;
        appClientSys = new AppClientSys2(args);
    }
//-------------------------------------------------------
  
  public AppClientSys2(String[]args){
    //////////////////////////
    title1 = "appClient: Mode = ";
    title2 = "Spinners Concurrent Client/Server: v6 070312.  ";

    interClient = new InterClient(args);
    appClient = new AppClient(args, interClient);

    simpleClient = interClient.getSimpleClient();
    liste = interClient.getList();
    
    setSize(250,600);    //**Initial size before opening network
    init = true;
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

  private void initGUIFrame(){
    int n = appClient.getCount()<2?2:appClient.getCount()+1; 
    setSize(200*n/2,600);    
    theGUIPanel = new GUIPanel(this,appClient);    
    add("Center", theGUIPanel);
    setVisible(true);  
    super.setTitle(title1+" "+appClient.getSysMode()+".  "+title2);
  }
  
  public void open(){
    try {
      System.out.println("clientFrame.open()");
  
      readFields();
      simpleClient.openConnection();
      simpleClient.checkConnection();
      if (init == true){
          int clientCount = Integer.parseInt(count.getText());
          int sysMode = Integer.parseInt(mode.getText());
          appClient.setCount(clientCount);
          appClient.setSysMode(sysMode);
          
          //server is remote -- set it up via network
          interClient.initServer(clientCount, sysMode);
   
          initGUIFrame();
          appClient.startClient(this);
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
  {//Send button requires 'm' as first character, to echo message
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
  
  public GUIPanel getGUIPanel(){
    return theGUIPanel;
  }  
}

//////////////////////////////////////////////////
//package appclientsys;
//
//import appclient.*;
//import interfaces.*;
//import java.io.IOException;
//import linkclient.*;
//
///**
// * 
// * 
// * @author Roger
// * 
// */
//public class AppClientSys2 {
//        
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws IOException{
//        /////////////////////////////////
//        String title1 = "appClient: Mode = ";
//        String title2 = "Spinners Concurrent Client/Server: v5 0611120.  ";
//        // The system implements the 'Model/View/Controller'(MVC) pattern
//        // The 'Model' objects are defined by status/records and are
//        // found in the appModel. 
//        AppClient appClient;
//        // a) The appRunners use these objects to execute the task threads
//        //              AppRunner appRunner
//        // b) The appServer provides this data
//        IServer interClient;
//        //      as parameters to implement the app's e.g. Spinners
//        //              Spinner spinner;
//        // c) The 'View/Controller' objects -- the GUI:
//        ClientFrame cf;
//        //Activate the Server and the Model i.e. the Server/Model/GUI system
//        interClient = new InterClient(args);
//        appClient = new AppClient(args, interClient);
//        // initServer(), initClient(), initGUI() and start the model        
//        cf = new ClientFrame(appClient,(InterClient)interClient,title1,title2);
//    }
//}
//
