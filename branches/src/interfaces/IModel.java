/*
 * IModel.java
 *
 * Created on 16 November 2006, 17:03
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package interfaces;

import java.util.Observable;

/**
 *
 * @author Roger
 */
public interface IModel {
    public void startModel();
    public Observable getObservable(int i);
    public int getMode(int i);
    public int getCount();      
}
