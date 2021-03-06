/*
 * nbMainForm.java
 *
 * Created on 08 October 2006, 12:37
 */

package appmatissegui;

import appgui.displayCanvas;
import appmodel.AppModel;
import javax.swing.*;
//import thread_spinners.stepSpinner;

/**
 *
 * @author  Roger
 */
public class MainForm extends javax.swing.JFrame{
    private DefaultListModel dlm = new DefaultListModel();
    
    int theMode;
    AppModel refModel;
    String theTitle;
    int theId;
    
    /**
     * Creates new form nbMainForm Display
     */
    public MainForm(AppModel aModel, String title,int sysMode,int clientCount) {
        theMode = sysMode;
        refModel = aModel;
        theTitle = title;
        if (theMode != 3){
            setSize(800,600);
        }else {
            setSize(400,600);}
        
        initComponents();
    }
    
    //////////////////////////////////
    // Identify Button[i] as an event source
    public JToggleButton getEventSource(int i){
        JToggleButton b = new JToggleButton();
        switch (i){
            case 0: b = Button0; break;
            case 1: b = Button1; break;
            case 2: b = Button2; break;
            case 3: b = Button3; break;
        }
        return b;
    }
    
//////////////////////////////////////////////////////////////////////////////
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        try {
            theGUIPanel =(javax.swing.JPanel)java.beans.Beans.instantiate(getClass().getClassLoader(), "appmatissegui.mainForm_theGUIPanel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        theSpinnerPanel0 = new javax.swing.JPanel();

        theId = 0;
        displayCanvas0 = new displayCanvas(refModel, theId);
        parameterPanel0 = new javax.swing.JPanel();
        Button0 = new javax.swing.JToggleButton();
        spinnerLabel0 = new javax.swing.JLabel();
        theSpinnerPanel1 = new javax.swing.JPanel();

        theId = 1;
        displayCanvas1 = new displayCanvas(refModel, theId);
        parameterPanel1 = new javax.swing.JPanel();
        Button1 = new javax.swing.JToggleButton();
        spinnerLabel1 = new javax.swing.JLabel();
        theSpinnerPanel2 = new javax.swing.JPanel();

        theId = 2;
        displayCanvas2 = new displayCanvas(refModel, theId);
        parameterPanel2 = new javax.swing.JPanel();
        Button2 = new javax.swing.JToggleButton();
        spinnerLabel2 = new javax.swing.JLabel();
        theSpinnerPanel3 = new javax.swing.JPanel();

        theId = 3;
        displayCanvas3 = new displayCanvas(refModel, theId);
        parameterPanel3 = new javax.swing.JPanel();
        Button3 = new javax.swing.JToggleButton();
        spinnerLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(theTitle + theMode);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(0, 153, 102));
        setMaximizedBounds(new java.awt.Rectangle(50, 50, 800, 600));
        setName("theGUI frame");
        theSpinnerPanel0.setBackground(new java.awt.Color(230, 230, 230));
        theSpinnerPanel0.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 255)));
        theSpinnerPanel0.setToolTipText("spinnerPanel");
        theSpinnerPanel0.setName("Spinner 0");
        displayCanvas0.setBackground(new java.awt.Color(0, 0, 0));
        displayCanvas0.setForeground(new java.awt.Color(0, 0, 0));
        displayCanvas0.setName("displayCanvas");
        //*********************

        parameterPanel0.setBackground(new java.awt.Color(255, 255, 255));
        parameterPanel0.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 255)));
        Button0.setBackground(new java.awt.Color(102, 255, 255));
        Button0.setText("Click to Run");
        Button0.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button0.setMaximumSize(new java.awt.Dimension(85, 23));
        Button0.setMinimumSize(new java.awt.Dimension(10, 10));
        Button0.setPreferredSize(new java.awt.Dimension(85, 23));

        spinnerLabel0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        spinnerLabel0.setLabelFor(Button0);
        spinnerLabel0.setText(theSpinnerPanel0.getName());
        spinnerLabel0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        spinnerLabel0.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        spinnerLabel0.setMinimumSize(new java.awt.Dimension(10, 10));

        org.jdesktop.layout.GroupLayout parameterPanel0Layout = new org.jdesktop.layout.GroupLayout(parameterPanel0);
        parameterPanel0.setLayout(parameterPanel0Layout);
        parameterPanel0Layout.setHorizontalGroup(
            parameterPanel0Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, parameterPanel0Layout.createSequentialGroup()
                .addContainerGap()
                .add(parameterPanel0Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, Button0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, spinnerLabel0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                .addContainerGap())
        );

        parameterPanel0Layout.linkSize(new java.awt.Component[] {Button0, spinnerLabel0}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        parameterPanel0Layout.setVerticalGroup(
            parameterPanel0Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, parameterPanel0Layout.createSequentialGroup()
                .addContainerGap()
                .add(Button0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(spinnerLabel0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .add(47, 47, 47))
        );

        parameterPanel0Layout.linkSize(new java.awt.Component[] {Button0, spinnerLabel0}, org.jdesktop.layout.GroupLayout.VERTICAL);

        org.jdesktop.layout.GroupLayout theSpinnerPanel0Layout = new org.jdesktop.layout.GroupLayout(theSpinnerPanel0);
        theSpinnerPanel0.setLayout(theSpinnerPanel0Layout);
        theSpinnerPanel0Layout.setHorizontalGroup(
            theSpinnerPanel0Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(theSpinnerPanel0Layout.createSequentialGroup()
                .addContainerGap()
                .add(parameterPanel0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .add(10, 10, 10)
                .add(displayCanvas0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        theSpinnerPanel0Layout.linkSize(new java.awt.Component[] {displayCanvas0, parameterPanel0}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        theSpinnerPanel0Layout.setVerticalGroup(
            theSpinnerPanel0Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(theSpinnerPanel0Layout.createSequentialGroup()
                .add(18, 18, 18)
                .add(theSpinnerPanel0Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(displayCanvas0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(parameterPanel0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(20, 20, 20))
        );

        theSpinnerPanel0Layout.linkSize(new java.awt.Component[] {displayCanvas0, parameterPanel0}, org.jdesktop.layout.GroupLayout.VERTICAL);

        theSpinnerPanel1.setBackground(new java.awt.Color(230, 230, 230));
        theSpinnerPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 255)));
        theSpinnerPanel1.setToolTipText("spinnerPanel");
        theSpinnerPanel1.setName("Spinner 1");
        displayCanvas1.setBackground(new java.awt.Color(0, 0, 0));
        displayCanvas1.setForeground(new java.awt.Color(0, 0, 0));
        displayCanvas1.setName("displayCanvas");

        parameterPanel1.setBackground(new java.awt.Color(255, 255, 255));
        parameterPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 255)));
        Button1.setBackground(new java.awt.Color(102, 255, 255));
        Button1.setText("Click to run");
        Button1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button1.setMinimumSize(new java.awt.Dimension(10, 10));

        spinnerLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        spinnerLabel1.setLabelFor(Button1);
        spinnerLabel1.setText(theSpinnerPanel1.getName());
        spinnerLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        spinnerLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        spinnerLabel1.setMinimumSize(new java.awt.Dimension(10, 10));

        org.jdesktop.layout.GroupLayout parameterPanel1Layout = new org.jdesktop.layout.GroupLayout(parameterPanel1);
        parameterPanel1.setLayout(parameterPanel1Layout);
        parameterPanel1Layout.setHorizontalGroup(
            parameterPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(parameterPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(parameterPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(parameterPanel1Layout.createSequentialGroup()
                        .add(Button1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(parameterPanel1Layout.createSequentialGroup()
                        .add(spinnerLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                        .add(18, 18, 18))))
        );

        parameterPanel1Layout.linkSize(new java.awt.Component[] {Button1, spinnerLabel1}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        parameterPanel1Layout.setVerticalGroup(
            parameterPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(parameterPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(Button1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(spinnerLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        parameterPanel1Layout.linkSize(new java.awt.Component[] {Button1, spinnerLabel1}, org.jdesktop.layout.GroupLayout.VERTICAL);

        org.jdesktop.layout.GroupLayout theSpinnerPanel1Layout = new org.jdesktop.layout.GroupLayout(theSpinnerPanel1);
        theSpinnerPanel1.setLayout(theSpinnerPanel1Layout);
        theSpinnerPanel1Layout.setHorizontalGroup(
            theSpinnerPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(theSpinnerPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(parameterPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(displayCanvas1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        theSpinnerPanel1Layout.linkSize(new java.awt.Component[] {displayCanvas1, parameterPanel1}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        theSpinnerPanel1Layout.setVerticalGroup(
            theSpinnerPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(theSpinnerPanel1Layout.createSequentialGroup()
                .add(19, 19, 19)
                .add(theSpinnerPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(displayCanvas1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, parameterPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        theSpinnerPanel2.setBackground(new java.awt.Color(230, 230, 230));
        theSpinnerPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 255)));
        theSpinnerPanel2.setToolTipText("spinnerPanel");
        theSpinnerPanel2.setName("Spinner 2");
        displayCanvas2.setBackground(new java.awt.Color(0, 0, 0));
        displayCanvas2.setForeground(new java.awt.Color(0, 0, 0));
        displayCanvas2.setName("displayCanvas");

        parameterPanel2.setBackground(new java.awt.Color(255, 255, 255));
        parameterPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 255)));
        Button2.setBackground(new java.awt.Color(102, 255, 255));
        Button2.setText("Click to run");
        Button2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button2.setMinimumSize(new java.awt.Dimension(10, 10));

        spinnerLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        spinnerLabel2.setLabelFor(Button2);
        spinnerLabel2.setText(theSpinnerPanel2.getName());
        spinnerLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        spinnerLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        spinnerLabel2.setMinimumSize(new java.awt.Dimension(10, 10));

        org.jdesktop.layout.GroupLayout parameterPanel2Layout = new org.jdesktop.layout.GroupLayout(parameterPanel2);
        parameterPanel2.setLayout(parameterPanel2Layout);
        parameterPanel2Layout.setHorizontalGroup(
            parameterPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(parameterPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(parameterPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(parameterPanel2Layout.createSequentialGroup()
                        .add(Button2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(parameterPanel2Layout.createSequentialGroup()
                        .add(spinnerLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                        .add(20, 20, 20))))
        );

        parameterPanel2Layout.linkSize(new java.awt.Component[] {Button2, spinnerLabel2}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        parameterPanel2Layout.setVerticalGroup(
            parameterPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(parameterPanel2Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(Button2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(spinnerLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .add(49, 49, 49))
        );

        parameterPanel2Layout.linkSize(new java.awt.Component[] {Button2, spinnerLabel2}, org.jdesktop.layout.GroupLayout.VERTICAL);

        org.jdesktop.layout.GroupLayout theSpinnerPanel2Layout = new org.jdesktop.layout.GroupLayout(theSpinnerPanel2);
        theSpinnerPanel2.setLayout(theSpinnerPanel2Layout);
        theSpinnerPanel2Layout.setHorizontalGroup(
            theSpinnerPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, theSpinnerPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(parameterPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(displayCanvas2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        theSpinnerPanel2Layout.setVerticalGroup(
            theSpinnerPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, theSpinnerPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(theSpinnerPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, parameterPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, displayCanvas2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        theSpinnerPanel2Layout.linkSize(new java.awt.Component[] {displayCanvas2, parameterPanel2}, org.jdesktop.layout.GroupLayout.VERTICAL);

        theSpinnerPanel3.setBackground(new java.awt.Color(230, 230, 230));
        theSpinnerPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 255)));
        theSpinnerPanel3.setToolTipText("spinnerPanel");
        theSpinnerPanel3.setName("Spinner 3");
        displayCanvas3.setBackground(new java.awt.Color(0, 0, 0));
        displayCanvas3.setForeground(new java.awt.Color(0, 0, 0));
        displayCanvas3.setName("displayCanvas");

        parameterPanel3.setBackground(new java.awt.Color(255, 255, 255));
        parameterPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 255)));
        Button3.setBackground(new java.awt.Color(102, 255, 255));
        Button3.setText("Click to run");
        Button3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button3.setMinimumSize(new java.awt.Dimension(10, 10));

        spinnerLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        spinnerLabel3.setLabelFor(Button3);
        spinnerLabel3.setText(theSpinnerPanel3.getName());
        spinnerLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        spinnerLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        spinnerLabel3.setMinimumSize(new java.awt.Dimension(10, 10));

        org.jdesktop.layout.GroupLayout parameterPanel3Layout = new org.jdesktop.layout.GroupLayout(parameterPanel3);
        parameterPanel3.setLayout(parameterPanel3Layout);
        parameterPanel3Layout.setHorizontalGroup(
            parameterPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(parameterPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(parameterPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(parameterPanel3Layout.createSequentialGroup()
                        .add(Button3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(18, Short.MAX_VALUE))
                    .add(parameterPanel3Layout.createSequentialGroup()
                        .add(spinnerLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                        .add(18, 18, 18))))
        );

        parameterPanel3Layout.linkSize(new java.awt.Component[] {Button3, spinnerLabel3}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        parameterPanel3Layout.setVerticalGroup(
            parameterPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(parameterPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(Button3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .add(14, 14, 14)
                .add(spinnerLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        parameterPanel3Layout.linkSize(new java.awt.Component[] {Button3, spinnerLabel3}, org.jdesktop.layout.GroupLayout.VERTICAL);

        org.jdesktop.layout.GroupLayout theSpinnerPanel3Layout = new org.jdesktop.layout.GroupLayout(theSpinnerPanel3);
        theSpinnerPanel3.setLayout(theSpinnerPanel3Layout);
        theSpinnerPanel3Layout.setHorizontalGroup(
            theSpinnerPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, theSpinnerPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(parameterPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(displayCanvas3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        theSpinnerPanel3Layout.linkSize(new java.awt.Component[] {displayCanvas3, parameterPanel3}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        theSpinnerPanel3Layout.setVerticalGroup(
            theSpinnerPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(theSpinnerPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(theSpinnerPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(theSpinnerPanel3Layout.createSequentialGroup()
                        .add(parameterPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(theSpinnerPanel3Layout.createSequentialGroup()
                        .add(displayCanvas3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(24, 24, 24))))
        );

        theSpinnerPanel3Layout.linkSize(new java.awt.Component[] {displayCanvas3, parameterPanel3}, org.jdesktop.layout.GroupLayout.VERTICAL);

        org.jdesktop.layout.GroupLayout theGUIPanelLayout = new org.jdesktop.layout.GroupLayout(theGUIPanel);
        theGUIPanel.setLayout(theGUIPanelLayout);
        theGUIPanelLayout.setHorizontalGroup(
            theGUIPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(theGUIPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(theGUIPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(theSpinnerPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(theSpinnerPanel0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(14, 14, 14)
                .add(theGUIPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(theSpinnerPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(theSpinnerPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(19, 19, 19))
        );
        theGUIPanelLayout.setVerticalGroup(
            theGUIPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, theGUIPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(theGUIPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(theSpinnerPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(theSpinnerPanel0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(theGUIPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(theGUIPanelLayout.createSequentialGroup()
                        .add(16, 16, 16)
                        .add(theSpinnerPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, theGUIPanelLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(theSpinnerPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        theGUIPanelLayout.linkSize(new java.awt.Component[] {theSpinnerPanel2, theSpinnerPanel3}, org.jdesktop.layout.GroupLayout.VERTICAL);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(theGUIPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(theGUIPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
/*    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nbMainForm().setVisible(true);
            }
        });
    }
 */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Button0;
    private javax.swing.JToggleButton Button1;
    private javax.swing.JToggleButton Button2;
    private javax.swing.JToggleButton Button3;
    private java.awt.Canvas displayCanvas0;
    private java.awt.Canvas displayCanvas1;
    private java.awt.Canvas displayCanvas2;
    private java.awt.Canvas displayCanvas3;
    private javax.swing.JPanel parameterPanel0;
    private javax.swing.JPanel parameterPanel1;
    private javax.swing.JPanel parameterPanel2;
    private javax.swing.JPanel parameterPanel3;
    private javax.swing.JLabel spinnerLabel0;
    private javax.swing.JLabel spinnerLabel1;
    private javax.swing.JLabel spinnerLabel2;
    private javax.swing.JLabel spinnerLabel3;
    private javax.swing.JPanel theGUIPanel;
    private javax.swing.JPanel theSpinnerPanel0;
    private javax.swing.JPanel theSpinnerPanel1;
    private javax.swing.JPanel theSpinnerPanel2;
    private javax.swing.JPanel theSpinnerPanel3;
    // End of variables declaration//GEN-END:variables
    
}
