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
    public void startClient();
    public Observable getObservable(int i);
    public int getSysMode();
    public int getCount();
    public ActionListener getListener(int i);
}

