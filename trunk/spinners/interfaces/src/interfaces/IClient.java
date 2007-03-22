/*
 * IClient.java
 *
 * Created on 16 November 2006, 17:03
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package interfaces;

import java.awt.event.ActionListener;
import java.util.Observable;

/**
 *
 * @author Roger
 */
public interface IClient {
    public void initClient();
    public void startClient();
    public IAppController getViewable(int i);
    public int getSysMode();
    public void setSysMode(int val);    
    public int getCount();
    public void setCount(int val);

}

