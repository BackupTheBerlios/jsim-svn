/*
 * IAppRunner.java
 *
 * Created on 14 February 2007, 21:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package interfaces;

/**
 *
 * @author Roger
 */
public interface IAppRunner {
    public IAppController getAppController();
    public IRecord update();
}
