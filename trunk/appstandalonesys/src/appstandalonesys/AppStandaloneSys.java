
/*
 * AppStandaloneSys.java
 *
     The system implements the 'Model/View/Controller'(MVC) pattern
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
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package appstandalonesys;

import appclient.AppClient;
import appgui.*;
import appserver.AppServer;
import interfaces.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Roger
 */
public class AppStandaloneSys extends JFrame implements IMainForm{
  private IServer appServer;
  private IClient appClient;
  private boolean init;
  
  private Button startB =     new Button("Start");
  private Button exitB =      new Button("Exit");
  private TextField count =   new TextField("8");
  private TextField mode =    new TextField("2");  
  private Label countLB =     new Label("Clients(<=12):", Label.RIGHT);
  private Label modeLB =      new Label("Mode(<=3): ", Label.RIGHT);    

  private GUIPanel theGUIPanel;
  private String title1, title2;

 //-------------------------------------------------
     public static void main(String[] args) throws IOException{
        IMainForm appStandaloneSys;
        appStandaloneSys = new AppStandaloneSys(args);        
    }   
//---------------------------------------------------
  public AppStandaloneSys(String[]args){     
    /////////////////////////////////
    title1 = "AppStandaloneSys:  Mode ="; 
    title2 = "Spinners Concurrent Threads: v5 070311";

    //Activate the Server and the Client i.e. the MVC system
    appServer = new AppServer();
    appClient = new AppClient(args, appServer);

    setSize(250,600);    //**Initial size
    init = true;
    //Overwrite defaults with args if any
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e)
      {
        quit();
      }
    });
    
    exitB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        quit();
      }
    });

    startB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        start();
      }
    });

    Panel bottom = new Panel();
    //n rows, m columns, spacing: H, V  NB: rows define size
    bottom.setLayout(new GridLayout(3,2,5,5));

    bottom.add(countLB);
    bottom.add(count);
    bottom.add(modeLB);
    bottom.add(mode);        
    bottom.add(startB);
    bottom.add(exitB);

    setLayout(new BorderLayout(5,5));
    add("South", bottom);

    setVisible(true);
    System.out.println("GUI: made mainFrame ");            
  }
  
  private void initGUIFrame(){
    int n = appClient.getCount()<2?2:appClient.getCount()+1; 
    setSize(200*n/2,600);    
    theGUIPanel = new GUIPanel(this,appClient);      
    add("Center", theGUIPanel);
    setVisible(true);
    super.setTitle(title1+" "+appClient.getSysMode()+".  "+title2);
  }

  public void start(){
    try {
      System.out.println("mainForm.start()");

      if (init == true){
          int clientCount = Integer.parseInt(count.getText());
          int sysMode = Integer.parseInt(mode.getText());
          appClient.setCount(clientCount);
          appClient.setSysMode(sysMode);
          //Pass client args to local server to make DB
          appServer.initServer(clientCount, sysMode);

          initGUIFrame();
          appClient.startClient(this);         

          init = false;
          System.out.println("Done mainForm.start()");
      }
    }
    catch (Exception ex)
    {
          System.out.println("mainForm.start() "+ex.toString());
    }
  }

  public void quit(){
    System.exit(0);
  }
/*    //////////////////////////////////
    // Identify Button[i] as an event source
    public JToggleButton getEventSource(int i){
        return theGUIPanel.getEventSource(i);
    }
*/   
    public IGUIPanel getGUIPanel(){
        return theGUIPanel;
    }  
}
