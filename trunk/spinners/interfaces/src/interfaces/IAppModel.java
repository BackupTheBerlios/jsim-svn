/*
 * IAppModel.java
 *
 * Created on 02 March 2007, 14:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package interfaces;

/**
 *
 * @author Roger
 */
public interface IAppModel {
    IRecord update(IRecord record);
    void setMaxFrameIndex(int val);
}
